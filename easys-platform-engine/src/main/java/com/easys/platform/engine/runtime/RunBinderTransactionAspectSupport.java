package com.easys.platform.engine.runtime;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * Created by liyingdan on 2016/8/20.
 */
public class RunBinderTransactionAspectSupport extends TransactionAspectSupport {

    public static void setRollbackOnly() {
        TransactionAspectSupport.TransactionInfo transactionInfo = currentTransactionInfo();
        if (transactionInfo != null && transactionInfo.hasTransaction()) {
            transactionInfo.getTransactionStatus().setRollbackOnly();
        }
    }

}
