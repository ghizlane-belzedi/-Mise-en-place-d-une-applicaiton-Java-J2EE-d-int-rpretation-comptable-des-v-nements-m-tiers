<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp" />
<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">PIECE COMPTABLE -> INDEX</h1>
	</div>

	<div class="row">

		<div class="col-xl-12 col-lg-7">
			<div class="card shadow mb-4">
				<!-- Card Header - Dropdown -->
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h5 class="m-0 font-weight-bold text-primary">Recherche des
						Pieces Comptable</h5>
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
									<option value="true">Envoyé</option>
									<option value="false">Non Envoyé</option>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<label>Date Piece du :</label> <input type="date"
									class="form-control" id="dateDu">
							</div>
							<div class="col-md-6">
								<label>Date Piece au :</label> <input type="date"
									class="form-control" id="dateAu">
							</div>
						</div>

					</div>
					<br>
					<div class="text-end">
						<a onclick="searchPiece()"
							class="dt-button buttons-collection btn btn-white btn-warning  btn-bold"><span><i
								class="fa fa-search bigger-110 green"></i> <span>Rechercher</span></span></a>
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
						Pieces Comptable</h5>
				</div>
				<!-- Card Body -->
				<div class="card-body table-responsive">
					<table id="piece-table" class="table  table-bordered table-hover">

					</table>
				</div>
			</div>
		</div>

	</div>
	<!-- /.container-fluid -->

</div>
<style>
td, th {
	width: 0.1%;
	white-space: nowrap;
}
</style>
	<script type="text/javascript">
	var datatable;	
	$(document).ready(function() {
		datatable = $('#piece-table').DataTable({
				pageLength : 10,
				"processing" : true,
				"serverSide" : true,
				"responsive" : true,
				"language" : { url: '${pageContext.request.contextPath}/assets1/json/datatable-fr-FR.json'},
				ajax : {
					url : '${pageContext.request.contextPath}/piece-comptable/findall',
					'type': 'POST',
					data : function(d) {
						$('#piece-table_filter').css('display', 'none');
					}
				},
				columns : [ {
					title : 'flux',
					name : 'flux',
					data : 'flux'
				}, {
					title : 'statut',
					data : 'statut',
					name : 'vehicule.sent',
					render : function(data) {
						console.log(data)
						return data ? 'Envoyé' : 'Non Envoyé';
					}
				}, {
					title : 'codeJournale',
					name : 'codeJournale',
					data : 'codeJournale'
				}, {
					title : 'numeroPiece',
					name : 'numeroPiece',
					data : 'numeroPiece'
				}, {
					title : 'date',
					name : 'date',
					data : 'date'
				}, {
					title : 'description',
					name : 'enteteDoc',
					data : 'description'
				}, {
					title : 'id',
					data : 'id',
					name : 'id',
					render : function(data) {
						return  '<div class="hidden-sm btn-group">' +
						'<a class="btn btn-xs btn-success" title="Consulter"' +
						'aria-controls="simple-table"' +
						'href="${pageContext.request.contextPath}/piece-comptable/consult/' + data +'"><span>' +
						'<i class="ace-icon fa fa-eye bigger-120"></i>' +
					'</span></a>' + 
				'</div>';;
					}
				} ]
			});
			
		});
		function searchPiece() {
			var search = $('#flux').val() + ";" + $('#statut').val() + ";" + $('#dateDu').val() + ";" + $('#dateAu').val() + ";" ;
			datatable.search(search).draw();
			
		}
	</script>
<jsp:include page="../footer.jsp" />