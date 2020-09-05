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
	<style>
		body {
			background-color: 	#E8E8E8;
		}
		
		.question-container {
			padding-top: 6rem;
		}
		
		.choice {
			padding-top: 3rem;
		}
	</style>
	<title>quiz detail</title>
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
            <a class="nav-link" href="pages/signup.jsp">Register</a>
          </li>
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
    
     <div class="question-container">
	   <c:if test='${sessionScope.finished == false }'>
		<div class="container">
		
		  <c:if test='${(not empty sessionScope.quizID)}'>
		  	  <c:set var="currentQuestionIndex" value="${sessionScope.currentQuiz.getCurrentQuestionIndex()}" scope="page" />
		  	  <c:set var="quiz" value="${sessionScope.currentQuiz}" scope="page" />
	          <p>Your quiz ID is ${sessionScope.quizID}.</p>
	      	  <p>Start time: ${pageScope.quiz.getStartTime()}</p>
	          <p>Expected end time: ${pageScope.quiz.getEndTime()}</p>
	          <p>Question#: ${pageScope.currentQuestionIndex + 1} / 5</p>
       	  </c:if>
       	  <form action="/quiz/UpdateQuiz" method="post" name="questionForm">
	      <div class="card-deck mb-3 text-center">
	        <div class="card mb-4 shadow-sm">
	          <div class="card-header">
	            <h4 class="my-0 font-weight-normal">
	            <c:set var="questionID" value="${pageScope.quiz.getQuestionID(pageScope.currentQuestionIndex)}" scope="page" />
	              <c:out value="${pageScope.quiz.getQuestionTitle(pageScope.questionID)}"/>
	            </h4>
	            </div>
	            
	            <div style="padding-top: 0.5rem" class="form-check">
	              <c:forEach var="choiceID" items="${pageScope.quiz.getChoices(pageScope.questionID)}">
	                <c:set var="userSelection" value="${sessionScope.currentQuiz.getUserChoice().get(pageScope.questionID)}" scope="page" />
	                <c:if test="${pageScope.userSelection == -1 or pageScope.choiceID != pageScope.userSelection}">
		                <input class="form-check-input choice" type="radio" name="userAnswer" id="questionChoice" value="${pageScope.choiceID}" />
		                <label for="${pageScope.quiz.getSingleChoice(choiceID)}">${pageScope.quiz.getSingleChoice(choiceID)}</label>
	                </c:if>
	                <c:if test="${pageScope.userSelection != -1 and pageScope.choiceID == pageScope.userSelection}">
		                <input class="form-check-input choice" type="radio" name="userAnswer" id="questionChoice" value="${pageScope.userSelection}" checked />
		                <label for="${pageScope.quiz.getSingleChoice(choiceID)}">${pageScope.quiz.getSingleChoice(choiceID)}</label>
	                </c:if>
	              	<br>	             
	               </c:forEach>
	            </div>
	            <div class="card-body">	
		          </div>
		        </div>
		      </div>   
		      <div class="text-center">
		      <c:if test='${pageScope.currentQuestionIndex > 0}'>
		         <button type="submit" name="action" class="btn btn-lg btn-primary" value="prev">
		              previous
            	  </button>
            	</c:if>
              <c:if test='${pageScope.currentQuestionIndex < 4}'>
	      		<button type="submit" name="action" class="btn btn-lg btn-primary" value="next">
		              next
            	</button>
              </c:if>
              <c:if test='${pageScope.currentQuestionIndex >= 4}'>
	      		<button type="submit" name="action" class="btn btn-lg btn-primary" value="submitQuiz">
		              submit
            	</button>
              </c:if>
              </div>
		    </form>
   		 </div>		
		</c:if>
		<!-- quiz result detail -->
		<!-- <div class="container"> -->
		  <c:if test='${sessionScope.finished == true }'>
			<div class="container">
			  <p>Your quiz ID is ${sessionScope.quizID}</p>
      	      <p>Time taken: ${sessionScope.currentQuiz.getStartTime()}</p>
      	      <p>Quiz grade: ${sessionScope.grade} / 5</p>
			<c:forEach var="questionID" items="${sessionScope.currentQuiz.getQuestionMap()}">
			  <c:set var="correctID" value="${sessionScope.currentQuiz.getCorrectAnswers().get(questionID.key)}" scope="page" />
			  <form>
			  <div class="card-deck mb-3 text-center">
		        <div class="card mb-4 shadow-sm">
		          <div class="card-header">
		            <h4 class="my-0 font-weight-normal">
		            	<c:out value="${sessionScope.currentQuiz.getQuestionTitle(questionID.key)}"/>
          			</h4>
          			</div>
	          		<div style="padding-top: 0.5rem" class="form-check">
		              <c:forEach var="choiceID" items="${sessionScope.currentQuiz.getChoices(questionID.key)}">
		               <c:set var="userChoice" value="${sessionScope.currentQuiz.getUserChoice().get(questionID.key)}" scope="page" />
		               <c:if test="${pageScope.userChoice == choiceID and pageScope.userChoice == pageScope.correctID }"> 
			                <input disabled class="form-check-input choice" type="radio" name="${questionID.key}" id="questionChoice" value="${choiceID}" checked/>
			                <label style="background-color:green" for="${sessionScope.currentQuiz.getSingleChoice(choiceID)}">${sessionScope.currentQuiz.getSingleChoice(choiceID)}</label>
			              	<br>
		              	</c:if>
		              	<c:if test="${pageScope.userChoice == choiceID and pageScope.userChoice != pageScope.correctID }"> 
			                <input disabled class="form-check-input choice" type="radio" name="${questionID.key}" id="questionChoice" value="${choiceID}" checked/>
			                <label for="${sessionScope.currentQuiz.getSingleChoice(choiceID)}">${sessionScope.currentQuiz.getSingleChoice(choiceID)}</label>
			              	<br>
		              	</c:if>
		              	<c:if test="${pageScope.userChoice != choiceID and choiceID != pageScope.correctID }">
			                <input disabled class="form-check-input choice" type="radio" name="${questionID.key}" id="questionChoice" value="${choiceID}"/>
			                <label for="${sessionScope.currentQuiz.getSingleChoice(choiceID)}">${sessionScope.currentQuiz.getSingleChoice(choiceID)}</label>
			              	<br>
		              	 </c:if>
		              	 <c:if test="${pageScope.userChoice != choiceID and choiceID == pageScope.correctID }">
			                <input disabled class="form-check-input choice" type="radio" name="${questionID.key}" id="questionChoice" value="${choiceID}"/>
			                <label style="background-color:green" for="${sessionScope.currentQuiz.getSingleChoice(choiceID)}">${sessionScope.currentQuiz.getSingleChoice(choiceID)}</label>
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
		</c:if>
		<!-- </div> -->
	</div>

	
	 <!-- script import -->
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
    <script type="text/javascript">

	
	</script>
</body>
</html>