<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<LINK REL=StyleSheet HREF="css/estilos.css" TYPE="text/css" MEDIA=screen>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">

<title>Insert title here</title>
</head>
<body class="p-3 mb-2 bg-light text-dark">

	<h6>
		<ins>
			<b>USUARIO:</b>
		</ins>
		<mark>${usuario}</mark>
	</h6>

	<a class="btn btn-primary" data-toggle="collapse"
		href="controller?seleccion_pedido=cancelar_lista" role="button"
		aria-expanded="false" aria-controls="collapseExample"> Cancelar </a>

	<div class="table-responsive">

		<table border="1" id="table" class="table">
		<thead>
			<tr>
				<td align="center"><b>Articulos</b></td>				
			</tr>
		</thead>
			<c:forEach var="item" items="${lista}">
				<tr id="celda" onclick="cambiar_color_over(this)">
					<td><c:out value="${ item.codigo_barra}"></c:out> <br> <c:out
							value="${ item.descripcion}"></c:out> <br> <c:out
							value="${ item.cantidad}"></c:out> <br> <c:out
							value="${ item.seccion}"></c:out> <br> <c:out
							value="${ item.num_pedido}"></c:out> <br> <c:out
							value="${ item.obs}"></c:out></td>
				</tr>
			</c:forEach>
		</table>

	</div>
	
	<script type="text/javascript">
	
		document.body.style.zoom="300%"
		
	</script>

	<script type="text/javascript">
		var value = "";

		$("#table tr").click(
				function() { //CLIC EN UNA CELDA Y ENVIO DE DATOS A controller
					$(this).addClass('selected').siblings().removeClass(
							'selected');
					value = $(this).find('td:first').html();

					var value2 = String(value);

					//alert(value2);

					location.href = "controller?seleccion_pedido="
							+ value2.split('<br>');

				});

		$('.ok').on('click', function(e) {

			//alert($("#table tr.selecteds td:first").html());

		});
	</script>

	<script type="text/javascript">
		function cambiar_color_over(celda) {
			celda.style.backgroundColor = "#ab2a3e"
			celda.style.color = "#ebeae4"
		}
	</script>

</body>
</html>