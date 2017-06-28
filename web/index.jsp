<%@page import="fu.holafood.function.FuncUser"%>

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

<jsp:include page="/WEB-INF/pages/header.jsp">
     <jsp:param name="title" value="HolaFood Homepage"/>
    <jsp:param name="username" value="<%=userName%>"/>
    <jsp:param name="profile" value="<%=profileLink%>"/>
    
    <jsp:param name="register" value="<%=register%>"/>
    <jsp:param name="logout" value="<%=logOutLink%>"/>
    
</jsp:include>

<!--Content of Website in here-->
<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">Maintain</div>
        <div class="panel-body">
            Sorry. This site is under maintain.
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/pages/footer.jsp"/>