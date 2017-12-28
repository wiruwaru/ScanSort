package com.github.wiruwaru.scansort.barcode.input;

import java.util.ArrayList;

public class DataFromPdf {
	
	private ArrayList<DataFromPdfSector> dataFromPdf;
	private boolean inversed;
	
	public DataFromPdf() {
		this.dataFromPdf = new ArrayList<>();
		this.inversed = false;
	}
	
	public DataFromPdf(DataFromPdfSector dataFromSector) {
		this();
		this.addData(dataFromSector);
	}
	
	public void addData(DataFromPdfSector dataFromSector) {
		this.dataFromPdf.add(dataFromSector);
		this.verifyOrientation();
	}
	
	private void verifyOrientation() {
		this.inversed = this.dataFromPdf.get(this.dataFromPdf.size() - 1).hasCorrectSector();
	}
	
	public String getDecodedData(int sector) {
		if (!inversed) {
			return this.dataFromPdf.get(sector - 1).getDecodedData();
		} 
		return this.dataFromPdf.get(6 - sector).getDecodedData();
	}

}
