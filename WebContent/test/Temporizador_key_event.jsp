<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

<title>Insert title here</title>
</head>
<body>

	<input id="in" type="text" />
	<div id="out"></div>

	<script type="text/javascript">
		//setup before functions
		var typingTimer; //timer identifier
		var doneTypingInterval = 1000; //time in ms, 5 second for example

		//on keyup, start the countdown
		$('#in').keyup(function() {
			clearTimeout(typingTimer);
			if ($('#in').val) {
				typingTimer = setTimeout(function() {
					//do stuff here e.g ajax call etc....
					var v = $("#in").val();
					$("#out").html(v);
				}, doneTypingInterval);
			}
		});
	</script>


</body>
</html>