import java.io.*;
import java.util.*;

class Karta{

	private Lastnosti lastnosti;
	private static final String vrsta = "KARTOGRAFSKO GRADIVO";

	
	public Karta(){
		this.lastnosti = new Lastnosti();	
	}
	
	public Karta(Lastnosti lastnosti){
		this.lastnosti = lastnosti;
	}
	
	public void setLastnosti(Lastnosti lastnosti){
		this.lastnosti = lastnosti;
	}
	public Lastnosti getLastnosti(){
		return this.lastnosti;
	}
	public String getVrsta(){
		return this.vrsta; 
	}
	public void dodajKarto() throws Exception{
		Lastnosti l = new Lastnosti();
		l.dodajLastnosti();
		setLastnosti(l);	
	}
	public  Karta izberiTip() throws Exception{
		boolean pravilniVnos = false;
		Karta k = new Karta();
		while(!pravilniVnos){
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(" ----------------------------------------------------- ");
			System.out.println("| Pritisnite (a) da dodate atlas                      |");
			System.out.println("| Pritisnite (z) da dodate zemljevid                  |");
			System.out.println("| Pritisnite (g) da dodate zvezdno karto              |");
			System.out.println("| Pritisnite (x) za vrnitev nazaj                     |");
			System.out.println(" ----------------------------------------------------- ");
			String vnos = br.readLine();
			if(vnos.length() == 1){
				char znak = vnos.charAt(0);
				switch(znak){
					case 'a':
						k = new Atlas();
						pravilniVnos = true;
						//knjiga.dodajknjigo();
						
						break;
					case 'g':
						k = new ZvezdnaKarta();
						pravilniVnos = true;
						break;
					case 'z':
						k = new Zemljevid();
						
						//knjiga.dodajKnjigo();
						pravilniVnos = true;
						break;
					case 'x':
						pravilniVnos = true;
						return null;
						//break;
					default:
						System.out.println(" ----------------------------------------------------- ");
						System.out.println("| Napačno ste vnesli, poskusite ponovno               |");
						System.out.println(" ----------------------------------------------------- ");				
				}
				
			}else{
				System.out.println(" ----------------------------------------------------- ");
				System.out.println("| Napačno ste vnesli, poskusite ponovno               |");
				System.out.println(" ----------------------------------------------------- ");	
			}
			
		}
		return k;
	}
	public void izpis(){
		System.out.println("**************************************");
		System.out.println(this.vrsta + "\n");
		getLastnosti().izpis();
		System.out.println("**************************************");
	}
	public String kartaToString(){
		String niz = "#Karta\n";
		niz += getLastnosti().getNaslov()+"\n";
		niz += getLastnosti().getAvtor()+"\n";
		niz += getLastnosti().getZalozba()+"\n";
		for(int i = 0; i < getLastnosti().getStatus().size() ; i++){
			niz += "#Status\n";
			niz += getLastnosti().getStatus().get(i).getSerijskaSt()+"\n";
			niz += getLastnosti().getStatus().get(i).getIzposojeno()+"\n";
		}
		
		for(int i = 0; i < getLastnosti().getSkupina().size() ; i++){
			niz += "#Skupina\n";
			niz += getLastnosti().getSkupina().get(i)+"\n";
		}	
		return niz;
	}
} 
