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

	<form action="controller" method="post">
		<input type="hidden" name="seleccion" value="controlar">
		<table border="0">
			<tr>
				<!-- COLUMNA -->
				<td>Código:</td>
				<!-- FILA -->
				<td><input type="text" class="form-control" name="txt_cod1"
					size="30" id="txt_cod1"></td>
			</tr>

			<tr>
				<td>Descripcion:</td>
				<!-- FILA -->
				<td><input type="text" class="form-control" name="txt_des1"
					size="50" id="txt_des1"></td>
			</tr>

			<tr>
				<td>Cantidad:</td>
				<!-- FILA -->
				<td><input type="text" class="form-control" name="txt_can1"
					size="50" id="txt_can1"></td>
			</tr>


			<tr>
				<!-- COLUMNA -->
				<td>Código:</td>
				<!-- FILA -->
				<td><input type="text" class="form-control" name="txt_cod2"
					size="30" id="txt_cod2" oninput="buscar(this)"></td>
			</tr>

			<tr>
				<td>Descripcion:</td>
				<!-- FILA -->
				<td><input type="text" class="form-control" name="txt_des2"
					size="50" id="txt_des2"></td>
			</tr>

			<tr>
				<td>Cantidad:</td>
				<!-- FILA -->
				<td><input type="text" class="form-control" name="txt_can2"
					id="txt_can2" size="50"></td>
			</tr>
		</table>
		<br>
		<div class="row">
			<div class="col-md-4 col-lg-2">
				<button type="submit" class="btn btn-light" id= "btn_enviar">Enviar</button>
			</div>
		</div>
	</form>


	<div id="resultsList"></div><!--MUESTRA EL ARTICULO PROVISORIAMENTE -->

	<script type="text/javascript">
		//RECIBIR PARAMETROS DESDE lista_items.jsp

		$("#txt_cod1").val("${codigo_barra}");
		$("#txt_des1").val("${descripcion}");
		$("#txt_can1").val("${cantidad}");

		console.log("${codigo_barra}");
		console.log("${descripcion}");
		console.log("${cantidad}");

		//RECIBIR PARAMETROS DESDE controller2

		$("#txt_cod2").val("${codigo_barra2}");
		$("#txt_des2").val("${descripcion2}"); //CUANDO SE ENCUENTRE UN ARTICULO

		console.log("${codigo_barra2}");
		console.log("${descripcion2}");
		
	</script>

	<script type="text/javascript">
		//TRAS RECIBIR PARAMETROS DE controller2

		var descripcion2 = document.getElementById('txt_des2').value

		if (descripcion2 == "") {

			console.log("El valor de Descripcion2 " + descripcion2); //Vacio

			$("#txt_des2").val('-');

			txt_cod2.focus(); //Si esta vacio, significa que el codigo buscado no existe. El foco le corresponde a txt_cod2

		} else {

			console.log("El valor de Descripcion2 " + descripcion2); //Cualquier otro

			txt_can2.focus(); //Si no esta vacio, significa que el codigo existe. El foco le corresponde a txt_can2

		}
	</script>

	<script type="text/javascript">
		//FOCO

		function buscar() { //BUSCAR EL ARTICULO TRAS PASAR EL ESCANER Y ENVIAR LA INFORMACION A controller2

			var codigo2 = document.getElementById('txt_cod2').value;

			console.log(codigo2);

			var codigo1 = document.getElementById('txt_cod1').value
			var descripcion1 = document.getElementById('txt_des1').value
			var cantidad1 = document.getElementById('txt_can1').value

			location.href = "controller2?seleccion_articulo=" + codigo2 + ","
					+ codigo1 + "," + descripcion1 + "," + cantidad1;

		}

		/////////////////////////////////////////////////////////////////////////////

		//APLICAR LA BUSQUEDA DEL ARTICULO CUANDO INTRODUZCA UN CODIGO CON EL ESCANER. ENVIAR DATOS A controller2

		// Assume that a loss of focus means the value has finished being entered
		var inputStart, inputStop, firstKey, lastKey, timing, userFinishedEntering;
		var minChars = 3;

		$("#txt_cod2").keypress(function(e) {
			// restart the timer
			if (timing) {
				clearTimeout(timing);
			}

			// handle the key event
			if (e.which == 13) {
				// Enter key was entered

				// don't submit the form
				e.preventDefault();

				// has the user finished entering manually?
				if ($("#txt_cod2").val().length >= minChars) {
					userFinishedEntering = true; // incase the user pressed the enter key
					inputComplete();
				}
			} else {
				// some other key value was entered

				// could be the last character
				inputStop = performance.now();
				lastKey = e.which;

				// don't assume it's finished just yet
				userFinishedEntering = false;

				// is this the first character?
				if (!inputStart) {
					firstKey = e.which;
					inputStart = inputStop;

					// watch for a loss of focus
					$("body").on("blur", "#txt_cod2", inputBlur);
				}

				// start the timer again
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

		// reset the page
		$("#reset").click(function(e) {
			e.preventDefault();
			resetValues();
		});

		function resetValues() {
			// clear the variables
			inputStart = null;
			inputStop = null;
			firstKey = null;
			lastKey = null;
			// clear the results
			inputComplete();
		}

		// Assume that it is from the scanner if it was entered really fast
		function isScannerInput() {
			return (((inputStop - inputStart) / $("#txt_cod2").val().length) < 15);
		}

		// Determine if the user is just typing slowly
		function isUserFinishedEntering() {
			return !isScannerInput() && userFinishedEntering;
		}

		function inputTimeoutHandler() {
			// stop listening for a timer event
			clearTimeout(timing);
			// if the value is being entered manually and hasn't finished being entered
			if (!isUserFinishedEntering() || $("#txt_cod2").val().length < 3) {
				// keep waiting for input
				return;
			} else {
				reportValues();
			}
		}

		// here we decide what to do now that we know a value has been completely entered
		function inputComplete() {
			// stop listening for the input to lose focus
			$("body").off("blur", "#txt_cod2", inputBlur);
			// report the results
			reportValues();
		}

		function reportValues() {
			// update the metrics
			$("#startTime").text(inputStart == null ? "" : inputStart);
			$("#firstKey").text(firstKey == null ? "" : firstKey);
			$("#endTime").text(inputStop == null ? "" : inputStop);
			$("#lastKey").text(lastKey == null ? "" : lastKey);
			$("#totalTime").text(
					inputStart == null ? "" : (inputStop - inputStart)
							+ " milliseconds");
			if (!inputStart) {
				// clear the results
				$("#resultsList").html("");
				$("#txt_cod2").focus().select();
			} else {
				// prepend another result item
				var inputMethod = isScannerInput() ? "Scanner" : "Keyboard";
				$("#resultsList").prepend($("#txt_cod2").val());
				$("#txt_cod2").focus().select();
				inputStart = null;

			}

		}
	</script>
	
	<script type="text/javascript"> //VALIDACIONES
	
		document.getElementById("txt_cod1").readOnly = true;
		document.getElementById("txt_des1").readOnly = true;
		document.getElementById("txt_can1").readOnly = true;
	
		var des = document.getElementById("txt_des2").value;
		
		if (des == "-") { 
			
			btn_enviar.disabled = true;
			
			document.getElementById("txt_can2").readOnly = true
			
		}		
		
	</script>
	
	

</body>
</html>