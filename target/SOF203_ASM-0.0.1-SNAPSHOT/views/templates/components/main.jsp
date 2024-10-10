<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="slug" uri="http://example.com/tags" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.0/css/bootstrap.min.css">
    <style>
        .carousel-caption h5 {
            display: -webkit-box;
            -webkit-line-clamp: 3; /* Giới hạn 3 dòng */
            -webkit-box-orient: vertical;
            overflow: hidden;
            text-overflow: ellipsis;
            color: white; /* Màu chữ tiêu đề */
            text-decoration: none; /* Không có gạch dưới */
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7); /* Đổ bóng cho chữ tiêu đề */
        }

        .carousel-caption p {
            display: -webkit-box;
            -webkit-line-clamp: 3; /* Giới hạn 3 dòng cho phần content */
            -webkit-box-orient: vertical;
            overflow: hidden;
            text-overflow: ellipsis;
            color: white; /* Màu chữ nội dung */
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7); /* Đổ bóng cho chữ nội dung */
        }

        .carousel-item img {
            max-width: 90%;
            max-height: 500px;
            width: auto;
            height: auto;
            object-fit: cover;
            display: block;
            margin-left: auto;
            margin-right: auto; /* Căn giữa ảnh */
        }

        .carousel-caption a {
            color: white; /* Màu chữ của thẻ a */
            text-decoration: none; /* Không có gạch dưới */
        }

        .carousel-caption a:hover {
            text-decoration: underline; /* Thêm gạch dưới khi hover */
            color: lightblue; /* Màu chữ khi hover */
        }

        .content {
            display: -webkit-box;
            -webkit-line-clamp: 3; /* Giới hạn 3 dòng cho phần content */
            -webkit-box-orient: vertical;
            overflow: hidden;
            text-overflow: ellipsis; /* Hiển thị dấu ... */
            color: black; /* Màu chữ nội dung */
            font-size: 1rem; /* Kích thước chữ nội dung */
        }

        .content:hover {
            text-decoration: underline; /* Thêm gạch dưới khi hover */
            color: palevioletred; /* Màu chữ khi hover */
        }

        .title:hover {
            text-decoration: underline; /* Thêm gạch dưới khi hover */
            color: #b16060; /* Màu chữ khi hover */
        }

    </style>

</head>
<body>

<section class="top-stories">
    <div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <c:forEach var="news" items="${listNews}" varStatus="status">
                <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="${status.index}" class="${status.first ? 'active' : ''}" aria-current="${status.first ? 'true' : 'false'}" aria-label="Slide ${status.index + 1}"></button>
            </c:forEach>
        </div>
        <div class="carousel-inner">
            <c:forEach var="news" items="${listNews}" varStatus="status">
                <div class="carousel-item ${status.first ? 'active' : ''}" data-bs-interval="2000">
                    <img src="${news.image}" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <a href="${pageContext.request.contextPath}/views/templates/page/detail_news.jsp"><h5>${news.title}</h5></a>
                        <p>${news.content}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</section>

<section class="news-content mt-5">
    <div class="container">

        <c:forEach var="verticalList" items="${listNews}">
            <div class="row mt-4">
                <div class="col-12">
                    <a href="${pageContext.request.contextPath}/<slug:toSlug input='${verticalList.title}'/>-00${verticalList.newsId}.html" style="color: black; text-decoration: none;">
                        <h2 class="title">${verticalList.title}</h2>
                        <hr>
                        <div class="row">
                            <div class="col-md-4">
                                <img src="${verticalList.image}" class="img-fluid" alt="News Image">
                            </div>
                            <div class="col-md-8">
                                <p class="content">${verticalList.content}</p>
                            </div>
                        </div>
                    </a>
                    <hr>
                    <div class="row">
                        <div class="col-md-6">
                            <p class="date">Published on: ${verticalList.postedDate}</p>
                        </div>
                        <div class="col-md-6 text-end">
                            <p class="author">Author: ${verticalList.authorId}</p>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</section>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.0/js/bootstrap.bundle.min.js"></script>

</body>
</html>
