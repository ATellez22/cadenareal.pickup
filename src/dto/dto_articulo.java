package dto;

public class dto_articulo {
	private String codigo_barra;
	private String descripcion;

	public dto_articulo(String codigo_barra, String descripcion) {
		super();
		this.codigo_barra = codigo_barra;
		this.descripcion = descripcion;
	}

	public dto_articulo() {

	}

	public String getCodigo_barra() {
		return codigo_barra;
	}

	public void setCodigo_barra(String codigo_barra) {
		this.codigo_barra = codigo_barra;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "dto_articulo [codigo_barra=" + codigo_barra + ", descripcion=" + descripcion + "]";
	}

}
