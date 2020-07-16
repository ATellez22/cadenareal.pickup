package dto;

public class dto_variables {
	private String consulta; // ALMACENA LA CONSULTA REALIZADA DESDE lista_pedidos_picker.jsp HACIA
	// lista_items.jsp.
	// DE ESA FORMA, AL RETROCEDER DESDE control_items.jsp o ACTUALIZAR EL ITEM, el
	// controller REGENERA LA LISTA

	public dto_variables() {
		super();
	}

	public dto_variables(String consulta) {
		super();
		this.consulta = consulta;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	@Override
	public String toString() {
		return "dto_variables [consulta=" + consulta + "]";
	}


}
