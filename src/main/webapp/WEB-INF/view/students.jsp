<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <title>Co-op Admin Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <style>
        .coursescheckbox {
            margin-left: 26%;
        }

        .errorMsgColor {
            color: red;
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

    <button id="btnManageStudents" class="buttonmargin btn btn-info"
        onclick="window.location.href = window.location.origin + '/adminHome'">Back to Recruiter List</button>

    <button id="btnRegStudent" data-toggle="modal" data-target="#modalpopup" class="buttonmargin btn btn-info"
        onclick="loadPrerequisiteCourse()">
        Register Student
    </button>

    <div class="modal fade" id="modalpopup" role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Register student</h4>
                    <button type="button" class="class btn btn-info" data-dismiss="modal">&times;</button>

                </div>
                <div class="modal-body">
                    <div>
                        <label class="col-sm-3">First Name</label>
                        <input type="text" id="txtfname" />
                        <label class="col-sm-3">Last Name</label>
                        <input type="text" id="txtlname" />
                    </div>
                    <br>
                    <div>
                        <label class="col-sm-3">Banner id</label>
                        <input type="text" id="txtbannerid" />
                        <label class="col-sm-3">Email</label>
                        <input type="emai" id="txtemail" />
                    </div>
                    <br>
                    <div>
                        <label class="col-sm-3">Phone Number</label>
                        <input type="text" id="txtphone" />
                        <label class="col-sm-3">Degree</label>
                        <input type="text" id="txtdegree" />
                    </div>
                    <div>
                        <br> <label class="col-sm-3">Department</label>
                        <input type="text" id="txtdept" />
                        <label class="col-sm-3">Program</label>
                        <input type="text" id="txtprogram" />
                    </div>
                    <br>
                    <div>
                        <label class="col-sm-3">Completed Courses</label>
                        <div id="courseCompleted" class="coursescheckbox"></div>
                    </div>
                    <br>
                    <div id="errorMsgs">
                        <span id="errMsg"></span>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn btn-info"
                        onclick="registerStudent()">Submit</button>
                </div>
            </div>
        </div>
    </div>
    <br>
    <br>
    <table id="regStudents" class="table table-hover">
        <thead>
            <tr class="table-info">
                <th>ID</th>
                <th>Banner No.</th>
                <th>Student Name</th>
                <th>Email</th>
                <th>Prerequisite Courses</th>
                <th style="width: 75px"></th>
                <th style="width: 75px"></th>
            </tr>
        </thead>

        <c:if test="${not empty registeredStudents}">
            <c:forEach var="regStudent" items="${registeredStudents}">
                <tr>
                    <td>${regStudent.id}</td>
                    <td>${regStudent.bannerid}</td>
                    <td>${regStudent.firstname}</td>
                    <td>${regStudent.email}</td>
                    <td>${regStudent.requiredCourses}</td>
                    <td><button class="btn btn-info" onclick="delActiveStudent(this)">Delete</button></td>
                </tr>
            </c:forEach>
        </c:if>

    </table>

    <script>
        function loadPrerequisiteCourse() {
            clearFields();
            var url = window.location.origin + "/getPrerequisiteCourses";
            fetch(url)
                .then(function (response) {
                    return response.json();
                })
                .then(function (courseList) {
                    console.log(JSON.stringify(courseList));
                    createDynamicCheckbox(courseList);
                })
        }
        function clearFields()
        {
                $("input#txtfname,input#txtlname,input#txtlname,input#txtbannerid,input#txtemail,input#txtphone,input#txtdegree,input#txtdept,input#txtprogram").val("");

        }
        function createDynamicCheckbox(courseList) {
            document.getElementById('courseCompleted').innerHTML = "";
            courseList.forEach(course => {
                var innerDiv = document.createElement("div");
                innerDiv.className = "coursesCheckbox";
                innerDiv.id = "coursesCheckbox";

                var coursecb = document.createElement("input");
                coursecb.type = "checkbox";
                coursecb.value = course.courseId;
                document.getElementById("courseCompleted").appendChild(innerDiv);
                innerDiv.appendChild(coursecb);
                innerDiv.appendChild(document.createTextNode(course.CourseName));
            });

        }

        function fetchSelectedCourses() {
            var selCourses = document.getElementsByClassName('coursesCheckbox');
            var selCourseArray = [];
            for (var i = 0; i < selCourses.length; i++) {
                if (selCourses[i].firstChild.checked) {
                    selCourseArray.push(selCourses[i].firstChild.value);
                }
            }
            return selCourseArray;
        }
        function validateRegistration() {
            isValidForm = true;
            $("#errorMsgs span").empty();
            var selectedCourses = fetchSelectedCourses();
            if ($('#txtfname').val() == "") {
                $("#errorMsgs span").append("* First Name is mandatory <br>").addClass("errorMsgColor");
                isValidForm = false;
            }
            if ($('#txtbannerid').val() == "") {
                $("#errorMsgs span").append("* Banner Number is mandatory <br>").addClass("errorMsgColor");
                isValidForm = false;
            }
            if ($('#txtemail').val() == "") {
                $("#errorMsgs span").append("* Email is mandatory <br>").addClass("errorMsgColor");
                isValidForm = false;
            }
            if ($('#txtdegree').val() == "") {
                $("#errorMsgs span").append("* Degree is mandatory <br>").addClass("errorMsgColor");
                isValidForm = false;
            }
            if ($('#txtprogram').val() == "") {
                $("#errorMsgs span").append("* Program is mandatory <br>").addClass("errorMsgColor");
                isValidForm = false;
            }
            if (selectedCourses.length == 0) {
                $("#errorMsgs span").append("* Prerequisite courses is mandatory").addClass("errorMsgColor");
                isValidForm = false;
            }
            return isValidForm;
        }
        function registerStudent() {
            if (validateRegistration()) {
                var fname = $('#txtfname').val();
                var lname = $('#txtlname').val();
                var bannerid = $('#txtbannerid').val();
                var email = $('#txtemail').val();
                var phone = $('#txtphone').val();
                var degree = $('#txtdegree').val();
                var dept = $('#txtdept').val();
                var pgm = $('#txtprogram').val();
                var completedCourses = fetchSelectedCourses();
                var data = {
                    firstname: fname,
                    lastname: lname,
                    bannerid: bannerid,
                    email: email,
                    phonenumber: phone,
                    degree: degree,
                    department: dept,
                    program: pgm,
                    completedCourses: completedCourses
                };
                var url = window.location.origin + "/registerstudent";
                fetch(url,
                    {
                        method: 'POST',
                        body: JSON.stringify(data),
                        headers:
                        {
                            'Content-Type': 'application/json'
                        }
                    }).then(res => res.json())
                    .then(response => console.log('Success:', JSON.stringify(response)))
                    .catch(error => console.error('Error:', error));
                    $("#modalpopup").hide();
		            window.location.reload(true);
            }
        }
        function delActiveStudent(srcElement) {

            const httpReq = new XMLHttpRequest();
            var currentRowIndex = srcElement.closest('tr').rowIndex;
            var activeStudentTable = document.getElementById('regStudents');
            var id = activeStudentTable.rows[currentRowIndex].cells[0].innerText;
            var params = { id: 1 };
            httpReq.open('DELETE', window.location.origin + '/deletestudent', true);
            httpReq.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            httpReq.send("id=" + id);
            httpReq.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    if (httpReq.responseText == "true") {
                        activeStudentTable.deleteRow(currentRowIndex);
                    }
                }
            };
            httpReq.onerror = function () {
                console.log(http.response);
            };
        }
    </script>
</body>

</html>