package com.github.wiruwaru.scansort.barcode.input;

public class DataFromPdfSector {
	
	private int sector;
	private String decodedData;
	
	public DataFromPdfSector(int sector) {
		this(sector, null);
	}
	
	public DataFromPdfSector(int sector, String decodedData) {
		this.sector = sector;
		this.decodedData = decodedData;
	}

	public boolean hasCorrectSector() {
		return sector == new Integer(decodedData.substring(0, 1));
	}
	
	public int getSector() {
		return sector;
	}

	public String getDecodedData() {
		return decodedData.substring(1);
	}
	
}
