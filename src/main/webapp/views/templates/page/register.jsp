<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Ký</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://unpkg.com/bs-brain@2.0.4/components/registrations/registration-3/assets/css/registration-3.css">
    <style>
        /* Đặt màu nền cho cả trang */
        body {
            background-color: #f8f9fa; /* Màu nền cho toàn bộ trang */
        }

        /* Màu nền cho phần bên trái */
        .bsb-tpl-bg-platinum {
            background-color: #f0f8ff; /* Màu nền cho phần bên trái */
        }

        /* Màu nền cho phần bên phải */
        .bsb-tpl-bg-lotion {
            background-color: #ffffff; /* Màu nền cho phần bên phải */
        }

        .bsb-tpl-bg-platinum img {
            width: 80%;
            height: auto; /* Giữ tỷ lệ hình ảnh */
            max-height: 80%; /* Giới hạn chiều cao hình ảnh */
            object-fit: cover; /* Đảm bảo hình ảnh đầy đủ chiều rộng */
        }

        .d-flex.flex-column.justify-content-between {
            height: 100%;
        }
    </style>
</head>
<body>
<%--Header--%>
<jsp:include page="../../templates/components/header.jsp"/>

<!-- Main-->
<!-- Registration 3 - Bootstrap Brain Component -->
<section class="p-3 p-md-4 p-xl-5">
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-6 bsb-tpl-bg-platinum">
                <div class="d-flex flex-column justify-content-between h-100 p-3 p-md-4 p-xl-5">
                    <img class="img-fluid rounded mx-auto my-4" loading="lazy" src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/img1.webp" width="245" height="80" alt="BootstrapBrain Logo">
                </div>
            </div>
            <div class="col-12 col-md-6 bsb-tpl-bg-lotion">
                <div class="p-3 p-md-4 p-xl-5">
                    <div class="row">
                        <div class="col-12">
                            <div class="mb-5">
                                <h2 class="h3">Registration</h2>
                                <h3 class="fs-6 fw-normal text-secondary m-0">Enter your details to register</h3>
                            </div>
                        </div>
                    </div>
                    <form action="#!">
                        <div class="row gy-3 gy-md-4 overflow-hidden">
                            <div class="col-12">
                                <label for="firstName" class="form-label">First Name <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" name="firstName" id="firstName" placeholder="First Name" required>
                            </div>
                            <div class="col-12">
                                <label for="lastName" class="form-label">Last Name <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Last Name" required>
                            </div>
                            <div class="col-12">
                                <label for="email" class="form-label">Email <span class="text-danger">*</span></label>
                                <input type="email" class="form-control" name="email" id="email" placeholder="name@example.com" required>
                            </div>
                            <div class="col-12">
                                <label for="password" class="form-label">Password <span class="text-danger">*</span></label>
                                <input type="password" class="form-control" name="password" id="password" value="" required>
                            </div>
                            <div class="col-12">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="" name="iAgree" id="iAgree" required>
                                    <label class="form-check-label text-secondary" for="iAgree">
                                        I agree to the <a href="#!" class="link-primary text-decoration-none">terms and conditions</a>
                                    </label>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="d-grid">
                                    <button class="btn bsb-btn-xl btn-primary" type="submit">Sign up</button>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="row">
                        <div class="col-12">
                            <hr class="mt-5 mb-4 border-secondary-subtle">
                            <p class="m-0 text-secondary text-end">Already have an account? <a href="${pageContext.request.contextPath}/views/pages/page/login.jsp" class="link-primary text-decoration-none">Sign in</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%--Footer--%>
<jsp:include page="../../templates/components/footer.jsp"/>
</body>
</html>
