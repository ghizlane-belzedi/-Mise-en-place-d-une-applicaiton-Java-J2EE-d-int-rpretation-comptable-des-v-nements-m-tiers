<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../header.jsp" />
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">FOURNISSEURS -> MODIFIER-> ${fournisseur.id } </h1>
	</div>
	<div class="row">

		<div class="col-xl-12 col-lg-7">
			<div class="card shadow mb-4">
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h5 class="m-0 font-weight-bold text-primary">Modifier un fournisseur</h5>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="card">
							<div class="card">
								<div class="card-body">
				                <form:form action="${pageContext.request.contextPath}/parametrageSupplier/edit" method="post" modelAttribute="fournisseur">
								<fieldset class="border p-3">
									<form:hidden path="id" />
									<div class="row">
										<div class="col-md-6">
											<div class="mb-3">
												<label for="codeMedtier">Code métier :</label>
												<form:input type="text" class="form-control" path="codeMedtier" />
												<form:errors path="codeMedtier" cssClass="error" />
											</div>
										</div>
										<div class="col-md-6">
											<div class="mb-3">
												<label for="codeFinance">Code finance :</label>
												<form:input type="text" class="form-control" path="codeFinance" />
												<form:errors path="codeFinance" cssClass="error" />
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
										<div class="col-md-6">
											<div class="mb-3">
												<label for="type">Type :</label>
												<form:input type="text" class="form-control" path="type" />
												<form:errors path="type" cssClass="error" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="mb-3">
												<label for="currency">Devise :</label>
												<form:input type="text" class="form-control" path="currency" />
											</div>
										</div>
									</div>

								</fieldset>
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
				</div>
			</div>
		</div>

	</div>

</div>
<script type="text/javascript">
	function saveParamSupplier() {
		var parametre = {
			id : $('#id').val(),
			codeMedtier : $('#codeMedtier').val(),
			codeFinance : $('#codeFinance').val(),
			description1 : $('#description1').val(),
			type : $('#type').val(),
			currency : $('#currency').val()

		}
		csrfHeader = $("meta[name='_csrf_header']").attr("content");
		csrfToken = $("meta[name='_csrf']").attr("content");
		var headers = {};
		headers[csrfHeader] = csrfToken;
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/parametrageSupplier/edit/",
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

	}
		
	</script>
<jsp:include page="../footer.jsp" />