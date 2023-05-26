import java.io.*;
import java.util.*;

class Status{

	private int serijskaSt;
	private boolean rezervirano;
	private boolean izposojeno;
	
	
	public Status(){
		this.serijskaSt = 0;
		this.rezervirano = false;
		this.izposojeno = true;
	}
	
	public Status(int serijskaSt,boolean rezervirano, boolean izposojeno){
		this.serijskaSt = serijskaSt;
		this.rezervirano = rezervirano;
		this.izposojeno = izposojeno;
	}
	
	public void setIzposojeno(boolean izposojeno){
		this.izposojeno = izposojeno;
	}

	public void setSerijskaSt(int serijskaSt){
		this.serijskaSt = serijskaSt;
	}
	
	public void setRezervirano(boolean rezervirano){
		this.rezervirano = rezervirano;
	}

	public int getSerijskaSt(){
		return this.serijskaSt;
	}
	
	public boolean getIzposojeno(){
		return this.izposojeno;
	}
	public boolean getRezervirano(){
		return this.rezervirano;
	}
	
	public void setAutoSerijskaSt() throws Exception{
		int SS = 0;
		try{
			BufferedReader br = new BufferedReader(new FileReader("Serial.txt"));
			SS = Integer.parseInt(br.readLine().trim());
			setSerijskaSt(SS+1);
			br.close();
			
			PrintWriter pw = new PrintWriter(new FileWriter("Serial.txt"));
			pw.print(SS+1);
			pw.close();
		}catch(FileNotFoundException ex){
			System.out.println("Nisem nasel datoteke 'Serial.txt'");
			
			System.out.println("Prosim vnestie zadnjo znano znano serijsko stevilko");
			System.out.println("V primeru, da ne poznate stare stevilke pritisnite (x)\n za zaključitev programa.");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			boolean test = false;
			while(!test){
				String vnos = br.readLine().toLowerCase();
				if(vnos.charAt(0) == 'x' && vnos.length()<2){
					return;
				}
					try{
						int write = Integer.parseInt(vnos);
						PrintWriter pw = new PrintWriter(new FileWriter("Serial.txt"));
						pw.print(write);
						test = true;
						setSerijskaSt(write+1);
						pw.close();
					}catch(NumberFormatException e){
						System.out.println();
						System.out.println("Niste vnesli pravilno");
						System.out.println("Vnesite serijsko stevilko ali pa pritisnite (x)");
					}
			}	
			
		}
		
	}
	public void dodajStatus()throws Exception{
		setAutoSerijskaSt();
		setIzposojeno(true);
	
	}
	public void izpis(){
		//System.out.println("Serijska stevilka: "+getSerijskaSt());
		if(getIzposojeno()){
			System.out.println("Gradivo je na voljo");
		}else if(getRezervirano()){
			System.out.println("Gradivo je rezervirano");	
		}else{
			System.out.println("Gradivo je izposojeno");
		}
	}
}
