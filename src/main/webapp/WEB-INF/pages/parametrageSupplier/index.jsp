<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp" />
<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">FOURNISSEURS -> INDEX</h1>
	</div>
	<div class="row">
		<div class="col-xl-12 col-lg-7">
			<div class="card shadow mb-4">
				<!-- Card Header - Dropdown -->
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h5 class="m-0 font-weight-bold text-primary">Recherche des fournisseurs</h5>
				</div>
				<div class="card-body">
					<div class="text-info">
							<div class="row">
								<div class="col-md-6">
									<div class="mb-3">
										<label >Type :</label> <input type="text" class="form-control" id="type">
									</div>
								</div>
								<div class="col-md-6">
									<div class="mb-3">
										<label >Code métier :</label> <input type="text" class="form-control" id="codeMedtier">
									</div>
								</div>
							</div>
						    <div class="row">
							<div class="col-md-6">
								<div class="mb-3">
									<label >Code finance  :</label> <input type="text" class="form-control" id="codeFinance">
								</div>
							</div>
						</div>
							
						</div>
						<div class="text-end">
							<a onclick="searchSupplier()"
								class="dt-button buttons-collection btn btn-white btn-warning  btn-bold">
								<span>
									<i class="fa fa-search bigger-110 green"></i>
									<span>Rechercher</span>
								</span>
							</a>
							<a href="${pageContext.request.contextPath}/parametrageSupplier/add/"
							   class="dt-button buttons-collection btn btn-white btn-primary  btn-bold">
								<span>
									<i class="fa fa-plus bigger-110 green"></i>
								    <span>Nouveau fournisseur</span>
								</span>
							</a>
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
					<h5 class="m-0 font-weight-bold text-primary">Liste des fournisseurs</h5>
				</div>
				<div class="card-body">
					<table id="supplier-table" class="table  table-bordered table-hover">
					</table>
				</div>
			</div>
		</div>
	</div>

</div>
<script type="text/javascript">
	var datatable;
		$(document)
                .ready(
                        function() {
                            datatable = $('#supplier-table')
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
												url : '${pageContext.request.contextPath}/parametrageSupplier/findall/',
												type : "POST",
												data : function(data) {
													$('#supplier-table_filter').css('display', 'none');
												}
											},
											columns : [
												{
													title : 'Type',
													name : 'type',
													data : 'type'
												},
												{
													title : 'Code métier',
													name : 'codeMedtier',
													data : 'codeMedtier'

												},
												{
													title : 'Code finance',
													name : 'codeFinance',
													data : 'codeFinance'
												},
												{
													title : 'Description',
													name : 'description1',
													data : 'description1'
												},
												{
													title : 'Devise',
													name : 'currency',
													data : 'currency'
												},
												{
													title : 'Actions',
													data : 'id',
													name : 'id',
													render : function(data) {
														return '<div class="hidden-sm btn-group">'
																+ '	<a class="btn btn-xs btn-info" title="Modifier"' +
														'aria-controls="simple-table" ' +
														'href="${pageContext.request.contextPath}/parametrageSupplier/edit/' + data + '"> <span>'
																+ '<i class="ace-icon fa fa-edit bigger-120"></i>'
													+ '</span></a>'
																+ '</div>';
													}
												} ]
										});
						$('#supplier-table_filter').css('display', 'none');
					});
	function searchSupplier() {
		var search = $('#type').val() + ";"
		         + $('#codeMedtier').val() + ";"
				+ $('#codeFinance').val() + ";";
		datatable.search(search).draw();
	}
</script>

<jsp:include page="../footer.jsp" />