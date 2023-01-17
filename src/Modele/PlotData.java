package Modele;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

import javafx.scene.chart.XYChart;

public class PlotData {
	
	private Function function;//La fonction qu'on doit traiter
	private XYChart.Series series;//Les s�ries qu'on va afficher a notre graphe
	
	public PlotData(String fns) {
		//On instantie une nouvelle fonction � partire d'une expression string
		this.function = new Function("fn(x) = "+fns);
		this.series =new XYChart.Series();
	}

	public void setFunction(String fns) {
		this.function = new Function("fn(x) = "+fns);
	}
	//Un getter de s�rie pour pouvoir l'afficher au view
	public XYChart.Series getSeries() {
		return series;
	}
	
	/*
	 *On cr�e une m�thode generateData() qui va it�rer entre -100 et 100
	 *cette m�thode va changer l'expression de calcul pour chaque point
	 *puis v�rifie si le point repr�sente un point de discontinuit�, c'est � dire 
	 *hors le domaine de d�finition, si c'est le cas , on passe au point suivant.
	 *Au fur et mesure que le calcule se fait, on fait remplire notre s�rie    
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