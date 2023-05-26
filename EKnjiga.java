import java.io.*;
import java.util.*;

class EKnjiga {

	private Lastnosti lastnosti;
	private static final String vrsta = "E-KNJIGA";

	
	public EKnjiga(){
		this.lastnosti = new Lastnosti();	
	}
	
	public EKnjiga(Lastnosti lastnosti){
		this.lastnosti = lastnosti;
	}
	
	public void setLastnosti(Lastnosti lastnosti){
		this.lastnosti = lastnosti;
	}
	public Lastnosti getLastnosti(){
		return this.lastnosti;
	}
	public void dodajEKnjigo() throws Exception{
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
	public  EKnjiga izberiTip() throws Exception{
		boolean pravilniVnos = false;
		EKnjiga eknjiga = new EKnjiga();
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
						eknjiga = new eRoman();
						pravilniVnos = true;
						//knjiga.dodajknjigo();
						
						break;
					case 'm':
						eknjiga = new eStrip();
						//knjiga.dodajKnjigo();
						pravilniVnos = true;
						break;
					case 'p':
						eknjiga = new ePrirocnik();
						//knjiga.dodajKnjigo();
						pravilniVnos = true;
						break;
					case 's':
						eknjiga = new eSlovar();
						//knjiga.dodajKnjigo();
						pravilniVnos = true;
						break;
					case 'd':
						eknjiga = new eDrugo();
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
		return eknjiga;
	}
	public String eKnjigaToString(){
		String niz = "#EKnjiga\n";
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
