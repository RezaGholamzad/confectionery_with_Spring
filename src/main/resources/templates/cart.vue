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
                     <a href="/recordsOfOrders" class="nav-link btn btn-outline-info btn-sm" style="margin: 5px auto;border: none;float: right">سوابق سفارشات</a>
                 </li>
                 <li class="nav-item">
                     <a href="/userProfile" class="nav-link btn btn-outline-info btn-sm" style="margin: 5px auto;border: none;float: right">پروفایل</a>
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
    <section>
        <div th:if="${not #lists.isEmpty(carts)}">
            <div class="alert alert-info" style="text-align: right">
                <strong>نکته: </strong>
                &nbsp;
                <span>با توجه به ساعات کاری فروشگاه زمان ارسال محصول را تایین بفرمایید.</span>
                &nbsp;
                <span>ساعات کاری فروشگاه:</span>
                &nbsp;
                <span th:text="${carts.get(0).product.shop.descriptionOfWorkingHours}"></span>
            </div>
        </div>
        <div th:if="${dayOrHourIsEmpty != null}">
            <div class="alert alert-danger" style="text-align: right">
                <strong>نکته: </strong>
                &nbsp;
                <span>لطفا روز و ساعت و نحوه تحویل سفارش را مشخص کنید.</span>
            </div>
        </div>
        <div class="container">
            <div class="card shopping-cart">
                <div class="card-header bg-dark text-light">
                    <div th:if="${not #lists.isEmpty(carts)}">
                        <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                        سبد خرید
                        <form method="post" th:action="@{/setOrder}" class="form-inline col-7 col-md-9" style="margin: auto;width: 100%">
                            <input type="submit" class="btn btn-outline-info btn-sm pull-right" value="ثبت سفارش">
                            <input list="day" name="day" placeholder="روز ارسال" class="form-control col-12 col-sm-3 col-md-2 col-xl-2" style="margin-left: 2%">
                            <datalist id="day">
                                <option value="1">
                                <option value="2">
                                <option value="3">
                                <option value="4">
                                <option value="5">
                            </datalist>
                            <input list="hour" name="hour" placeholder="ساعات" class="form-control col-12 col-sm-3 col-md-2 col-xl-2" style="margin-left: 2%">
                            <datalist id="hour">
                                <option value="9 تا 12">
                                <option value="12 تا 15">
                                <option value="15 تا 18">
                                <option value="18 تا 21">
                            </datalist>
                            <div th:if="${carts.get(0).product.shop.deliveryMan}" class="form-control col-12 col-sm-3 col-md-2 col-xl-2" style="margin-left: 2%; display: contents">
                                <input list="deliveryMan" name="deliveryMan" placeholder="ارسال با پیک" class="form-control">
                                <datalist id="deliveryMan">
                                    <option value="بله">
                                    <option value="خیر">
                                </datalist>
                            </div>
                        </form>
                    </div>
                    <div th:unless="${not #lists.isEmpty(carts)}">
                        <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                        سفارشات ثبت شده
                    </div>
                    <div class="clearFix"></div>
                </div>
                <div th:if="${not #lists.isEmpty(carts)}">
                    <div th:each="cart : ${carts}">
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
                                    <!--<div class="col-3 col-sm-2 col-md-2 col-lg-2">-->
                                        <!--<div class="quantity">-->
                                            <!--<input type="number" placeholder="تعداد" step="1" max="99" min="1" th:value="${cart.getNumber()}" title="Qty" class="qty" size="4">-->
                                        <!--</div>-->
                                    <!--</div>-->
                                    <div class="col-5 col-sm-4 col-md-5 text-right col-lg-5">
                                        <form method="post" th:action="@{/cartUpdate}">
                                            <input type="hidden" th:value="${cart.codeCart}" name="codeCart">
                                            <button style="margin-right: 10px" type="submit" class="btn btn-outline-info btn-xs update">
                                                <i class="fa fa-refresh" aria-hidden="true"></i>
                                            </button>
                                            <div class="quantity">
                                                <input type="number" name="number" placeholder="تعداد" step="1" max="99" min="1" th:value="${cart.getNumber()}" title="Qty" class="qty" size="4">
                                            </div>
                                        </form>
                                        <form style="float: left;" method="post" th:action="@{/cartDelete}">
                                            <input type="hidden" th:value="${cart.codeCart}" name="codeCart">
                                            <button type="submit" class="btn btn-outline-danger btn-xs">
                                                <i class="fa fa-trash" aria-hidden="true"></i>
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <hr>
                            <!--END PRODUCT -->
                            <!--<div class="pull-right">-->
                            <!--<a href="" class="btn btn-outline-secondary pull-right">-->
                            <!--Update shopping cart-->
                            <!--</a>-->
                            <!--</div>-->
                        </div>
                    </div>
                </div>
                <div th:if="${not #lists.isEmpty(carts)}">
                    <div class="card-footer">
                        <div class="coupon col-md-5 col-sm-5 no-padding-left pull-left">
                            <!--<div class="row">-->
                                <!--<div class="col-6">-->
                                    <!--<input type="text" class="form-control" placeholder="کد تخفیف">-->
                                <!--</div>-->
                                <!--<div class="col-6">-->
                                    <!--<input type="submit" class="btn btn-default" value="استفاده از کد تخفیف">-->
                                <!--</div>-->
                            <!--</div>-->
                        </div>
                        <div class="pull-right" style="margin: 10px">
                            <div class="pull-right" style="margin: 5px">
                                <span>مبلغ کل سفارش:</span>
                                &nbsp;
                                <span th:text="${price}"></span>
                            </div>
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

<script>
    // $(document).ready(function () {
    //     var code;
    //     $(".update").click(function () {
    //         code = $(".qty").val();
    //         //alert(code)
    //         $(".productNumber").val(code);
    //     });
    // });
</script>

<style>

    .quantity {
        float: right;
        margin-right: 15px;
        background-color: #eee;
        position: relative;
        width: 80px;
        overflow: hidden
    }

    .quantity input {
        margin: 0;
        text-align: center;
        width: 15px;
        height: 15px;
        padding: 0;
        float: right;
        color: #000;
        font-size: 20px;
        border: 0;
        outline: 0;
        background-color: #F6F6F6
    }

    .quantity input.qty {
        position: relative;
        border: 0;
        width: 100%;
        height: 40px;
        padding: 10px 25px 10px 10px;
        text-align: center;
        font-weight: 400;
        font-size: 15px;
        border-radius: 0;
        background-clip: padding-box
    }

    /*.quantity .minus, .quantity .plus {*/
        /*line-height: 0;*/
        /*background-clip: padding-box;*/
        /*-webkit-border-radius: 0;*/
        /*-moz-border-radius: 0;*/
        /*border-radius: 0;*/
        /*-webkit-background-size: 6px 30px;*/
        /*-moz-background-size: 6px 30px;*/
        /*color: #bbb;*/
        /*font-size: 20px;*/
        /*position: absolute;*/
        /*height: 50%;*/
        /*border: 0;*/
        /*right: 0;*/
        /*padding: 0;*/
        /*width: 25px;*/
        /*z-index: 3*/
    /*}*/

    /*.quantity .minus:hover, .quantity .plus:hover {*/
        /*background-color: #dad8da*/
    /*}*/

    /*.quantity .minus {*/
        /*bottom: 0*/
    /*}*/
    .shopping-cart {
        margin-top: 20px;
    }
</style>