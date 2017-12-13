package com.github.wiruwaru.scansort.barcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.aztec.AztecWriter;
import com.google.zxing.common.BitMatrix;

public class AztecCreator {
	
	private AztecWriter writer;
	
	public AztecCreator() {
		writer = new AztecWriter();
	};
	
	public BitMatrix getAztec(String code) {
		return writer.encode(code, BarcodeFormat.AZTEC, 100, 100);
	}
	
}
