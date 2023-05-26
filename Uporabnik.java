import java.io.*;
import java.util.*;


class Uporabnik{

	private String ime;
	private String geslo;
	private Gradivo gradivo;
	boolean admin;
	
	public Uporabnik(){
		this.ime = "";	
		this.geslo = "";
		this.gradivo = new Gradivo();
		this.admin = false;
	}
	
	public void setIme(String ime){
		this.ime = ime;		
	}
	public void setGeslo(String geslo){
			this.geslo = geslo;
	}
	public void setGradivo(Gradivo gradivo){
		this.gradivo = gradivo;
	}
	public void setAdmin(boolean admin){
		this.admin = admin;
	}
	public String getIme(){
		return this.ime;
	}
	public String getGeslo(){
			return this.geslo;
	}
	public Gradivo getGradivo(){
		return this.gradivo;
	} 
	public boolean getAdmin(){
		return this.admin;
	}
	
	public void vnosImena() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(" ----------------------------------------------------- ");
		System.out.println("| Vpisite uporabnisko ime                             |");
		System.out.println(" ----------------------------------------------------- ");
		System.out.print("| ");
		setIme(br.readLine().trim());
		}
		
	public void vnosGesla() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(" ----------------------------------------------------- ");
		System.out.println("| Vpisite geslo                                       |");
		System.out.println(" ----------------------------------------------------- ");
		System.out.print("| ");
		String vnos = "";
		while(true){
			vnos = br.readLine().trim();
			if(vnos.length()<5){
				System.out.println(" ----------------------------------------------------- ");
				System.out.println("| Geslo mora vsebovati vsaj 5 znakov                  |");
				System.out.println("| Poskusite ponovno                                   |");
				System.out.println(" ----------------------------------------------------- ");
			}else{
				break;
			}
		}
		setGeslo(vnos);
	}	
	public void kodiranjeGesla(){
		String geslo = getGeslo();
		String koda = "";
		for(int i = 0; i < geslo.length();i++){
			char znak = geslo.charAt(i);
			int j = i + 1;
			for(int k = 0; k<j;k++){
				znak++;
			}
			koda =  koda + znak;
		}
		setGeslo(koda);
	}
	public void odkodiranjeGesla(String geslo){
		String koda = "";
		for(int i = 0; i < geslo.length();i++){
			char znak = geslo.charAt(i);
			int j = i + 1;
			for(int k = 0; k<j;k++){
				znak--;
			}
			koda =  koda + znak;
		}
		setGeslo(koda);
	}
	public void izpis(){
		System.out.println(getIme());
		getGradivo().izpisGradiv();
	}
	
	public void izpisImen(){
		System.out.println(getIme());
	}
}
