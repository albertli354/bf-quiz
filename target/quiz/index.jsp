<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0" /> -->
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
    />
    <link rel="stylesheet" href="css/style.css" />
    <title>Welcome to QuizVisor</title>
  </head>
  <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <a class="navbar-brand" href="#">Home</a>
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
          <li class="nav-item">
            <a class="nav-link" href="pages/login.jsp"
              >Login <span class="sr-only">(current)</span></a
            >
          </li>
          <li class="nav-item">
            <a class="nav-link" href="pages/signup.jsp">Signup</a>
          </li>
        </ul>
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link" href="#">Contact us</a>
          </li>
        </ul>
        <%-- <c:set var = "salary" scope = "session" value = "${2000*2}"/>
        <c:if test = "${salary > 2000}">
	        <ul class="navbar-nav ml-auto">
	          <li class="nav-item">
	            <a class="nav-link" href="#">${sessionScope.user}</a>
	          </li>
	        </ul>
        </c:if> --%>
      </div>
    </nav>
    
    <main role="main">
      <section class="jumbotron text-center">
        <div class="container">
          <h1>Welcome to QuizVisor</h1>
          <p class="lead text-muted">
            QuizVisor is an online platform that makes learning and evaluating
            programming knowledge enjoyable. <br>Please login or register to use the
            product.
          </p>
          <p>
            <a href="pages/signup.jsp" class="btn btn-primary my-2" id="tet">Register</a>
            <a href="pages/login.jsp" class="btn btn-secondary my-2">Login</a>
          </p>
        </div>
      </section>

      <div class="album py-5 bg-light">
        <div class="container">
          <div class="row">
            <div class="col-md-4">
              <div class="card mb-4 shadow-sm">
                <img src="css/java.jpg" class="card-img-top" alt="java">
                <div class="card-body">
                  <p class="card-text">
                    Java is a set of computer software that provides a system
                    for developing software in a cross-platform computing
                    environment.
                  </p>
                  <div
                    class="d-flex justify-content-between align-items-center"
                  >
                    <div class="btn-group">
                      <button
                        type="button"
                        id="java"
                        data-type="java"
                        class="btn btn-sm btn-outline-secondary"
                      >
                        Take the quiz
                      </button>
                    </div>
                    <small class="text-muted">5 questions</small>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-4">
              <div class="card mb-4 shadow-sm">
              <img src="css/javascript.jpg" class="card-img-top" alt="javascript">
               
                <div class="card-body">
                  <p class="card-text">
                    JavaScript (JS) is a lightweight, interpreted programming
                    language. It is most well-known as the scripting language
                    for Web pages.
                  </p>
                  <div
                    class="d-flex justify-content-between align-items-center"
                  >
                    <div class="btn-group">
                      <button
                        type="button"
                        id="js"
                        data-type="js"
                        class="btn btn-sm btn-outline-secondary"
                      >
                        Take the quiz
                      </button>
                    </div>
                    <small class="text-muted">5 questions</small>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-4">
              <div class="card mb-4 shadow-sm">
                <img src="css/react.png" class="card-img-top" alt="react">
                <div class="card-body">
                  <p class="card-text">
                    React is an open-source JavaScript library for building user
                    interfaces. React can be used as a base in the development
                    of single-page or mobile applications.
                  </p>
                  <div
                    class="d-flex justify-content-between align-items-center"
                  >
                    <div class="btn-group">
                      <button
                        type="button"
                        id="react"
                        data-type="react"
                        class="btn btn-sm btn-outline-secondary"
                      >
                        Take the quiz
                      </button>
                    </div>
                    <small class="text-muted">5 questions</small>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
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
    <script src="js/index.js"></script>
  </body>
</html>
