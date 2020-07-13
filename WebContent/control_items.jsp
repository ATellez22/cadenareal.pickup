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


<title>Insert title here</title>
</head>
<body class="p-3 mb-2 bg-light text-dark">

	<h6>
		<ins>
			<b>N° DE PEDIDO:</b>
		</ins>
		<mark>${num_pedido}</mark>
	</h6>

	<form action="controller2" method="post" id="myForm2">
		<input type="hidden" name="seleccion" value="${num_pedido}">
	
		<button type="submit" class="btn btn-primary" >Volver</button>	
	
	</form>


<!-- <a class="btn btn-primary" data-toggle="collapse" -->
<!-- 		href="controller?seleccion_pedido=" role="button" -->
<!-- 		aria-expanded="false" aria-controls="collapseExample"> Volver </a> -->

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
						size="30" id="txt_cod2" oninput="buscar(this)"></td>
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
					
				<!-- VALOR OCULTO PARA ENVIAR NUM_PEDIDO AL CONTROLLER 2 Y ASI EVITAR QUE AL VOLVER NO TENGA NUM_PEDIDO -->	
				<tr>
					<td><input type="hidden" class="form-control" name="num_pedido"
						id="num_pedido" size="50"></td>
				</tr>
				<!-- VALOR OCULTO PARA ENVIAR NUM_PEDIDO AL CONTROLLER 2 Y ASI EVITAR QUE AL VOLVER NO TENGA NUM_PEDIDO -->	
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
	
		document.body.style.zoom="300%"
		
		$("#num_pedido").val("${num_pedido}");
		
	</script>

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
		//APLICAR LA BUSQUEDA DEL ARTICULO CUANDO INTRODUZCA UN CODIGO CON EL ESCANER O POR TECLADO (AUNQUE ES LENTO)

		// Suponga que una pérdida de enfoque significa que el valor ha terminado de ingresarse
		var inputStart, inputStop, firstKey, lastKey, timing, userFinishedEntering;
		var minChars = 3;

		$("#txt_cod2").keypress(function(e) {
			// reiniciar el temporizador
			if (timing) {
				clearTimeout(timing);
			}

			// manejar el evento clave
			if (e.which == 13) {
				// Se ingresó la clave

				//no enviar el formulario
				e.preventDefault();

				// ¿El usuario ha terminado de ingresar manualmente?
				if ($("#txt_cod2").val().length >= minChars) {
					userFinishedEntering = true; // En caso de que la usuario presione la tecla enter
					inputComplete();
				}
			} else {
				// se ingresó algún otro valor clave

				// Podría ser el último caracter
				inputStop = performance.now();
				lastKey = e.which;

				// no asumas que ya está terminado
				userFinishedEntering = false;

				// ¿Es este el primer caracter?
				if (!inputStart) {
					firstKey = e.which;
					inputStart = inputStop;

					// estar atento a una pérdida de enfoque
					$("body").on("blur", "#txt_cod2", inputBlur);
				}

				// iniciar el tiempo de nuevo
				timing = setTimeout(inputTimeoutHandler, 500);
			}
		});

		function inputBlur() {
			clearTimeout(timing);
			if ($("#txt_cod2").val().length >= minChars) {
				userFinishedEntering = true;
				inputComplete();
			}
		};

		// reinicia la pagina
		$("#reset").click(function(e) {
			e.preventDefault();
			resetValues();
		});

		function resetValues() {
			// limpiar variables
			inputStart = null;
			inputStop = null;
			firstKey = null;
			lastKey = null;
			// limpiar resultados
			inputComplete();
		}

		// Suponga que es del escáner si se ingresó realmente rápido
		function isScannerInput() {
			return (((inputStop - inputStart) / $("#txt_cod2").val().length) < 15);
		}

		// Determinar si el usuario está escribiendo lentamente
		function isUserFinishedEntering() {
			return !isScannerInput() && userFinishedEntering;
		}

		function inputTimeoutHandler() {
			// dejar de escuchar un evento de temporizador
			clearTimeout(timing);
			// si el valor se está ingresando manualmente y no se ha terminado de ingresar
			if (!isUserFinishedEntering() || $("#txt_cod2").val().length < 3) {
				// sigue esperando la entrada
				return;
			} else {
				reportValues();
			}
		}

		// aquí decidimos qué hacer ahora que sabemos que un valor ha sido ingresado por completo
		function inputComplete() {
			// deja de escuchar la entrada para perder el foco
			$("body").off("blur", "#txt_cod2", inputBlur);
			// informar los resultados
			reportValues();
		}

		function reportValues() {
			// actualizar las metricas
			$("#startTime").text(inputStart == null ? "" : inputStart);
			$("#firstKey").text(firstKey == null ? "" : firstKey);
			$("#endTime").text(inputStop == null ? "" : inputStop);
			$("#lastKey").text(lastKey == null ? "" : lastKey);
			$("#totalTime").text(
					inputStart == null ? "" : (inputStop - inputStart)
							+ " milliseconds");
			if (!inputStart) {
				// limpiar los resultados
				$("#resultsList").html("");
				$("#txt_cod2").focus().select();
			} else {
				// anteponer otro elemento de resultado
				var inputMethod = isScannerInput() ? "Scanner" : "Keyboard";
				$("#resultsList").prepend($("#txt_cod2").val());
				$("#txt_cod2").focus().select();
				inputStart = null;

			}

		}

		//////////////////////////////////////////////////////////////////////////////////////////////////////

		function buscar() { //BUSCAR EL ARTICULO TRAS PASAR EL ESCANER, Y ENVIAR LA INFORMACION A controller2.

			var codigo2 = (document.getElementById('txt_cod2').value).trim();

			var codigo1 = document.getElementById('txt_cod1').value
			var descripcion1 = document.getElementById('txt_des1').value
			var cantidad1 = document.getElementById('txt_can1').value
			
			var num_pedido = document.getElementById('num_pedido').value
			
			location.href = "controller2?seleccion_articulo=" + codigo2 + ","
					+ codigo1 + "," + descripcion1 + "," + cantidad1 + "," +num_pedido;

		}
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

				if (opcion == true) { //ENVIAR AL FORMULARIO

					document.getElementById("myForm").submit(); 

				} else { //VOLVER AL PRINCIPIO

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