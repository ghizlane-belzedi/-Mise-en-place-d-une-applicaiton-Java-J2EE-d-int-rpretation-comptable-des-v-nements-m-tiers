<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../header.jsp" />
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">PARAMETRAGES -> MODIFIER-> ${parametre.categorie } -> ${parametre.paramId } </h1>
	</div>
	<div class="row">

		<div class="col-xl-12 col-lg-7">
			<div class="card shadow mb-4">
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h5 class="m-0 font-weight-bold text-primary">Modifier un paramètre</h5>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="card">
							<div class="card">
								<div class="card-body">
				                <form:form action="${pageContext.request.contextPath}/parametrages/edit" method="post" modelAttribute="parametre">
								<fieldset class="border p-3">
									<form:hidden path="paramId" />
									<form:hidden path="categorie" />
									<div class="row">
										<div class="col-md-6">
											<div class="mb-3">
												<label for="codeFinance">Code finance :</label>
												<form:input type="text" class="form-control" path="codeFinance" />
											</div>
										</div>
										<div class="col-md-6">
											<div class="mb-3">
												<label for="codeMedtier">Code métier :</label>
												<form:input type="text" class="form-control" path="codeMedtier" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="mb-3">
												<label for="description1">Description :</label>
												<form:input type="text" class="form-control" path="description1" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="mb-3">
												<label for="addtionalField">Additional field :</label>
												<form:input type="text" class="form-control" path="addtionalField" />
											</div>
										</div>
										<div class="col-md-6">
											<div class="mb-3">
												<label for="addtionalFieldDescr"> Additional field description: </label>
												<form:input type="text" class="form-control" path="addtionalFieldDescr" />
											</div>
										</div>
									</div>

								</fieldset>
								<br>
								<div class="text-end" style="padding-top: 2em;">
									<a class="btn btn-secondary rounded-pill px-4 waves-effect waves-light" href="${pageContext.request.contextPath}/parametrages/">
										<i class="fa fa-arrow-left bigger-110"></i>
										Retour
									</a><a class="btn btn-info rounded-pill px-4 waves-effect waves-light" onclick="saveParametre()">
										<i class="fa fa-save bigger-110"></i>
										Enregistrer
									</a>
									
								</div>
							    </form:form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

</div>
<script type="text/javascript">
	function saveParametre() {
		var parametre = {
			paramId : $('#paramId').val(),
			categorie : $('#categorie').val(),
			codeFinance : $('#codeFinance').val(),
			codeMedtier : $('#codeMedtier').val(),
			description1 : $('#description1').val(),
			addtionalField : $('#addtionalField').val(),
			addtionalFieldDescr : $('#addtionalFieldDescr').val()

		}
		csrfHeader = $("meta[name='_csrf_header']").attr("content");
		csrfToken = $("meta[name='_csrf']").attr("content");
		var headers = {};
		headers[csrfHeader] = csrfToken;
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/parametrages/edit/",
			contentType : 'application/json;charset=UTF-8',
			dataType: 'json',
			//headers : headers,
			data: JSON.stringify(parametre),
			success : function(data) {
				console.log(data);
			},
			error : function(data) {
				console.log(data.responseJSON.message)
			}
		});
		document.location.href ='${pageContext.request.contextPath}/parametrages/indexCategorie/' + parametre.categorie;
	}
		
	</script>
<jsp:include page="../footer.jsp" />