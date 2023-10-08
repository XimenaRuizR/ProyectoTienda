package controllers;

import java.net.URL;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import aplication.Aplicacion;
import exceptions.ClienteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Usuario;


public class NuevoVendedorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtDocumento;
    
    @FXML
    private TextField txtCedula;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private TextField txtContrasenia;

    private Aplicacion aplicacion;

    @FXML
    private ImageView flechaRegresar;
    
    ModelFactoryController modelFactoryController; 

    @FXML
    void registrarseEvent(ActionEvent event) {
    	registrarseAction();
    }
    
    @FXML
    void mouseEvent(MouseEvent event) {
    	btnRegistrarse.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent ->{
    		btnRegistrarse.setStyle("-fx-background-color: #999999;");
        	});
    }

    @FXML
    void mouseEvent2(MouseEvent event) {
    	
    	btnRegistrarse.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent ->{
    		btnRegistrarse.setStyle("-fx-background-color #0676b7;");
    	});
    }

    private void registrarseAction() {
    	String nombre = txtNombre.getText();
    	String apellido = txtApellido.getText();
    	String correo = txtDireccion.getText();
    	String direccion = txtDocumento.getText();
    	String contrasenia= txtContrasenia.getText();
    	String usuario2 = txtCedula.getText();
    	
    	if(datosValidos(nombre, apellido, correo, direccion, contrasenia, usuario2) == true){
    		if(verificarUsuario(usuario2)){
    			if(verificarContraseña(contrasenia)){
        			Usuario usuario = null;
            		try {
        				usuario=modelFactoryController.crearUsuario(nombre, apellido, correo, direccion, usuario2, contrasenia);
        				mostrarMensaje("Notificaciï¿½n registro", "Vendedor registrado", "Se ha registrado con exito en Shopify", AlertType.INFORMATION);
        				enviarCorreo(correo);
        			 	aplicacion.mostrarVentanaLoginV();
        			} catch (ClienteException e) {

        				mostrarMensaje("Notificaciï¿½n registro", "Vendedor no registrado", "Ya se ha registrado un vendedor con nï¿½ identificacion", AlertType.ERROR);
        			}
            	}else{
            		mostrarMensaje("Notificaciï¿½n contrasenia", "contrasenia invalida", "La contraseña debe tener una mayuscula, un número, una minuscula y un caracter. No sobrepasarse de 8 letras", AlertType.ERROR);
            	}
    		}else{
    			mostrarMensaje("Notificaciï¿½n usuario", "usuario invalido", "El nombre de usuario solo puede contener letras", AlertType.ERROR);
		}
        		}else{
        			mostrarMensaje("Notificaciï¿½n registro", "Informacion invalida", "Informacion invalida", AlertType.ERROR);
    		}
    }

    		
    	
		
	private boolean verificarUsuario(String usuario) {
		// Define una expresión regular para verificar el nombre de usuario
        String regex = "^[a-zA-Z]+$";

        // Compila la expresión regular en un patrón
        Pattern pattern = Pattern.compile(regex);

        // Crea un matcher para el nombre de usuario
        Matcher matcher = pattern.matcher(usuario);

        // Comprueba si el nombre de usuario cumple con la expresión regular
        return matcher.matches();
	}
	
	private void enviarCorreo(String destinatario) {
		
		 Properties properties = new Properties();
	        properties.put("mail.smtp.host", "smtp.gmail.com"); // Cambia esto al servidor SMTP que desees utilizar
	        properties.put("mail.smtp.port", "587"); // Cambia esto al puerto SMTP adecuado
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true"); // Habilita STARTTLS para la seguridad

       Session session = Session.getInstance(properties, new Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication("angieruiz564@gmail.com", "drad zlvr tdwx scuh");
           }
       });

       try {
           // Crear un objeto de mensaje
           Message mensaje = new MimeMessage(session);

           // Configurar el remitente y los destinatarios
           mensaje.setFrom(new InternetAddress(destinatario));
           mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
           mensaje.setSubject("Registro online");
           mensaje.setText("Usted se ha registrado con éxito en la tienda!");

           // Enviar el mensaje
           Transport.send(mensaje);

           System.out.println("Correo electrónico enviado con éxito.");
       } catch (MessagingException e) {
           e.printStackTrace();
           System.err.println("Error al enviar el correo electrónico: " + e.getMessage());
       }
   }


	private boolean datosValidos(String nombre, String apellido, String documento, String direccion,
			String contrasenia, String usuario2) {
		if(nombre.equals("")){
			return false;
		}
		if(apellido.equals("")){
			return false;
		}
		if(documento.equals("")){
			return false;
		}
		if(direccion.equals("")){
			return false;
		}
		if(contrasenia.equals("")){
			return false;
		}
		if(usuario2.equals("")){
			return false;
		}
		
		return true;
		
	}
	
	public static boolean verificarContraseña(String contraseña) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!.,?¡¿-_]).{8}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(contraseña);

        return matcher.matches();
    }

	@FXML
    void flechaRegresarEvent(MouseEvent event) {
    	flechaRegresar.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
    		aplicacion.mostrarVentanaIniciarRed();
    	});


    }

    @FXML
    void initialize() {

    	modelFactoryController = ModelFactoryController.getInstance();
    }

    public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;

	}
    
    private boolean mostrarMensajeConfirmacion(String mensaje) {

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmaciï¿½n");
		alert.setHeaderText(null);
		alert.setContentText(mensaje);

		Optional<ButtonType> action = alert.showAndWait();

		if(action.get() == ButtonType.OK){
			return true;
		}else{
			return false;
		}
	}

	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

    	Alert alert = new Alert(alertType);
    	alert.setTitle(titulo);
    	alert.setHeaderText(header);
    	alert.setContentText(contenido);
    	alert.showAndWait();
	}
}