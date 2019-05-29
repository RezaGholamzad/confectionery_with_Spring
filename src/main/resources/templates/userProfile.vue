<html lang="en" dir="rtl" xmlns:th="http://www.thymeleaf.org">
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
    <link rel="stylesheet" type="text/css" href="/css/app.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!--Vue js-->
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.7/dist/vue.js"></script>
    <title>شیرینی</title>
</head>
<body class="container-fluid register">
<div id="app">
    <ul class="nav nav-tabs">
        <li class="active"><a href="#home" data-toggle="tab">پروفایل</a></li>
        <li><a href="#profile" data-toggle="tab">تغییر پسورد</a></li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active in" id="home">
            <form method="post" class="col-md-9" th:action="@{/changeProfile}" th:object="${user}">
                <div class="col-md-12 register-right" style="opacity: 0.9">
                    <h3 class="register-heading">پروفایل</h3>
                    <p th:if="${#httpServletRequest.getParameter('changeStatus') == 'success'}" class="messagePassword">پسورد شما تغییر کرد</p>
                    <p th:if="${#httpServletRequest.getParameter('changeStatus') == 'notValidConfirmPassword'}" class="messagePassword">تکرار پسورد شما اشتباه بود و پسورد تغییری نکرد</p>
                    <p th:if="${#httpServletRequest.getParameter('changeStatus') == 'wrongPassword'}" class="messagePassword">پسورد وارد شده اشتباه بود و رمز تغییری نکرد</p>
                    <div class="row register-form">
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="text"  class="form-control" maxlength="30"  placeholder="نام" th:field="*{firstName}">
                                <p th:if="${#fields.hasErrors('firstName')}" th:errors="*{firstName}" class="error"></p>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" maxlength="30" placeholder="نام خانوادگی" th:field="*{lastName}">
                                <p th:if="${#fields.hasErrors('lastName')}" th:errors="*{lastName}" class="error"></p>
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" placeholder="رمز" th:field="*{password}">
                                <p th:if="${#fields.hasErrors('password')}" th:errors="*{password}" class="error"></p>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" maxlength="10" minlength="10" placeholder="کد ملی" th:field="*{nationalCode}">
                                <p th:if="${#fields.hasErrors('nationalCode')}" th:errors="*{nationalCode}"  class="error"></p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="email" class="form-control" maxlength="100" placeholder="ایمیل" th:field="*{email}">
                                <p th:if="${#fields.hasErrors('email')}" th:errors="*{email}" class="error"></p>
                                <p th:if="${emailStatus == 'duplicate'}" class="error">ایمیل تکراری است</p>
                            </div>
                            <div class="form-group">
                                <input type="tel" class="form-control" maxlength="11" minlength="11" placeholder="شماره موبایل" th:field="*{phone}">
                                <p th:if="${#fields.hasErrors('phone')}" th:errors="*{phone}" class="error"></p>
                            </div>
                            <div class="form-group">
                                <input list="states" maxlength="30" placeholder="استان" class="form-control" th:field="*{state}">
                                <datalist id="states">
                                    <option value="تهران">
                                    <option value="اصفهان">
                                    <option value="شیراز">
                                    <option value="اردبیل">
                                    <option value="مشهد">
                                </datalist>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" maxlength="30" placeholder="شهر" th:field="*{city}">
                            </div>
                            <div class="form-group">
                                <textarea class="form-control" rows="5" maxlength="200" placeholder="ادرس" th:field="*{address}"></textarea>
                            </div>
                            <input type="submit" class="btnRegister"  value="ثبت"/>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="tab-pane fade" id="profile">
            <form id="tab2" class="col-md-9" method="post" th:action="@{/changePassword}">
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="رمز فعلی" name="currentPassword">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="رمز جدید" name="newPassword" v-model="newPassword" @change="check_password">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="تکرار رمز جدید" name="confirmNewPassword" v-model="confirmNewPassword" @change="check_confirmPassword">
                </div>
                <div>
                    <button type="submit" class="btn btn-primary">تغییر</button>
                </div>
            </form>
        </div>
    </div>

</div>
<script>
    new Vue({
        el: '#app',
        data : {
            newPassword: '',
            confirmNewPassword: ''
        },
        methods : {
            // check_nationalCode : function () {
            //     var code = parseInt(this.nationalCode);
            //
            //     if (this.nationalCode.length !== 10){
            //         this.nationalCode='';
            //         alert("کد ملی وارد شده صحیح نیست");
            //         return false;
            //     }
            //     var sum = 0;
            //     for (var i = 0 ; i < 9; i++){
            //         sum += parseInt(this.nationalCode[i]) * (10 - i);
            //     }
            //     var temp = sum % 11;
            //     if (temp < 2){
            //         if (parseInt(this.nationalCode[9]) !== temp){
            //             this.nationalCode='';
            //             alert("کد ملی وارد شده صحیح نیست");
            //         }
            //     }
            //     else {
            //         if (parseInt(this.nationalCode[9]) !== (11 - temp)){
            //             this.nationalCode='';
            //             alert("کد ملی وارد شده صحیح نیست");
            //         }
            //     }
            // },
            check_confirmPassword : function () {
                if (this.newPassword.localeCompare(this.confirmNewPassword) !== 0) {
                    alert("تکرار پسورد با پسورد اصلی برابر نیست!");
                    this.newPassword='';
                    this.confirmNewPassword='';
                }
            },
            check_password : function () {
                if (this.confirmNewPassword !== ''){
                    this.check_confirmPassword();
                }
            }
        }
    });
</script>
<style>
    form{
        display: flex;
    }
    body{
        background-image: url("/static image/userRegister.jpg");
        background-size: cover;
    }
    .register{
        margin-top: 3%;
        padding: 3%;
    }
    .register-right{
        background: #f8f9fa;
        border-top-left-radius: 10% 50%;
        border-bottom-left-radius: 10% 50%;
    }
    @-webkit-keyframes mover {
        0% { transform: translateY(0); }
        100% { transform: translateY(-20px); }
    }
    @keyframes mover {
        0% { transform: translateY(0); }
        100% { transform: translateY(-20px); }
    }
    .register .register-form{
        padding: 10%;
        margin-top: 10%;
    }
    .btnRegister{
        float: right;
        margin-top: 10%;
        border: none;
        border-radius: 1.5rem;
        padding: 2%;
        background: #0062cc;
        color: #fff;
        font-weight: 600;
        width: 50%;
        cursor: pointer;
    }
    .register-heading{
        text-align: center;
        margin-top: 8%;
        margin-bottom: -15%;
        color: #495057;
    }
    .error {
        color: red;
        float: right;
    }
    a{
        margin: 40px;
        font-size: large;
        color: white;
    }
    .messagePassword{
        color: red;
        font-size: larger;
        float: right;
    }
</style>
</body>
</html>