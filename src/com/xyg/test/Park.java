package com.xyg.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;



public class Park {
	
	//时间格式转换
	public Object Timestamp2uDate(Object data) throws Exception{
		Object a = null;
		if(data.getClass().getName().equals("java.sql.Timestamp")){
			SimpleDateFormat formatter; 
		    formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss"); 
		    a = formatter.format(data);
		}else{
			a = data;
		}
		return a;
	}
	
//   添加车位
	public HashMap<String, Object> inspark(String ins_parkId,String ins_parkType ) throws SQLException, ClassNotFoundException{
	//	Car car = new Car();
		HashMap<String, Object> hashMap = new HashMap<String,Object>(); 
		try{	
			Car car = new Car();
			Connection conn = car.getConn();
			
			Park p = new Park();

			String sql = "insert into XYG_TEST_PARKING_INFORM(PARKING_ID,PARKING_CODE,PARKING_TYPE) values(XYG_TEST_PARKING_INFORM_S.NEXTVAL,?,?)";
			String sql1 = " SELECT PARKING_CODE FROM XYG_TEST_PARKING_INFORM WHERE PARKING_CODE = ?"; 
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			
			pstmt.setNString(1,ins_parkId);
			pstmt.setNString(2,ins_parkType);
			pstmt1.setNString(1,ins_parkId);
	//		System.out.println(sql);				
			ResultSet rs1 = pstmt1.executeQuery();
				
			if(rs1.next()){	
				
				String es1 = "aa";
				String msg1 = "添加失败！车位已存在，请重新输入！！！";
				hashMap.put("RetCode1", es1);	
				hashMap.put("Msg1",msg1); 
				
				String allPark =  Integer.toString(p.getallparkNum());
				String nullPark =  Integer.toString(p.getnullparkNum());
				hashMap.put("aP",allPark); 
				hashMap.put("nP",nullPark); 
	//			System.out.println("ap:"+allPark);
	//			System.out.println("np:"+nullPark);
			}else{
				pstmt.executeUpdate();
				String es1 = "bb";
				String msg1 = "车位添加成功！！！";
				hashMap.put("RetCode1", es1);	
				hashMap.put("Msg1",msg1); 
				
				String allPark =  Integer.toString(p.getallparkNum());
				String nullPark =  Integer.toString(p.getnullparkNum());
				hashMap.put("aP",allPark); 
				hashMap.put("nP",nullPark);
				
				
	//			System.out.println("ap:"+allPark);
	//			System.out.println("np:"+nullPark);
			}
		
			
			car.closeAll(conn,pstmt,null);
			car.closeAll(conn,pstmt1,rs1);
		}catch (NullPointerException e) {
			e.printStackTrace();	
	    }  
//		System.out.println("hashMap1"+hashMap);
		return hashMap;	
	}

//删除车位
	public HashMap<String, Object> delpark(String del_parkId) throws SQLException, ClassNotFoundException{
	//	Car car = new Car();
		HashMap<String,Object> hashMap = new HashMap<String,Object>(); 
		try{
		//	Class.forName("oracle.jdbc.driver.OracleDriver");
		//	Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.0.26:1521/PDB_APEX","APEX_UAT"," apex_uat");
			Car car = new Car();
			Connection conn = car.getConn();
			
			Park p = new Park();
			
			String sql = "delete from XYG_TEST_PARKING_INFORM where PARKING_CODE = ?";
			String sql1 = " SELECT PARKING_CODE FROM XYG_TEST_PARKING_INFORM WHERE PARKING_CODE = ?";
//			String sql2 = " SELECT count(*) FROM XYG_TEST_PARKING_INFORM";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt.setNString(1,del_parkId);
			pstmt1.setNString(1,del_parkId);
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next()){
				
				pstmt.executeUpdate();	
				String es2= "cc";
				String msg2 = "车位删除成功！！！";
				hashMap.put("RetCode2", es2);	
				hashMap.put("Msg2",msg2);
				
				String allPark =  Integer.toString(p.getallparkNum());
				String nullPark =  Integer.toString(p.getnullparkNum());
				
				hashMap.put("ap",allPark); 
				hashMap.put("nP",nullPark);
				System.out.println("ap:"+allPark);
				System.out.println("np:"+nullPark);
				
			}else{
				String es2 = "dd";
				String msg2 = "删除失败！车位不存在，请重新输入！！！";
				hashMap.put("RetCode2", es2);	
				hashMap.put("Msg2",msg2);
				
				String allPark =  Integer.toString(p.getallparkNum());
				String nullPark =  Integer.toString(p.getnullparkNum());
				hashMap.put("aP",allPark); 
				hashMap.put("nP",nullPark);
				
				System.out.println("ap:"+allPark);
				System.out.println("np:"+nullPark);
			}
				
			car.closeAll(conn,pstmt,null);
			car.closeAll(conn,pstmt1,rs1);
		}catch (Exception e) {
			e.printStackTrace();	       
	    } 
//		System.out.println("hashMap2"+hashMap);
		return hashMap;	
	}
	
	
    /* 获取总页码数*/
	public int getPage(int park_pageSize){
		int rowCount = 0;//一共有多少条记录 
		int pageCount = 0;
		int page = 0;
		try{
			Car car = new Car();
			Connection conn = car.getConn();
			String sql = "select count(*) from XYG_TEST_PARKING_INFORM";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				rowCount = rs.getInt(1);
	//			System.out.println(rowCount);
			}
			page = rowCount%park_pageSize;
			if(page!=0){
				pageCount = rowCount/park_pageSize+1;
			}else{
				pageCount = rowCount/park_pageSize;
			}
	//		System.out.println("最大页码1："+pageCount);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pageCount;
	}
	/* 获取历史表总页码数*/
	public int gethisPage(int park_pageSize){
		int rowCount = 0;//一共有多少条记录 
		int pageCount = 0;
		int page = 0;
		try{
			Car car = new Car();
			Connection conn = car.getConn();
			String sql = "select count(*) from XYG_TEST_CAR_NEWFORM";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				rowCount = rs.getInt(1);
	//			System.out.println(rowCount);
			}
			page = rowCount%park_pageSize;
			if(page!=0){
				pageCount = rowCount/park_pageSize+1;
			}else{
				pageCount = rowCount/park_pageSize;
			}
	//		System.out.println("最大页码1："+pageCount);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pageCount;
	}
	
//  查询总车位数
	public int getallparkNum(){
		int parkNum = 0;//一共有多少条车位 
		try{
			Car car = new Car();
			Connection conn = car.getConn();
			
			String sql1 = " SELECT count(*) FROM XYG_TEST_PARKING_INFORM";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			ResultSet rs1 = pstmt1.executeQuery();
			while(rs1.next()){
				 parkNum = rs1.getInt(1);
		//		System.out.println("总床位:"+parkNum);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return parkNum;
	}
	
	
	
//  查询空车位数
	public int getnullparkNum(){
		int nullparkNum = 0;//多少空车位 
		try{
			Car car = new Car();
			Connection conn = car.getConn();
			
			String sql1 = " SELECT count(*) FROM XYG_TEST_PARKING_INFORM WHERE CAR_NUM IS NULL";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			ResultSet rs1 = pstmt1.executeQuery();
			while(rs1.next()){
				 nullparkNum = rs1.getInt(1);
		//		System.out.println("空车位:"+parkNum);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return nullparkNum;
	}
	

	//查询车位信息	
	public  ArrayList<Object> selectPage(int park_currentpage,int park_pageSize){		
		ArrayList<Object> Totallist = new ArrayList<Object>();	
		Park park = new Park();
	//	int pageCount = park.getPage(pageSize);
		try{
			Car car = new Car();
			Connection conn = car.getConn();
	  //  	System.out.println("conn:"+conn);
	//		int rowCount = 0;//一共有多少条记录
	//		int currentpage = 1;//当前页码为1
		//	int pageCount;//计算总页数rowCount/pageSize
			
			int start = (park_currentpage-1)*park_pageSize+1;
			int end = park_currentpage*park_pageSize;
		//	System.out.println("CP:"+park_currentpage);
	//		pageCount = rowCount/pageSize;
			String sql = "select * from (select e.*,rownum rn from (select * from XYG_TEST_PARKING_INFORM) e) where rn>=? and rn<=?";
	//		String sql1 = "select * from (select e.*,rownum rn from (select * from XYG_TEST_PARKING_INFORM) e) where rn>=? and rn<=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()){	
				ArrayList<String> list = new ArrayList<String>();
				for(int i = 1;i<rs.getFetchSize();i++){
			//		System.out.println(rsmd.getColumnName(i));					
			//		System.out.println(rsmd.getColumnName(i) +":"+rs.getString(i));
					
			/*		String rsStr = car.isNull(rs.getString(i));*/
			//		System.out.println(rs.getObject(i)==null?"":rs.getObject(i));
					list.add("\""+rsmd.getColumnName(i) +"\":\""+(rs.getObject(i)==null?"":Timestamp2uDate(rs.getObject(i)))+"\"");
			//     	System.out.println("list:"+list);
				}
				Totallist.add(list);
			//	System.out.println("tolist:"+Totallist);
			}
			
			car.closeAll(conn,pstmt,rs);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("TO:"+Totallist);
		return Totallist;
	}
	
	//查询进出历史表	
		public  ArrayList<Object> select_HisPages(int currentpage,int pageSize){
			ArrayList<Object> Totallist = new ArrayList<Object>();	
		//	int pageCount = park.getPage(pageSize);
			try{
				Car car = new Car();
				Connection conn = car.getConn();

				int start1 = (currentpage-1)*pageSize+1;
				int end1 = currentpage*pageSize;
		
				String sql = " select * from (select e.*,rownum rn from (select * from XYG_TEST_CAR_NEWFORM) e) where rn>=? and rn<=? ";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start1);
				pstmt.setInt(2, end1);
				ResultSet rs = pstmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				while(rs.next()){	
					ArrayList<String> list = new ArrayList<String>();
					for(int i = 1;i<rs.getFetchSize();i++){				
						list.add("\""+rsmd.getColumnName(i) +"\":\""+(rs.getObject(i)==null?"":Timestamp2uDate(rs.getObject(i)))+"\"");
					}
					Totallist.add(list);
					//SimpleDateFormat formatter; 
				    //formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss"); 
				    //System.out.println("I_T:"+rs.getObject("IN_TIME").getClass().getName().equals("java.sql.Timestamp"));
				    //System.out.println("H_I:"+rs.getObject("HISTORY_ID").getClass().getName().equals("java.sql.Timestamp"));
				    //System.out.println("date:"+formatter.format(rs.getObject("IN_TIME")));
				}
				car.closeAll(conn,pstmt,rs);
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println("TO1:"+Totallist);
			return Totallist;
		}		
	
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException{
		Park p = new Park();
		p.select_HisPages(1, 1);
	//	p.selectPage(1, 1);
	//	p.inspark("A156", "A");
	//	p.delpark("A156");
	//	int ap = p.getallparkNum();
	//	int np = p.getnullparkNum();
	//	System.out.println("总车位:"+ap);
	//	System.out.println("空车位:"+np);
		/*StringBuffer sb = new StringBuffer();
		ArrayList<Object> L = new ArrayList<Object>();
		ArrayList<Object> l = new ArrayList<Object>();
		L = p.selectPage(1,4);
		for(int i = 0;i<L.size();i++){
			l = (ArrayList<Object>)L.get(i);
			for(int j = 0;j<l.size();j++){
				if(j==0){
					if(i==0){
						sb.append("[{");
					}else{
						sb.append("{");
					}	
				}else if(j==(l.size()-1)){
					if(i==(L.size()-1)){
						sb.append(l.get(j)+"}]");
					}else{
						sb.append(l.get(j)+"},");
					}					
				}else{
					sb.append(l.get(j)+",");
				}				
			}
	
		}
		System.out.println("sb:"+sb);*/
	}	
}