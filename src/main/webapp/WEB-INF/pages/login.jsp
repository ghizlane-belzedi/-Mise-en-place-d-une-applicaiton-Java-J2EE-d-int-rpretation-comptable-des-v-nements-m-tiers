<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Brique comptable - Login</title>

<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath}/assets1/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link
	href="${pageContext.request.contextPath}/assets1/css/sb-admin-2.min.css"
	rel="stylesheet">
<style type="text/css">
.bg-login-image {
	background:
		url("${pageContext.request.contextPath}/assets1/img/autoNejma-3.jpg");
	background-position: center;
	background-size: cover;
}
</style>
</head>

<body class="bg-gradient-primary">

	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">
			<div class="text-center" style="margin-top: 20px">
				<h1 class="h4 text-gray-900 mb-4" style="color: black !important">
					Bienvenue dans la brique comptable !</h1>
			</div>

			<div style="width: 110%">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<div class="col-lg-8 d-none d-lg-block"> 
								<img style="width: 100%; height: auto;"
									src="${pageContext.request.contextPath}/assets1/img/autoNejma-8.png" />
							</div>
							<div class="col-lg-4">
								<div class="p-5">
									<div class="text-center">
										<h6 class="header lighter bigger">
											<i class="fas fa-sign-in green"></i> Merci de saisir votre
											identifiant
										</h6>
									</div>
									<form class="user" name='f'
										action="${pageContext.request.contextPath}/login"
										method='POST'>
										<div class="form-group">
											<input type="text" class="form-control form-control-user"
												id="username" name="username" placeholder="Username ...">
										</div>
										<div class="form-group">
											<input type="password" class="form-control form-control-user"
												id="password" name="password" placeholder="Password">
										</div>
										<button type="submit"
											class="btn btn-primary btn-user btn-block">
											<i class="fa fa-key"></i> <span class="bigger-110">Login</span>
										</button>
									</form>
									<hr>
									<!--  <div class="text-center">
                                        <a class="small" href="forgot-password.html">Forgot Password?</a>
                                    </div>-->
								</div> <!-- p-5 -->
								
							</div>
						</div>
						<!-- row -->
					</div>
				</div>

			</div>

		</div>

	</div>

	<!-- Bootstrap core JavaScript-->
	<script
		src="${pageContext.request.contextPath}/assets1/vendor/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets1/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="${pageContext.request.contextPath}/assets1/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script
		src="${pageContext.request.contextPath}/assets1/js/sb-admin-2.min.js"></script>

</body>

</html>