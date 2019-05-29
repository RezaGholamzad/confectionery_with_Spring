<html lang="fa" dir="rtl" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <!--external css-->
    <link rel="stylesheet" type="text/css" href="/css/app.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <!--Vue js-->
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.7/dist/vue.js"></script>

    <title>شیرینی</title>
</head>

<body class="container-fluid">
<div id="app">
    <nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top" style="margin-bottom: -50px">
        <a class="navbar-brand" th:href="@{/}"><img class="rounded-circle" src="/static image/logo.jpg" style="height: 30px;width: 30px"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <form th:action="@{/destroy}" class="logout" method="post" style="margin: 5px auto">
                        <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
                        <input type="submit" value="خروج" class="nav-link btn btn-outline-info btn-sm" style="border: none" >
                    </form>
                </li>
                <li class="nav-item">
                    <a href="/shopProfile" style="margin: 5px auto;border: none;float: right" class="nav-link btn btn-outline-info btn-sm">پروفایل</a>
                </li>
            </ul>
        </div>
        <!--<div>-->
            <!--<form class="form-inline" action="" style="margin: 5px auto">-->
                <!--<input class="form-control mr-sm-2" type="text" placeholder="عبارت مورد نظر">-->
                <!--<button class="btn btn-success" type="submit">جست و جو</button>-->
            <!--</form>-->
        <!--</div>-->
    </nav>
    <div class="clearFix"></div>
    <section>
        <div th:if="${not #lists.isEmpty(orders)}">
            <div th:each="order : ${orders}">
                <table class="table table-dark table-hover">
                    <thead>
                    <tr>
                        <th>نام خریدار</th>
                        <th>ادرس</th>
                        <th>شماره</th>
                        <th>شماره سفارش</th>
                        <th>تاریخ ثبت</th>
                        <th>تاریخ دریافت</th>
                        <th>ساعت تحویل</th>
                        <th>پیک</th>
                        <th>مبلغ</th>
                        <th>وضعیت</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td th:text="${order.carts.get(0).user.firstName} + ' ' + ${order.carts.get(0).user.lastName}"></td>
                        <td th:text="${order.carts.get(0).user.address}"></td>
                        <td th:text="${order.carts.get(0).user.phone}"></td>
                        <td th:text="${order.orderCode}"></td>
                        <td th:text="${order.purchaseDate}"></td>
                        <td th:text="${order.deliveryDate}"></td>
                        <td th:text="${order.deliveryDate.getHours()} +' تا '+ ${order.deliveryDate.getHours() + 3}"></td>
                        <td th:if="${order.deliveryMan}">بله</td>
                        <td th:unless="${order.deliveryMan}">خیر</td>
                        <td th:text="${order.price}"></td>
                        <td>
                            <form th:action="@{/sendOrder}" class="logout" method="post" style="margin: 5px auto">
                                <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
                                <input type="hidden" name="orderCode" th:value="${order.orderCode}">
                                <input type="submit" value="فرستاده شد" class="nav-link btn btn-outline-info btn-sm" style="border: none" >
                            </form>
                            <form th:action="@{/orderDelete}" class="logout" method="post" style="margin: 5px auto">
                                <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
                                <input type="hidden" name="orderCode" th:value="${order.orderCode}">
                                <input type="submit" value="حذف سفارش" class="nav-link btn btn-outline-danger btn-sm" style="border: none" >
                            </form>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div th:if="${not #lists.isEmpty(order.carts)}">
                    <div th:each="cart : ${order.carts}">
                        <div class="card-body">
                            <!-- PRODUCT -->
                            <div class="row">
                                <div class="col-12 col-sm-12 col-md-2 col-lg-2 text-center">
                                    <img class="img-responsive img-thumbnail" th:src="${cart.getProduct().getProductImageAddress()}" alt="prewiew" width="120" height="80">
                                </div>
                                <div class="col-12 text-center text-sm-center col-sm-12 col-md-2 text-md-right col-lg-2 text-lg-right">
                                    <h4 class="product-name"><strong th:text="${cart.getProduct().getName()}"></strong></h4>
                                    <h4>
                                        <small th:text="${cart.getProduct().getExplanation()}"></small>
                                    </h4>
                                </div>
                                <div class="col-12 col-sm-12 text-sm-center col-md-8 text-md-right col-lg-8 text-lg-right row">
                                    <div class="col-3 col-sm-4 col-md-3 text-md-right col-lg-3 text-lg-right" style="padding-top: 5px">
                                        <div th:if="${cart.product.discountCode != null}">
                                            قیمت:
                                            <strike style="color: red"><span style="color: black" th:text="${cart.product.price}"></span></strike>
                                            &nbsp;	&nbsp;
                                            <span th:text="${cart.product.price * (100 - cart.product.discountCode.percentage)/100}"></span>
                                            &nbsp;
                                            تومان
                                        </div>
                                        <div th:unless="${cart.product.discountCode != null}">
                                            <span>قیمت:</span>
                                            &nbsp;
                                            <span class="card-text" th:text="${cart.product.price}"></span>
                                            &nbsp;
                                            <span>تومان</span>
                                        </div>
                                        <div th:if="${cart.getProduct().isCake()}">
                                            <span class="text-muted" th:text="${'تعداد : ' + cart.getNumber()}"></span>
                                        </div>
                                        <div th:unless="${cart.getProduct().isCake()}">
                                            <span class="text-muted" th:text="${'تعداد کیلو : ' + cart.getNumber()}"></span>
                                        </div>
                                    </div>
                                    <div class="col-4 col-sm-4 col-md-4 text-md-right col-lg-4 text-lg-right" style="padding-top: 5px">
                                        <div th:if="${cart.product.discountCode != null}">
                                            <h6>
                                                <span>قیمت در مجموع:</span>
                                                &nbsp;
                                                <span th:text="${(cart.product.price * (100 - cart.product.discountCode.percentage)/100) * cart.number}">
                                                </span>
                                            </h6>
                                        </div>
                                        <div th:unless="${cart.product.discountCode != null}">
                                            <h6>
                                                <span th:with="result = ${cart.product.price * cart.number}">
                                                    <span th:text="${'قیمت در مجموع: ' + result}"></span>
                                                </span>
                                            </h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <hr>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <div class="clearFix"></div>
    <section id="footer">
        <div class="container">
            <div class="row text-center text-xs-center text-sm-left text-md-left">
                <div class="col-xs-12 col-sm-5 col-md-5">
                    <h5>ارتباط با ما</h5>
                    <ul class="list-unstyled quick-links">
                        <li><a>09364006697<i class="fa"></i></a></li>
                        <li><a>gholamzadreza@gmail.com<i class="fa"></i></a></li>
                    </ul>
                </div>
                <div class="col-xs-12 col-sm-4 col-md-4">
                    <h5>دسترسی سریع</h5>
                    <ul class="list-unstyled quick-links">
                        <li><a href="/">خانه<i class="fa fa-angle-double-left"></i></a></li>
                        <li><a href="/userRegister">ثبت نام کاربر<i class="fa fa-angle-double-left"></i></a></li>
                        <li><a href="/shopRegister">ثبت نام فروشگاه<i class="fa fa-angle-double-left"></i></a></li>
                        <li><a href="/loginPage">ورود<i class="fa fa-angle-double-left"></i></a></li>
                    </ul>
                </div>
                <!--<div class="col-xs-12 col-sm-4 col-md-4">-->
                <!--<h5>راهنمای استفاده از سایت</h5>-->
                <!--<ul class="list-unstyled quick-links">-->
                <!--<li><a href="">ورود<i class="fa fa-angle-double-left"></i></a></li>-->
                <!--<li><a href="">ثبت نام<i class="fa fa-angle-double-left"></i></a></li>-->
                <!--<li><a href="">طریقه درج محصول<i class="fa fa-angle-double-left"></i></a></li>-->
                <!--<li><a href="">طریقه سبد خرید<i class="fa fa-angle-double-left"></i></a></li>-->
                <!--</ul>-->
                <!--</div>-->
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 mt-2 mt-sm-5">
                    <ul class="list-unstyled list-inline social text-center">
                        <!--<li class="list-inline-item"><a href="javascript:void();"><i class="fa fa-facebook"></i></a></li>-->
                        <!--<li class="list-inline-item"><a href="javascript:void();"><i class="fa fa-twitter"></i></a></li>-->
                        <li class="list-inline-item"><a href=""><i class="fa fa-instagram"></i></a></li>
                        <li class="list-inline-item"><a href=""><i class="fa fa-google-plus"></i></a></li>
                        <!--<li class="list-inline-item"><a href="javascript:void();" target="_blank"><i class="fa fa-envelope"></i></a></li>-->
                    </ul>
                </div>
                <hr>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 mt-2 mt-sm-2 text-center text-white">
                    <p> استفاده از مطالب فروشگاه فقط برای مقاصد غیرتجاری و با ذکر منبع بلامانع است. کلیه حقوق این سایت متعلق به دانشگاه سمنان می‌باشد.</p>
                    <p class="h6"><a class="text-green ml-2" href="https://www.semnan.ac.ir/" target="_blank">دانشگاه سمنان</a></p>
                </div>
                <hr>
            </div>
        </div>
    </section>
</div>

</body>

</html>



<script>
    $(document).ready(function () {
        var code;
        $(".edit").click(function () {
            code = $(this).children().val();
            //alert(code)
            $(".productCode").val(code);
        });
        $('[data-toggle="tooltip"]').tooltip();
    });
    // $(document).ready(function(){
    //
    // });
</script>

<style>
    .custom-file-input::-webkit-file-upload-button {
        visibility: hidden;
    }
    .custom-file-input::before {
        content: 'عکس محصول';
        background: linear-gradient(top, #f9f9f9, #e3e3e3);
        border: 1px solid #999;
        border-radius: 3px;
        margin-right: -11px;
        padding: 5px 3px;
        vertical-align: super;
        outline: none;
        white-space: nowrap;
        -webkit-user-select: none;
        cursor: pointer;
        text-shadow: 1px 1px #fff;
        font-weight: 100;
        font-size: 10pt;
    }
    .custom-file-input:hover::before {
        border-color: black;
    }
    .custom-file-input:active::before {
        background: -webkit-linear-gradient(top, #e3e3e3, #f9f9f9);
    }
    #productImageSweet{
        opacity: 1;
    }
    #productImageCake{
        opacity: 1;
    }
    #productImageEditCake{
        opacity: 1;
    }
    #productImageEditSweet{
        opacity: 1;
    }
    .offer{
        z-index: 1;
        width: 105px;
        float: left;
        background-color: #dc3545;
        border-radius: 50%;
        display: inline-block;
        margin-left: -15px;
        margin-top: -70px;
    }
    .inside-offer{
        color: white;
        margin: 6px 12px 6px -15px;
        font-size: large;
        float: right;
    }
    .discount{
        margin-top: 7px;
        text-align: center;
        font-size: large;
        color: white;
    }

</style>