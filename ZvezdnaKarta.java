import java.io.*;
import java.util.*;


class ZvezdnaKarta extends Karta{
	
	private static final String vrsta = "ZVEZDNA KARTA";
	private double epoha;
	private double mejnaMagnituda;

	public ZvezdnaKarta(){
		super();
		this.epoha = 0;
		this.mejnaMagnituda = 0;
	}
	
	public ZvezdnaKarta(Lastnosti lastnosti, double epoha,double mejnaMagnituda){
		super(lastnosti);
		this.epoha = epoha;
		this.mejnaMagnituda = mejnaMagnituda;
	}
	
	public void setEpoha(double epoha){
		this.epoha = epoha;
	}
	
	public void setMejnaMagnituda(double mejnaMagnituda){
		this.mejnaMagnituda = mejnaMagnituda;
	}
	
	public double getEpoha(){
		return this.epoha;
	}
	
	public double getMejnaMagnituda(){
		return this.mejnaMagnituda;
	}
	
	@Override
	public void izpis(){
		System.out.println("**************************************");
		System.out.println(super.getVrsta());
		System.out.println("Vrsta: " +this.vrsta);
		System.out.println("--------------------------------------");
		getLastnosti().izpis();
		System.out.println("--------------------------------------");
		System.out.println("Epoha:" + this.epoha);
		System.out.println("Mejna magnituda: "+this.mejnaMagnituda);
		System.out.println("**************************************");	
	
	}
	@Override
	public  void dodajKarto() throws Exception{
		Lastnosti l = new Lastnosti();
		l.dodajLastnosti();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Vpisite epoho zvezdne karte");
		double epoha = 0;
		while(true){
			try{
				epoha = Double.parseDouble(br.readLine().trim());
				setEpoha(epoha);
				break;
			}catch(NumberFormatException e){
				System.out.println("Niste vpisali stevilke!");
			}
		}	
		System.out.println("Vpisite mejno magnitudo zvezdne karte");
		double mejnaMagnituda = 0;
		while(true){
			try{
				mejnaMagnituda = Double.parseDouble(br.readLine().trim());
				setMejnaMagnituda(mejnaMagnituda);
				break;
			}catch(NumberFormatException e){
				System.out.println("Niste vpisali stevilke!");
			}
		}
		setLastnosti(l);	
	}
	@Override
	public String kartaToString(){
		String niz = "#ZvezdnaKarta\n";
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
		niz += "#Ephona\n";
		niz += getEpoha()+"\n";
		niz += "#mejnaMagnituda\n";
		niz += getMejnaMagnituda()+"\n";	
		return niz;
	}


}