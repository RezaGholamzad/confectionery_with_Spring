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

    <!--Vue js-->
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.7/dist/vue.js"></script>

    <title>شیرینی</title>
</head>
<body class="container-fluid">
<div id="app">
    <nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
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
                    <a href="/shopVerification" class="nav-link btn btn-outline-info btn-sm" style="margin: 5px auto;border: none;float: right">صفحه فروشندگان</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="clearFix"></div>
    <section>
        <div class="card col-md-12 col-12" style="margin-top: 10px;margin-bottom: 10px;border-color: #17a2b8">
            <img class="card-img-top col-md-5 rounded-circle" th:src="${shop.shopImageAddress}" alt="Card image" style="max-height: 250px;float:right ">
            <div class="card-body cardBody col-md-5" id="card-position">
                <h4 class="card-title" th:text="${'فروشگاه ' + shop.shopName}"></h4>
                <p class="card-text" th:text="${'استان_شهر: ' + shop.state + '_' + shop.city}"></p>
                <p class="card-text" th:text="${'ادرس: ' + shop.address}"></p>
                <p class="card-text" style="float: right">شماره های فروشگاه: </p>
                <div th:each="shopPhone : ${shop.shopPhones}">
                    <p class="card-text" style="float: right" th:text="${'_' + shopPhone.phone}"></p>
                </div>
                <div th:if="${shop.deliveryMan}">
                    <div class="clearFix"></div>
                    <p class="card-text">فروشگاه قابلیت ارسال با پیک را دارد</p>
                </div>
                <div th:if="${shop.score != 0}">
                    <div class="clearFix"></div>
                    <p class="card-text" th:text="${'امتیاز فروشگاه: ' + shop.score}"></p>
                </div>
                <div th:if="${!shop.explanation.isEmpty()}">
                    <div class="clearFix"></div>
                    <p class="card-text" th:text="${'توضیحات: ' + shop.explanation}"></p>
                </div>
                <div th:if="${!shop.descriptionOfWorkingHours.isEmpty()}">
                    <div class="clearFix"></div>
                    <p class="card-text" th:text="${'ساعات کاری در هفته: ' + shop.descriptionOfWorkingHours}"></p>
                </div>
            </div>
        </div>
        <div class="clearFix"></div>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <ul class="nav nav-tabs" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="tab" href="#cake">کیک</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#sweet">شیرنی</a>
                </li>
            </ul>
        </nav>
        <div class="tab-content">
            <div class="tab-pane container active" id="cake">
                <div style="margin: auto" class="col-11">
                    <div th:if="${not #lists.isEmpty(products)}">
                        <div th:each="product : ${products}">
                            <div th:if="${product.cake}">
                                <div class="card my-card col-xl-3 col-lg-3 col-md-5 col-10" style="width:100%;background-color: #50565c;min-height: 345px;padding: 0;margin: 0">
                                    <img class="card-img-top img-thumbnail" th:src="${product.productImageAddress}" alt="Card image" style="height: 250px">
                                    <div class="card-body cardBody" style="color: white">
                                        <div th:if="${product.discountCode != null}">
                                            <div class="offer">
                                                <p class="inside-offer" th:text="${product.discountCode.percentage + '%'}"></p>
                                                <p class="discount" >تخفیف</p>
                                            </div>
                                        </div>
                                        <h4 class="card-title" data-toggle="tooltip" th:title="${product.explanation}" th:text="${product.name}"></h4>
                                        <p class="card-text" th:text="${product.price + ' تومان'}"></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="tab-pane container fade" id="sweet">
                <div style="margin: auto" class="col-11">
                    <div th:if="${not #lists.isEmpty(products)}">
                        <div th:each="product : ${products}">
                            <div th:if="${!product.cake}">
                                <div class="card my-card col-xl-3 col-lg-3 col-md-5 col-10" style="width:100%;background-color: #50565c;min-height: 345px;padding: 0;margin: 0">
                                    <img class="card-img-top img-thumbnail" th:src="${product.productImageAddress}" alt="Card image" style="height: 250px">
                                    <div class="card-body cardBody" style="color: white">
                                        <div th:if="${product.discountCode != null}">
                                            <div class="offer">
                                                <p class="inside-offer" th:text="${product.discountCode.percentage + '%'}"></p>
                                                <p class="discount" >تخفیف</p>
                                            </div>
                                        </div>
                                        <h4 class="card-title" data-toggle="tooltip" th:title="${product.explanation}" th:text="${product.name +' ( '+ product.numberInKil + 'تا در هر کیلو' + ' ) ' }"></h4>
                                        <p class="card-text" th:text="${product.price + ' تومان'}"></p>
                                    </div>
                                </div>
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
    <!-- ./Footer -->
</div>

</body>

<script>
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>
<style>

    #card-position {
        position: absolute;
        left: 0;
    }
</style>
