package dto;

public class dto_pedido {

	int codigo;
	String num_pedido, supervisor, picker, fecha, sucursal, estado;

	public dto_pedido() {
		// TODO Auto-generated constructor stub
	}
	
	

	public dto_pedido(int codigo, String num_pedido, String supervisor, String picker, String fecha, String sucursal,
			String estado) {
		super();
		this.codigo = codigo;
		this.num_pedido = num_pedido;
		this.supervisor = supervisor;
		this.picker = picker;
		this.fecha = fecha;
		this.sucursal = sucursal;
		this.estado = estado;
	}



	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNum_pedido() {
		return num_pedido;
	}

	public void setNum_pedido(String num_pedido) {
		this.num_pedido = num_pedido;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getEncargado() {
		return picker;
	}

	public void setEncargado(String encargado) {
		this.picker = encargado;
	}

	public String getPicker() {
		return picker;
	}

	public void setPicker(String picker) {
		this.picker = picker;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "dto_pedido [codigo=" + codigo + ", num_pedido=" + num_pedido + ", supervisor=" + supervisor
				+ ", picker=" + picker + ", fecha=" + fecha + ", sucursal=" + sucursal + ", estado=" + estado + "]";
	}

}
