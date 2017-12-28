package com.github.wiruwaru.scansort.barcode.output;

import java.io.File;

import com.github.wiruwaru.scansort.barcode.output.util.AztecCreator;
import com.github.wiruwaru.scansort.barcode.output.util.BitMatrixToPdf;

public class BarcodePdfCreator {
	
	private File pdfFile;
	private AztecCreator aztecCreator;
	private BitMatrixToPdf bitMatrixToPdfWriter;
	
	public BarcodePdfCreator(String pdfFile) {
		this(new File(pdfFile));
	}
	
	public BarcodePdfCreator(File pdfFile) {
		this.pdfFile = pdfFile;
		this.aztecCreator = new AztecCreator();
		this.bitMatrixToPdfWriter = new BitMatrixToPdf();
	}
	
	public void addBarcodes(DataForPdf data) {
		
		DataForPdfSector dataForSector;
		int currentSector = 1;
		
		while ((dataForSector = data.next()) != null) {
		
			this.bitMatrixToPdfWriter.paint(this.aztecCreator.getAztec(new Integer(currentSector) + "_" + 
					dataForSector.getDataToBeEncoded()), currentSector);
			
			if (dataForSector.getDescription() != null) {
					this.bitMatrixToPdfWriter.addDescription(dataForSector.getDescription(), currentSector);
			}
			currentSector++;
		}
	}
	
	public void createPdf() {
		this.bitMatrixToPdfWriter.export(pdfFile);
	}

}
