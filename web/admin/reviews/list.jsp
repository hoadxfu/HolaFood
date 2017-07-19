<%-- 
    Document   : list
    Created on : Jul 18, 2017, 9:16:56 PM
    Author     : BH_Wind
--%>

<%@page import="fu.holafood.model.ReviewModel"%>
<%@page import="fu.holafood.entity.Reviews"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="fu.holafood.model.UserModel"%>
<%@page import="fu.holafood.controller.UserController"%>


<%
    UserController fu = new UserController();
    Cookie c = fu.getCookie(request, response);
    ReviewModel rm = new ReviewModel();
    UserModel um = new UserModel();
    String permission = null;
    ArrayList<Reviews> review = null;
    try {
        permission = um.getPermision(c);
        review = rm.getReview();
    } catch (Exception e) {
        System.out.println(e);
    }
    if (c == null || !permission.equalsIgnoreCase("admin")) {
        response.sendRedirect("../../index.jsp");
    }
%>

<jsp:include page="/WEB-INF/pages/admin/header.jsp">
    <jsp:param name="title" value="Review"/>
    <jsp:param name="name" value="review_list"/>
    <jsp:param name="permission" value="<%=permission%>"/>
</jsp:include>


<script>
    function getReviewId(userId, buttonName) {
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
    Reviews rv = (Reviews) request.getAttribute("ReviewUpdate");
    if (rv != null) {
%>


<div class="box">
    <div class="box-header with-border">
        <h3 class="box-title"> Review Update </h3>
    </div>
    <!-- /.box-header -->
    <div class="box-body">

        <form action="${pageContext.request.contextPath}/ReviewListActionUpdate" method="POST">
         <!--
            <input type="hidden" name="reviewId" value="">
            <div class="form-group">
                <label>Review : </label>
                <select  name="newItem_id">
                    <option value="0">None</option>
                    <%
                        if (review != null) {
                            for (int i = 0; i < review.size(); i++) {
                                if (rv.getId() != review.get(i).getId()) {
                    %>
                    <option value="<%=review.get(i).getId()%>"><%= review.get(i).getValue()%></option>
                    <%
                                }
                            }
                        }
                    %>
                </select>
            </div>
         -->
         <div class="form-group">
                <label> ID </label>
                <input type="text" class="form-control" value="<%= rv.getId() %>" readonly="" name="reviewId">
            </div>
            <div class="form-group">
                <label> Value: </label>
                <input type="text" class="form-control" value="<%= rv.getValue()%>" name="newValue">
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
                    <h3 class="box-title">List All Reviews</h3>
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

                <form id='actionForm' action="${pageContext.request.contextPath}//ReviewtListAction" method="POST">
                    <input id="deleteAction" type="hidden" name="deleteAction" value="">
                    <input id="updateAction" type="hidden" name="updateAction" value="">
                </form>

                <!-- /.box-header -->
                <div class="box-body table-responsive no-padding">
                    <table class="table table-hover">
                        <tr>
                            <th>ID</th>    
                            <th>UserID</th>
                            <th>Value</th>
                            <th>Created At</th>
                            <th>Updated At</th>
                            <th>Action</th>
                        </tr>
                        <%
                            try {
                                List<Reviews> list = rm.getReview();
                                for (int i = 0; i < list.size(); i++) {
                                    Reviews rev = list.get(i);
                                    String cateParent = "No Parent";
                                    for (int j = 0; j < list.size(); j++) {
                                        if (rev.getItem_id()!= 0 && rev.getItem_id() == list.get(j).getId()) {
                                            cateParent = list.get(j).getValue();
                                        }
                                    }
                        %>
                        <tr>
                            <td><%=rev.getId()%></td> 
                            <td><%=rev.getUserId()%></td>
                            <td><%=rev.getValue()%></td>
                            <td><%=rev.getCread_at()%></td>
                            <td><%=rev.getUpdated_at()%></td>
                            <td><%=cateParent%></td>
                            <td>
                                <input type="button" value="Delete" onclick="getReviewId(<%=rev.getId()%>, 'deleteButton')">
                                <input type="button" value="Update" onclick="getReviewId(<%=rev.getId()%>, 'updateButton')">
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