<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>AUTONEJMA - BRIQUE COMPTABLE</title>

<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath}/assets1/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css"></link>
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link
	href="${pageContext.request.contextPath}/assets1/css/sb-admin-2.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets1/css/jquery.dataTables.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets1/css/buttons.dataTables.min.css"
	rel="stylesheet">
<!-- Bootstrap core JavaScript-->
<script
	src="${pageContext.request.contextPath}/assets1/vendor/jquery/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets1/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<style type="text/css">
.text-info label {
	color: #0178b6 !important;
}

.bg-gradient-primary {
	color: #0178b6 !important;
	background-image: linear-gradient(180deg, #0178b6 10%, #0178b6 100%);
}

.text-primary {
	color: #0178b6 !important;
}
form label {
	color: #0178b6 !important;
}
.error {
	color: red;
	font-size : 1em;
}

h1 {
    font-size: 18px !important;
    font-weight: 800 !important;
}

h3 {
    font-size: 16px !important;
    font-weight: 800 !important;
}

h5 {
    font-size: 14px !important;
    font-weight: 800 !important;
}

label {
    font-size: 14px !important;
    /* font-weight: 800 !important; */
}
.modal-xxl {
	max-width: 80%;
}
</style>
</head>

<body id="page-top">
<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Se d�connecter</h5>
				
				</button>
			</div>
			<div class="modal-body">S�lectionnez "D�connexion" ci-dessous si vous �tes pr�t
pour terminer votre session en cours.</div>
			<div class="modal-footer">
				<button class="btn btn-secondary" type="button" data-dismiss="modal">Annuler</button>
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/logout">D�connexion</a>
			</div>
		</div>
	</div>
</div>
	<!-- Page Wrapper -->
	<div id="wrapper">

		<jsp:include page="./menu.jsp" />

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						<div class="topbar-divider d-none d-sm-block"></div>

						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small"><sec:authentication
										property="principal.username" /></span> <img
								class="img-profile rounded-circle"
								src="${pageContext.request.contextPath}/assets1/img/undraw_profile.svg">
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item" href="#"> <i
									class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Profile
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									Logout
								</a>
							</div></li>

					</ul>

				</nav>
				<!-- End of Topbar -->