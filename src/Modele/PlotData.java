package Modele;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

import javafx.scene.chart.XYChart;

public class PlotData {
	
	private Function function;//La fonction qu'on doit traiter
	private XYChart.Series series;//Les séries qu'on va afficher a notre graphe
	
	public PlotData(String fns) {
		//On instantie une nouvelle fonction à partire d'une expression string
		this.function = new Function("fn(x) = "+fns);
		this.series =new XYChart.Series();
	}

	public void setFunction(String fns) {
		this.function = new Function("fn(x) = "+fns);
	}
	//Un getter de série pour pouvoir l'afficher au view
	public XYChart.Series getSeries() {
		return series;
	}
	
	/*
	 *On crée une méthode generateData() qui va itérer entre -100 et 100
	 *cette méthode va changer l'expression de calcul pour chaque point
	 *puis vérifie si le point représente un point de discontinuité, c'est à dire 
	 *hors le domaine de définition, si c'est le cas , on passe au point suivant.
	 *Au fur et mesure que le calcule se fait, on fait remplire notre série    
	 */
	public void generateData() {
		for(int i=-100 ; i<=100; i++) {
        	String actualExpression = String.format("fn(%s)", i);
        	Expression e1=new Expression(actualExpression,function);
        	if(Double.isNaN(e1.calculate()) == false && Double.isInfinite(e1.calculate())==false) {
        		series.getData().add(new XYChart.Data(i, e1.calculate()));
        		}
		}
	}
}