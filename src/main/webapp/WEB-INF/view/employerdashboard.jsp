<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Active Jobs</title>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
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
  table th, table td {
    text-align: left;
    padding: 12px;
  }
  .closeJobBtn, .viewApplicantsBtn {
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
<table id="activeJobs">
<thead>
<tr class="tablehdr">
    <th style="display:none;">ID</th>
    <th>Job Id</th>
    <th>Title</th>
    <th>Job Type</th>
    <th>Company</th>
    <th>Location</th>
    <th>Deadline</th>
    <th>Prerequisite Courses</th>
    <th style="width:75px"></th>
    <th style="width:75px"></th>
  </tr>
</thead>
<tbody>

</tbody>
</table>

<script>
	  const http = new XMLHttpRequest();
	  var url = window.location.origin + '/activejobs';
	  http.open('GET', url, true);
	  http.send();
	  http.onreadystatechange = function () {
	    if (this.readyState === 4 && this.status === 200) {
	      var jobs = JSON.parse(http.response);
	      jobs.forEach(job => {
	        var activeJobsTable = document.getElementById('activeJobs');
	        var row = activeJobs.insertRow();
	        var colId = row.insertCell(0);
	        colId.style.display = 'none';
	        var colJobId = row.insertCell(1);
	        var colJobTitle = row.insertCell(2);
	        var colJobType = row.insertCell(3);
            var colOrg = row.insertCell(4);
            var colLocation = row.insertCell(5);
            var colAppDeadline = row.insertCell(6);
            var colPrereqCourses = row.insertCell(7);
	        var colViewDetail = row.insertCell(8);
	        var colDelete = row.insertCell(9);
	        colId.innerText = job.id;
	        colJobId.innerText = job.jobId;
	        colJobTitle.innerHTML = '<a href="javascript:void(0)" onclick="viewJob(this)">' + job.jobTitle + '</a>';
	        colJobType.innerText  = job.jobType;
            colOrg.innerText = job.organization;
            colLocation.innerText = job.location;
            colAppDeadline.innerText = job.applicationDeadline;
	        colViewDetail.innerHTML = '<button class="closeJobBtn" onclick="closeJob(this)">Close Job</button>';
	        colDelete.innerHTML = '<button class="viewApplicantsBtn" onclick="viewApplicants(this)">View Applicants</button>';
	      });
	    }
	  };
	  http.onerror = function () {
	    console.log(http.response);
      };
      
      function viewJob(e) {
        console.log(e);
      }
</script>
</body>
</html>