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
							<h4 class="card-title">Modifier un Flux</h4>
							<form:form action="${pageContext.request.contextPath}/parametrage-flux/edit" method="post"
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
												<form:input type="text" class="form-control" path="nom" />
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
												<th width="2%"><input class="form-check-input"
													type="checkbox" value="" id="visible"></th>
												<th>Code</th>
												<th>Description</th>
												<td>Ordre</td>
												<td style="display:none"> Id</td>
											</tr>
											<c:forEach var="f" items="${ flux.fields}">
												<tr class="${f.visible ? 'sortable' : ''}">
													<td><input class="form-check-input" type="checkbox"
														${f.visible ? 'checked' : '' } id="visible"></td>
													<td>${f.code }</td>
													<td><input type="text" class="form-control"
														id="description" value="${f.description }" /></td>
													<td>${f.ordre }</td>
													<td style="display:none"> ${f.id }</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									
								</fieldset>
								<br>

								<div class="text-end" style="padding-top: 2em;">
									<a
										class="btn btn-secondary rounded-pill px-4 waves-effect waves-light"
										href="${pageContext.request.contextPath}/parametrage-flux/"> <i
										class="fa fa-arrow-left bigger-110"></i> Annuler
									</a>
									<a
										class="btn btn-info rounded-pill px-4 waves-effect waves-light"
										 onclick="saveFlux()"> <i
										class="fa fa-save bigger-110"></i> Enregistrer
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
	function saveFlux() {
		var fields = [];
		$("#parametrage-flux-table tr:not(tr:first-child)").each(function(index) {
			fields.push({
				id : $(this).find('td').eq(4).html(),
				code : $(this).find('td').eq(1).html(),
				description : $(this).find('td').eq(2).find('input').val(),
				ordre : index +1,
				visible : $(this).find('input[type=checkbox]').is(':checked')
			})
		});
		var flux = {
				fluxId : $('#fluxId').val(),
				code : $('#code').val(),
				nom : $('#nom').val(),
				fields : fields
		}
		console.log(flux)
		csrfHeader = $("meta[name='_csrf_header']").attr("content");
		csrfToken = $("meta[name='_csrf']").attr("content");
		var headers = {};
		headers[csrfHeader] = csrfToken;
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/parametrage-flux/edit/",
			contentType : 'application/json;charset=UTF-8',
			dataType: 'json',
			//headers : headers,
			data: JSON.stringify(flux),
			success : function(data) {
				console.log(data);
				
			},
			error : function(data) {
				console.log(data.responseJSON.message)
			}
		});

	}
		$(function() {
			createSortable();
			$('#parametrage-flux-table tr:first-child input[type=checkbox]').change(function() {
				if(this.checked ) {
					$('#parametrage-flux-table tr:not(tr:first-child)').each(function(index) {
			            $(this).find('input[type=checkbox]').prop( "checked", true );
			            $(this).addClass('sortable');
			        });
				}
				else {
					$('#parametrage-flux-table tr:not(tr:first-child)').each(function() {
			            $(this).find('input[type=checkbox]').prop( "checked", false );
			            $(this).removeClass('sortable');
			        });
				}
				$("#parametrage-flux-table").sortable("destroy");
				createSortable();
			});
			$('#parametrage-flux-table tr:not(tr:first-child) input[type=checkbox]').change(function() {
				console.log($(this).parent().parent().index())
				console.log($(this).parent().parent())
				$("#parametrage-flux-table tr").eq(1).after('')
				if(this.checked) {
					$("#parametrage-flux-table tr").eq(0).after('<tr class="sortable">' + $(this).parent().parent().html()+'</tr>');
					$("#parametrage-flux-table tr").eq(1).find('input[type=checkbox]').prop( "checked", true );
					$(this).parent().parent().remove();
				}
				else {
					$(this).parent().parent().removeClass('sortable')
					$("#parametrage-flux-table tr").eq($("#parametrage-flux-table tr").length-1).after('<tr class="">' + $(this).parent().parent().html()+'</tr>');
					$("#parametrage-flux-table tr").eq($("#parametrage-flux-table tr").length-1).find('input[type=checkbox]').prop( "checked", false );
					$(this).parent().parent().remove();
				}
				$("#parametrage-flux-table").sortable("destroy");
				createSortable();
			});
		});
		function refreshOrdre() {
			$("#parametrage-flux-table tr:not(tr:first-child)").each(function(index) {
				if(!$(this).find('input[type=checkbox]').is(':checked')) {
					$(this).find("td").eq(3).html(0);
				} else if (index >= 0) {
					$(this).find("td").eq(3).html(index+1);
				}
			});
		}
		function createSortable() {
			$('#fieldset-fields p span').html($('#parametrage-flux-table tr:not(tr:first-child) input[type=checkbox]:checked').length);
			
			$("#parametrage-flux-table").sortable({
				items : '.sortable',
				cursor : 'pointer',
				axis : 'y',
				dropOnEmpty : false,
				start : function(e, ui) {
					if(!ui.item.find('input[type=checkbox]').is(':checked')){
					}
					ui.item.addClass("selected");
					
				},
				stop : function(e, ui) {
					ui.item.removeClass("selected");
					refreshOrdre();
				}
			});
			
		}
		
	</script>
	<jsp:include page="../footer.jsp" />