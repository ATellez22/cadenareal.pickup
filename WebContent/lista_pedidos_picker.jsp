<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<LINK REL=StyleSheet HREF="css/estilos.css" TYPE="text/css" MEDIA=screen>

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">


<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body class="p-3 mb-2 bg-light text-dark">
	<h6>
		<ins>
			<b>BIENVENIDO:</b>
		</ins>
		<mark>${usuario}</mark>
	</h6>

	<a href="login.jsp" style="color: #ab2a3e;"><b>Cerrar sesion</b></a>

	<table border="1" id="table">
		<thead>
			<tr>
				<td align="center"><b>N° de pedido</b></td>
				<td align="center"><b>Estado</b></td>
			</tr>
		</thead>
		<c:forEach var="pedido" items="${lista}">
			<tr id="celda" onclick="cambiar_color_over(this)">
				<td align="center" style="font-weight: bold"><c:out
						value="${ pedido.num_pedido}"></c:out></td>
				<td align="center"><c:out value="${ pedido.estado}"></c:out></td>
			</tr>

		</c:forEach>
	</table>

	<footer class="blockquote-footer">Creado por A.T. Ribeiro</footer>

	<script type="text/javascript">
				
		if (${bandera} == 2) {
	
			alert('SESION CORRECTA');	
	
		}			
	</script>

	<script type="text/javascript">
	
		var value = "";

		$("#table tr").click(function() {
			$(this).addClass('selected').siblings().removeClass('selected');
			value = $(this).find('td:first').html();			
			
			alert(value);
			
			location.href = "controller?seleccion_pedido=" + value;
			
		});

		$('.ok').on('click', function(e) {
			
			alert($("#table tr.selecteds td:first").html());			
			
		});
	</script>

	<script type="text/javascript">
	
		function cambiar_color_over(celda) {
		   celda.style.backgroundColor="#ab2a3e";
		   celda.style.color="#ebeae4";
		   		   
		}
		
	</script>

</body>
</html>