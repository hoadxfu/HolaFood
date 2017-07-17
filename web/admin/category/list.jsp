<%-- 
    Document   : list
    Created on : Jul 14, 2017, 1:35:52 PM
    Author     : Ray Sparrow
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="fu.holafood.entity.Category"%>
<%@page import="fu.holafood.model.UserModel"%>
<%@page import="fu.holafood.controller.UserController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UserController fu = new UserController();
    Cookie c = fu.getCookie(request, response);
    UserModel um = new UserModel();
    String permission = null;
    ArrayList<Category> categories = null;
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
    <jsp:param name="title" value="Category"/>
    <jsp:param name="name" value="category_list"/>
    <jsp:param name="permission" value="<%=permission%>"/>
</jsp:include>

<script>
    function getCategoryId(userId, buttonName) {
        if (buttonName === "deleteButton") {
            var bool = confirm("Do you really want to delete?");
            if (bool) {
                document.getElementById('deleteAction').value = userId;
                document.getElementById('actionForm').submit();
            }
        } else {
            document.getElementById('updateAction').value = userId;
            document.getElementById('actionForm').submit();
        }
    }
</script>

<%
    Category ca = (Category) request.getAttribute("categoryUpdate");
    if (ca != null) {
%>
<div class="box">
    <div class="box-header with-border">
        <h3 class="box-title">Update Category</h3>
    </div>
    <!-- /.box-header -->
    <div class="box-body">

        <form action="${pageContext.request.contextPath}/CategoryListActionUpdate" method="POST">
            <input type="hidden" name="categoryId" value="<%=ca.getId()%>">
            <div class="form-group">
                <label>Category Parent: </label>
                <select  name="newPid">
                    <option value="0">None</option>
                    <%
                        if (categories != null) {
                            for (int i = 0; i < categories.size(); i++) {
                                if (ca.getId() != categories.get(i).getId()) {
                    %>
                    <option value="<%=categories.get(i).getId()%>"><%=categories.get(i).getName()%></option>
                    <%
                                }
                            }
                        }
                    %>
                </select>
            </div>
            <div class="form-group">
                <label>Category Name: </label>
                <input type="text" class="form-control" value="<%=ca.getName()%>" name="newCategoryName">
            </div>
            <div class="form-group">
                <label>Slug: </label>
                <input type="text" class="form-control" value="<%=ca.getSlug()%>" name="newSlug">
            </div>
            <div class="form-group">
                <label>Description</label>
                <textarea id="destext" name="newDescription"  class="textarea" style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
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
                <input class="btn btn-info pull-right" type="button" onclick="location.href = '${pageContext.request.contextPath}/admin/category/list.jsp';" value="Cancel" />
                <button type="submit" class="btn btn-info pull-right">Update</button>
            </div>
            <!-- /.box-footer -->
        </form>
    </div>
</div>
<%
    }
%>

<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">List All Categories</h3>
                    <div class="box-tools">
                        <div class="input-group input-group-sm" style="width: 150px;">
                            <input type="text" name="table_search" class="form-control pull-right" placeholder="Search">

                            <div class="input-group-btn">
                                <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                            </div>
                        </div>
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
                </div>

                <form id='actionForm' action="${pageContext.request.contextPath}/CategoryListAction" method="POST">
                    <input id="deleteAction" type="hidden" name="deleteAction" value="">
                    <input id="updateAction" type="hidden" name="updateAction" value="">
                </form>

                <!-- /.box-header -->
                <div class="box-body table-responsive no-padding">
                    <table class="table table-hover">
                        <tr>
                            <th>ID</th>                            
                            <th>Category Name</th>
                            <th>Category Parent</th>
                            <th>Slug</th>
                            <th>Description</th>
                            <th>Created At</th>
                            <th>Updated At</th>
                            <th>Action</th>
                        </tr>
                        <%
                            try {
                                List<Category> list = um.getCategory();
                                for (int i = 0; i < list.size(); i++) {
                                    Category cat = list.get(i);
                                    String cateParent = "No Parent";
                                    for (int j = 0; j < list.size(); j++) {
                                        if (cat.getPid() != 0 && cat.getPid() == list.get(j).getId()) {
                                            cateParent = list.get(j).getName();
                                        }
                                    }
                        %>
                        <tr>
                            <td><%=cat.getId()%></td>                            
                            <td><%=cat.getName()%></td>
                            <td><%=cateParent%></td>
                            <td><%=cat.getSlug()%></td>
                            <td><%=cat.getDescreption()%></td>
                            <td><%=cat.getCreatedAt()%></td>
                            <td><%=cat.getUpdatedAt()%></td>
                            <td>
                                <input type="button" value="Delete" onclick="getCategoryId(<%=cat.getId()%>, 'deleteButton')">
                                <input type="button" value="Update" onclick="getCategoryId(<%=cat.getId()%>, 'updateButton')">
                            </td>
                        </tr>
                        <%
                                }
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        %>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
    </div>
</section>

<jsp:include page="/WEB-INF/pages/admin/footer.jsp"/>
<script>
    $(function () {
        $(".textarea").wysihtml5();
    });
</script>