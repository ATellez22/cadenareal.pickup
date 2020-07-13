
function buscar() { //BUSCAR EL ARTICULO TRAS ESCRIBIR O HACER COPY-PASTE Y ENVIAR LA INFORMACION A controller2

	var codigo2 = document.getElementById('txt_cod2').value;

	console.log(codigo2);

	var codigo1 = document.getElementById('txt_cod1').value
	var descripcion1 = document.getElementById('txt_des1').value
	var cantidad1 = document.getElementById('txt_can1').value

	location.href = "controller2?seleccion_articulo=" + codigo2 + ","
			+ codigo1 + "," + descripcion1 + "," + cantidad1;
	
}



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	//Si está probando una cadena vacía: 

	if (myVar === '') {// hacer cosas};
	//Si está buscando una variable que ha sido declarada pero no definida:
	}
	if (myVar === null) {// hacer cosas};
	//Si está buscando una variable que puede no estar definida:
	}
	if (myVar === undefined) {// hacer cosas};
	//Si está marcando ambos, es decir, cualquiera de las variables es nula o indefinida:
	}
	if (myVar == null) {// hacer cosas};
	}
	