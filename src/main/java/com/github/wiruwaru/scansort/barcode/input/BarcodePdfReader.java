package com.github.wiruwaru.scansort.barcode.input;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

import com.github.wiruwaru.scansort.barcode.input.util.AztecScanner;
import com.google.zxing.NotFoundException;

public class BarcodePdfReader {
	
	private AztecScanner scanner;
	
	private DataFromPdf data;
	
	public BarcodePdfReader(PDDocument doc) {
		this.scanner = new AztecScanner(doc);
		this.scanAll();
	}
	
	public BarcodePdfReader(String pdfFile) throws InvalidPasswordException, IOException {
		this(PDDocument.load(new File(pdfFile)));
	}
	
	public BarcodePdfReader(File pdfFile) throws InvalidPasswordException, IOException {
		this(PDDocument.load(pdfFile));
	}
	
	public void scanAll() {
		String tempScan = null;
		for (int sector = 0; sector < 7; sector ++) {
			try {
				tempScan = scanner.scan(sector);
			} catch (NotFoundException e) {
				//handle exception
				e.printStackTrace();
			}
			
			this.data.addData(new DataFromPdfSector(sector, tempScan));
			
		}
	}
	
	public String getDecodedDataBySector(int sector) {
		return this.data.getDecodedData(sector);
	}
	
	public String[] getDecodedData() {
		String[] allData = new String[7];
		for (int sector = 0; sector < 7; sector ++) {
			allData[sector] = this.data.getDecodedData(sector);
		}
		return allData;
	}

}
