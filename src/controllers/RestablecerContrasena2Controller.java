package controllers;


import java.net.URL;
import java.util.ResourceBundle;

import aplication.Aplicacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;


public class RestablecerContrasena2Controller {
	  @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Button btnRestablecer;

	    @FXML
	    private PasswordField txtContraseniaAgain;

	    @FXML
	    private Label ingresa;

	    @FXML
	    private TextField txtCodigoIngresado;

	    @FXML
	    private PasswordField txtNuevaContrasenia;

	    @FXML
	    private Button btnVerificar;
	    
	    ModelFactoryController modelFactoryController; 

	    @FXML
	    void verificarCodigoEvent(ActionEvent event) {
	    	verificarCodigo();
	    }

	    @FXML
	    void restablecerContrasenaFinalEvent(ActionEvent event) {
	    	verificarCodigo();

	    }

	    @FXML
	    void ingresaEvent(MouseEvent event) {
	    	ingresa.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
	    	});
	    }


	private Aplicacion aplicacion;

	private String documento;
	
	private String codigo;

	@FXML
    void initialize() {
		modelFactoryController = ModelFactoryController.getInstance();
    }

	public void setAplicacion(Aplicacion aplicacion, String documento, String codigo) {
		this.aplicacion = aplicacion;
		this.documento = documento;
		this.codigo= codigo; 
	}

	private void verificarCodigo(){
		String codigoIngresado = txtCodigoIngresado.getText();

    		if(datosValidos(codigoIngresado) == true){
        		if(codigo.equals(codigoIngresado)){
        			mostrarMensaje("Notificaci�n codigo", "Codigo correcto", "El codigo fue ingresado correctamente, restablezca su contrase�a", AlertType.INFORMATION);
        			actualizarContrasenia();
        		}else{
        			mostrarMensaje("Notificaci�n codigo", "Codigo incorrecto", "El codigo ingresado es incorrecto, vuelva a escribirlo", AlertType.ERROR);
        		}
        	}else{
        		mostrarMensaje("Notificaci�n codigo", "Datos Incompletos", "Debe ingresar el codigo para poder restablecer su contrase�a, despues de 3 intentos se bloqueara el usuario", AlertType.WARNING);
        	}
    }


	private void actualizarContrasenia(){
		String nuevaContrasenia = txtNuevaContrasenia.getText();
		String contraseniaAgain = txtContraseniaAgain.getText();
		String correo = documento;

			if(datosValidosU(nuevaContrasenia, contraseniaAgain)){
				if(nuevaContrasenia.equals(contraseniaAgain)){
					modelFactoryController.actualizarContrasenia(correo, nuevaContrasenia);
					mostrarMensaje("Notificaci�n contrase�a", "Contrase�a correcta", "Su contrase�a fue bien escrita, se ha actualizado correctamente", AlertType.INFORMATION);
				}else{
					mostrarMensaje("Notificaci�n contrase�a", "Contrase�a incorrecto", "Las contrase�as no son iguales, vuelva a escribirlas", AlertType.ERROR);
				}
			}else{
				mostrarMensaje("Notificaci�n contrase�a", "Datos Incompletos", "Debe ingresar la nueva contrase�a para poder restablecerla, despues de 3 intentos se bloqueara el usuario", AlertType.WARNING);
			}
	}


	private boolean datosValidosU(String nuevaContrasenia, String contraseniaAgain) {
		if(nuevaContrasenia.equals("") || contraseniaAgain.equals("") ){
			return false;
		}
		return true;
	}

	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

		Alert alert = new Alert(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}

	private boolean datosValidos(String codigoIngresado) {
		if(codigoIngresado.equals("")){
			return false;
		}
		return true;
	}













}
