package com.lanbao.cn.db.single;

public class DBNode {
 
	public String dbNode = null;
	
 
	public String dbDriver = null;
 
	public String dbName = null;
	
 
	public String dbIp = null;
	
 
	public int dbPort = 0 ;
	
	 
	public String dbUser = null;
	
 
	public String dbPass = null;
	
 
	public String url = null;
	
 
	public int maxCount = 0;
	
 
	public int minCount = 0 ;
	
	public int initCount = 0;
	
	public int distanceTime=0;
	
	public int waitTimer = 0;
	

	public String getDbNode() {
		return dbNode;
	}

	public void setDbNode(String dbNode) {
		this.dbNode = dbNode;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbIp() {
		return dbIp;
	}

	public void setDbIp(String dbIp) {
		this.dbIp = dbIp;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPass() {
		return dbPass;
	}

	public void setDbPass(String dbPass) {
		this.dbPass = dbPass;
	}

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	public int getMinCount() {
		return minCount;
	}

	public void setMinCount(int minCount) {
		this.minCount = minCount;
	}
 
	public String getUrl() {
        url="jdbc:"+dbDriver+"://"+dbIp+":"+dbPort+"/"+dbName; 
		return url;
	}

	public String getDbDriver() {
		return "com."+dbDriver+".jdbc.Driver";
	}

	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver; 
	}

	public int getDbPort() {
		return dbPort;
	}

	public void setDbPort(int dbPort) {
		this.dbPort = dbPort;
	}

	public int getInitCount() {
		return initCount;
	}

	public void setInitCount(int initCount) {
		this.initCount = initCount;
	}

	public int getWaitTimer() {
		return waitTimer;
	}

	public void setWaitTimer(int waitTimer) {
		this.waitTimer = waitTimer;
	}

	public int getDistanceTime() {
		return distanceTime;
	}

	public void setDistanceTime(int distanceTime) {
		this.distanceTime = distanceTime;
	} 
}
