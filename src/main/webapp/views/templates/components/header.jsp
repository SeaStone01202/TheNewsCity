<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
  <style>
    /* Tăng kích thước chữ và giãn khoảng cách giữa các chữ */
    .navbar-nav .nav-link {
      font-size: 1.2rem; /* Tăng kích thước chữ */
      letter-spacing: 1px; /* Giãn khoảng cách giữa các chữ */
    }
  </style>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/index">Trang chủ</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/views/templates/page/hot_news.jsp">Tin nóng</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Thể loại
          </a>
          <ul class="dropdown-menu">
            <c:forEach var="listVerticalCategory" items="${listCategory}">
              <li><a class="dropdown-item" href="#">${listVerticalCategory.categoryName}</a></li>
            </c:forEach>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/views/pages/page/video.jsp">Video hay</a>
        </li>
      </ul>
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
        <c:choose>
          <c:when test="${user.role == true }">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Tài khoản
              </a>
              <ul class="dropdown-menu">
                <p>Xin chào: ${user.fullname}</p>
                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/news_letter">News_letter</a></li>
                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/category">Cập nhật thể loại</a></li>
                <li><hr class="dropdown-divider"></li>
                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
              </ul>
            </li>
          </c:when>
          <c:when test="${user.role == false }">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Tài khoản
              </a>
              <ul class="dropdown-menu">
                <p>Xin chào: ${user.fullname}</p>
                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/news">Bài viết</a></li>
                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/personal">Cập nhật thông tin</a></li>
                <li><hr class="dropdown-divider"></li>
                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
              </ul>
            </li>
          </c:when>
          <c:otherwise>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Tài khoản
              </a>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/login">Đăng nhập</a></li>
                <li><hr class="dropdown-divider"></li>
                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/register">Đăng ký</a></li>
              </ul>
            </li>
          </c:otherwise>
        </c:choose>
      </ul>
      <form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
</body>
</html>
