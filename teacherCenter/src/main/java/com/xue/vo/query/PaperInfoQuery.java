package com.xue.vo.query;

import java.util.Date;

public class PaperInfoQuery {
    private Integer paperid;

    private String papername;

    private String papertype;

    private Integer totalnum;

    private Integer questionnum;

    private Integer anwsertime;

    private String typename;

    private Integer radiosum;

    private Integer checksum;

    private Integer questionsum;

    private Integer questiontotalpoints;

    /**
    /*考试时间
    */
    private Date examtime;

    /**
    /*是否存在直播(0:是，1：否)
    */
    private Integer islive;

    /**
    /*直播时间
    */
    private Date livetime;

    /**
    /*月月考月份
    */
    private Integer monthonmonth;

    
    /**
    /*(0:月月考，1：全国模考賽，2：在线模考)
    */
    private Integer source;
    
    private String subjectId;
    
    private String majorId;
    
    
    
    public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getMajorId() {
		return majorId;
	}

	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Date getExamtime() {
		return examtime;
	}

	public void setExamtime(Date examtime) {
		this.examtime = examtime;
	}

	public Integer getIslive() {
		return islive;
	}

	public void setIslive(Integer islive) {
		this.islive = islive;
	}

	public Date getLivetime() {
		return livetime;
	}

	public void setLivetime(Date livetime) {
		this.livetime = livetime;
	}

	public Integer getMonthonmonth() {
		return monthonmonth;
	}

	public void setMonthonmonth(Integer monthonmonth) {
		this.monthonmonth = monthonmonth;
	}

	public Integer getPaperid() {
        return paperid;
    }

    public void setPaperid(Integer paperid) {
        this.paperid = paperid;
    }

    public String getPapername() {
        return papername;
    }

    public void setPapername(String papername) {
        this.papername = papername == null ? null : papername.trim();
    }

    public String getPapertype() {
        return papertype;
    }

    public void setPapertype(String papertype) {
        this.papertype = papertype == null ? null : papertype.trim();
    }

    public Integer getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(Integer totalnum) {
        this.totalnum = totalnum;
    }

    public Integer getQuestionnum() {
        return questionnum;
    }

    public void setQuestionnum(Integer questionnum) {
        this.questionnum = questionnum;
    }

    public Integer getAnwsertime() {
        return anwsertime;
    }

    public void setAnwsertime(Integer anwsertime) {
        this.anwsertime = anwsertime;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public Integer getRadiosum() {
        return radiosum;
    }

    public void setRadiosum(Integer radiosum) {
        this.radiosum = radiosum;
    }

    public Integer getChecksum() {
        return checksum;
    }

    public void setChecksum(Integer checksum) {
        this.checksum = checksum;
    }

    public Integer getQuestionsum() {
        return questionsum;
    }

    public void setQuestionsum(Integer questionsum) {
        this.questionsum = questionsum;
    }

    public Integer getQuestiontotalpoints() {
        return questiontotalpoints;
    }

    public void setQuestiontotalpoints(Integer questiontotalpoints) {
        this.questiontotalpoints = questiontotalpoints;
    }
}