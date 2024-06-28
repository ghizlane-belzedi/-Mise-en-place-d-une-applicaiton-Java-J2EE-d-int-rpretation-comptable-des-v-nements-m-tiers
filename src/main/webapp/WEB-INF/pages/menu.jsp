<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!-- Sidebar -->
<ul
	class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
	id="accordionSidebar">

	<!-- Sidebar - Brand -->
	<a
		class="sidebar-brand d-flex align-items-center justify-content-center"
		href="${pageContext.request.contextPath}/home">
		<div class="sidebar-brand-icon">
			<img style="width: 100%;" alt=""
				src="https://www.autonejma.ma/media/uploads/marque/logo_mercedes_ez7ZLoO.jpeg">
		</div>
		<div class="sidebar-brand-text mx-3">AUTONEJMA</div>
	</a>

	<!-- Divider -->
	<hr class="sidebar-divider my-0">

	<!-- Nav Item - Dashboard -->
	<!--
	<li class="nav-item active"><a class="nav-link" href="index.html">
			<i class="fas fa-fw fa-tachometer-alt"></i> <span>Dashboard</span>
	</a></li>
	-->
	<li class="nav-item"><a class="nav-link"
		href="${pageContext.request.contextPath}/chargement/"
		aria-expanded="false"><i class="me-3 fas fa-spinner"
			aria-hidden="true"></i> <span class="hide-menu"> Chargement </span></a></li>
	<li class="nav-item"><a class="nav-link"
		href="${pageContext.request.contextPath}/simulation/"
		aria-expanded="false"><i class="me-3 fa fa-play"
			aria-hidden="true"></i><span class="hide-menu">Simulation</span></a></li>
	<li class="nav-item"><a class="nav-link"
		href="${pageContext.request.contextPath}/piece-comptable/"
		aria-expanded="false"><i class="me-3 fa fa-receipt"
			aria-hidden="true"></i><span class="hide-menu">Pièces comptables</span></a></li>
	<hr class="sidebar-divider">
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true"
		aria-controls="collapseTwo"> <i class="fas fa-fw fa-cog"></i> <span>Administration</span>
	</a>
		<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
			data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<sec:authorize access="hasAuthority('USER')">
					<a href="${pageContext.request.contextPath}/user/" class="collapse-item"><i class="fa fa-user"></i><span
						class="hide-menu"> Gestion des utilisateur </span></a>
				</sec:authorize>
				<sec:authorize access="hasAuthority('PROFILE')">
					<a href="${pageContext.request.contextPath}/profile/" class="collapse-item"><i
						class="fa fa-users"></i><span class="hide-menu"> Gestion
							des profiles </span></a>
				</sec:authorize>
			</div>
		</div></li>

	<!-- Nav Item - Utilities Collapse Menu -->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseUtilities"
		aria-expanded="true" aria-controls="collapseUtilities"> <i
			class="fas fa-fw fa-wrench"></i> <span>Paramétrage</span>
	</a>
		<div id="collapseUtilities" class="collapse"
			aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<a href="${pageContext.request.contextPath}/parametrage-flux/"
					class="collapse-item"> <i class="fa fa-project-diagram"></i> <span
					class="hide-menu"> Flux </span>
				</a>
				<a href="${pageContext.request.contextPath}/compte/" class="collapse-item"> 
					<i class="fa fa-wallet"></i> 
					<span class="hide-menu">Comptes des frais <br> d'approche</span>
				</a>
				<a href="${pageContext.request.contextPath}/compte-facturation/"
					class="collapse-item"> <i class="fa fa-wallet"></i> <span
					class="hide-menu"> Comptes de facturation</span>
				</a>
				<a href="${pageContext.request.contextPath}/compte-versement/"
					class="collapse-item"> <i class="fa fa-wallet"></i> <span
					class="hide-menu"> Comptes de versement</span>
				</a>
				<a href="${pageContext.request.contextPath}/client/"
					class="collapse-item"> <i class="fa fa-users"></i> <span
					class="hide-menu"> Clients </span>
				</a>
				<a href="${pageContext.request.contextPath}/parametrageSupplier/"
					class="collapse-item"> <i class="fa fa-users"></i> <span
					class="hide-menu"> Fournisseurs </span>
				</a>
				<a href="${pageContext.request.contextPath}/parametrages/"
					class="collapse-item"> <i class="fa fa-fw fa-wrench"></i><span
					class="hide-menu"> Autres paramètres </span>
				</a> 
				<%--<a href="#" class="collapse-item"><i
					class="fa fa-users"></i><span class="hide-menu"> Gestion des
						Sites </span></a> <a href="#" class="collapse-item"><i class="fa fa-user"></i><span
					class="hide-menu"> Gestion des Devices </span></a> <a href="#"
					class="collapse-item"><i class="fa fa-users"></i><span
					class="hide-menu"> Gestion des Marques </span></a>--%>
			</div>
		</div></li>

	<!-- Divider -->
	<hr class="sidebar-divider"></hr>

	<!-- Sidebar Toggler (Sidebar) -->
	<div class="text-center d-none d-md-inline">
		<button class="rounded-circle border-0" id="sidebarToggle"></button>
	</div>
</ul>
<!-- End of Sidebar -->