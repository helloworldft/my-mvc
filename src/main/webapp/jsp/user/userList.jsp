<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!-- Bootstrap -->
<link href="<%=basePath%>plugins/Bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="<%=basePath%>plugins/DataTables-1.10.10/css/jquery.dataTables.min.css" rel="stylesheet" media="screen">
<script src="<%=basePath%>plugins/jQuery-1.11.3/jquery-1.11.3.js"></script>
<script src="<%=basePath%>plugins/Bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script src="<%=basePath%>plugins/DataTables-1.10.10/js/jquery.dataTables.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function() {
	$('#userTable').dataTable( {
		dom: '<"top"l>rt<"bottom"fip><"clear">',
        ajax: {
        	url:"getJsonUserList" ,
            type: "POST"
			},
		serverSide: true,
		columns: [
	            { data: "userName" },
	            { data: "phone" },
	            { data: "email" },
	            { data: "sex" },
	            { data: "createTime" }
	    ],
	    
	    select: true,
	    bPaginate: true, //翻页功能
	    bLengthChange: true, //改变每页显示数据数量
	    bFilter: false, //过滤功能
	    bSort: true, //排序功能
	    bInfo: true,//页脚信息
	    bAutoWidth: true//自动宽度
    } );
});
</script>
</head>
<body>
	<div class="container">
		<a href="<@s.url'/user/exit'/>" class="btn btn-link pull-right"
			role="button">Exit</a>
		<h1 class="page-header">User Management</h1>
		<div class="row">
			<div class="col-xs-3">
				<button type="button" class="btn btn-success" data-toggle="modal"
					data-target="#addWin">Add</button>
				<a href="javascript:void(0)" onclick="updateUser();"
					class="btn btn-primary" role="button">Update</a> <a
					href="javascript:void(0)" onclick="deleteUser();"
					class="btn btn-danger" role="button">Delete</a>
			</div>
			<div class="input-group col-xs-3 pull-right">
				<input type="text" id="search" placeholder="Search"
					class="form-control"> <span class="input-group-addon"><span
						class="glyphicon glyphicon-search"
						style="color: rgb(255, 140, 60);"></span></span>
			</div>
		</div>
		<div>&nbsp;</div>
		<table id="userTable" class="table table-bordered table-hover">
			<thead>
            <tr>
                <th>User Name</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Sex</th>
                <th>Create Time</th>
            </tr>
        </thead>
		</table>


		<hr />

		<div class="modal fade" id="addWin" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<form class="form-horizontal" action="<%=basePath%>/user/addUser"
					role="form">
					<div class="modal-content">
					
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Add User</h4>
						</div>
						
						<div class="modal-body">
							<div class="form-group">
								<label for="userName" class="col-xs-2 control-label">UserName</label>
								<div class="col-xs-10">
									<input type="text" class="form-control" id="userName" name="username">
								</div>
							</div>
							
							<div class="form-group">
								<label for="password" class="col-xs-2 control-label">Password</label>
								<div class="col-xs-10">
									<input type="password" class="form-control" id="password" name="password">
								</div>
							</div>
							<div class="form-group">
								<label for="phone" class="col-xs-2 control-label">phone</label>
								<div class="col-xs-10">
									<input type="tel" class="form-control" id="phone"
										name="phone">
								</div>
							</div>
							<div class="form-group">
								<label for="email" class="col-xs-2 control-label">Email</label>
								<div class="col-xs-10">
									<input type="email" class="form-control" id="email"
										name="email">
								</div>
							</div>
							
							<div class="form-group">
								<label for="sex" class="col-xs-2 control-label">Sex</label>
								<div class="col-xs-10">
									<select class="form-control" name="sex">
										<option value="1">M</option>
										<option value="0">W</option>
										<option value="2">M/W</option>
									</select>
								</div>
							</div>
							
						</div>
						
						<div class="modal-footer">
							<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Save</button>
						</div>
						
					</div>
				</div>
			</form>
		</div>

		<div class="modal fade" id="updateWin" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<form class="form-horizontal" action="<@s.url'/user/updateUser'/>" role="form" method="post">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Update User</h4>
						</div>
						<div class="modal-body">

							<div class="form-group">
								<label for="u_id" class="col-xs-2 control-label">ID</label>
								<div class="col-xs-10">
									<input type="text" class="form-control" id="u_id" name="id">
								</div>
							</div>

							<div class="form-group">
								<label for="u_username" class="col-xs-2 control-label">UserName</label>
								<div class="col-xs-10">
									<input type="text" class="form-control" id="u_username" name="username">
								</div>
							</div>

							<div class="form-group">
								<label for="u_password" class="col-xs-2 control-label">Password</label>
								<div class="col-xs-10">
									<input type="text" class="form-control" id="u_password" name="password">
								</div>
							</div>

							<div class="form-group">
								<label for="u_email" class="col-xs-2 control-label">Email</label>
								<div class="col-xs-10">
									<input type="email" class="form-control" id="u_email" name="email">
								</div>
							</div>
							<div class="form-group">
								<label for="u_sex" class="col-xs-2 control-label">Sex</label>
								<div class="col-xs-10">
									<select class="form-control" id="u_sex" name="sex">
										<option value="1">M</option>
										<option value="0">W</option>
										<option value="2">M/W</option>
									</select>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Update</button>
						</div>
					</div>
			</div>
			</form>
		</div>
		<!-- 分页显示的div块开始 -->
		<div style="float: right;">
			<div id="pageBar" style="float:right"></div>
		</div>
		<!-- 分页显示的div块结束 -->
	</div>
</body>

</html>