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
        <a class="navbar-brand" th:href="@{/}">Logo</a>
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
                    <a  href="#" data-toggle="modal" data-target="#modelForCake" style="margin: 5px auto;border: none;float: right" class="nav-link btn btn-outline-info btn-sm">اضافه کردن کیک</a>
                </li>
                <li class="nav-item">
                    <a href="#" data-toggle="modal" data-target="#modelForSweet" style="margin: 5px auto;border: none;float: right" class="nav-link btn btn-outline-info btn-sm">اضافه کردن شیرینی</a>
                </li>
                <li class="nav-item">
                    <a th:href="@{/manageOrders}" style="margin: 5px auto;border: none;float: right" class="nav-link btn btn-outline-info btn-sm">مشاهده سفارشات</a>
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
        <!--modal for add cake-->
    <div class="modal fade" id="modelForCake">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <!--<h4 class="modal-title">ورود</h4>-->
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <form method="post" enctype="multipart/form-data" th:action="@{/cakeRegistration}">
                        <div class="form-group">
                            <input type="text" placeholder="نام کیک" class="form-control" name="name">
                        </div>
                        <div class="form-group">
                            <input type="file" class="form-control  custom-file-input" id="productImageCake" name="productImage">
                        </div>
                        <div class="form-group">
                            <input type="number" placeholder="قیمت کیک" class="form-control" name="price">
                        </div>
                        <div class="form-group">
                            <textarea class="form-control" rows="5" maxlength="200" placeholder="توضیحات" name="explanation"></textarea>
                        </div>
                        <input type="submit" value="ثبت" style="float: right">
                    </form>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">بستن</button>
                </div>
            </div>
        </div>
    </div>

    <!--modal for add sweet-->
    <div class="modal fade" id="modelForSweet">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <!--<h4 class="modal-title">ورود</h4>-->
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <form method="post" enctype="multipart/form-data" th:action="@{/sweetRegistration}">
                        <div class="form-group">
                            <input type="text" placeholder="نام شیرینی" class="form-control" name="name">
                        </div>
                        <div class="form-group">
                            <input type="file" class="form-control  custom-file-input" id="productImageSweet" name="productImage">
                        </div>
                        <div class="form-group">
                            <input type="number" placeholder="قیمت هر کیلو" class="form-control" name="price">
                        </div>
                        <div class="form-group">
                            <input type="number" placeholder="تعداد تقریبی در هر کیلو" class="form-control" name="numberInKilo">
                        </div>
                        <div class="form-group">
                            <textarea class="form-control" rows="5" maxlength="200" placeholder="توضیحات" name="explanation"></textarea>
                        </div>
                        <input type="submit" value="ثبت" style="float: right">
                    </form>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">بستن</button>
                </div>
            </div>
        </div>
    </div>
    <section>
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
            <div class="tab-pane active in" id="cake">
                <div style="margin: auto" class="col-11">
                    <div th:if="${not #lists.isEmpty(products)}">
                        <div th:each="product : ${products}">
                            <div th:if="${product.cake}">
                                <div class="card my-card col-xl-3 col-lg-3 col-md-5 col-10" style="width:100%;background-color: #50565c;min-height: 510px;padding: 0;margin: 0">
                                    <img class="card-img-top img-thumbnail" th:src="${product.productImageAddress}" alt="Card image" style="height: 250px">
                                    <div class="card-body cardBody" style="color: white">
                                        <div th:if="${product.discountCode != null}">
                                            <div class="offer">
                                                <p class="inside-offer" th:text="${product.discountCode.percentage + '%'}"></p>
                                                <p class="discount" >تخفیف</p>
                                            </div>
                                        </div>
                                        <h4 class="card-title" data-toggle="tooltip" th:title="${product.explanation}" th:text="${product.name}"></h4>
                                        <div th:if="${product.discountCode != null}">
                                            <p class="card-text" style="margin-bottom: 10px;">
                                                قیمت:
                                                <strike style="color: red"><span style="color: white" th:text="${product.price}"></span></strike>
                                                &nbsp;	&nbsp;
                                                <span th:text="${product.price * (100 - product.discountCode.percentage)/100}"></span>
                                                &nbsp;
                                                تومان
                                            </p>
                                        </div>
                                        <div th:unless="${product.discountCode != null}">
                                            <p class="card-text" style="margin-bottom: 10px;">
                                                <span>قیمت:</span>
                                                &nbsp;
                                                <span class="card-text" th:text="${product.price}"></span>
                                                &nbsp;
                                                <span>تومان</span>
                                            </p>
                                        </div>
                                        <div th:if="${product.discountCode == null}">
                                            <!--<h6 class="card-title" style="float: right">ثبت کد تخفیف: </h6>-->
                                            <form method="post" th:action="@{/setDiscountCode}">
                                                <div class="form-inline" style="float: left">
                                                    <input type="hidden" placeholder="" name="productCode" th:value="${product.code}">
                                                    <input type="number" placeholder="درصد تخفیف(ضروری)" name="percentage" class="form-control">
                                                </div>
                                                <div class="form-inline" style="float: left">
                                                    <input type="number" placeholder="تعداد روزهای اعتبار" name="expirationDate" class="form-control">
                                                </div>
                                                <div class="form-inline" style="float: left">
                                                    <input type="number" placeholder="تعداد محدود" name="numberLimit" class="form-control">
                                                </div>
                                                <input type="submit" value="ثبت تخفیف" class="btn btn-outline-info btn-sm">
                                            </form>
                                        </div>
                                        <div th:unless="${product.discountCode == null}">
                                            <form method="post" th:action="@{/deleteDiscountCode}">
                                                <div class="form-group" hidden>
                                                    <input type="hidden" placeholder="" name="productCode"  th:value="${product.code}">
                                                </div>
                                                <input type="submit" value="حذف تخفیف" class="btn btn-outline-danger btn-sm">
                                            </form>
                                        </div>
                                        <div class="clearFix"></div>
                                        <form method="post" th:action="@{/productDelete}">
                                            <div class="form-group" hidden>
                                                <input type="hidden" placeholder="" name="productCode"  th:value="${product.code}">
                                            </div>
                                            <input type="submit" value="حذف" class="btn btn-outline-danger btn-sm">
                                        </form>
                                        <div th:if="${product.available}">
                                            <form method="post" th:action="@{/notAvailable}">
                                                <div class="form-group" hidden>
                                                    <input type="hidden" placeholder="" name="productCode"  th:value="${product.code}">
                                                </div>
                                                <input type="submit" value="ناموجود" class="btn btn-outline-warning btn-sm">
                                            </form>
                                        </div>
                                        <div th:unless="${product.available}">
                                            <form method="post" th:action="@{/available}">
                                                <div class="form-group" hidden>
                                                    <input type="hidden" placeholder="" name="productCode"  th:value="${product.code}">
                                                </div>
                                                <input type="submit" value="موجود" class="btn btn-outline-warning btn-sm">
                                            </form>
                                        </div>
                                        <a href="#" data-toggle="modal" data-target="#modelForEditCake" class="nav-link btn btn-outline-info btn-sm edit" style="float: left">ویرایش
                                            <input type="hidden" placeholder="" th:value="${product.code}">
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="tab-pane fade" id="sweet">
                <div style="margin: auto" class="col-11">
                    <div th:if="${not #lists.isEmpty(products)}">
                        <div th:each="product : ${products}">
                            <div th:if="${!product.cake}">
                                <div class="card my-card col-xl-3 col-lg-3 col-md-5 col-10" style="width:100%;background-color: #50565c;min-height: 510px;padding: 0;margin: 0">
                                    <img class="card-img-top img-thumbnail" th:src="${product.productImageAddress}" alt="Card image" style="height: 250px">
                                    <div class="card-body cardBody" style="color: white">
                                        <div th:if="${product.discountCode != null}">
                                            <div class="offer">
                                                <p class="inside-offer" th:text="${product.discountCode.percentage + '%'}"></p>
                                                <p class="discount" >تخفیف</p>
                                            </div>
                                        </div>
                                        <h4 style="display: inline" class="card-title" data-toggle="tooltip" th:title="${product.explanation}" th:text="${product.name}"></h4>
                                        &nbsp;
                                        <span>(</span>
                                        <span th:text="${product.numberInKil}"></span>
                                        &nbsp;
                                        <span>عدد در هر کیلو</span>
                                        <span>)</span>
                                        <div style="margin-top: 10px" th:if="${product.discountCode != null}">
                                            <p class="card-text" style="margin-bottom: 10px;">
                                                قیمت:
                                                <strike style="color: red"><span style="color: white" th:text="${product.price}"></span></strike>
                                                &nbsp;	&nbsp;
                                                <span th:text="${product.price * (100 - product.discountCode.percentage)/100}"></span>
                                                &nbsp;
                                                تومان
                                            </p>
                                        </div>
                                        <div style="margin-top: 10px" th:unless="${product.discountCode != null}">
                                            <p class="card-text" style="margin-bottom: 10px;">
                                                <span>قیمت:</span>
                                                &nbsp;
                                                <span class="card-text" th:text="${product.price}"></span>
                                                &nbsp;
                                                <span>تومان</span>
                                            </p>
                                        </div>
                                        <div th:if="${product.discountCode == null}">
                                            <!--<h6 class="card-title" style="float: right">ثبت کد تخفیف: </h6>-->
                                            <form method="post" th:action="@{/setDiscountCode}">
                                                <div class="form-inline" style="float: left">
                                                    <input type="hidden" placeholder="" name="productCode" th:value="${product.code}">
                                                    <input type="number" placeholder="درصد تخفیف(ضروری)" name="percentage" class="form-control">
                                                </div>
                                                <div class="form-inline" style="float: left">
                                                    <input type="number" placeholder="تعداد روزهای اعتبار" name="expirationDate" class="form-control">
                                                </div>
                                                <div class="form-inline" style="float: left">
                                                    <input type="number" placeholder="تعداد محدود" name="numberLimit" class="form-control">
                                                </div>
                                                <input type="submit" value="ثبت تخفیف" class="btn btn-outline-info btn-sm">
                                            </form>
                                        </div>
                                        <div th:unless="${product.discountCode == null}">
                                            <form method="post" th:action="@{/deleteDiscountCode}">
                                                <div class="form-group" hidden>
                                                    <input type="hidden" placeholder="" name="productCode"  th:value="${product.code}">
                                                </div>
                                                <input type="submit" value="حذف تخفیف" class="btn btn-outline-danger btn-sm">
                                            </form>
                                        </div>
                                        <div class="clearFix"></div>
                                        <form method="post" th:action="@{/productDelete}">
                                            <div class="form-group" hidden>
                                                <input type="hidden" placeholder="" name="productCode"  th:value="${product.code}">
                                            </div>
                                            <input type="submit" value="حذف" class="btn btn-outline-danger btn-sm">
                                        </form>
                                        <div th:if="${product.available}">
                                            <form method="post" th:action="@{/notAvailable}">
                                                <div class="form-group" hidden>
                                                    <input type="hidden" placeholder="" name="productCode"  th:value="${product.code}">
                                                </div>
                                                <input type="submit" value="ناموجود" class="btn btn-outline-warning btn-sm">
                                            </form>
                                        </div>
                                        <div th:unless="${product.available}">
                                            <form method="post" th:action="@{/available}">
                                                <div class="form-group" hidden>
                                                    <input type="hidden" placeholder="" name="productCode"  th:value="${product.code}">
                                                </div>
                                                <input type="submit" value="موجود" class="btn btn-outline-warning btn-sm">
                                            </form>
                                        </div>
                                        <a href="#" data-toggle="modal" data-target="#modelForEditSweet" class="nav-link btn btn-outline-info btn-sm edit" style="float: left">ویرایش محصول
                                            <input type="hidden" placeholder="" th:value="${product.code}">
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!--modal for edit cake-->
            <div class="modal fade" id="modelForEditCake">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <!-- Modal Header -->
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">فقط فیلدهایی که میخواهید تغییر کنند را پر کنید</h4>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <form method="post" enctype="multipart/form-data" th:action="@{/cakeEditRegistration}">
                                <input type="hidden" placeholder=""  class="productCode"  value="" name="productCode">
                                <div class="form-group">
                                    <input type="text" placeholder="نام کیک" class="form-control" name="name">
                                </div>
                                <div class="form-group">
                                    <input type="file" class="form-control  custom-file-input" id="productImageEditCake" name="productImage">
                                </div>
                                <div class="form-group">
                                    <input type="number" placeholder="قیمت کیک" class="form-control" name="price">
                                </div>
                                <div class="form-group">
                                    <textarea class="form-control" rows="5" maxlength="200" placeholder="توضیحات" name="explanation"></textarea>
                                </div>
                                <input type="submit" value="ثبت" style="float: right">
                            </form>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-dismiss="modal">بستن</button>
                        </div>
                    </div>
                </div>
            </div>

            <!--modal for edit sweet-->
            <div class="modal fade" id="modelForEditSweet">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <!-- Modal Header -->
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">فقط فیلدهایی که میخواهید تغییر کنند را پر کنید</h4>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <form method="post" enctype="multipart/form-data" th:action="@{/sweetEditRegistration}">
                                <input type="hidden" placeholder=""  class="productCode"  value="" name="productCode">
                                <div class="form-group">
                                    <input type="text" placeholder="نام شیرینی" class="form-control" name="name">
                                </div>
                                <div class="form-group">
                                    <input type="file" class="form-control  custom-file-input" id="productImageEditSweet" name="productImage">
                                </div>
                                <div class="form-group">
                                    <input type="number" placeholder="قیمت هر کیلو" class="form-control" name="price">
                                </div>
                                <div class="form-group">
                                    <input type="number" placeholder="تعداد تقریبی در هر کیلو" class="form-control" name="numberInKilo">
                                </div>
                                <div class="form-group">
                                    <textarea class="form-control" rows="5" maxlength="200" placeholder="توضیحات" name="explanation"></textarea>
                                </div>
                                <input type="submit" value="ثبت" style="float: right">
                            </form>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-dismiss="modal">بستن</button>
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