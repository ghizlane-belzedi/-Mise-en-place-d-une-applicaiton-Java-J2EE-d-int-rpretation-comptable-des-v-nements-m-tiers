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
          <h5 class="m-0 font-weight-bold text-primary">Recherche des paramtrèes : ${categorie } </h5>
        </div>
        <div class="card-body">
          <div class="text-info">
            <div class="row">
              <div class="col-md-6">
                  <div class="mb-3">
                      <label >Code métier :</label> <input type="text"
                          class="form-control" id="codeMedtier">
                  </div>
              </div>
              <div class="col-md-6">
                <div class="mb-3">
                    <label >Code finance  :</label> <input type="text" class="form-control" id="codeFinance">
                </div>
              </div>
            </div>
          </div>
          <div class="text-end" style="text-align: center; margin-top: 2%;">
            <a onclick="searchParametre()" class="dt-button buttons-collection btn btn-white btn-warning  btn-bold">
								    <span>
									   <i class="fa fa-search bigger-110 green"></i>
									   <span>Rechercher</span>
								    </span>
            </a>
            <a id="link2" onclick="addCategorie()"
               class="dt-button buttons-collection btn btn-white btn-primary  btn-bold">
                <span>
                    <i class="fa fa-plus bigger-110 green"></i>
                    <span>Nouveau(lle) ${categorie}</span>
                </span>
            </a>
          </div>
        </div>
      </div>
    </div>
    <!-- Column -->
  </div>
  <div class="row">
    <!-- column -->
    <div class="col-xl-12 col-lg-7">
      <div class="card shadow mb-4">
        <div
                class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
          <h5 class="m-0 font-weight-bold text-primary">Liste des paramètres</h5>
        </div>
        <div class="card-body">
          <table id="parametres-table" class="table  table-bordered table-hover">
          </table>
        </div>
      </div>
    </div>
  </div>

</div>
<script type="text/javascript">
  var datatable;
  $(document)
          .ready(
                  function() {
                    datatable = $('#parametres-table')
                            .DataTable(
                                    {
                                      pageLength : 10,
                                      "processing" : true,
                                      "serverSide" : true,
                                      "responsive" : true,
                                      "searching" : true,
                                      "lengthMenu": [ [10, 25, 50, -1], [10, 25, 50, "Tout"] ],
                                      "language" : {
                                        url : '${pageContext.request.contextPath}/assets1/json/datatable-fr-FR.json'
                                      },
                                      contentType : "application/json",
                                      ajax : {
                                        url : '${pageContext.request.contextPath}/parametrages/findallByCategorie/' + "${categorie }",
                                        type : "POST",
                                        data : function(data) {
                                          $('#parametres-table_filter').css('display', 'none');
                                        }
                                      },
                                      columns : [
                                        {
                                          title : 'Catégorie',
                                          name : 'categorie',
                                          data : 'categorie'
                                        },
                                        {
                                          title : 'Code métier',
                                          name : 'codeMedtier',
                                          data : 'codeMedtier'

                                        },
                                        {
                                          title : 'Code finance',
                                          name : 'codeFinance',
                                          data : 'codeFinance'
                                        },
                                        {
                                          title : 'Description',
                                          name : 'description1',
                                          data : 'description1'
                                        },
                                        {
                                          title : 'Addtional field',
                                          name : 'addtionalField',
                                          data : 'addtionalField'
                                        },
                                        {
                                          title : 'Addtional field description',
                                          name : 'addtionalFieldDescr',
                                          data : 'addtionalFieldDescr'
                                        },
                                        {
                                          title : 'Actions',
                                          data : 'paramId',
                                          name : 'paramId',
                                          render : function(data) {
                                            return '<div class="hidden-sm btn-group">'
                                                    + '	<a class="btn btn-xs btn-info" title="Modifier"' +
                                                    'aria-controls="simple-table" ' +
                                                    'href="${pageContext.request.contextPath}/parametrages/edit/' + data + '"> <span>'
                                                    + '<i class="ace-icon fa fa-edit bigger-120"></i>'
                                                    + '</span></a>'
                                                    + '</div>';
                                          }
                                        } ]
                                    });
                    $('#parametres-table_filter').css('display', 'none');
                  });
  function searchParametre() {
    var search =  $('#codeMedtier').val() + ";"
				+ $('#codeFinance').val() + ";";
    datatable.search(search).draw();
  }
  function addCategorie() {
    categorie_param = "${categorie}";
    var strLink = "${pageContext.request.contextPath}/parametrages/add/" + categorie_param;
    document.getElementById("link2").setAttribute("href",strLink);
  };
</script>

<jsp:include page="../footer.jsp" />