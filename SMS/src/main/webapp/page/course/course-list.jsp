<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Course Lists</title>
        <link href="${pageContext.request.contextPath}/assets/bootstrap/bootstrap-5.0.2-dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="${pageContext.request.contextPath}/assets/css/CourseList.css" rel="stylesheet">
        <script>
            function deleteCourse(id) {
                let conf = confirm("Are you sure to delete course with ID: " + id + "?");
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
            <h1 class="text-center mb-4">Course List</h1>

            <c:if test="${not empty param.message}">
                <div class="alert alert-success" role="alert">
                    <c:out value="${param.message}" />
                </div>
            </c:if>

            <div class="button-container mb-4">
                <div class="button-group">
                    <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">Home</a>
                </div>
            </div>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Code</th>
                        <th scope="col">Name</th>
                        <th scope="col">Credit</th>
                        <th scope="col" colspan="2">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="course" items="${requestScope.courses}" varStatus="loopStatus">
                        <tr>
                            <td>${loopStatus.count}</td>
                            <td>${course.code}</td>
                            <td>${course.name}</td>
                            <td>${course.credit}</td>

                            <td style="width: 25%">
                                <a href="${pageContext.request.contextPath}/course/update?id=${course.id}" class="btn btn-warning btn-sm">
                                    <i class="fas fa-edit"></i> Update
                                </a>
                                <a href="${pageContext.request.contextPath}/course/list-student-by-course?id=${course.id}" class="btn btn-primary btn-sm">
                                    <i class="fas fa-users"></i> List Students
                                </a>
                                <button onclick="deleteCourse(${course.id})" type="button" class="btn btn-danger btn-sm">
                                    <i class="fas fa-trash-alt"></i> Delete
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:if test="${requestScope.currentPage > 1}">
                        <li class="page-item">
                            <a class="page-link" href="?page=${requestScope.currentPage - 1}&pageSize=${requestScope.pageSize}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach var="i" begin="1" end="${requestScope.totalPages}" varStatus="status">
                        <li class="page-item <c:if test="${requestScope.currentPage == i}">active</c:if>">
                            <a class="page-link" href="?page=${i}&pageSize=${requestScope.pageSize}">${i}</a>
                        </li>
                    </c:forEach>
                    <c:if test="${requestScope.currentPage < requestScope.totalPages}">
                        <li class="page-item">
                            <a class="page-link" href="?page=${requestScope.currentPage + 1}&pageSize=${requestScope.pageSize}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>
            <div class="button-container">
                <div class="button-group">
                    <a href="${pageContext.request.contextPath}/course/add-new" class="btn btn-primary">Add New</a>
                </div>
                <a  class="btn btn-danger logout-btn text-decoration-none text-white " href="${pageContext.request.contextPath}/auth/logout">
                    <i class="fas fa-sign-out-alt"></i> Logout
                </a>
            </div>
        </div>
    </body>
</html>