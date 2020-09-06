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
<title>result detail</title>
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
    <div class="container">
		  	  <h1 style="padding-top: 2rem">The quiz result of quiz #${requestScope.quizID}:</h1>
  	     	  <p>Time taken: ${requestScope.currentQuiz.getStartTime()}</p>
     	      <p>Quiz grade: ${requestScope.grade} / 5</p>
			<c:forEach var="questionID" items="${requestScope.currentQuiz.getQuestionMap()}">
			  <c:set var="correctID" value="${requestScope.currentQuiz.getCorrectAnswers().get(questionID.key)}" scope="page" />
			  <form>
			  <div class="card-deck mb-3 text-center">
		        <div class="card mb-4 shadow-sm">
		          <div class="card-header">
		            <h4 class="my-0 font-weight-normal">
		            	<c:out value="${requestScope.currentQuiz.getQuestionTitle(questionID.key)}"/>
          			</h4>
          			</div>
	          		<div style="padding-top: 0.5rem" class="form-check">
		              <c:forEach var="choiceID" items="${requestScope.currentQuiz.getChoices(questionID.key)}">
		               <c:set var="userChoice" value="${requestScope.currentQuiz.getUserChoice().get(questionID.key)}" scope="page" />
		               
		               <c:if test="${pageScope.userChoice == choiceID and pageScope.userChoice == pageScope.correctID }"> 
			                <input disabled class="form-check-input choice" type="radio" name="${questionID.key}" id="questionChoice" value="${choiceID}" checked/>
			                <label style="background-color:green" for="${requestScope.currentQuiz.getSingleChoice(choiceID)}">${requestScope.currentQuiz.getSingleChoice(choiceID)}</label>
			              	<br>
		              	</c:if>
		              	<c:if test="${pageScope.userChoice == choiceID and pageScope.userChoice != pageScope.correctID }"> 
			                <input disabled class="form-check-input choice" type="radio" name="${questionID.key}" id="questionChoice" value="${choiceID}" checked/>
			                <label for="${requestScope.currentQuiz.getSingleChoice(choiceID)}">${requestScope.currentQuiz.getSingleChoice(choiceID)}</label>
			              	<br>
		              	</c:if>
		              	<c:if test="${pageScope.userChoice != choiceID and choiceID != pageScope.correctID }">
			                <input disabled class="form-check-input choice" type="radio" name="${questionID.key}" id="questionChoice" value="${choiceID}"/>
			                <label for="${requestScope.currentQuiz.getSingleChoice(choiceID)}">${requestScope.currentQuiz.getSingleChoice(choiceID)}</label>
			              	<br>
		              	 </c:if>
		              	 <c:if test="${pageScope.userChoice != choiceID and choiceID == pageScope.correctID }">
			                <input disabled class="form-check-input choice" type="radio" name="${questionID.key}" id="questionChoice" value="${choiceID}"/>
			                <label style="background-color:green" for="${requestScope.currentQuiz.getSingleChoice(choiceID)}">${requestScope.currentQuiz.getSingleChoice(choiceID)}</label>
			              	<br>
		              	 </c:if>
		               </c:forEach>
		            </div>
		            <c:if test="${  pageScope.userChoice == pageScope.correctID }"><div class="alert alert-success" role="alert">You got it right</div></c:if>
		            <c:if test="${  pageScope.userChoice != pageScope.correctID }"><div class="alert alert-danger" role="alert">You got it wrong</div></c:if>
	          	</div>
         	   </div>
         	   </form>
			</c:forEach>
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
</body>
</html>