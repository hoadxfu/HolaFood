<%@page import="fu.holafood.model.UserModel"%>
<%@page import="fu.holafood.controller.UserController"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="/struts-tags" prefix="s"%>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="user-scalable=yes, width=device-width, initial-scale=1.0, maximum-scale=1, minimum-scale=1">
        <title>${param.title}</title>
        <!--<link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">-->
        <!--Bootstrap CSS-->
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" />
        <!--FlexSlider-->
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/assets/css/flexslider.css" />
        <!--Main site CSS-->
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/assets/css/style.css" />
    </head>
    <body>

        <%
            UserController fu = new UserController();
            request.setCharacterEncoding("utf-8");
            Cookie c = fu.getCookie(request, response);
            UserModel um = new UserModel();
            String permision = null;
            try {
                permision = um.getPermision(c);
            } catch (Exception e) {
                System.out.println(e);
            }

            String userName;
            String register;

            String profileLink;
            String logOutLink;

            if (c != null) {
                userName = "Xin Chào " + c.getValue();
                profileLink = "#"; //link to profile page
                if (permision.equalsIgnoreCase("user")) {
                    register = "Đăng Xuất";
                    logOutLink = "Logout";
                } else if (permision.equalsIgnoreCase("admin") || permision.equalsIgnoreCase("moderator")) {
                    register = "Admin";
                    logOutLink = "admin/index.jsp";
                } else {
                    register = "Đăng Xuất";
                    logOutLink = "Logout";
                }

            } else {
                userName = "Đăng Nhập";
                profileLink = "login.jsp";

                register = "Đăng Ký";
                logOutLink = "register.jsp"; //link to register
            }
        %>

        <!--Header code in here-->
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="col-md-10 col-md-offset-1">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="index.jsp">HolaFood</a>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="<%= profileLink%>"><%= userName%></a></li>
                            <li><a href="<%= logOutLink%>"><%= register%></a></li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </div>
            </div><!-- /.container -->
        </nav>
