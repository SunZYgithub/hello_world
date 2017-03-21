package com.xyg.test.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.xyg.test.logCheck;

@SuppressWarnings("serial")
public class Servlet extends HttpServlet{
	//protected HttpSession sess;
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
    {
       doGet(req,res);
    }
    @SuppressWarnings("rawtypes")
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
    {
    	req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		HttpSession sess = req.getSession();
		String username = req.getParameter("username");
		String password = req.getParameter("password");	
		logCheck md = new logCheck();		
		HashMap hashMap = new HashMap();
		try {
			hashMap = md.check(username,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String RetCode = (String)hashMap.get("RetCode");        
        if(RetCode.equals("a")){
        	String MSG = (String)hashMap.get("Msg");
        	sess.setAttribute("msg",MSG);
        	RequestDispatcher rd = req.getRequestDispatcher("Success.jsp");
    		rd.forward(req, res);
        }else{
        	String MSG = (String)hashMap.get("Msg");
        	sess.setAttribute("msg",MSG);
        	RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
    		rd.forward(req, res);
        }       				
    }
}
