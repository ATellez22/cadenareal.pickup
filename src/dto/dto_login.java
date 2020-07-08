package dto;

public class dto_login {
	private String usuario;
	private String password;

	public dto_login(String usuario, String password) {
		super();
		this.usuario = usuario;
		this.password = password;
	}

	public dto_login() {

	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Producto [usuario=" + usuario + ", password=" + password + "]";
	}

}
