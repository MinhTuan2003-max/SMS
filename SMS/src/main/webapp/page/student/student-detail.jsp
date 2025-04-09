<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Student Detail</title>
        <link href="${pageContext.request.contextPath}/assets/bootstrap/bootstrap-5.0.2-dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="${pageContext.request.contextPath}/assets/css/StudentDetail.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <div class="card shadow-lg">
                <div class="card-body">
                    <h1 class="card-title mb-4">Student Details</h1>

                    <div class="mb-3">
                        <label for="id" class="form-label">ID:</label>
                        <input type="text" name="studentId" id="id" class="form-control" value="${requestScope.student.id}" readonly>
                    </div>

                    <div class="mb-3">
                        <label for="name" class="form-label">Name:</label>
                        <input type="text" name="studentName" id="name" class="form-control" value="${requestScope.student.name}" readonly>
                    </div>

                    <div class="mb-3">
                        <label for="semester" class="form-label">Semester:</label>
                        <input type="text" name="studentSemester" id="semester" class="form-control" value="${requestScope.student.semester}" readonly>
                    </div>

                    <div class="mb-3">
                        <label for="course" class="form-label">Courses:</label>
                        <div class="row">
                            <c:forEach var="course" items="${requestScope.student.courses}">
                                <div class="col-md-3">
                                    <input type="text" name="studentCourse" id="course" class="form-control mb-2" value="${course.code}" readonly>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                    <div>
                        <button type="button" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/student/find-all'">
                            <i class="fas fa-arrow-left"></i> Back
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </body>

</html>
