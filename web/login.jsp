<%-- 
    Document   : login2
    Created on : Jun 28, 2017, 8:22:20 PM
    Author     : NhocNho
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="user-scalable=yes, width=device-width, initial-scale=1.0, maximum-scale=1, minimum-scale=1">
        <title>Login page</title>
        <!--<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">-->
        <!--Bootstrap CSS-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" />
        <!--Main site CSS-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/style.css" />
    </head>
    <body>
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
                </div>
            </div><!-- /.container -->
        </nav>

        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="panel panel-login">
                        <div class="panel-heading">
                            <div class="row">
                                <a href="#" class="active" id="login-form-link">Login</a>
                            </div>
                            <hr>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form id="login-form" action="Login" method="post" role="form" style="display: block;">
                                        <div class="form-group">
                                            <label for="username">Username</label>
                                            <input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Example: John" value="">
                                        </div>
                                        <div class="form-group">
                                            <label for="password">Password</label>
                                            <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Example: John123">
                                        </div>


                                        <div class="form-group text-center">
                                            <input type="checkbox" tabindex="3" class="" name="remember" id="remember" value="checked">
                                            <label for="remember"> Remember Me</label>
                                        </div>
                                        <div class="form-group text-center">
                                            <%
                                                if (null != request.getAttribute("errorMessage")) {
                                                    out.println("<p class=\"bg-danger\">" + request.getAttribute("errorMessage") + "</p>");
                                                }
                                            %>
                                        </div>
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-3">
                                                    <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <div class="text-center">
                                                        <a href="#" tabindex="5" class="forgot-password">Forgot Password?</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="/WEB-INF/pages/footer.jsp"/>
    </body>
</html>


