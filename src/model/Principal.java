package model;

import java.util.ArrayList;

public class Principal {
	private String nombre; 
	private ArrayList<Usuario> listaUsuarios;
	
	public Principal(String nombre) {
		super();
		this.nombre = nombre;
		this.listaUsuarios = new ArrayList<Usuario>();
	}
	
	public Principal(){
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public boolean verificarDocumento(String documento, String contrasenia) {
		for (Usuario vendedor : listaUsuarios) {
			if(vendedor.verificarDocumento(documento,contrasenia)){
				return true;
			}
		}
		return false;
	}

	public boolean verificarCorreo(String correo) {
		for (Usuario vendedor : listaUsuarios) {
			if(vendedor.getCorreo().equals(correo)){
				return true;
			}
		}
		return false;
	}

	public void actualizarContrasenia(String correo, String nuevaContrasenia) {
		for (Usuario vendedor : listaUsuarios) {
			if(vendedor.getCorreo().equals(correo)){
				vendedor.setContrasenia(nuevaContrasenia);
			}
		}
		
	}
	
	
	
	

}
