<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../header.jsp" />
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">PROFIL -> MODIFIER</h1>
	</div>
	<div class="row">

		<div class="col-xl-12 col-lg-7">
			<div class="card shadow mb-4">
				<!-- Card Header - Dropdown -->
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h5 class="m-0 font-weight-bold text-primary">Modifier un
						Profile</h5>
				</div>
				<!-- Card Body -->
				<div class="card-body table-responsive">
					<form:form action="${pageContext.request.contextPath}/profile/edit" method="post"
						modelAttribute="profile">
						<form:hidden path="id" />
						<c:if test="${result == true}">
							<div class="alert alert-success" id="save-success">
								<h6>
									<strong> !</strong> has.success
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
									<label for="code">code :</label>
									<form:input type="text" class="form-control" path="code" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="mb-3">
									<label for="description">Description :</label>
									<form:input type="text" class="form-control" path="description" />
								</div>
							</div>
						</div>
						<h2>
							<span>Liste des authorisations :</span>

						</h2>
						<div class="row">
							<div class="col-md-8"></div>
							<div class="col-md-2" align="center">
								<strong>Tout séléctionner</strong>
							</div>
							<div class="col-md-1">
								<input id="alls" type="checkbox" value="" class="checkbox"
									onclick="selectAlls()" />
							</div>
						</div>
						<table
							class="table table table-bordered table-striped  table-hover">
							<tr>
								<th width="20%">Module</th>
								<th>Sous module</th>
								<th width="20%">modification</th>
								<th width="20%">consultation</th>
								<th width="20%">Validation</th>
								<th width="20%">Tout séléctionner</th>
							</tr>

							<tr>
								<td rowspan="2">Administration</td>
								<td>Gestion des utilisateurs</td>
								<td><input name="roles" onclick="selectOne($(this))"
									class="role" type='checkbox' value="USER_UPDATE"
									${profile.roles.contains("USER_UPDATE")? 'checked' : '' }
									class='checkbox' /></td>
								<td><input name="roles" onclick="selectOne($(this))"
									class="role" type='checkbox' value="USER_CONSULT"
									${profile.roles.contains("USER_CONSULT")? 'checked' : '' }
									class='checkbox' /></td>
								<td></td>
								<td><input class="all" type="checkbox" value=""
									class="checkbox" onclick="selectAll($(this))" /></td>
							</tr>
							<tr>
								<td>Gestion des Profiles</td>
								<td><input name="roles" onclick="selectOne($(this))"
									class="role" type='checkbox' value="PROFILE_UPDATE"
									${profile.roles.contains("PROFILE_UPDATE")? 'checked' : '' }
									class='checkbox' /></td>
								<td><input name="roles" onclick="selectOne($(this))"
									class="role" type='checkbox' value="PROFILE_CONSULT"
									${profile.roles.contains("PROFILE_CONSULT")? 'checked' : '' }
									class='checkbox' /></td>
								<td></td>
								<td><input class="all" type="checkbox" value=""
									class="checkbox" onclick="selectAll($(this))" /></td>
							</tr>
							<tr>
								<td rowspan="4">Parametrage</td>
								<td>Parametrage générique</td>
								<td><input name="roles" onclick="selectOne($(this))"
									class="role" type='checkbox' value="PARAMETRAGE_UPDATE"
									${profile.roles.contains("PARAMETRAGE_UPDATE")? 'checked' : '' }
									class='checkbox' /></td>
								<td><input name="roles" onclick="selectOne($(this))"
									class="role" type='checkbox' value="PARAMETRAGE_CONSULT"
									${profile.roles.contains("PARAMETRAGE_CONSULT")? 'checked' : '' }
									class='checkbox' /></td>
								<td></td>
								<td><input class="all" type="checkbox" value=""
									class="checkbox" onclick="selectAll($(this))" /></td>
							</tr>
							<tr>
								<td>Parametrage des numéros de comptes</td>
								<td><input name="roles" onclick="selectOne($(this))"
									class="role" type='checkbox' value="PARAMETRAGE_COMPTE_UPDATE"
									${profile.roles.contains("PARAMETRAGE_COMPTE_UPDATE")? 'checked' : '' }
									class='checkbox' /></td>
								<td><input name="roles" onclick="selectOne($(this))"
									class="role" type='checkbox' value="PARAMETRAGE_COMPTE_CONSULT"
									${profile.roles.contains("PARAMETRAGE_COMPTE_CONSULT")? 'checked' : '' }
									class='checkbox' /></td>
								<td></td>
								<td><input class="all" type="checkbox" value=""
									class="checkbox" onclick="selectAll($(this))" /></td>
							</tr>
							<tr>
								<td>Parametrage des Fournisseurs</td>
								<td><input name="roles" onclick="selectOne($(this))"
									class="role" type='checkbox'
									value="PARAMETRAGE_FOURNISSEUR_UPDATE"
									${profile.roles.contains("PARAMETRAGE_FOURNISSEUR_UPDATE")? 'checked' : '' }
									class='checkbox' /></td>
								<td><input name="roles" onclick="selectOne($(this))"
									class="role" type='checkbox'
									value="PARAMETRAGE_FOURNISSEUR_CONSULT"
									${profile.roles.contains("PARAMETRAGE_FOURNISSEUR_CONSULT")? 'checked' : '' }
									class='checkbox' /></td>
								<td></td>
								<td><input class="all" type="checkbox" value=""
									class="checkbox" onclick="selectAll($(this))" /></td>
							</tr>
							<tr>
								<td>Parametrage des FLux</td>
								<td><input name="roles" onclick="selectOne($(this))"
									class="role" type='checkbox' value="PARAMETRAGE_FLUX_UPDATE"
									${profile.roles.contains("PARAMETRAGE_FLUX_UPDATE")? 'checked' : '' }
									class='checkbox' /></td>
								<td><input name="roles" onclick="selectOne($(this))"
									class="role" type='checkbox' value="PARAMETRAGE_FLUX_CONSULT"
									${profile.roles.contains("PARAMETRAGE_FLUX_CONSULT")? 'checked' : '' }
									class='checkbox' /></td>
								<td></td>
								<td><input class="all" type="checkbox" value=""
									class="checkbox" onclick="selectAll($(this))" /></td>
							</tr>
							<tr>
								<td rowspan="2">Simulation</td>
								<td>Génération</td>
								<td><input name="roles" onclick="selectOne($(this))"
									class="role" type='checkbox' value="GENERATION_UPDATE"
									${profile.roles.contains("SIMULATION_UPDATE")? 'checked' : '' }
									class='checkbox' /></td>
								<td></td>
								<td></td>
								<td><input class="all" type="checkbox" value=""
									class="checkbox" onclick="selectAll($(this))" /></td>
							</tr>
							<tr>
								<td>Simulation</td>
								<td><input name="roles" onclick="selectOne($(this))"
									class="role" type='checkbox' value="SIMULATION_UPDATE"
									${profile.roles.contains("SIMULATION_UPDATE")? 'checked' : '' }
									class='checkbox' /></td>
								<td><input name="roles" onclick="selectOne($(this))"
									class="role" type='checkbox' value="SIMULATION_CONSULT"
									${profile.roles.contains("SIMULATION_CONSULT")? 'checked' : '' }
									class='checkbox' /></td>
								<td></td>
								<td><input class="all" type="checkbox" value=""
									class="checkbox" onclick="selectAll($(this))" /></td>
							</tr>
						</table>
						<br>

						<div class="text-end" style="padding-top: 2em;">
							<a
								class="btn btn-secondary rounded-pill px-4 waves-effect waves-light"
								href="${pageContext.request.contextPath}/profile/"> <i class="fa fa-arrow-left bigger-110"></i>
								Retour
							</a>
							<button type="submit"
								class="btn btn-info rounded-pill px-4 waves-effect waves-light">
								<i class="fa fa-save bigger-110"></i> Enregistrer
							</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>

	</div>

</div>
<!-- End of Main Content -->
<script type="text/javascript">
	$(".all").closest('td').css('text-align', 'center');
	$(".role").closest('td').css('text-align', 'center');
	function selectAlls() {
		var seelectAlls = document.getElementById("alls").checked;
		if (seelectAlls == true) {
			$('.role').each(function(index, element) {
				$(element).prop('checked', true)
			})
			$('.all').each(function(index, element) {
				$(element).prop('checked', true)
			})
		} else {
			$('.role').each(function(index, element) {
				$(element).prop('checked', false)
			})
			$('.all').each(function(index, element) {
				$(element).prop('checked', false)
			})
		}

	}
	function selectAll(elem) {
		var seelectAlls = $(elem).prop('checked')
		if (seelectAlls == true) {
			$(elem).closest('tr').find('.role').each(function(index, element) {
				$(element).prop('checked', true)
			})
		} else {
			document.getElementById("alls").checked = $(elem).prop('checked')
			$(elem).closest('tr').find('.role').each(function(index, element) {
				$(element).prop('checked', false)
			})
		}

	}
	function selectOne(elem) {
		var select = $(elem).prop('checked')
		if (select == false) {
			$(elem).closest('tr').find('.all').prop('checked', false);
			document.getElementById("alls").checked = false;
		}
	}
</script>
<jsp:include page="../footer.jsp" />