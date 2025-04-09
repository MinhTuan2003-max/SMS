<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add Student</title>
        <link href="${pageContext.request.contextPath}/assets/bootstrap/bootstrap-5.0.2-dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="${pageContext.request.contextPath}/assets/css/AddStudent.css" rel="stylesheet">
        <style>
            .error-message {
                color: red;
                font-size: 0.9em;
                display: block;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <form id="studentForm" action="${pageContext.request.contextPath}/student/add-new" method="POST" onsubmit="return validateForm()">
                <div class="mb-3">
                    <a href="${pageContext.request.contextPath}/student/find-all" class="btn btn-primary">
                        <i class="fas fa-arrow-left"></i> Back
                    </a>
                </div>
                <div class="mb-3 row">
                    <label for="s-name" class="col-sm-2 col-form-label">Name</label>
                    <div class="col-sm-10">
                        <input class="form-control" name="studentName" type="text" id="s-name" placeholder="Enter Student's Name">
                        <span id="nameError" class="error-message"></span>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="s-semester" class="col-sm-2 col-form-label">Semester</label>
                    <div class="col-sm-10">
                        <input class="form-control" name="semester" type="number" min="1" max="9" step="1" id="s-semester" placeholder="Enter Semester">
                        <span id="semesterError" class="error-message"></span>
                    </div>
                </div>
                <label for="s-course">Course</label>
                <fieldset>
                    <legend>Select Courses</legend>
                    <div class="course-grid">
                        <c:forEach var="course" items="${requestScope.courses}">
                            <label class="course-item">
                                <input name="courseIds" id="s-course" type="checkbox" value="${course.id}">
                                    ${course.code}
                            </label>
                        </c:forEach>
                    </div>
                    <span id="courseError" class="error-message"></span>
                </fieldset>
                <br>
                <button type="submit">Save</button>
                <button type="reset" onclick="clearErrors()">Reset</button>
            </form>
        </div>
        <script src="${pageContext.request.contextPath}/assets/js/ValidateStudentForm.js"></script>
    </body>
</html>
