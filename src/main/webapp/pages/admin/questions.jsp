<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
 />
<link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css" />
<title>question details</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <a class="navbar-brand" href="/quiz">Home</a>
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
         <c:if test='${empty sessionScope.user}'>
          <li class="nav-item">
            <a class="nav-link" href="pages/login.jsp"
              >Login <span class="sr-only">(current)</span></a
            >
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/quiz/pages/signup.jsp">Register</a>
          </li>
          </c:if>
          <c:if test='${not empty sessionScope.user and sessionScope.user == "admin"}'>
          <li class="nav-item">
            <a class="nav-link" href="pages/admin.jsp"
              >Admin Portal</a>
          </c:if>
        </ul>
        <ul class="navbar-nav ml-auto">
          <c:if test='${not empty sessionScope.user}'>
	          <li class="nav-item">
	            <a class="nav-link" href="#">${sessionScope.user}</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="/quiz/LogoutServlet">Logout</a>
	          </li>
       		</c:if>
        </ul>
      </div>
    </nav>
    <div class="container" style="padding-bottom: 3rem">
	<h1 class="text-center" style="padding-top: 2rem">All questions</h1> 
	<div class="table-responsive" style="padding-top: 3rem">
	<table id="myTable" class="display table">  
        <thead>  
          <tr>  
            <th>question ID</th>
            <th>question type</th>  
            <th>question title</th>  
            <th>actions</th>  
          </tr>  
        </thead>  
        <tbody>
        	<c:forEach items="${requestScope.question}" var="element" >  
	          <tr>  
	            <td>${element.getQuestionID()}</td>
	            <td>${element.getQuizType()}</td>
	            <td>${element.getQuestionContent()}</td>
	            <td><div class="btn-group" role="group">
				    <button id="btnGroupDrop1" type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				      edit
				    </button>
				    <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
				      <a class="dropdown-item" href="#">deactivate</a>
				      <a class="dropdown-item" href="#">activate</a>
				      <a class="dropdown-item" href="#">edit title</a>
				    </div>
				  </div>
			</td>
  			</c:forEach>  
        </tbody>  
      </table>
      </div>
      <a class="btn btn-primary float-right" href="pages/admin/newQuestion.jsp" role="button">add new question</a>
      
      </div>
    	
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script
      src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
    ></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
    ></script>
    <script type="text/javascript"
 	src="//cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js">
    </script>
	<script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
	<script>
	$(document).ready(function(){
	    $('#myTable').dataTable();
	});
	</script>
</body>
</html>