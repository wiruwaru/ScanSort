package com.github.wiruwaru.scansort.barcode.output.util;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import com.google.zxing.common.BitMatrix;

public class BitMatrixToPdf {
	
	public static float mmPerStandard = 0.35F;
	
	private PDDocument doc;
	private PDPage page;
	
	public BitMatrixToPdf() {
		this(new PDPage(PDRectangle.A4));
	}
	
	public BitMatrixToPdf(float pdfWidth, float pdfHeight) {
		this(new PDPage(new PDRectangle(pdfWidth, pdfHeight)));
	}
	
	public BitMatrixToPdf(PDPage page) {
		this.page = page;
		this.doc = new PDDocument();
		this.doc.addPage(page);
	}
	
	public void addDescription(String description) {
		addDescription(description, 1);
	}
	
	public void addDescription(String description, int sector) {
		
		try {
			
			PDPageContentStream text = new PDPageContentStream(this.doc, this.page, PDPageContentStream.AppendMode.APPEND, false);
			text.moveTo(0, 100);
			text.beginText();
			float halfWidth = this.page.getMediaBox().getWidth() / 2;
			float pageHeight = this.page.getMediaBox().getHeight();
			float thirdHeight = pageHeight / 3; 
			
					
			text.newLineAtOffset(((sector % 2 + 1) % 2) * halfWidth + 10/ BitMatrixToPdf.mmPerStandard, 
					pageHeight - (((float) Math.ceil(new Double(sector) / 2) - 1) * thirdHeight + 5 / BitMatrixToPdf.mmPerStandard));
//			text.newLineAtOffset(0, 0);
	
			text.setFont(PDType1Font.HELVETICA, 12);
			text.showText(description);
			text.endText();
			
			text.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void export(String pdfFile) {
		this.export(new File(pdfFile));
	}
	
	public void close() throws IOException {
		this.doc.close();
	}
	
	public void export(File file) {
		try {
			this.doc.save(file);
			this.doc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			doc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paint(BitMatrix matrix, int sector) {
		if (sector == 0) {
			this.paint(matrix, 0, 0);
		} else {
			float halfWidth = this.page.getMediaBox().getWidth() / 2;
			float thirdHeight = this.page.getMediaBox().getHeight() / 3;
			this.paint(matrix, ((sector % 2 + 1) % 2) * halfWidth, ((int) Math.ceil(new Double(sector) / 2) - 1) * thirdHeight);
		}
	}
	
	public void paint(BitMatrix matrix) {
		this.paint(matrix, 0, 0);
	}
	
	private void paint(BitMatrix matrix, float positionFromLeft, float positionFromTop) {
		
		try {
			
			PDPageContentStream barcodeImage = new PDPageContentStream(this.doc, page, PDPageContentStream.AppendMode.APPEND, false);
			
			float matrixWidth = new Float(matrix.getWidth());
			float matrixHeight = new Float(matrix.getHeight());
			
			float barcodeWidth = (float) (0.9 * this.page.getMediaBox().getWidth() / 2);
			float barcodeHeight = (float) (0.9 * this.page.getMediaBox().getHeight() / 3);
			
			if (matrix.getHeight() == matrix.getWidth()) {
				
				if (barcodeWidth < barcodeHeight) {
					barcodeHeight = barcodeWidth;
				} else {
					barcodeWidth = barcodeHeight;
				}
			}
			
			float topAdjustment = this.page.getMediaBox().getHeight() - positionFromTop - this.page.getMediaBox().getHeight() / 3 + barcodeHeight / 2;
			float leftAdjustment = positionFromLeft + this.page.getMediaBox().getWidth() / 2 - barcodeWidth / 2; 
			
			for (int x = 0; x < matrixWidth; x ++) {
				for (int y = 0; y < matrixHeight; y ++) {
					if (matrix.get(x,  y)) {
						
						barcodeImage.addRect(leftAdjustment - barcodeWidth / 2 + new Float(x) / matrixWidth * barcodeWidth, 
								topAdjustment + barcodeHeight / 2 - new Float(y) / matrixHeight * barcodeHeight, 
								barcodeWidth / matrixWidth, 
								barcodeHeight / matrixHeight);
						barcodeImage.fill();
					
					}
				}
			} 
			
			barcodeImage.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
