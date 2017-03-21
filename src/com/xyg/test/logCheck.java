package com.xyg.test;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.xyg.test.logCheck;

public class logCheck {

	public String toMD5(String plainText){
		String hexValue = null;
		try{
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for(int offset = 0;offset<b.length;offset++){
				i = b[offset];
				if(i < 0)
					i += 256;
				if(i < 16)
					buf.append("0");
					buf.append(Integer.toHexString(i));
			}
		//	System.out.println("32位："+buf.toString());
			hexValue = buf.toString();
	//		System.out.println("hexValue1:"+hexValue);
		//	System.out.println("16位："+buf.toString().substring(8,24));
		}catch(Exception e){
			e.printStackTrace();
		}
	//	System.out.println("hexValue2:"+hexValue);
		return hexValue;		
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HashMap check(String username,String password) throws SQLException, ClassNotFoundException{
		logCheck MD5 = new logCheck();	
		String mdpass = MD5.toMD5(password);
	//	System.out.println("Md:"+mdpass);
	//	Connection conn = null ;
		String pwd = null;
		HashMap hashMap = new HashMap();
		
		try{
		//	Car Car = new Car();
		//	Connection conn = Car.getConnection();
			/*Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.0.26:1521/PDB_APEX","APEX_UAT"," apex_uat");
			*/
			Car car = new Car();
			Connection conn = car.getConn();
			String sql = "SELECT ENCRYPTED_USER_PASSWORD FROM XYG_ALB2B_USER_V WHERE USER_NAME = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);		
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				pwd = rs.getString(1);
	//		    System.out.println(pwd);
			}
			if(pwd.equals(mdpass)){
				String es = "a";
				String msg = "登入成功！！！！";
				hashMap.put("RetCode", es);
				hashMap.put("Msg",msg);
			}else{
				String es = "b";
				String msg = "密码错误！！！！";
				hashMap.put("RetCode", es);
				hashMap.put("Msg",msg);
			}	
		//	conn.close();
			car.closeAll(conn, pstmt, null);
		}catch(NullPointerException e){
			String es = "c";
			String msg = "用户不存在！！！";
			hashMap.put("RetCode", es);	
			hashMap.put("Msg",msg);
		}
		return hashMap;			
	}	
	public static void main(String[] agrs) throws ClassNotFoundException, SQLException{	
		/*logCheck md = new logCheck();		
	//	md.toMD5("654321");	
		HashMap h = new HashMap();
		h = md.check("BH", "321");
		System.out.println("H:"+h);
		//System.out.println("结果:"+h.get("Msg").toString());
*/	}		
}
