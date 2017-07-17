<%-- 
    Document   : create
    Created on : Jul 14, 2017, 1:17:05 PM
    Author     : Ray Sparrow
--%>

<%@page import="fu.holafood.entity.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fu.holafood.model.UserModel"%>
<%@page import="fu.holafood.controller.UserController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UserController fu = new UserController();
    Cookie c = fu.getCookie(request, response);
    UserModel um = new UserModel();
    ArrayList<Category> categories = null;
    String permission = null;
    try {
        permission = um.getPermision(c);
        categories = um.getCategory();
    } catch (Exception e) {
        System.out.println(e);
    }
    if (c == null || !permission.equalsIgnoreCase("admin")) {
        response.sendRedirect("../../index.jsp");
    }

%>
<jsp:include page="/WEB-INF/pages/admin/header.jsp">
    <jsp:param name="title" value="Create New Category"/>
    <jsp:param name="name" value="category_create"/>
    <jsp:param name="permission" value="<%=permission%>"/>
</jsp:include>
<%@taglib uri="/struts-tags" prefix="s"%>  
<section class="content">
    <div class="box">
        <div class="box-header with-border">
            <h3 class="box-title">Create Category</h3>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
            <form action="${pageContext.request.contextPath}/CreateCategory" method="POST">
                <div class="form-group">
                    <label>Category Name: </label>
                    <input type="text" class="form-control" placeholder="" name="categoryName">
                </div>
                <div class="form-group">
                    <label>Slug: </label>
                    <input type="text" class="form-control" placeholder="" name="slug">
                </div>
                <div class="form-group">
                    <label>Category Parent: </label>
                    <select  name="pid">
                        <option value="0">None</option>
                        <%
                            if (categories != null) {
                                for (int i = 0; i < categories.size(); i++) {
                        %>
                        <option value="<%=categories.get(i).getId() %>"><%=categories.get(i).getName() %></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <textarea name="description" class="textarea" placeholder="Place some text here" style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
                </div>

                <div class="form-group text-center">
                    <div class="col-sm-12">
                        <%
                            if (null != request.getAttribute("error")) {
                                out.println("<p class=\"bg-danger\">" + request.getAttribute("error") + "</p>");
                            }
                            if (null != request.getAttribute("success")) {
                                out.println("<p class=\"bg-success\">" + request.getAttribute("success") + "</p>");
                            }
                        %>
                    </div>
                </div>
                <div class="box-footer">
                    <button type="submit" class="btn btn-info pull-right">Create New</button>
                </div>
                <!-- /.box-footer -->
            </form>
        </div>
    </div>
</section>

<jsp:include page="/WEB-INF/pages/admin/footer.jsp"/>
<script>
    $(function () {
        $(".textarea").wysihtml5();
    });
</script>