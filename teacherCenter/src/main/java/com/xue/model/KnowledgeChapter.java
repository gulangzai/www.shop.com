/*
 * KnowledgeChapter.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 章表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class KnowledgeChapter {
    /**
    /*章id
    */
    private Integer chapterId;

    /**
    /*题库集id
    */
    private String topicSetId;

    /**
    /*章内容
    */
    private String chapterContent;

    /**
    /*是否删除（0:未删除 1：删除）
    */
    private Integer isDel;

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getTopicSetId() {
        return topicSetId;
    }

    public void setTopicSetId(String topicSetId) {
        this.topicSetId = topicSetId == null ? null : topicSetId.trim();
    }

    public String getChapterContent() {
        return chapterContent;
    }

    public void setChapterContent(String chapterContent) {
        this.chapterContent = chapterContent == null ? null : chapterContent.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}