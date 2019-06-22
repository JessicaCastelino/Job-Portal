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

        .reopenJobBtn,
        .viewApplicantsBtn {
            background-color: #009933;
            border-color: #00802b;
            border-radius: 7px;
            height: 60px;
            color: white;
            width: 100px;
        }
    </style>
</head>

<body>
    <table id="closedJobs">
        <thead>
            <tr class="tablehdr">
                <th>Job Id</th>
                <th>Title</th>
                <th>Job Type</th>
                <th>Company</th>
                <th>Location</th>
                <th>Deadline</th>
                <th>Prerequisite Courses</th>
                <th style="width: 75px"></th>
                <th style="width: 75px"></th>
            </tr>
        </thead>
        <c:if test="${not empty closedJobs}">
            <c:forEach var="job" items="${closedJobs}">
                <tr>
                    <td>${job.id}</td>
                    <td>${job.jobTitle}</td>
                    <td>${job.jobType}</td>
                    <td>${job.organization}</td>
                    <td>${job.location}</td>
                    <td>${job.applicationDeadline}</td>
                    <td>${job.requiredCourses}</td>
                    <td><button class="reopenJobBtn" onclick="reOpenJob(this)">Reopen Job</button></td>
                    <td><button class="viewApplicantsBtn" onclick="viewApplicants(this)">View Applicants</button></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <script>
        function reOpenJob(srcElement) {
            const httpReq = new XMLHttpRequest();
            var currentRowIndex = srcElement.closest('tr').rowIndex;
            var activeJobsTable = document.getElementById('closedJobs');
            var id = activeJobsTable.rows[currentRowIndex].cells[0].innerText;
            var params = { id: 1 };

            httpReq.open('PUT', baseUrl + '/closeJob', true);
            httpReq.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            httpReq.send("id=" + id);
            httpReq.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    if (httpReq.responseText == "true") {
                        activeJobsTable.deleteRow(currentRowIndex);
                    }
                }
            };
            httpReq.onerror = function () {
                console.log(http.response);
            };
        }

        function viewApplicants(srcElement) {
            var currentRowIndex = srcElement.closest('tr').rowIndex;
            var activeJobsTable = document.getElementById('closedJobs');
            var jobId = activeJobsTable.rows[currentRowIndex].cells[0].innerText;
            var url = window.location.origin + '/applications?jobId=' + jobId;
            window.location.href = url;
        }
    </script>
</body>

</html>