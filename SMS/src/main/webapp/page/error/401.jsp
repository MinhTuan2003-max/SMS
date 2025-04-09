<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Error 401 Authorization</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f8f9fa;
                font-family: Arial, sans-serif;
                color: #343a40;
                text-align: center;
                padding: 50px;
            }

            h1 {
                color: #dc3545;
                font-size: 4rem;
                margin-bottom: 20px;
            }

            p {
                font-size: 1.25rem;
                margin-bottom: 30px;
            }

            .btn {
                display: inline-block;
                padding: 10px 20px;
                font-size: 1rem;
                font-weight: bold;
                color: #fff;
                background-color: #007bff;
                border: none;
                border-radius: 5px;
                text-decoration: none;
                transition: background-color 0.3s, transform 0.3s;
            }

            .btn:hover {
                background-color: #0056b3;
                transform: scale(1.05);
            }

            .btn i {
                margin-right: 8px;
            }
        </style>
    </head>
    <body>
        <h1>Error 401</h1>
        <p>You don't have permission to access this page.</p>
        <a href="javascript:history.back()" class="btn">
            <i class="fas fa-arrow-left"></i> Go Back
        </a>
    </body>
</html>
