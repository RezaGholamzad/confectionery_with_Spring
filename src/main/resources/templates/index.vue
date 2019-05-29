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
            <div th:if="${#httpSession != null}">
                <div th:if="${#httpSession.getAttribute('userEmail') != null}">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <form th:action="@{/destroy}" class="logout" method="post" style="margin: 5px auto">
                                <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
                                <input type="submit" value="خروج" class="nav-link btn btn-outline-info btn-sm" style="border: none" >
                            </form>
                        </li>
                        <li class="nav-item">
                            <a href="/cart" class="nav-link btn btn-outline-info btn-sm" style="margin: 5px auto;border: none;float: right">سبد خرید</a>
                        </li>
                        <li class="nav-item">
                            <a href="/userProfile" class="nav-link btn btn-outline-info btn-sm" style="margin: 5px auto;border: none;float: right">پروفایل</a>
                        </li>
                    </ul>
                </div>
                <div th:if="${#httpSession.getAttribute('shopEmail') != null}">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <form th:action="@{/destroy}" class="logout" method="post" style="margin: 5px auto">
                                <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
                                <input type="submit" value="خروج" class="nav-link btn btn-outline-info btn-sm" style="border: none" >
                            </form>
                        </li>
                        <li class="nav-item">
                            <a href="/shopProfile" class="nav-link btn btn-outline-info btn-sm" style="margin: 5px auto;border: none;float: right">پروفایل</a>
                        </li>
                    </ul>
                </div>
                <div th:unless="${#httpSession.getAttribute('userEmail') != null} or ${#httpSession.getAttribute('shopEmail') != null}">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link btn btn-outline-info btn-sm" style="margin: 5px auto;border: none;float: right" href="/loginPage">ورود</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn btn-outline-info btn-sm" style="margin: 5px auto;border: none;float: right" href="/userRegister">عضویت کاربر</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn btn-outline-info btn-sm" style="margin: 5px auto;border: none;float: right" href="/shopRegister">عضویت فروشگاه</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div th:unless="${#httpSession != null}">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link btn btn-outline-info btn-sm" style="margin: 5px auto;border: none;float: right" href="/loginPage">ورود</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link btn btn-outline-info btn-sm" style="margin: 5px auto;border: none;float: right" href="/userRegister">عضویت کاربر</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link btn btn-outline-info btn-sm" style="margin: 5px auto;border: none;float: right" href="/shopRegister">عضویت فروشگاه</a>
                    </li>
                </ul>
            </div>
        </div>
        <!--<div>-->
            <!--<form class="form-inline" action="" style="margin: 5px auto">-->
                <!--<input class="form-control mr-sm-2" type="text" placeholder="عبارت مورد نظر">-->
                <!--<button class="btn btn-success" type="submit">جست و جو</button>-->
            <!--</form>-->
        <!--</div>-->
    </nav>
    <section>
        <div class="alert alert-info" style="text-align: right">
            <strong>نکته: </strong>
            &nbsp;
            <span>همزمان نمیتوانید از محصولات بیش از یک فروشگاه در سبد خرید خود اضافه کنید.</span>
            &nbsp;
            <span>اگر میخواهید از چند فروشگاه خرید انجام دهید برای هر فروشگاه جداگانه ثبت سفارش انجام دهید.</span>
        </div>
        <div style="display: flex;background-color: #343a40">
            <p class="col-5 col-sm-3 col-md-3 col-lg-2" style="color: white;margin-top: 1%">مرتب سازی براساس: </p>
            <form method="get" th:action="@{/filter}" class="form-inline col-7 col-md-9" style="margin: auto;width: 100%">
                <input list="states" name="state" maxlength="30" placeholder="استان" class="form-control col-12 col-sm-3 col-md-2 col-xl-1" style="margin-left: 2%">
                <datalist id="states">
                    <option value="تهران">
                    <option value="اصفهان">
                    <option value="شیراز">
                    <option value="اردبیل">
                    <option value="مشهد">
                </datalist>
                <input list="city" maxlength="30" name="city" placeholder="شهر" class="form-control col-12 col-sm-3 col-md-2 col-xl-1" style="margin-left: 2%">
                <datalist id="city">
                    <option value="تهران">
                    <option value="اصفهان">
                    <option value="شیراز">
                    <option value="اردبیل">
                    <option value="مشهد">
                </datalist>
                <input list="sort" maxlength="30" placeholder="ترتیب" name="sort" class="form-control col-12 col-sm-3 col-md-2 col-xl-1" style="margin-left: 2%">
                <datalist id="sort">
                    <option value="پربازدیدترین">
                    <option value="جدیدترین">
                </datalist>
                <button type="submit" class="btn btn-outline-info col-12 col-sm-3 col-md-2 col-xl-1">فیلتر</button>
            </form>
        </div>
        <div style="margin: auto" class="col-11">
            <div th:if="${not #lists.isEmpty(shops)}">
                <div th:each="shop : ${shops}">
                    <div class="card my-card col-xl-3 col-lg-3 col-md-5 col-10" style="width:100%;background-color: #50565c;height: 435px;padding: 0;margin: 0">
                        <a th:href="@{/goToShop(shopEmail=${shop.email})}">
                            <img class="card-img-top img-thumbnail" th:src="${shop.shopImageAddress}" alt="Card image" style="height: 250px">
                        </a>
                        <div class="card-body cardBody" style="color: white">
                            <h4 class="card-title" th:text="${'فروشگاه  ' + shop.shopName}"></h4>
                            <p class="card-text" th:text="${'استان_شهر: ' + shop.state + '_' + shop.city}"></p>
                            <div th:if="${shop.deliveryMan}">
                                <p class="card-text" th:text="${'نشانی: ' + shop.address + ' (ارسال با پیک)'}"></p>
                            </div>
                            <div th:unless="${shop.deliveryMan}">
                                <p class="card-text" th:text="${'نشانی: ' + shop.address}"></p>
                            </div>
                            <div th:if="${shop.score != 0}">
                                <p class="card-text" th:text="${'نمره: ' + shop.score}"></p>
                            </div>
                            <div class="clearFix"></div>
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
<div class="clearFix"></div>
</body>

</html>
<script>
</script>
<style>
    .carousel-inner img {
        width: 100%;
        height: 100%;
    }
</style>