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
    <button id="btnAddJob" class="btn btn-info" onclick="window.location.href = window.location.origin + '/employerHome'">Back to active jobs</button>
    <h1>Closed Jobs</h1>
    <table id="closedJobs" class="table table-hover">
        <thead>
            <tr class="tablehdr table-info">
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
                    <td><button class="reopenJobBtn btn btn-info" onclick="reOpenJob(this)">Reopen Job</button></td>
                    <td><button class="viewApplicantsBtn btn btn-info" onclick="viewApplicants(this)">View Applicants</button></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <script>
        function reOpenJob(srcElement) {
            const httpReq = new XMLHttpRequest();
            var currentRowIndex = srcElement.closest('tr').rowIndex;
            var closedJobsTable = document.getElementById('closedJobs');
            var id = closedJobsTable.rows[currentRowIndex].cells[0].innerText;
            var params = { id: 1 };

            httpReq.open('PUT', window.location.origin + '/openJob', true);
            httpReq.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            httpReq.send("id=" + id);
            httpReq.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    if (httpReq.responseText == "true") {
                        closedJobsTable.deleteRow(currentRowIndex);
                    }
                }
            };
            httpReq.onerror = function () {
                console.log(http.response);
            };
        }

        function viewApplicants(srcElement) {
            var currentRowIndex = srcElement.closest('tr').rowIndex;
            var closedJobsTable = document.getElementById('closedJobs');
            var jobId = closedJobsTable.rows[currentRowIndex].cells[0].innerText;
            var url = window.location.origin + '/applications?jobId=' + jobId;
            window.location.href = url;
        }
    </script>
</body>

</html>