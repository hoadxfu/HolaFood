<jsp:include page="/WEB-INF/pages/header.jsp">
    <jsp:param name="title" value="HolaFood Homepage"/>
</jsp:include>
<%@ taglib uri="/struts-tags" prefix="s" %>  

<!-- filter box -->
<div class="filter-box">
    <div class="container">
        <div class="col-md-10 col-md-offset-1">
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active">
                    <a href="#find-anything" aria-controls="find-anything"
                       role="tab" data-toggle="tab">Tìm kiếm mọi thứ</a>
                </li>
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="find-anything">
                    <div class="row">
                        <div class="filter-form col-md-8">
                            <form method="post" onsubmit="return false;">
                                <div class="form-group form-group-lg">
                                    <label class="control-label" for="txtFilter">Bạn muốn tìm gì?</label>
                                    <div class="input-group">
                                        <input type="text" id="txtFilter" class="form-control"
                                               name="txtFilter" placeholder="Đồ ăn, địa điểm, ..." />
                                        <div class="input-group-addon">
                                            <span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                                <div class="row">
                                    <div class="col-md-4 form-group">
                                        <label for="txtCategory">Thể loại</label>
                                        <select id="txtCategory" name="txtCategory"
                                                class="form-control">
                                            <option>Đồ ăn sáng</option>
                                            <option>Đồ ăn vặt</option>
                                            <option>Nướng</option>
                                            <option>Lẩu</option>
                                            <option>Cơm trưa</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4 form-group">
                                        <label for="txtRating">Đánh giá</label>
                                        <select id="txtRating" name="txtRating"
                                                class="form-control">
                                            <option>Rất tệ</option>
                                            <option>Tệ</option>
                                            <option>Bình thường</option>
                                            <option>Tốt</option>
                                            <option>Rất tốt</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4 form-group">
                                        <label for="txtPrice">Giá từ</label>
                                        <div class="input-group">
                                            <input type="number" id="txtPrice" min="0"
                                                   class="form-control" name="txtPrice"/>
                                            <span class="input-group-addon">VND</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                                <button class="btn btn-block btn-lg btn-primary btn-submit"
                                        type="submit">Tìm kiếm</button>
                            </form>
                        </div><!--/. end filter form -->
                        <div class="col-md-4 hidden-xs hidden-sm">
                            <div class="filter-slider-image">
                                <div class="flexslider">
                                    <ul class="slides">
                                        <li>
                                            <img src="assets/images/filter-box-slider-1.jpg" />
                                        </li>
                                        <li>
                                            <img src="assets/images/filter-box-slider-2.jpg" />
                                        </li>
                                        <li>
                                            <img src="assets/images/filter-box-slider-3.jpg" />
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div><!--/. end slider image -->
                    </div><!--/. end row -->
                </div><!--/. end tab: find anything -->
            </div><!--/. end tab content -->
        </div>
    </div>
</div><!--/. end filter box -->

<!-- Content of Website in here -->
<div class="container">
    <main class="site-content">
        <section class="list-item row">
            <h2 class="block-title">
                <span>Hôm nay ăn gì?</span>
            </h2>
            <s:iterator value="products">
                <a href="product.html?id=<s:property value="id"/>">
                    <div class="col-md-4 item">
                        <div class="feature-img">
                            <img src="assets/images/stock-photo-105815115.jpg" alt=""/>
                        </div>
                        <div class="item-info">
                            <h3 class="item-name"><s:property value="name"/></h3>
                            <div class="item-rating">
                                <span>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star-half-o" aria-hidden="true"></i>
                                    <i class="fa fa-star-o" aria-hidden="true"></i>
                                </span>
                                <span> 102 lượt</span>
                            </div>
                            <div class="comment-box">
                                <ul>
                                    <li>
                                        <div class="user-avatar">
                                            <img src="admin/assets/img/avatar2.png" alt=""/>
                                        </div>
                                        <div class="user-info">
                                            <span class="username">
                                                <strong>Ms Nobody</strong>
                                            </span>
                                            <p class="user-review">
                                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut id condimentum purus ...
                                            </p>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="user-avatar">
                                            <img src="admin/assets/img/avatar5.png" alt=""/>
                                        </div>
                                        <div class="user-info">
                                            <span class="username">
                                                <strong>hoadx</strong>
                                            </span>
                                            <p class="user-review">
                                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut id condimentum purus ...
                                            </p>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="item-action-box">
                                <span class="checked-in">
                                    <i class="fa fa-map-marker" aria-hidden="true"></i> 69
                                </span>
                                <span class="review">
                                    <i class="fa fa-comments" aria-hidden="true"></i> 96
                                </span>
                            </div>
                            <div class="bookmark">
                                <button>
                                    <i class="fa fa-bookmark" aria-hidden="true"></i> Lưu
                                </button>
                            </div>
                        </div>
                    </div><!--/.end item -->
                </a>
            </s:iterator>
            <div class="clearfix"></div>
            <div id="fountainG">
                <div id="fountainG_1" class="fountainG"></div>
                <div id="fountainG_2" class="fountainG"></div>
                <div id="fountainG_3" class="fountainG"></div>
                <div id="fountainG_4" class="fountainG"></div>
                <div id="fountainG_5" class="fountainG"></div>
                <div id="fountainG_6" class="fountainG"></div>
                <div id="fountainG_7" class="fountainG"></div>
                <div id="fountainG_8" class="fountainG"></div>
            </div>
        </section>
    </main><!--/. end site-content -->
</div>

<jsp:include page="/WEB-INF/pages/footer.jsp"/>