<%--
  Created by IntelliJ IDEA.
  User: haith
  Date: 10/1/2024
  Time: 9:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>500 Oops</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="d-flex align-items-center justify-content-center vh-100">
    <div class="text-center">
        <h1 class="display-1 fw-bold">500</h1>
        <p class="fs-3"> <span class="text-danger">Opps!</span> Something went wrong :(</p>
        <p class="lead">
            The page you’re looking for doesn’t exist.
        </p>
        <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary">Go Home</a>
    </div>
</div>
</body>
</html>