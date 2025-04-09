<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>List Students By Course</title>
        <link href="${pageContext.request.contextPath}/assets/bootstrap/bootstrap-5.0.2-dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/ListStudentByCourse.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    </head>
    <body>
        <div class="page-header">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-auto">
                        <a href="${pageContext.request.contextPath}/course/find-all" class="btn btn-outline-primary back-button">
                            <i class="bi bi-arrow-left"></i> Back
                        </a>
                    </div>
                    <div class="col text-center">
                        <h2 class="mb-0">Student Lists</h2>
                    </div>
                    <div class="col-auto">
                        <!-- Placeholder div for symmetry -->
                        <div style="width: 87px;"></div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Main Content -->
        <div class="table-container">
            <table class="table student-table table-bordered table-hover align-middle mb-0">
                <thead>
                    <tr class="text-center">
                        <th class="py-3">ID</th>
                        <th class="py-3">Name</th>
                        <th class="py-3">Semester</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="student" items="${requestScope.students}" varStatus="loopStatus">
                        <tr class="text-center">
                            <td class="py-3">${student.id}</td>
                            <td class="py-3">${student.name}</td>
                            <td class="py-3">${student.semester}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Pagination -->
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                        <a class="page-link" href="?page=${currentPage - 1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <c:forEach begin="1" end="${totalPages}" var="pageNumber">
                        <li class="page-item ${pageNumber == currentPage ? 'active' : ''}">
                            <a class="page-link" href="?page=${pageNumber}">${pageNumber}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                        <a class="page-link" href="?page=${currentPage + 1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>

        <!-- Bootstrap JS Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
