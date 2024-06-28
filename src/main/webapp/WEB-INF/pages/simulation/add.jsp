<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../header.jsp" />
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">SIMULATION -> NOUVELLE</h1>
	</div>
	<div class="row">

		<div class="col-xl-12 col-lg-7">
			<div class="card shadow mb-4">
				<!-- Card Header - Dropdown -->
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h5 class="m-0 font-weight-bold text-primary">Nouvelle
						simulation</h5>
				</div>
				<!-- Card Body -->
				<div class="card-body table-responsive">
					<form:form action="${pageContext.request.contextPath}/simulation/generer" method="get"
						modelAttribute="simulation" id="form-simulation">
						<nav class="nav nav-tabs">
							<a
								class="nav-item nav-link p1 ${resultatSimulation == null ? 'active' : '' }"
								onclick="activer('p1')" data-toggle="tab">Consultation </a> <a
								class="nav-item nav-link p2 ${resultatSimulation != null ? 'active' : '' }"
								onclick="activer('p2')" data-toggle="tab">Simulation </a>
						</nav>
						<div class="tab-content">
							<div class="tab-pane fade ${resultatSimulation == null ? 'active show' : '' }"
								id="p1" style="border: 1px black">
								<br>
								<div class="row"
									style="border-color: black; border-style: solid; border-width: 0.0em">
									<div class="col-md-6">
										<label>Flux <span style="color: red;"> ( * )</span> :
										</label>
										<form:select class="form-control" path="flux">
											<form:option value="">--- Choisir un flux</form:option>
											<c:forEach var="f" items="${ flux}">
												<form:option value="${f.key }">${f.value }</form:option>
											</c:forEach>
										</form:select>
										<form:errors path="flux" cssClass="error" />
									</div>
									<div class="col-md-6">
										<label>Date Arrêté à <span style="color: red;">
												( * )</span>:
										</label>
										<form:input type="date" class="form-control"
											path="dateArreter" />
										<form:errors path="dateArreter" cssClass="error" />
									</div>
								</div>
								<br>
								<fieldset ${resultatGeneration != null }>
									<h3>Extraction de donnée</h3>
									<h4>
										<form:errors path="isEmpty" cssClass="error" />
										<form:errors path="enable" cssClass="error" />
										<form:errors path="comment" cssClass="error" />
									</h4>
									<div class="table-responsive">
										<table id="vehicule-table"
											class="table  table-bordered table-hover datatable">
											<thead>
												<tr>
													<c:forEach var="th" items="${ resultatGeneration.header}">
														<th>${th }</th>
													</c:forEach>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="tr" items="${ resultatGeneration.details}">
													<tr>
														<c:forEach var="td" items="${ tr}">
															<td>${td }</td>
														</c:forEach>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</fieldset>

							</div>
							<div
								class="tab-pane fade ${resultatSimulation != null ? 'active show' : '' }"
								id="p2">
								<c:if test="${resultatSimulation.containsKey(true)}">
									<fieldset>
										<h3>Extraction des écritures comptable</h3>
										<div class="px-3 py-3 bg-gradient-success text-white"><h3 style="font-size: large;">Simulation
											effectué avec succèss</h3></div>
										<div class="table-responsive">
											<table id="ecriture-table"
												class="table  table-bordered table-hover datatable">
												<thead>
													<tr>
														<th>Code Journal</th>
														<th>N° Pièce</th>
														<th>Date</th>
														<th>Description entête Doc</th>
														<th>Actions</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="piece" items="${ resultatSimulation[true]}">
														<tr>
															<td>${piece.codeJournale }</td>
															<td>${piece.numeroPiece }</td>
															<td>${piece.date }</td>
															<td>${piece.description }</td>
															<td><a onclick="openForm(${piece.id})"
																class="btn btn-success rounded-pill px-4 waves-effect waves-light">
																	<i class="fa fa-eye bigger-110"></i>
															</a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</fieldset>

								</c:if>
								<c:if test="${resultatSimulation.containsKey(false)}">
									<div class="px-3 py-3 bg-gradient-danger text-white">
									<h3 style="font-size: large;"><spring:message code="data.invalid"/></h3></div>
									<table id="erreur-table"
										class="table table-bordered table-hover datatable" border="1">
										<thead>
											<tr>
												<th style="width: 1%">numéro de ligne</th>
												<th>Description erreur</th>
											</tr>
										</thead>
										<tbody>

											<c:forEach var="erreur" items="${ resultatSimulation[false]}">
												<c:forEach var="val" items="${ erreur.value}"
													varStatus="loop">
													<tr>
														<td>${erreur.key}</td>
														<td>${val }</td>
													</tr>
												</c:forEach>
											</c:forEach>
										</tbody>
									</table>
								</c:if>
							</div>
						</div>

						<div class="text-end" style="padding-top: 2em;">
							<a
								class="btn btn-danger rounded-pill px-4 waves-effect waves-light"
								href="${pageContext.request.contextPath}/simulation/"> <i class="fa fa-arrow-left bigger-110"></i>
								Retour
							</a>
							<button type="submit"
								class="btn btn-success rounded-pill px-4 waves-effect waves-light">
								<i class="fa fa-eye bigger-110"></i> Consulter
							</button>
							<!-- <button type="submit"
										class="btn btn-info rounded-pill px-4 waves-effect waves-light">
										<i class="fa fa-bars bigger-110"></i> Simuler
									</button>
									 -->
							<c:if
								test="${simulation.flux != null && resultatGeneration.details != null && fn:length(resultatGeneration.details)>0}">
								<a
									href="${pageContext.request.contextPath}/simulation/simuler?flux=${simulation.flux}&dateArreter=${simulation.dateArreter}"
									class="btn btn-info rounded-pill px-4 waves-effect waves-light">
									<i class="fa fa- bigger-110"></i> Simuler
								</a>
							</c:if>
							<c:if test="${resultatSimulation.containsKey(true)}">
								<a href="${pageContext.request.contextPath}/simulation/generer/${simulation.id}"
									class="btn btn-primary rounded-pill px-4 waves-effect waves-light">
									<i class="fa fa- bigger-110"></i> Génerer
								</a>
								<a href="${pageContext.request.contextPath}/simulation/exporter/${simulation.id}"
											class="btn btn-light rounded-pill px-4 waves-effect waves-light">
											<i class="fa fa-print bigger-110"></i> Exporter
										</a>
							</c:if>
							<!-- -->
						</div>
					</form:form>
				</div>
			</div>
		</div>

	</div>

</div>
<!-- Modal -->
<div id="myModal" class="modal fade" tabindex="-1">
	<div class="modal-dialog  modal-xxl">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Consulter une piece comptable</h5>
			</div>
			<div class="modal-body"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal"
					onclick="$('#myModal').modal('hide')">Cancel</button>
			</div>
		</div>
	</div>
</div>
<!-- End of Main Content -->

<script type="text/javascript">
		function activer(element) {
			$('#form-simulation .tab-content div').removeClass('show active');
			$('#form-simulation nav a').removeClass('active');
			$('#' + element).addClass('show active');
			$('#form-simulation .' + element).addClass('active');
		}
		$(document)
				.ready(
						function() {
							$('#ecriture-table')
									.DataTable(
											{
												dom : 'frtipB',
												responsive: true,
												"language" : {
													url : '${pageContext.request.contextPath}/assets1/json/datatable-fr-FR.json'
												},
												buttons : [ 'csv', 'excel',
														'pdf' ]
											});
							$('#erreur-table')
									.DataTable(
											{
												dom : 'frtipB',
												responsive: true,
												rowGroup : {
													dataSrc : 0
												},
												"language" : {
													url : '${pageContext.request.contextPath}/assets1/json/datatable-fr-FR.json'
												},
												buttons : [ 'csv', 'excel',
														'pdf' ]
											});
							$('#vehicule-table')
									.DataTable(
											{
												dom : 'frtipB',
												responsive: true,
												"language" : {
													url : '${pageContext.request.contextPath}/assets1/json/datatable-fr-FR.json'
												},
												buttons : [ 'csv', 'excel',
														'pdf' ]
											});
						});
		function openForm(element) {
			$('#myModal .modal-body').html('');
			$.ajax({url: "${pageContext.request.contextPath}/simulation/piece/"+element, success: function(result){
				$('#myModal .modal-body').html(result);
				$("#myModal").modal('show')
			  }});
		}
	</script>
<jsp:include page="../footer.jsp" />