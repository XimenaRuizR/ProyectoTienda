package model;

import java.util.ArrayList;

import exceptions.ClienteException;


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
	
	public Usuario crearUsuario(String nombre2, String apellidos, String correo,String cedula, String usuario, String contrasenia) throws ClienteException {
		Usuario vendedor = new Usuario();
		vendedor.setNombre(nombre2);
		vendedor.setApellido(apellidos);
		vendedor.setCorreo(correo);
		vendedor.setUsuario(usuario);
		vendedor.setDocumento(cedula);
		vendedor.setContrasenia(contrasenia);

		if(verificarCorreo(correo) == true){
			throw new ClienteException("Vendedor ya existe");
		}
		listaUsuarios.add(vendedor);
		return vendedor;
	}
	
	
	
	

}
