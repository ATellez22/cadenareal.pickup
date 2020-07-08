<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link REL=StyleSheet HREF="css/estilo_login.css" TYPE="text/css"
	MEDIA=screen>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">

<title>Login</title>
</head>
<body class="p-3 mb-2 bg-danger text-white">
	<div class="container mt-5">
		<div class="row">
			<div class="col-sm">

				<img src="imagenes/real_logo.png" class="rounded mx-auto d-block"
					alt="Responsive image">

				<form action="controller" method="post">
					<input type="hidden" name="seleccion" value="ingresar">
					<table border="0">
						<tr>
							<!-- COLUMNA -->
							<td>Usuario:</td>
							<!-- FILA -->
							<td><input type="text" class="form-control"
								name="txt_usuario" size="30"></td>
						</tr>

						<tr>
							<td>Password:</td>
							<!-- FILA -->
							<td><input type="password" class="form-control"
								name="txt_pass" size="50"></td>
						</tr>
					</table>
					<br>
					<div class="row">
						<div class="col-md-4 col-lg-2">
							<button type="submit" class="btn btn-light">Enviar</button>
						</div>
					</div>					
				</form>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">	

	if (${bandera} == 0 || ${bandera} == 1) {
	
		alert('DATOS INCORRECTOS');	
	
	}

</script>
<html>