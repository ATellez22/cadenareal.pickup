package dto;

public class dto_pedido_detalle2 {

	private long codigo_barra;
	private String num_pedido, descripcion;
	private double cantidad;
	private String obs;
	private String seccion;

	public dto_pedido_detalle2() {

	}

	public dto_pedido_detalle2(long codigo_barra, String num_pedido, String descripcion, double cantidad, String obs,
			String seccion) {
		super();
		this.codigo_barra = codigo_barra;
		this.num_pedido = num_pedido;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.obs = obs;
		this.seccion = seccion;
	}

	public long getCodigo_barra() {
		return codigo_barra;
	}

	public void setCodigo_barra(long codigo_barra) {
		this.codigo_barra = codigo_barra;
	}

	public String getNum_solicitud() {
		return num_pedido;
	}

	public void setNum_solicitud(String num_solicitud) {
		this.num_pedido = num_solicitud;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public String getNum_pedido() {
		return num_pedido;
	}

	public void setNum_pedido(String num_pedido) {
		this.num_pedido = num_pedido;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	@Override
	public String toString() {
		return "dto_pedido_detalle [codigo_barra=" + codigo_barra + ", num_pedido=" + num_pedido + ", descripcion="
				+ descripcion + ", cantidad=" + cantidad + ", obs=" + obs + ", seccion=" + seccion + "]";
	}

}
