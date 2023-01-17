package controller;

import View.MainView;
import View.PlotView;

import java.beans.PropertyChangeEvent;


import Modele.Accumulateur;
import Modele.DivideByZeroException;
import Modele.OperandeInsuffisantException;
import Modele.Pile;
import Modele.PlotData;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.stage.WindowEvent;

/*Notre controlleur va implementer l'interface EventHandler<Event> pour gerer les evenements issus de notre view
 * D'autre part il va implementer l'interface java.beans.PropertyChangeListener pour gerer les changements qui viennent de notre modèle 
 * et précisemment de la pile de l'accumulateur
*/
public class Controller implements EventHandler<Event>, java.beans.PropertyChangeListener {
	
	public MainView view; //Notre Main view c'est a dire celle de la calculette
	public Accumulateur accumulateur;//Notre accumulateur
	public PlotData pldata;//Notre view relatif aux graphes des fonctions
	
	public Controller(MainView vi){
		//On récupère notre vie puis on instancie notre accumulateur
		view = vi;
		accumulateur = new Accumulateur(this);
	}
	
	
	public void pushData(String data) {
		//On commence par gérer le cas ou l'utilisateur rentre une valeur non numerique
		try {
			double operandeActuelle = Double.parseDouble(data);
			accumulateur.push(operandeActuelle);
			view.t5.clear();	
		}catch(Exception ex) {//En cas d'erreur on fait afficher une alerte d'erreur
			view.alert.setTitle("Erreur");
			view.alert.setHeaderText(null);
			view.alert.setContentText("Veuillez saisir une valeur numérique");
			view.showAlert(view.alert);
		}
	}
	 
	@Override
	public void handle(Event e) {
		//Comme On a plusieurs type d'event a gerer ,
		//on commence par distincter chaque type à part 
		if(e.getEventType() == ActionEvent.ACTION) {
			//Dans le cas ou l'evenement vient d'un boutton
			Button nv = (Button)e.getSource();
			if("0123456789.".contains(nv.getText())) {
				/*
				 * On vérifie si le boutton en question est un boutton de saisie
				 * est donc si son text est compris dans la chaine "0123456789."
				 */
				String old = view.t5.getText();
				view.t5.setText(old + nv.getText());//On concatène l'ancienne valeur du text field avec la nouvelle valeur écrite
				
			}else if (e.getSource() == view.b17) {//Push Button
				//si la zone est vide on demande à l'utilisateur de rentrer une valeur
				if(!view.t5.getText().isEmpty()) {
					pushData(view.t5.getText());			
				}else {
					view.alert.setTitle("Erreur");
					view.alert.setHeaderText(null);
					view.alert.setContentText("Veuillez saisir une opérande");
					view.showAlert(view.alert);
				}
	
			}
			/*
			 * Dans tout ce qui va suivre on va vérifier si la valeur de la zone 
			 * n'est pas vide pour pusher directement la valeur
			 * Et donc l'utilisateur peut directement effectuer ses opération sans faire 
			 * deux pushs
			 */
			else if ((e.getSource() == view.b12)) {
				if(!view.t5.getText().isEmpty()) {
					pushData(view.t5.getText());
				}
				accumulateur.neg();
			}
			/*
			 * Pour les methodes qui peuvent génerer des erreurs,
			 * On va les entrourer par un bloc try and catch qui va 
			 * afficher une alerte contenant le message d'erreur
			 */
			else if (e.getSource() == view.b13) {
				if(!view.t5.getText().isEmpty()) {
					pushData(view.t5.getText());
				}
				try {
					accumulateur.add();
				} catch (OperandeInsuffisantException e1) {
					// TODO Auto-generated catch block
					view.alert.setContentText(e1.getMessage());
					view.showAlert(view.alert);
				}
				
			}else if (e.getSource() == view.b14) {
				if(!view.t5.getText().isEmpty()) {
					pushData(view.t5.getText());
				}
				try {
					accumulateur.sub();
				} catch (OperandeInsuffisantException e1) {
					view.alert.setContentText(e1.getMessage());
					view.showAlert(view.alert);
				}
			}else if (e.getSource() == view.b15) {
				if(!view.t5.getText().isEmpty()) {
					pushData(view.t5.getText());
				}
				try {
					accumulateur.mul();
				} catch (OperandeInsuffisantException e1) {
					// TODO Auto-generated catch block
					view.alert.setContentText(e1.getMessage());
					view.showAlert(view.alert);
				}
			}else if (e.getSource() == view.b16) {
				if(!view.t5.getText().isEmpty()) {
					pushData(view.t5.getText());
				}
				try {
					accumulateur.div();
				} catch (DivideByZeroException | OperandeInsuffisantException e1) {
					view.alert.setContentText(e1.getMessage());
					view.showAlert(view.alert);
				}
			}else if (e.getSource() == view.bplot) {
				String fns = view.t6.getText().strip();//on récupère la fonction en éliminant les espaces
				pldata = new PlotData(fns);//On crée une instance du modèle en lui passant l'expression de la fonction
				pldata.generateData();//On génère les données
				PlotView pl = new PlotView(pldata.getSeries());//On instancie notre view à partie de la série du modèle
				pl.secondaryStage.show();//On affiche notre graphique
			}else if(e.getSource() == view.b18) {
				accumulateur.clear();
			}else if(e.getSource() == view.bswap) {
				try {
					accumulateur.swap();
				} catch (OperandeInsuffisantException e1) {
					// TODO Auto-generated catch block
					view.alert.setContentText(e1.getMessage());
					view.showAlert(view.alert);
				}
			}
		}else if (e.getEventType() == WindowEvent.WINDOW_CLOSE_REQUEST) {
			/*
			 * On affiche une alerte de confirmation 
			 * pour s'assurer que l'utilisateur veut vraiment quitter l'application
			 */
			if(view.alertConfirmation.showAndWait().get() == ButtonType.OK) {
				view.primaryStage.close();
			}
			
		}
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {//Gestion des modifications de la pile 
		// TODO Auto-generated method stub
		
		Pile pa = (Pile)evt.getNewValue();//On prend la nouvelle instance de la pile
		
		//Selon la taille de la pile , on va faire remplire les zones d'affichage
		if(pa.size()>=4) {
			view.t1.setText(pa.get(pa.size()-4).toString());
		}else {
			view.t1.clear();
		}
		if(pa.size()>=3) {
			view.t2.setText(pa.get(pa.size()-3).toString());
		}else {
			view.t2.clear();
		}
		if(pa.size()>=2) {
			view.t3.setText(pa.get(pa.size()-2).toString());
		}else {
			view.t3.clear();
		}
		if(pa.size()>=1) {
			view.t4.setText(pa.get(pa.size()-1).toString());
		}else {
			view.t4.clear();
		}
					
		

		
	}



}
