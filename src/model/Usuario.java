package model;

public class Usuario {
	
	private String nombre;  
	private String correo; 
	private String usuario; 
	private String contrasenia;
	
	public Usuario(String nombre, String correo, String usuario, String contrasenia) {
		super();
		this.nombre = nombre;
		this.correo = correo;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
	} 
	
	public Usuario(){
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public boolean verificarDocumento(String documento, String contrasenia2) {
			if(getUsuario().equals(documento) && getContrasenia().equals(contrasenia2)){
				return true;
			}
			return false;
		}
	
	
	
	
	
	

}
