<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp" />
<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">SCHEMA COMPTABLE  -> INDEX</h1>
	</div>
	<div class="row">
		<div class="col-xl-12 col-lg-7">
			<div class="card shadow mb-4">
				<!-- Card Header - Dropdown -->
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h5 class="m-0 font-weight-bold text-primary">Recherche des
						Comptes</h5>
				</div>
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
								<label>Sens :</label> <select class="form-control" id="sens">
									<option value="">--- Choisir un sens</option>
									<option value="C">Crédit</option>
									<option value="D">Débit</option>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<label>Cost Center :</label> <input type="text"
									class="form-control" id="costCenter">
							</div>
							<div class="col-md-6">
								<label>Fournisseur :</label> <input type="text"
									class="form-control" id="fournisseur">
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<label>Numéro de Compte :</label> <input type="text"
									class="form-control" id="accountNumber">
							</div>
							<div class="col-md-6">
								<label>Description :</label> <input type="text"
									class="form-control" id="accountDescription">
							</div>
						</div>

					</div>
					<br>
					<div class="text-end">
						<a onclick="searchCompte()"
							class="dt-button buttons-collection btn btn-white btn-warning  btn-bold"><span><i
								class="fa fa-search bigger-110 green"></i> <span>Rechercher</span></span></a>
						<a href="${pageContext.request.contextPath}/compte/add/"
							class="dt-button buttons-collection btn btn-white btn-primary  btn-bold"><span><i
								class="fa fa-plus bigger-110 green"></i> <span>Nouveau
									schéma</span></span></a>
					</div>
				</div>
			</div>
		</div>
		<!-- Column -->
	</div>
	<div class="row">
		<!-- column -->
		<div class="col-xl-12 col-lg-7">
			<div class="card shadow mb-4">
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h5 class="m-0 font-weight-bold text-primary">Liste des
						Comptes</h5>
				</div>
				<div class="card-body">
					<table id="compte-table" class="table  table-bordered table-hover">
						
					</table>

				</div>
			</div>
		</div>
		<!-- column -->
	</div>
	<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<script type="text/javascript">
	var datatable;
	$(document)
			.ready(
					function() {
						datatable = $('#compte-table')
								.DataTable(
										{
											pageLength : 10,
											"processing" : true,
											"serverSide" : true,
											"responsive" : true,
											"searching" : true,
											"lengthMenu": [ [10, 25, 50, -1], [10, 25, 50, "Tout"] ],
											"language" : {
												url : '${pageContext.request.contextPath}/assets1/json/datatable-fr-FR.json'
											},
											contentType : "application/json",
											ajax : {
												url : '${pageContext.request.contextPath}/compte/findall/',
												type : "POST",
												data : function(data) {
													$('#compte-table_filter').css('display', 'none');
												}
											},
											columns : [
													{
														title : 'Code',
														name : 'code',
														data : 'code'
													},
													{
														title : 'Description',
														name : 'description',
														data : 'description'
													},
													{
														title : 'Fournisseur',
														name : 'codeFournisseur',
														data : 'fournisseur'
													},
													{
														title : 'Code Analyse',
														name : 'codeAnalyse',
														data : 'codeAnalyse'
													},
													{
														title : 'Cost Center',
														name : 'costCenter',
														data : 'costCenter'
													},
													{
														title : 'Compte Débiteur',
														name : 'compteDebiteur',
														data : 'compteDebiteur'
													},
													{
														title : 'Compte TVA',
														name : 'tvaAccount',
														data : 'tvaAccount'
													},
													{
														title : 'Compte Créditeur',
														name : 'compteCrediteur',
														data : 'compteCrediteur'
													},
													{
														title : 'Actions',
														data : 'id',
														name : 'id',
														render : function(data) {
															return ' <a class="btn btn-xs btn-info" title="Modifier"' +
																		'aria-controls="simple-table"' +
																		'href="${pageContext.request.contextPath}/compte/edit/' + data +'"><span>'
																	+ '<i class="ace-icon fa fa-edit bigger-120"></i>'
																	+ '</span></a>'
																	+ '</div>';
														}
													} ]
										});
						$('#compte-table_filter').css('display', 'none');
					});
	function searchCompte() {
		var search = $('#flux').val() + ";" + $('#sens').val() + ";"
				+ $('#fournisseur').val() + ";" + $('#costCenter').val() + ";"
				+ $('#accountNumber').val() + ";"
				+ $('#accountDescription').val() + ";";
		datatable.search(search).draw();

	}
</script>
<jsp:include page="../footer.jsp" />