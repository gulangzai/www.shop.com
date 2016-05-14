package com.lanbao.cn.db.single;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DBPool {
	
	DBNode dbNode = null;
	  
	private static int freeConnection=0;
	
	private static int createConnection=0;
	
    private LinkedList<Connection> connectionsPool=new LinkedList<Connection>();
    
    private static Logger logger = LoggerFactory.getLogger(DBPool.class);
  
	public DBPool(DBNode dbNode){
		try {  
		     this.dbNode = dbNode; 
			 Class.forName(dbNode.getDbDriver());   
			 initAllConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timer timer = new Timer();
		timer.schedule(new MyTask(), dbNode.getDistanceTime()*1000, dbNode.getDistanceTime()*1000);
	}
	
	class MyTask extends TimerTask{
		public void run(){
			refreshConnection();
			//LyLog.getLog().info("����ˢ�����ӳغ�״̬("+connectionsPool.size()+"):��ǵĿ���������=("+freeConnection+")------������="+createConnection);
		}
	}
	
	
	 
	public void initAllConnection(){ 
		for(int i = 0;i<dbNode.getInitCount();i++){
			Connection conn = initcreateConnection();
			addConnection(conn); 
	    } 
	}
	 
	
	/*�������ӣ���������++�� ��������++*/
	public synchronized  Connection initcreateConnection(){
		Connection conn = null;
		   if(createConnection<dbNode.getMaxCount()){
			 conn = createConnection();
//LyLog.getLog().info("�µ�����"+conn+"������");
		 }   
		 return conn;
	}
	
 
	public synchronized  Connection createConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbNode.getUrl(),dbNode.getDbUser(),dbNode.getDbPass());
			createConnection++;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	 
	public synchronized void addConnection(Connection conn){
		if(conn!=null){
			connectionsPool.addLast(conn);
			freeConnection++; 
		} 
	}
	
  
	/*ˢ�����ӳ�*/
	public synchronized void refreshConnection(){ 
		for(int i=0;i<connectionsPool.size();i++){
			Connection con = getConnection();
			close(con); 
			Connection conn = createConnection();
			addConnection(conn);
		} 
	}
	
	
	public synchronized final Connection getConnection() {
		Connection conn =null; 
		try{ 
//System.out.println("����ǰ��״̬("+connectionsPool.size()+"):��ǵĿ���������=("+freeConnection+"):��ʹ��������("+useConnection+")------������="+createConnection);
			
	    while(freeConnection==0){
			try {
				//LyLog.getLog().info("�ȴ����ӡ�����........��");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		conn = connectionsPool.removeFirst();
		freeConnection--; 
		}catch(NoSuchElementException e){
			e.printStackTrace();
		} 
		System.out.println("������״̬("+connectionsPool.size()+"):��ǵĿ���������=("+freeConnection+"):��ʹ��������("+(createConnection-freeConnection)+")------������="+createConnection+"�����ӵ�״̬��"+"connection:"+conn);
		return conn;
	} 
	  
	 
	
	//�ر�����,����������--��
	public synchronized void close(Connection conn){
		try {
			conn.close();
			createConnection--; 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*�رռ��ϣ�����*/
	public synchronized  void free(Connection conn, Statement stat, ResultSet rs) {
		try {
			if (stat != null) {
				stat.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				this.free(conn);
			}
		}
	}
	 
		/*�ͷ�����*/
    public synchronized void free(Connection conn){
			// TODO Auto-generated method stu  
			addConnection(conn);
		    notifyAll();
		   // LyLog.getLog().info("�����������������������������������������ӱ��Żػ��Ѱ�"+conn+"���Ż�");
		 //LyLog.getLog().info("�ͷ�����+��ǰ��������"+connectionsPool.size()+":"+freeConnection);
   }  
}
