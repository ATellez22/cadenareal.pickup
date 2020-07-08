<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>BIENVENIDO</h1>
	
	<h4>USUARIO CONECTADO: ${usuario} </h4>
	
	<table border="1">
		<tr>
			<td>N° de pedido</td>
			<td>Supervisor</td>
			<td>Picker</td>
			<td>Fecha</td>
			<td>Sucursal</td>
			<td>Estado</td>			
		</tr>
		<c:forEach var="pedido" items="${lista}">
			<tr>
				<td> <a href="pedidos?opcion=preparar&num_pedido=<c:out value="${ pedido.num_pedido}"></c:out>"> <c:out value="${ pedido.num_pedido}"></c:out>  </a> </td>
				<td> <c:out value="${ pedido.supervisor}"></c:out> </td>
				<td> <c:out value="${ pedido.picker}"></c:out></td>
				<td> <c:out value="${ pedido.fecha}"></c:out> </td>
				<td><c:out value="${ pedido.sucursal}"></c:out> </td>				
				<td> <c:out value="${ pedido.estado}"></c:out> </td>				
			</tr>
		</c:forEach>
	</table>
	
	

	<a href="login.jsp">Cerrar sesión</a>

	<script type="text/javascript">	

			if (${bandera} == 2) {
	
				alert('SESION CORRECTA');	
	
			}				
		
	</script>

</body>
</html>