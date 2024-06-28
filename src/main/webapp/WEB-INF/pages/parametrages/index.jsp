<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp" />
<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">PARAMETRAGES -> INDEX</h1>
	</div>
	<div class="row">
		<div class="col-xl-12 col-lg-7">
			<div class="card shadow mb-4">
				<!-- Card Header - Dropdown -->
				<div
						class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h5 class="m-0 font-weight-bold text-primary">Choisir une catégorie</h5>
				</div>
				<div class="card-body">
					<div class="text-info">
						<div class="row">
							<div class="col-md-4"></div>
							<div class="col-md-4">
								<label>Catégorie :</label>
								<select class="form-control" id="categorie">
									<option value="">--- Choisir une catégorie</option>
									<option value="currency">Devise</option>
									<option value="marque">Marque</option>
									<option value="site">Site</option>
									<option value="tva">TVA</option>
									<% /** 
									<c:forEach var="c" items="${ categories}">
										<option value="${c }">${c }</option>
									</c:forEach>
									**/ %>
								</select>
							</div>
						</div>

					</div>
					<div class="text-end" style="text-align: center; margin-top: 2%;">
						<a id="link" onclick="addCategorie()"

						   class="dt-button buttons-collection btn btn-white btn-warning  btn-bold">
								    <span>
									   <i class="fa fa-search bigger-110 green"></i>
									   <span>Rechercher</span>
								    </span>
						</a>
					</div>
				</div>
			</div>
		</div>
		<!-- Column -->
	</div>

</div>
<script type="text/javascript">
	function searchParametre() {
		var search = $('#categorie').val() + ";";
		datatable.search(search).draw();
	}
	function addCategorie() {
		var categorie_param = $('#categorie').val();
		var strLink = "${pageContext.request.contextPath}/parametrages/indexCategorie/" + categorie_param;
		document.getElementById("link").setAttribute("href",strLink);
	};
</script>

<jsp:include page="../footer.jsp" />