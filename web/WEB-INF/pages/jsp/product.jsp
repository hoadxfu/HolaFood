<jsp:include page="/WEB-INF/pages/header.jsp">
    <jsp:param name="title" value="Product detail"/>
</jsp:include>
<%@ taglib uri="/struts-tags" prefix="s" %>  

<div class="container">
    <section class="product row">
        <div class="product-feature">
            <div class="col-md-4 feature-img">
                <img src="assets/images/stock-photo-105815115.jpg" alt=""/>
            </div>
            <div class="col-md-8 product-info">
                <h1 class="title">Product name</h1>
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
                <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam sed justo neque. Phasellus eget lobortis elit, sit amet ornare ex. Suspendisse ultrices dui mauris, non dictum massa iaculis at. Cras auctor hendrerit finibus. Vestibulum vitae ligula a nulla maximus iaculis a nec lorem. In nunc ipsum, luctus eu elit eget, tincidunt molestie magna. Quisque risus nunc, pharetra a lectus at, ullamcorper placerat eros. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.<br/>
                </p>
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
                        <i class="fa fa-bookmark" aria-hidden="true"></i>
                    </button>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </section>
    <section class="product-content row">
        <section class="review col-md-8">
            <div class="review-container">
                <h3>Đánh giá</h3>
                <form method="post">
                    <div class="rating">
                        <span>
                            <i class="fa fa-star" aria-hidden="true"></i>
                            <i class="fa fa-star" aria-hidden="true"></i>
                            <i class="fa fa-star" aria-hidden="true"></i>
                            <i class="fa fa-star-half-o" aria-hidden="true"></i>
                            <i class="fa fa-star-o" aria-hidden="true"></i>
                        </span>
                    </div>
                    <textarea class="form-control" rows="5" aria-describedby="helpBlock"></textarea>
                    <span id="helpBlock" class="help-block">Để lại nhận xét của bạn về sản phẩm này</span>
                    <button type="button" class="btn btn-primary">Gửi nhận xét</button>
                </form>
                <ul class="review-list">
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
                        <div class="clearfix"></div>
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
            <div class="clearfix"></div>
        </section>
        <section class="related col-md-4">
            <div class="related-container">
                <h3>Có thể bạn thích</h3>
                <ul class="related-list">
                    <li>
                        <img src="assets/images/stock-photo-105815115.jpg" alt=""/>
                        <div class="product-info">
                            <h4>Product name</h4>
                            <div class="rating">
                                <span>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star-half-o" aria-hidden="true"></i>
                                    <i class="fa fa-star-o" aria-hidden="true"></i>
                                </span>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </li>
                    <li>
                        <img src="assets/images/stock-photo-105815115.jpg" alt=""/>
                        <div class="product-info">
                            <h4>Product name</h4>
                            <div class="rating">
                                <span>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star-half-o" aria-hidden="true"></i>
                                    <i class="fa fa-star-o" aria-hidden="true"></i>
                                </span>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </li>
                    <li>
                        <img src="assets/images/stock-photo-105815115.jpg" alt=""/>
                        <div class="product-info">
                            <h4>Product name</h4>
                            <div class="rating">
                                <span>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star-half-o" aria-hidden="true"></i>
                                    <i class="fa fa-star-o" aria-hidden="true"></i>
                                </span>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </li>
                    <li>
                        <img src="assets/images/stock-photo-105815115.jpg" alt=""/>
                        <div class="product-info">
                            <h4>Product name</h4>
                            <div class="rating">
                                <span>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star-half-o" aria-hidden="true"></i>
                                    <i class="fa fa-star-o" aria-hidden="true"></i>
                                </span>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </li>
                    <li>
                        <img src="assets/images/stock-photo-105815115.jpg" alt=""/>
                        <div class="product-info">
                            <h4>Product name</h4>
                            <div class="rating">
                                <span>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star-half-o" aria-hidden="true"></i>
                                    <i class="fa fa-star-o" aria-hidden="true"></i>
                                </span>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </li>
                </ul>
            </div>
        </section>
        <div class="clearfix"></div>
    </section>
</div>

<jsp:include page="/WEB-INF/pages/footer.jsp"/>
