<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add Course</title>
        <link href="${pageContext.request.contextPath}/assets/bootstrap/bootstrap-5.0.2-dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="${pageContext.request.contextPath}/assets/css/AddCourse.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center mb-4">Add Course</h1>
            <c:if test="${not empty requestScope.errorMessage}">
                <div class="alert alert-danger" role="alert">
                    <c:out value="${requestScope.errorMessage}" />
                </div>
            </c:if>
            <form action="${pageContext.request.contextPath}/course/add-new" method="post">
                <div class="mb-3">
                    <a href="${pageContext.request.contextPath}/course/find-all" class="btn btn-primary btn-sm mt-3">
                        <i class="fas fa-arrow-left"></i> Back
                    </a>
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">Name:</label>
                    <input type="text" name="courseName" id="name" class="form-control" value="${requestScope.courseName}" required>
                </div>

                <div class="mb-3">
                    <label for="code" class="form-label">Code:</label>
                    <input type="text" name="courseCode" id="code" class="form-control" value="${requestScope.courseCode}" required>
                </div>

                <div class="mb-3">
                    <label for="credit" class="form-label">Credit:</label>
                    <input type="number" name="courseCredit" id="credit" class="form-control" min="0" step="1" value="${requestScope.courseCredit}" required>
                </div>

                <div>
                    <button type="submit" class="btn btn-primary">Save</button>
                    <button type="reset" class="btn btn-secondary">Reset</button>
                </div>
            </form>
        </div>
    </body>
</html>
