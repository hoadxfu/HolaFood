/* 
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */

$(window).on('load', function () {
    $('.flexslider').flexslider({
        animation: "slide"
    });
});

$(document).ready(function () {

    function productItemTemplate(item) {
        return '<a href="product.html?id=' + item.id + '">\n\
                    <div class="col-md-4 item">\n\
                        <div class="feature-img">\n\
                            <img src="http://lorempixel.com/600/400/food/' + item.id % 10 + '" alt=""/>\n\
                        </div>\n\
                        <div class="item-info">\n\
                            <h3 class="item-name">' + item.name + '</h3>\n\
                            <div class="item-rating">\n\
                                <span>\n\
                                    <i class="fa fa-star" aria-hidden="true"></i>\n\
                                    <i class="fa fa-star" aria-hidden="true"></i>\n\
                                    <i class="fa fa-star" aria-hidden="true"></i>\n\
                                    <i class="fa fa-star-half-o" aria-hidden="true"></i>\n\
                                    <i class="fa fa-star-o" aria-hidden="true"></i>\n\
                                </span>\n\
                                <span> 102 lượt</span>\n\
                            </div>\n\
                            <div class="comment-box">\n\
                                <ul>\n\
                                    <li>\n\
                                        <div class="user-avatar">\n\
                                            <img src="admin/assets/img/avatar2.png" alt=""/>\n\
                                        </div>\n\
                                        <div class="user-info">\n\
                                            <span class="username">\n\
                                                <strong>Ms Nobody</strong>\n\
                                            </span>\n\
                                            <p class="user-review">\n\
                                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut id condimentum purus ...\n\
                                            </p>\n\
                                        </div>\n\
                                    </li>\n\
                                    <li>\n\
                                        <div class="user-avatar">\n\
                                            <img src="admin/assets/img/avatar5.png" alt=""/>\n\
                                        </div>\n\
                                        <div class="user-info">\n\
                                            <span class="username">\n\
                                                <strong>hoadx</strong>\n\
                                            </span>\n\
                                            <p class="user-review">\n\
                                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut id condimentum purus ...\n\
                                            </p>\n\
                                        </div>\n\
                                    </li>\n\
                                </ul>\n\
                            </div>\n\
                            <div class="item-action-box">\n\
                                <span class="checked-in">\n\
                                    <i class="fa fa-map-marker" aria-hidden="true"></i> 69\n\
                                </span>\n\
                                <span class="review">\n\
                                    <i class="fa fa-comments" aria-hidden="true"></i> 96\n\
                                </span>\n\
                            </div>\n\
                            <div class="bookmark">\n\
                                <button>\n\
                                    <i class="fa fa-bookmark" aria-hidden="true"></i> Lưu\n\
                                </button>\n\
                            </div>\n\
                        </div>\n\
                    </div><!--/.end item -->\n\
                </a>';
    }

    $('#filter').submit(function() {
        var result = $('#list-item-result');
        result.empty();
        result.data('paged', 1);
        result.data('filter', $('#txtFilter').val());
        loadListProduct();
        $('html, body').animate({
            scrollTop: $(".list-item").offset().top - 75
        }, 2000);
        return false;
    });

    $('#load-more').click(function () {
        var result = $('#list-item-result'),
                paged = result.data('paged');
        result.data('paged', paged + 1);
        loadListProduct();
    });

    function loadListProduct() {
        var result = $('#list-item-result'),
                paged = result.data('paged'),
                filter = result.data('filter'),
                loading = $('#fountainG');
        loading.show();
        setTimeout(function () {
            $.post(
                    'ajaxProduct.html',
                    {
                        paged: paged,
                        filter: filter
                    },
                    function (response) {
                        loading.hide();
                        var products = response.products;
                        $.each(products, function (index, item) {
                            result.append(productItemTemplate(item));
                        });
                    }, 'json');
        }, 1000);
    }
    loadListProduct();
});
