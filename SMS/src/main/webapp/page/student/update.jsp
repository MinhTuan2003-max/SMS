<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Update Student</title>
        <link href="${pageContext.request.contextPath}/assets/bootstrap/bootstrap-5.0.2-dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="${pageContext.request.contextPath}/assets/css/UpdateStudent.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center">Update Student</h1>

            <form action="${pageContext.request.contextPath}/student/update" method="post" onsubmit="return validateForm()">
                <div class="mb-3">
                    <a href="${pageContext.request.contextPath}/student/find-all" class="btn btn-primary btn-sm mt-3">
                        <i class="fas fa-arrow-left"></i> Back
                    </a>
                </div>

                <div class="mb-3">
                    <label for="s-id" class="form-label">ID</label>
                    <input name="id" id="s-id" class="form-control" value="${requestScope.student.id}" readonly>
                </div>

                <div class="mb-3">
                    <label for="s-name" class="form-label">Name</label>
                    <input type="text" name="name" id="s-name" class="form-control" value="${requestScope.student.name}">
                    <span id="nameError" class="text-danger"></span>
                </div>

                <div class="mb-3">
                    <label for="s-semester" class="form-label">Semester</label>
                    <input type="text" id="s-semester" name="semester" class="form-control" value="${requestScope.student.semester}">
                    <span id="semesterError" class="text-danger"></span>
                </div>

                <fieldset class="mb-3">
                    <legend>Select Courses</legend>
                    <div class="row">
                        <c:forEach var="course" items="${requestScope.courses}">
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input
                                    <jsp:useBean id="selectedCourses" scope="request" type="java.util.List"/>
                                    <c:forEach var="selectedCourse" items="${selectedCourses}">
                                    <c:if test="${course.id == selectedCourse.id}">
                                            checked="checked"
                                    </c:if>
                                    </c:forEach>
                                            name="courseIds" id="course-${course.id}" class="form-check-input" type="checkbox" value="${course.id}">
                                    <label class="form-check-label" for="course-${course.id}">${course.code}</label>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <span id="courseError" class="text-danger"></span>
                </fieldset>

                <div>
                    <button type="submit" class="btn btn-primary">Update</button>
                    <button type="reset" class="btn btn-secondary" onclick="clearErrors()">Reset</button>
                </div>
            </form>
        </div>

        <script src="${pageContext.request.contextPath}/assets/js/ValidateStudentForm.js"></script>
    </body>

</html>
