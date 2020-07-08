<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link REL=StyleSheet HREF="css/estilo.css" TYPE="text/css"
	MEDIA=screen>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

<title>Insert title here</title>
</head>
<body>

	<form>
		<input id="scanInput" />
		<button id="reset">Reset</button>
	</form>
	<br />
	<div>
		<h2>Event Information</h2>
		Start: <span id="startTime"></span> <br />First Key: <span
			id="firstKey"></span> <br />Last Ley: <span id="lastKey"></span> <br />End:
		<span id="endTime"></span> <br />Elapsed: <span id="totalTime"></span>

	</div>
	<div>
		<h2>Results</h2>

		<div id="resultsList"></div>
	</div>

	<script type="text/javascript">
		var inputStart, inputStop, firstKey, lastKey, timing, userFinishedEntering;
		var minChars = 3;

		// handle a key value being entered by either keyboard or scanner
		$("#scanInput").keypress(function(e) {
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
				if ($("#scanInput").val().length >= minChars) {
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
					$("body").on("blur", "#scanInput", inputBlur);
				}

				// start the timer again
				timing = setTimeout(inputTimeoutHandler, 500);
			}
		});

		// Assume that a loss of focus means the value has finished being entered
		function inputBlur() {
			clearTimeout(timing);
			if ($("#scanInput").val().length >= minChars) {
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
			return (((inputStop - inputStart) / $("#scanInput").val().length) < 15);
		}

		// Determine if the user is just typing slowly
		function isUserFinishedEntering() {
			return !isScannerInput() && userFinishedEntering;
		}

		function inputTimeoutHandler() {
			// stop listening for a timer event
			clearTimeout(timing);
			// if the value is being entered manually and hasn't finished being entered
			if (!isUserFinishedEntering() || $("#scanInput").val().length < 3) {
				// keep waiting for input
				return;
			} else {
				reportValues();
			}
		}

		// here we decide what to do now that we know a value has been completely entered
		function inputComplete() {
			// stop listening for the input to lose focus
			$("body").off("blur", "#scanInput", inputBlur);
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
				$("#scanInput").focus().select();
			} else {
				// prepend another result item
				var inputMethod = isScannerInput() ? "Scanner" : "Keyboard";
				$("#resultsList").prepend(
						"<div class='resultItem " + inputMethod + "'>"
								+ "<span>Value: "
								+ $("#scanInput").val()
								+ "<br/>"
								+ "<span>ms/char: "
								+ ((inputStop - inputStart) / $("#scanInput")
										.val().length) + "</span></br>"
								+ "<span>InputMethod: <strong>" + inputMethod
								+ "</strong></span></br>"
								+ "</span></div></br>");
				$("#scanInput").focus().select();
				inputStart = null;
			}
		}

		$("#scanInput").focus();
	</script>

</body>
</html>