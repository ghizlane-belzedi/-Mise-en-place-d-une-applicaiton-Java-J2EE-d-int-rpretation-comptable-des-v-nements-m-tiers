<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../header.jsp" />
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">GESTION DES FLUX  -> CONSULTER-> ${flux.fluxId } </h1>
	</div>
	<div class="row">

		<div class="col-xl-12 col-lg-7">
			<div class="card shadow mb-4">
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h5 class="m-0 font-weight-bold text-primary">Consulter un Flux</h5>
				</div>
				<div class="row">
					<!-- column -->
					<div class="col-sm-12">
						<div class="card">
							<div class="card">
								<div class="card-body">
									<form:form action="${pageContext.request.contextPath}/parametrage-flux/consult" method="post"
								modelAttribute="flux">
								<fieldset class="border p-3">
									<legend> Information Générale </legend>
									<form:hidden path="fluxId" />
									<div class="row">
										<div class="col-md-6">
											<div class="mb-3">
												<label for="code">Code :</label>
												<form:input readonly="true" type="text" class="form-control"
													path="code" />
											</div>
										</div>
										<div class="col-md-6">
											<div class="mb-3">
												<label for="nom">Nom :</label>
												<form:input  readonly="true"  type="text" class="form-control" path="nom" />
											</div>
										</div>
									</div>


								</fieldset>
								<br>
								<fieldset class="border p-3" id="fieldset-fields">
									<legend> Détails du flux </legend>
									<p style="color: red; text-align: right;">Nombre d'élément à afficher : <span>${visibles }<span></span></p>
									<table id="parametrage-flux-table"
										class="table table-bordered pagin-table">
										<tbody>
											<tr>
												<th width="2%">Visible</th>
												<th>Code</th>
												<th>Description</th>
												<td>Ordre</td>
											</tr>
											<c:forEach var="f" items="${ flux.fields}">
												<c:if test="${f.visible}">
												<tr>
													<td><input class="form-check-input" type="checkbox" disabled="disabled"
														${f.visible ? 'checked' : '' } id="visible"></td>
													<td>${f.code }</td>
													<td>${f.description }</td>
													<td>${f.ordre }</td>
												</tr>
												</c:if>
												
											</c:forEach>
										</tbody>
									</table>
									
								</fieldset>
								<br>

								<div class="text-end" style="padding-top: 2em;">
									<a
										class="btn btn-secondary rounded-pill px-4 waves-effect waves-light"
										href="${pageContext.request.contextPath}/parametrage-flux/"> <i
										class="fa fa-arrow-left bigger-110"></i> Retour
									</a>
								</div>
							</form:form>

									<!-- Form 2 -->


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
<jsp:include page="../footer.jsp" />