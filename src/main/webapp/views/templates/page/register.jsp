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
    <!-- SweetAlert CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
    <!-- SweetAlert JS -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
    <h5>
        <c:if test="${not empty sessionScope.message}">
            <script>
                Swal.fire({
                    icon: 'success',
                    title: 'Success',
                    text: '${sessionScope.message}',
                    showConfirmButton: true
                });
                <c:remove var="message" scope="session" />
            </script>
        </c:if>

        <c:if test="${not empty sessionScope.errorMessage}">
            <script>
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: '${sessionScope.errorMessage}',
                    showConfirmButton: true
                });
                <c:remove var="errorMessage" scope="session" />
            </script>
        </c:if>
    </h5>
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
                                <h2 class="h3">Đăng Ký</h2>
                                <h3 class="fs-6 fw-normal text-secondary m-0">Nhập thông tin của bạn để đăng ký</h3>
                            </div>
                        </div>
                    </div>
                    <form action="${pageContext.request.contextPath}/register" method="post">
                        <div class="row gy-3 gy-md-4 overflow-hidden">
                            <div class="col-12">
                                <label for="username" class="form-label">Username <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" name="username" id="username" placeholder="Username" required>
                            </div>
                            <div class="col-12">
                                <label for="fullname" class="form-label">Username <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" name="fullname" id="fullname" placeholder="Username" required>
                            </div>
                            <div class="col-12">
                                <label for="email" class="form-label">Email <span class="text-danger">*</span></label>
                                <input type="email" class="form-control" name="email" id="email" placeholder="name@example.com" required>
                            </div>
                            <div class="col-12">
                                <label for="password" class="form-label">Mật khẩu <span class="text-danger">*</span></label>
                                <input type="password" class="form-control" name="password" id="password" required>
                            </div>
                            <div class="col-12">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="" name="iAgree" id="iAgree" required>
                                    <label class="form-check-label text-secondary" for="iAgree">
                                        Tôi đồng ý với <a href="#!" class="link-primary text-decoration-none">các điều khoản và điều kiện</a>
                                    </label>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="d-grid">
                                    <button class="btn bsb-btn-xl btn-primary" type="submit">Đăng ký</button>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="row">
                        <div class="col-12">
                            <hr class="mt-5 mb-4 border-secondary-subtle">
                            <p class="m-0 text-secondary text-end">Đã có tài khoản? <a href="${pageContext.request.contextPath}/views/pages/page/login.jsp" class="link-primary text-decoration-none">Đăng nhập</a></p>
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
