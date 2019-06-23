<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
        <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
        crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
    <script
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
    <script
        src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
    <link rel="stylesheet"
        href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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

        .hiddenStatus {
            display: none;
        }
    </style>
</head>

<body>
    <div>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="/homepage">My Career</a>
                </div>
                <div class="navbar-header">
                    <a class="navbar-brand" href="/logout">Logout</a>
                </div>
            </div>
        </nav>
    </div>
    <button id="btnAddJob" onclick="window.history.back()">Back to job list</button>
    <h1>Applicants for Job Id: ${applicant.jobId}</h1>
    <table id="applications">
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
                    <td>
                        <select name="appStatus" id="appStatus">
                            <c:forEach var="status" items="${appStatus}">
                                <option value="${status}" ${status==applicant.applicationStatus ? 'selected' : '' }>
                                    ${status}</option>
                            </c:forEach>
                        </select>
                        <label class="hiddenStatus">${applicant.applicationStatus}</label>
                    </td>
                    <td><button class="updateStatusBtn" onclick="UpdateStatus(this)">updateStatus</button></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <script>
        function UpdateStatus(srcElement) {
            var currentRowIndex = srcElement.closest('tr').rowIndex;
            var activeJobsTable = document.getElementById('applications');
            var appId = activeJobsTable.rows[currentRowIndex].cells[0].innerText;
            var appStatus = activeJobsTable.rows[currentRowIndex].cells[6];
            if (appStatus.children[1].innerText == appStatus.children[0].value) {
                alert("No changes to update !!!")
            }
            else {
                const httpReq = new XMLHttpRequest();
                httpReq.open('PUT', window.location.origin + '/updateApplicationStatus', true);
                httpReq.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                httpReq.send("applicationId=" + appId + "&appStatus=" + appStatus.children[0].value);
                httpReq.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        if (httpReq.responseText == "true") {
                            alert('Application Status updated!!!')
                        }
                    }
                };
                httpReq.onerror = function () {
                    console.log(http.response);
                };
            }
        }
    </script>
</body>

</html>