<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp" />
<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">CLIENT -> INDEX</h1>
	</div>
	<div class="row">
		<!-- column -->
		<div class="col-xl-12 col-lg-7">
			<div class="card shadow mb-4">
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h5 class="m-0 font-weight-bold text-primary">Liste des
						Clients</h5>
						<a href="${pageContext.request.contextPath}/client/add/"
							class="dt-button buttons-collection btn btn-white btn-primary  btn-bold"><span><i
								class="fa fa-plus bigger-110 green"></i> <span>
									Ajouter Client </span></span></a>
				</div>
				<div class="card-body responsive">
					<table id="client-table" class="table  table-bordered table-hover">
						<thead>
							<tr>
								<th>Site</th>
								<th>Code Métier </th>
								<th>Code finance</th>
								<th>Description</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="client" items="${ clients}">
								<tr>
									<td>${client.site }</td>
									<td>${client.codeMedtier }</td>
									<td>${client.codeFinance }</td>
									<td>${client.description}</td>
									<td>
										<div class="hidden-sm btn-group">
											<a class="btn btn-xs btn-info" title="Modifier"
												aria-controls="simple-table"
												href="${pageContext.request.contextPath}/client/edit/${client.id}"><span>
													<i class="ace-icon fa fa-edit bigger-120"></i>
											</span></a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<td>${client.site }</td>
									<td>${client.codeMedtier }</td>
									<td>${client.codeFinance }</td>
									<td>${client.description}</td>
								<th>	</th>
							</tr>
						</tfoot>
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
						datatable = $('#client-table')
								.DataTable(
										{
											"responsive" : true,
											"language" : {
												url : '${pageContext.request.contextPath}/assets1/json/datatable-fr-FR.json'
											},
											"processing" : true,
											initComplete : function() {
												this
														.api()
														.columns()
														.every(
																function() {
																	var column = this;
																	if (column.index() == 4) {
															            return; 
															        }
																	var select = $(
																			'<select class="form-control"><option value=""></option></select>')
																			.appendTo(
																					$(
																							column
																									.footer())
																							.empty())
																			.on(
																					'change',
																					function() {
																						var val = DataTable.util
																								.escapeRegex($(
																										this)
																										.val());

																						column
																								.search(
																										val ? '^'
																												+ val
																												+ '$'
																												: '',
																										true,
																										false)
																								.draw();
																					});

																	
																	column
																			.data()
																			.unique()
																			.sort()
																			.each(
																					function(
																							d,
																							j) {
																						select
																								.append('<option value="' + d + '">'
																										+ d
																										+ '</option>');
																					});
																});
											},
										});
					});
</script>

<jsp:include page="../footer.jsp" />