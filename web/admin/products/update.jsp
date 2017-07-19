<%@page import="fu.holafood.entity.Category"%>
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

<%
    int productId = Integer.parseInt(request.getParameter("id"));
    Product p = um.getProductById(productId);
    if (p != null) {
%>
<div class="content">
    <div class="box">
        <div class="box-header with-border">
            <h3 class="box-title">Update Product</h3>
        </div>
        <!-- /.box-header -->
        <div class="box-body">

            <form action="${pageContext.request.contextPath}/ProductListUpdate" method="POST">
                <input type="hidden" name="productId" value="<%=p.getId()%>">
                <div class="form-group">
                    <label>Product Name: </label>
                    <input id="newProductName" type="text" class="form-control" value="<%=p.getName()%>" name="newProductName">
                </div>
                <div class="form-group">
                    <label>Slug: </label>
                    <input id="newSlug" type="text" class="form-control" value="<%=p.getSlug()%>" name="newSlug">
                </div>

                <div class="form-group">
                    <label for="categories" class="col-sm-2 control-label">Category</label>
                    <br/>


                    <%
                        List<Integer> listCategoriesIds = um.getListCategoriesByProductId(productId);
                        List<Category> listCategory = um.getCategory();
                        int i;
                        for (i = 0; i < listCategory.size(); i++) {
                            Category categories = listCategory.get(i);
                            int id = categories.getId();
                            String name = categories.getName();
                            String ch = "";
                            for (int j = 0; j < listCategoriesIds.size(); j++) {
                                int tmp = listCategoriesIds.get(j);
                                if (tmp == id) {
                                    ch = "checked";
                                }
                            }
                    %>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="categories" value="<%=id%>" <%=ch%>/>
                            <%=name%>
                        </label>
                    </div>
                    <%
                        }
                    %>
                </div>  

                <div class="form-group">
                    <label>Description</label>
                    <textarea id="destext" name="newDescription" class="textarea" style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"><%=p.getDescription()%></textarea>
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
</div>
<%
    }
%>

<jsp:include page="/WEB-INF/pages/admin/footer.jsp"/>
<script>
    $(function () {
        $("#destext").wysihtml5();
    });
</script>