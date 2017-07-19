<jsp:include page="/WEB-INF/pages/header.jsp">
    <jsp:param name="title" value="${product.name}"/>
</jsp:include>
<%@ taglib uri="/struts-tags" prefix="s" %>  

<div class="container">
    <section class="product row">
        <div class="product-feature">
            <div class="col-md-4 feature-img">
                <img src="http://lorempixel.com/600/400/food/<s:property value="product.id % 10"/>" alt=""/>
            </div>
            <div class="col-md-8 product-info">
                <h1 class="title"><s:property value="product.name"/></h1>
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
                <ul class="categories">
                    <s:iterator value="categories">
                        <li><s:property value="name"/>, </li>
                    </s:iterator>
                </ul>
                <div class="clearfix"></div>
                <p><s:property value="product.description" escapeHtml="false"/></p>
                <div class="item-action-box">
                    <span class="checked-in">
                        <i class="fa fa-map-marker" aria-hidden="true"></i> 69
                    </span>
                    <span class="review">
                        <i class="fa fa-comments" aria-hidden="true"></i> <s:property value="listReview.size()"/>
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
                <s:if test="%{mess == 'reviewSuccess'}">
                    <div class="alert alert-success" role="alert">Thank you for review</div>  
                </s:if>
                <s:if test="%{mess == 'reviewError'}">
                    <div class="alert alert-error" role="alert">Something wrong!!!</div>  
                </s:if>
                <s:if test="%{loggedUser != null && loggedUser != ''}">
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
                        <textarea name="review" class="form-control" rows="5" aria-describedby="helpBlock"></textarea>
                        <span id="helpBlock" class="help-block">Để lại nhận xét của bạn về sản phẩm này</span>
                        <button type="submit" class="btn btn-primary">Gửi nhận xét</button>
                    </form>
                </s:if>
                <ul class="review-list">
                    <s:iterator value="listReview">
                        <li>
                            <div class="user-avatar">
                                <img src="http://lorempixel.com/75/75/cats/<s:property value="id % 10"/>" alt=""/>
                            </div>
                            <div class="user-info">
                                <span class="username">
                                    <strong><s:property value="userName"/></strong>
                                </span>
                                <p class="user-review">
                                    <s:property value="value"/>
                                </p>
                            </div>
                            <div class="clearfix"></div>
                        </li>
                    </s:iterator>
                </ul>
            </div>
            <div class="clearfix"></div>
        </section>
        <section class="related col-md-4">
            <div class="related-container">
                <h3>Có thể bạn thích</h3>
                <ul class="related-list">
                    <s:iterator value="relatedProduct">
                        <li>
                            <img src="http://lorempixel.com/50/50/food/<s:property value="id % 10"/>" alt=""/>
                            <div class="product-info">
                                <h4><s:property value="name"/></h4>
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
                    </s:iterator>
                </ul>
            </div>
        </section>
        <div class="clearfix"></div>
    </section>
</div>

<jsp:include page="/WEB-INF/pages/footer.jsp"/>
