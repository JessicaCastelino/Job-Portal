<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
            border: 1px solid #ddd;
        }

        .tablehdr {
            background-color: midnightblue;
            color: white;
        }

        table th,
        table td {
            text-align: left;
            padding: 12px;
        }

        .updateStatusBtn {
            background-color: #009933;
            border-color: #00802b;
            border-radius: 7px;
            height: 40px;
            color: white;
            width: 100px;
        }
    </style>
</head>

<body>
    <table>
        <thead>
            <tr class="tablehdr">
                <th style="display:none">ApplicationId</th>
                <th>Applicant ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Courses completed</th>
                <th>Documents</th>
                <th>Status</th>
                <th></th>
            </tr>
        </thead>
        <c:if test="${not empty applicants}">
            <c:forEach var="applicant" items="${applicants}">
                <tr>
                    <td style="display:none">${applicant.applicationId}</td>
                    <td>${applicant.studentId}</td>
                    <td>${applicant.applicantFName} ${applicant.applicantLName}</td>
                    <td>${applicant.email}</td>
                    <td>${applicant.completedCourses}</td>
                    <td></td>
                    <td>${applicant.applicationStatus}</td>
                    <td><button class="updateStatusBtn">updateStatus</button></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</body>

</html>