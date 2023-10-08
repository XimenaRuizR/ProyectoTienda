package controllers;

import java.util.ArrayList;

import exceptions.ClienteException;
import model.Principal;
import model.Usuario;

public class ModelFactoryController {
		
		Principal principal;


		//------------------------------  Singleton ------------------------------------------------
		// Clase estatica oculta. Tan solo se instanciara el singleton una vez
		private static class SingletonHolder {
			// El constructor de Singleton puede ser llamado desde aqu� al ser protected
			private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
		}

		// Metodo para obtener la instancia de nuestra clase
		
		public static ModelFactoryController getInstance() {
			return SingletonHolder.eINSTANCE;
		}

		public ModelFactoryController() {	
			
			//Siempre se debe verificar si la raiz del recurso es null
			
			if(principal == null)
			{
				iniciarSalvarDatosPrueba();
			}
		}

		private void iniciarSalvarDatosPrueba() {

			inicializarDatos();

		}
		
		//Inicializacion de los datos (quemados)
		
		private void inicializarDatos() {
			principal = new Principal("Prueba");
			
			ArrayList<Usuario> listaUsuarios= new ArrayList<>();
			
			Usuario usuario = new Usuario();
			usuario.setNombre("Daniela");
			usuario.setUsuario("dani");
			usuario.setCorreo("danielalosada202@gmail.com");
			usuario.setContrasenia("111");
			
			Usuario usuario2 = new Usuario();
			usuario2.setNombre("Ximena");
			usuario2.setUsuario("xime");
			usuario2.setCorreo("angieruiz564@gmail.com");
			usuario2.setContrasenia("111");
			
			listaUsuarios.add(usuario);
			listaUsuarios.add(usuario2);
			
			principal.setListaUsuarios(listaUsuarios);
			
		
		}

		public Principal getPrincipal() {
			return principal;
		}

		public void setPrincipal(Principal principal) {
			this.principal = principal;
		}

		public boolean verificarDocumento(String documento, String contrasenia) {
			return principal.verificarDocumento(documento, contrasenia);
		}

		public boolean verificarCorreo(String correo) {
			return principal.verificarCorreo(correo);
		}

		public void actualizarContrasenia(String correo, String nuevaContrasenia) {
			principal.actualizarContrasenia(correo, nuevaContrasenia);
			
		}

		public Usuario crearUsuario(String nombre, String apellido, String documento, String direccion,String usuario2,
				String contrasenia) throws ClienteException {
			return principal.crearUsuario(nombre, apellido, documento, direccion, usuario2, contrasenia);
		}
		
		
		
		
	

}
