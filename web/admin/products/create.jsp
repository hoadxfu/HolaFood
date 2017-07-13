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
    <jsp:param name="title" value="Create New Product"/>
    <jsp:param name="name" value="product_create"/>
    <jsp:param name="permission" value="<%=permission%>"/>
</jsp:include>
<%@taglib uri="/struts-tags" prefix="s"%>  
<section class="content">
    <div class="box">
        <div class="box-header with-border">
            <h3 class="box-title">Create Product</h3>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
            <form action="${pageContext.request.contextPath}/CreateProduct" method="POST">
                <div class="form-group">
                    <label>Product Name: </label>
                    <input type="text" class="form-control" placeholder="" name="productName">
                </div>
                <div class="form-group">
                    <label>Slug: </label>
                    <input type="text" class="form-control" placeholder="" name="slug">
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
