package javacommon.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPListParseEngine;

 

public class FtpUtil {
	private FTPClient ftpclient;
    private String ipAddress;
    private int ipPort;
    private String userName;
    private String PassWord;
    /**
     * 构造函数
     * @param ip String 机器IP
     * @param port String 机器FTP端口号
     * @param username String FTP用户名
     * @param password String FTP密码
     * @throws Exception
     */
    public FtpUtil(String ip, int port, String username, String password) throws
            Exception {
        ipAddress = new String(ip);
        ipPort = port;
        ftpclient = new FTPClient( );
        ftpclient.setDefaultPort(ipPort);
        ftpclient.setConnectTimeout(10*1000);
        ftpclient.connect(ipAddress);
        ftpclient.setDataTimeout(10*1000);
        ftpclient.setSoTimeout(10*1000);

        //ftpclient = new FtpClient(ipAddress);
        userName = new String(username);
        PassWord = new String(password);
        
    }
    public void changeDir(String pathname){
    	try {
			ftpclient.changeWorkingDirectory(pathname);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public long getFileSize(String fullFilePath)
    {
    	try {
			FTPListParseEngine engine = ftpclient.initiateListParsing(fullFilePath);
			while(engine.hasNext()){  
                FTPFile[] files = engine.getNext(5);  
                for(int i=0;i<files.length;i++){  
                    //获取文件大小  
                    long size = files[i].getSize();  
                    return size/1024;
                }  
            }  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return 0L;
    }
    
    public int deletePath(String fullFilePath)
    {
    	int result=-1;
    	try {
    		 
    		FTPListParseEngine engine = ftpclient.initiateListParsing(fullFilePath);
			while(engine.hasNext()){  
                FTPFile[] files = engine.getNext(5); 
             
                for(int i=0;i<files.length;i++){  
                	if (files[i].isDirectory()&&!files[i].getName().equals(".")&&!files[i].getName().equals("..")) {
                		deletePath(fullFilePath+"/"+files[i].getName());
					}else{
						 ftpclient.deleteFile(fullFilePath+"/"+files[i].getName());
					}
                	 
                }  
            }
			result=ftpclient.sendCommand("RMD "+fullFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return result;
    }
    
    //获取目录占用空间大小
    public long getDirSize(String fullFilePath)
    {
    	long size=0l;
    	try {
			FTPListParseEngine engine = ftpclient.initiateListParsing(fullFilePath);
			while(engine.hasNext()){  
                FTPFile[] files = engine.getNext(5); 
             
                for(int i=0;i<files.length;i++){  
                	 
                	if (files[i].isDirectory()&&!files[i].getName().equals(".")&&!files[i].getName().equals("..")) {
                		size += getDirSize(fullFilePath+"/"+files[i].getName()); 
					}else{
						//获取文件大小  
	                	size += files[i].getSize(); 
					}
                	 
                }  
            }  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return size;
    }

    /**
     * 构造函数
     * @param ip String 机器IP，默认端口为21
     * @param username String FTP用户名
     * @param password String FTP密码
     * @throws Exception
     */
    public FtpUtil(String ip, String username, String password) throws
            Exception {
        ipAddress = new String(ip);
        ipPort = 21;
        ftpclient = new FTPClient( );
        ftpclient.setDefaultPort(ipPort);
        ftpclient.connect(ipAddress);
        //ftpclient = new FtpClient(ipAddress);
        userName = new String(username);
        PassWord = new String(password);
        ftpclient.enterLocalPassiveMode();//被动模式
    }


    /**
     * 登录FTP服务器
     * @throws Exception
     */
    public void login() throws Exception {
        ftpclient.login(userName, PassWord);
        ftpclient.enterLocalPassiveMode();   
        ftpclient.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftpclient.setFileTransferMode( FTPClient.STREAM_TRANSFER_MODE );
    }

    /**
     * 退出FTP服务器
     * @throws Exception
     */
    public void logout() throws Exception {
        //用ftpclient.closeServer()断开FTP出错时用下更语句退出
    	//ftpclient.logout();
    	if (ftpclient.isConnected()) {
    		ftpclient.disconnect();
		}
    	try {
    		ftpclient.logout();
		} catch (Exception e) {
			 
		}
       
    }

    
    /**
     * 在FTP服务器上建立指定的目录,当目录已经存在的情下不会影响目录下的文件,这样用以判断FTP
     * 上传文件时保证目录的存在目录格式必须以"/"根目录开头
     * @param pathList String
     * @throws Exception
     */
    public void buildList(String pathList) throws Exception {
    	pathList=checkName(pathList);
    	String[] paths=pathList.split("/");
    	for (int i = 0; i < paths.length; i++) {
    		ftpclient.makeDirectory(paths[i]);
    		ftpclient.changeWorkingDirectory(paths[i]) ;
		}
    }
    public void createFile(String fileName,InputStream input){
    	try {
			ftpclient.storeFile(fileName, input);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
     * 取得指定目录下的所有文件名，不包括目录名称
     * 分析nameList得到的输入流中的数，得到指定目录下的所有文件名
     * @param fullPath String
     * @return ArrayList
     * @throws Exception
     */
    public boolean isExistFilePath(String fullPath) throws Exception {
    	String parentPath=fullPath.substring(0,fullPath.lastIndexOf('/')); 
    	String pathName=fullPath.substring( fullPath.lastIndexOf('/')+1); 
    	parentPath=checkName(parentPath);
    	 
    	ArrayList namesList = getDirs(parentPath);
    		return namesList.contains(pathName);
    }
    
    /**
     * 取得指定目录下的所有文件名，不包括目录名称
     * 分析nameList得到的输入流中的数，得到指定目录下的所有文件名
     * @param fullPath String
     * @return ArrayList
     * @throws Exception
     */
    public boolean isExistFileName(String fullName) throws Exception {
    	String parentPath=fullName.substring(0,fullName.lastIndexOf('/'));
    	String pathName=fullName.substring( fullName.lastIndexOf('/')+1);
    	parentPath=checkName(parentPath);
    	ArrayList namesList = fileNames(parentPath);
    		return namesList.contains(pathName);
    }

    /**
     * 取得指定目录下的所有文件名，不包括目录名称
     * 分析nameList得到的输入流中的数，得到指定目录下的所有文件名
     * @param fullPath String
     * @return ArrayList
     * @throws Exception
     */
    public ArrayList getDirs(String fullPath) throws Exception {
    	fullPath=checkName(fullPath);
    	ArrayList namesList = new ArrayList();
    	FTPFile[] names=ftpclient.listFiles(fullPath);
            if (names!=null) {
    			for (int i = 0; i < names.length; i++) {
    				namesList.add(names[i].getName());
    				System.out.println(names[i].getName());
				}
    		}
        return namesList;
    }
    
    /**
     * 取得指定目录下的所有文件名，不包括目录名称
     * 分析nameList得到的输入流中的数，得到指定目录下的所有文件名
     * @param fullPath String
     * @return ArrayList
     * @throws Exception
     */
    public ArrayList fileNames(String fullPath) throws Exception {
    	fullPath=checkName(fullPath);
    	ArrayList namesList = new ArrayList();
    		String[] names=ftpclient.listNames(fullPath);
            if (names!=null) {
    			for (int i = 0; i < names.length; i++) {
    				namesList.add(names[i].toLowerCase());
				}
    		} 
        return namesList;
    }
    /**
    * 取得指定目录下所有xml文件路径 
    * @param fullPath
    * @return List<String>
    * @throws Exception
    */
    public List<String> getDirectoryFiles(String fullPath) throws Exception
    {
    	fullPath=checkName(fullPath);
    	List<String> namesList = new ArrayList();
    	String[] names=ftpclient.listNames(fullPath);
        if (names!=null) 
        {
			for (int i = 0; i < names.length; i++) {
				if(names[i].endsWith(".xml"))
				{
					namesList.add(names[i]);
				}
			}
    	} 
        return namesList;
    }
    
    /**
     * 取得指定目录下所有文件
     * @param fullPath
     * @return List<String>
     * @throws Exception
     */
     public FTPFile[] getFiles(String fullPath) throws Exception
     {
     	fullPath=checkName(fullPath);
     	FTPFile[] ftpFiles=ftpclient.listFiles(fullPath);
     	return ftpFiles;
     }
    /**
     * 上传文件到FTP服务器,destination路径以FTP服务器的"/"开始，带文件名、
     * 上传文件只能使用二进制模式，当文件存在时再次上传则会覆盖
     * @param source String
     * @param destination String
     * @throws Exception
     */
    public void upFile(String source, String destination) throws Exception {
    	destination=checkName(destination);
    	if (destination.split("/").length>2) {
    		if (!isExistFilePath(destination.substring(0, destination.lastIndexOf('/')))) {
    			buildList( destination.substring(0, destination.lastIndexOf('/')) );
    		}
		}
    	FileInputStream ftpIn = new FileInputStream(source);
    	System.out.println("upFile:"+ftpIn.available());
    	ftpclient.storeFile(destination, ftpIn);
    	 
//    	OutputStream ftpOut = ftpclient.storeFileStream(destination);
//    	FileInputStream ftpIn = new FileInputStream(source);
//    	byte[] buf = new byte[ftpIn.available()];
//        ftpIn.read(buf);
//        ftpOut.write(buf);
//        ftpIn.close();
//        ftpOut.close();

    }


    /**
     * JSP中的流上传到FTP服务器,
     * 上传文件只能使用二进制模式，当文件存在时再次上传则会覆盖
     * 字节数组做为文件的输入流,此方法适用于JSP中通过
     * request输入流来直接上传文件在RequestUpload类中调用了此方法，
     * destination路径以FTP服务器的"/"开始，带文件名
     * @param sourceData byte[]
     * @param destination String
     * @throws Exception
     */
    public void upFile(byte[] sourceData, String destination) throws Exception {
    	 
    	destination=checkName(destination);
    	if (destination.split("/").length>2) {
    		if (!isExistFilePath(destination.substring(0, destination.lastIndexOf('/')))) {
    			buildList( destination.substring(0, destination.lastIndexOf('/')) );
    		}
		}
    	
    	
    	InputStream ftpIn = new ByteArrayInputStream(sourceData);
    	if (!ftpclient.storeFile(destination, ftpIn)) {
    		ftpIn.close();
			throw new Exception("upFile fail!"+destination);
		} 
    	ftpIn.close();
//    	OutputStream ftpOut = ftpclient.storeFileStream(destination);
//    	//ftpclient.storeFile(remote, local);
//    	ftpOut.write(sourceData);
//        ftpOut.close();
//         
    }

    /**
     * 从FTP文件服务器上下载文件SourceFileName，到本地destinationFileName
     * 所有的文件名中都要求包括完整的路径名在内
     * @param SourceFileName String
     * @param destinationFileName String
     * @throws Exception
     */
    public void downFile(String SourceFileName, String destinationFileName) throws
            Exception {
    	SourceFileName=checkName(SourceFileName);
    	FileOutputStream byteOut = null;
     	try {
     		 byteOut = new FileOutputStream(destinationFileName); 
              ftpclient.retrieveFile(SourceFileName, byteOut);  
              byteOut.close();  
     		  } catch (IOException e) {
     		   e.printStackTrace();
     		  }  
    }
    
    
    public String getMD5andDown(String SourceFileName,String destinationFileName ) throws Exception {
		String md5 = "";
		try {
			
			InputStream in = ftpclient.retrieveFileStream(SourceFileName);
			MessageDigest md = MessageDigest.getInstance("MD5");
			Long bufferSize=1024*4l;
			byte[] buffer = new byte[bufferSize.intValue()];
			File file=new File(destinationFileName);
     		File parentFile=file.getParentFile();
     		if (!parentFile.exists()) {
     			parentFile.mkdirs();
			}
			FileOutputStream out=new FileOutputStream(destinationFileName);
			
     		ftpclient.setBufferSize(bufferSize.intValue());
			int numRead = 0;
			while ((numRead = in.read(buffer)) > 0) {
				md.update(buffer, 0, numRead);
				out.write(buffer, 0, numRead);
			}
			out.close();
			in.close();
			
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			ftpclient.completePendingCommand();
			md5 = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5;
}
    
    public void makePath(String pathName){
    	try {
			ftpclient.makeDirectory(pathName);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**
     *从FTP文件服务器上下载文件，输出到字节数组中
     * @param SourceFileName String
     * @return byte[]
     * @throws Exception
     */
    public byte[] downFile(String SourceFileName) throws
            Exception {
    	SourceFileName=checkName(SourceFileName); 
    	ByteArrayOutputStream byteOut = null;
    	try {
    		 byteOut = new ByteArrayOutputStream(); 
             ftpclient.retrieveFile(SourceFileName, byteOut);
             byteOut.close();  
    		} catch (IOException e) {
    		   e.printStackTrace();
    	    }  

        return byteOut.toByteArray();
    }
    
    /**
     * 获取当前目录下的所有文件
     * @return
     * @throws Exception
     */
    public FTPFile[] getFiles() throws Exception
    {
    	FTPFile[] ftpFiles=ftpclient.listFiles();
    	return ftpFiles;
    }
    
    public boolean deleteFile(String filePath){
    	try {
			return ftpclient.deleteFile(filePath);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
    private String checkName(String name){
    	if (!name.startsWith("/")) {
    		name="/"+name;
		}
    	return name;
    }
    
    /**
     * 建立多级目录
     * @param paths
     */
    public void createMultistagePath(String multistagePath){
    	multistagePath=multistagePath.startsWith("/")?multistagePath.substring(1):multistagePath;
    	multistagePath=multistagePath.endsWith("/")?multistagePath.substring(0,multistagePath.length()-1):multistagePath;
    	String[] paths=multistagePath.split("/");
    	for(int i=0;i<paths.length;i++){
    		try {
				ftpclient.makeDirectory(paths[i]);
	    		ftpclient.changeWorkingDirectory(paths[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
    public void changePath(String path){
    	path=path.startsWith("/")?path.substring(1):path;
    	path=path.endsWith("/")?path.substring(0,path.length()-1):path;
    	try {
			ftpclient.changeWorkingDirectory(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void changeParentPath(){
    	try {
			ftpclient.changeToParentDirectory();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
