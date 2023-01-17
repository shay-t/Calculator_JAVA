package View;

import java.util.ArrayList;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class PlotView {
	public GridPane root;
	public Scene scene;
	public Stage secondaryStage;
	public PlotView(XYChart.Series series) {

		secondaryStage = new Stage();
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        lineChart.setCreateSymbols(false);
        lineChart.getData().add(series);

        scene  = new Scene(lineChart,800,600);
        secondaryStage.setScene(scene);
        secondaryStage.setMaxWidth(800);
        secondaryStage.setMinWidth(800);
        secondaryStage.setMaxHeight(600);
        secondaryStage.setMinHeight(600);
        
	}
	

	
}
