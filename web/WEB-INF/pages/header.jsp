<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="fu.holafood.controller.FuncUser"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="user-scalable=yes, width=device-width, initial-scale=1.0, maximum-scale=1, minimum-scale=1">
        <title>${param.title}</title>
        <!--<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">-->
        <!--Bootstrap CSS-->
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" />
        <!--Main site CSS-->
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/assets/css/style.css" />
    </head>
    <body>

        <%
            FuncUser fu = new FuncUser();
            request.setCharacterEncoding("utf-8");
            Cookie c = fu.getCookie(request, response);
            String userName;
            String register;

            String profileLink;
            String logOutLink;

            if (c != null) {
                userName = "Xin Chào " + c.getValue();
                profileLink = "#"; //link to profile page

                register = "Đăng Xuất";
                logOutLink = "Logout";

            } else {
                userName = "Đăng Nhập";
                profileLink = "login.jsp";

                register = "Đăng Ký";
                logOutLink = "register.jsp"; //link to register
            }
        %>

        <!--Header code in here-->
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">HolaFood</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="<%=profileLink%>"><%=userName%></a></li>
                        <li><a href="<%=logOutLink%>"><%=register%></a></li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
