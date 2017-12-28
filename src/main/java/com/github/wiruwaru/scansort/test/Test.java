package com.github.wiruwaru.scansort.test;

import com.github.wiruwaru.scansort.barcode.output.util.AztecCreator;

public class Test {
	
	public static void main(String[] args) {
		AztecCreator ac = new AztecCreator();
		System.out.println(ac.getAztec("Hello World").toString());
	}
	
}
