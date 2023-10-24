<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login-PAGEs</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<style>
body {
	padding: 2rem 0;
}
</style>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
</head>
<body>
<% String email = (String)request.getAttribute("email");
if(email != null){
	out.print(email);
}
%>
	<div class="container">
		<div class="row mx-0 justify-content-center">
			<div class="col-md-7 col-lg-5 px-lg-2 col-xl-4 px-xl-0 px-xxl-3">
				<form class="w-100 rounded-1 p-4 border bg-white"
					action="userController" method="post" id="reg">
					
					<h2 class="text-center">Forgot Password</h2>
					<label class="d-block mb-4"> <span
						class="form-label d-block"></span> <input name="email"
						type="email" class="form-control" placeholder="Email" />
					</label>

					<div class="mb-3">
						<button name="action" value="sendotp"
							class="btn btn-primary px-3 rounded-3">Send OTP</button>

					</div>
					<div class="mb-3"></div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>