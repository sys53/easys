package com.easys.platform.engine.runtime;

import com.easys.platform.EasysException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 业务拦截器，主要拦截异常及错误信息，最好配合RunBinderMvcInterceptor使用防止内存溢出
 * <p>
 * Created by liyd on 4/9/14.
 */
@Aspect
@Slf4j
public class RunBinderInterceptor {

    /**
     * 日志对象
     */

    private static ThreadLocal<AtomicInteger> methodHierarchy = new ThreadLocal<AtomicInteger>();

    /**
     * 执行时间超过打印warn日志毫秒数
     */
    private static final long LOG_TIMEOUT = 1000;

    @Pointcut("@within(com.easys.platform.engine.runtime.BinderClass)")
    public void methodAnnotation() {

    }

    @Pointcut("bean(*ManagerImpl)")
    public void methodName() {

    }

    @Around("methodAnnotation() || methodName()")
    public Object around(ProceedingJoinPoint pjp) {

        AtomicInteger ai = methodHierarchy.get();
        if (ai == null) {
            ai = new AtomicInteger(1);
            methodHierarchy.set(ai);
        } else {
            ai.incrementAndGet();
        }

        //被拦截的类
        String targetClass = pjp.getTarget().getClass().getName();

        Signature signature = pjp.getSignature();
        //被拦截方法
        String targetMethod = signature.getName();

        //当前时间毫秒数
        long beginTime = System.currentTimeMillis();

        log.debug("start:[class={},method={},beginTime={}]", new Object[]{ targetClass, targetMethod, beginTime });

        //返回结果
        Object result = null;
        try {
            //此处调用业务方法
            result = pjp.proceed();

        } catch (EasysException e) {
            if (ai.get() > 1) {
                throw e;
            }
            RunBinderTransactionAspectSupport.setRollbackOnly();
            RunBinder.addError(e);
            result = this.getDefaultValue(signature);
            log.warn("已知异常,方法:[class={},method={}],信息:[resultCode={},resultMsg={}],参数:[{}]", e,
                    new Object[]{ targetClass, targetMethod, e.getErrCode(), e.getMessage(), argsToString(pjp) });
            //ignore
        } catch (Throwable throwable) {
            if (ai.get() > 1) {
                throw new RuntimeException(throwable);
            }
            RunBinderTransactionAspectSupport.setRollbackOnly();
            RunBinder.addError("UN_KNOWN_EXCEPTION", "未知异常");
            result = this.getDefaultValue(signature);
            log.error("未知异常,方法:[class={},method={}],参数:[{}]", throwable, new Object[]{ targetClass, targetMethod, argsToString(pjp) });
            //ignore
        } finally {
            if (ai.decrementAndGet() == 0) {
                methodHierarchy.remove();
            }
        }

        long endTime = System.currentTimeMillis();

        long useTime = endTime - beginTime;

        log.debug("end:[class={},method={},endTime={},usedTime={}]", new Object[]{ targetClass, targetMethod, endTime, useTime });

        if (useTime > LOG_TIMEOUT) {
            log.warn("Long processing time:[class={},method={},usedTime={}]", new Object[]{ targetClass, targetMethod, useTime });
        }
        return result;

    }

    /**
     * 获取基本类型的默认值
     * 如果方法返回的是基本的值类型,直接返回null会出异常
     *
     * @param signature
     *
     * @return
     */
    private Object getDefaultValue(Signature signature) {
        if (!( signature instanceof MethodSignature )) {
            return null;
        }

        MethodSignature methodSignature = (MethodSignature) signature;
        Class returnType = methodSignature.getReturnType();
        if (!returnType.isPrimitive()) {
            return null;
        }
        if (returnType == Boolean.TYPE) {
            return Boolean.FALSE;
        } else if (returnType == Character.TYPE) {
            return '\u0000';
        } else if (returnType == Byte.TYPE) {
            return (byte) 0;
        } else if (returnType == Short.TYPE) {
            return (short) 0;
        } else if (returnType == Integer.TYPE) {
            return 0;
        } else if (returnType == Long.TYPE) {
            return 0L;
        } else if (returnType == Float.TYPE) {
            return 0.0F;
        } else if (returnType == Double.TYPE) {
            return 0.0D;
        }
        return null;
    }

    /**
     * 获取参数字符串
     *
     * @param pjp
     *
     * @return
     */
    private String argsToString(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        if (ArrayUtils.isEmpty(args)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : args) {
            if (obj == null) {
                sb.append("null;");
            } else {
                sb.append(obj.getClass().getName()).append("=").append(obj).append(";");
            }
        }
        return sb.toString();
    }
}
