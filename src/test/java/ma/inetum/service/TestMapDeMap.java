package ma.inetum.service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class TestMapDeMap {

	public static void main(String[] args) {
//		Map<Map<String, String>, String> map = new HashMap<>();
//		Map<String, String> cle = new HashMap<>();
//		cle.put("F001", "MAD");
//		map.put(cle, "001");
//		Map<String, String> cle1 = new HashMap<>();
//		cle1.put("F001", "MAD");
//		map.put(cle1, "002");
//		Map<String, String> cle2 = new HashMap<>();
//		cle2.put("F001", "EUR");
//		map.put(cle2, "002");
//		System.out.println(map.toString());
		Double d = 33388733.9558;
		DecimalFormat myFormatter = new DecimalFormat();
		DecimalFormatSymbols symbols = new DecimalFormat().getDecimalFormatSymbols();
		symbols.setGroupingSeparator(' ');
		symbols.setDecimalSeparator(',');
		myFormatter.setDecimalFormatSymbols(symbols);
		System.out.println(myFormatter.format(d));
		
		
		Double d1 = 0d;
		Double d2 = 0d;
		System.out.println();
		Double mnt = 2d;
		mnt = (mnt + 3d);
		System.out.println(mnt);
	}
	
	public void testAffectaion() {
		
	}
}
