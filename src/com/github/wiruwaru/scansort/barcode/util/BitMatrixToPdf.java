package com.github.wiruwaru.scansort.barcode.util;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.google.zxing.common.BitMatrix;

public class BitMatrixToPdf {
	
	private BitMatrix matrix;
	
	private String pdfFile;
	
	private float barcodeWidth;
	private float barcodeHeight;
	private float pdfWidth;
	private float pdfHeight;
	
	public BitMatrixToPdf(BitMatrix matrix, String pdfFile, float barcodeWidth, float barcodeHeight, float pdfWidth, float pdfHeight) {
		this.matrix = matrix;
		this.pdfFile = pdfFile;
		this.barcodeWidth = barcodeWidth;
		this.barcodeHeight = barcodeHeight;
		this.pdfWidth = pdfWidth;
		this.pdfHeight = pdfHeight;
	}
	
	public void exportToPdf() {
		
	}
	
	private void paint() {
		
	}
	
	private void export() {
		
	}
	
	private PDDocument setup() {
		return null;
	}

}
