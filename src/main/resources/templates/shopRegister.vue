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
    <p></p>
    <form method="post" class="col-md-9" enctype="multipart/form-data" th:action="@{/shopRegisterSubmit}" th:object="${shop}">
        <div class="col-md-12 register-right" style="opacity: 0.9">
            <h3 class="register-heading">ثبت نام</h3>
            <div class="row register-form">
                <div class="col-md-6">
                    <div class="form-group">
                        <input type="text"  class="form-control" maxlength="30" placeholder="نام" th:field="*{shopName}">
                        <p th:if="${#fields.hasErrors('shopName')}" th:errors="*{shopName}" class="error"></p>
                    </div>
                    <div class="form-group">
                        <input type="file" class="form-control custom-file-input-shopImage"  placeholder="عکس فروشگاه"  th:field="*{shopImage}">
                        <p th:if="${#fields.hasErrors('shopImage')}" th:errors="*{shopImage}" class="error"></p>
                    </div>
                    <div class="form-group">
                        <input type="text"  class="form-control" minlength="10" placeholder="شماره پروانه کسب" th:field="*{businessLicense}">
                        <p th:if="${#fields.hasErrors('businessLicense')}" th:errors="*{businessLicense}" class="error"></p>
                    </div>
                    <div class="form-group">
                        <input type="file" class="form-control custom-file-input-businessLicenseImage"  placeholder="عکس پروانه کسب" th:field="*{businessLicenseImage}">
                        <p th:if="${#fields.hasErrors('businessLicenseImage')}" th:errors="*{businessLicenseImage}" class="error"></p>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="رمز" v-model="password" th:field="*{password}" @change="check_password">
                        <p th:if="${#fields.hasErrors('password')}" th:errors="*{password}" class="error"></p>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="تکرار رمز" v-model="confirmPassword" th:field="*{confirmPassword}" @change="check_confirmPassword">
                        <p th:if="${#fields.hasErrors('confirmPassword')}" th:errors="*{confirmPassword}" class="error"></p>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" maxlength="10" minlength="10" placeholder="کد ملی" th:field="*{nationalCode}">
                        <p th:if="${#fields.hasErrors('nationalCode')}" th:errors="*{nationalCode}"  class="error"></p>
                    </div>
                    <div class="form-group">
                        <textarea class="form-control" rows="3" maxlength="200" placeholder="توضیحات (اختیاری)" th:field="*{explanation}"></textarea>
                        <p th:if="${#fields.hasErrors('explanation')}" th:errors="*{explanation}" class="error"></p>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <input type="email" class="form-control" maxlength="100" placeholder="ایمیل" th:field="*{email}">
                        <p th:if="${#fields.hasErrors('email')}" th:errors="*{email}" class="error"></p>
                        <p th:if="${emailStatus == 'duplicate'}" class="error">ایمیل تکراری است</p>
                    </div>
                    <div class="form-group">
                        <input type="tel" class="form-control" placeholder="شماره های فروشگاه" th:field="*{phone}">
                        <p th:if="${#fields.hasErrors('phone')}" th:errors="*{phone}" class="error"></p>
                    </div>
                    <div class="form-group">
                        <input list="states" maxlength="30" placeholder="استان" class="form-control" th:field="*{state}">
                        <datalist id="states">
                            <option value="تهران">
                            <option value="سمنان">
                        </datalist>
                        <p th:if="${#fields.hasErrors('state')}" th:errors="*{state}" class="error"></p>
                    </div>
                    <div class="form-group">
                        <input list="city" class="form-control" maxlength="30" placeholder="شهر" th:field="*{city}">
                        <datalist id="city">
                            <option value="تهران">
                            <option value="سمنان">
                        </datalist>
                        <p th:if="${#fields.hasErrors('city')}" th:errors="*{city}" class="error"></p>
                    </div>
                    <div class="form-group">
                        <textarea class="form-control" rows="3" maxlength="200" placeholder="ادرس" th:field="*{address}"></textarea>
                        <p th:if="${#fields.hasErrors('address')}" th:errors="*{address}" class="error"></p>
                    </div>
                    <div class="form-group">
                        <textarea class="form-control" rows="2" maxlength="200" placeholder="ساعات کاری فروشگاه" th:field="*{descriptionOfWorkingHours}"></textarea>
                        <p th:if="${#fields.hasErrors('descriptionOfWorkingHours')}" th:errors="*{descriptionOfWorkingHours}" class="error"></p>
                    </div>
                    <div class="form-group row align-items-center remember">
                        قابلیت ارسال دارید؟<input type="checkbox" placeholder="deliveryMan" id="deliveryMan" class="form-control" th:field="*{deliveryMan}">
                    </div>
                    <input type="submit" class="btnRegister"  value="ثبت"/>
                </div>
            </div>
        </div>
    </form>
</div>
<script>
    new Vue({
        el: '#app',
        data : {
            password : '',
            confirmPassword:'',
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
                if (this.password.localeCompare(this.confirmPassword) !== 0) {
                    alert("تکرار پسورد با پسورد اصلی برابر نیست!");
                    this.password='';
                    this.confirmPassword='';
                }
            },
            check_password : function () {
                if (this.confirmPassword !== ''){
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
    #deliveryMan{
        width: 6%;
        margin-right: 10px;
        margin-top: 5px;
    }
    .custom-file-input-shopImage::-webkit-file-upload-button {
         visibility: hidden;
     }
    .custom-file-input-shopImage::before {
        content: 'عکس فروشگاه';
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
    .custom-file-input-shopImage:hover::before {
        border-color: black;
    }
    .custom-file-input-shopImage:active::before {
        background: -webkit-linear-gradient(top, #e3e3e3, #f9f9f9);
    }
    .custom-file-input-businessLicenseImage::-webkit-file-upload-button {
        visibility: hidden;
    }
    .custom-file-input-businessLicenseImage::before {
        content: 'عکس پروانه کسب';
        background: linear-gradient(top, #f9f9f9, #e3e3e3);
        border: 1px solid #999;
        border-radius: 3px;
        margin-right: -11px;
        padding: 5px 0;
        vertical-align: super;
        outline: none;
        white-space: nowrap;
        -webkit-user-select: none;
        cursor: pointer;
        text-shadow: 1px 1px #fff;
        font-weight: 100;
        font-size: 10pt;
    }
    .custom-file-input-businessLicenseImage:hover::before {
        border-color: black;
    }
    .custom-file-input-businessLicenseImage:active::before {
        background: -webkit-linear-gradient(top, #e3e3e3, #f9f9f9);
    }
</style>
</body>
</html>