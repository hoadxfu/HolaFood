<%-- 
    Document   : index
    Created on : Jun 14, 2017, 2:17:06 PM
    Author     : hoadx
--%>

<%@page import="fu.holafood.model.UserModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h1>We are team 3</h1>
        <h1>test commit code :/</h1>
        <% UserModel um = new UserModel(); %>
        <%= um.getUsers().get(0).getFullname() %><br/>
    </body>
</html>
