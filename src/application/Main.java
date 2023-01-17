package application;
	
import View.MainView;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//C'est notre class main qui va demarrer l'application
	        MainView view = new MainView();        
	        Scene scene = view.scene;
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        view.primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
