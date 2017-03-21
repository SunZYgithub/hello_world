<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">   
    <title>My JSP 'Success.jsp' starting page</title>
	
	<script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.js"></script>		
 	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,user-scalable:no">
	<style>
		@media screen and (min-width:738px) {/*分辨率大于900实行这里面的CSS样式*/
			 ul{width:100%;height:80px;}
        	 ul li{width:25%;float:left;text-align:center;font-size:20px;padding:10px 0;}    
      
		}

		@media screen and (max-width: 737px) { /*当屏幕尺寸小于600px时，应用下面的CSS样式*/
			ul{width:100%;height:20px;}
			ul li{width:25%;float:left;height:34px;line-height:31px;text-align:center;font-size:18px;padding:10px 0;}
		}  
		 @media screen and (max-width: 665px) {
		 	ul{width:100%;height:15px;}
			ul li{width:25%;float:left;height:30px;font-size:15px;padding:10px 0;} 
		 }
		  @media screen and (max-width: 557px) {
		 	ul{width:100%;height:15px;}
			ul li{width:25%;float:left;height:58px;font-size:15px;padding:10px 0;} 
		 }
        *{margin:0;padding:0;}
        body,html{width:100%;height:100%;background:#fff;font-family:"微软雅黑";position:relative;}
        
        .btnHid{visibility:hidden;}
        #tab tr td{font-size:15px;border:1px solid black;}
         html { overflow-x: hidden; overflow-y:scoll; } 
        tr{width:25%;height:35px;}
        thead tr{font-size:25px;}
        td{width:220px;height:20px;text-align:center;}
        ul,li{list-style:none;}
   
        #tabForm tr td{font-size:15px;border:1px solid black;}
       
      	h1{width:100%;text-align:center;margin:20px 0;}
        input:-webkit-autofill { 
			-webkit-box-shadow: 0 0 0px 1000px #FFF inset; 
			}
		input:focus{
			transition:border linear .2s,box-shadow linear .5s;
			outline:none;border-color:rgba(93,149,242,.75);
			box-shadow:0 0 8px rgba(93,149,242,.105);
		} 
        .left{float:left;!import;}
  
        .li1 {background:#000;color:#FFF;}
      	.li2,.li3,.li4{background:#333;color:#666;}
      	.changeLi{color:#999;cursor:pointer;background:#333;}
      	.popWindow {  
	        background-color:#9D9D9D;  
	        width: 100%;  
	        height: 128%;  
	        left: 0;  
	        top: 0px;  
	        filter: alpha(opacity=50);  
	        opacity: 0.7;  
	        z-index: 1;  
	        position: absolute;            
    	}  
        #quit{
        	    margin: -51px -161px;
        		background:#fff;
        		border:1px solid red;
				text-align:center;
				font-size:30px;
				position: absolute;
				top: 50%;
				left: 50%;
		
				min-width: 320px;
		        line-height: ;    
		        color:#000;  
		        z-index: 2;  		       
		        text-align:center;  
        } 
        #insertPark{overfloat:hidden;}
        button:hover{cursor:pointer;}
    </style>
  </head> 
  <body>
  	  <div style="position:fixed;width:100%;height:126px;left:0;top:0;"> 
      	  <div style="background:#abcdef;height:80px;line-height:80px;font-size:30px;font-weight:bold;letter-spacing:10px;text-align:center;">后台管理页面</div>
          <ul >
 		  		<li class="li1">车位管理</li>
 		   		<li class="li2 ">停车场进出库操作	</li>
 		  		<li class="li3">停车场历史进出查询</li>
 		  		<li class="li4">退出登录</li>
 	      </ul> 
	  </div>
	  <div style="width:100%;height:100%;">
<!-- //  车位操作	 -->
		<div id="opera_park" style="margin-top:158px;"> 
			<h3 style="margin:20px 0 10px 200px;">一：新增车位管理</h3>	
		 	<form action="C.C" name="formC" method="post" id="insertPark" style="border-bottom:2px solid #e8ecee;" > 		
	<!-- 	  加一个hidden隐藏域 -->
		    	<input type="hidden" name="typeC" value="fc1" />
		    	<div style="width:100%;margin:10px 0 7px 0px;font-size:20px;height:50px;">
			    	<label for="" style="display:block;font-size:20px;margin:0 0 0 200px;float:left;">新增车位编号:</label>&nbsp;&nbsp;&nbsp;
			    	<input type="text"  placeholder="例如：A001" style="float:left;margin:5px 0 0 20px;" id="ins_parkId" name="ins_parkId"/>
			    	<p id="cue_parkC" style="width:322px;height:20px;line-height:20px;margin:4px 0 -9px 200px;text-align:center;font-size:16px;"></p>
		    	</div>
		    	<div style="margin:0px 0 10px 200px;font-size:20px;height:50px;overfloat:hidden;">
			    	<label for="" class=".left">新增车位类型:</label>&nbsp;&nbsp;&nbsp;
			    	<input id="ins_parkType" class=".left" type="text" name="ins_parkType" placeholder="只能填写'A'或者'B'" />
			    	<p id="cue_parkT" style="width:322px;height:20px;line-height:20px;margin-top:4px;text-align:center;font-size:16px;"></p>
		    	</div>
		    	<input type="submit" id="btn_insertPark" value="新增车位" onclick="return chkinPut(form)" style="width:100px; height:30px;margin:5px 0 10px 250px;cursor:pointer;"><br/>	
		    </form>
		    
		    <h3 style="margin:20px 0 10px 200px;">二：删除车位管理</h3>	
		    <form action="C.C" name="formC" method="post" id="delPark" style="border-bottom:2px solid #e8ecee;">
	 <!-- 	  加一个hidden隐藏域 -->
		    	<input type="hidden" name="typeC" value="fc2" />
		    	<div style="width:100%;height:46px;overfloat:hidden;margin:16px 0 10px 0 ;">
			    	<label for="" style="display:block;font-size:20px;margin:0 0 0 200px;float:left;">删除车位编号:</label>&nbsp;&nbsp;&nbsp;
			    	<input type="text"  placeholder="例如：A001" id="del_parkId" name="del_parkId" style="float:left;margin:5px 0 0 20px;"/><br/>
		    		<p id="cue_parkC1" style="width:322px;height:20px;line-height:20px;margin:9px 0 -9px 200px;text-align:center;"></p>
		    	</div>
		    	<input type="submit" id="btn_delPark" value="删除车位" onclick="return chkindelparkPut(form)" style="width:100px; height:30px;margin:5px 0 10px 250px;cursor:pointer;" >	
		    	<br/>   			
		    </form>
    <!--    添加<iframe>标签提交表单时不刷新页面 -->
		    <iframe id="rfFrame" name="rfFrame" src="about:blank" style="display:none;"></iframe> 
	<!--    登入提示框 -->	   
		       <%
		     	 HttpSession sess = request.getSession();
			 	 
			     String errMsg3 = (String)sess.getAttribute("msg");
			     sess.setAttribute("msg", null);			 		      

			 	 String errMsg1 = (String)sess.getAttribute("msg1");
			 	 String errMsg2 = (String)sess.getAttribute("msg2");
			   	 sess.setAttribute("msg1", null);
			   	 sess.setAttribute("msg2", null);	
				  	
		   	  %>  
		   	 
		    <br/>
		    <h3 style="margin:20px 0 10px 200px;">三：查询车位</h3>	
		    <div style="width:100%;margin:10px 0 10px 0px;font-size:20px;">
			    <label for = "" style="display:block;font-size:20px;margin:0 0 0 200px;float:left;">当前车位页码:</label>&nbsp;&nbsp;&nbsp;
				<input type = "text" readonly = "readonly" name = "park_currentpage" id="park_currentpage" value="1"/><br/>
			</div>
			<div style="width:100%;margin:10px 0 10px 0px;font-size:20px;">
				<label for = "park_pageSize" style="display:block;font-size:20px;margin:0 0 0 200px;float:left;">当前车位行数:</label>&nbsp;&nbsp;&nbsp;
				<input type = "text" name ="park_pageSize" id="park_pageSize" value="5"/><span>可更改车位行数</span>
			</div>
			
			<h2 style="margin:40px 0 0px 200px;">显示车位信息:</h2> 
			<h3 style="margin:0px 0 0px 200px;">现有车位数量: <span class="nowparkNum" style="color:red;font-size:26px;"></span></h3>
			<h3 style="margin:0px 0 20px 200px; width: 513px">空车位数量: <span class="nownoparkNum" style="color:red;font-size:24px;"></span></h3>
		    <table border="3" style="margin:10px 0  0 200px; border-collapse:collapse;">
		    	<thead >
		      		<tr>
			      		<th>车位号</th>
			      		<th>车位类型</th>
			      		<th>车牌号</th>
			      		<th>入库时间</th>
		      		</tr>
		      	</thead>
		      	<tbody id="tab"></tbody>
		    </table> 
		    <div id="" style="margin:0 0 300px 200px;overfloat:hidden;width:80%;height:20px;">
			    <button id = "park_fir1" style="width:40px;height:25px;margin:20px 10px 0 0;float:left;">首页</button>
				<button id = "park_pre1" style="width:60px;height:25px;margin:20px 10px 0 0;float:left;">上一页</button>
			    <button id = "park_next1" style="width:60px;height:25px;margin:20px 10px 0 0;float:left;">下一页</button>
				<button id = "park_fin1" style="width:40px;height:25px;margin:20px 10px 0 0;float:left;">末页</button>
				<div id = "park_skip1" style="width:160px;float:left;margin:20px 0 0 10px;">跳转到：第
					<input type="text" name = "park_currentpage1" id="park_currentpage1" style="width: 42px; ">页
				</div>
				<button id="park_btn1" style="width:40px;height:25px;margin:20px 10px 0 0;float:left;">GO</button>
				<button id="park_refresh" style="width:100px;height:26px;float:left;margin:20px 0 0 140px;">刷新当前页面</button>
			</div>
<!-- 		 //存放scorllTop值 -->
		<!--  	<input type="hidden" id="hdnvalue" class="hdnvalue" value="0" /> -->
		 </div>
<!-- //  汽车出入库操作 -->		 
		 <div id="opera_car" style="display:none;margin-top:150px;">
		 	 <h3 style="margin:20px 0 10px 200px;">一：汽车进库操作</h3>
		 	 <form id="entForm" style="border-bottom:2px solid #e8ecee;">
		<!-- 	  加一个hidden隐藏域 -->
		    	<input type="hidden" name="typeF" id="typeF1" value="ff1" />
		 	 	<div style="margin:10px 0 7px 200px;font-size:20px;height:50px;">
	    			<label for="">车牌号码：</label>
	    			<input type="text"  placeholder="a001" id="ent_carId" name="ent_carId"/><br/>
	    			<p id="cue_carId" style="width:280px;height:20px;line-height:20px;margin-top:4px;font-size:16px;"></p>
	    		</div>
	    		<div style="margin:10px 0 7px 200px;font-size:20px;height:50px;">
	    			<label for="">入库时间：</label>
	    			<input type="text" placeholder="2017-01-01 00:00:00" id="entTime" name="entTime"/><br/>
	    			<p id="cue_entTime" style="width:280px;height:20px;line-height:20px;margin-top:4px;font-size:16px;"></p>
	    		</div>	
	    		<input type="button" id="carEnter" value="进库"  style="width:100px; height:30px;margin:5px 0 10px 250px;cursor:pointer;" />
	      	</form>
	      	<h3 style="margin:20px 0 010px 200px;">二：汽车出库操作</h3>
	      	<form id="outForm" >
	      	<!-- 	  加一个hidden隐藏域 -->
		    	<input type="hidden" name="typeF" id="typeF2" value="ff2" />
	      		<div style="margin:10px 0 7px 200px;font-size:20px;height:50px;">
	    			<label for="">车牌号码：</label>
	    			<input type="text" placeholder="a001" id="out_carId" name="out_carId"/><br/>
	    			<p id="cue_carId1" style="width:280px;height:20px;line-height:20px;margin-top:4px;font-size:16px;"></p>
	    		</div>
	    		<div style="margin:10px 0 7px 200px;font-size:20px;height:50px;">
	    			<label for="">出库时间：</label>
	    			<input type="text" placeholder="2017-01-01 00:00:00" id="outTime" name="outTime"/><br/>
	    			<p id="cue_outTime" style="width:280px;height:20px;line-height:20px;margin-top:4px;font-size:16px;"></p>
	    		</div>
	    		<input type="button" id="carOut" value="出库"  style="width:100px; height:30px;margin:5px 0 10px 250px;cursor:pointer;" />
	      	 </form>
	      	 <h2 style="margin:20px 0 010px 200px;">费用：￥:<span id="fee" style = "color:red;font-size:40px;">0</span></h2>
		
		 </div>
<!-- //  历史信息查询	 -->
		 <div id="hisForm" style="display:none;margin-top:150px;">	
			<div style="width:100%;margin:10px 0 10px 0px;font-size:20px;">
			    <label for = "" style="display:block;font-size:20px;margin:0 0 0 200px;float:left;">当前历史页码:</label>&nbsp;&nbsp;&nbsp;
				<input type = "text" readonly = "readonly" name = "currentpage" id="currentpage" value="1"/><br/>
			</div>
			<div style="width:100%;margin:10px 0 10px 0px;font-size:20px;">
				<label for = "pageSize" style="display:block;font-size:20px;margin:0 0 0 200px;float:left;">当前页码行数:</label>&nbsp;&nbsp;&nbsp;
				<input type = "text" name ="pageSize" id="pageSize" value="5"/><span>可更改页码行数</span>
			</div>
		 	<h3 style="border-top:2px solid #e8ecee;dispaly:block;padding:20px 0 10px 200px  ;">车库历史车辆信息</h3>		 	
		 	<table  border="3" style="margin:10px auto 0;width:80%;border-collapse:collapse;">
		 		<thead >
		      		<tr>
			      		<th>车位号</th>
			      		<th>车位类型</th>
			      		<th>车牌号</th>
			      		<th>进库时间</th>
			      		<th>出库时间</th>
			      		<th>消费总金额</th>
		      		</tr>
		      	</thead>
		      	<tbody id="tabForm">	</tbody>	      		
		    </table>
			 <div id="" style="margin:0 0 300px 200px;overfloat:hidden;">
			    <button id = "his_fir2" style="width:40px;height:25px;margin:20px 10px 0 0;float:left;">首页</button>
				<button id = "his_pre2" style="width:60px;height:25px;margin:20px 10px 0 0;float:left;">上一页</button>
			    <button id = "his_next2" style="width:60px;height:25px;margin:20px 10px 0 0;float:left;">下一页</button>
				<button id = "his_fin2" style="width:40px;height:25px;margin:20px 10px 0 0;float:left;">末页</button>
				<div id = "his_skip2" style="width:160px;float:left;margin:20px 0 0 10px;">跳转到：第
					<input type="text" name = "his_currentpage2" id="his_currentpage2" style="width: 42px; ">页
				</div>
				<button id="his_btn2" style="width:40px;height:25px;margin:20px 10px 0 0;float:left;">GO</button>
				<button id="his_refresh1" style="width:100px;height:26px;float:left;margin:20px 0 0 140px;">刷新当前页面</button>
			</div>
		 </div>	 
		 <!-- //    用户是否退出弹出窗         -->
		 <div id="pop" style="width:100%;display:none;height:100%;background:;position:relative;">
			 <div id="popWindow" class="popWindow" style="background:;width:100%;height:100%;"></div> 	
		 	 <div id = "quit" style="width:;height:;background:;">
				<p style="margin-top:10px;">确定是否退出！！！</p>
				<div style="margin:5px 0;">
					<button id="quit_Y">确定</button>
					<button id="quit_N">取消</button>
				</div>
		 	 </div>	
		 </div>
	 </div>	 

  <!-- ======================刷新页面  高度不变 ====================-->
	<script type="text/javascript">  
	   /*  $(function () {     
	  		window.scrollTo(0, $("#hdnvalue").val());
	  		console.log( "111111::::"+$("#hdnvalue").val());
	        $("#park_btn1").click(function () {         
	            $(".hdnvalue").val(document.documentElement.scrollTop);   
	        });    
	    });    */
	</script>  
  
  <!-- =====================登入是否成功, 车位是否添加及删除 提示框=======================-->
	  <script>
	 			console.log("loading start");
	 			var err3 = '<%=errMsg3 %>';
			    console.log("err3:"+err3);
			    if(err3!='null'){
			        alert(err3);		      
			    }    
			     
		    	 var err1 = '<%=errMsg1 %>';
			    console.log("err1:"+err1);
			    if(err1!='null'){
			        alert(err1); 
			        $("#cue_parkT").css("display","none");
					$("#cue_parkC").css("display","none");
					$("#cue_parkC1").css("display","none");
					document.getElementById("ins_parkId").value="";
					document.getElementById("ins_parkType").value="";    
			    }  
			    var err2 = '<%=errMsg2 %>';
			    console.log("err2:"+err2);
			    if(err2!='null'){
			        alert(err2);
			        $("#cue_parkT").css("display","none");
					$("#cue_parkC").css("display","none");
					$("#cue_parkC1").css("display","none");
					document.getElementById("del_parkId").value="";
			    } 
			  
			 
	</script> 
	 <!--   ====================================== 动画效果	================================ -->																	
	
	<script>
//  动画效果   		
       $(".li1").click(function(){
      		$("#opera_park").css("display","block").siblings("div").css("display","none")
      })
      $(".li2").click(function(){
      		$("#opera_car").css("display","block").siblings("div").css("display","none")
      })
      $(".li3").click(function(){
      		$("#hisForm").css("display","block").siblings("div").css("display","none")
      })
       $("ul").find("li").click(function(){
      		$(this).attr("class","li1").siblings("li").attr("class","li2");      	
       }) 
        $("ul").find("li").hover(function(){
        	if($(this).hasClass("li1")){
      			$(this).addClass("li1")
      		}else{
      			$(this).addClass("changeLi").siblings("li").removeClass("changeLi")
      		}
       },function(){
       		$(this).removeClass("changeLi")
       }) 
  		 
//是否退出页面
      $(".li4").click(function(){
      		$("#pop").css("display","block").siblings("div").css("display","none")
      		/* $("#quit").css("display","block");
      		$("#popWindow").css("display","block"); */
     	})
      $("#quit_Y").click(function(){
			window.location.href="index.jsp"
		})
	  $("#quit_N").click(function(){
	  		$("#pop").css("display","none")
	  		/* $("#popWindow").css("display","none");
			$("#quit").css("display","none") */
	  })
	 
    </script>
	 
	

	
	<!-- ==================车位管理页面---添加车位 提示====================-->
  <script type="text/javascript">
  /*   添加删除车位 提示 */
  	$("#ins_parkId").focus(function(){	
			$("#cue_parkC1").css("display","none"); 
			document.getElementById("del_parkId").value="";
	}) 
	$("#ins_parkType").focus(function(){
			$("#cue_parkC1").css("display","none"); 
			document.getElementById("del_parkId").value="";
	})   
  	function chkinPut(form){
  		if(form.ins_parkId.value == ""){
  			$("#cue_parkC").css({"display":"block","background":"yellow","color":"#333","text-align":"center"}).html("请输入车位编号！！！")
			form.ins_parkId.focus();	
  			return false;
  		}
  		var parkCreg = /^[A-B]{1}[0-9]{3}$/; 
		var obj = document.getElementById("ins_parkId").value;
  		if(!parkCreg.test(obj)) {	
			$("#cue_parkC").css({"display":"block","background":"#FFE384","color":"#333","text-align":"center"}).html("请输正确车位编号(例如 A001)！！");
			form.ins_parkId.focus();
			return false;  
		}else{
			$("#cue_parkC").css({"display":"block","background":"green","color":"#333","text-align":"center"}).html("输入成功！！！")
			form.ins_parkType.focus();
		}			
  		var obj1 = document.getElementById("ins_parkType").value;
  		if(form.ins_parkType.value == ""){
  			$("#cue_parkT").css({"display":"block","background":"yellow","color":"#333","text-align":"center"}).html("请输入车位类型！！！")
  			return false;
  		}else if(obj1!="A"&&obj1!="B") { 	    	
		    $("#cue_parkT").css({"display":"block","background":"#FFE384","color":"#333","text-align":"center"}).html("请输正确车位类型(A或B)！！！")	        	
		  	form.ins_parkType.focus();
		  	return false;  
		}else{
			$("#cue_parkT").css({"display":"block","background":"green","color":"#333","text-align":"center"}).html("输入成功！！！")	
			form.ins_parkType.blur();
		}
		
  	}
  	
  	 $("#del_parkId").focus(function(){
	    $("#cue_parkT").css("display","none");
		$("#cue_parkC").css("display","none");
		document.getElementById("ins_parkId").value="";
		document.getElementById("ins_parkType").value="";
	})	
  	function chkindelparkPut(form){
  		if(form.del_parkId.value == ""){
			$("#cue_parkC1").css({"display":"block","background":"yellow","color":"#333","text-align":"center"}).html("请输入车位编号！！！")
  			form.del_parkId.focus();
  			return false;
  		}
  		var parkCreg = /^[A-B]{1}[0-9]{3}$/; 
		var obj = document.getElementById("del_parkId").value;
  		if(!parkCreg.test(obj)) {	
			$("#cue_parkC1").css({"display":"block","background":"#FFE384","color":"#333","text-align":"center"}).html("请输正确车位编号(例如 A001)！！");
			form.del_parkId.focus();
			return false;  
		}else{
			$("#cue_parkC1").css({"display":"block","background":"green","color":"#333","text-align":"center"}).html("输入成功！！！")
			form.del_parkId.blur();
		} 
  	
  	}
  	
  /* 	汽车进出库提示 */
  
   $("#out_carId").focus(function(){
	     	$("#cue_carId").css("display","none");
			$("#cue_entTime").css("display","none");
			document.getElementById("ent_carId").value="";
			document.getElementById("entTime").value="";
	})
  	function outcarPut(){
  		if($("#out_carId").val() == ""){
			$("#out_carId").focus();
			$("#cue_carId1").css({"display":"block","background":"yellow","color":"#333","text-align":"center"}).html("请输入车牌号码！！！")
  			return false;
  		}
  		var carCreg = /^[a-b]{1}[0-9]{3}$/; 
		var obj1 = document.getElementById("out_carId").value;
  		if(!carCreg.test(obj1)) {	
			$("#cue_carId1").css({"display":"block","background":"#FFE384","color":"#333","text-align":"center"}).html("请输正确车牌号码(例如 a001)！！");
			$("#out_carId").focus();
			return false;  
		}else{
			$("#cue_carId1").css({"display":"block","background":"green","color":"#333","text-align":"center"}).html("输入成功！！！")
			$("#out_carId").blur();
		}  	
		
  		if($("#outTime").val()!=""){
	  		var carTCreg = /^(?:(?:(?:(?:(?:1[6-9]|[2-9][0-9])?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))([-/.])(?:0?2\1(?:29)))|(?:(?:(?:1[6-9]|[2-9][0-9])?[0-9]{2})([-/.])(?:(?:(?:0?[13578]|1[02])\2(?:31))|(?:(?:0?[13-9]|1[0-2])\2(?:29|30))|(?:(?:0?[1-9])|(?:1[0-2]))\2(?:0?[1-9]|1[0-9]|2[0-8])))) ([01]\d|2[01234]):([0-5]\d|60):([0-5]\d|60)$/; 
			var obj = document.getElementById("outTime").value;
	  		if(!carTCreg.test(obj)) {
				$("#cue_outTime").css({"display":"block","background":"#FFE384","color":"#333","text-align":"center"}).html("(例如2017-01-01 00:00:00)！！");
				$("#outTime").focus();
				return false;  
			}else{
				$("#cue_outTime").css({"display":"block","background":"green","color":"#333","text-align":"center"}).html("输入成功！！！");
			}
		}else{
			$("#cue_outTime").css({"display":"block","background":"green","color":"#333","text-align":"center"}).html("当前时间为系统时间！！！")
			$('#outTime').attr('placeholder','当前时间为系统时间');
		}
		var out_carId = document.getElementById("out_carId").value;
		var outTime  = document.getElementById("outTime").value; 
		var typeF2 = document.getElementById("typeF2").value;
		
		$.ajax({
			url:'F.F',
			type:'post',
			data:{"out_carId":out_carId ,"outTime":outTime,"typeF":typeF2},
			dataType:'json',
			success:function(data){
				console.log("data:"+data);	
				console.log("fee:"+data.fee);
				alert(data.msg)
				$("#fee").text(data.fee);
			},error:function(){
				alert("json格式返回错误");
			}
		})	
  	}
  	 
  	 $("#ent_carId").focus(function(){
		$("#cue_carId1").css("display","none");
		$("#cue_outTime").css("display","none");
		document.getElementById("out_carId").value="";
		document.getElementById("outTime").value="";
	})
  	 function entcarPut(){
  		if($("#ent_carId").val() == ""){
			$("#ent_carId").focus();
			$("#cue_carId").css({"display":"block","background":"yellow","color":"#333","text-align":"center"}).html("请输入车牌号码！！！")
  			return false;
  		}
  		var carCreg = /^[a-b]{1}[0-9]{3}$/; 
		var obj = document.getElementById("ent_carId").value;
  		if(!carCreg.test(obj)) {
			$("#cue_carId").css({"display":"block","background":"#FFE384","color":"#333","text-align":"center"}).html("请输正确车牌号码(例如 a001)！！");
			$("#ent_carId").focus();
			return false;  
		} else{
			$("#cue_carId").css({"display":"block","background":"green","color":"#333","text-align":"center"}).html("输入成功！！！")
			$("#ent_carId").blur();
		} 	
 
  		if($("#entTime").val()!=""){
	  		var cCreg = /^(?:(?:(?:(?:(?:1[6-9]|[2-9][0-9])?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))([-/.])(?:0?2\1(?:29)))|(?:(?:(?:1[6-9]|[2-9][0-9])?[0-9]{2})([-/.])(?:(?:(?:0?[13578]|1[02])\2(?:31))|(?:(?:0?[13-9]|1[0-2])\2(?:29|30))|(?:(?:0?[1-9])|(?:1[0-2]))\2(?:0?[1-9]|1[0-9]|2[0-8])))) ([01]\d|2[01234]):([0-5]\d|60):([0-5]\d|60)$/; 
			var ob = document.getElementById("entTime").value;
	  		if(!cCreg.test(ob)) {
				$("#cue_entTime").css({"display":"block","background":"#FFE384","color":"#333","text-align":"center"}).html("(例如2017-01-01 00:00:00)！！");
				return false;  
			}else{
				$("#cue_entTime").css({"display":"block","background":"green","color":"#333","text-align":"center"}).html("输入成功！！！")
			} 
		}else{
			$("#cue_entTime").css({"display":"block","background":"green","color":"#333","text-align":"center"}).html("当前时间为系统时间！！！")
			$('#entTime').attr('placeholder','当前时间为系统时间');
		}
		var ent_carId = document.getElementById("ent_carId").value;
		var entTime  = document.getElementById("entTime").value; 
		var typeF1 = document.getElementById("typeF1").value;
		$.ajax({
			url:'F.F',
			type:'post',
			data:{"ent_carId":ent_carId ,"entTime":entTime,"typeF":typeF1},
			dataType:'json',
			success:function(data){
				alert(data.msg);
				$("#fee").text("0");
			},error:function(){
				alert("json格式返回错误");
			}
		})   	
  	}  
  	/* 汽车进库操作 */
  	$("#carEnter").on("click",function(){
  		return entcarPut();
  		
  	})
  	/* 汽车出库操作 */
  	$("#carOut").on("click",function(){
  		return outcarPut();  
   	 })
  
 
/* 	判断按钮是否被隐藏 */
  	function btnIshide(pageNo,allPage,ap,np){
		$(".nowparkNum").text(ap);
		$(".nownoparkNum").text(np); 
		document.getElementById("park_currentpage").value=pageNo;
  		 if(pageNo==1){
			if(allPage == 1){
				$("#park_fir1").addClass("btnHid");
				$("#park_pre1").addClass("btnHid");
				$("#park_next1").addClass("btnHid");
				$("#park_fin1").addClass("btnHid");
				$("#park_skip1").addClass("btnHid");
				$("#park_btn1").addClass("btnHid");	
			}else{
				$("#park_fir1").addClass("btnHid");
				$("#park_pre1").addClass("btnHid");
				$("#park_next1").removeClass("btnHid");
				$("#park_fin1").removeClass("btnHid");
				$("#park_skip1").removeClass("btnHid");
				$("#park_btn1").removeClass("btnHid");	
			}
		}else if(pageNo == allPage){
			$("#park_fir1").removeClass("btnHid");
			$("#park_pre1").removeClass("btnHid");
			$("#park_next1").addClass("btnHid");
			$("#park_fin1").addClass("btnHid");
			$("#park_skip1").removeClass("btnHid");
			$("#park_btn1").removeClass("btnHid");
		}else{
			$("#park_fir1").removeClass("btnHid");
			$("#park_pre1").removeClass("btnHid");
			$("#park_next1").removeClass("btnHid");
			$("#park_fin1").removeClass("btnHid");
			$("#park_skip1").removeClass("btnHid");
			$("#park_btn1").removeClass("btnHid");
		}
  	} 

  	function hisbtnIshide(pageNo,allPage){
  			 if(pageNo==1){
						if(allPage == 1){
							$("#his_fir2").addClass("btnHid");
							$("#his_pre2").addClass("btnHid");
							$("#his_next2").addClass("btnHid");
							$("#his_fin2").addClass("btnHid");
							$("#his_skip2").addClass("btnHid");
							$("#his_btn2").addClass("btnHid");	
						}else{
							$("#his_fir2").addClass("btnHid");
							$("#his_pre2").addClass("btnHid");
							$("#his_next2").removeClass("btnHid");
							$("#his_fin2").removeClass("btnHid");
							$("#his_skip2").removeClass("btnHid");
							$("#his_btn2").removeClass("btnHid");
						}
			}else if(pageNo == allPage){
						$("#his_fir2").removeClass("btnHid");
						$("#his_pre2").removeClass("btnHid");
						$("#his_next2").addClass("btnHid");
						$("#his_fin2").addClass("btnHid");
						$("#his_skip2").removeClass("btnHid");
						$("#his_btn2").removeClass("btnHid");
			}else{
						//			   alert("NO!=12")
						$("#his_fir2").removeClass("btnHid");
						$("#his_pre2").removeClass("btnHid");
						$("#his_next2").removeClass("btnHid");
						$("#his_fin2").removeClass("btnHid");
						$("#his_skip2").removeClass("btnHid");
						$("#his_btn2").removeClass("btnHid");
		   }
  	}
  	
    /* 输入行数  页码是否为正整数 */
  	function PSnumChange(){
  		var numCreg = /^[1-9]*[1-9][0-9]*$/; 
		if(!numCreg.test($("#park_pageSize").val())){
				alert("请输入正整数！！！");
		}else{
			$("#tab").find('tr').remove();
			document.getElementById("park_currentpage").value = 1;
			park_ajax_null();
		}
  	}
  	
  	function PCnumChange(){
  		var numCreg = /^[1-9]*[1-9][0-9]*$/; 
		var obj = document.getElementById("park_currentpage1").value;
		if(!numCreg.test(obj)){
			alert("请输入正整数！！！");
			$("#park_currentpage1").focus();
		}else{	
			var park_currentpage = document.getElementById("park_currentpage1").value;
			park_ajax_isFas(park_currentpage,false);
		}
  	}
  	
  function hisPSnumChange(){
  		var numCreg = /^[1-9]*[1-9][0-9]*$/; 
		var obj1 = document.getElementById("pageSize").value;
		if(!numCreg.test(obj1)){
				alert("请输入正整数！！！");
		//		$("#park_pageSize").focus();
		}else{
			$("#tabForm").find('tr').remove();
			document.getElementById("currentpage").value = 1;
			his_ajax_null();
		}
  	}
  	
  	function hisPCnumChange(){
  		var numCreg = /^[1-9]*[1-9][0-9]*$/; 
		var obj = document.getElementById("his_currentpage2").value;
		if(!numCreg.test(obj)){
			alert("请输入正整数！！！");
			$("#his_currentpage2").focus();
		}else{
			var currentpage = document.getElementById("his_currentpage2").value;
			his_ajax_isFas(currentpage,false);
		}
  	}
  	
  </script>	  


<!-- 	=========查询车位条件改变，点击查询======== -->
   <script>
   		
 		function  park_ajax_null(){
   	  		 var park_currentpage = document.getElementById("park_currentpage").value;
			 var park_pageSize = document.getElementById("park_pageSize").value;	
		  	 $.ajax({	
		  		url:'E.E',
		  		type:'post',
		  		data:{"park_currentpage":park_currentpage ,"park_pageSize":park_pageSize,"lastPage":false },
		  		dataType:'json',
		  		success:function(data){	
		  		    var da = data.da;
		  			for (i=0;i<da.length;i++) {  	              		                
					       $("#tab").append("<tr><td class='td_PC'>"+da[i].PARKING_CODE+"</td>"+	
	  				        "<td class='td_PT'>"+da[i].PARKING_TYPE+"</td>"+
	  				        "<td class='td_CN'>"+da[i].CAR_NUM+"</td>"+
	  				        "<td class='td_IT'>"+da[i].IN_TIME+"</td></tr>")    				         				        	  				        
				   } 
				   var ap = data.ap;
				   var np = data.np;
				   var pageNo =parseInt(data.pageCurrentpage);
				   var allPage = parseInt(data.allPage);
				   btnIshide(pageNo,allPage,ap,np);
		  		}
		
		  	 }); 
	   
   	    }
   	    function  park_ajax_isFas(park_currentpage,lastPage){
   	    	 $("#tab").find('td').html("");
	  			var park_pageSize = document.getElementById("park_pageSize").value;
	   	    	 $.ajax({	
				  		url:'E.E',
				  		type:'post',
				  		data:{"park_currentpage":park_currentpage ,"park_pageSize":park_pageSize,"lastPage":lastPage },
				  		dataType:'json',
				  		success:function(data){	  					
				  			var da = data.da;
				  			for (i in da) {  	              		                
							    $("#tab").find('tr').eq(i).find(".td_PC").text(da[i].PARKING_CODE);
				                $("#tab").find('tr').eq(i).find(".td_PT").text(da[i].PARKING_TYPE);
				                $("#tab").find('tr').eq(i).find(".td_CN").text(da[i].CAR_NUM);
				                $("#tab").find('tr').eq(i).find(".td_IT").text(da[i].IN_TIME);	    
						   }   
						   var ap = data.ap;
						   var np = data.np; 
						   var pageNo =parseInt(data.pageCurrentpage);
						   var allPage = parseInt(data.allPage);					
	 					   btnIshide(pageNo,allPage,ap,np);
				  		}
				 }); 
		
   	    }  	    
   	    
   	    function  his_ajax_null(){
   	  		 var currentpage = document.getElementById("currentpage").value;
			 var pageSize = document.getElementById("pageSize").value;	
		  	 $.ajax({	
		  		url:'G.G',
		  		type:'post',
		  		data:{"currentpage":currentpage ,"pageSize":pageSize,"lastPage":false },
		  		dataType:'json',
		  		success:function(data){	
		//			console.log(data);
		  			var da = data.da;
		  			for (i=0;i<da.length;i++) {  	              		                
					    $("#tabForm").append("<tr><td class='td_PC'>"+da[i].PARKING_CODE+"</td>"+	
	  				        "<td class='td_PT'>"+da[i].PARKING_TYPE+"</td>"+
	  				        "<td class='td_CN'>"+da[i].CAR_NUM+"</td>"+
	  				        "<td class='td_IT'>"+da[i].IN_TIME+"</td>"+
	  				        "<td class='td_OT'>"+da[i].OUT_TIME+"</td>"+
	  				        "<td class='td_CH'>"+da[i].CHARGE+"</td></tr>")   	  				        	  				        
				    } 
				    var pageNo = data.pageCurrentpage;
				    var allPage = data.allPage;
				    document.getElementById("currentpage").value=pageNo;
				    hisbtnIshide(pageNo,allPage)	
		  		}
		
		  	 }); 
	   
   	    }
   	    function  his_ajax_isFas(currentpage,lastpage){
   	  //  	 var currentpage = document.getElementById("currentpage").value;
   	  		 $("#tabForm").find('td').html("");
			 var pageSize = document.getElementById("pageSize").value;
   	    	 $.ajax({	
			  		url:'G.G',
			  		type:'post',
			  		data:{"currentpage":currentpage ,"pageSize":pageSize,"lastPage":lastpage },
			  		dataType:'json',
			  		success:function(data){	  					
			  			var da = data.da;
			  			for (i in da) {  	              		                
						    $("#tabForm").find('tr').eq(i).find(".td_PC").text(da[i].PARKING_CODE);
			                $("#tabForm").find('tr').eq(i).find(".td_PT").text(da[i].PARKING_TYPE);
			                $("#tabForm").find('tr').eq(i).find(".td_CN").text(da[i].CAR_NUM);
			                $("#tabForm").find('tr').eq(i).find(".td_IT").text(da[i].IN_TIME);	
			                $("#tabForm").find('tr').eq(i).find(".td_OT").text(da[i].OUT_TIME);	
			                $("#tabForm").find('tr').eq(i).find(".td_CH").text(da[i].CHARGE);	    
					   }
					   var pageNo = data.pageCurrentpage;
					    var allPage = data.allPage;
					    document.getElementById("currentpage").value=pageNo;
						hisbtnIshide(pageNo,allPage);
						   
					       
			  		}
			 });
		
   	    }
   	    
 /*   	 改变行数时   */ 
		$(function(){
		/* 正整数 */	
		     $("#park_pageSize").on("change",function(){
				 PSnumChange();
			 })	
			 $("#pageSize").on("change",function(){
				 hisPSnumChange();
			 }) 	
		})
/*		 记录滚动条高度 */	
   		$("#park_pageSize").blur(function(){
   				$(".hdnvalue").val(document.documentElement.scrollTop); 
   		})	
   		
/* =============   提交form表单不刷新页面   ================= */
   	   $("#insertPark").attr("target","rfFrame");
  	   $("#delPark").attr("target","rfFrame"); 
  	   
   </script>   
  
	<!-- 	======================   点击上下按钮分页          ======== -->  
   <script type="text/javascript">
   
   	 park_ajax_null();  	
   	 his_ajax_null();	  	 
     $(document).ready(function(){	
     			   		
  		 $("#park_fir1").on("click",function(){ 	    	
	  	    	document.getElementById("park_currentpage1").value="";
	  			var park_currentpage = 1;
	  			park_ajax_isFas(park_currentpage,false);		 
  	     })
  	    
  	     $("#park_pre1").on("click",function(){ 
  	     		document.getElementById("park_currentpage1").value="";				
	  			 var park_currentpage = parseInt($("#park_currentpage").val()-1); 
	  			 park_ajax_isFas(park_currentpage,false);
  	     })	
  	    
  	     $("#park_next1").on("click",function(){
  	 			document.getElementById("park_currentpage1").value="";
	  			var park_currentpage = parseInt($("#park_currentpage").val())+1;
	  			park_ajax_isFas(park_currentpage,false);
		})
		
	  	$("#park_fin1").on("click",function(){ 	
	  		document.getElementById("park_currentpage1").value="";  		
	  		var park_currentpage = parseInt($("#park_currentpage").val())+1;		
			park_ajax_isFas(park_currentpage,true);
		})
		
		 $("#park_btn1").on("click",function(){
		  	PCnumChange();	
  		 });
		
		 $("#park_refresh").on("click",function(){ 
   	    	var park_currentpage = document.getElementById("park_currentpage").value;
			park_ajax_isFas(park_currentpage,false);
     	 }) 
     	 
     	 $("#his_fir2").on("click",function(){ 	    					
	  	    	document.getElementById("his_currentpage2").value="";
	  			var currentpage = 1;
	  			his_ajax_isFas(currentpage,false);	  		  	
  	     })
  	     
  	     $("#his_pre2").on("click",function(){				
	  			var currentpage = document.getElementById("currentpage").value-1;
	  			document.getElementById("his_currentpage2").value="";
	  			his_ajax_isFas(currentpage,false)		
  	    	})	
  	    
  	    	$("#his_next2").on("click",function(){							
	  			document.getElementById("his_currentpage2").value="";
	 			var currentpage = parseInt( document.getElementById("currentpage").value)+1;
	  			his_ajax_isFas(currentpage,false)
		   	})
	  	$("#his_fin2").on("click",function(){ 	
		  	var currentpage = parseInt( document.getElementById("currentpage").value)+1;
		  	his_ajax_isFas(currentpage,true)
		})
		
		 $("#his_btn2").on("click",function(){
		  	hisPCnumChange();		 	
  		 });
		
		 $("#his_refresh1").on("click",function(){ 
		// 	$("#tabForm").find('tr').remove();
			var currentpage = document.getElementById("currentpage").value;
			 his_ajax_isFas(currentpage,false); 
			/* his_ajax_null(); */
     	 })   
		
  })	    
   console.log("loading end");   
   </script>
    

<!-- ==============================查询历史信息表====================================== -->
	
	
  </body>
</html>