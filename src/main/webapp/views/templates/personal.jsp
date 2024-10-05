<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh sửa thông tin cá nhân</title>
    <link rel="stylesheet" href="../css/admin.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</head>

<body>
<div id="textEditorBlog">
    <nav class="navbar navbar-expand-lg navbar-light mb-4">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Blog Management</a>
            <h3>Xin chào: admin</h3>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="admin.jsp">Bài viết</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Tài khoản</a> <!-- Trang hiện tại -->
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Đăng xuất</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="../../index.jsp">Chuyển sang trang chính</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <form class="form-personal-info" method="post" enctype="multipart/form-data">
        <!-- Nhập thông tin cá nhân -->
        <div class="row mb-4">
            <div class="form-group col-md-6">
                <label for="fullName">Họ và tên</label>
                <input type="text" name="fullName" class="form-control" id="fullName" placeholder="Nhập họ và tên">
            </div>
            <div class="form-group col-md-6">
                <label for="email">Email</label>
                <input type="email" name="email" class="form-control" id="email" placeholder="Nhập email">
            </div>
        </div>

        <div class="row mb-4">
            <div class="form-group col-md-6">
                <label for="phone">Số điện thoại</label>
                <input type="text" name="phone" class="form-control" id="phone" placeholder="Nhập số điện thoại">
            </div>
            <div class="form-group col-md-6">
                <label for="address">Địa chỉ</label>
                <input type="text" name="address" class="form-control" id="address" placeholder="Nhập địa chỉ">
            </div>
        </div>

        <div class="row mb-4">
            <div class="form-group col-md-6">
                <label for="dob">Ngày tháng năm sinh</label>
                <input type="date" name="dob" class="form-control" id="dob">
            </div>
            <div class="form-group col-md-6">
                <label for="company">Công ty làm việc</label>
                <input type="text" name="company" class="form-control" id="company" placeholder="Nhập tên công ty">
            </div>
        </div>

        <div class="row mb-4">
            <div class="form-group col-md-6">
                <label for="role">Vai trò</label>
                <select name="role" class="form-control" id="role">
                    <option value="user">User</option> <!-- Tùy chọn User -->
                    <option value="guest">Guest</option> <!-- Tùy chọn Guest -->
                </select>
            </div>
            <div class="form-group col-md-6">
                <label for="oldPassword">Mật khẩu cũ</label>
                <input type="password" name="oldPassword" class="form-control" id="oldPassword" placeholder="Nhập mật khẩu cũ">
            </div>
        </div>

        <div class="row mb-4">
            <div class="form-group col-md-6">
                <label for="newPassword">Mật khẩu mới</label>
                <input type="password" name="newPassword" class="form-control" id="newPassword" placeholder="Nhập mật khẩu mới">
            </div>
            <div class="form-group col-md-6">
                <label for="profilePicture">Ảnh đại diện</label>
                <label for="profilePictureInput" style="cursor: pointer;">
                    <i id="profile-label-image" class="fa-solid fa-plus"></i>
                    <img id="profileImagePreview" src="https://via.placeholder.com/150" alt="Ảnh đại diện" class="img-fluid" style="margin-top: 10px; border-radius: 5%;">
                </label>
                <input type="file" name="profilePicture" class="form-control" id="profilePictureInput" accept="image/*" style="display: none" onchange="previewProfileImage(event)">
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-12">
                <button type="submit" class="btn btn-success">Cập nhật thông tin</button>
                <button type="button" class="btn btn-secondary" onclick="resetPersonalForm()">Làm mới</button>
            </div>
        </div>
    </form>

    <br>
    <br>
    <div class="card"></div>
    <div class="card-body">
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>Blog ID</th>
                <th>Category</th>
                <th class="fixed-width-title">Title</th>
                <th>Thumbnails</th>
                <th>Status</th>
                <th>Role</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody id="table-blog-result">
            <tr data-blog-id="1">
                <td>1</td>
                <td>Technology</td>
                <td class="fixed-width-title">Blog Title 1</td>
                <td class="mockup-cell"><img src="images/image01.jpg" alt="Thumbnail 1"></td>
                <td>Original</td>
                <td>User</td>
                <td><button class="btn btn-warning edit-button" data-blog-id="1">Edit</button></td>
            </tr>
            <tr data-blog-id="2">
                <td>2</td>
                <td>Education</td>
                <td class="fixed-width-title">Blog Title 2</td>
                <td class="mockup-cell"><img src="images/image02.jpg" alt="Thumbnail 2"></td>
                <td>Draft</td>
                <td>Guest</td>
                <td><button class="btn btn-warning edit-button" data-blog-id="2">Edit</button></td>
            </tr>
            </tbody>
        </table>
        <div class="col-lg-12">
            <div class="product__pagination blog__pagination" id="pagination">
                <a href="#">&laquo;</a>
                <a href="#">1</a>
                <a href="#">2</a>
                <a href="#">3</a>
                <a href="#">&raquo;</a>
            </div>
        </div>
    </div>
</div>
</div>

<script>
    function previewProfileImage(event) {
        const imagePreview = document.getElementById('profileImagePreview');
        const file = event.target.files[0];

        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                imagePreview.src = e.target.result;
            }
            reader.readAsDataURL(file);
        }
    }

    function resetPersonalForm() {
        document.querySelector('.form-personal-info').reset();
        document.getElementById('profileImagePreview').src = 'images/default-avatar.png';
    }

    // Quản lý trạng thái hoạt động của các liên kết điều hướng
    document.addEventListener('DOMContentLoaded', function() {
        const navLinks = document.querySelectorAll('.nav-link');

        navLinks.forEach(link => {
            link.addEventListener('click', function() {
                // Xóa lớp active từ tất cả các liên kết
                navLinks.forEach(nav => nav.classList.remove('active'));

                // Thêm lớp active vào liên kết đã được nhấp
                this.classList.add('active');
            });
        });
    });
</script>

</body>
</html>
