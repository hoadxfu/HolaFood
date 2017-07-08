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
    <jsp:param name="title" value="Dashboard"/>
    <jsp:param name="name" value="users"/>
    <jsp:param name="permission" value="<%=permission%>"/>
</jsp:include>

<!-- Main content -->
<section>
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
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive no-padding">
                    <table class="table table-hover">
                        <tr>
                            <th>ID</th>
                            <th>User Name</th>
                            <th>Password</th>
                            <th>Email</th>
                            <th>Full Name</th>
                            <th>Permission</th>
                            <th>Gender</th>
                            <th>Dob</th>
                            <th>Created At</th>
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
                            <td><%=u.getPassword()%></td>
                            <td><%=u.getEmail()%></td>
                            <td><%=u.getFullname()%></td>
                            <td><%=u.getPermi()%></td>
                            <td><%=u.getGender()%></td>
                            <td><%=u.getDob()%></td>
                            <td><%=u.getCreatedAt()%></td>
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