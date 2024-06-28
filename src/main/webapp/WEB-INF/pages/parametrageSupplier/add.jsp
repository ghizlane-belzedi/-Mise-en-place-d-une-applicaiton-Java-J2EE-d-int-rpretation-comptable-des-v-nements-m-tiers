<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../header.jsp" />
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">FOURNISSEURS -> NOUVEAU    </h1>
	</div>
	<div class="row">

		<div class="col-xl-12 col-lg-7">
			<div class="card shadow mb-4">
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h5 class="m-0 font-weight-bold text-primary">Nouveau fournisseur</h5>
				</div>
				<div class="row">
					<!-- column -->
					<div class="col-sm-12">
						<div class="card">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title"></h4>
									<form:form action="${pageContext.request.contextPath}/parametrageSupplier/add" method="post" modelAttribute="fournisseur">
										<div class="row col-md-12">
											<div class="col-md-6">
												<label for="codeMedtier">Code métier :</label>
												<form:input class="form-control" path="codeMedtier" />
												<form:errors path="codeMedtier" cssClass="error" />
											</div>
											<div class="col-md-6">
												<label for="codeFinance">Code finance :</label>
												<form:input class="form-control" path="codeFinance" />
												<form:errors path="codeFinance" cssClass="error" />
											</div>
										</div>
										<div class="row col-md-12">
											<div class="col-md-6">
												<label for="description1">Description :</label>
												<form:input class="form-control" path="description1" />
												<form:errors path="description1" cssClass="error" />
											</div>
											<div class="col-md-6">
												<label for="type">Type :</label>
												<form:input class="form-control" path="type" />
												<form:errors path="type" cssClass="error" />
											</div>
										</div>

										<div class="row col-md-12">
											<div class="col-md-6">
												<label for="currency">Devise :</label>
													<form:input class="form-control" path="currency" />
                                                    <form:errors path="currency" cssClass="error" />
											</div>
										</div>
										<br>

										<div class="text-end" style="padding-top: 2em;">
											<a class="btn btn-secondary rounded-pill px-4 waves-effect waves-light" href="${pageContext.request.contextPath}/parametrageSupplier/">
												<i class="fa fa-arrow-left bigger-110"></i>
												Retour
											</a>
											<button type="submit" class="btn btn-info rounded-pill px-4 waves-effect waves-light">
												<i class="fa fa-plus bigger-110"></i>
												Enregistrer
											</button>
										</div>
									</form:form>
								</div>
							</div>
						</div>
					</div>
					<!-- column -->
				</div>
			</div>
		</div>

	</div>

</div>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('.datatable')
								.DataTable(
										{
											dom : 'frtipB',
											"language" : {
												url : '${pageContext.request.contextPath}/assets/json/datatable-fr-FR.json'
											},
											buttons : [ 'csv', 'excel', 'pdf' ]
										});

					});
</script>

<jsp:include page="../footer.jsp" />