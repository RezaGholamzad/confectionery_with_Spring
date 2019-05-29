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
    <!--Fontawesome CDN-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

    <title>شیرینی</title>
</head>

<body class="container-fluid">
<div id="app">
    <div class="container">
        <div class="d-flex justify-content-center h-100">
            <div class="card">
                <div class="card-header">
                    <h3>ورود</h3>
                </div>
                <div class="card-body">
                    <form method="post" th:action="@{/loginPage}">
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="input-label-email"><i class="fas fa-user"></i></span>
                            </div>
                            <input type="email" name="email" class="form-control" id="input-email" placeholder="ایمیل">
                        </div>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="input-label-password"><i class="fas fa-key"></i></span>
                            </div>
                            <input type="password" name="password" class="form-control" id="input-pass" placeholder="پسورد">
                        </div>
                        <div class="row align-items-center remember" style="margin: auto auto 10px;">
                            <input type="checkbox" name="rememberMe">مرا به خاطر بسپار
                        </div>
                        <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
                        <div class="form-group">
                            <input type="submit" value="ورود" class="btn float-right login_btn">
                            <div th:if="${#httpServletRequest.getParameter('loginStatus') == 'failed'}">
                                <p  id="loginStatus">ایمیل یا پسورد اشتباه است</p>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="card-footer">
                    <div class="d-flex justify-content-center links">
                        برای ساخت اکانت جدید <a href="/userRegister">عضویت</a>
                    </div>
                    <div class="d-flex justify-content-center">
                        <a href="#" data-toggle="modal" data-target="#myModal">یاداوری پسورد</a>
                    </div>
                    <div th:if="${#httpServletRequest.getParameter('sendStatus') == 'success'}">
                        <p class="statusEmail">پسورد به ایمیل وارد شده ارسال شد</p>
                    </div>
                    <div th:if="${#httpServletRequest.getParameter('sendStatus') == 'fail'}">
                        <p class="statusEmail">ایمیل وارد شده وجود ندارد</p>
                    </div>
                    <div class="modal fade" id="myModal">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <!-- Modal Header -->
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <!--<h4 class="modal-title">ورود</h4>-->
                                </div>
                                <!-- Modal body -->
                                <div class="modal-body">
                                    <form method="post" th:action="@{/sendEmail}" >
                                        <div class="form-group">
                                            <label for="email">پسورد به ایمیل شما ارسال میشود </label>
                                            <input type="email" class="form-control" id="email" name="email" placeholder="ایمیل">
                                        </div>
                                        <input type="submit" value="تایید" style="float: right">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>
<script>
    new Vue({
        el: '#app',
        data : {
          email:''
        }
    });
</script>

<style scoped>
    html,
    body {
        background-image: url("/static image/userRegister.jpg");
        background-size: cover;
        background-repeat: no-repeat;
        height: 100%;
    }

    .container {
        height: 100%;
        align-content: center;
    }

    #input-label-email {
        border-radius: 0 5px 5px 0;
    }

    #input-label-password {
        border-radius: 0 5px 5px 0;
    }

    #input-pass {
        border-radius: 5px 0 0 5px;
    }

    #input-email {
        border-radius: 5px 0 0 5px;
    }

    .card {
        height: 370px;
        margin-top: auto;
        margin-bottom: auto;
        width: 400px;
        background-color: rgba(0, 0, 0, 0.5) !important;
    }

    .social_icon span {
        font-size: 60px;
        margin-left: 10px;
        color: #FFC312;
    }

    .social_icon span:hover {
        color: white;
        cursor: pointer;
    }

    .card-header h3 {
        color: white;
        float: right;
    }

    .input-group-prepend span {
        width: 50px;
        background-color: #FFC312;
        color: black;
        border: 0 !important;
    }

    .remember {
        color: white;
    }

    .remember input {
        width: 20px;
        height: 20px;
        margin-left: 15px;
        margin-right: 5px;
    }

    .login_btn {
        color: black;
        background-color: #FFC312;
        width: 100px;
    }

    .login_btn:hover {
        color: black;
        background-color: white;
    }

    .links {
        color: white;
    }

    .links a {
        margin-left: 4px;
    }
    .statusEmail{
        text-align: center;
        color: red;
    }
    #loginStatus{
        text-align: center;
        color: red;
    }
</style>