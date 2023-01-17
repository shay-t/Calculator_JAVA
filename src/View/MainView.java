package View;


import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import controller.Controller;

public class MainView {
	//On commence par définir nos components 
	public GridPane root,part1,part2;
	public TextField t1,t2,t3,t4,t5,t6;
	public Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,bplot,bswap;
	public Scene scene;
	public Alert alert,alertConfirmation;
	public Stage primaryStage;
	public MainView() {
		//On instantie chaque composant
		
		//Les layouts
        root=new GridPane();  
        part1=new GridPane();
        part2=new GridPane();
        //La scene
        scene = new Scene(root,200, 400);
        //Le stage
        primaryStage = new Stage();
        
        //Les TextField
		t1=new TextField();
        t2=new TextField();
        t3=new TextField();
        t4=new TextField();
        t5=new TextField();
        t6=new TextField();
        
        //Deux alertes
        alert = new Alert(AlertType.INFORMATION);//Alerte d'information
        alertConfirmation = new Alert(AlertType.CONFIRMATION);//Alerte de confirmation
        
        //Les bouttons
        b1=new Button("0");
        b2=new Button("1");
        b3=new Button("2");
        b4=new Button("3");
        b5=new Button("4");
        b6=new Button("5");
        b7=new Button("6");
        b8=new Button("7");
        b9=new Button("8");
        b10=new Button("9");
        b11=new Button(".");
        b12=new Button("+/-");
        b13=new Button("+");
        b14=new Button("-");
        b15=new Button("*");
        b16=new Button("/");
        b17=new Button("<>");
        b18=new Button("C");
        bswap=new Button("S");
        bplot = new Button("plot");
        
        //On met les text field relatifs à l'affichage inaccessible
        t1.setDisable(true);
        t2.setDisable(true);
        t3.setDisable(true);
        t4.setDisable(true);
        
        //Puis on positionne nos élements
        part1.add(t1, 0, 0);
        part1.add(t2, 0, 1);
        part1.add(t3, 0, 2);
        part1.add(t4, 0, 3);
        part1.add(t5, 0, 4);
        part1.add(t6, 0, 5);
        
        part2.add(b8,0,1);
        part2.add(b9,0,2);
        part2.add(b10,0,3);
        part2.add(b5,1,1);
        part2.add(b6,1,2);
        part2.add(b7,1,3);
        part2.add(b2,2,1);
        part2.add(b3,2,2);
        part2.add(b4,2,3);
        part2.add(b1,3,1);
        part2.add(b11,3,2);
        part2.add(b12,3,3);
        
        part2.add(bplot, 3, 4);
        part2.add(b18, 0, 0);
        part2.add(bswap,1,0);
        
        part2.add(b13,4,0);
        part2.add(b14,4,1);
        part2.add(b15,4,2);
        part2.add(b16,4,3);
        part2.add(b17,4,4);
        
        //On ajoute les informations communes entre les alerte
		alert.setTitle("Erreur");
		alert.setHeaderText(null);
		alertConfirmation.setTitle("Confirmation");
		alertConfirmation.setHeaderText(null);
		alertConfirmation.setContentText("Etes vous sur de vouloir quitter le programme?");
		
		
		//On ajoute un texte indicateur pour la zone dédiée à l'entrée de la fonction
		t6.setPromptText("Entrer la fonction f(x)");
		
		
		//On attache chaque boutton aux Controller qui est notre event Handler
        Controller c1 = new Controller(this);
        b1.addEventHandler(ActionEvent.ACTION, c1);
        b2.addEventHandler(ActionEvent.ACTION, c1);
        b3.addEventHandler(ActionEvent.ACTION, c1);
        b4.addEventHandler(ActionEvent.ACTION, c1);
        b5.addEventHandler(ActionEvent.ACTION, c1);
        b6.addEventHandler(ActionEvent.ACTION, c1);
        b7.addEventHandler(ActionEvent.ACTION, c1);
        b8.addEventHandler(ActionEvent.ACTION, c1);
        b9.addEventHandler(ActionEvent.ACTION, c1);
        b10.addEventHandler(ActionEvent.ACTION, c1);
        b11.addEventHandler(ActionEvent.ACTION, c1);
        b12.addEventHandler(ActionEvent.ACTION, c1);
        b13.addEventHandler(ActionEvent.ACTION, c1);
        b14.addEventHandler(ActionEvent.ACTION, c1);
        b15.addEventHandler(ActionEvent.ACTION, c1);
        b16.addEventHandler(ActionEvent.ACTION, c1);
        b17.addEventHandler(ActionEvent.ACTION, c1);
        b18.addEventHandler(ActionEvent.ACTION, c1);
        bplot.addEventHandler(ActionEvent.ACTION, c1);
        bswap.addEventHandler(ActionEvent.ACTION, c1); 
        t5.addEventHandler(KeyEvent.KEY_PRESSED, c1); 
        
        root.add(part1, 0, 0);
        root.add(part2, 0, 1);
        part1.setVgap(2.5);
        part2.setHgap(10);
        part2.setVgap(10);
        root.setVgap(25);
        
        primaryStage.setScene(scene);
        primaryStage.setMaxWidth(350);
        primaryStage.setMinWidth(350);
        primaryStage.setMaxHeight(600);
        primaryStage.setMinHeight(600);
        
        //On gère l'evenement de fermeture de notre stage
        primaryStage.setOnCloseRequest(event -> event.consume());
        primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, c1);
	}
	
	public void showAlert(Alert a) {
		a.showAndWait();
	}
	
	

}
