module CalculettePolonnaise {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	requires java.desktop;
	requires MathParser.org.mXparser;
	
	opens application to javafx.graphics, javafx.fxml;
}
