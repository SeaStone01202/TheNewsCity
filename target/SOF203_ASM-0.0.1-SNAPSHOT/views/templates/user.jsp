<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blog Management</title>
    <link rel="stylesheet" href="../css/user.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
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
                        <a class="nav-link active" href="#">Bài viết</a> <!-- Trang hiện tại -->
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="personalUser.jsp">Tài khoản</a>
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

    <form class="form-product" method="post" enctype="multipart/form-data">
        <div class="row mb-4">
            <div class="form-group col-md-8">
                <label for="titleBlog">Post Title</label>
                <input type="text" name="titleBlog" class="form-control" id="titleBlog" placeholder="Input title here">
            </div>
            <div class="form-group col-md-4">
                <label for="selectedBlogs">Selected Categories</label>
                <select id="selectedBlogs" class="form-control">
                    <option value="1">News</option>
                    <option value="2">Technology</option>
                    <option value="3">Entertainment</option>
                    <option value="4">Education</option>
                    <option value="5">Politics</option>
                </select>
            </div>
            <div class="form-group col-md-2">
                <label>Status</label>
                <div id="activeBlog">
                    <label>
                        <input type="radio" name="statusActive" value="1" checked> Original
                    </label>
                    <label>
                        <input type="radio" name="statusActive" value="0"> Draft
                    </label>
                </div>
            </div>
            <div class="form-group col-md-2 text-center">
                <label>Thumbnails</label> <br>
                <label for="mockupID" style="cursor: pointer;">
                    <i id="blog-label-image" class="fa-solid fa-plus"></i>
                    <img id="imagePreview" src="https://via.placeholder.com/150" alt="Thumbnail Preview" style="display: block; width: 100%; height: auto; max-height: 100px; margin-top: 10px;">
                </label>
                <input type="file" name="mockupID" class="form-control" id="mockupID" placeholder="Choose File Thumbnails" accept="image/*" style="display: none" onchange="previewImage(event)">
            </div>
        </div>
        <div class="row mb-3">
            <div class="form-group col-md-12">
                <label for="contentBlog">Content</label>
                <textarea id="contentBlog" name="contentBlog" rows="4" cols="50"></textarea>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-md-12">
                <button type="button" id="saveButton" name="action" value="save" class="btn btn-success">Save</button>
                <button type="button" id="updateButton" name="action" value="update" class="btn btn-primary" disabled>Update</button>
                <button type="button" id="deleteButton" name="action" value="delete" class="btn btn-danger" disabled>Delete</button>
                <button type="button" id="refreshButton" name="action" value="refresh" class="btn btn-secondary">Refresh</button>
            </div>
        </div>

        <p hidden="hidden" id="idBlogHidden"></p>
    </form>
    <br>
    <hr>
    <div class="card">
        <div class="card-body">
            <table class="table table-bordered table-hover">
                <thead>
                <tr class="table-blog">
                    <th>Blog ID</th>
                    <th>Category</th>
                    <th class="fixed-width-title">Title</th>
                    <th>Thumbnails</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody id="table-blog-result">
                <tr class="table-blog" data-blog-id="1">
                    <td>1</td>
                    <td>Technology</td>
                    <td class="fixed-width-title">Blog Title 1</td>
                    <td class="mockup-cell"><img src="../images/image01.jpg" alt="Thumbnail 1"></td>
                    <td>Original</td>
                    <td><button class="btn btn-warning edit-button" data-blog-id="1">Edit</button></td>
                </tr>
                <tr class="table-blog" data-blog-id="2">
                    <td>2</td>
                    <td>Education</td>
                    <td class="fixed-width-title">Blog Title 2</td>
                    <td class="mockup-cell"><img src="../images/image02.jpg" alt="Thumbnail 2"></td>
                    <td>Draft</td>
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

<!-- CKEditor Script -->
<script src="//cdn.ckeditor.com/4.22.1/standard/ckeditor.js"></script>
<script>
    // Thay thế textarea bằng CKEditor
    CKEDITOR.replace('contentBlog');

    // Hàm xử lý chỉnh sửa bài viết
    function editBlog(id, title, content) {
        document.getElementById("titleBlog").value = title;
        CKEDITOR.instances.contentBlog.setData(content);

        const saveButton = document.getElementById("saveButton");
        const updateButton = document.getElementById("updateButton");
        const deleteButton = document.getElementById("deleteButton");

        saveButton.disabled = true;
        updateButton.disabled = false;
        deleteButton.disabled = false;

        updateButton.style.backgroundColor = "#4CAF50";
        deleteButton.style.backgroundColor = "#f44336";
    }

    // Hàm reset form
    function resetForm() {
        document.querySelector('.form-product').reset();
        CKEDITOR.instances.contentBlog.setData('');

        const saveButton = document.getElementById("saveButton");
        const updateButton = document.getElementById("updateButton");
        const deleteButton = document.getElementById("deleteButton");

        saveButton.disabled = false;
        updateButton.disabled = true;
        deleteButton.disabled = true;

        updateButton.style.backgroundColor = "#cccccc";
        deleteButton.style.backgroundColor = "#cccccc";
    }

    // Hàm xem trước hình ảnh đã tải lên
    function previewImage(event) {
        const imagePreview = document.getElementById('imagePreview');
        const file = event.target.files[0];

        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                imagePreview.src = e.target.result;
            }
            reader.readAsDataURL(file);
        }
    }

    // Xử lý trạng thái hoạt động cho các liên kết điều hướng
    document.addEventListener('DOMContentLoaded', function() {
        const navLinks = document.querySelectorAll('.nav-link');

        navLinks.forEach(link => {
            link.addEventListener('click', function() {
                // Xóa lớp active từ tất cả các liên kết
                navLinks.forEach(nav => nav.classList.remove('active'));
                // Thêm lớp active vào liên kết được nhấn
                this.classList.add('active');
            });
        });
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz4fnFO9gyb+46A0jJt+L5vN5m5hGfmBv9z8qO1fef2N27FVWZBpxhN0bB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-6He0B+F5IOVpO3U4KmMR7p8gvZktmY4qF9+hLNpeBzFG+mbTDAV8UxRAc0uZrjD5" crossorigin="anonymous"></script>
</body>
</html>
