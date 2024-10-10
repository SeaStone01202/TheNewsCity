<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        }

        /* Giới hạn kích thước ảnh trong slider */
        .carousel-item img {
            max-width: 100%;  /* Đảm bảo ảnh không vượt quá chiều rộng container */
            max-height: 500px; /* Giới hạn chiều cao của ảnh */
            width: auto;       /* Tự động điều chỉnh chiều rộng dựa trên chiều cao */
            height: auto;      /* Tự động điều chỉnh chiều cao dựa trên chiều rộng */
            object-fit: cover; /* Đảm bảo ảnh bao phủ toàn bộ vùng chứa mà không bị méo */
        }
    </style>
</head>
<body>

<section class="top-stories">
    <div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active"
                    aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1"
                    aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2"
                    aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active" data-bs-interval="10000">
                <img src="${pageContext.request.contextPath}/views/images/image01.jpg" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                    <a href="${pageContext.request.contextPath}/views/templates/page/detail_news.jsp"><h5>First slide
                        label</h5></a>
                    <p>Some representative placeholder content for the first slide.</p>
                </div>
            </div>
            <div class="carousel-item" data-bs-interval="2000">
                <img src="${pageContext.request.contextPath}/views/images/image02.jpg" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Second slide label</h5>
                    <p>Some representative placeholder content for the second slide.</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/views/images/image03.jpg" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Third slide label</h5>
                    <p>Some representative placeholder content for the third slide.</p>
                </div>
            </div>
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
        <div class="row">
            <div class="col-12"> <!-- Toàn bộ nội dung bài báo chiếm hết chiều ngang -->
                <h2 class="title">Giá vàng nhẫn bật tăng trở lại, vàng miếng SJC duy trì mốc cao</h2>
                <hr>
                <div class="row">
                    <div class="col-md-4">
                        <img src="https://nld.mediacdn.vn/thumb_w/640/291774122806476800/2024/8/10/vang-10-17232556148051183192387.jpg" class="img-fluid" alt="News Image">
                    </div>
                    <div class="col-md-8">
                        <p class="content">(NLĐO) – Giá vàng nhẫn 24K các loại chạm trở lại mốc 83 triệu đồng/lượng... </p>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-md-6">
                        <p class="date">Published on: 2024-10-03</p>
                    </div>
                    <div class="col-md-6 text-end">
                        <p class="author">Author: John Doe</p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bài báo tiếp theo -->
        <div class="row mt-4">
            <div class="col-12">
                <h2 class="title">Thị trường chứng khoán bứt phá mạnh mẽ</h2>
                <hr>
                <div class="row">
                    <div class="col-md-4">
                        <img src="https://example.com/stock-market-image.jpg" class="img-fluid" alt="Stock Market Image">
                    </div>
                    <div class="col-md-8">
                        <p class="content">Thị trường chứng khoán đã có một ngày giao dịch sôi động với các chỉ số chính đều tăng mạnh... </p>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-md-6">
                        <p class="date">Published on: 2024-10-03</p>
                    </div>
                    <div class="col-md-6 text-end">
                        <p class="author">Author: Jane Smith</p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bài báo tiếp theo -->
        <div class="row mt-4">
            <div class="col-12">
                <h2 class="title">Giá dầu tiếp tục tăng, lo ngại về nguồn cung</h2>
                <hr>
                <div class="row">
                    <div class="col-md-4">
                        <img src="https://example.com/oil-market-image.jpg" class="img-fluid" alt="Oil Market Image">
                    </div>
                    <div class="col-md-8">
                        <p class="content">Giá dầu thô đã tiếp tục leo thang do những lo ngại về gián đoạn nguồn cung từ các nước sản xuất lớn... </p>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-md-6">
                        <p class="date">Published on: 2024-10-03</p>
                    </div>
                    <div class="col-md-6 text-end">
                        <p class="author">Author: Mark Lee</p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bài báo tiếp theo -->
        <div class="row mt-4">
            <div class="col-12">
                <h2 class="title">Công nghệ AI thay đổi ngành công nghiệp</h2>
                <hr>
                <div class="row">
                    <div class="col-md-4">
                        <img src="https://example.com/ai-industry-image.jpg" class="img-fluid" alt="AI Industry Image">
                    </div>
                    <div class="col-md-8">
                        <p class="content">Công nghệ AI đang dần thay đổi cách mà các doanh nghiệp hoạt động, từ tự động hóa quy trình cho đến dự đoán thị trường... </p>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-md-6">
                        <p class="date">Published on: 2024-10-03</p>
                    </div>
                    <div class="col-md-6 text-end">
                        <p class="author">Author: Sarah Connor</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>



<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.0/js/bootstrap.bundle.min.js"></script>

</body>
</html>
