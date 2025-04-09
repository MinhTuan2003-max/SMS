<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Search Student</title>
        <link href="${pageContext.request.contextPath}/assets/bootstrap/bootstrap-5.0.2-dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/assets/bootstrap/bootstrap-5.0.2-dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="${pageContext.request.contextPath}/assets/css/SearchStudentByCourse.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="mb-4">Search Students by Course</h2>

            <form id="searchForm" action="${pageContext.request.contextPath}/student/search-student-by-course" method="get">
                <div class="mb-3">
                    <label for="course" class="form-label">Course:</label>
                    <select id="course" name="courseID" class="form-select" onchange="document.getElementById('searchForm').submit();">
                        <option value="0">-- Select Course --</option>
                        <c:forEach var="course" items="${requestScope.courses}">
                            <option value="${course.id}"
                                    <c:if test="${course.id eq param.courseID}">
                                        selected="selected"
                                    </c:if>>
                                    ${course.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <table class="table table-bordered table-striped">
                    <thead class="table-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Semester</th>
                            <th scope="col">Update</th>
                            <th scope="col">Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${requestScope.students}" varStatus="loopStatus">
                            <tr>
                                <th scope="row">${loopStatus.count}</th>
                                <td>
                                    <a class="student-link" href="${pageContext.request.contextPath}/student/get-detail?id=${student.id}">
                                            ${student.name}
                                    </a>
                                </td>
                                <td>${student.semester}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/student/update?id=${student.id}" class="btn btn-warning btn-sm">
                                        <i class="fas fa-edit"></i> Update
                                    </a>
                                </td>
                                <td>
                                    <button class="btn btn-danger btn-sm" onclick="deleteStudent(${student.id})">
                                        <i class="fas fa-trash-alt"></i> Delete
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>

            <a href="${pageContext.request.contextPath}/home" class="btn btn-primary btn-sm mt-3">
                <i class="fas fa-arrow-left"></i> Back
            </a>
        </div>
    </body>
</html>
