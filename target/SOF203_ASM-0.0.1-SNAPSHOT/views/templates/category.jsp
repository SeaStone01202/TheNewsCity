<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Category Management</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/user.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <!-- SweetAlert CSS -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
  <!-- SweetAlert JS -->
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body>
<div id="categoryManagement">
  <nav class="navbar navbar-expand-lg navbar-light mb-4">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Category Management</a>
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
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
              aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto">
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/admin/news_letter">News_letter</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" href="${pageContext.request.contextPath}/admin/category">Category</a> <!-- Trang hiện tại -->
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

  <form class="form-category" action="${pageContext.request.contextPath}/admin/category" method="post">
    <div class="row mb-4">
      <div class="form-group col-md-6">
        <p>ID Loại tin: <span id="categoryIdDisplay">${not empty selectedCategory.categoryId ? selectedCategory.categoryId : ''}</span></p>
        <input type="hidden" id="categoryId" name="categoryId" value="${not empty selectedCategory.categoryId ? selectedCategory.categoryId : ''}">
        <label for="categoryName">Tên loại tin</label>
        <input type="text" name="categoryName" class="form-control" id="categoryName" placeholder="Nhập tên loại tin"
               value="${not empty selectedCategory.categoryName ? selectedCategory.categoryName : ''}">
      </div>
    </div>

    <div class="row mb-3">
      <div class="col-md-12">
        <button type="submit" name="action" value="save" class="btn btn-success">Lưu</button>
        <button type="submit" name="action" value="update" class="btn btn-primary">Cập nhật</button>
        <button type="submit" name="action" value="delete" class="btn btn-danger">Xóa</button>
        <button type="button" class="btn btn-secondary" onclick="clearForm()">Làm mới</button>
      </div>
    </div>
  </form>

  <br>
  <hr>
  <div class="card">
    <div class="card-body">
      <table class="table table-bordered table-hover">
        <thead>
        <tr>
          <th>ID</th>
          <th>Tên loại tin</th>
          <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="category" items="${listCategory}">
          <tr>
            <td>${category.categoryId}</td>
            <td>${category.categoryName}</td>
            <td>
              <a href="${pageContext.request.contextPath}/admin/category/detail?ids=${category.categoryId}">Sửa</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>

<script>
  function clearForm() {
    // Xóa nội dung của thẻ span hiển thị ID
    document.getElementById("categoryIdDisplay").innerText = "";

    // Làm trống trường nhập tên loại tin
    document.getElementById("categoryName").value = "";

    // Làm trống giá trị của trường hidden categoryId
    document.getElementById("categoryId").value = "";
  }


</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-7Q1s1U/+ON7QzqQ6vfdmW4HCOOiQk3skRoM3Db8E8WmjG/3bNQtnv/fHjxK9sco7"
        crossorigin="anonymous"></script>
</body>
</html>
