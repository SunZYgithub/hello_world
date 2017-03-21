<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >  
    <title>My JSP 'index.jsp' starting page</title>	
	<script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
	<style>

		body{background:#abcdef;width:100%;height:100%;font-family:"微软雅黑";margin:0;padding:0;}
		#btns{width:100px;height:30px;font-size:18px;margin:30px 0 0 0;font-family:"微软雅黑";}
		#btns:hover{cursor:pointer;}
		input:-webkit-autofill { 
			-webkit-box-shadow: 0 0 0px 1000px #999 inset; 
			}
		input:focus{
			transition:border linear .2s,box-shadow linear .5s;
			-moz-transition:border linear .2s,-moz-box-shadow linear .5s;
			-webkit-transition:border linear .2s,-webkit-box-shadow linear .5s;
			outline:none;border-color:rgba(93,149,242,.75);
			box-shadow:0 0 8px rgba(93,149,242,.105);
			-moz-box-shadow:0 0 8px rgba(93,149,242,.5);
			-webkit-box-shadow:0 0 8px rgba(93,149,242,3);
		}
	
}

	</style>
  </head>
  
  <body> 

    
  	 <div class = ".block" style="background:;text-align:center;width:70%;height:400px;margin:100px auto;"> 
  		<h1 style="text-align:center;">欢迎来到停车管理系统！！！</h1>  
     	<form action="A.A" method="post" style="width:100%;height:100%;font-size:20px;">
     		<div style="margin:120px 0 20px 0;overfloat:hidden;">
		    	<label style="font-size:18px;" for="username">用户名：</label>
		    	<input style="border:none;height:18px;" type="text" id="username" name="username"/>
	    	</div>
	    	<div style="margin:20px 0 ;">
		    	<label style="font-size:18px;" for="password">密&nbsp;&nbsp;&nbsp;码：</label>
		    	<input  style="border:none;height:18px;" type="password" id="password" name="password"/>
			</div>
			
	    	<input type="submit" id="btns" value="登入" />
        </form>
    </div> 
    <%
     HttpSession sess = request.getSession();
     String errMsg = (String)sess.getAttribute("msg");
     sess.setAttribute("msg", null);
    %>
    <script>
       var err = '<%=errMsg %>';
       console.log(err);
       if(err!='null'){
          alert(err);
       }      
    </script> 
    
  </body>
</html>
