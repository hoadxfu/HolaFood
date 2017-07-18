<%-- 
    Document   : list
    Created on : Jul 18, 2017, 10:08:29 PM
    Author     : Ray Sparrow
--%>

<%@page import="fu.holafood.model.RatingModel"%>
<%@page import="fu.holafood.entity.Product"%>
<%@page import="java.util.List"%>
<%@page import="fu.holafood.entity.Rate"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fu.holafood.model.UserModel"%>
<%@page import="fu.holafood.controller.UserController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UserController fu = new UserController();
    Cookie c = fu.getCookie(request, response);
    UserModel um = new UserModel();
    RatingModel rm = new RatingModel();
    String permission = null;
   // ArrayList<Rate> rates = null;
    try {
        permission = um.getPermision(c);
        //rates = um.getRate();
    } catch (Exception e) {
        System.out.println(e);
    }
    if (c == null || !permission.equalsIgnoreCase("admin")) {
        response.sendRedirect("../../index.jsp");
    }
%>
<jsp:include page="/WEB-INF/pages/admin/header.jsp">
    <jsp:param name="title" value="Rating"/>
    <jsp:param name="name" value="Rating_list"/>
    <jsp:param name="permission" value="<%=permission%>"/>
</jsp:include>

<script>
    function getRateId(userId, buttonName) {
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
    Rate ra = (Rate) request.getAttribute("rateUpdate");
    if (ra != null) {
%>
<div class="box">
    <div class="box-header with-border">
        <h3 class="box-title">Update Ratings</h3>
    </div>
    <!-- /.box-header -->
    <div class="box-body">

        <form action="${pageContext.request.contextPath}/RateListActionUpdate" method="POST">
            <input type="hidden" name="rateId" value="<%=ra.getId()%>">
            
            <div class="form-group">
                <label>Rating Value: </label>
                <input type="text" class="form-control" value="<%=ra.getValue() %>" name="value">
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
                <input class="btn btn-info pull-right" type="button" onclick="location.href = '${pageContext.request.contextPath}/admin/ratting/list.jsp';" value="Cancel" />
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
                    <h3 class="box-title">List All Ratings</h3>
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

                <form id='actionForm' action="${pageContext.request.contextPath}/RateListAction" method="POST">
                    <input id="deleteAction" type="hidden" name="deleteAction" value="">
                    <input id="updateAction" type="hidden" name="updateAction" value="">
                </form>

                <!-- /.box-header -->
                <div class="box-body table-responsive no-padding">
                    <table class="table table-hover">
                        <tr>
                            <th>ID</th>                            
                            <th>Item</th>
                            <th>Value</th>
                            <th>Created At</th>
                            <th>Updated At</th>
                            <th>Action</th>
                        </tr>
                        <%
                            try {
                                List<Rate> list = rm.getRate();
                                for (int i = 0; i < list.size(); i++) {
                                    Rate rat = list.get(i);
                                    Product pro = um.getProductById(rat.getItem_id());
                                   
                        %>
                        <tr>
                            <td><%=rat.getId()%></td>                            
                            <td><%=pro.getName() %></td>
                            <td><%=rat.getValue() %></td>
                            <td><%=rat.getCreatedAt()%></td>
                            <td><%=rat.getUpdatedAt()%></td>
                            <td>
                                <input type="button" value="Delete" onclick="getRateId(<%=rat.getId()%>, 'deleteButton')">
                                <input type="button" value="Update" onclick="getRateId(<%=rat.getId()%>, 'updateButton')">
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
