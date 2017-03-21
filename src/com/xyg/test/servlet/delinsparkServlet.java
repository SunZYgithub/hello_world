package com.xyg.test.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xyg.test.Park;

@SuppressWarnings("serial")
public class delinsparkServlet extends HttpServlet{
	
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

		String typeC = req.getParameter("typeC");
	
		if(typeC.equals("fc1")){
/*	添加车位   */
			System.out.println("FC111");
			HttpSession sess1 = req.getSession();
			//	System.out.println("sess1:"+sess1);
			String ins_parkId = req.getParameter("ins_parkId");
			String ins_parkType  = req.getParameter("ins_parkType");	
			try {
				Park park = new Park();
				HashMap<String, Object> hashMap1 = park.inspark(ins_parkId,ins_parkType);
				String RetCode1 = (String)hashMap1.get("RetCode1"); 
				System.out.println("hashMap1333:"+hashMap1);
				if(RetCode1.equals("aa")){
					HashMap<Object, Object> map = new HashMap<Object, Object>();
			        String MSG1 = (String)hashMap1.get("Msg1");	
		
				    sess1.setAttribute("msg1",MSG1);
				       
			    }else if(RetCode1.equals("bb")){
			    	HashMap<Object, Object> map = new HashMap<Object, Object>();
			        String MSG1 = (String)hashMap1.get("Msg1");	
		
			        sess1.setAttribute("msg1",MSG1);
			    } 
				
				RequestDispatcher rd = req.getRequestDispatcher("Success.jsp");
		    	rd.forward(req, res);
			} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
					
		}else if(typeC.equals("fc2")){
/*   删除车位	*/
			System.out.println("FC222");
			HttpSession sess2 = req.getSession();
			String del_parkId = req.getParameter("del_parkId");	
			try {
				Park park = new Park();
				HashMap<String, Object> hashMap2 = park.delpark(del_parkId);
				String RetCode2 = (String)hashMap2.get("RetCode2"); 
				System.out.println("hashMap2333:"+hashMap2);
				if(RetCode2.equals("cc")){
					HashMap<Object, Object> map = new HashMap<Object, Object>();
			        String MSG2 = (String)hashMap2.get("Msg2");	
			  
				        sess2.setAttribute("msg2",MSG2);
		        }else if(RetCode2.equals("dd")){
		        	HashMap<Object, Object> map = new HashMap<Object, Object>();
			        String MSG2 = (String)hashMap2.get("Msg2");	
			  
				        sess2.setAttribute("msg2",MSG2);
		        } 

				RequestDispatcher rd = req.getRequestDispatcher("Success.jsp");
		    	rd.forward(req, res);
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