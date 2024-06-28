<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp" />
<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">CHARGEMENT -> INDEX</h1>
	</div>

	<div class="row">

		<div class="col-xl-12 col-lg-7">
			<div class="card shadow mb-4">
				<!-- Card Header - Dropdown -->
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h5 class="m-0 font-weight-bold text-primary">Recherche des
						Chargements</h5>
				</div>
				<!-- Card Body -->
				<div class="card-body">
					<div class="text-info">
						<div class="row">
							<div class="col-md-6">
								<label>Flux :</label> <select class="form-control" id="flux">
									<option value="">--- Choisir un flux</option>
									<c:forEach var="f" items="${ flux}">
										<option value="${f.key }">${f.value }</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-6">
								<label>Statut :</label> <select class="form-control" id="statut">
									<option value="">--- Choisir une statut</option>
									<c:forEach var="statut" items="${ statuts}">
										<option value="${statut }">${statut }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<label>Date Chargement du :</label> <input type="date"
									class="form-control" id="dateDu">
							</div>
							<div class="col-md-6">
								<label>Date Chargement au :</label> <input type="date"
									class="form-control" id="dateAu">
							</div>
						</div>

					</div>
					<br>
					<div class="text-end">
						<a onclick="searchChargement()"
							class="dt-button buttons-collection btn btn-white btn-warning  btn-bold"><span><i
								class="fa fa-search bigger-110 green"></i> <span>Rechercher</span></span></a>
						<a onclick="openForm()"
							class="dt-button buttons-collection btn btn-white btn-warning  btn-bold"><span><i
								class="fa fa-play bigger-110 green"></i> <span>Lancement
									manuel</span></span></a>
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
					<h5 class="m-0 font-weight-bold text-primary">Liste des
						Chargements</h5>
				</div>
				<!-- Card Body -->
				<c:if test="${resultat != null && resultat == 'OK'}">
				 	<div class="px-3 py-3 bg-gradient-success text-white"><h3 style="font-size: large;">Lancement avec succées du chargement</h3></div>
				</c:if>
				<c:if test="${resultat != null && resultat != 'OK'}">
					<div class="px-3 py-3 bg-gradient-danger text-white"><h3 style="font-size: large;">${resultat }</h3></div>
				</c:if>
				<div class="card-body table-responsive">
					<table id="chargement-table"
						class="table  table-bordered table-hover">

					</table>
				</div>
			</div>
		</div>

	</div>
	<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<!-- Modal -->
<div id="myModal" class="modal fade" tabindex="-1">
	<div class="modal-dialog  modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Lancement Manuel du chargement</h5>
			</div>
			<div class="modal-body">
				<form method="get" action="${pageContext.request.contextPath}/chargement/start-chargement" id="launchChargement">
					<div class="col-md-6">
						<label>Flux <span style="color: red;"> ( * )</span> :
						</label> <select class="form-control" name="fluxCode" id="fluxCode">
							<option value="">--- Choisir un flux</option>
							<c:forEach var="f" items="${ flux}">
								<option value="${f.key }">${f.value }</option>
							</c:forEach>
						</select>
					</div>
				</form>
			</div>

			<div class="modal-footer">
				<button type="submit" onclick="$('#launchChargement').submit()" class="btn btn-primary" data-dismiss="modal">Lancer</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal"
					onclick="$('#myModal').modal('hide')">Cancel</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var datatable;
	$(document)
			.ready(
					function() {
						datatable = $('#chargement-table')
								.DataTable(
										{
											pageLength : 10,
											"processing" : true,
											"serverSide" : true,
											"responsive" : true,
											"searching" : true,
											"lengthMenu" : [
													[ 10, 25, 50, -1 ],
													[ 10, 25, 50, "Tout" ] ],
											"language" : {
												url : '${pageContext.request.contextPath}/assets1/json/datatable-fr-FR.json'
											},
											"order" :[[3, 'desc']],
											contentType : "application/json",
											ajax : {
												url : '${pageContext.request.contextPath}/chargement/findall/',
												type : "POST",
												data : function(data) {
													$(
															'#chargement-table_filter label')
															.css('display',
																	'none');
												}
											},
											columns : [
													{
														title : 'Identifiant',
														name : 'id',
														data : 'id'
													},
													{
														title : 'Flux',
														name : 'flux.nom',
														data : 'flux'
													},
													{
														title : 'Status',
														data : 'state',
														name : 'state'
													},
													{
														title : 'date debut de <br>chargement',
														name : 'dateChargement',
														data : 'dateChargement'
													},
													{
														title : 'date Fin de <br>chargement',
														name : 'dateFinChargement',
														orderable : false,
														data : 'dateFinChargement'
													},
													{
														title : 'Premiére ligne chargée',
														name : 'fisrt',
														data : 'fisrt',
														render: $.fn.dataTable.render.number(' ', ',', 0, ''),
														className: "text-right"
													},
													{
														title : 'Derniére ligne chargé ',
														name : 'last',
														data : 'last',
														render: $.fn.dataTable.render.number(' ', ',', 0, ''),
														className: "text-right"
													},
													{
														title : 'Actions',
														data : 'id',
														name : 'id',
														render : function(data) {
															return '<div class="hidden-sm btn-group">'
																	+ '<a class="btn btn-xs btn-success" title="Consulter"' +
							'aria-controls="simple-table"' +
							'href="${pageContext.request.contextPath}/chargement/consult/' + data +'"><span>' +
							'<i class="ace-icon fa fa-eye bigger-120"></i>' +
						'</span></a>' + 
					'</div>';
														}
													} ]
										});

					});
	function searchChargement() {
		var search = $('#flux').val() + ";" + $('#statut').val() + ";"
				+ $('#dateDu').val() + ";" + $('#dateAu').val() + ";";
		datatable.search(search).draw();

	}
	function openForm() {
		$("#myModal").modal('show');
	}
	function startChargement() {
		$.ajax({
			url : "/chargement/start-chargement/" + $('#fluxCode').val(),
			success : function(result) {

			}
		});
	}
</script>
<jsp:include page="../footer.jsp" />