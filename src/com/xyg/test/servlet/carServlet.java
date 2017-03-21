package com.xyg.test.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xyg.test.Car;
import com.xyg.test.Park;

@SuppressWarnings("serial")
public class carServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
    {
       doGet(req,res);
    }
    @SuppressWarnings({ })
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
    {
    	req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");	
		
		String typeF = req.getParameter("typeF");
		
		if(typeF.equals("ff1")){
/*	车辆进库*/
			System.out.println("ff111");
		//	HttpSession sess1 = req.getSession();
			//	System.out.println("sess1:"+sess1);
			String ent_carId = req.getParameter("ent_carId");
			String entTime  = req.getParameter("entTime");	
			if(entTime.equals("")){
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String str = sdf.format(date);
				entTime = str;
				System.out.println(str);
			}
			try {	
				Car car = new Car();
				HashMap<String, String> hashMap1 = car.insCar(ent_carId,entTime);
				String RetCode1 = (String)hashMap1.get("RetCode1"); 
			//	System.out.println("hashMap1333:"+hashMap1);
				if(RetCode1.equals("a")){
			        String MSG1 = (String)hashMap1.get("Msg1");
			//        System.out.println("MSG1:"+MSG1);
			    //    sess1.setAttribute("msg3",MSG1);
			        String json = "{\"msg\":\""+MSG1+"\"}";
			//        System.out.println("json:"+json);
			        res.getWriter().print(json);
			    }else if(RetCode1.equals("b")){
			        String MSG1 = (String)hashMap1.get("Msg1");
			  //      System.out.println("MSG1:"+MSG1);
			     //   sess1.setAttribute("msg3",MSG1);
			        String json = "{\"msg\":\""+MSG1+"\"}";
			  //      System.out.println("json:"+json);
			        res.getWriter().print(json);
			    } 
				 
//				res.sendRedirect("Success.jsp");
				/*RequestDispatcher rd = req.getRequestDispatcher("Success.jsp");
		    	rd.forward(req, res);*/
			} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}else if(typeF.equals("ff2")){
	/*	车辆出库 */		
			System.out.println("ff222");
	//		HttpSession sess2 = req.getSession();
			String out_carId = req.getParameter("out_carId");
			String outTime  = req.getParameter("outTime");
			if(outTime.equals("")){
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String str = sdf.format(date);
				outTime = str;
				System.out.println("str11:"+outTime);
			}
			try {
				Car car = new Car();
				HashMap<String, String> hashMap2 = car.outCar(out_carId,outTime );
				String RetCode2 = (String)hashMap2.get("RetCode2"); 
				System.out.println("hashMap1333:"+hashMap2);
				if(RetCode2.equals("c")){
			        String MSG2 = (String)hashMap2.get("Msg2");

			        int fee = car.allFee(out_carId, outTime);
			//        System.out.println("fee111:"+fee);
			        String json = "{\"fee\":"+fee+",\"msg\":\""+MSG2+"\"}";
			//        System.out.println("json:"+json);
			        res.getWriter().print(json);
			        
			    }else if(RetCode2.equals("d")){
			        String MSG2 = (String)hashMap2.get("Msg2");
		//	        System.out.println("MSG2:"+MSG2);
		//	        sess2.setAttribute("msg4",MSG2);
			        int fee = 0;
			        String json = "{\"fee\":"+fee+",\"msg\":\""+MSG2+"\"}";
		//	        System.out.println("json:"+json);
			        res.getWriter().print(json);
			    
			    } else{
			    	 String MSG2 = (String)hashMap2.get("Msg2");
		//	    	 System.out.println("MSG2:"+MSG2);
			//	     sess2.setAttribute("msg4",MSG2);
			    	 int fee = 0;
			    	 String json = "{\"fee\":"+fee+",\"msg\":\""+MSG2+"\"}";
	//			     System.out.println("json:"+json);
				     res.getWriter().print(json);
			    }
		
				
				
				/*RequestDispatcher rd = req.getRequestDispatcher("Success.jsp");
		    	rd.forward(req, res);*/
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 				
	
    }
}
