/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.dao;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author sys53
 * @date 2017/8/20
 */
public class QueryConditionTransferTest {
    @Test
    public void createQueryCondition() throws Exception {
        UserQo userQo = UserQo.builder().name("shenys").maxAge(40).minAge(20).build();
        QueryConditionTransfer qct = new QueryConditionTransfer();
        QueryCondition qc = qct.createQueryCondition(userQo);
        Assert.assertEquals("Select obj From Hoop_user As obj Where 1=1  And obj. age <= :maxAge And obj. age >= :minAge And obj. userName = :name", qc.getQueryString());
    }

}