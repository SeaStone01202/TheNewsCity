<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Article Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/admin.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div id="textEditorBlog">
    <nav class="navbar navbar-expand-lg navbar-light mb-4">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Blog Management</a>
            <h3>Xin chào: admin</h3>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link active" href="#">Bài viết</a></li>
                    <li class="nav-item"><a class="nav-link" href="personal.jsp">Tài khoản</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Đăng xuất</a></li>
                    <li class="nav-item"><a class="nav-link" href="../../index.jsp">Chuyển sang trang chính</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <form class="form-product" method="post" enctype="multipart/form-data">
        <div class="row mb-4">
            <div class="form-group col-md-8">
                <label for="titleBlog">Post Title</label>
                <input type="text" name="titleBlog" class="form-control" id="titleBlog" placeholder="Input title here"
                       required>
            </div>
            <div class="form-group col-md-4">
                <label for="selectedBlogs">Selected Categories</label>
                <select id="selectedBlogs" class="form-control" required>
                    <option value="">Select a category</option>
                    <c:forEach var="listVerticalCategory" items="${listCategory}">
                        <option value="${listVerticalCategory.categoryId}">${listVerticalCategory.categoryName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-2">
                <label>Status</label>
                <div id="activeBlog">
                    <label><input type="radio" name="statusActive" value="1" checked> Original</label>
                    <label><input type="radio" name="statusActive" value="0"> Draft</label>
                </div>
            </div>
            <div class="form-group col-md-2 text-center">
                <label>Thumbnails</label><br>
                <label for="mockupID" style="cursor: pointer;">
                    <i id="blog-label-image" class="fa-solid fa-plus"></i>
                    <img id="imagePreview" src="https://via.placeholder.com/150" alt="Thumbnail Preview"
                         style="max-height: 100px; margin-top: 10px;">
                </label>
                <input type="file" name="mockupID" class="form-control" id="mockupID" accept="image/*"
                       style="display: none" onchange="previewImage(event)" required>
            </div>
        </div>
        <div class="row mb-3">
            <div class="form-group col-md-12">
                <label for="contentBlog">Content</label>
                <textarea id="contentBlog" name="contentBlog" rows="4" cols="50" required></textarea>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-md-12">
                <button type="button" id="saveButton" name="action" value="save" class="btn btn-success">Save</button>
                <button type="button" id="updateButton" name="action" value="update" class="btn btn-primary" disabled>
                    Update
                </button>
                <button type="button" id="deleteButton" name="action" value="delete" class="btn btn-danger" disabled>
                    Delete
                </button>
                <button type="button" id="refreshButton" name="action" value="refresh" class="btn btn-secondary">
                    Refresh
                </button>
            </div>
        </div>
        <p hidden id="idBlogHidden"></p>
    </form>

    <br>
    <hr>
    <div class="card">
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
                <c:forEach var="table" items="${listNews}">
                    <tr data-blog-id="1">
                        <td>1</td>
                        <td>Technology</td>
                        <td class="fixed-width-title">Blog Title 1</td>
                        <td class="mockup-cell"><img src="../images/image01.jpg" alt="Thumbnail 1"></td>
                        <td>Original</td>
                        <td>User</td>
                        <td>
                            <button class="btn btn-warning edit-button" data-blog-id="1">Edit</button>
                        </td>
                    </tr>
                </c:forEach>
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
    CKEDITOR.replace('contentBlog');

    function editBlog(id, title, content) {
        document.getElementById("titleBlog").value = title;
        CKEDITOR.instances.contentBlog.setData(content);
        toggleButtons(true);
    }

    function resetForm() {
        document.querySelector('.form-product').reset();
        CKEDITOR.instances.contentBlog.setData('');
        toggleButtons(false);
    }

    function toggleButtons(isEditing) {
        document.getElementById("saveButton").disabled = isEditing;
        document.getElementById("updateButton").disabled = !isEditing;
        document.getElementById("deleteButton").disabled = !isEditing;
    }

    function previewImage(event) {
        const imagePreview = document.getElementById('imagePreview');
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                imagePreview.src = e.target.result;
            }
            reader.readAsDataURL(file);
        }
    }

    // Thêm sự kiện click cho các nút Edit
    document.querySelectorAll('.edit-button').forEach(button => {
        button.addEventListener('click', function () {
            const blogId = this.getAttribute('data-blog-id');
            const title = 'Blog Title ' + blogId; // Thay thế bằng giá trị thực tế
            const content = 'Content for Blog ' + blogId; // Thay thế bằng giá trị thực tế
            editBlog(blogId, title, content);
        });
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
