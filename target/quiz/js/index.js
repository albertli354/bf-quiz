$(document).ready(function () {
	
	$("#login-btn").click(function (e) {
		e.preventDefault();
		const username = $('#loginUsernameInput').val();
		const password = $('#loginPasswordInput').val();
		if (username === "" || password === "") {
			window.alert("Please enter a valid username or password");
			return;
		}
		
		
		$.ajax({
			url: '/quiz/LoginServlet',
			data: {username, password},
			dataType: 'json',
			type: 'post',
			success: function(data) {
				if (data === "true") {
					window.location.href = "/quiz/index.jsp";
				} else if (data === "logged") {
					window.alert("You already logged in");
					window.location.href = "/quiz/index.jsp";
				} else {
					window.alert("Sorry, the username or password is not correct!");
				}
			}
		});
	});

	$("#signup-btn1").click(function (e) {
	    e.preventDefault();
	    const username = $('#signupUsernameInput').val();
		const password = $('#signupPasswordInput').val();
		if (username === "" || password === "") {
			window.alert("Please enter a valid username or password");
			return;
		}
		$.ajax({
			url: '/quiz/SignupServlet',
			data: {username, password},
			dataType: 'json',
			type: 'post',
			success: function(data) {
				console.log(data);
				if (data === "true") {
					window.location.href = "/quiz/index.jsp";
				} else if (data === "invalid") {
					window.alert("Please enter valid input");
				}else {
					window.alert("You already registered, please login");
					window.location.href = "/quiz/pages/login.jsp";
				}
			}
		});
 	});


	// check cookies for login before taking quiz
	$("#java, #js, #react").click(function (e) {
	    e.preventDefault();
	    const userCookies = $.cookie("user");
		if (typeof userCookies === "undefined") {
			window.alert("please login first");
			window.location.href = "/quiz/pages/login.jsp";
		} else {
			const quizType = $(this).attr("data-type");
			
		}
 	});

	



	
	
});
