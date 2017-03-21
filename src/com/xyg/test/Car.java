package com.xyg.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

public class Car {
	
	private int charge;
	/*
	protected static Properties properties = new Properties();
	protected static String driver = "";
	protected static String url = "";
	protected static String user = "";
	protected static String name = "";
	protected static String pwd = "";
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>(); 
	static{
		try{
			properties.load(Car.class.getResourceAsStream("db.properties"));		
		}catch(Exception e){	
	   }
//	   System.out.println(properties.getProperty("jdbc.driver"));
	   driver=properties.getProperty("jdbc.driver");
	   url=properties.getProperty("jdbc.url");
	   user=properties.getProperty("jdbc.user");
	   pwd=properties.getProperty("jdbc.pwd");
	   try {
		  Class.forName(driver);
	   } catch (ClassNotFoundException e) {
//		  TODO Auto-generated catch block
		  e.printStackTrace();
	   }	
//	   System.out.println("debug1");
	}
	
	protected Connection getConnection() throws SQLException{
		Connection conn=threadLocal.get();
		if(conn==null){//若是空的，说明这个线程第一次获取连接
			//创建和数据库的连接
			conn=DriverManager.getConnection(url,user,pwd);
	        //将创建出来的连接放入线程共享中，set方法用于保存线程共享的数据
			threadLocal.set(conn);		
		}
		return conn;							
    }
	protected  void closeConnection(Connection conn) throws SQLException{
		if(conn!=null){
			try{	
				conn.close();	
				conn = null;
			} catch (SQLDataException e) { 
				e.printStackTrace();
			}	
		}//catch块内容可不写，即所谓的“安静”的关闭连接
	}
*/

	private static final String DRIVER="oracle.jdbc.driver.OracleDriver";  
    private static final String URL = "jdbc:oracle:thin:@//192.168.0.26:1521/PDB_APEX";  
    private static final String USERNAME="APEX_UAT";  
    private static final String PASSWORD="apex_uat";  
    public Connection getConn()throws ClassNotFoundException,SQLException{  
        Class.forName(DRIVER);  
        Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);  
        return conn;  
    }  
    public void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs)throws SQLException{  
        if(rs!=null){  
            rs.close();  
        }  
        if(pstmt!=null){  
            pstmt.close();  
        }  
        if(conn!=null){  
            conn.close();  
        }  
    }  
    
	//  汽车入库 	
	public HashMap<String,String> insCar(String ent_carId,String entTime) throws SQLException, ClassNotFoundException{
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try{
		//	Class.forName("oracle.jdbc.driver.OracleDriver");
		//	Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.0.26:1521/PDB_APEX","APEX_UAT"," apex_uat");
			Car car = new Car();
			Connection conn = car.getConn();
			String sql = "UPDATE XYG_TEST_PARKING_INFORM SET CAR_NUM = ?,IN_TIME = to_date(?,'yyyy-MM-dd HH24:mi:ss') where PARKING_ID = "
					+ "(select PARKING_ID from (select PARKING_ID from XYG_TEST_PARKING_INFORM order by dbms_random.value) where rownum=1)";
			String sql1 = " insert into XYG_TEST_CAR_NEWFORM(HISTORY_ID,PARKING_CODE,PARKING_TYPE,CAR_NUM,IN_TIME) SELECT XYG_TEST_CAR_NEWFORM_S.NEXTVAL,"
					+ "XTPI.PARKING_CODE,XTPI.PARKING_TYPE,XTPI.CAR_NUM,XTPI.IN_TIME FROM XYG_TEST_PARKING_INFORM XTPI WHERE XTPI.CAR_NUM = ?"; 
			String sql2 = "SELECT CAR_NUM FROM XYG_TEST_PARKING_INFORM WHERE CAR_NUM = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt.setNString(1,ent_carId);
			pstmt.setNString(2,entTime);	
			pstmt1.setNString(1,ent_carId);	
			pstmt2.setNString(1,ent_carId);	
			ResultSet rs1 = pstmt2.executeQuery();
			if(rs1.next()){
				String es1 = "a";
				String msg1 = "汽车入库失败！汽车已存在，请重新输入！！！";
				hashMap.put("RetCode1", es1);	
				hashMap.put("Msg1",msg1); 
			}else{
				pstmt.executeUpdate();
				pstmt1.executeUpdate();
				String es1 = "b";
				String msg1 = "汽车入库成功！！！";
				hashMap.put("RetCode1", es1);	
				hashMap.put("Msg1",msg1); 
			}
		//	pstmt.close();
		//	pstmt1.close();
	
		//	conn.close();
			car.closeAll(conn, pstmt, null);
			car.closeAll(conn, pstmt1, null);
			car.closeAll(conn, pstmt2, null);
		}catch (Exception e) {
			e.printStackTrace();  	     
	    }
		System.out.println(hashMap);
		return hashMap;   		
	}
	
//  汽车出库
	public HashMap<String, String> outCar(String out_carId,String outTime) throws SQLException, ClassNotFoundException{
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try{
		//	Class.forName("oracle.jdbc.driver.OracleDriver");
		//	Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.0.26:1521/PDB_APEX","APEX_UAT"," apex_uat");
			Car car = new Car();
			Connection conn = car.getConn();

			
			String sql1 = " SELECT PARKING_TYPE,IN_TIME FROM XYG_TEST_PARKING_INFORM WHERE CAR_NUM=?";				
			String sql2 = "UPDATE XYG_TEST_CAR_NEWFORM SET CHARGE=?,OUT_TIME = to_date(?,'yyyy-MM-dd HH24:mi:ss')  where CAR_NUM=? AND IN_TIME = to_date(?,'yyyy-MM-dd HH24:mi:ss')";		
			String sql3 = "UPDATE XYG_TEST_PARKING_INFORM SET CAR_NUM = NULL,IN_TIME = NULL WHERE CAR_NUM = ?";
			
	//		PreparedStatement pstmt = conn.prepareStatement(sql);
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			PreparedStatement pstmt3 = conn.prepareStatement(sql3);
			
			System.out.println("OutTime:"+outTime);
				
			pstmt1.setNString(1, out_carId);
			
			
			ResultSet rs1 = pstmt1.executeQuery();			

			if(rs1.next()){
				String type = rs1.getString(1);
				String inTime = rs1.getString(2);
				System.out.println("inTime1:"+inTime);
				String inTime1 = inTime.substring(0, 19);
				charge = car.fee(type, inTime, outTime);

				if(charge<=0){
					String es2= "e";
					String msg2 = "汽车出库失败！汽车出库时间小于进库时间，请重新输入！！！";
					hashMap.put("RetCode2", es2);	
					hashMap.put("Msg2",msg2); 
				}else{
					System.out.println("inTime3:"+inTime1);
					pstmt2.setInt(1, charge);
					pstmt2.setNString(3, out_carId);
					pstmt2.setNString(2, outTime);
					pstmt2.setNString(4, inTime1);
					

					pstmt2.executeUpdate();	
					pstmt3.setNString(1,out_carId);
					pstmt3.executeUpdate();
					
					String es2 = "c";
					String msg2 = "汽车出库成功！！！";

					hashMap.put("RetCode2", es2);	
					hashMap.put("Msg2",msg2);

					
				}
			}else{
				String es2 = "d";
				String msg2 = "汽车出库失败！汽车不存在，请重新输入！！！";
				hashMap.put("RetCode2", es2);	
				hashMap.put("Msg2",msg2); 
				
			}					
					
			car.closeAll(conn,pstmt1,rs1);
			car.closeAll(conn,pstmt2,null);
			car.closeAll(conn,pstmt3,null);
		}catch (Exception e) {
			e.printStackTrace();  
	    }
		System.out.println(hashMap);
		return hashMap;  	
	}
	
// 判断是否为空
	/*public String isNull(String n){
		n = (n.length()<=0)?"":n;
		return  n;
	}*/
	
//  判断时间是否过夜
	public int overNight(int n){
		n= (n<0)?0:1;
		return n;
	}
//  判断是否为整数	
	public int isPlus(int n){
		n=(n<=0)?0:n;
		return n;
	}	
//  判断是否超过限额
	public int FeeA(int n){
		n=(n>=40)?40:n;
		return n;
	}
	
	public int FeeB(int n){
		n=(n>=64)?64:n;
		return n;
	}
//   计算总费用
	public int fee(String type,String inTime,String outTime) throws ClassNotFoundException, SQLException{	
		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		 try {  
			long inTime_Sec =  format.parse(inTime).getTime();
	        long outTime_Sec =  format.parse(outTime).getTime();
			int Di =  format.parse(inTime).getHours();
			int Do = ( format.parse(outTime).getSeconds()==0 && format.parse(outTime).getMinutes()==0)
	        		?format.parse(outTime).getHours():(format.parse(outTime).getHours()+1);
	        int day1 = format.parse(inTime).getDate();
	    //  System.out.println(day1);
	        int day2 = format.parse(outTime).getDate();
	    //    System.out.println("dddd:"+(format.parse(outTime).getHours()));
	        int days = (int) ((outTime_Sec-inTime_Sec)/1000/3600/24);
	        int hour = (int) ((outTime_Sec-inTime_Sec)/1000/3600);
//	        int day =format.parse(outTime).getDay()-format.parse(inTime).getDay();
       //     System.out.println("天数days："+days);
       //     System.out.println("iS:"+inTime_Sec);
       //     System.out.println("oS:"+outTime_Sec);
  //          A类车位价格	 
            Car car = new Car();
			Connection conn = car.getConn();
            
            
            if(type.equals("A")){
	 			if(days<1 && day1==day2){ 	
	 		//		System.out.println("Di1:"+Di);
	 	     //       System.out.println("Do1:"+Do);
	 	     //       System.out.println("hour1:"+hour);
	 	     //       System.out.println("day-1:"+(hour-(24-Di)-Do+1));
	 				if(Di<8){
	 					charge = 10+car.FeeA(car.isPlus(Do-8)*5);
	 				}else{
	 					charge = car.FeeA((Do-Di-car.isPlus(Do-22))*5+car.overNight(Do-22)*10);
	 				}			
	 			}else{	 
	 	//			System.out.println("Di2:"+Di);
	 	//            System.out.println("Do2:"+Do);
	 	 //           System.out.println("hour2:"+hour);
	 	 //           System.out.println("day-1:"+(hour-(24-Di)-Do+1));
	 				if(Di<8){	 		
	 					charge = 50+car.FeeA(car.isPlus(Do-8)*5)+40*car.isPlus((hour-(24-Di)-Do+1)/24);
	 				}else{     
	 		           charge = car.FeeA(10+car.isPlus(22-Di)*5)+40*car.isPlus((hour-(24-Di)-Do+1)/24)+ car.FeeA(car.isPlus(Do-8)*5);
	 				}
	 			}			
	 		}else{
	 			System.out.println("B");
  //          B类车位价格	
	 			if(days<1 && day1==day2){ 				
	 				if(Di<8){
	 					charge = 15+car.FeeB(car.isPlus(Do-8)*8);
	 				}else{
	 					charge = car.FeeB((Do-Di-car.isPlus(Do-22))*8+car.overNight(Do-22)*10);
	 				}			
	 			}else{
	 				if(Di<8){
	 					charge = 79+car.FeeB(car.isPlus(Do-8)*5)+64*((hour-(24-Di)-Do)/24);
	 				}else{ 			
	 					charge = car.FeeB(15+car.isPlus(22-Di)*8)+64*((hour-(24-Di)-Do)/24)+ car.FeeB(car.isPlus(Do-8)*8);
	 				}
	 			}		 						
	 	   }	 		       
	 } catch (ParseException e) { 
	 		 System.out.println("eeeee");
	            e.printStackTrace();  
	 }  	   
		System.out.println("费用:"+charge);
 		return charge;     
  }	

	
//  查询历史表 得出费用
	public int allFee(String out_carId,String outTime){
		int fee = 0;
		try{
			Car car = new Car();
			Connection conn = car.getConn();
			
			String sql1 = "SELECT CHARGE FROM XYG_TEST_CAR_NEWFORM WHERE CAR_NUM=? AND OUT_TIME = to_date(?,'yyyy-MM-dd HH24:mi:ss')";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setNString(1,out_carId);
			pstmt1.setNString(2,outTime);
			
			ResultSet rs1 = pstmt1.executeQuery();
			while(rs1.next()){
				 fee = rs1.getInt(1);
		//		System.out.println("总床位:"+parkNum);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
//		System.out.print("fefe:"+fee);
		return fee;
	}

	public static void main(String args[]) throws ClassNotFoundException, SQLException{
		Car car = new Car();
	//	car.insCar("a003","2017-03-16 08:00:02");
		/*Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(date);
		System.out.println(str);*/
		car.outCar("a001","2017-03-16 14:12:06");
	//	car.allFee("a002","2017-03-09 18:10:00");
	//	car.fee("A", "2017-3-1 10:00:10","2017-3-2 24:00:00");
	//	car.equals(9);
	}
	
}
