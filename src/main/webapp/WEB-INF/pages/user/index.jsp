<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp" />
<div class="page-wrapper">
	<!-- ============================================================== -->
	<!-- Container fluid  -->
	<!-- ============================================================== -->
	<div class="container-fluid">
		<!-- ============================================================== -->
		<!-- Sales chart -->
		<!-- ============================================================== -->
		<div class="row">
			<!-- Column -->
			<div></div>
			<!-- Column -->
			<!-- Column -->
			<div class="col-sm-12">
				<div class="card">
					<div class="card-body">
						<h4 class="card-title">Recherche des utilisateurs</h4>
						<div class="text-info">
							<div class="row">
								<div class="col-md-6">
									<div class="mb-3">
										<label >Nom  :</label> <input type="text"
											class="form-control" id="nomClient">
									</div>
								</div>
								<div class="col-md-6">
									<div class="mb-3">
										<label >Prénom :</label> <input type="text"
											class="form-control" id="nomConsultant">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="mb-3">
										<label >Profile :</label> 
										<select class="form-control" id="statut">
											<option value=""> ---- Séléctionnée un Profile ----</option>
											<c:forEach  var="profile" items="${ profiles}">
												<option value="${ profile.id}"> ${ profile.description}</option>
											</c:forEach>
										</select>
									</div>
									<div class="mb-3">
										<label >statut :</label> 
										<select class="form-control" id="statut">
											<option value=""> ---- Séléctionnée une statut ----</option>
											<option value="1"> Active</option>
											<option value="0"> Désactivé</option>
										</select>
									</div>
								</div>
							</div>
							
						</div>
						<div class="text-end">
							<a onclick=""
								class="dt-button buttons-collection btn btn-white btn-warning  btn-bold"><span><i
									class="fa fa-search bigger-110 green"></i> <span>Rechercher</span></span></a>
							<a href="${pageContext.request.contextPath}/user/add"
								class="dt-button buttons-collection btn btn-white btn-primary btn-bold"><span><i
									class="fa fa-plus bigger-110 blue"></i> <span>Ajouter</span></span></a>

						</div>
					</div>
				</div>
			</div>
			<!-- Column -->
		</div>
		<br>
		<div class="row">
			<!-- column -->
			<div class="col-sm-12">
				<div class="card">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">Liste des utilisateurs</h4>
							<table id="simple-table"
								class="table  table-bordered table-hover">
								<thead>
									<tr>
										<th>Username</th>
										<th>Nom </th>
										<th>Nom Prénom</th>
										<th>Profile</th>
										<th>Statut</th>
										<th>Actions</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach var="user" items="${ users}">
										<tr>
											<td>${user.userName }</td>
											<td>${user.nom }</td>
											<td>${user.prenom }</td>
											<td>${user.profile }</td>
											<td>${user.enabled}</td>
											<td>
												<div class="hidden-sm btn-group">
													<a class="btn btn-xs btn-success" title="Consulter"
														aria-controls="simple-table"
														href="${pageContext.request.contextPath}/user/consult/${user.userId }"><span>
															<i class="ace-icon fa fa-eye bigger-120"></i>
													</span> </a> <a class="btn btn-xs btn-info" title="Modifier"
														aria-controls="simple-table"
														href="${pageContext.request.contextPath}/user/edit/${user.userId }"><span>
															<i class="ace-icon fa fa-edit bigger-120"></i>
													</span></a>
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>
					</div>
				</div>
			</div>
			<!-- column -->
		</div>
		<!-- ============================================================== -->
		<!-- Table -->
		<!-- ============================================================== -->
		<div class="row">
			<div class="col-sm-12"></div>
		</div>
		<!-- ============================================================== -->
		<!-- Table -->
		<!-- ============================================================== -->

	</div>
	<!-- ============================================================== -->
	<!-- End Container fluid  -->
	<!-- ============================================================== -->
	<jsp:include page="../footer.jsp" />