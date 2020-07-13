<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link REL=StyleSheet HREF="css/estilo_control.css" TYPE="text/css"
	MEDIA=screen>

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


<title>control 3</title>
</head>
<body class="p-3 mb-2 bg-light text-dark">

	<a class="btn btn-primary" data-toggle="collapse"
		href="controller?seleccion_pedido=cancelar" role="button"
		aria-expanded="false" aria-controls="collapseExample"> </a>

	<form action="controller2" method="post" id="myForm">
		<input type="hidden" name="seleccion" value="controlar">

		<div class="form-group">

			<p>
			<h6>
				<ins>
					<center>Código a verificar:</center>
				</ins>
			</h6>
			</p>

			<table>

				<tr>
					<!-- COLUMNA -->
					<td></td>

				</tr>

				<tr>
					<!-- COLUMNA -->
					<td><b>Código: </b></td>
					<!-- FILA -->
					<td><input type="text" class="form-control" name="txt_cod1"
						size="30" id="txt_cod1"></td>
				</tr>

				<tr>
					<td><b>Descripcion: </b></b></td>
					<!-- FILA -->
					<td><input type="text" class="form-control" name="txt_des1"
						size="50" id="txt_des1"></td>
				</tr>

				<tr>
					<td><b>Cantidad: </b></td>
					<!-- FILA -->
					<td><input type="text" class="form-control" name="txt_can1"
						size="50" id="txt_can1"></td>
				</tr>

			</table>
			<br>

			<p>
			<h6>
				<ins>
					<center>Código a ingresar:</center>
				</ins>
			</h6>
			</p>

			<table>

				<tr>
					<!-- COLUMNA -->
					<td><b>Código: </b></td>
					<!-- FILA -->
					<td><input type="text" class="form-control" name="txt_cod2"
						size="30" id="txt_cod2"></td>
					<!-- FUNCION "BUSCAR" SE ACTIVA TRAS RECIBIR INFORMACION DEL ESCANER -->
				</tr>

				<tr>
					<td><b>Descripcion: </b></td>
					<!-- FILA -->
					<td><input type="text" class="form-control" name="txt_des2"
						size="50" id="txt_des2"></td>
				</tr>

				<tr>
					<td><b>Cantidad: </b></td>
					<!-- FILA -->
					<td><input type="text" class="form-control" name="txt_can2"
						id="txt_can2" size="50"></td>
				</tr>


				</div>

			</table>
			<br>
	</form>

	<div class="row">
		<div class="col-md-4 col-lg-2">
			<button class="btn btn-primary" id="btn_enviar"
				onclick="confirmar();">Enviar</button>
		</div>
	</div>

	<div id="resultsList"></div>
	<!--MUESTRA EL ARTICULO PROVISORIAMENTE -->

	<script type="text/javascript">
		//VALIDACIONES AL INICIO

		document.getElementById("txt_cod1").readOnly = true;
		document.getElementById("txt_des1").readOnly = true;
		document.getElementById("txt_can1").readOnly = true;

		document.getElementById("txt_des2").readOnly = true;

		var des = document.getElementById("txt_des2").value;

		if (des == "-") { //SI LA DESCRIPCIÓN ESTA VACIA, SE DESHABILITA EL EL TEXT DE 'CANTIDAD' Y EL BOTÓN DE ENVIAR.

			btn_enviar.disabled = true;

			document.getElementById("txt_can2").readOnly = true

		}
	</script>

	<script type="text/javascript">
	
		//TOMAR VALOR CON TECLADO TRAS UNOS SEGUNDOS
	
		var typingTimer; //timer identifier
		var doneTypingInterval = 500; //time in ms, 5 second for example

		//on keyup, start the countdown
				    	
		    	$('#txt_cod2').keyup(function() {	//copy-paste
					
		   		 clearTimeout(typingTimer);
		   			if ($('#txt_cod2').val) {	
		   																			
		   				typingTimer = setTimeout(function() {
		   					//do stuff here e.g ajax call etc....
		   					var codigo2 = $("#txt_cod2").val().trim();
		   										
		   					//ENVIAR LA INFORMACION A controller2.
		   					var codigo1 = document.getElementById('txt_cod1').value
		   					var descripcion1 = document.getElementById('txt_des1').value
		   					var cantidad1 = document.getElementById('txt_can1').value

		   					location.href = "controller2?seleccion_articulo=" + codigo2 + ","
		   							+ codigo1 + "," + descripcion1 + "," + cantidad1;
		   					
		   					//ENVIAR LA INFORMACION A controller2.
		   														
		   				}, doneTypingInterval);
		   			}
		   		});		    	
		    			
	</script>

	<script type="text/javascript">
		//TRAS RECIBIR PARAMETROS DE controller2

		var descripcion2 = "${descripcion2}"

		if (descripcion2 == "") {

			$("#txt_des2").val('-');

			txt_cod2.focus(); //Si esta vacio, significa que el codigo buscado no existe. El foco le corresponde a txt_cod2

		} else if (descripcion2 == "-") {

			$("#txt_des2").val('-');

			txt_cod2.focus(); //Si esta vacio, significa que el codigo buscado no existe. El foco le corresponde a txt_cod2

		} else {

			txt_can2.focus(); //Si no esta vacio, significa que el codigo existe. El foco le corresponde a txt_can2

		}
	</script>

	<script type="text/javascript">
		function confirmar() {//VALIDAR EL CODIGO INGRESADO ANTES DEL SUBMIT

			var codigo2 = "";
			var codigo1 = "";

			codigo2 = document.getElementById('txt_cod2').value;
			codigo1 = document.getElementById('txt_cod1').value;

			var num2 = parseInt(codigo2);
			var num1 = parseInt(codigo1);

			console.log("codigo1: " + num1);
			console.log("codigo2: " + num2);

			if (num2 === num1) { //SI SON IGUALES, SIGUE ADELANTE

				console.log("iguales");

				document.getElementById("myForm").submit();

			} else { //SI SON DIFERENTES, SE PIDE CONFIRMACIÓN PARA SUSTITUIR

				console.log("diferentes");

				var mensaje;

				var opcion = confirm("Códigos diferentes, ¿Desea sustituir?");

				if (opcion == true) {

					document.getElementById("myForm").submit();

				} else {

					$("#txt_cod2").val("");
					$("#txt_des2").val("-");
					$("#txt_can2").val("");

					txt_cod2.focus();

				}

			}

		}
	</script>

	<script type="text/javascript">
		//RECIBIR PARAMETROS DESDE lista_items.jsp

		$("#txt_cod1").val("${codigo_barra}");
		$("#txt_des1").val("${descripcion}");
		$("#txt_can1").val("${cantidad}");

		//RECIBIR PARAMETROS DESDE controller2 - GET

		$("#txt_cod2").val("${codigo_barra2}");
		$("#txt_des2").val("${descripcion2}"); //CUANDO SE ENCUENTRE UN ARTICULO

		var bandera = "${bandera}"; //BANDERA QUE VIENE DE controller2. 

		if (bandera == "0") {

			alert("Verifique la cantidad ingresada");

		} else if (bandera == "1") {

			alert("Articulo verificado");

		}
	</script>

</body>
</html>