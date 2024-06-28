<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp" />
<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800"> PROFILE -> INDEX </h1>
					</div>

					<div class="row">

						<div class="col-xl-12 col-lg-7">
							<div class="card shadow mb-4">
								<!-- Card Header - Dropdown -->
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h5 class="m-0 font-weight-bold text-primary">Recherche
										des Profiles</h5>
								</div>
								<!-- Card Body -->
								<div class="card-body">
									<div class="text-info">
							<div class="row">
								<div class="col-md-6">
									<div class="mb-3">
										<label>Code :</label> <input type="text" class="form-control"
											id="nomClient">
									</div>
								</div>
								<div class="col-md-6">
									<div class="mb-3">
										<label>Nom :</label> <input type="text" class="form-control"
											id="nomConsultant">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="mb-3">
										<label>Role :</label> <select class="form-control" id="statut">
											<option value="">---- Séléctionnée un role ----</option>
											<c:forEach var="role" items="${ roles}">
												<option value="${ role.key}">${ role.value}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="col-md-6">
									<div class="mb-3">
										<label>statut :</label> <select class="form-control"
											id="statut">
											<option value="">---- Séléctionnée une statut ----</option>
											<option value="1">Active</option>
											<option value="0">Désactivé</option>
										</select>
									</div>
								</div>
							</div>

						</div>
						<div class="text-end">
							<a onclick=""
								class="dt-button buttons-collection btn btn-white btn-warning  btn-bold"><span><i
									class="fa fa-search bigger-110 green"></i> <span>Rechercher</span></span></a>
							<a href="${pageContext.request.contextPath}/profile/add"
								class="dt-button buttons-collection btn btn-white btn-primary btn-bold"><span><i
									class="fa fa-plus bigger-110 blue"></i> <span>Ajouter</span></span></a>

						</div>
								</div>
							</div>
						</div>

					</div>

					<div class="row">

						<div class="col-xl-12 col-lg-7">
							<div class="card shadow mb-4">
								<!-- Card Header - Dropdown -->
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h5 class="m-0 font-weight-bold text-primary">Liste des Profiles</h5>
								</div>
								<!-- Card Body -->
								<div class="card-body table-responsive">
									<table id="simple-table"
								class="table  table-bordered table-hover">
								<thead>
									<tr>
										<th>Code </th>
										<th>Nom </th>
										<th>Nombre d'utilisateurs</th>
										<th>Statut</th>
										<th>Actions</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach var="profile" items="${ profiles}">
										<tr>
											<td>${profile.code }</td>
											<td>${profile.description }</td>
											<td>${profile.nombreUsers }</td>
											<td>${profile.enabled}</td>
											<td>
												<div class="hidden-sm btn-group">
													<a class="btn btn-xs btn-success" title="Consulter"
														aria-controls="simple-table"
														href="${pageContext.request.contextPath}/profile/consult/${profile.id }"><span> <i
															class="ace-icon fa fa-eye bigger-120"></i>
													</span> </a> <a class="btn btn-xs btn-info" title="Modifier"
														aria-controls="simple-table"
														href="${pageContext.request.contextPath}/profile/edit/${profile.id }"><span> <i
															class="ace-icon fa fa-edit bigger-120"></i>
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
					<!-- /.container-fluid -->

				</div>
				<!-- End of Main Content -->
	
	<jsp:include page="../footer.jsp" />