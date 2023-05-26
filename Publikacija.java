import java.io.*;
import java.util.*;

class Publikacija {

	private Lastnosti lastnosti;
	private static final String vrsta = "PERIODICNA PUBLIKACIJA";

	
	public Publikacija(){
		this.lastnosti = new Lastnosti();	
	}
	
	public Publikacija(Lastnosti lastnosti){
		this.lastnosti = lastnosti;
	}
	
	public void setLastnosti(Lastnosti lastnosti){
		this.lastnosti = lastnosti;
	}
	public Lastnosti getLastnosti(){
		return this.lastnosti;
	}
	
	public void dodajPublikacijo() throws Exception{
		Lastnosti l = new Lastnosti();
		l.dodajLastnosti();
		setLastnosti(l);	
	}
	public String getVrsta(){
		return this.vrsta; 
	}
	public void izpis(){
		System.out.println("**************************************");
		System.out.println(this.vrsta + "\n");
		getLastnosti().izpis();
		System.out.println("**************************************");
	}
	public  Publikacija izberiTip() throws Exception{
		boolean pravilniVnos = false;
		Publikacija p = new Publikacija();
		while(!pravilniVnos){
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(" ----------------------------------------------------- ");
			System.out.println("| Pritisnite (c) da dodate casopis                    |");
			System.out.println("| Pritisnite (r) da dodate revijo                     |");
			System.out.println("| Pritisnite (a) da dodate clanek                     |");
			System.out.println("| Pritisnite (o) da dodate ostalo                     |");
			System.out.println("| Pritisnite (x) za vrnitev nazaj                     |");
			System.out.println(" ----------------------------------------------------- ");
			String vnos = br.readLine();
			if(vnos.length() == 1){
				char znak = vnos.charAt(0);
				switch(znak){
					case 'c':
						p = new Casopis();
						pravilniVnos = true;
						//knjiga.dodajknjigo();
						
						break;
					case 'r':
						p = new Revija();
						//knjiga.dodajKnjigo();
						pravilniVnos = true;
						break;
					case 'a':
						p = new Clanek();
						//knjiga.dodajKnjigo();
						pravilniVnos = true;
						break;
					case 'o':
						p = new Ostalo();
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
		return p;
	}
	public String publikacijaToString(){
		String niz = "#Publikacija\n";
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
