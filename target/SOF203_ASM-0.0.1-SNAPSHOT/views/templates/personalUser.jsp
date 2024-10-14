<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh sửa thông tin cá nhân</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/user.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <!-- SweetAlert CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
    <!-- SweetAlert JS -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>
        /* CSS để điều chỉnh kích thước ảnh đại diện */
        #profileImagePreview {
            max-width: 100px; /* Kích thước tối đa của ảnh đại diện */
            max-height: 100px; /* Chiều cao tối đa của ảnh đại diện */
            border-radius: 5%; /* Bo góc cho ảnh */
        }
    </style>
</head>

<body>
<div id="textEditorBlog">
    <nav class="navbar navbar-expand-lg navbar-light mb-4">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Blog Management</a>
            <input hidden="hidden" disabled name="username" value="${user.username}">
            <h3>Xin chào: ${user.fullname}</h3>
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
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/user/news">Bài viết</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/user/personal">Tài khoản</a> <!-- Trang hiện tại -->
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/index">Chuyển sang trang chính</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <form class="form-personal-info" action="${pageContext.request.contextPath}/user/personal" method="post" enctype="multipart/form-data">
        <!-- Nhập thông tin cá nhân -->
        <div class="mb-4">
            <label for="fullName">Họ và tên</label>
            <input type="text" name="fullName" class="form-control" id="fullName" placeholder="Nhập họ và tên" value="${user.fullname}">
        </div>

        <div class="mb-4">
            <label for="email">Email</label>
            <input type="email" name="email" class="form-control" id="email" placeholder="Nhập email" value="${user.email}" disabled>
        </div>

        <div class="mb-4">
            <label for="phone">Số điện thoại</label>
            <input type="text" name="phone" class="form-control" id="phone" placeholder="Nhập số điện thoại" value="${user.phone}">
        </div>

        <div class="mb-4">
            <label for="dob">Ngày tháng năm sinh</label>
            <input type="date" name="birthday" class="form-control" id="dob" value="${user.birthday}">
        </div>

        <div class="mb-4">
            <label for="oldPassword">Mật khẩu cũ</label>
            <input type="password" name="oldPassword" class="form-control" id="oldPassword" placeholder="Nhập mật khẩu cũ">
        </div>

        <div class="mb-4">
            <label for="newPassword">Mật khẩu mới</label>
            <input type="password" name="newPassword" class="form-control" id="newPassword" placeholder="Nhập mật khẩu mới">
        </div>

<%--        <div class="mb-4">--%>
<%--            <label for="profilePicture">Ảnh đại diện</label>--%>
<%--            <label for="profilePictureInput" style="cursor: pointer;">--%>
<%--                <i id="profile-label-image" class="fa-solid fa-plus"></i>--%>
<%--                <img id="profileImagePreview" src="https://via.placeholder.com/150" alt="Ảnh đại diện" class="img-fluid" style="margin-top: 10px; border-radius: 5%;">--%>
<%--            </label>--%>
<%--            <input type="file" name="profilePicture" class="form-control" id="profilePictureInput" accept="image/*" style="display: none" onchange="previewProfileImage(event)">--%>
<%--        </div>--%>

        <div class="mb-4">
            <label for="verificationCode">Mã xác thực</label>
            <div class="input-group">
                <input type="text" name="verificationCode" class="form-control" id="verificationCode" placeholder="Mã xác thực gồm 6 số">
                <button type="button" class="btn btn-primary" id="sendVerificationCode">Gửi mã xác thực</button>
                <span id="countdown" style="display: none; margin-left: 10px;"></span>
            </div>
        </div>

        <div class="mb-3">
            <button type="submit" class="btn btn-success" name="actionUserPersonal" value="update">Cập nhật thông tin</button>
            <button type="button" class="btn btn-secondary" onclick="resetPersonalForm()">Làm mới</button>
        </div>
    </form>

    <br>
    <br>

</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        // Gán sự kiện click cho nút gửi mã xác thực
        document.getElementById('sendVerificationCode').addEventListener('click', sendVerificationCode);

        // Gán sự kiện click cho nút "Làm mới"
        document.querySelector('.btn-secondary').addEventListener('click', resetPersonalForm); // Sửa đổi đây

        function sendVerificationCode() {
            const sendButton = document.getElementById('sendVerificationCode');
            const countdownElement = document.getElementById('countdown');

            // Vô hiệu hóa nút gửi và hiển thị bộ đếm ngược
            sendButton.disabled = true;
            countdownElement.style.display = 'inline';
            let countdown = 60;

            const countdownInterval = setInterval(function () {
                countdownElement.textContent = `Vui lòng chờ: ${countdown}s`;
                countdown--;

                if (countdown < 0) {
                    clearInterval(countdownInterval); // Dừng bộ đếm khi kết thúc
                    sendButton.disabled = false; // Kích hoạt lại nút gửi
                    countdownElement.style.display = 'none'; // Ẩn bộ đếm ngược
                }
            }, 1000);

            // Gửi yêu cầu AJAX đến servlet
            const xhr = new XMLHttpRequest();
            xhr.open('POST', '${pageContext.request.contextPath}/user/personal/sendVerificationCode', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        // Hiển thị thông báo thành công
                        Swal.fire({
                            icon: 'success',
                            title: 'Thành công',
                            text: 'Mã xác thực đã được gửi đến email!',
                            showConfirmButton: true
                        });
                    } else {
                        // Xử lý lỗi và dừng bộ đếm nếu có lỗi
                        clearInterval(countdownInterval); // Dừng bộ đếm ngay khi có lỗi
                        sendButton.disabled = false; // Kích hoạt lại nút gửi
                        countdownElement.style.display = 'none'; // Ẩn bộ đếm

                        Swal.fire({
                            icon: 'error',
                            title: 'Lỗi',
                            text: 'Có lỗi xảy ra khi gửi mã xác thực!',
                            showConfirmButton: true
                        });
                    }
                }
            };

            // Gửi email qua AJAX
            xhr.send('email=' + encodeURIComponent('${user.email}'));
        }




        // Hàm reset form
        function resetPersonalForm() {
            document.getElementById('fullName').value = '';
            document.getElementById('email').value = '';
            document.getElementById('phone').value = '';
            document.getElementById('address').value = '';
            document.getElementById('dob').value = '';
            document.getElementById('company').value = '';
            document.getElementById('oldPassword').value = '';
            document.getElementById('newPassword').value = '';
            document.getElementById('verificationCode').value = '';
            document.getElementById('profileImagePreview').src = 'https://via.placeholder.com/150'; // Reset ảnh đại diện
            document.getElementById('profilePictureInput').value = ''; // Reset input file
        }
    });
</script>

</body>
</html>
