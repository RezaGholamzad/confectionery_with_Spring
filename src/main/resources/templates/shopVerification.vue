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
            </ul>
        </div>
    </nav>
    <div class="clearFix"></div>
    <section>
          <table class="table table-dark table-hover">
              <thead>
              <tr>
                  <th>نام فروشگاه</th>
                  <th>استان</th>
                  <th>شهر</th>
                  <th>ادرس</th>
                  <th>شماره های تماس</th>
                  <th>ایمیل</th>
                  <th>توضیحات</th>
                  <th>کد ملی</th>
                  <th>شماره پروانه کسب</th>
                  <th>ساعات کاری</th>
                  <th>فروشگاه</th>
                  <th>پروانه کسب</th>
                  <th>وضعیت</th>
              </tr>
              </thead>
              <tbody>
              <div th:if="${not #lists.isEmpty(shopsNotValidate)}">
                  <div th:each="shop : ${shopsNotValidate}">
                      <tr>
                          <td>
                              <a th:href="@{/goToShop(shopEmail=${shop.email})}" th:text="${shop.shopName}"></a>
                          </td>
                          <td th:text="${shop.state}"></td>
                          <td th:text="${shop.city}"></td>
                          <td th:text="${shop.address}" style="max-width: 100px"></td>
                          <td>
                              <div th:each="shopPhone : ${shop.shopPhones}">
                                  <p class="card-text" style="float: right" th:text="${shopPhone.phone}"></p>
                                  <br>
                              </div>
                          </td>
                          <td th:text="${shop.email}"></td>
                          <td th:text="${shop.explanation}" style="max-width: 100px"></td>
                          <td th:text="${shop.nationalCode}"></td>
                          <td th:text="${shop.businessLicense}"></td>
                          <td th:text="${shop.descriptionOfWorkingHours}" style="max-width: 100px"></td>
                          <td>
                              <form method="get" th:action="@{/shopImageDownload}">
                                  <input type="hidden" name="shopEmail" th:value="${shop.email}" />
                                  <button type="submit" class="btn btn-light">دانلود</button>
                              </form>
                          </td>
                          <td>
                              <form method="get" th:action="@{/businessLicenseImageDownload}">
                                  <input type="hidden" name="shopEmail" th:value="${shop.email}"/>
                                  <button type="submit" class="btn btn-light">دانلود</button>
                              </form>
                          </td>
                          <td>
                              <form method="post" th:action="@{/shopVerification}">
                                  <input type="hidden" name="shopEmail" th:value="${shop.email}"/>
                                  <button type="submit" class="btn btn-outline-success" style="margin-bottom: 10px">تایید</button>
                              </form>
                              <br>
                              <form method="post" th:action="@{/shopDelete}">
                                  <input type="hidden" name="shopEmail" th:value="${shop.email}"/>
                                  <button type="submit" class="btn btn-outline-danger">حذف</button>
                              </form>
                          </td>
                      </tr>
                  </div>
              </div>
              </tbody>
          </table>
        <table class="table table-hover">
        <thead>
        <tr>
            <th>نام فروشگاه</th>
            <th>استان</th>
            <th>شهر</th>
            <th>ادرس</th>
            <th>شماره های تماس</th>
            <th>ایمیل</th>
            <th>توضیحات</th>
            <th>کد ملی</th>
            <th>شماره پروانه کسب</th>
            <th>ساعات کاری</th>
            <th>فروشگاه</th>
            <th>پروانه کسب</th>
            <th>وضعیت</th>
        </tr>
        </thead>
        <tbody>
        <div th:if="${not #lists.isEmpty(shopsValidate)}">
            <div th:each="shop : ${shopsValidate}">
                <tr>
                    <td>
                        <a th:href="${'/shopCheck?shopEmail=' + shop.email}" th:text="${shop.shopName}"></a>
                    </td>
                    <td th:text="${shop.state}"></td>
                    <td th:text="${shop.city}"></td>
                    <td th:text="${shop.address}" style="max-width: 100px"></td>
                    <td>
                        <div th:each="shopPhone : ${shop.shopPhones}">
                            <p class="card-text" style="float: right" th:text="${shopPhone.phone}"></p>
                            <br>
                        </div>
                    </td>
                    <td th:text="${shop.email}"></td>
                    <td th:text="${shop.explanation}" style="max-width: 100px"></td>
                    <td th:text="${shop.nationalCode}"></td>
                    <td th:text="${shop.businessLicense}"></td>
                    <td th:text="${shop.descriptionOfWorkingHours}" style="max-width: 100px"></td>
                    <td>
                        <form method="get" th:action="@{/shopImageDownload}">
                            <input type="hidden" name="shopEmail" th:value="${shop.email}" />
                            <button type="submit" class="btn btn-light">دانلود</button>
                        </form>
                    </td>
                    <td>
                        <form method="get" th:action="@{/businessLicenseImageDownload}">
                            <input type="hidden" name="shopEmail" th:value="${shop.email}" />
                            <button type="submit" class="btn btn-light">دانلود</button>
                        </form>
                    </td>
                    <td>
                        <form method="post" th:action="@{/shopSuspension}">
                            <input type="hidden" name="shopEmail" th:value="${shop.email}"/>
                            <button type="submit" class="btn btn-warning" style="margin-bottom: 10px">تعلیق</button>
                        </form>
                        <br>
                        <form method="post" th:action="@{/shopDelete}">
                            <input type="hidden" name="shopEmail" th:value="${shop.email}"/>
                            <button type="submit" class="btn btn-danger">حذف</button>
                        </form>
                    </td>
                </tr>
            </div>
        </div>
        </tbody>
    </table>
    </section>
</div>

</body>

<script>

</script>

<style>
</style>
