package com.github.wiruwaru.scansort.test;

import com.github.wiruwaru.scansort.barcode.AztecCreator;
import com.github.wiruwaru.scansort.barcode.util.BitMatrixPainter;

import javafx.application.Application;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class TestApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		String toEncode = "M://Users/Hello/World/Test/How/Long/Can/This/Be/Maybe/Even/Longer/Or/Not/";
		
		AztecCreator ac = new AztecCreator();
		System.out.println(ac.getAztec(toEncode).toString());
		
		Group root = new Group();
		Scene s = new Scene(root, 300, 300);

		final Canvas canvas = new Canvas(250,250);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		BitMatrixPainter bmt = new BitMatrixPainter(ac.getAztec(toEncode), gc, 250);
		bmt.paint();
		
		PrinterJob job = PrinterJob.createPrinterJob();
		
		
		root.getChildren().add(canvas);
		primaryStage.setScene(s);
		primaryStage.show();
		if (job.printPage(canvas)) {job.endJob();}
		
	}
	
	public static void main (String[] args) {
		launch(args);
	}

}
