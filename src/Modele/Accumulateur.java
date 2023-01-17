package Modele;


import java.beans.PropertyChangeSupport;

import controller.Controller;

public class Accumulateur {
	
	private double tempMemory; // une sorte de memoire temporaire 
	private Pile p; // La pile 
	Controller c1; // Le controlleur qu'on va passer en argument du listener de notre java bean 
	
	PropertyChangeSupport changeSupport;
	
	public Accumulateur(Controller co) {
		super();
		this.tempMemory = 0;
		this.p = new Pile();
		this.c1=co;
		changeSupport = new PropertyChangeSupport(this);//On crée instance de PropertyChangeSupport
		changeSupport.addPropertyChangeListener(c1);//puis on lui attache le controleur comme listener
	}
	
	//On definit nos getters
	public double getTempMemory() {
		return tempMemory;
	}




	public Pile getP() {
		return p;
	}



	//On définit un setter pour la memoire temporaire
	public void setTempMemory(double tempMemory) {
		this.tempMemory = tempMemory;
	}


	
	//Maintenant on fait appel aux méthodes existantes de la pile
	
	public void push(double d) {
		Pile oldPile = (Pile) p.clone();
		p.push(d);
		changeSupport.firePropertyChange("valeur",oldPile,p);//La méthode firePropertyChange() permet d'informer tous les composants enregistrés du changement de la valeur de la propriété.
	}
	
	public void pop() {
		p.pop();
	}
	
	public void clear() {
		//De meme si on vide la pile on informe le controlleur
		Pile oldPile = (Pile) p.clone();
		tempMemory=0;
		p.clear();
		changeSupport.firePropertyChange("ClearValues",oldPile,p);

	}
	  
	/*
	 * Puisque la suite des méthodes utilisent la méthode push de l'accumulateur,
	 * on a pas besoin de leurs rattacher une une Property Change
	 */
	
	/*
	 *Ces méthodes sont déclarés de facon à faire appel a des erreurs selon chaque cas:
	 *On a implémenter deux types d'erreurs, une pour la division par 0
	 *l'autre pour la gestion des nombre d'opérandes nécessaire pour une méthode 
	 */
	public void swap() throws OperandeInsuffisantException{
		if(p.size()>=2) {
			double temp1 = p.pop();
			double temp2 = p.pop();
			this.push(temp1);
			this.push(temp2);
			
		}else {
			throw new OperandeInsuffisantException();
		}
		
	}
	
	public void add() throws OperandeInsuffisantException{
		if(p.size()>=2) {
			double op1 = p.pop();
			double op2=p.pop();
			tempMemory = op2 + op1;
			this.push(tempMemory);			
		}else {
			throw new OperandeInsuffisantException();
		}

	}
	
	public void sub() throws OperandeInsuffisantException{
		if(p.size()>=2) {
			double op1 = p.pop();
			double op2=p.pop();
			tempMemory = op2 - op1;
			this.push(tempMemory);
		}else {
			throw new OperandeInsuffisantException();
		}

	}
	
	public void div() throws DivideByZeroException,OperandeInsuffisantException{
		if(p.size()>=2) {
			double op1 = p.pop();
			double op2=p.pop();
			if(op1 == 0) {
				throw new DivideByZeroException();
			}else {
				tempMemory = op2/op1;
				this.push(tempMemory);
			}			
		}else {
			throw new OperandeInsuffisantException();
		}

	}
	
	public void mul() throws OperandeInsuffisantException{
		if(p.size()>=2) {
			double op1 = p.pop();
			double op2 = p.pop();
			tempMemory = op1*op2;
			this.push(tempMemory);			
		}else {
			throw new OperandeInsuffisantException();
		}
	}
	
	public void neg() {
		double op1 = p.pop();
		tempMemory = -op1;
		this.push(tempMemory);
	}
}
