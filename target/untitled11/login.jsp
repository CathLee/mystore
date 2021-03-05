<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<link rel="stylesheet" href="<%=basePath%>/css/header.css" />
<head>
    <div class="modal fade in" id="myModal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" style="display: block;padding-left: 17px;">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">登录</h4>
                </div>
                <form action="user/login" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="exampleInputName2">Username </label> <input
                                type="text" name="username" class="form-control"
                                id="exampleInputName2" placeholder="Username">
                        </div>
                        <br>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Password</label> <input
                                type="password" name="password" class="form-control"
                                id="exampleInputPassword1" placeholder="Password">
                        </div>
                        <br>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-primary"
                        >登录</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</head>
</body>
</html>