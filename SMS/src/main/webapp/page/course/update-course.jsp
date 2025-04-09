<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Update Course</title>
        <link href="${pageContext.request.contextPath}/assets/bootstrap/bootstrap-5.0.2-dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="${pageContext.request.contextPath}/assets/css/UpdateCourse.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1 class="text-center mb-4">Update Course</h1>
            <form action="${pageContext.request.contextPath}/course/update" method="post">
                <div class="mb-3">
                    <a href="${pageContext.request.contextPath}/course/find-all" class="btn btn-primary btn-sm mt-3">
                        <i class="fas fa-arrow-left"></i> Back
                    </a>
                </div>
                <div class="mb-3">
                    <label for="id" class="form-label">ID:</label>
                    <input type="text" name="courseId" id="id" class="form-control" value="${requestScope.course.id}" readonly>
                </div>

                <div class="mb-3">
                    <label for="name" class="form-label">Name:</label>
                    <input type="text" name="courseName" id="name" class="form-control" value="${requestScope.course.name}" required>
                </div>

                <div class="mb-3">
                    <label for="code" class="form-label">Code:</label>
                    <input type="text" name="courseCode" id="code" class="form-control" value="${requestScope.course.code}" required>
                </div>

                <div class="mb-3">
                    <label for="credit" class="form-label">Credit:</label>
                    <input type="number" name="courseCredit" id="credit" class="form-control" value="${requestScope.course.credit}" required>
                </div>

                <button type="submit" class="btn btn-primary">Update</button>
                <button type="reset" class="btn btn-secondary">Reset</button>
            </form>
        </div>
    </body>
</html>
