package aplication;

import java.io.IOException;

import controllers.LoginVendedorController;
import controllers.RestablecerContrasena2Controller;
import controllers.RestablecerContrasenaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Principal;

public class Aplicacion extends Application{

	private Stage primaryStage;

	Principal p = new Principal ("Prueba");
	



	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Prueba");

		mostrarVentanaLoginV();

	}


	
	//Procedimiento que muestra la ventana del login del recepcionista 
	
	public void mostrarVentanaLoginV() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("../views/LoginVendedor.fxml"));

			AnchorPane rootLayout = (AnchorPane)loader.load();

			LoginVendedorController loginController = loader.getController();
			loginController.setAplicacion(this);

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mostrarVentanaRestablecerContrasena() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("../views/RestablecerContrasena.fxml"));

			AnchorPane rootLayout = (AnchorPane)loader.load();

			RestablecerContrasenaController restablecerController = loader.getController();
			restablecerController.setAplicacion(this);

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void mostrarVentanaRestablecerContrasena2(String documento, String codigo) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("../views/RestablecerContrasena2.fxml"));

			AnchorPane rootLayout = (AnchorPane)loader.load();

			RestablecerContrasena2Controller restablecer2Controller = loader.getController();
			restablecer2Controller.setAplicacion(this,documento, codigo);

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}