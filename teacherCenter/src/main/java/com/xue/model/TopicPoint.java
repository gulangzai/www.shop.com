/*
 * TopicPoint.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 知识点和题目之间的关系表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class TopicPoint {
    /**
    /*所属题目id
    */
    private String topicId;

    /**
    /*所属知识点id
    */
    private String pointId;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId == null ? null : topicId.trim();
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId == null ? null : pointId.trim();
    }
}