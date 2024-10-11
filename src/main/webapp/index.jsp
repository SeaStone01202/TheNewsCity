<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Chủ Báo Điện Tử</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>

<body>
<%--Header--%>
 <jsp:include page="views/templates/components/header.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-9" >
            <!-- Main Content -->
             <jsp:include page="views/templates/components/main.jsp"/>
        </div>
        <!-- Cột 3 phần -->
        <div class="col-3" >
            <!-- Sidebar -->
            <jsp:include page="views/templates/components/sidebar.jsp"/>
        </div>
    </div>
</div>

<!-- Footer -->
 <jsp:include page="views/templates/components/footer.jsp"/>

</body>
</html>