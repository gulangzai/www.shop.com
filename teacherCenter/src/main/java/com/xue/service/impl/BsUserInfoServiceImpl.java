package com.xue.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javacommon.util.SafeUtils;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.sun.istack.internal.logging.Logger;
import com.xue.dao.BsCallbackInfoMapper;
import com.xue.dao.BsNoticeMapper;
import com.xue.dao.BsUserInfoDetailsMapper;
import com.xue.dao.BsUserInfoMapper;
import com.xue.dao.BsUserNoticSetMapper;
import com.xue.dao.BsUserPayInfoMapper;
import com.xue.dao.BsUserShopSetMapper;
import com.xue.dao.BsuserOpinionMapper;
import com.xue.dao.BsuserOpinionSetMapper;
import com.xue.dao.ClassesMapper;
import com.xue.dao.StudentInfoMapper; 
import com.xue.dto.BsUserInfoDetail;
import com.xue.model.BsCallbackInfo;
import com.xue.model.BsNotice;
import com.xue.model.BsUserInfo;
import com.xue.model.BsUserInfoDetails;
import com.xue.model.BsUserNoticSet;
import com.xue.model.BsUserPayInfo;
import com.xue.model.BsUserShopSet;
import com.xue.model.BsuserOpinion;
import com.xue.model.BsuserOpinionSet;
import com.xue.model.Classes;
import com.xue.model.MyCollect;
import com.xue.model.MyCollectDetailPointSet; 
import com.xue.model.StudentHighScore;
import com.xue.model.StudentInfo;
import com.xue.model.StudentMyCollectSet;
import com.xue.model.SysCollect;
import com.xue.service.BsUserInfoService;
import com.xue.service.ClassesService; 
import com.xue.service.StudentInfoService;
import com.xue.vo.query.BsNoticeQuery;
import com.xue.vo.query.BsUserInfoQuery;
import com.xue.vo.query.BsUserShopSetQuery;
import com.xue.vo.query.UserQuery; 

@Service
public class BsUserInfoServiceImpl implements BsUserInfoService {
	
	@Autowired
	private BsUserInfoDetailsMapper bsUserInfoDetailsMapper;
	@Autowired
	private BsUserPayInfoMapper bsUserPayInfoMapper;
	@Autowired
	private BsNoticeMapper bsNoticeMapper;
	@Autowired
	private BsCallbackInfoMapper bsCallbackInfoMapper;
	@Autowired
	private BsUserShopSetMapper bsUserShopSetMapper;
	@Autowired
	private BsUserInfoMapper bsUserInfoMapper;
	@Autowired
	private BsUserNoticSetMapper bsUserNoticSetMapper;
	@Autowired
	private BsuserOpinionMapper bsuserOpinionMapper;
	@Autowired
	private BsuserOpinionSetMapper bsuserOpinionSetMapper;
	
	@Override
	public BsUserInfoDetail findByUserId(String userId) {
		return bsUserInfoDetailsMapper.findByUserId(userId);
	}

	@Override
	public List<BsUserPayInfo> findUserPayInfo(String userId) {
		return bsUserPayInfoMapper.findUserPayInfo(userId);
	}

	@Override
	public List<BsNotice> findNotice(BsNoticeQuery bsNoticeQuery) {
		List<BsNotice> allBsNotices =new ArrayList<BsNotice>();
		try {
			 PageHelper.startPage(bsNoticeQuery.getPageNumber(), bsNoticeQuery.getPageSize());
			 List<BsNotice> bsNotices=bsNoticeMapper.findNotice(); 
			 allBsNotices.addAll(bsNotices);
			 List<BsUserNoticSet> noticSets = new ArrayList<BsUserNoticSet>();
			 List<Integer> list = new ArrayList<Integer>();
			 if(bsNotices.size()>0){
					for (int i = 0; i < bsNotices.size(); i++) {
						list.add(bsNotices.get(i).getId());
					}
					List<BsUserNoticSet> bsUserNoticSets=bsUserNoticSetMapper.findNoticeInUserId(list);
					for (int i = 0; i < list.size(); i++)  //外循环是循环的次数
		            {
						BsNotice bsNotice = allBsNotices.get(i);
		                for (int j = 0; j < bsUserNoticSets.size(); j++)  //内循环是 外循环一次比较的次数
		                {
	                		//System.out.println(list.get(i)+"---"+bsUserNoticSets.get(j).getNoticId());
	                		//System.out.println(list.get(i)==bsUserNoticSets.get(j).getNoticId());
		                    if (list.get(i)==bsUserNoticSets.get(j).getNoticId())
		                    {
		                    	bsNotices.remove(bsNotice);
		                    	break;
		                    }
		                }
		            }
					for (int i = 0; i < bsNotices.size(); i++) {
						BsUserNoticSet bsUserNoticSet = new BsUserNoticSet();
						bsUserNoticSet.setUserId("teacher");
						bsUserNoticSet.setState(0);
						bsUserNoticSet.setNoticId(bsNotices.get(i).getId());
						noticSets.add(bsUserNoticSet);
					}
				}
		    if(noticSets.size()>0){
		        bsUserNoticSetMapper.batchInsert(noticSets);
		    }
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
		}
		return allBsNotices;
	}

	@Override
	public List<BsCallbackInfo> findBsCallbackInfo() {
		return bsCallbackInfoMapper.findBsCallbackInfo();
	}

	@Override
	@Transactional
	public int insertCallBackInfo(String feedback) {
		BsCallbackInfo bsCallbackInfo = new BsCallbackInfo();
		bsCallbackInfo.setCallbackContent(feedback);
		bsCallbackInfo.setStatu(2);
		bsCallbackInfo.setCreateTime(new Date());;
		return bsCallbackInfoMapper.insertSelective(bsCallbackInfo);
	}
	@Override
	public Integer findAnnualConsumption(String userId) {
		return bsUserPayInfoMapper.findAnnualConsumption(userId);
	}
	@Override
	public BsUserInfoDetail login(String userName, String password) {
		BsUserInfoQuery bsUserInfoQuery = new BsUserInfoQuery(); 
		bsUserInfoQuery.setPassword(password);
		if (Pattern
				.compile("^((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$")
				.matcher(userName).matches()) {
			bsUserInfoQuery.setMobile(userName);
			return bsUserInfoDetailsMapper.login(bsUserInfoQuery);
		} /*else if (Pattern
				.compile(
						"^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")
				.matcher(userName).matches()) {
			studentInfoQuery.setEmail(userName);
			return studentInfoMapper.login(userQuery);
		}*/
		bsUserInfoQuery.setUserName(userName);
		return bsUserInfoDetailsMapper.login(bsUserInfoQuery);  
	}

	@Override
	public List<BsUserShopSet> findAllMajor(String userId) {
		return bsUserShopSetMapper.findAllMajor(userId);
	}

	@Override
	public List<BsUserShopSet> findMajor(String userId) {
		return bsUserShopSetMapper.findMajor(userId);
	}

	@Override
	public void updateBsUserInfo(BsUserInfoDetails bsUserInfo) {
		if(bsUserInfo!=null){
			if(bsUserInfo.getUserName()!=null&&!bsUserInfo.getUserName().equals("")){
				BsUserInfo userInfo = new BsUserInfo();
				userInfo.setUserId(bsUserInfo.getUserId());
				userInfo.setUserName(bsUserInfo.getUserName());
				bsUserInfoMapper.updateByPrimaryKeySelective(userInfo);
			}
			if((bsUserInfo.getRealName()!=null&&!bsUserInfo.getRealName().equals("")
					||bsUserInfo.getJigouName()!=null&&!bsUserInfo.getJigouName().equals("")
						||bsUserInfo.getTelephone()!=null&&!bsUserInfo.getTelephone().equals(""))){
				bsUserInfoDetailsMapper.updateByPrimaryKeySelective(bsUserInfo);
			}
		} 
	}

	@Override
	public int readNotice() {
		return bsUserNoticSetMapper.readNotice();
	}

	@Override
	public Integer findNoticeCount() {
		return bsNoticeMapper.findNoticeCount();
	}

	@Override
	public List<BsNotice> readPageNotice(BsNoticeQuery bsNoticeQuery) {
		PageHelper.startPage(bsNoticeQuery.getPageNumber(), bsNoticeQuery.getPageSize());
		return bsNoticeMapper.readPageNotice();
	}

	@Override
	public int readNoNotice() {
		return bsUserNoticSetMapper.readNoNotice();
	}

	@Override
	public List<BsNotice> readNoPageNotice(BsNoticeQuery bsNoticeQuery) {
		PageHelper.startPage(bsNoticeQuery.getPageNumber(), bsNoticeQuery.getPageSize());
		return bsNoticeMapper.readNoPageNotice();
	}

	@Override
	public List<StudentHighScore> getAchivementInfo(String userId, String marjorId) {
		BsUserShopSetQuery bsUserShopSetQuery = new BsUserShopSetQuery();
		bsUserShopSetQuery.setUserId(userId);
		bsUserShopSetQuery.setMajorId(marjorId);
		return bsUserShopSetMapper.getAchivementInfo(bsUserShopSetQuery);
	}

	@Override
	public List<StudentHighScore> getAchivementInfoByClassId(String userId, String marjorId) {
		BsUserShopSetQuery bsUserShopSetQuery = new BsUserShopSetQuery();
		bsUserShopSetQuery.setUserId(userId);
		bsUserShopSetQuery.setMajorId(marjorId);
		return bsUserShopSetMapper.getAchivementInfoByClassId(bsUserShopSetQuery);
	}

	@Override
	public List<StudentHighScore> getAchivementInfoByMonth() {
		return bsUserShopSetMapper.getAchivementInfoByMonth();
	}

	@Override
	public List<StudentHighScore> getMouthAchivementInfo(String userId, String marjorId,Integer mouth) {
		BsUserShopSetQuery bsUserShopSetQuery = new BsUserShopSetQuery();
		bsUserShopSetQuery.setUserId(userId);
		bsUserShopSetQuery.setMajorId(marjorId);
		bsUserShopSetQuery.setStatu(mouth);
		return bsUserShopSetMapper.getMouthAchivementInfo(bsUserShopSetQuery);
	}

	@Override
	public List<StudentHighScore> getMouthAchivementInfoByClassId(String userId, String marjorId, Integer mouth) {
		BsUserShopSetQuery bsUserShopSetQuery = new BsUserShopSetQuery();
		bsUserShopSetQuery.setUserId(userId);
		bsUserShopSetQuery.setMajorId(marjorId);
		bsUserShopSetQuery.setStatu(mouth);
		return bsUserShopSetMapper.getMouthAchivementInfoByClassId(bsUserShopSetQuery);
	}

	@Override
	public List<BsuserOpinion> findOpinion(String userId) {
		return bsuserOpinionMapper.findOpinion(userId);
	}

	@Override
	@Transactional
	public int insertBsuserOpinion(String feedback,
			BsUserInfoDetail bsUserInfoDetail) {
		try {
			BsuserOpinion bsuserOpinion = new BsuserOpinion();
			bsuserOpinion.setCreateTime(new Date());
			bsuserOpinion.setOpinionContent(feedback);
			bsuserOpinionMapper.insertSelective(bsuserOpinion);
			BsuserOpinionSet bsuserOpinionSet = new BsuserOpinionSet();
			bsuserOpinionSet.setOpinionId(bsuserOpinion.getId());
			bsuserOpinionSet.setUserId(bsUserInfoDetail.getUserId());
			bsuserOpinionSetMapper.insertSelective(bsuserOpinionSet);
		} catch (Exception e) {
			
		}
		return 1;
	}
 
}
