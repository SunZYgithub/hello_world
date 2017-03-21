package com.xyg.test.servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.xyg.test.Park;

@SuppressWarnings("serial")
public class selectHisServlet extends HttpServlet{

	 
		protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	    {
	       doGet(req,res);
	    }
	    @SuppressWarnings({ "unchecked" })
		protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	    {
	    	req.setCharacterEncoding("utf-8");
			res.setCharacterEncoding("utf-8");
			res.setContentType("text/html;charset=utf-8");
			
/*  查询历史车位  */	
			Park park = new Park();

			
			int pageSize = Integer.parseInt(req.getParameter("pageSize"));
			int currentpage = Integer.parseInt(req.getParameter("currentpage"));	
			String lastPageFlag = req.getParameter("lastPage");
			
		/*总共历史页码*/
			int pageCount = park.gethisPage(pageSize);
		/*判断是否是最后一页*/
			if(lastPageFlag.equals("true")){
				currentpage = pageCount;
			}
			if(currentpage>=pageCount){
				currentpage=pageCount;	
			}else
			if(currentpage<=1){
				currentpage=1;	
			}
			StringBuffer sb = new StringBuffer();

			ArrayList<Object> L = new ArrayList<Object>();
			ArrayList<Object> l = new ArrayList<Object>();
			L = park.select_HisPages(currentpage,pageSize);
		//	System.out.println(L.size());
			
			for(int i = 0;i<L.size();i++){
				
				System.out.println("SBSIZE2:"+L.size());
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
			int row = L.size();
			
			String sb2 = "{\"pageCurrentpage\":"+currentpage+",\"allPage\":"+pageCount+",\"row\":"+row+",\"da\":"+sb+"}";
			System.out.println("SB2:"+sb2);
			
			res.getWriter().print(sb2);
		
		
		
	 }
	    
	   
}
