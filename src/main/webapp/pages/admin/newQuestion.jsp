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
<title>add new question</title>
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
    <div class="container col-md-8 order-md-1" style="padding-top: 3rem">
    	<form>
    		<div class="mb-3">
	          <label for="text">Question type</label>
	          <input type="text" class="form-control" placeholder="type of your question type">
        	</div>
        	<div class="mb-3">
	          <label for="text">Question title</label>
	          <input type="text" class="form-control" placeholder="question title">
        	</div>
        	<div class="row">
	          <div class="col-md-6 mb-3">
	            <label for="firstName">Question choice</label>
	            <input type="text" class="form-control"  placeholder="choice content"  required>
	          </div>
	          <div class="col-md-6 mb-3">
	            <label for="lastName">Is the choice correct?</label>
	            <input type="text" class="form-control"  placeholder="Y or N" required>
	          </div>
        	</div>
        	<div class="row">
	          <div class="col-md-6 mb-3">
	            <label for="firstName">Question choice</label>
	            <input type="text" class="form-control"  placeholder="choice content"  required>
	          </div>
	          <div class="col-md-6 mb-3">
	            <label for="lastName">Is the choice correct?</label>
	            <input type="text" class="form-control"  placeholder="Y or N" required>
	          </div>
        	</div>
        	<div class="row">
	          <div class="col-md-6 mb-3">
	            <label for="firstName">Question choice</label>
	            <input type="text" class="form-control"  placeholder="choice content"  required>
	          </div>
	          <div class="col-md-6 mb-3">
	            <label for="lastName">Is the choice correct?</label>
	            <input type="text" class="form-control"  placeholder="Y or N" required>
	          </div>
        	</div>
        	<div class="row">
	          <div class="col-md-6 mb-3">
	            <label for="firstName">Question choice</label>
	            <input type="text" class="form-control"  placeholder="choice content"  required>
	          </div>
	          <div class="col-md-6 mb-3">
	            <label for="lastName">Is the choice correct?</label>
	            <input type="text" class="form-control"  placeholder="Y or N" required>
	          </div>
        	</div>
        	<a class="btn btn-primary float-right" href="/quiz/AdminQuestionServlet" role="button">Submit</a>
    	</form>
    </div>
</body>
</html>