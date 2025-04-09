<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Student List</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="${pageContext.request.contextPath}/assets/bootstrap/bootstrap-5.0.2-dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/StudentList.css" rel="stylesheet">
        <script>
            function deleteStudent(id) {
                let conf = confirm("Are you sure to delete student with ID: " + id + "?");
                if (conf) {
                    window.location.href = 'delete?id=' + id;
                }
            }
        </script>
        <style>
            .button-container {
                display: flex;
                justify-content: space-between;
                align-items: center;
                width: 100%;
            }
            .button-group {
                display: flex;
                gap: 10px;
            }
            .logout-btn {
                margin-left: 20px;
                margin-right: 20px;
            }
        </style>
    </head>
    <body>

        <div class="container mt-5">
            <h1 class="text-center mb-4">Student List</h1>
            <c:if test="${not empty param.message}">
                <div class="alert alert-success" role="alert">
                    <c:out value="${param.message}" />
                </div>
            </c:if>
            <form id="searchForm" action="${pageContext.request.contextPath}/student/find-all" method="get">
                <div class="mb-3">
                    <label for="course" class="form-label">Search Student By Course:</label>
                    <select style="width: 30%" id="course" name="courseID" class="form-select" onchange="document.getElementById('searchForm').submit();">
                        <option value="0">-- All Course --</option>
                        <c:forEach var="course" items="${requestScope.courses}">
                            <option value="${course.id}"
                                    <c:if test="${course.id eq param.courseID}">
                                        selected="selected"
                                    </c:if>>
                                ${course.name} - (${course.code})
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </form>
            <div class="button-container">
                <div class="button-group">
                    <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">Home</a>
                </div>
            </div>
            <table class="table table-striped">
                <thead class="table-head">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Semester</th>
                        <th scope="col">Course</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="student" items="${requestScope.students}" varStatus="loopStatus">
                        <tr>
                            <td>${loopStatus.count}</td>
                            <td>${student.name}</td>
                            <td>${student.semester}</td>
                            <td>
                                <c:forEach var="course" items="${student.courses}">
                                    ${course.code}<br>
                                </c:forEach>
                            </td>
                            <td style="width: 20%">
                                <a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/student/get-detail?id=${student.id}" >
                                    <i class="fas fa-eye"></i> Detail
                                </a>

                                <a href="${pageContext.request.contextPath}/student/update?id=${student.id}" class="btn btn-warning btn-sm">
                                    <i class="fas fa-edit"></i> Update
                                </a>

                                <button class="btn btn-danger btn-sm" onclick="deleteStudent(${student.id})">
                                    <i class="fas fa-trash"></i> Delete
                                </button>
                            </td>
                        </tr>
                    </c:forEach>

                    <nav aria-label="Page navigation">
                        <ul class="pagination justify-content-center">
                            <c:if test="${requestScope.currentPage > 1}">
                                <li class="page-item">
                                    <a class="page-link" href="?courseID=${param.courseID}&page=${requestScope.currentPage - 1}&pageSize=${requestScope.pageSize}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>

                            <c:forEach var="i" begin="1" end="${requestScope.totalPages}">
                                <li class="page-item <c:if test='${i == requestScope.currentPage}'>active</c:if>">
                                    <a class="page-link" href="?courseID=${param.courseID}&page=${i}&pageSize=${requestScope.pageSize}">${i}</a>
                                </li>
                            </c:forEach>

                            <c:if test="${requestScope.currentPage < requestScope.totalPages}">
                                <li class="page-item">
                                    <a class="page-link" href="?courseID=${param.courseID}&page=${requestScope.currentPage + 1}&pageSize=${requestScope.pageSize}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </tbody>
            </table>
            <div class="button-container">
                <div class="button-group">
                    <a href="${pageContext.request.contextPath}/student/add-new" class="btn btn-primary">Add New</a>
                </div>
                <a  class="btn btn-danger logout-btn text-decoration-none text-white " href="${pageContext.request.contextPath}/auth/logout">
                    <i class="fas fa-sign-out-alt"></i> Logout
                </a>
            </div>
        </div>
    </body>
</html>
