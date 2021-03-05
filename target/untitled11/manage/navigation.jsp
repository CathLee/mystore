<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="background-color: rgb(43, 43, 60)">
	<div>
		<img src="<%=basePath%>/img/logo.jpg" style="margin-left: 20px" />
	</div>
	<div style="margin-top: 50px">
		<hr>
		<a href="<%=basePath%>manage/managerInformation.jsp"
			style="text-decoration: none;" target="main"><p
				style="font-size: 20px; color: white; text-align: center">
				<img src="<%=basePath%>/img/icon-1.png" style="width: 20px" />
				修改个人信息
			</p></a>
		<hr>
		<a href="<%=basePath%>manage/managerPassword.jsp"
			style="text-decoration: none;" target="main"><p
				style="font-size: 20px; color: white; text-align: center">
				<img src="<%=basePath%>/img/icon-2.png" style="width: 20px" />
				修改登录密码
			</p></a>
		<hr>
		<a href="<%=basePath%>jsp/booksList.jsp"
			style="text-decoration: none;" target="main"><p
				style="font-size: 20px; color: white; text-align: center">
				<img src="<%=basePath%>/img/icon-9.png" style="width: 20px" />
				书籍信息维护
			</p></a>
		<hr>
		<a href="<%=basePath%>manage/categoryList"
			style="text-decoration: none;" target="main"><p
				style="font-size: 20px; color: white; text-align: center">
				<img src="<%=basePath%>/img/icon-8.png" style="width: 20px" />
				书籍分类维护
			</p></a>
		<hr>
		<a href="<%=basePath%>/manage/addCategory.jsp"
			style="text-decoration: none;" target="main"><p
				style="font-size: 20px; color: white; text-align: center">
				<img src="<%=basePath%>/img/icon-3.png" style="width: 20px" />
				添加书籍分类
			</p></a>
		<hr>
		<a
			href="<%=basePath%>manage/addBookUI"
			style="text-decoration: none;" target="main"><p
				style="font-size: 20px; color: white; text-align: center">
				<img src="<%=basePath%>/img/icon-4.png" style="width: 20px" />
				添加图书信息
			</p></a>
		<hr>
		<a
			href="<%=basePath%>order/findOrders"
			style="text-decoration: none;" target="main"><p
				style="font-size: 20px; color: white; text-align: center">
				<img src="<%=basePath%>/img/icon-5.png" style="width: 20px" />
				销售订单管理
			</p></a>
		<hr>
		<a
			href="<%=basePath%>manage/findUsers"
			style="text-decoration: none;" target="main"><p
				style="font-size: 20px; color: white; text-align: center">
				<img src="<%=basePath%>/img/icon-6.png" style="width: 20px" />
				用户信息管理
			</p></a>
		<hr>
		<a href="<%=basePath%>/manage/addAdmin.jsp"
			style="text-decoration: none;" target="main"><p
				style="font-size: 20px; color: white; text-align: center">
				<img src="<%=basePath%>/img/icon-15.png" style="width: 20px" />
				添加工作人员
			</p></a>
		<hr>
		<a
			href="<%=basePath%>manage/sales"
			style="text-decoration: none;" target="main"><p
				style="font-size: 20px; color: white; text-align: center">
				<img src="<%=basePath%>/img/icon-16.png" style="width: 20px" />
				书籍销售情况
			</p></a>
		<hr>
	</div>
</body>
</html>