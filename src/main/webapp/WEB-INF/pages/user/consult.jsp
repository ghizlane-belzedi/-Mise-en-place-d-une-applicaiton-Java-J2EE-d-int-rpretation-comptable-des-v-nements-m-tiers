<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
							<h4 class="card-title">Consulter un Utilisateur</h4>
							<form:form action="${pageContext.request.contextPath}/user/consult" method="post"
								modelAttribute="user">
								<fieldset class="border p-3">
									<legend> Utilisateur </legend>
									<form:hidden path="userId" />
									<div class="row">
										<div class="col-md-6">
											<div class="mb-3">
												<label for="prenom">nom :</label>
												<form:input disabled="true" type="text" class="form-control"
													path="nom" />
											</div>
										</div>
										<div class="col-md-6">
											<div class="mb-3">
												<label for="prenom">Prénom :</label>
												<form:input disabled="true" type="text" class="form-control"
													path="prenom" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="mb-3">
												<label for="email">UserName :</label>
												<form:input disabled="true" type="text" class="form-control"
													path="userName" />
											</div>
										</div>
										<div class="col-md-6">
											<div class="mb-3">
												<label for="tel">Profile :</label>
												<form:select disabled="true" class="form-control"
													path="profile">
													<form:option value="">---- Séléctionnée un Profile ----</form:option>
													<c:forEach var="profile" items="${ profiles}">
														<form:option value="${ profile.id}">${ profile.description}</form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<label>Flux :</label>
											<c:forEach var="f1" items="${ flux}">
												<c:forEach var="f" items="${ user.flux}">
													<c:if test="${f1.key == f }"><br> ${f1.value } </c:if>
												</c:forEach>
											</c:forEach>
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