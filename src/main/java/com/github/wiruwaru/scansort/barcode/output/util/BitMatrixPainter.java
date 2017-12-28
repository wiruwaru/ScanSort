package com.github.wiruwaru.scansort.barcode.output.util;

import com.google.zxing.common.BitMatrix;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;

public class BitMatrixPainter {
	
	private BitMatrix matrix;
	private GraphicsContext graphics;
	
	private double width;
	private double height;
	
	public BitMatrixPainter (BitMatrix matrix, GraphicsContext graphics, double width, double height) {
		this.matrix = matrix;
		this.graphics = graphics;
		this.width = width;
		this.height = height;
	}
	
	public BitMatrixPainter () {
		this (null, null, 100, 100);
	}
	
	public BitMatrixPainter (BitMatrix matrix) {
		this (matrix, null, 100, 100);
	};
	
	public BitMatrixPainter (BitMatrix matrix, GraphicsContext graphics) {
		this (matrix, graphics, 100, 100);
	}
	
	public BitMatrixPainter (GraphicsContext graphics) {
		this (null, graphics, 100, 100);
	}
	
	public BitMatrixPainter (double dimension) {
		this (null, null, dimension, dimension);
	}
	
	public BitMatrixPainter (double width, double height) {
		this (null, null, width, height);
	}
	
	public BitMatrixPainter (BitMatrix matrix, double dimension) {
		this (matrix, null, dimension, dimension);
	}
	
	public BitMatrixPainter (BitMatrix matrix, double width, double height) {
		this (matrix, null, width, height);
	}
	
	public BitMatrixPainter (BitMatrix matrix, GraphicsContext graphics, double dimension) {
		this (matrix, graphics, dimension, dimension);
	}

	public BitMatrix getMatrix() {
		return matrix;
	}

	public void setMatrix(BitMatrix matrix) {
		this.matrix = matrix;
	}

	public GraphicsContext getGraphics() {
		return graphics;
	}

	public void setGraphics(GraphicsContext graphics) {
		this.graphics = graphics;
	}
	
	public void paint() {
		
		double matrixWidth = matrix.getWidth();
		double matrixHeight = matrix.getHeight();
		
		graphics.setFill(Color.BLACK);
		graphics.setStroke(Color.BLACK);
		graphics.setGlobalBlendMode(BlendMode.ADD);
		graphics.setLineWidth(1);//this.width / (matrixWidth * matrixWidth));
		
		for (int x = 0; x < matrixWidth; x ++) {
			for (int y = 0; y < matrixHeight; y ++) {
				if (matrix.get(x,  y)) {
					
					graphics.fillRect(Math.floor((new Double (x).doubleValue() / matrixWidth * this.width)), 
							Math.floor(new Double (y).doubleValue() / matrixHeight * this.height), 
							Math.ceil(this.width / matrixWidth), 
							Math.ceil(this.height / matrixHeight));
				
				}
			}
		}
	}
	
}
