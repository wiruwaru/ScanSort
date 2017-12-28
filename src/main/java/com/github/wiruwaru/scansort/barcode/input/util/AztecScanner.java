package com.github.wiruwaru.scansort.barcode.input.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;

public class AztecScanner {
	
	private BufferedImage pdfAsImage;
	private String barcodeContent;
	
	public AztecScanner(PDDocument doc) {
		this.prepareScanning(doc);
	}
	
	public AztecScanner(String pdfFile) {
		PDDocument doc = null;
		try {
			doc = PDDocument.load(new File(pdfFile));
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.prepareScanning(doc);
	}
	
	public AztecScanner(File pdfFile) {
		PDDocument doc = null;
		try {
			doc = PDDocument.load(pdfFile);
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.prepareScanning(doc);
	}
	
	private void prepareScanning(PDDocument doc) {
		try {
			this.pdfAsImage = new PDFRenderer(doc).renderImage(0);
			doc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getBarcodeContent() {
		return this.barcodeContent;
	}
	
	public String scan(int sector) throws NotFoundException {
		
		BufferedImage imageToScan;
		
		if (sector > 0) {
			
			int halfWidth = (int) Math.floor(new Double(this.pdfAsImage.getWidth())/2);
			int oneThirdHeight = (int) Math.floor(new Double(this.pdfAsImage.getHeight())/3);

			imageToScan = this.pdfAsImage.getSubimage(
					((sector % 2 + 1) % 2) * halfWidth, 
					((int) Math.ceil(new Double(sector) / 2) - 1) * oneThirdHeight, 
					halfWidth, 
					oneThirdHeight);
		} else {
			
			imageToScan = this.pdfAsImage;
			
		}
		
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
		   new BufferedImageLuminanceSource(imageToScan)));
		
		Result decodedBitmap = new MultiFormatReader().decode(binaryBitmap);
		
		return decodedBitmap.getText();
		
	}
	
}
