import java.io.*;
import java.util.*;

class Lastnosti{

	private String naslov;
	private String avtor;
	private String zalozba;
	private ArrayList<Status> status;
	private ArrayList<String> skupina;
	
	public Lastnosti(){
		this.naslov = "";
		this.avtor = "";
		this.zalozba = "";
		this.status = new ArrayList<>();
		this.skupina = new ArrayList<>();
	}

	public Lastnosti(String naslov, String avtor, String zalozba, ArrayList<Status> status, ArrayList<String> skupina){
		this.naslov = naslov;
		this.avtor = avtor;
		this.zalozba = zalozba;
		this.status = status;
		this.skupina = skupina;
	}
	
	public void setNaslov(String naslov){
		this.naslov = naslov;
	}
	public void setAvtor (String avtor){
		this.avtor = avtor;
	}
	public void setZalozba(String zalozba){
		this.zalozba = zalozba;
	}
	public void  setStatus(ArrayList<Status> status){
		this.status = status;
	}
	public void setSkupina(ArrayList<String> skupina){
		this.skupina = skupina;
	}
	public String getNaslov(){
		return this.naslov;
	}
	public String getAvtor(){
		return this.avtor;
	}
	public String getZalozba(){
		return this.zalozba;
	}
	public ArrayList<Status> getStatus(){
		return this.status;
	}
	public ArrayList<String> getSkupina(){
		return this.skupina;
	}
	public void izpis(){
		System.out.println("| Naslov: "+this.getNaslov());
		System.out.println("| Avtor: "+this.getAvtor());
		System.out.println("| Zalozba: "+this.getZalozba());
		
		System.out.println("|----------------------------------------------------- ");
		System.out.println("| Tip gradiva:");
		System.out.print("| ");
		for(int i = 0; i<getSkupina().size();i++){
			if(i>0){
				System.out.print(", ");
			}
			System.out.print(getSkupina().get(i));
		}
		System.out.println();
		System.out.println("|----------------------------------------------------- ");
		boolean iz = false;
		for(int i = 0; i< getStatus().size(); i++){
			if(getStatus().get(i).getIzposojeno() && !(getStatus().get(i).getRezervirano())){
				iz = true;
				break;
			}
		}
		if(iz){
			System.out.println("| Gradivo je na voljo");		
		}else{
			System.out.println("| Gradivo ni na voljo");
		}
		System.out.println("|----------------------------------------------------- ");
		System.out.println("| Stevilo gradiv v knjiznici: " + status.size());
		int naVoljo = 0;
		int izposojeno = 0;
		int rezervirano = 0;
		for ( int i = 0; i<getStatus().size();i++){
			boolean p = status.get(i).getIzposojeno();
			if(p){
				naVoljo++;
				if (status.get(i).getRezervirano()){
				rezervirano++;
				}
			}else if(!p){
				izposojeno++;
			}
			
		}
		naVoljo -= rezervirano; 
		System.out.println("| Na voljo: "+naVoljo);
		System.out.println("| Rezerviranih " + rezervirano);
		System.out.println("| Izposojeno: "+izposojeno);
	}
	public void dodajLastnosti() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Vnesite naslov gradiva");
		setNaslov(br.readLine());
		System.out.println("Vnesite avtorja gradiva.");
		setAvtor(br.readLine());
		System.out.println("Vnesite zalozbo gradiva");
		setZalozba(br.readLine());
		ArrayList<String> skupine = new ArrayList<>();
		System.out.println("Vnesite skupine gradiva");
		skupine.add(br.readLine());
		boolean dodaj = true;
		while(dodaj){
			System.out.println("Zelite dodati se kaksno skupino?" );
			System.out.println("vnesite (da) ali (ne)");
				String vnos = br.readLine().toLowerCase();
				if(vnos.equals("da")){
					System.out.println("Vnesite skupino");
					skupine.add(br.readLine());
				}else if(vnos.equals("ne")){
					dodaj = false;
				}else{
					System.out.println("Napacno ste odgovorili, poskusite ponovno.");
				}
		}
		setSkupina(skupine);
		System.out.println("Koliko gradiv želite dodati?");
		ArrayList<Status> status = new ArrayList<>();
		boolean loop = true;
		while(loop){
			try{
				int i = Integer.parseInt(br.readLine());
				for (int j = 0; j<i; j++){
					Status s = new Status();
					s.dodajStatus();
					status.add(s);
				}
				loop = false;
				setStatus(status);
			}catch(NumberFormatException e){
				System.out.println("Prosim vnesite stevilko");
		
			}
		}
		System.out.println("\nVnesli ste novo gradivo.\n");
	}
	public static void main(String[] args) throws Exception{
		Lastnosti l = new Lastnosti();
		l.dodajLastnosti();
		l.izpis();
	}
}
