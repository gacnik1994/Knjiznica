import java.io.*;
import java.util.*;

class aRoman extends AKnjiga{

	private static final String vrsta = "ROMAN";

	public aRoman(){
	super();
	}
	
	public aRoman(Lastnosti lastnosti){
	super(lastnosti);
	}
	
	@Override
	public void izpis(){
		System.out.println("**************************************");
		System.out.println(super.getVrsta());
		System.out.println("Vrsta: " +this.vrsta);
		System.out.println("--------------------------------------");
		getLastnosti().izpis();
		System.out.println("**************************************");	
	
	}
	@Override
	public  void dodajAKnjigo() throws Exception{
		Lastnosti l = new Lastnosti();
		l.dodajLastnosti();
		setLastnosti(l);	
	}
	@Override
	public String aKnjigaToString(){
		String niz = "#aRoman\n";
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
