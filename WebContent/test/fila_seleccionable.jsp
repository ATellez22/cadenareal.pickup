<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<LINK REL=StyleSheet HREF="../css/test.css" TYPE="text/css" MEDIA=screen>

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

<title>Insert title here</title>
</head>
<body>

	<table id="table">
		<tr>
			<td>1 Ferrari F138</td>
			<td>1 000€</td>
			<td>1 200€</td>
			<td>Model monopostu stajne Scuderia Ferrari pre sezónu 2013</td>
			<td>1</td>
			<td>F138</td>
			<td>Klik pre detaily</td>
		</tr>
		<tr>
			<td>2 Ferrari F138</td>
			<td>1 000€</td>
			<td>1 200€</td>
			<td>Model monopostu stajne Scuderia Ferrari pre sezónu 2013</td>
			<td>1</td>
			<td>F138</td>
			<td>Klik pre detaily</td>
		</tr>
		<tr>
			<td>3 Ferrari F138</td>
			<td>1 000€</td>
			<td>1 200€</td>
			<td>Model monopostu stajne Scuderia Ferrari pre sezónu 2013</td>
			<td>1</td>
			<td>F138</td>
			<td>Klik pre detaily</td>
		</tr>
	</table>
	<input type="button" name="OK" class="ok" value="OK" />

	<script>
		$(document).ready(function() {

			//instrucciones jquery

		});
	</script>


	<script type="text/javascript">
		var value = "";

		$("#table tr").click(function() {
			$(this).addClass('selected').siblings().removeClass('selected');
			value = $(this).find('td:first').html();
			value2 = $(this).text(); //PARA TOMAR TODOS LOS VALORES
			
			alert(value);
			location.href = "../controlador?var=" + value;
			
		});

		$('.ok').on('click', function(e) {
			
			alert($("#table tr.selecteds td:first").html());			
			
		});
	</script>


</body>
</html>