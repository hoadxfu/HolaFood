<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${pageContext.request.contextPath}/admin/assets/img/avatar04.png" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${param.userName}</p>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
        <!-- search form -->
        <!--        <form action="#" method="get" class="sidebar-form">
                    <div class="input-group">
                        <input type="text" name="q" class="form-control" placeholder="Search...">
                        <span class="input-group-btn">
                            <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                            </button>
                        </span>
                    </div>
                </form>-->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <% String name = request.getParameter("name");%>
            <li class="header">HolaFood Admin Panel</li>
            <li class="<%= (name.equals("dashboard")) ? "active" : ""%>">
                <a href="${pageContext.request.contextPath}/admin/index.jsp">
                    <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                </a>
            </li>
            <li class="<%= (name.equals("users") || name.contains("user_")) ? "active" : ""%> treeview">
                <a href="#">
                    <i class="fa fa-user"></i> <span>User Management</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li class="<%= (name.equals("user_list")) ? "active" : ""%>"><a href="${pageContext.request.contextPath}/admin/users/list.jsp"><i class="fa fa-circle-o"></i> List User</a></li>
                    <li class="<%= (name.equals("user_create")) ? "active" : ""%>"><a href="${pageContext.request.contextPath}/admin/users/create.jsp"><i class="fa fa-circle-o"></i> Create New</a></li>
                </ul>
            </li>
            <li class="<%= (name.equals("products") || name.contains("product_")) ? "active" : ""%> treeview">
                <a href="#">
                    <i class="fa fa-birthday-cake"></i> <span>Product Management</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li class="<%= (name.equals("product_list")) ? "active" : ""%>"><a href="${pageContext.request.contextPath}/admin/products/list.jsp"><i class="fa fa-circle-o"></i> List Product</a></li>
                    <li class="<%= (name.equals("product_create")) ? "active" : ""%>"><a href="${pageContext.request.contextPath}/admin/products/create.jsp"><i class="fa fa-circle-o"></i> Create New Product</a></li>
                </ul>
            </li>
            <li class="<%= (name.equals("category") || name.contains("category_")) ? "active" : ""%> treeview">
                <a href="#">
                    <i class="fa fa-birthday-cake"></i> <span>Category Management</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li class="<%= (name.equals("category_list")) ? "active" : ""%>"><a href="${pageContext.request.contextPath}/admin/category/list.jsp"><i class="fa fa-circle-o"></i> List Category</a></li>
                    <li class="<%= (name.equals("category_create")) ? "active" : ""%>"><a href="${pageContext.request.contextPath}/admin/category/create.jsp"><i class="fa fa-circle-o"></i> Create New Category</a></li>
                </ul>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>