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
            <div id="list-item-result" data-paged="1"></div>
            <div class="clearfix"></div>
            <div id="fountainG" style="display: none;">
                <div id="fountainG_1" class="fountainG"></div>
                <div id="fountainG_2" class="fountainG"></div>
                <div id="fountainG_3" class="fountainG"></div>
                <div id="fountainG_4" class="fountainG"></div>
                <div id="fountainG_5" class="fountainG"></div>
                <div id="fountainG_6" class="fountainG"></div>
                <div id="fountainG_7" class="fountainG"></div>
                <div id="fountainG_8" class="fountainG"></div>
            </div>
            <div class="clearfix"></div>
            <button class="btn btn-primary center-block load-more" id="load-more">Load More</button>
        </section>
    </main><!--/. end site-content -->
</div>

<jsp:include page="/WEB-INF/pages/footer.jsp"/>