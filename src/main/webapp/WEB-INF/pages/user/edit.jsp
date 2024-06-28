<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../header.jsp" />
<div class="page-wrapper">
	<!-- ============================================================== -->
	<!-- Container fluid  -->
	<!-- ============================================================== -->
	<div class="container-fluid">
		<div class="row">
			<!-- column -->
			<div class="col-sm-12">
				<div class="card">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">Modifier un Utilisateur</h4>
							<form:form action="${pageContext.request.contextPath}/user/edit" method="post" modelAttribute="user">
								<fieldset class="border p-3">
									<legend> Utilisateur </legend>
									<form:hidden path="userId"/>
									<c:if test="${result == true}">
										<div class="alert alert-success" id="save-success">
											<h6>
												<strong> !</strong>
												has.success
											</h6>
										</div>
									</c:if>
									<c:if test="${result == false}">
										<div class="alert alert-danger" id="msgError">
											<div class="form-group">
												<div class="col-md-10">
													<h6>
														<strong>Erreur!! </strong>
													</h6>
												</div>
											</div>
											<div class="form-group">
												<c:forEach items="${errors }" var="error">
													${error}<br>
												</c:forEach>
											</div>
										</div>
									</c:if>
									<div class="row">
										<div class="col-md-6">
											<div class="mb-3">
												<label for="prenom">nom :</label>
												<form:input type="text" class="form-control" path="nom" />
											</div>
										</div>
										<div class="col-md-6">
											<div class="mb-3">
												<label for="prenom">Pr�nom :</label>
												<form:input type="text" class="form-control" path="prenom" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="mb-3">
												<label for="email">UserName :</label>
												<form:input readonly="true" type="text" class="form-control" path="userName" />
											</div>
										</div>
										<div class="col-md-6">
											<div class="mb-3">
												<label for="tel">Profile :</label> <form:select class="form-control"
													path="profile">
													<form:option value="">---- S�l�ctionn�e un Profile ----</form:option>
													<c:forEach var="profile" items="${ profiles}">
														<form:option value="${ profile.id}">${ profile.description}</form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
												<label>Flux :</label> <form:select class="form-control" path="flux">
													<form:option value="">--- Choisir un flux</form:option>
													<c:forEach var="f" items="${ flux}">
														<form:option value="${f.key }">${f.value }</form:option>
													</c:forEach>
												</form:select>
											</div>
									</div>
								</fieldset>
								<br>
								
								<div class="text-end" style="padding-top: 2em;">
									<a
										class="btn btn-secondary rounded-pill px-4 waves-effect waves-light"
										href="${pageContext.request.contextPath}/user/"> <i class="fa fa-arrow-left bigger-110"></i>
										Retour
									</a>
									<button type="submit"
										class="btn btn-info rounded-pill px-4 waves-effect waves-light">
										<i class="fa fa-save bigger-110"></i> Enregistrer
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
		<!-- ============================================================== -->
		<!-- Table -->
		<!-- ============================================================== -->
		<div class="row">
			<div class="col-sm-12"></div>
		</div>
		<!-- ============================================================== -->
		<!-- Table -->
		<!-- ============================================================== -->

	</div>
	<!-- ============================================================== -->
	<!-- End Container fluid  -->
	<!-- ============================================================== -->
	<script type="text/javascript">
		function cleanConsultant() {

		}
		function searchByCin() {
			var cin = $('#cin').val();
			console.log(cin)
			csrfHeader = $("meta[name='_csrf_header']").attr("content");
			csrfToken = $("meta[name='_csrf']").attr("content");
			var headers = {};
			headers[csrfHeader] = csrfToken;
			$.ajax({
				type : "GET",
				url : "/contact/findConsultant?cin=" + cin,
				contentType : 'application/json;charset=UTF-8',
				//headers : headers,
				success : function(data) {
					console.log(data);
					$("#consultantId").val(data.contactId);
					$("#nomConsultant").val(data.nom);
					$("#prenomConsultant").val(data.prenom);
					$("#rib").val(data.rib);
					$("#banque").val(data.banque);
					$("#profile").val(data.profile);
				},
				error : function(data) {
					console.log(data.responseJSON.message)
				}
			});

		}
	</script>
	<jsp:include page="../footer.jsp" />