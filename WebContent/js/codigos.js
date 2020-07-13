$(document).ready(function() {
			$("#txt_cod2").keydown(function(e) {
				alert(String.fromCharCode(e.which));
			});
		});

//OBTENER EL TECLADO OPRIMIDO EN ESE MOMENTO





//	var value = "";

//	$("#table tr").click(function() {
//		$(this).addClass('selected').siblings().removeClass('selected');
//		value = $(this).find('td:first').html();			
	
//		//alert(value);
	
//		location.href = "controller?seleccion_pedido=" + value;
	
//	});

//	$('.ok').on('click', function(e) {
	
//		//alert($("#table tr.selecteds td:first").html());			
	
//	});


//OBTENER EL VALOR DE LA CELDA SELECCIONADA