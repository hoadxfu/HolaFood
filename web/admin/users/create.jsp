<%@page import="java.util.List"%>
<%@page import="fu.holafood.entity.PermissionGroup"%>
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
    <jsp:param name="title" value="Create New User"/>
    <jsp:param name="name" value="user_create"/>
    <jsp:param name="permission" value="<%=permission%>"/>
</jsp:include>

<!-- Main content -->
<section class="content">
    <!-- Horizontal Form -->
    <div class="box box-info">
        <div class="box-header with-border">
            <h3 class="box-title">Create New User</h3>
        </div>
        <!-- /.box-header -->
        <!-- form start -->
        <form class="form-horizontal" action="${pageContext.request.contextPath}/CreateUser" method="POST">
            <div class="box-body">
                <div class="form-group">
                    <label for="userName" class="col-sm-2 control-label">User name (*)</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="userName" name="userName" placeholder="User Name"/>
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-sm-12">
                        <%
                            if (null != request.getAttribute("emptyUserName")) {
                                out.println("<p class=\"bg-danger\">" + request.getAttribute("emptyUserName") + "</p>");
                            }
                        %>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">Password (*)</label>

                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="inputPassword3" name="password" placeholder="Password">
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-sm-12">
                        <%
                            if (null != request.getAttribute("emptyPassword")) {
                                out.println("<p class=\"bg-danger\">" + request.getAttribute("emptyPassword") + "</p>");
                            }
                        %>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">Email (*)</label>

                    <div class="col-sm-10">
                        <input type="email" class="form-control" id="inputEmail3" name="email" placeholder="Email">
                    </div>
                </div>
                <div class="form-group text-center">
                    <div class="col-sm-12">
                        <%
                            if (null != request.getAttribute("emptyEmail")) {
                                out.println("<p class=\"bg-danger\">" + request.getAttribute("emptyEmail") + "</p>");
                            }
                        %>
                    </div>
                </div>
                <div class="form-group">
                    <label for="fullName" class="col-sm-2 control-label">Full name</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Full Name">
                    </div>

                </div>
                <!-- select -->
                <div class="form-group">
                    <label for="permission" class="col-sm-2 control-label">Permission</label>
                    <div class="col-sm-3">
                        <select class="form-control" name="permission">
                            <%
                                try {
                                    List<PermissionGroup> list = um.getPermissionGroups();
                                    for (int i = 0; i < list.size(); i++) {
                                        PermissionGroup pg = list.get(i);
                            %>
                            <option value="<%=pg.getId()%>"><%=pg.getName()%></option>
                            <%
                                    }
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                            %>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="gender" class="col-sm-2 control-label">Gender</label>
                    <div class="col-sm-2">
                        <select  class="form-control" name="gender" id="gender">
                            <option>Male</option>
                            <option>Female</option>
                        </select>

                    </div>
                </div>
                <div class="form-group">
                    <label for="dob" class="col-sm-2 control-label">Date Of Birth</label>
                    <div class="col-sm-3">
                        <input type="date" class="form-control" id="dob" name="dob" >
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
            <!-- /.box-body -->
            <div class="box-footer">
                <input type="button" onclick="location.href = '../index.jsp';" value="Cancel" />
                <button type="submit" class="btn btn-info pull-right">Create New</button>
            </div>
            <!-- /.box-footer -->
        </form>
    </div>
    <!-- /.box -->
</section>

<jsp:include page="/WEB-INF/pages/admin/footer.jsp"/>