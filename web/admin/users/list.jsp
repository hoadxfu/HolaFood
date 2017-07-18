<%@page import="fu.holafood.entity.UserInforUpdate"%>
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
    if (c == null || !(permission.equalsIgnoreCase("admin") || permission.equalsIgnoreCase("moderator"))) {
        response.sendRedirect("../../index.jsp");
    }
%>
<jsp:include page="/WEB-INF/pages/admin/header.jsp">
    <jsp:param name="title" value="Users"/>
    <jsp:param name="name" value="user_list"/>
    <jsp:param name="permission" value="<%=permission%>"/>
</jsp:include>

<script>
    function getUserId(userId, buttonName) {
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
    UserInforUpdate userUpdate = (UserInforUpdate) request.getAttribute("userUpdate");
    if (userUpdate != null) {
%>
<form id='updateActionForm' action="${pageContext.request.contextPath}/UserListActionUpdate" method="POST">
    <!-- /.box-header -->
    <div class="box-body table-responsive no-padding">
        <input type="hidden" name="userId" value="<%=userUpdate.getId()%>">
        <table class="table table-hover">
            <tr>
                <th>ID</th>
                <th>password</th>
                <th>Email</th>
                <th>Full Name</th>
                <th>Gender</th>
                <th>Dob</th>
            </tr>
            <tr>
                <td><%=userUpdate.getId()%></td>
                <td><input type="password" name="newPassword" placeholder=""></input></td>
                <td>
                    <div>
                        <label for="inputEmail3" class="col-sm-2 control-label"></label>
                        <div class="col-sm-10">
                            <input  type="email" name="newEmail" value="<%=userUpdate.getEmail()%>"/>
                        </div>
                    </div>
                </td>
                <td><input type="text" name="newFullName" value="<%=userUpdate.getFullname()%>"></input></td>
                <td><select name="newGender" id="newGender">
                        <option value="Male" <%=userUpdate.getGender().equalsIgnoreCase("Male") ? "selected" : ""%>>Male</option>
                        <option value="Female" <%=userUpdate.getGender().equalsIgnoreCase("Female") ? "selected" : ""%>>Female</option>
                    </select></input></td>
                <td><input type="date" name="newDob" value="<%= userUpdate.getDob()%>"></input></td>
            </tr>
        </table>
        <div class="box-footer">
            <input class="btn btn-info pull-right" type="button" onclick="location.href = '${pageContext.request.contextPath}/admin/users/list.jsp';" value="Cancel" />
            <button type="submit" class="btn btn-info pull-right">Update</button>
        </div>
    </div>
    <!-- /.box-body -->
</form>

<%
    }
%>

<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">List All Users</h3>
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

                <form id='actionForm' action="${pageContext.request.contextPath}/UserListAction" method="POST">
                    <input id="deleteAction" type="hidden" name="deleteAction" value="">
                    <input id="updateAction" type="hidden" name="updateAction" value="">
                </form>

                <!-- /.box-header -->
                <div class="box-body table-responsive no-padding">
                    <table class="table table-hover">
                        <tr>
                            <th>ID</th>
                            <th>User Name</th>
                            <th>Email</th>
                            <th>Permission</th>
                            <th>Gender</th>
                            <th>Created At</th>
                            <th>Action</th>
                        </tr>
                        <%
                            try {
                                List<User> list = um.getUsers();
                                for (int i = 0; i < list.size(); i++) {
                                    User u = list.get(i);
                        %>
                        <tr>
                            <td><%=u.getId()%></td>
                            <td><%=u.getUsername()%></td>
                            <td><%=u.getEmail()%></td>
                            <td><%=u.getPermi()%></td>
                            <td><%=u.getGender()%></td>
                            <td><%=u.getCreatedAt()%></td>
                            <td>
                                <input type="button" value="Delete" onclick="getUserId(<%=u.getId()%>, 'deleteButton')">
                                <input type="button" value="Update" onclick="getUserId(<%=u.getId()%>, 'updateButton')">
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