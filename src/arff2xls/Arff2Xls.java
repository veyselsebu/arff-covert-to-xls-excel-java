package arff2xls;

import java.io.*;

public class Arff2Xls {

	public static void main(String[] args) throws IOException {
		String baslik;
		String icerik;

		icerik = "";
		baslik = "";

		File file = new File("supermarket.arff");
		FileReader fileReader = new FileReader(file);
		String line;

		BufferedReader br = new BufferedReader(fileReader);

		while ((line = br.readLine()) != null) {
			System.out.println("1.dongu");
			line = line.replaceAll("\\s", "");
			if (line.substring(0, 9).equals("@relation")) {
				baslik = line.substring(9, line.length());
				break;
			}
		}
		while (!((line = br.readLine()).equals("@data"))) {
		System.out.println("2. dongu");
			if (line.length() > 10) {
			
				line = line.replaceAll("\\s+", "");
				// String b=line.substring(0,11);
				// System.out.println(b);

				if (line.substring(0, 10).equals("@attribute")) {
					String gecici = line.substring(11, line.length());

					for (char a : gecici.toCharArray()) {
						if (a == '\'') {
							icerik = icerik + '\t';
							break;
						} else {
							icerik = icerik + a;
						}
					}

				}
			}
		}
		icerik=icerik+"\n";
		int vey=220;
		while ((line = br.readLine()) != null) {
			
			System.out.println("3. dongu : "+vey++);
			line = line.replaceAll("\\s", "");
			line = line.replaceAll(",", "\t");
			line = line.replaceAll("\\?", "");
			icerik=icerik+line+"\n";
			
			/*
			for(char a:line.toCharArray()) {
				
				if(a!=',') {
					if(a!='?') 
					icerik=icerik+a;
				}else {
					icerik=icerik+'\t';
				}
				
		}
			icerik=icerik+'\n';*/
	}
		
		br.close();
		File sonuc=new File("./sonuclar/"+baslik+".xls");
		FileWriter yazdir=new FileWriter(sonuc);
		yazdir.write(icerik);
		System.out.println(baslik);
		System.out.println(icerik);
		yazdir.close();
	}
}
