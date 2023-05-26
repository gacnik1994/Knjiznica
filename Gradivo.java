import java.io.*;
import java.util.*;

class Gradivo{

	private ArrayList<Knjiga> knjige;
	private ArrayList<Karta> karte;
	private ArrayList<AKnjiga> aKnjige;
	private ArrayList<EKnjiga> eKnjige;
	private ArrayList<Publikacija> publikacije;
	
	
	public Gradivo(){
		this.knjige = new ArrayList<>();
		this.karte = new ArrayList<>();
		this.aKnjige = new ArrayList<>();
		this.eKnjige = new ArrayList<>();
		this.publikacije = new ArrayList<>();
	
	}
	
	public Gradivo(ArrayList<Knjiga> knjige, ArrayList<Karta> karte, ArrayList<AKnjiga> aKnjige, ArrayList<EKnjiga> eKnjige,ArrayList<Publikacija> publikacije){
		this.knjige = knjige;
		this.karte = karte;
		this.aKnjige = aKnjige;
		this.eKnjige = eKnjige;
		this.publikacije = publikacije;	
	}
	
	public void setKnjige(ArrayList<Knjiga> knjige){
		this.knjige = knjige;
	}
	public void setKarte(ArrayList<Karta> karte){
		this.karte = karte;
	}
	public void setAKnjige(ArrayList<AKnjiga> aKnjige){
		this.aKnjige = aKnjige;
	}
	public void setEKnjige(ArrayList<EKnjiga> eKnjige){
		this.eKnjige = eKnjige;
	}
	public void setPublikacije(ArrayList<Publikacija> publikacije){
		this.publikacije = publikacije;
	}
	
	public ArrayList<Knjiga> getKnjige(){
		return this.knjige;
	}
	public ArrayList<Karta> getKarte(){
		return this.karte;
	}
	public ArrayList<AKnjiga> getAKnjige(){
		return this.aKnjige;
	}
	public ArrayList<EKnjiga> getEKnjige(){
		return this.eKnjige;
	}
	public ArrayList<Publikacija> getPublikacije(){
		return this.publikacije;
	}

	public void dodajKnjiga() throws Exception{
		Knjiga k = new Knjiga();
		k = k.izberiTip();
		k.dodajKnjigo();
		this.knjige.add(k);
		
	}
	public void dodajKarta()throws Exception{
		Karta k = new Karta();
		k = k.izberiTip();
		k.dodajKarto();
		this.karte.add(k);
	}
	public void dodajAKnjiga()throws Exception{
		AKnjiga a = new AKnjiga();
		a = a.izberiTip();
		a.dodajAKnjigo();
		this.aKnjige.add(a);
	}
	public void dodajEKnjiga()throws Exception{
		EKnjiga e = new EKnjiga();
		e = e.izberiTip();
		e.dodajEKnjigo();
		this.eKnjige.add(e);
	}
	public void dodajPublikacija()throws Exception{
		Publikacija p = new Publikacija();
		p = p.izberiTip();
		p.dodajPublikacijo();
		this.publikacije.add(p);
	}
	
	public void izpisKnjig(){
		for(int i = 0 ; i<knjige.size();i++){
			getKnjige().get(i).izpis();
		}
	}
	public void izpisKart(){
		for(int i = 0 ; i<karte.size();i++){
			getKarte().get(i).izpis();
		}
	}public void izpisAKnjig(){
		for(int i = 0 ; i<aKnjige.size();i++){
			getAKnjige().get(i).izpis();
		}
	}public void izpisEKnjig(){
		for(int i = 0 ; i<eKnjige.size();i++){
			getEKnjige().get(i).izpis();
		}
	}public void izpisPublikacij(){
		for(int i = 0 ; i<publikacije.size();i++){
			getPublikacije().get(i).izpis();
		}
	}
	public void izpisGradiv(){
		izpisKnjig();
		izpisKart();
		izpisAKnjig();
		izpisEKnjig();
		izpisPublikacij();		
	}
	public String gradivoToString(){
		String niz = "";
		for(int i = 0; i < knjige.size();i++){
			niz += knjige.get(i).knjigaToString();
		}
		for(int i = 0; i < karte.size();i++){
			niz += karte.get(i).kartaToString();
		}
		for(int i = 0; i < aKnjige.size();i++){
			niz += aKnjige.get(i).aKnjigaToString();
		}
		for(int i = 0; i < eKnjige.size();i++){
			niz += eKnjige.get(i).eKnjigaToString();
		}
		for(int i = 0; i < publikacije.size();i++){
			niz += publikacije.get(i).publikacijaToString();
		}
		return niz;
	
	}

	public void dodajGradivo() throws Exception{
		boolean zacetek = true;
		while(zacetek){
			zacetek = false;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(" ----------------------------------------------------- ");
			System.out.println("| Pritisnite (k) za vnos knjige                       |");
			System.out.println("| Pritisnite (m) za vnos kartografskega gradiva       |");
			System.out.println("| Pritisnite (p) za vnos publikacije                  |");
			System.out.println("| Pritisnite (a) za vnos audio knjige                 |");
			System.out.println("| Pritisnite (e) za vnos e-knjige                     |");
			System.out.println("| Pritisnite (x) za izhod                             |");
			System.out.println(" ----------------------------------------------------- ");
			boolean pravilno = false;
			while(!pravilno){
				try{
					System.out.print("| ");
					String vnos = br.readLine().toLowerCase();
					if(vnos.length() == 1){	
						char znak = vnos.charAt(0);
						switch(znak){
								case 'k':
									dodajKnjiga();
									pravilno = true;
									break;
								case 'e':
									dodajEKnjiga();
									pravilno = true;
									break;
								case 'a':
									dodajAKnjiga();
									pravilno = true;
									break;
								case 'm':
									dodajKarta();
									pravilno = true;
									break;
								case 'p':
									dodajPublikacija();
									pravilno = true;
									break;
								case 'x':
									return;
								default:
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Napacen vnos poskusite ponovno                      |");
									System.out.println(" ----------------------------------------------------- ");
									break;
						}
					
					}else{
						System.out.println(" ----------------------------------------------------- ");
						System.out.println("| Napacen vnos poskusite ponovno                      |");
						System.out.println(" ----------------------------------------------------- ");
					}
				}catch (NullPointerException e){
					pravilno = true;
					zacetek = true;
				}		
			}
		}	
	}
	
	
	/*public static void main(String[] args)throws Exception{
		//Gradivo g = new Gradivo();
		//int argument = Integer.parseInt(args[0]);
		//for(int i = 0; i<argument;i++){
		//	g.dodajGradivo();
		//}	
		//g.izpisGradiv();
		
		System.out.println(" _  _  _   _     _  _  ___  _   _  _  ___    _ ");
		System.out.println("| |/ /| \\ | |   | || ||_  || \\ | || |/ __|  / \\");
		System.out.println("|   | |  \\| |||_| || | / / |  \\| || |||__  / _ \\");
		System.out.println("|_|\\_\\|_|\\__||____||_||___||_|\\__||_|\\___|/_/ \\_\\");
	}*/
}
