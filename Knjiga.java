import java.io.*;
import java.util.*;

class Knjiga{

	private Lastnosti lastnosti;
	private static final String vrsta = "KNJIGA";

	
	public Knjiga(){
		this.lastnosti = new Lastnosti();	
	}
	
	public Knjiga(Lastnosti lastnosti){
		this.lastnosti = lastnosti;
	}
	
	public void setLastnosti(Lastnosti lastnosti){
		this.lastnosti = lastnosti;
	}
	public Lastnosti getLastnosti(){
		return this.lastnosti;
	}
	public void dodajKnjigo() throws Exception{
		Lastnosti l = new Lastnosti();
		l.dodajLastnosti();
		setLastnosti(l);	
	}
	public  Knjiga izberiTip() throws Exception{
		boolean pravilniVnos = false;
		Knjiga knjiga = new Knjiga();
		while(!pravilniVnos){
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(" ----------------------------------------------------- ");
			System.out.println("| Pritisnite (r) da dodate roman                      |");
			System.out.println("| Pritisnite (m) da dodate strip                      |");
			System.out.println("| Pritisnite (p) da dodate prirocnik                  |");
			System.out.println("| Pritisnite (s) da dodate slovar                     |");
			System.out.println("| Pritisnite (d) da dodate druge vrste knjig          |");
			System.out.println("| Pritisnite (x) za vrnitev nazaj                     |");
			System.out.println(" ----------------------------------------------------- ");
			String vnos = br.readLine();
			if(vnos.length() == 1){
				char znak = vnos.charAt(0);
				switch(znak){
					case 'r':
						knjiga = new Roman();
						pravilniVnos = true;
						//knjiga.dodajknjigo();
						
						break;
					case 'm':
						knjiga = new Strip();
						//knjiga.dodajKnjigo();
						pravilniVnos = true;
						break;
					case 'p':
						knjiga = new Prirocnik();
						//knjiga.dodajKnjigo();
						pravilniVnos = true;
						break;
					case 's':
						knjiga = new Slovar();
						//knjiga.dodajKnjigo();
						pravilniVnos = true;
						break;
					case 'd':
						knjiga = new Drugo();
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
		return knjiga;
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
	public String knjigaToString(){
		String niz = "#Knjiga\n";
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
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//char vnos = br.readLine().toLowerCase().charAt(0);
		Knjiga k = new Knjiga();
		k = k.izberiTip() ;
		k.dodajKnjigo();
		k.izpis();
		boolean bol = k.getLastnosti().getStatus().get(0).getIzposojeno();
	}
}
