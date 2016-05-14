/*
 * BsUserInfo.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.service;

import java.util.Date;
import java.util.List;

import com.xue.dto.BsUserInfoDetail;
import com.xue.model.BsCallbackInfo;
import com.xue.model.BsNotice;
import com.xue.model.BsUserInfo;
import com.xue.model.BsUserInfoDetails;
import com.xue.model.BsUserPayInfo;
import com.xue.model.BsUserShopSet;
import com.xue.model.BsuserOpinion;
import com.xue.model.StudentHighScore;
import com.xue.vo.query.BsNoticeQuery;

/**
 * 
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public interface BsUserInfoService {

	/**
	 * @Description:根据id寻找详情
	 * @param string
	 * @return
	 * BsUserInfoDetail
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月17日 下午5:58:18
	 */
	BsUserInfoDetail findByUserId(String string);

	/**
	 * @Description:找最近充值的金额
	 * @param string
	 * @return
	 * List<BsUserPayInfo>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月17日 下午5:58:16
	 */
	List<BsUserPayInfo> findUserPayInfo(String string);

	/**
	 * @Description:根据自己的权限来寻找公告信息
	 * @return
	 * List<BsNotice>
	 * @exception:
	 * @author: Mr.Ruan
	 * @param bsNoticeQuery 
	 * @time:2016年1月18日 上午11:31:02
	 */
	List<BsNotice> findNotice(BsNoticeQuery bsNoticeQuery);

	/**
	 * @Description:查询反馈数据
	 * @return
	 * List<BsCallbackInfo>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月18日 下午3:49:15
	 */
	List<BsCallbackInfo> findBsCallbackInfo();

	/**
	 * @Description:插入数据
	 * @param feedback
	 * void
	 * @exception:
	 * @author: Mr.Ruan
	 * @return 
	 * @time:2016年1月18日 下午4:11:27
	 */
	int insertCallBackInfo(String feedback);

	BsUserInfoDetail login(String userId, String newpassword);
	/**
	 * @Description:查询年度充值
	 * @return
	 * List<Integer>
	 * @exception:
	 * @author: Mr.Ruan
	 * @param userId 
	 * @time:2016年1月19日 上午10:49:02
	 */
	Integer findAnnualConsumption(String userId);

	/**
	 * @Description:寻找所有的已开通课程的数据
	 * @param userId
	 * @return
	 * List<BsUserShopSet>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月19日 下午5:42:10
	 */
	List<BsUserShopSet> findAllMajor(String userId);

	/**
	 * @Description:TODO
	 * @param string
	 * @return
	 * List<BsUserShopSet>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月20日 上午9:48:24
	 */
	List<BsUserShopSet> findMajor(String string);

	/**
	 * @Description:更新用户信息
	 * @param bsUserInfo
	 * void
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月20日 下午2:34:48
	 */
	void updateBsUserInfo(BsUserInfoDetails bsUserInfo);

	/**
	 * @Description:获取已经读过的信息
	 * @return
	 * int
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月20日 下午4:52:41
	 */
	int readNotice();

	/**
	 * @Description:获取所有的通知的个数
	 * @return
	 * Integer
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月21日 上午10:21:31
	 */
	Integer findNoticeCount();

	/**
	 * @Description:获取已经读过的信息
	 * @return
	 * List<BsNotice>
	 * @exception:
	 * @author: Mr.Ruan
	 * @param bsNoticeQuery 
	 * @time:2016年1月21日 上午11:21:57
	 */
	List<BsNotice> readPageNotice(BsNoticeQuery bsNoticeQuery);

	/**
	 * @Description:获取未读的信息
	 * @return
	 * int
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月21日 下午12:43:08
	 */
	int readNoNotice();

	/**
	 * @Description:获取未读的信息
	 * @return
	 * List<BsNotice>
	 * @exception:
	 * @author: Mr.Ruan
	 * @param bsNoticeQuery 
	 * @time:2016年1月21日 下午12:45:16
	 */
	List<BsNotice> readNoPageNotice(BsNoticeQuery bsNoticeQuery);

	/**
	 * @Description:获取业绩情况
	 * @return
	 * List<StudentHighScore>
	 * @exception:
	 * @author: Mr.Ruan
	 * @param marjorId 
	 * @param userId 
	 * @time:2016年1月22日 上午10:09:59
	 */
	List<StudentHighScore> getAchivementInfo(String userId, String marjorId);

	/**
	 * @Description:获取业绩情况(根据classId分组)
	 * @return
	 * List<StudentHighScore>
	 * @exception:
	 * @author: Mr.Ruan
	 * @param marjorId 
	 * @param userId 
	 * @time:2016年1月22日 下午1:08:57
	 */
	List<StudentHighScore> getAchivementInfoByClassId(String userId, String marjorId);

	/**
	 * @Description:获取业绩情况(根据月份分组)
	 * @return
	 * List<StudentHighScore>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月22日 下午1:33:25
	 */
	List<StudentHighScore> getAchivementInfoByMonth();

	/**
	 * @Description:获取业绩情况（根据月份）
	 * @return
	 * List<StudentHighScore>
	 * @exception:
	 * @author: Mr.Ruan
	 * @param marjorId 
	 * @param userId 
	 * @param mouth 
	 * @time:2016年1月25日 下午1:36:43
	 */
	List<StudentHighScore> getMouthAchivementInfo(String userId, String marjorId, Integer mouth);

	/**
	 * @Description:获取业绩情况(根据月份根据classId分组)
	 * @return
	 * List<StudentHighScore>
	 * @exception:
	 * @author: Mr.Ruan
	 * @param mouth 
	 * @param marjorId 
	 * @param userId 
	 * @time:2016年1月25日 下午1:37:09
	 */
	List<StudentHighScore> getMouthAchivementInfoByClassId(String userId, String marjorId, Integer mouth);

	/**
	 * @Description:查询用户意见反馈
	 * @param userId
	 * @return
	 * List<BsuserOpinion>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月27日 下午1:30:56
	 */
	List<BsuserOpinion> findOpinion(String userId);

	/**
	 * @Description:意见反馈插入
	 * @param feedback
	 * @param bsUserInfoDetail
	 * @return
	 * int
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月27日 下午2:08:09
	 */
	int insertBsuserOpinion(String feedback, BsUserInfoDetail bsUserInfoDetail);
    
}