package com.github.wiruwaru.scansort.test;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import com.github.wiruwaru.scansort.barcode.input.util.AztecScanner;
import com.github.wiruwaru.scansort.barcode.output.util.AztecCreator;
import com.github.wiruwaru.scansort.barcode.output.util.BitMatrixPainter;
import com.github.wiruwaru.scansort.barcode.output.util.BitMatrixToPdf;

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
		
		//PrinterJob job = PrinterJob.createPrinterJob();
		
		BitMatrixToPdf bmp = new BitMatrixToPdf();
		bmp.paint(ac.getAztec(toEncode), 6);
		//bmp.addDescription("Test description stuff", 6);
		bmp.export("C:/Users/matth/Desktop/testAztec.pdf");
		
		AztecScanner as = new AztecScanner("C:/Users/matth/Desktop/testAztec.pdf");
		root.getChildren().add(canvas);
		primaryStage.setScene(s);
		primaryStage.show();
		//if (job.printPage(canvas)) {job.endJob();}
		
	}
	
	public static void main (String[] args) {
		//launch(args);
		String toEncode = "M://Users/Hello/World/Test/How/Long/Can/This/Be/Maybe/Even/Longer/Or/Not/";
		
		AztecCreator ac = new AztecCreator();
		System.out.println(ac.getAztec(toEncode).toString());
		
		//PrinterJob job = PrinterJob.createPrinterJob();
		
		BitMatrixToPdf bmp = new BitMatrixToPdf();
		bmp.paint(ac.getAztec(toEncode), 6);
		bmp.paint(ac.getAztec("Another test string that does nothing"), 3);
		bmp.addDescription("Test description stuff", 1);
		bmp.addDescription("HelloBarcode",4);
		bmp.export("C:/Users/matth/Desktop/testAztec.pdf");
		
		AztecScanner as = new AztecScanner("C:/Users/matth/Desktop/testAztec.pdf");
	}

}
