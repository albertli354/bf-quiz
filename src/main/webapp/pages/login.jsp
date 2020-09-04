<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0" /> -->
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link
      href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      rel="stylesheet"
      id="bootstrap-css"
    />
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    
    <link rel="stylesheet" href="../css/login.css" />
    <title>Login page</title>
  </head>
  <body>
    <div>
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
            <a class="nav-link" href="pages/signup.jsp">Signup</a>
          </li>
          </c:if>
        </ul>
        <ul class="navbar-nav ml-auto">
          <c:if test='${not empty sessionScope.user}'>
	          <li class="nav-item">
	            <a class="nav-link" href="#">${sessionScope.user}</a>
	          </li>
       		</c:if>
        </ul>
  		
      </div>
    </nav>

      <div class="wrapper fadeInDown">
        <div id="formContent">
          <!-- Tabs Titles -->

          <!-- Icon -->
          <div class="fadeIn first" id="welcome">Welcome back to QuizVisor</div>

          <!-- Login Form -->
          <form>
            <input
              type="text"
              id="loginUsernameInput"
              class="fadeIn second"
              name="login"
              placeholder="username"
            />
            <input
              type="password"
              id="loginPasswordInput"
              class="fadeIn third"
              name="login"
              placeholder="password"
            />
            <input type="submit" id="login-btn" class="fadeIn fourth" value="Log In" />
          </form>

          <!-- Remind Passowrd -->
          <div id="formFooter">
            <a class="underlineHover" href="#">Forgot Password?</a>
          </div>
        </div>
      </div>
    </div>
    <!-- script import -->
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script
      src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
    ></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
    ></script>
    <script src="../js/index.js"></script>
  </body>
</html>
