<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fieldset class="border p-3">
	<legend> <spring:message code="piece.comptable"/> </legend>
	<div class="row">
		<div class="col-md-6">
			<div class="mb-3">
				<label for="dateSimuation">Flux :</label> ${piece.flux }

			</div>
		</div>
		<div class="col-md-6">
			<div class="mb-3">
				<label for="dateSimuation">Statut :</label> ${piece.statut ? 'Envoyé' : 'Non envoyé'}

			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="mb-3">
				<label for="dateSimuation">Code journale :</label>
				${piece.codeJournale}

			</div>
		</div>
		<div class="col-md-6">
			<div class="mb-3">
				<label for="dateArreter">Numéro de piece :</label>
				${chargement.numeroPiece}
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="mb-3">
				<label for="dateGeneration">Date Piece :</label> ${piece.date}
			</div>
		</div>
		<div class="col-md-6">
			<div class="mb-3">
				<label for="dateGeneration">Description :</label>
				${piece.description }
			</div>
		</div>
	</div>
	<div class="table-responsive">

		<table id="simple-table" class="table  table-bordered table-hover">
			<thead>
				<tr>
					<th>Account number</th>
					<th>Cost center</th>
					<th style="width :20%">Account description</th>
					<th>Ref 1</th>
					<th>Ref 2</th>
					<th>Ref 3</th>
					<th>Ref 4</th>
					<th>Ref 5</th>
					<th>Ref 6</th>
					<th>currency amount<br> (debit)
					</th>
					<th>currency amount <br>(credit)
					</th>
					<th>DEBIT (MAD)</th>
					<th>CREDIT ( MAD)</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="ecriture" items="${ piece.ecritures}">
					<tr>
						<td>${ecriture.ncpt}</td>
						<td>${ecriture.costCenter}</td>
						<td>${ecriture.accountDescription}</td>
						<td>${ecriture.ref1}</td>
						<td>${ecriture.ref2}</td>
						<td>${ecriture.ref3}</td>
						<td>${ecriture.ref4}</td>
						<td>${ecriture.ref5}</td>
						<td>${ecriture.ref6 }</td>
						<c:if test="${ecriture.sens == 'D' }">
							<td>${ecriture.montant}</td>
							<td></td>
							<td>${ecriture.montantMAD }</td>
							<td></td>
						</c:if>
						<c:if test="${ecriture.sens == 'C' }">
							<td></td>
							<td>${ecriture.montant}</td>
							<td></td>
							<td>${ecriture.montantMAD }</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</fieldset>