package com.github.wiruwaru.scansort.barcode.output;

import java.util.ArrayList;

public class DataForPdf {
	
	private ArrayList<DataForPdfSector> dataForPdf;
	private int currentPosition;
	
	public DataForPdf(DataForPdfSector data) {
		this();
		this.dataForPdf.add(data);
	}
	
	public DataForPdf() {
		this.dataForPdf = new ArrayList<>();
		this.currentPosition = 0;
	}
	
	public void addData(DataForPdfSector data) {
		if (this.dataForPdf.size() < 6) {
			this.dataForPdf.add(data);
		} else {
			//no more space on this page, decide how to deal with that
		}
	}
	
	public DataForPdfSector next() {
		if (this.currentPosition < this.dataForPdf.size() - 1) {
			return this.dataForPdf.get(this.currentPosition++);
		}
		return null;
	}
	
}
