<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp" />
<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">GESTION DES FLUX -> INDEX</h1>
	</div>
	
	<div class="row">
		<!-- column -->
		<div class="col-xl-12 col-lg-7">
			<div class="card shadow mb-4">
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h5 class="m-0 font-weight-bold text-primary">Liste des
						flux</h5>
				</div>
				<div class="card-body">
					<table id="simple-table"
								class="table  table-bordered table-hover">
								<thead>
									<tr>
										<th>Code</th>
										<th>Nom </th>
										<th>Actions</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach var="f" items="${ flux}">
										<tr>
											<td>${f.code}</td>
											<td>${f.nom }</td>
											<td>
												<div class="hidden-sm btn-group">
													<a class="btn btn-xs btn-success" title="Consulter"
														aria-controls="simple-table"
														href="${pageContext.request.contextPath}/parametrage-flux/consult/${f.fluxId }"><span>
															<i class="ace-icon fa fa-eye bigger-120"></i>
													</span> </a> <a class="btn btn-xs btn-info" title="Modifier"
														aria-controls="simple-table"
														href="${pageContext.request.contextPath}/parametrage-flux/edit/${f.fluxId }"><span>
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
		<!-- column -->
	</div>
	<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<jsp:include page="../footer.jsp" />