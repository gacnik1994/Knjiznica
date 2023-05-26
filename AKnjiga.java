import java.io.*;
import java.util.*;

class AKnjiga {

	private Lastnosti lastnosti;
	private static final String vrsta = "AUDIO KNJIGA";

	
	public AKnjiga(){
		this.lastnosti = new Lastnosti();	
	}
	
	public AKnjiga(Lastnosti lastnosti){
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
	public void dodajAKnjigo() throws Exception{
		Lastnosti l = new Lastnosti();
		l.dodajLastnosti();
		setLastnosti(l);	
	}
	public  AKnjiga izberiTip() throws Exception{
		boolean pravilniVnos = false;
		AKnjiga knjiga = new AKnjiga();
		while(!pravilniVnos){
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(" ----------------------------------------------------- ");
			System.out.println("| Pritisnite (r) da dodate roman                      |");
			System.out.println("| Pritisnite (p) da dodate prirocnik                  |");
			System.out.println("| Pritisnite (s) da dodate slovar                     |");
			System.out.println("| Pritisnite (d) da dodate druge vrste audioknjig     |");
			System.out.println("| Pritisnite (x) za vrnitev nazaj                     |");
			System.out.println(" ----------------------------------------------------- ");
			String vnos = br.readLine();
			if(vnos.length() == 1){
				char znak = vnos.charAt(0);
				switch(znak){
					case 'r':
						knjiga = new aRoman();
						pravilniVnos = true;
						//knjiga.dodajknjigo();
						
						break;
					case 'p':
						knjiga = new aPrirocnik();
						//knjiga.dodajKnjigo();
						pravilniVnos = true;
						break;
					case 's':
						knjiga = new aSlovar();
						//knjiga.dodajKnjigo();
						pravilniVnos = true;
						break;
					case 'd':
						knjiga = new aDrugo();
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
	public void izpis(){
		System.out.println("**************************************");
		System.out.println(this.vrsta + "\n");
		getLastnosti().izpis();
		System.out.println("**************************************");
	}
	public String aKnjigaToString(){
		String niz = "#AKnjiga\n";
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
