package controllers;


import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import aplication.Aplicacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class RestablecerContrasenaController {

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnRestablecer;

    @FXML
    private Label regresar;

    @FXML
    public TextField txtDocumento;
    
    ModelFactoryController modelFactoryController; 
    
    String txtCodigoVerificacion= "";

    @FXML
    void restablecerContrasenaEvent(ActionEvent event) {
    	restablecerAction();


    }

    private void restablecerAction() {

    	String correo = "";

    	correo = txtDocumento.getText();

    	if(datosValidos(correo)){

    		boolean documentoValido = modelFactoryController.verificarCorreo(correo);
    		if(documentoValido){
    			enviarCorreo(correo);
    			aplicacion.mostrarVentanaRestablecerContrasena2(correo, txtCodigoVerificacion);
    		}else{
    			mostrarMensaje("Notificaci�n Inicio sesion", "Usuario no existe", "Los datos ingresados no corresponde a un usuario valido", AlertType.INFORMATION);

    		}
    	}else{
			mostrarMensaje("Notificaci�n Inicio sesion", "Datos Incompletos", "Debe ingresar los datos correctamente, despues de 3 intentos se bloqueara el usuario", AlertType.WARNING);

    	}

	}
    
	private String cadenaAleatoria() {
        String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String cadenaAux= "";
        for (int x = 0; x < 8; x++) {
            int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
            char caracterAleatorio = banco.charAt(indiceAleatorio);
            cadenaAux+=caracterAleatorio;
            txtCodigoVerificacion= cadenaAux;
        }
        return txtCodigoVerificacion;

    }
	
	private int numeroAleatorioEnRango(int minimo, int maximo) {
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }


    public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

  		Alert alert = new Alert(alertType);
  		alert.setTitle(titulo);
  		alert.setHeaderText(header);
  		alert.setContentText(contenido);
  		alert.showAndWait();
  	}

  	private boolean datosValidos(String usuario) {
  		if(usuario.equals("")){
  			return false;
  		}
  		return true;
  	}


    @FXML
    void regresarEvent(MouseEvent event) {
    	regresar.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
    	});
    }


	private Aplicacion aplicacion;

	@FXML
    void initialize() {
		modelFactoryController = ModelFactoryController.getInstance();

    }

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
		txtCodigoVerificacion= cadenaAleatoria();
		

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
            mensaje.setSubject("Reestablecer contrase�a");
            mensaje.setText("El c�digo para reestablecer su contrase�a es el siguiente: "+txtCodigoVerificacion);

            // Enviar el mensaje
            Transport.send(mensaje);

            System.out.println("Correo electr�nico enviado con �xito.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error al enviar el correo electr�nico: " + e.getMessage());
        }
    }

}
