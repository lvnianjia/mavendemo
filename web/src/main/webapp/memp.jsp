<%@page import="com.lnj.service.MempService"%>
<%@page import="com.lnj.entry.Memp"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.Enumeration" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="bootstrap-3.3.7-dist\js\jquery-1.12.4.js"></script>
<link href="bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
<script src="bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<style>
.main {
	width: 100%;
	height: 100%;
	position: absolute;
	top: 0px;
	left: 0px;
	background-size: cover;
	background-image: url(img/timg.jpg);
}

</style>
</head>
<body>
	<div class="main">
		<div class="col-md-6 col-md-offset-2" id="mdiv">
			<div>
				<h1>员工信息</h1>
				<table class="table table-hover">
					<tr>
						<th>姓名</th>
						<th>工号</th>
						<th>工作</th>
						<th>上司</th>
						<th>工资</th>
						<th>入职日期</th>
						<th>部门</th>
					</tr>
					<%
					List<Memp> list=new MempService().findAll();
						for(Memp memp:list){
					%>
					<tr id="mtr">
						<td><%=memp.getEname()%></td>
						<td><%=memp.getEmpno()%></td>
						<td><%=memp.getJob()%></td>
						<td><%=memp.getMgr()%></td>
						<td><%=memp.getSal()%></td>
						<td><%=memp.getHiredate()%></td>
						<td><%=memp.getDeptno()%></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
		</div>
	</div>
</body>
</html> 