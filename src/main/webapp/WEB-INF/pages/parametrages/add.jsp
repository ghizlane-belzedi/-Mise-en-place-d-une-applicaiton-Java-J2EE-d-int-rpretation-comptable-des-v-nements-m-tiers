<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../header.jsp" />
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">PARAMETRAGES -> NOUVEAU -> ${categorie }    </h1>
	</div>
	<div class="row">

		<div class="col-xl-12 col-lg-7">
			<div class="card shadow mb-4">
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h5 class="m-0 font-weight-bold text-primary">Nouveau paramétre</h5>
				</div>
				<div class="row">
					<!-- column -->
					<div class="col-sm-12">
						<div class="card">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title"></h4>
									<form:form id="link3" onclick="addCategorie()" method="post" modelAttribute="parametre">
										<div class="row col-md-12">
											<div class="col-md-6">
												<label for="codeFinance">Code finance :</label>
												<form:input class="form-control" path="codeFinance" />
												<form:errors path="codeFinance" cssClass="error" />
											</div>
											<div class="col-md-6">
												<label for="codeMedtier">Code métier :</label>
												<form:input class="form-control" path="codeMedtier" />
												<form:errors path="codeMedtier" cssClass="error" />
											</div>
										</div>
										<div class="row col-md-12">
											<div class="col-md-6">
												<label for="description1">Description :</label>
												<form:input class="form-control" path="description1" />
												<form:errors path="description1" cssClass="error" />
											</div>
											<div class="col-md-6">
												<label for="addtionalField">Additional field :</label>
												<form:input class="form-control" path="addtionalField" />
												<form:errors path="addtionalField" cssClass="error" />
											</div>
										</div>

										<div class="row col-md-12">
											<div class="col-md-6">
												<label for="addtionalFieldDescr">Additional field description:</label>
													<form:input class="form-control" path="addtionalFieldDescr" />
													<form:errors path="addtionalFieldDescr" cssClass="error" />
											</div>
										</div>
										<br>

										<div class="text-end" style="padding-top: 2em;">
											<a class="btn btn-secondary rounded-pill px-4 waves-effect waves-light" href="${pageContext.request.contextPath}/parametrages/">
												<i class="fa fa-arrow-left bigger-110"></i>
												Retour
											</a>
											<button type="submit" class="btn btn-primary rounded-pill px-4 waves-effect waves-light">
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
	function addCategorie() {
		categorie_param = "${categorie}";
		var strLink = "${pageContext.request.contextPath}/parametrages/add/" + categorie_param;
		document.getElementById("link3").setAttribute("action",strLink);
	};
</script>


<jsp:include page="../footer.jsp" />