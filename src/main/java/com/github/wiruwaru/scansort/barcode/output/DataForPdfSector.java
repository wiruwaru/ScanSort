package com.github.wiruwaru.scansort.barcode.output;

public class DataForPdfSector {

	private String description;
	private String dataToBeEncoded;
	
	public DataForPdfSector(String dataToBeEncoded, String description) {
		this.dataToBeEncoded = dataToBeEncoded;
		this.description = description;
	}
	
	public DataForPdfSector(String dataToBeEncoded) {
		this(dataToBeEncoded, null);
	}

	public String getDescription() {
		return description;
	}

	public String getDataToBeEncoded() {
		return dataToBeEncoded;
	}
	
}
