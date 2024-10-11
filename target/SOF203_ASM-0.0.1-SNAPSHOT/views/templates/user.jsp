<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blog Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/user.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- SweetAlert CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
    <!-- SweetAlert JS -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body>
<div id="textEditorBlog">
    <nav class="navbar navbar-expand-lg navbar-light mb-4">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Blog Management</a>
            <h3>Xin chào: admin</h3>
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
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
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

    <form class="form-product" action="${pageContext.request.contextPath}/user/news" method="post"
          enctype="multipart/form-data">
        <div class="row mb-4">
            <div class="form-group col-md-8">
                <p>ID: <span>${not empty selectedNews.newsId ? selectedNews.newsId : ''}</span></p>
                <input type="text" name="idC" value="${not empty selectedNews.newsId ? selectedNews.newsId : ''}" readonly class="form-control">
                <label for="titleBlog">Post Title</label>
                <input type="text" name="title" class="form-control" id="titleBlog" placeholder="Input title here"
                       value="${not empty selectedNews.title ? selectedNews.title : ''}">
            </div>
            <div class="form-group col-md-4">
                <label for="selectedNews">Selected Categories</label>
                <select id="selectedNews" name="category" class="form-control">
                    <option value="">Select a category</option>
                    <c:forEach var="listVerticalCategory" items="${listCategory}">
                        <option value="${listVerticalCategory.categoryId}" ${listVerticalCategory.categoryId == selectedNews.categoryId ? 'selected' : ''}>${listVerticalCategory.categoryName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-2">
                <label>Home</label>
                <div id="activeBlog">
                    <label>
                        <input type="radio" name="home" value="true" ${selectedNews.isHome ? 'checked' : ''}> True
                    </label>
                    <label>
                        <input type="radio" name="home" value="false" ${selectedNews.isHome ? 'checked' : ''}> False
                    </label>
                </div>
            </div>
            <div class="form-group col-md-2 text-center">
                <label>Thumbnails</label> <br>
                <label for="mockupID" style="cursor: pointer;">
                    <i id="blog-label-image" class="fa-solid fa-plus"></i>
                    <img id="imagePreview"
                         src="${not empty selectedNews.image ? selectedNews.image : 'https://via.placeholder.com/150'}"
                         alt="Thumbnail Preview"
                         style="display: block; width: 100%; height: auto; max-height: 100px; margin-top: 10px;">
                </label>
                <input type="file" name="mockupID" class="form-control" id="mockupID"
                       placeholder="Choose File Thumbnails" accept="image/*" style="display: none"
                       onchange="previewImage(event)">
            </div>
        </div>
        <div class="row mb-3">
            <div class="form-group col-md-12">
                <label for="contentBlog">Content</label>
                <textarea id="contentBlog" name="content" rows="4" cols="50">
                     <c:out value="${not empty selectedNews.content ? selectedNews.content : ''}"/>
                 </textarea>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-12">
                <button type="submit" id="saveButton" name="action" value="save" class="btn btn-success">Save</button>
                <button type="submit" id="updateButton" name="action" value="update" class="btn btn-primary">
                    Update
                </button>
                <button type="submit" id="deleteButton" name="action" value="delete" class="btn btn-danger">
                    Delete
                </button>
                <button type="button" id="refreshButton" class="btn btn-secondary" onclick="clearForm()">
                    Refresh
                </button>
            </div>
        </div>
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
                <c:forEach var="news" items="${listNews}">
                    <tr class="table-blog" data-blog-id="${news.newsId}">
                        <td>${news.newsId}</td>
                        <td>${news.categoryId}</td>
                        <td class="fixed-width-title">${news.title}</td>
                        <td class="mockup-cell"><img src="${news.image}" alt="Thumbnail 1"></td>
                        <td>${news.isHome}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/user/news/detail?id=${news.newsId}">Edit</a>
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
    // Thay thế textarea bằng CKEditor
    CKEDITOR.replace('contentBlog');

    // Hàm xem trước hình ảnh
    function previewImage(event) {
        const imagePreview = document.getElementById("imagePreview");
        imagePreview.src = URL.createObjectURL(event.target.files[0]);
    }

    function clearForm() {
        // Xóa giá trị ID
        document.getElementById("idBlogHidden").innerText = "ID: "; // Nếu bạn cần giữ label nhưng xóa giá trị

        // Xóa giá trị Title
        document.getElementById("titleBlog").value = "";

        // Xóa giá trị Content
        CKEDITOR.instances['contentBlog'].setData(""); // Nếu bạn đang sử dụng CKEditor

        // Xóa giá trị Category
        document.getElementById("selectedNews").selectedIndex = 0; // Chọn option đầu tiên

        // Xóa giá trị Home (Radio)
        const homeRadios = document.getElementsByName("home");
        homeRadios.forEach(radio => radio.checked = false); // Bỏ chọn tất cả

        // Khôi phục hình ảnh về mặc định
        const imagePreview = document.getElementById("imagePreview");
        imagePreview.src = "https://via.placeholder.com/150"; // Đường dẫn đến hình ảnh mặc định
        document.getElementById("mockupID").value = ""; // Xóa giá trị file input
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-7Q1s1U/+ON7QzqQ6vfdmW4HCOOiQk3skRoM3Db8E8WmjG/3bNQtnv/fHjxK9sco7"
        crossorigin="anonymous"></script>
</body>
</html>
