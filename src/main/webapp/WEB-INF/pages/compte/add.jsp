<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../header.jsp" />
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">SCHEMA COMPTABLE -> ${ schema.id == null ? 'NOUVEAU' : 'MODIFIER -> ' } ${schema.id } </h1>
	</div>
	<div class="row">

		<div class="col-xl-12 col-lg-7">
			<div class="card shadow mb-4">
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h5 class="m-0 font-weight-bold text-primary">${ schema.id == null ? 'NOUVEAU' : 'MODIFIER -> ' } schéma comptable</h5>
				</div>
				<div class="row">
					<!-- column -->
					<div class="col-sm-12">
						<div class="card">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title"></h4>
									<form:form action="${pageContext.request.contextPath}/compte/add" method="post"
										modelAttribute="schema">
										<form:hidden path="id" />
										<div class="row col-md-12">
											<div class="col-md-6">
												<label for="flux">Flux :</label>
												<form:select class="form-control" path="flux">
													<form:option value="">--- Choisir un flux</form:option>
													<c:forEach var="f" items="${ flux}">
														<form:option value="${f.key }">${f.value }</form:option>
													</c:forEach>
												</form:select>
												<form:errors path="flux" cssClass="error" />
											</div>
											<div class="col-md-6">
												<label for="codeAnalyse">Code analyse :</label>
												<form:input class="form-control" path="codeAnalyse" />
												<form:errors path="codeAnalyse" cssClass="error" />
											</div>
										</div>
										<div class="row col-md-12">
											<div class="col-md-6">
												<label for="code">Code :</label>
												<form:input class="form-control" path="code" />
												<form:errors path="code" cssClass="error" />
											</div>
											<div class="col-md-6">
												<label for="description">Description :</label>
												<form:textarea class="form-control" path="description" />
												<form:errors path="description" cssClass="error" />
											</div>
										</div>
										<div class="row col-md-12">
											<div class="col-md-6">
												<label for="fournisseur">Fournisseur :</label>
												<form:input class="form-control" path="fournisseur" />
												<form:errors path="fournisseur" cssClass="error" />
											</div>
											<div class="col-md-6">
												<label for="costCenter">Cost Center :</label>
												<form:input class="form-control" path="costCenter" />
											</div>
										</div>
										<br/><hr/>
										<fieldset>
											<label>Compte débiteur</label>
											<div class="row col-md-12">
												<div class="col-md-2">
													<label style="padding-right: 5px;">Activé : </label>
													<form:radiobutton path="compteDebiteurActif" value="true" />
												</div>
												<div class="col-md-2">
													<label style="padding-right: 5px;">Désactivé : </label>
													<form:radiobutton path="compteDebiteurActif" value="false" />
												</div>
												<div class="col-md-2">
													<form:errors path="compteDebiteurActif" cssClass="error" />
												</div>
												<div class="col-md-6"></div>
											</div>
											<div class="row col-md-12">
												<div class="col-md-6">
													<label for="compteDebiteur"><spring:message code="numero.compte"/> :</label>
													<form:input class="form-control" path="compteDebiteur" />
													<form:errors path="compteDebiteur" cssClass="error" />
												</div>
												<div class="col-md-6">
													<label for="compteDebiteurDescription">Description :</label>
													<form:textarea class="form-control" path="compteDebiteurDescription" />
												</div>
											</div>
										</fieldset>
										<hr/>
										<fieldset>
											<label> Compte TVA </label>
											<div class="row col-md-12">
												<div class="col-md-2">
													<label style="padding-right: 5px;">Activé : </label>
													<form:radiobutton path="compteTvaActif" value="true" />
												</div>
												<div class="col-md-2">
													<label style="padding-right: 5px;">Désactivé : </label><form:radiobutton path="compteTvaActif" value="false" />
												</div>
												<div class="col-md-2">
													<form:errors path="compteTvaActif" cssClass="error" />
												</div>
												<div class="col-md-6"></div>
											</div>
											<div class="row col-md-12">
												<div class="col-md-6">
													<label for="compteTva"><spring:message code="numero.compte"/> :</label>
													<form:input class="form-control" path="compteTva" />
													<form:errors path="compteTva" cssClass="error" />
												</div>
												<div class="col-md-6">
													<label for="compteTvaDescription">Description :</label>
													<form:textarea class="form-control" path="compteTvaDescription" />
												</div>
											</div>
										</fieldset>
										<hr/>
										<fieldset>
											<label> Compte créditeur </label>
											<div class="row col-md-12">
												<div class="col-md-2">
													<label style="padding-right: 5px;">Activé : </label>
													<form:radiobutton path="compteCrediteurActif" value="true" />
												</div>
												<div class="col-md-2">
													<label style="padding-right: 5px;">Désactivé : </label>
													<form:radiobutton path="compteCrediteurActif" value="false" />
												</div>
												<div class="col-md-2">
													<form:errors path="compteCrediteurActif" cssClass="error" />
												</div>
												<div class="col-md-6"></div>
											</div>
											<div class="row col-md-12">
												<div class="col-md-6">
													<label for="compteCrediteur"><spring:message code="numero.compte"/> :</label>
													<form:input class="form-control" path="compteCrediteur" />
													<form:errors path="compteCrediteur" cssClass="error" />
												</div>
												<div class="col-md-6">
													<label for="compteCrediteurDescription">Description :</label>
													<form:textarea class="form-control" path="compteCrediteurDescription" />
												</div>
											</div>
										</fieldset>
										<br>

										<div class="text-end" style="padding-top: 2em;">
											<a class="btn btn-secondary rounded-pill px-4 waves-effect waves-light"
												href="${pageContext.request.contextPath}/compte/"> <i class="fa fa-arrow-left bigger-110"></i>
												Retour
											</a>
											<button type="submit"
												class="btn btn-info rounded-pill px-4 waves-effect waves-light">
												<i class="fa fa-plus bigger-110"></i> Enregistrer
											</button>
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

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('.datatable')
								.DataTable(
										{
											dom : 'frtipB',
											"language" : {
												url : '${pageContext.request.contextPath}/assets1/json/datatable-fr-FR.json'
											},
											buttons : [ 'csv', 'excel', 'pdf' ]
										});

					});
</script>

<jsp:include page="../footer.jsp" />