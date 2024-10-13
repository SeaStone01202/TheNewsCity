<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi Tiết Bài Báo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>

<body>
<%--Header--%>
 <jsp:include page="../../templates/components/header.jsp"/>

<main class="container mt-4">
    <div class="row">
        <!-- Phần bài viết -->
        <section class="col-12 col-md-9">
            <article>
                <h1>${news.title}</h1>
                <h3>Tác giả: ${selectedUser.fullname}</h3>
                <h3>Ngày đăng: ${news.postedDate}</h3>
                <img src="${news.image}" alt="Hình ảnh bài báo" class="img-fluid mb-3">
                <p>${news.content}</p>
            </article>

            <!-- Phần bình luận -->
            <div class="comment-section mt-4">
                <div class="p-3 border rounded bg-white">
                    <h3>Bình luận</h3>
                    <form class="comment-form mb-3">
                        <textarea class="form-control" rows="3" placeholder="Viết bình luận của bạn..."></textarea>
                        <button type="submit" class="btn btn-primary mt-2">Gửi bình luận</button>
                    </form>
                    <div class="comments">
                        <div class="comment">
                            <p><strong>Người dùng 1:</strong> Đây là một bài viết rất hữu ích!</p>
                        </div>
                        <div class="comment">
                            <p><strong>Người dùng 2:</strong> Cảm ơn tác giả đã chia sẻ thông tin!</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Cột tin tức cùng loại -->
        <aside class="col-12 col-md-3">
            <h3>Tin cùng loại</h3>
            <ul class="list-unstyled">
                <li><a href="#">Sự kiện nổi bật trong tháng 9</a></li>
                <li><a href="#">Các chính sách mới của năm 2024</a></li>
                <li><a href="#">Xu hướng công nghệ hiện nay</a></li>
                <li><a href="#">Ảnh hưởng của biến đổi khí hậu</a></li>
                <li><a href="#">Thể thao quốc tế tháng 10</a></li>
            </ul>
        </aside>
    </div>
</main>

<%--Footer--%>
<jsp:include page="../../templates/components/footer.jsp"/>
</body>
</html>
