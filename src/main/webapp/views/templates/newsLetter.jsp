<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Email Subscription Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/admin.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</head>
<body>
<div id="emailSubscriptionManagement">
    <nav class="navbar navbar-expand-lg navbar-light mb-4">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Email Subscription Management</a>
            <h3>Xin chào: admin</h3>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/admin/news_letter">News_letter</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/category">Category</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/index">Chuyển sang trang chính</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <form class="form-email" method="post" action="${pageContext.request.contextPath}/admin/news_letter">
        <div class="row mb-4">
            <div class="form-group col-md-3">

                <label for="emailAddress">ID EMAIL</label>
                <input type="email" name="idEmail" class="form-control" id="idEmail" placeholder="Here id email"
                       disabled value="${not empty newsLetter.newsletterId ? newsLetter.newsletterId : ''}">
            </div>
            <div class="form-group col-md-8">
                <label for="emailAddress">Email Address</label>
                <input type="email" name="emailAddress" class="form-control" id="emailAddress" placeholder="Enter email address"
                       required value="${not empty newsLetter.email ? newsLetter.email : ''}">
            </div>
            <div class="form-group col-md-4">
                <label for="subscriptionStatus">Subscription Status</label>
                <select id="subscriptionStatus" name="subscriptionStatus" class="form-control" required>
                    <option>Selected value</option>
                    <c:choose>
                        <c:when test="${newsLetter.enabled == true}">
                            <option value="true" selected>Subscribed</option>
                            <option value="false">Unsubscribed</option>
                        </c:when>
                        <c:otherwise>
                            <option value="true">Subscribed</option>
                            <option value="false" selected>Unsubscribed</option>
                        </c:otherwise>
                    </c:choose>

                </select>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-md-12">
                <button type="submit" id="saveEmailButton" name="action" value="save" class="btn btn-success">Add Email</button>
                <button type="submit" id="updateEmailButton" name="action" value="update" class="btn btn-primary" >
                    Update
                </button>
                <button type="submit" id="deleteEmailButton" name="action" value="delete" class="btn btn-danger" >
                    Delete
                </button>
                <button type="button" id="refreshEmailButton" name="action" value="refresh" class="btn btn-secondary" onclick="resetEmailForm()">
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
                <tr>
                    <th>Email ID</th>
                    <th>Email Address</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody id="table-email-result">
                <c:forEach var="email" items="${newsLetterList}">
                    <tr data-email-id="${email.newsletterId}">
                        <td>${email.newsletterId}</td>
                        <td>${email.email}</td>
                        <td>${email.enabled == true ? 'Subscribed' : 'Unsubscribed'}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/news_letter/detail?id=${email.newsletterId}">Edit</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="col-lg-12">
                <div class="product__pagination blog__pagination" id="emailPagination">
                    <a href="#">&laquo;</a>
                    <a href="#">1</a>
                    <a href="#">2</a>
                    <a href="#">3</a>
                    <a href="#">&raquo;</a>
                </div>
            </div>
        </div>
    </div>
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
</div>

<script>
    function resetEmailForm() {
        document.getElementById('emailAddress').value = '';
        document.getElementById('subscriptionStatus').selectedIndex = 0;
    }

</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
