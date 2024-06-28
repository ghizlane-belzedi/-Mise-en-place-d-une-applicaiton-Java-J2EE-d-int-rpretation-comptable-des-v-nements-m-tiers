<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp" />
<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800"> CHARGEMENT -> SITUATION DES CHARGEMENT </h1>
					</div>

					<div class="row">

						<div class="col-xl-12 col-lg-7">
							<div class="card shadow mb-4">
								<!-- Card Header - Dropdown -->
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h5 class="m-0 font-weight-bold text-primary"></h5>
								</div>
								<!-- Card Body -->
								<div class="card-body table-responsive">
									<table id="chargement-table"
										class="table  table-bordered table-hover">
										<thead>
											<tr>
												<th>Flux</th>
												<th>Date Chargement</th>
												<th>Statut</th>
												<th>Action </th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="chargement" items="${ chargements}">
												<tr>
													<td>${chargement.flux }</td>
													<td>${chargement.dateChargement }</td>
													<td>${chargement.state }</td>
													<td> 
														<c:if test="${ chargement.state == 'WITH_ERRORS'}"> 
															<a href="/chargement/start-chargement/${ chargement.id}"
																class="btn btn-primary rounded-pill px-4 waves-effect waves-light">
																	<i class="fa fa-play bigger-110"></i>
															</a>
														</c:if>
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
	<div id="myModal" class="modal fade" tabindex="-1">
	<div class="modal-dialog  modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Lancement Manuel du chargement</h5>
			</div>
			<div class="modal-body">
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal"
					onclick="$('#myModal').modal('hide')">Cancel</button>
			</div>
		</div>
	</div>
</div>
	<jsp:include page="../footer.jsp" />