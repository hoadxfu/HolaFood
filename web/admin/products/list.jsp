<%@page import="fu.holafood.entity.Product"%>
<%@page import="java.util.List"%>
<%@page import="fu.holafood.entity.User"%>
<%@page import="fu.holafood.model.UserModel"%>
<%@page import="fu.holafood.controller.UserController"%>
<%
    UserController fu = new UserController();
    Cookie c = fu.getCookie(request, response);
    UserModel um = new UserModel();
    String permission = null;
    try {
        permission = um.getPermision(c);
    } catch (Exception e) {
        System.out.println(e);
    }
    if (c == null || !permission.equalsIgnoreCase("admin")) {
        response.sendRedirect("../../index.jsp");
    }
%>
<jsp:include page="/WEB-INF/pages/admin/header.jsp">
    <jsp:param name="title" value="Products"/>
    <jsp:param name="name" value="product_list"/>
    <jsp:param name="permission" value="<%=permission%>"/>
</jsp:include>

<script>
    function getProductId(userId, buttonName) {
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
    Product p = (Product) request.getAttribute("productUpdate");
    if (p != null) {
%>
<div class="box">
    <div class="box-header with-border">
        <h3 class="box-title">Update Product</h3>
    </div>
    <!-- /.box-header -->
    <div class="box-body">
        
        <form action="${pageContext.request.contextPath}/ProductListActionUpdate" method="POST">
            <input type="hidden" name="productId" value="<%=p.getId()%>">
            <div class="form-group">
                <label>Product Name: </label>
                <input type="text" class="form-control" placeholder="<%=p.getName()%>" name="newProductName">
            </div>
            <div class="form-group">
                <label>Slug: </label>
                <input type="text" class="form-control" placeholder="<%=p.getSlug()%>" name="newSlug">
            </div>
            <div class="form-group">
                <label>Description</label>
                <textarea id="destext" name="newDescription" class="textarea" style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
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
                <input class="btn btn-info pull-right" type="button" onclick="location.href = '${pageContext.request.contextPath}/admin/products/list.jsp';" value="Cancel" />
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
                    <h3 class="box-title">List All Products</h3>
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

                <form id='actionForm' action="${pageContext.request.contextPath}/ProductListAction" method="POST">
                    <input id="deleteAction" type="hidden" name="deleteAction" value="">
                    <input id="updateAction" type="hidden" name="updateAction" value="">
                </form>

                <!-- /.box-header -->
                <div class="box-body table-responsive no-padding">
                    <table class="table table-hover">
                        <tr>
                            <th>ID</th>
                            <th>Product Name</th>
                            <th>Slug</th>
                            <th>Description</th>
                            <th>Created At</th>
                            <th>Updated At</th>
                            <th>Action</th>
                        </tr>
                        <%
                            try {
                                List<Product> list = um.getProducts();
                                for (int i = 0; i < list.size(); i++) {
                                    Product pro = list.get(i);
                        %>
                        <tr>
                            <td><%=pro.getId()%></td>
                            <td><%=pro.getName()%></td>
                            <td><%=pro.getSlug()%></td>
                            <td><%=pro.getDescription()%></td>
                            <td><%=pro.getCreatedAt()%></td>
                            <td><%=pro.getUpdatedAt()%></td>
                            <td>
                                <input type="button" value="Delete" onclick="getProductId(<%=pro.getId()%>, 'deleteButton')">
                                <input type="button" value="Update" onclick="getProductId(<%=pro.getId()%>, 'updateButton')">
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