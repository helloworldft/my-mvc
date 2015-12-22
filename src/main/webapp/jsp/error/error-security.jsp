<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head><title>Exception!</title>
	<!-- Bootstrap -->
    <link href="<%=basePath%>plugins/Bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet" media="screen">

</head>
<body>
<div class="container">
<% Exception e = (Exception)request.getAttribute("ex"); %>
<h1 class="page-header">业务错误: <%= e.getClass().getSimpleName()%></h1>

<P>错误描述：</P>
<%= e.getMessage()%>
<P>错误信息：</P>
<% e.printStackTrace(new java.io.PrintWriter(out)); %>
<script src="<%=basePath%>plugins/jQuery-1.11.3/jquery-1.11.3.min.js"></script>
    <script src="<%=basePath%>plugins/Bootstrap-3.3.5/js/bootstrap.min.js"></script>
</div>
</body>
</html>