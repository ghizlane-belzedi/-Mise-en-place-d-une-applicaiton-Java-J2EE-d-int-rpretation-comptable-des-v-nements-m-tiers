<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../header.jsp" />
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">COMPTE FACTURATION ->
			MODIFIER-> ${compte.id }</h1>
	</div>
	<div class="row">

		<div class="col-xl-12 col-lg-7">
			<div class="card shadow mb-4">
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h5 class="m-0 font-weight-bold text-primary">Modifier un
						compte</h5>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="card">
							<div class="card">
								<div class="card-body">
								
									<c:if test="${success ==0}">
										<div class="px-3 py-3 bg-gradient-danger text-white"><h3 style="font-size: large;"> ${message} </h3></div>
									</c:if>
									<form:form
										action="${pageContext.request.contextPath}/compte-facturation/edit"
										method="post" modelAttribute="compte">
										<fieldset class="border p-3">
											<form:hidden path="id" />
											<div class="row">
												<div class="col-md-6">
													<div class="mb-3">
														<label for="site">Site :</label>
														<form:input type="text" class="form-control" path="site" />
														<form:errors path="site" cssClass="error" />
													</div>
												</div>
												<div class="col-md-6">
													<div class="mb-3">
														<label for="tva">Comptes TVA ? :</label>
														<form:select path="tva" class="form-control">
															<form:option value="true"> Oui</form:option>
															<form:option value="false"> Non</form:option>
														</form:select>
														<form:errors path="tva" cssClass="error" />
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-6">
													<div class="mb-3">
														<label for="type">Compte G�n�rale :</label>
														<form:input type="text" class="form-control"
															path="compteGeneral" />
														<form:errors path="compteGeneral" cssClass="error" />
													</div>
												</div>
												<div class="col-md-6">
													<div class="mb-3">
														<label for="desc1">Description :</label>
														<form:input type="text" class="form-control" path="desc1" />
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-6">
													<div class="mb-3">
														<label for="compteClass3">Compte Class 3 :</label>
														<form:input type="text" class="form-control"
															path="compteClass3" />
													</div>
												</div>
												<div class="col-md-6">
													<div class="mb-3">
														<label for="desc2">Description :</label>
														<form:input type="text" class="form-control" path="desc2" />
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-6">
													<div class="mb-3">
														<label for="compteClass4">Compte Class 4 :</label>
														<form:input type="text" class="form-control"
															path="compteClass4" />
													</div>
												</div>
												<div class="col-md-6">
													<div class="mb-3">
														<label for="desc3">Description :</label>
														<form:input type="text" class="form-control" path="desc3" />
													</div>
												</div>
											</div>

										</fieldset>
										<br>
										<div class="text-end" style="padding-top: 2em;">
											<a
												class="btn btn-secondary rounded-pill px-4 waves-effect waves-light"
												href="${pageContext.request.contextPath}/compte-facturation/">
												<i class="fa fa-arrow-left bigger-110"></i> Retour
											</a>
											<button type="submit"
												class="btn btn-info rounded-pill px-4 waves-effect waves-light">
												<i class="fa fa-plus bigger-110"></i> Enregistrer
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
	
</script>
<jsp:include page="../footer.jsp" />