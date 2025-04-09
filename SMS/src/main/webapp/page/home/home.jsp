<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Home</title>
        <link href="${pageContext.request.contextPath}/assets/bootstrap/bootstrap-5.0.2-dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/HomePage.css" rel="stylesheet">
        <style>
            a {
                text-decoration: none;
                color: white;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <h1>Home Page: Welcome ${requestScope.user.username}</h1>
            <div class="d-flex justify-content-center gap-3">
                <a href="${pageContext.request.contextPath}/student/find-all" class="btn btn-primary">Student List</a>
                <a href="${pageContext.request.contextPath}/course/find-all" class="btn btn-secondary">Course List</a>
                <a  class="btn btn-danger logout-btn text-decoration-none text-white " href="${pageContext.request.contextPath}/auth/logout">
                    <i class="fas fa-sign-out-alt"></i> Logout
                </a>
            </div>
        </div>
    </body>
</html>
