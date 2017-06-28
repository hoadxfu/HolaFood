<%-- 
    Document   : register
    Created on : Jun 26, 2017, 6:36:35 PM
    Author     : Ray Sparrow
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/pages/header.jsp">
    <jsp:param name="title" value="HolaFood Homepage"/>
</jsp:include>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" />
        <!--Main site CSS-->
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/assets/css/style.css" />
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/assets/js/jquery.min.js" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="panel panel-primary">
                <div class="panel-heading">Register</div>
                <br/>
                <br/>
                <form class="form-horizontal" action="AddUser" method="GET">

                    <div class="form-group">
                        <label for="userName" class="col-sm-5 control-label">User name(*)</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="userName" name="userName" placeholder="User Name"/>
                            <%
                                String errorUser = (String) request.getAttribute("errorUser");
                                if (errorUser != null) {
                            %>
                            <p><%=errorUser%></p>
                            <%
                                }
                            %>

                        </div>

                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-5 control-label">Password(*)</label>
                        <div class="col-sm-3">
                            <input type="password" class="form-control" id="inputPassword3" name="password" >
                             <%
                                String errorPass = (String) request.getAttribute("errorPass");
                                if (errorUser != null) {
                            %>
                            <p><%= errorPass %></p>
                            <%
                                }
                            %>
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="fullName" class="col-sm-5 control-label">Full name</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Full Name">
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="dob" class="col-sm-5 control-label">Date Of Birth</label>
                        <div class="col-sm-3">
                            <input type="date" class="form-control" id="dob" name="dob" >
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-5 control-label">Email(*)</label>
                        <div class="col-sm-3">
                            <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
                             <%
                                String errorMail = (String) request.getAttribute("errorMail");
                                if (errorMail != null) {
                            %>
                            <p><%=errorMail%></p>
                            <%
                                }
                            %>
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="gender" class="col-sm-5 control-label">Gender</label>
                        <div class="col-sm-3">
                            <select  class="form-control" name="gender" id="gender">
                                <option>Male</option>
                                <option>Female</option>
                            </select>

                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-7 col-sm-4">
                            <button type="submit" class="btn btn-default">Send</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
