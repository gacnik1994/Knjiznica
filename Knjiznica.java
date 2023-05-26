import java.io.*;
import java.util.*;

class Knjiznica{

	private Gradivo gradivo;
	private ArrayList<Uporabnik> uporabniki;
	
	public Knjiznica(){
		this.gradivo = new Gradivo();
		this.uporabniki = new ArrayList<>();	
	}

	public Knjiznica(Gradivo gradivo, ArrayList<Uporabnik> uporabnik){
		this.gradivo = gradivo;
		this.uporabniki = uporabnik;
	}
	
	public Gradivo getGradivo(){
		return this.gradivo;
	}
	
	public ArrayList<Uporabnik> getUporabniki(){
		return this.uporabniki;
	}
	
	public void setGradivo(Gradivo gradivo){
		this.gradivo = gradivo;
	}
	
	public void knjiznicaToFile() throws Exception{
		PrintWriter pw = new PrintWriter(new FileWriter("Gradivo.txt"));
		String vGradivo = gradivo.gradivoToString();
		pw.println("#Gradivo");
		pw.print(vGradivo);
		pw.println("Gradivo#");
		pw.close();
		vnosVDatoteko();
	}
	
	public void dodajUporabnika(Uporabnik u) throws Exception{
		getUporabniki().add(u);
	
	}
	
	public String odkodiranjeGesla(String geslo){
		String koda = "";
		for(int i = 0; i < geslo.length();i++){
			char znak = geslo.charAt(i);
			int j = i + 1;
			for(int k = 0; k<j;k++){
				znak--;
			}
			koda =  koda + znak;
		}
		return koda;
	}
	
	public void vstop(Uporabnik u)throws Exception{
		boolean admin = u.getAdmin();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean ok = true;
		while(ok)	
			System.out.println(" ----------------------------------------------------- ");
			System.out.println("| Pritisnite (i) za iskanje po ključnih besedah       |");
			System.out.println("| Pritisnite (k) za iskanje knjig                     |");
			System.out.println("| Pritisnite (m) za iskanje kartografskih gradiv      |");
			System.out.println("| Pritisnite (p) za iskanje periodicnih publikacij    |");
			System.out.println("| Pritisnite (e) za iskanje e-Knjig                   |");
			System.out.println("| Pritisnite (a) za iskanje audio knjig               |");
			System.out.println("| Pritisnite (t) za iskanje gradiv po temah           |");
			System.out.println(" ----------------------------------------------------- ");
			String vnos = br.readLine().trim().toLowerCase();
			if(vnos.length() == 1 ){
				char znak = vnos.charAt(0);
				switch(znak){
					case 'i':
					case 'k':
					case 'm':
					case 'p':
					case 'e':
					case 'a':
					case 't':
					default:
						System.out.println(" ----------------------------------------------------- ");
						System.out.println("| Napacen vnos, poskusite ponovno.                    |");
						System.out.println(" ----------------------------------------------------- ");
				}
			
			}else{
				System.out.println(" ----------------------------------------------------- ");
				System.out.println("| Napacen vnos, poskusite ponovno.                    |");
				System.out.println(" ----------------------------------------------------- ");
			}
			
		
		
	
	}
	
	public void odstraniGradivo(Uporabnik u) throws Exception{
			int stevec = 1;
			ArrayList<Integer> indeksK = new ArrayList<>();
			ArrayList<Integer> indeksM = new ArrayList<>();
			ArrayList<Integer> indeksP = new ArrayList<>();
			ArrayList<Integer> indeksA = new ArrayList<>();
			ArrayList<Integer> indeksE = new ArrayList<>();
			for(int i = 0; i<u.getGradivo().getKnjige().size() ; i++){
				if(!(u.getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(0).getIzposojeno())&&!(u.getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(0).getRezervirano())){	
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("| Indeks "+stevec);
					System.out.println(" ----------------------------------------------------- ");	
					stevec++;
					indeksK.add(i);
					u.getGradivo().getKnjige().get(i).izpis();
				}	
			}
			for(int i = 0; i<u.getGradivo().getKarte().size() ; i++){
				if(!(u.getGradivo().getKarte().get(i).getLastnosti().getStatus().get(0).getIzposojeno())&&!(u.getGradivo().getKarte().get(i).getLastnosti().getStatus().get(0).getRezervirano())){	
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("| Indeks "+stevec);
					System.out.println(" ----------------------------------------------------- ");	
					stevec++;
					indeksM.add(i);
					u.getGradivo().getKarte().get(i).izpis();
				}	
			}
			for(int i = 0; i<u.getGradivo().getPublikacije().size() ; i++){
				if(!(u.getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(0).getIzposojeno())&&!(u.getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(0).getRezervirano())){	
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("| Indeks "+stevec);
					System.out.println(" ----------------------------------------------------- ");	
					stevec++;
					indeksP.add(i);
					u.getGradivo().getPublikacije().get(i).izpis();
				}	
			}
			for(int i = 0; i<u.getGradivo().getAKnjige().size() ; i++){
				if(!(u.getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(0).getIzposojeno())&&!(u.getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(0).getRezervirano())){	
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("| Indeks "+stevec);
					System.out.println(" ----------------------------------------------------- ");	
					stevec++;
					indeksE.add(i);
					u.getGradivo().getAKnjige().get(i).izpis();
				}
			}
			for(int i = 0; i<u.getGradivo().getEKnjige().size() ; i++){
				if(!(u.getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(0).getIzposojeno())&&!(u.getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(0).getRezervirano())){	
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("| Indeks "+stevec);
					System.out.println(" ----------------------------------------------------- ");	
					stevec++;
					indeksA.add(i);
					u.getGradivo().getEKnjige().get(i).izpis();
				}
			}
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.println(" ----------------------------------------------------- ");
			System.out.println("| Pritisnite (v) za vracilo gradiv                    |");
			System.out.println("| Pritisnite (x) za nazaj                             |");
			System.out.println(" ----------------------------------------------------- ");
			String vnos=br.readLine().toLowerCase();
			if(vnos.length()==1){
				char znak=vnos.charAt(0);
				switch(znak){
					case 'v':
						System.out.println(" ----------------------------------------------------- ");
						System.out.println("| Vnesite indeks gradiva, ki ga zelite vrniti         |");
						System.out.println("| ali vnesite drug simbol, ki ni stevilo za zakljucek |");
						System.out.println(" ----------------------------------------------------- ");
						int serijska = 0;
						while(true){
							try{
								int dKnjige = indeksK.size();
								int dKarte = indeksM.size();
								int dPublikacije = indeksP.size();
								int dEKnjige = indeksE.size();
								int dAKnjige = indeksA.size();
								int indeks = Integer.parseInt(br.readLine().trim());
								if(indeks <= dKnjige){
									serijska = u.getGradivo().getKnjige().get(indeks-1).getLastnosti().getStatus().get(0).getSerijskaSt();
									u.getGradivo().getKnjige().remove(indeks-1);
									for(int i = 0;i<getGradivo().getKnjige().size();i++){
										for(int j = 0 ; j < getGradivo().getKnjige().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt() == serijska){
												getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).setIzposojeno(true);
											}	
										}	
									}
									break;
								}else if(indeks > dKnjige && indeks <= (dKarte + dKnjige)){
									serijska = u.getGradivo().getKarte().get(indeks-1- dKnjige).getLastnosti().getStatus().get(0).getSerijskaSt();
									u.getGradivo().getKarte().remove(indeks-1- dKnjige);
									for(int i = 0;i<getGradivo().getKarte().size();i++){
										for(int j = 0 ; j < getGradivo().getKarte().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getSerijskaSt() == serijska){
												getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).setIzposojeno(true);
											}	
										}	
									}			
									break;
								}else if(indeks > (dKarte + dKnjige) && indeks <= (dKarte + dKnjige + dPublikacije)){
									serijska = u.getGradivo().getPublikacije().get(indeks-1- dKnjige-dKarte).getLastnosti().getStatus().get(0).getSerijskaSt();
									u.getGradivo().getPublikacije().remove(indeks-1- dKnjige-dKarte);
									for(int i = 0;i<getGradivo().getPublikacije().size();i++){
										for(int j = 0 ; j < getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getSerijskaSt() == serijska){
												getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).setIzposojeno(true);
											}	
										}	
									}
									break;
								}else if(indeks > (dKarte + dKnjige + dPublikacije) && indeks <= (dKarte + dKnjige + dPublikacije + dAKnjige )){
									serijska = u.getGradivo().getAKnjige().get(indeks-1- dKnjige-dKarte-dPublikacije).getLastnosti().getStatus().get(0).getSerijskaSt();
									u.getGradivo().getAKnjige().remove(indeks-1- dKnjige-dKarte-dPublikacije);
									for(int i = 0;i<getGradivo().getAKnjige().size();i++){
										for(int j = 0 ; j < getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt() == serijska){
												getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).setIzposojeno(true);
											}	
										}	
									}
									break;
								}else if(indeks >(dKarte + dKnjige + dPublikacije + dAKnjige ) && indeks <= (dKarte + dKnjige + dPublikacije + dAKnjige +dEKnjige)){
									serijska = u.getGradivo().getEKnjige().get(indeks-1- dKnjige-dKarte-dPublikacije-dAKnjige).getLastnosti().getStatus().get(0).getSerijskaSt();
									u.getGradivo().getEKnjige().remove(indeks-1- dKnjige-dKarte-dPublikacije-dAKnjige);
									for(int i = 0;i<getGradivo().getEKnjige().size();i++){
										for(int j = 0 ; j < getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt() == serijska){
												getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).setIzposojeno(true);
											}	
										}	
									}
									break;
								}else{
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Napacen vnos indeksa poskusite ponovno              |");
									System.out.println(" ----------------------------------------------------- ");
								}
							}catch(NumberFormatException e){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Niste vnesli stevila, program se bo vrnil nazaj     |");
									System.out.println(" ----------------------------------------------------- ");
									break;
							}	
						}	
					case 'x':
						return;
					default:
						System.out.println(" ----------------------------------------------------- ");
						System.out.println("| Napacen vnos poskusite ponovno                      |");
						System.out.println(" ----------------------------------------------------- ");
						break;
				}

			}
		}
	
		public void odstraniRezervirano(Uporabnik u) throws Exception{
			int stevec = 1;
			ArrayList<Integer> indeksK = new ArrayList<>();
			ArrayList<Integer> indeksM = new ArrayList<>();
			ArrayList<Integer> indeksP = new ArrayList<>();
			ArrayList<Integer> indeksA = new ArrayList<>();
			ArrayList<Integer> indeksE = new ArrayList<>();
			for(int i = 0; i<u.getGradivo().getKnjige().size() ; i++){
				if((u.getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(0).getIzposojeno())&&(u.getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(0).getRezervirano())){	
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("| Indeks "+stevec);
					System.out.println(" ----------------------------------------------------- ");	
					stevec++;
					indeksK.add(i);
					u.getGradivo().getKnjige().get(i).izpis();
				}	
			}
			for(int i = 0; i<u.getGradivo().getKarte().size() ; i++){
				if((u.getGradivo().getKarte().get(i).getLastnosti().getStatus().get(0).getIzposojeno())&&(u.getGradivo().getKarte().get(i).getLastnosti().getStatus().get(0).getRezervirano())){	
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("| Indeks "+stevec);
					System.out.println(" ----------------------------------------------------- ");	
					stevec++;
					indeksM.add(i);
					u.getGradivo().getKarte().get(i).izpis();
				}	
			}
			for(int i = 0; i<u.getGradivo().getPublikacije().size() ; i++){
				if((u.getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(0).getIzposojeno())&&(u.getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(0).getRezervirano())){	
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("| Indeks "+stevec);
					System.out.println(" ----------------------------------------------------- ");	
					stevec++;
					indeksP.add(i);
					u.getGradivo().getPublikacije().get(i).izpis();
				}	
			}
			for(int i = 0; i<u.getGradivo().getAKnjige().size() ; i++){
				if((u.getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(0).getIzposojeno())&&(u.getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(0).getRezervirano())){	
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("| Indeks "+stevec);
					System.out.println(" ----------------------------------------------------- ");	
					stevec++;
					indeksE.add(i);
					u.getGradivo().getAKnjige().get(i).izpis();
				}
			}
			for(int i = 0; i<u.getGradivo().getEKnjige().size() ; i++){
				if((u.getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(0).getIzposojeno())&&(u.getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(0).getRezervirano())){	
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("| Indeks "+stevec);
					System.out.println(" ----------------------------------------------------- ");	
					stevec++;
					indeksA.add(i);
					u.getGradivo().getEKnjige().get(i).izpis();
				}
			}
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.println(" ----------------------------------------------------- ");
			System.out.println("| Pritisnite (v) odstranitev rezervacije gradiva      |");
			System.out.println("| Pritisnite (i) za izposojo rezerviranih gradiv      |");
			System.out.println("| Pritisnite (x) za nazaj                             |");
			System.out.println(" ----------------------------------------------------- ");
			String vnos=br.readLine().toLowerCase();
			if(vnos.length()==1){
				char znak=vnos.charAt(0);
				switch(znak){
					case 'v':
						System.out.println(" ----------------------------------------------------- ");
						System.out.println("| Vnesite indeks gradiva,                             |");
						System.out.println("| ki ne zelite imeti vec rezerviranega                |");
						System.out.println(" ----------------------------------------------------- ");
						int serijska = 0;
						while(true){
							try{
								int dKnjige = indeksK.size();
								int dKarte = indeksM.size();
								int dPublikacije = indeksP.size();
								int dEKnjige = indeksE.size();
								int dAKnjige = indeksA.size();
								int indeks = Integer.parseInt(br.readLine().trim());
								if(indeks <= dKnjige){
									serijska = u.getGradivo().getKnjige().get(indeks-1).getLastnosti().getStatus().get(0).getSerijskaSt();
									u.getGradivo().getKnjige().remove(indeks-1);
									for(int i = 0;i<getGradivo().getKnjige().size();i++){
										for(int j = 0 ; j < getGradivo().getKnjige().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt() == serijska){
												getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).setRezervirano(false);
											}	
										}	
									}
									break;
								}else if(indeks > dKnjige && indeks <= (dKarte + dKnjige)){
									serijska = u.getGradivo().getKarte().get(indeks-1- dKnjige).getLastnosti().getStatus().get(0).getSerijskaSt();
									u.getGradivo().getKarte().remove(indeks-1- dKnjige);
									for(int i = 0;i<getGradivo().getKarte().size();i++){
										for(int j = 0 ; j < getGradivo().getKarte().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getSerijskaSt() == serijska){
												getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).setRezervirano(false);
											}	
										}	
									}			
									break;
								}else if(indeks > (dKarte + dKnjige) && indeks <= (dKarte + dKnjige + dPublikacije)){
									serijska = u.getGradivo().getPublikacije().get(indeks-1- dKnjige-dKarte).getLastnosti().getStatus().get(0).getSerijskaSt();
									u.getGradivo().getPublikacije().remove(indeks-1- dKnjige-dKarte);
									for(int i = 0;i<getGradivo().getPublikacije().size();i++){
										for(int j = 0 ; j < getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getSerijskaSt() == serijska){
												getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).setRezervirano(false);
											}	
										}	
									}
									break;
								}else if(indeks > (dKarte + dKnjige + dPublikacije) && indeks <= (dKarte + dKnjige + dPublikacije + dAKnjige )){
									serijska = u.getGradivo().getAKnjige().get(indeks-1- dKnjige-dKarte-dPublikacije).getLastnosti().getStatus().get(0).getSerijskaSt();
									u.getGradivo().getAKnjige().remove(indeks-1- dKnjige-dKarte-dPublikacije);
									for(int i = 0;i<getGradivo().getAKnjige().size();i++){
										for(int j = 0 ; j < getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt() == serijska){
												getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).setRezervirano(false);
											}	
										}	
									}
									break;
								}else if(indeks >(dKarte + dKnjige + dPublikacije + dAKnjige ) && indeks <= (dKarte + dKnjige + dPublikacije + dAKnjige +dEKnjige)){
									serijska = u.getGradivo().getEKnjige().get(indeks-1- dKnjige-dKarte-dPublikacije-dAKnjige).getLastnosti().getStatus().get(0).getSerijskaSt();
									u.getGradivo().getEKnjige().remove(indeks-1- dKnjige-dKarte-dPublikacije-dAKnjige);
									for(int i = 0;i<getGradivo().getEKnjige().size();i++){
										for(int j = 0 ; j < getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt() == serijska){
												getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).setRezervirano(false);
											}	
										}	
									}
									break;
								}else{
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Napacen vnos indeksa poskusite ponovno              |");
									System.out.println(" ----------------------------------------------------- ");
								}
							}catch(NumberFormatException e){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Niste vnesli stevila, program se bo vrnil nazaj     |");
									System.out.println(" ----------------------------------------------------- ");
									break;
							}	
						}
						break;
					case 'i':
						System.out.println(" ----------------------------------------------------- ");
						System.out.println("| Vnesite indeks gradiva,                             |");
						System.out.println("| ki ga zelite izposoditi                             |");
						System.out.println(" ----------------------------------------------------- ");
						serijska = 0;
						while(true){
							try{
								int dKnjige = indeksK.size();
								int dKarte = indeksM.size();
								int dPublikacije = indeksP.size();
								int dEKnjige = indeksE.size();
								int dAKnjige = indeksA.size();
								int indeks = Integer.parseInt(br.readLine().trim());
								if(indeks <= dKnjige){
									serijska = u.getGradivo().getKnjige().get(indeks-1).getLastnosti().getStatus().get(0).getSerijskaSt();
									u.getGradivo().getKnjige().get(indeks-1).getLastnosti().getStatus().get(0).setRezervirano(false);
									u.getGradivo().getKnjige().get(indeks-1).getLastnosti().getStatus().get(0).setIzposojeno(false);
									for(int i = 0;i<getGradivo().getKnjige().size();i++){
										for(int j = 0 ; j < getGradivo().getKnjige().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt() == serijska){
												getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).setRezervirano(false);
												getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);

											}	
										}	
									}
									break;
								}else if(indeks > dKnjige && indeks <= (dKarte + dKnjige)){
									serijska = u.getGradivo().getKarte().get(indeks-1- dKnjige).getLastnosti().getStatus().get(0).getSerijskaSt();
									u.getGradivo().getKarte().get(indeks-1- dKnjige).getLastnosti().getStatus().get(0).setRezervirano(false);
									u.getGradivo().getKarte().get(indeks-1- dKnjige).getLastnosti().getStatus().get(0).setIzposojeno(false);
									for(int i = 0;i<getGradivo().getKarte().size();i++){
										for(int j = 0 ; j < getGradivo().getKarte().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getSerijskaSt() == serijska){
												getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).setRezervirano(false);
												getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
											}	
										}	
									}			
									break;
								}else if(indeks > (dKarte + dKnjige) && indeks <= (dKarte + dKnjige + dPublikacije)){
									serijska = u.getGradivo().getPublikacije().get(indeks-1- dKnjige-dKarte).getLastnosti().getStatus().get(0).getSerijskaSt();
									u.getGradivo().getPublikacije().get(indeks-1- dKnjige-dKarte).getLastnosti().getStatus().get(0).setRezervirano(false);
									u.getGradivo().getPublikacije().get(indeks-1- dKnjige-dKarte).getLastnosti().getStatus().get(0).setIzposojeno(false);
									for(int i = 0;i<getGradivo().getPublikacije().size();i++){
										for(int j = 0 ; j < getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getSerijskaSt() == serijska){
												getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).setRezervirano(false);
												getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
											}	
										}	
									}
									break;
								}else if(indeks > (dKarte + dKnjige + dPublikacije) && indeks <= (dKarte + dKnjige + dPublikacije + dAKnjige )){
									serijska = u.getGradivo().getAKnjige().get(indeks-1- dKnjige-dKarte-dPublikacije).getLastnosti().getStatus().get(0).getSerijskaSt();
									u.getGradivo().getAKnjige().get(indeks-1- dKnjige-dKarte-dPublikacije).getLastnosti().getStatus().get(0).setRezervirano(false);
									u.getGradivo().getAKnjige().get(indeks-1- dKnjige-dKarte-dPublikacije).getLastnosti().getStatus().get(0).setIzposojeno(false);
									for(int i = 0;i<getGradivo().getAKnjige().size();i++){
										for(int j = 0 ; j < getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt() == serijska){
												getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).setRezervirano(false);
												getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
											}	
										}	
									}
									break;
								}else if(indeks >(dKarte + dKnjige + dPublikacije + dAKnjige ) && indeks <= (dKarte + dKnjige + dPublikacije + dAKnjige +dEKnjige)){
									serijska = u.getGradivo().getEKnjige().get(indeks-1- dKnjige-dKarte-dPublikacije-dAKnjige).getLastnosti().getStatus().get(0).getSerijskaSt();
									u.getGradivo().getEKnjige().get(indeks-1- dKnjige-dKarte-dPublikacije-dAKnjige).getLastnosti().getStatus().get(0).setRezervirano(false);
									u.getGradivo().getEKnjige().get(indeks-1- dKnjige-dKarte-dPublikacije-dAKnjige).getLastnosti().getStatus().get(0).setIzposojeno(false);
									for(int i = 0;i<getGradivo().getEKnjige().size();i++){
										for(int j = 0 ; j < getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt() == serijska){
												getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).setRezervirano(false);
												getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
											}	
										}	
									}
									break;
								}else{
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Napacen vnos indeksa poskusite ponovno              |");
									System.out.println(" ----------------------------------------------------- ");
								}
							}catch(NumberFormatException e){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Niste vnesli stevila, program se bo vrnil nazaj     |");
									System.out.println(" ----------------------------------------------------- ");
									break;
							}	
						}
						break;
					case 'x':
						return;
					default:
						System.out.println(" ----------------------------------------------------- ");
						System.out.println("| Napacen vnos                                        |");
						System.out.println(" ----------------------------------------------------- ");
						break;
				}

			}
		}	
	public void isciGradivo(Uporabnik u) throws Exception{
		
		ArrayList<Integer>  knjige = new ArrayList<>();
		ArrayList<Integer>  karte = new ArrayList<>();
		ArrayList<Integer>  publikacije = new ArrayList<>();
		ArrayList<Integer>  aKnjige = new ArrayList<>();
		ArrayList<Integer>  eKnjige = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(" ----------------------------------------------------- ");
		System.out.println("| Vtipkajte niz za iskanje gradiva                    |");
		System.out.println(" ----------------------------------------------------- ");
		String vnos = br.readLine().trim().toLowerCase();
		vnos +=" ";
		ArrayList<String> vnosi= new ArrayList<>();
		String niz = "";
		for(int i = 0; i<vnos.length();i++){
			if(vnos.charAt(i) == ' '){
				vnosi.add(niz.trim());
				niz = "";
				
			}else{
				niz+= vnos.charAt(i);
			}
		}
		int stevec = 0;
		novo:
		for(int i = 0; i<getGradivo().getKnjige().size();i++){
			String naslov = getGradivo().getKnjige().get(i).getLastnosti().getNaslov();
			naslov +=" ";
			String glejNiz ="";
			for(int k = 0 ; k< naslov.length();k++){
				if(naslov.charAt(k) == ' '){
					glejNiz = glejNiz.toLowerCase();
					for(int j = 0 ; j<vnosi.size();j++){
						if(glejNiz.equals(vnosi.get(j))){
							knjige.add(i);
							continue novo;
						}
					}
					glejNiz ="";
						
				}else{
					glejNiz += naslov.charAt(k);	
				}	
			}		
		}
		novo1:
		for(int i = 0; i<getGradivo().getAKnjige().size();i++){
			String naslov = getGradivo().getAKnjige().get(i).getLastnosti().getNaslov();
			naslov +=" ";
			String glejNiz ="";
			for(int k = 0 ; k< naslov.length();k++){
				if(naslov.charAt(k) == ' '){
					glejNiz = glejNiz.toLowerCase();
					for(int j = 0 ; j<vnosi.size();j++){
						if(glejNiz.equals(vnosi.get(j))){
							aKnjige.add(i);
							continue novo1;
						}
					}
					glejNiz ="";
						
				}else{
					glejNiz += naslov.charAt(k);	
				}	
			}		
		}
		novo2:
		for(int i = 0; i<getGradivo().getKarte().size();i++){
			String naslov = getGradivo().getKarte().get(i).getLastnosti().getNaslov();
			naslov +=" ";
			String glejNiz ="";
			for(int k = 0 ; k< naslov.length();k++){
				if(naslov.charAt(k) == ' '){
					glejNiz = glejNiz.toLowerCase();
					for(int j = 0 ; j<vnosi.size();j++){
						if(glejNiz.equals(vnosi.get(j))){
							karte.add(i);
							continue novo2;
						}
					}
					glejNiz ="";
						
				}else{
					glejNiz += naslov.charAt(k);	
				}	
			}		
		}
		novo3:
		for(int i = 0; i<getGradivo().getPublikacije().size();i++){
			String naslov = getGradivo().getPublikacije().get(i).getLastnosti().getNaslov();
			naslov +=" ";
			String glejNiz ="";
			for(int k = 0 ; k< naslov.length();k++){
				if(naslov.charAt(k) == ' '){
					glejNiz = glejNiz.toLowerCase();
					for(int j = 0 ; j<vnosi.size();j++){
						if(glejNiz.equals(vnosi.get(j))){
							publikacije.add(i);
							continue novo3;
						}
					}
					glejNiz ="";
						
				}else{
					glejNiz += naslov.charAt(k);	
				}	
			}		
		}
		novo4:
		for(int i = 0; i<getGradivo().getEKnjige().size();i++){
			String naslov = getGradivo().getEKnjige().get(i).getLastnosti().getNaslov();
			naslov +=" ";
			String glejNiz ="";
			for(int k = 0 ; k< naslov.length();k++){
				if(naslov.charAt(k) == ' '){
					glejNiz = glejNiz.toLowerCase();
					for(int j = 0 ; j<vnosi.size();j++){
						if(glejNiz.equals(vnosi.get(j))){
							eKnjige.add(i);
							continue novo4;
						}
					}
					glejNiz ="";
						
				}else{
					glejNiz += naslov.charAt(k);	
				}	
			}		
		}
		
		
		int steviloNajdenih = knjige.size() + karte.size() + publikacije.size() + aKnjige.size() + eKnjige.size();
		int stevecS = 0;
		konec:
		novaStran:
		while(stevec< steviloNajdenih){
			stevec++;
			System.out.println(" ----------------------------------------------------- ");
			System.out.println("| Zaporedna stevilka " + stevec);
			if(stevec <= knjige.size()){
				getGradivo().getKnjige().get(knjige.get(stevec-1)).izpis();
			}else if(stevec > knjige.size() && stevec <= (knjige.size()+karte.size())){
				getGradivo().getKarte().get(karte.get(stevec-1-knjige.size())).izpis();
			}else if(stevec > (knjige.size()+karte.size()) && stevec <= (knjige.size()+karte.size()+publikacije.size())){
				getGradivo().getPublikacije().get(publikacije.get(stevec-1-knjige.size()-karte.size())).izpis();
			}else if(stevec > (knjige.size()+karte.size()+publikacije.size()) && stevec <= (knjige.size()+karte.size()+publikacije.size()+ aKnjige.size())){
				getGradivo().getAKnjige().get(aKnjige.get(stevec-1-knjige.size()-karte.size()-publikacije.size())).izpis();
			}else{
				getGradivo().getEKnjige().get(eKnjige.get(stevec-1-knjige.size()-karte.size()-publikacije.size()-aKnjige.size())).izpis();
			}
				
			if(stevec%5 == 0  || stevec == steviloNajdenih){
				stevecS++;
				System.out.println("| Stran "+stevecS);
				System.out.println(" ----------------------------------------------------- ");
				
				System.out.println("| Pritisnite (i) za izposojo gradiva                  |");
				System.out.println("| Pritisnite (r) za rezervacijo gradiva               |");
				if(stevec > 5){
					System.out.println("| Pritisnite (b) za prejsnjo stran                    |");
				}if(stevec != steviloNajdenih){
					System.out.println("| Pritisnite (n) za naslednjo stran                   |");
				}	
				System.out.println("| Pritisnite (x) za zakljucitev iskanja               |");	
				System.out.println(" ----------------------------------------------------- ");
				String input = br.readLine().toLowerCase();
				if(input.length() == 1){
					char znak = input.charAt(0);
					switch(znak){
						case 'b':
							if(stevec <= 5){
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Napacen vnos                                        |");
								System.out.println(" ----------------------------------------------------- ");
								break;
							}
							stevec -= 10;
							stevecS -= 2;
							if(stevecS < 2){
								stevecS=0;
							}
							if(stevec < 0){
								stevec = 0;
							}
							break;
						case 'n':
							if(stevec == steviloNajdenih){
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Napacen vnos                                        |");
								System.out.println(" ----------------------------------------------------- ");
								break;
							}
							continue novaStran;
						case 'i':
							System.out.println(" ----------------------------------------------------- ");
							System.out.println("| Vpisite indeks gradiva na strani                    |");
							System.out.println(" ----------------------------------------------------- ");
							niNaVoljo:
							
							dodaj:
							while(true){
								try{
									int g = Integer.parseInt(br.readLine().trim());
									if(g <= knjige.size()){
										int i = knjige.get(g-1);
										Knjiga k = new Knjiga();
										Status s = new Status();
										rez:
										for(int b = 0; b < getGradivo().getKnjige().get(i).getLastnosti().getStatus().size();b++){
											for(int r = 0; r < u.getGradivo().getKnjige().size() ; r++){
												for(int p = 0; p < u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().size();p++){
													if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
														if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}	
													}else if(!(u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno())){
														if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}	
													}else if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
														System.out.println("| Gradivo imate rezervirano |");
														System.out.println("| si ga zelite izposoditi? |");
														while(true){
														System.out.println("| vtipkajte (d) za izposojo ali (n) da si ne izposodite gradiva |");
														String dane = br.readLine().toLowerCase().trim();
														if(dane.length() == 1){
															char dn = dane.charAt(0);
															switch(dn){
																case 'd':
																	getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).setRezervirano(false);
																	u.getGradivo().getKnjige().remove(r);
																	break rez;
																case 'n':
																	break konec;
																default:
																	System.out.println(" ----------------------------------------------------- ");
																	System.out.println("| Napacen vnos poskusite ponovno                      |");
																	System.out.println(" ----------------------------------------------------- ");		
															
															}
														}else{
															System.out.println(" ----------------------------------------------------- ");
															System.out.println("| Napacen vnos poskusite ponovno                      |");
															System.out.println(" ----------------------------------------------------- ");
														}
														
														}
													}
												}		
											}
											
										}
										k.getLastnosti().setNaslov(getGradivo().getKnjige().get(i).getLastnosti().getNaslov());
										k.getLastnosti().setAvtor(getGradivo().getKnjige().get(i).getLastnosti().getAvtor());
										k.getLastnosti().setZalozba(getGradivo().getKnjige().get(i).getLastnosti().getZalozba());
										k.getLastnosti().setSkupina(getGradivo().getKnjige().get(i).getLastnosti().getSkupina());
										for(int j = 0 ; j <getGradivo().getKnjige().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
												if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
													if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo ni na voljo                                 |");
														System.out.println(" ----------------------------------------------------- ");
														break konec;
													}
													continue;
												}
												s.setSerijskaSt(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
												s.setIzposojeno(false);
												getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).setRezervirano(false);
												getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
												k.getLastnosti().getStatus().add(s);
												u.getGradivo().getKnjige().add(k);
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" izposojeno");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}
											if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo ni na voljo                                 |");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}	
										}

									}else if(g > knjige.size() && g <= (knjige.size()+karte.size())){
										int i = karte.get(g-1-knjige.size());
										Karta k = new Karta();
										Status s = new Status();
										rez:
										for(int b = 0; b < getGradivo().getKarte().get(i).getLastnosti().getStatus().size();b++){
											for(int r = 0; r < u.getGradivo().getKarte().size() ; r++){
												for(int p = 0; p < u.getGradivo().getKarte().get(r).getLastnosti().getStatus().size();p++){
													if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
														if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}
													}else if(!(u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getIzposojeno())){
														if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}		
													}else if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
														System.out.println("| Gradivo imate rezervirano |");
														System.out.println("| si ga zelite izposoditi? |");
														while(true){
														System.out.println("| vtipkajte (d) za izposojo ali (n) da si ne izposodite gradiva |");
														String dane = br.readLine().toLowerCase().trim();
														if(dane.length() == 1){
															char dn = dane.charAt(0);
															switch(dn){
																case 'd':
																	getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).setRezervirano(false);
																	u.getGradivo().getKarte().remove(r);
																	break rez;
																case 'n':
																	break konec;
																default:
																	System.out.println(" ----------------------------------------------------- ");
																	System.out.println("| Napacen vnos poskusite ponovno                      |");
																	System.out.println(" ----------------------------------------------------- ");		
															
															}
														}else{
															System.out.println(" ----------------------------------------------------- ");
															System.out.println("| Napacen vnos poskusite ponovno                      |");
															System.out.println(" ----------------------------------------------------- ");
														}
														
														}
													}
												}		
											}
											
										}
										k.getLastnosti().setNaslov(getGradivo().getKarte().get(i).getLastnosti().getNaslov());
										k.getLastnosti().setAvtor(getGradivo().getKarte().get(i).getLastnosti().getAvtor());
										k.getLastnosti().setZalozba(getGradivo().getKarte().get(i).getLastnosti().getZalozba());
										k.getLastnosti().setSkupina(getGradivo().getKarte().get(i).getLastnosti().getSkupina());
										for(int j = 0 ; j <getGradivo().getKarte().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
												if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
													if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo ni na voljo                                 |");
														System.out.println(" ----------------------------------------------------- ");
														break;
													}
													continue;
												}
												s.setSerijskaSt(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
												s.setIzposojeno(false);
												getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
												k.getLastnosti().getStatus().add(s);
												u.getGradivo().getKarte().add(k);
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" izposojeno");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}
											if((j+1)== getGradivo().getKarte().get(i).getLastnosti().getStatus().size()){
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo ni na voljo                                 |");
												System.out.println(" ----------------------------------------------------- ");
											}	
										}

									}else if(g > (knjige.size()+karte.size()) && g <= (knjige.size()+karte.size()+publikacije.size())){
										int i = publikacije.get(g-1-knjige.size()-karte.size());
										Publikacija k = new Publikacija();
										Status s = new Status();
										rez:
										for(int b = 0; b < getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size();b++){
											for(int r = 0; r < u.getGradivo().getPublikacije().size() ; r++){
												for(int p = 0; p < u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().size();p++){
													if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
														if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}
													}else if(!(u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getIzposojeno())){
														if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}
													}else if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
														System.out.println("| Gradivo imate rezervirano |");
														System.out.println("| si ga zelite izposoditi? |");
														while(true){
														System.out.println("| vtipkajte (d) za izposojo ali (n) da si ne izposodite gradiva |");
														String dane = br.readLine().toLowerCase().trim();
														if(dane.length() == 1){
															char dn = dane.charAt(0);
															switch(dn){
																case 'd':
																	getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).setRezervirano(false);
																	u.getGradivo().getPublikacije().remove(r);
																	break rez;
																case 'n':
																	break konec;
																default:
																	System.out.println(" ----------------------------------------------------- ");
																	System.out.println("| Napacen vnos poskusite ponovno                      |");
																	System.out.println(" ----------------------------------------------------- ");		
															
															}
														}else{
															System.out.println(" ----------------------------------------------------- ");
															System.out.println("| Napacen vnos poskusite ponovno                      |");
															System.out.println(" ----------------------------------------------------- ");
														}
														
														}
													}
												}		
											}
											
										}
										k.getLastnosti().setNaslov(getGradivo().getPublikacije().get(i).getLastnosti().getNaslov());
										k.getLastnosti().setAvtor(getGradivo().getPublikacije().get(i).getLastnosti().getAvtor());
										k.getLastnosti().setZalozba(getGradivo().getPublikacije().get(i).getLastnosti().getZalozba());
										k.getLastnosti().setSkupina(getGradivo().getPublikacije().get(i).getLastnosti().getSkupina());
										for(int j = 0 ; j <getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
												if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
													if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo ni na voljo                                 |");
														System.out.println(" ----------------------------------------------------- ");
														break;
													}
													continue;
												}
												s.setSerijskaSt(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
												s.setIzposojeno(false);
												getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
												k.getLastnosti().getStatus().add(s);
												u.getGradivo().getPublikacije().add(k);
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" izposojeno");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}
											if((j+1)== getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size()){
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo ni na voljo                                 |");
												System.out.println(" ----------------------------------------------------- ");
											}	
										}

									}else if(g > (knjige.size()+karte.size()+publikacije.size()) && g <= (knjige.size()+karte.size()+publikacije.size()+ aKnjige.size())){
										int i = aKnjige.get(g-1-knjige.size()-karte.size()-publikacije.size());
										AKnjiga k = new AKnjiga();
										Status s = new Status();
										rez:
										for(int b = 0; b < getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size();b++){
											for(int r = 0; r < u.getGradivo().getAKnjige().size() ; r++){
												for(int p = 0; p < u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().size();p++){
													if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
														if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}	
													}else if(!(u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno())){
														if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}
													}else if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
														System.out.println("| Gradivo imate rezervirano |");
														System.out.println("| si ga zelite izposoditi? |");
														while(true){
														System.out.println("| vtipkajte (d) za izposojo ali (n) da si ne izposodite gradiva |");
														String dane = br.readLine().toLowerCase().trim();
														if(dane.length() == 1){
															char dn = dane.charAt(0);
															switch(dn){
																case 'd':
																	getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).setRezervirano(false);
																	u.getGradivo().getAKnjige().remove(r);
																	break rez;
																case 'n':
																	break konec;
																default:
																	System.out.println(" ----------------------------------------------------- ");
																	System.out.println("| Napacen vnos poskusite ponovno                      |");
																	System.out.println(" ----------------------------------------------------- ");		
															
															}
														}else{
															System.out.println(" ----------------------------------------------------- ");
															System.out.println("| Napacen vnos poskusite ponovno                      |");
															System.out.println(" ----------------------------------------------------- ");
														}
														
														}
													}
												}		
											}
											
										}
										k.getLastnosti().setNaslov(getGradivo().getAKnjige().get(i).getLastnosti().getNaslov());
										k.getLastnosti().setAvtor(getGradivo().getAKnjige().get(i).getLastnosti().getAvtor());
										k.getLastnosti().setZalozba(getGradivo().getAKnjige().get(i).getLastnosti().getZalozba());
										k.getLastnosti().setSkupina(getGradivo().getAKnjige().get(i).getLastnosti().getSkupina());
										for(int j = 0 ; j <getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
												if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
													if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo ni na voljo                                 |");
														System.out.println(" ----------------------------------------------------- ");
														break;
													}
													continue;
												}
												s.setSerijskaSt(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
												s.setIzposojeno(false);
												getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
												k.getLastnosti().getStatus().add(s);
												u.getGradivo().getAKnjige().add(k);
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" izposojeno");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}
											if((j+1)== getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size()){
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo ni na voljo                                 |");
												System.out.println(" ----------------------------------------------------- ");
											}	
										}

									}else if(g > (knjige.size()+karte.size()+publikacije.size()+ aKnjige.size()) && g <= (knjige.size()+karte.size()+publikacije.size()+ aKnjige.size()+eKnjige.size())){
										int i = eKnjige.get(g-1-knjige.size()-karte.size()-publikacije.size());
										EKnjiga k = new EKnjiga();
										Status s = new Status();
										rez:
										for(int b = 0; b < getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size();b++){
											for(int r = 0; r < u.getGradivo().getEKnjige().size() ; r++){
												for(int p = 0; p < u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().size();p++){
													if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
														if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}
													}else if(!(u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno())){
														if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}
													}else if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
														System.out.println("| Gradivo imate rezervirano |");
														System.out.println("| si ga zelite izposoditi? |");
														while(true){
														System.out.println("| vtipkajte (d) za izposojo ali (n) da si ne izposodite gradiva |");
														String dane = br.readLine().toLowerCase().trim();
														if(dane.length() == 1){
															char dn = dane.charAt(0);
															switch(dn){
																case 'd':
																	getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).setRezervirano(false);
																	u.getGradivo().getEKnjige().remove(r);
																	break rez;
																case 'n':
																	break konec;
																default:
																	System.out.println(" ----------------------------------------------------- ");
																	System.out.println("| Napacen vnos poskusite ponovno                      |");
																	System.out.println(" ----------------------------------------------------- ");		
															
															}
														}else{
															System.out.println(" ----------------------------------------------------- ");
															System.out.println("| Napacen vnos poskusite ponovno                      |");
															System.out.println(" ----------------------------------------------------- ");
														}
														
														}
													}
												}		
											}
											
										}
										k.getLastnosti().setNaslov(getGradivo().getEKnjige().get(i).getLastnosti().getNaslov());
										k.getLastnosti().setAvtor(getGradivo().getEKnjige().get(i).getLastnosti().getAvtor());
										k.getLastnosti().setZalozba(getGradivo().getEKnjige().get(i).getLastnosti().getZalozba());
										k.getLastnosti().setSkupina(getGradivo().getEKnjige().get(i).getLastnosti().getSkupina());
										for(int j = 0 ; j <getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
												if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
													if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo ni na voljo                                 |");
														System.out.println(" ----------------------------------------------------- ");
														break;
													}
													continue;
												}
												s.setSerijskaSt(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
												s.setIzposojeno(false);
												getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
												k.getLastnosti().getStatus().add(s);
												u.getGradivo().getEKnjige().add(k);
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" izposojeno");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}
											if((j+1)== getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size()){
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo ni na voljo                                 |");
												System.out.println(" ----------------------------------------------------- ");
											}	
										}
										
									}else{
										break konec;
									}
									
									
								} catch(NumberFormatException e){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Napacen vnos poskusite ponovno                      |");
									System.out.println(" ----------------------------------------------------- ");
									
								}catch(IndexOutOfBoundsException e ){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Knjiga s tem indeksom ne obstaja, poskusite ponovno |");
									System.out.println(" ----------------------------------------------------- ");
									
								}
							
								
							}
						case 'r':
						System.out.println(" ----------------------------------------------------- ");
						System.out.println("| Vpisite indeks gradiva na strani                    |");
						System.out.println(" ----------------------------------------------------- ");
						niNaVoljo:
						
						dodaj:
						while(true){
							try{
								int g = Integer.parseInt(br.readLine().trim());
								if(g <= knjige.size()){
									int i = knjige.get(g-1);
									Knjiga k = new Knjiga();
									Status s = new Status();
									for(int b = 0; b < getGradivo().getKnjige().get(i).getLastnosti().getStatus().size();b++){
										for(int r = 0; r < u.getGradivo().getKnjige().size() ; r++){
											for(int p = 0; p < u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().size();p++){
												if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
													if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
														System.out.println("| Gradivo imate ze izposojeno |");
														break konec;
													}	
												}else if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
													if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
														System.out.println("| Gradivo imate ze rezervirano |");
														break konec;
													}
												}
											}		
										}	
									}
									k.getLastnosti().setNaslov(getGradivo().getKnjige().get(i).getLastnosti().getNaslov());
									k.getLastnosti().setAvtor(getGradivo().getKnjige().get(i).getLastnosti().getAvtor());
									k.getLastnosti().setZalozba(getGradivo().getKnjige().get(i).getLastnosti().getZalozba());
									k.getLastnosti().setSkupina(getGradivo().getKnjige().get(i).getLastnosti().getSkupina());
									for(int j = 0 ; j <getGradivo().getKnjige().get(i).getLastnosti().getStatus().size();j++){
										if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
											if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
												if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
													System.out.println(" ----------------------------------------------------- ");
													System.out.println("| Gradivo ni na voljo                                 |");
													System.out.println(" ----------------------------------------------------- ");
													break;
												}
												continue;
											}
											s.setSerijskaSt(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
											s.setRezervirano(true);
											s.setIzposojeno(true);
											getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).setRezervirano(true);
											k.getLastnosti().getStatus().add(s);
											u.getGradivo().getKnjige().add(k);
											System.out.println(" ----------------------------------------------------- ");
											System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" rezervirano ");
											System.out.println(" ----------------------------------------------------- ");
											break konec;
										}
										if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
											System.out.println(" ----------------------------------------------------- ");
											System.out.println("| Gradivo ni na voljo                                 |");
											System.out.println(" ----------------------------------------------------- ");
										}	
									}
								}else if(g > knjige.size() && g <= (knjige.size()+karte.size())){
									int i = karte.get(g-1-knjige.size());
									Karta k = new Karta();
									Status s = new Status();
									for(int b = 0; b < getGradivo().getKarte().get(i).getLastnosti().getStatus().size();b++){
										for(int r = 0; r < u.getGradivo().getKarte().size() ; r++){
											for(int p = 0; p < u.getGradivo().getKarte().get(r).getLastnosti().getStatus().size();p++){
												if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
													if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
														System.out.println("| Gradivo imate ze izposojeno |");
														break konec;
													}	
												}else if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
													if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
														System.out.println("| Gradivo imate ze rezervirano |");
														break konec;
													}
												}
											}		
										}	
									}
									k.getLastnosti().setNaslov(getGradivo().getKarte().get(i).getLastnosti().getNaslov());
									k.getLastnosti().setAvtor(getGradivo().getKarte().get(i).getLastnosti().getAvtor());
									k.getLastnosti().setZalozba(getGradivo().getKarte().get(i).getLastnosti().getZalozba());
									k.getLastnosti().setSkupina(getGradivo().getKarte().get(i).getLastnosti().getSkupina());
									for(int j = 0 ; j <getGradivo().getKarte().get(i).getLastnosti().getStatus().size();j++){
										if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
											if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
												if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
													System.out.println(" ----------------------------------------------------- ");
													System.out.println("| Gradivo ni na voljo                                 |");
													System.out.println(" ----------------------------------------------------- ");
													break;
												}
												continue;
											}
											s.setSerijskaSt(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
											s.setRezervirano(true);
											s.setIzposojeno(true);
											getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).setRezervirano(true);
											k.getLastnosti().getStatus().add(s);
											u.getGradivo().getKarte().add(k);
											System.out.println(" ----------------------------------------------------- ");
											System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" rezervirano ");
											System.out.println(" ----------------------------------------------------- ");
											break konec;
										}
										if((j+1)== getGradivo().getKarte().get(i).getLastnosti().getStatus().size()){
											System.out.println(" ----------------------------------------------------- ");
											System.out.println("| Gradivo ni na voljo                                 |");
											System.out.println(" ----------------------------------------------------- ");
										}	
									}
								}else if(g > (knjige.size()+karte.size()) && g <= (knjige.size()+karte.size()+publikacije.size())){
									int i = publikacije.get(g-1-knjige.size()-karte.size());
									Publikacija k = new Publikacija();
									Status s = new Status();
									for(int b = 0; b < getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size();b++){
										for(int r = 0; r < u.getGradivo().getPublikacije().size() ; r++){
											for(int p = 0; p < u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().size();p++){
												if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
													if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
														System.out.println("| Gradivo imate ze izposojeno |");
														break konec;
													}	
												}else if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
													if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
														System.out.println("| Gradivo imate ze rezervirano |");
														break konec;
													}
												}
											}		
										}	
									}
									k.getLastnosti().setNaslov(getGradivo().getPublikacije().get(i).getLastnosti().getNaslov());
									k.getLastnosti().setAvtor(getGradivo().getPublikacije().get(i).getLastnosti().getAvtor());
									k.getLastnosti().setZalozba(getGradivo().getPublikacije().get(i).getLastnosti().getZalozba());
									k.getLastnosti().setSkupina(getGradivo().getPublikacije().get(i).getLastnosti().getSkupina());
									for(int j = 0 ; j <getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size();j++){
										if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
											if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
												if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
													System.out.println(" ----------------------------------------------------- ");
													System.out.println("| Gradivo ni na voljo                                 |");
													System.out.println(" ----------------------------------------------------- ");
													break;
												}
												continue;
											}
											s.setSerijskaSt(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
											s.setRezervirano(true);
											s.setIzposojeno(true);
											getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).setRezervirano(true);
											k.getLastnosti().getStatus().add(s);
											u.getGradivo().getPublikacije().add(k);
											System.out.println(" ----------------------------------------------------- ");
											System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" rezervirano ");
											System.out.println(" ----------------------------------------------------- ");
											break konec;
										}
										if((j+1)== getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size()){
											System.out.println(" ----------------------------------------------------- ");
											System.out.println("| Gradivo ni na voljo                                 |");
											System.out.println(" ----------------------------------------------------- ");
										}	
									}										
								}else if(g > (knjige.size()+karte.size()+publikacije.size()) && g <= (knjige.size()+karte.size()+publikacije.size()+ aKnjige.size())){
									int i = aKnjige.get(g-1-knjige.size()-karte.size()-publikacije.size());
									AKnjiga k = new AKnjiga();
									Status s = new Status();
									for(int b = 0; b < getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size();b++){
										for(int r = 0; r < u.getGradivo().getAKnjige().size() ; r++){
											for(int p = 0; p < u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().size();p++){
												if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
													if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
														System.out.println("| Gradivo imate ze izposojeno |");
														break konec;
													}	
												}else if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
													if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
														System.out.println("| Gradivo imate ze rezervirano |");
														break konec;
													}
												}
											}		
										}	
									}
									k.getLastnosti().setNaslov(getGradivo().getAKnjige().get(i).getLastnosti().getNaslov());
									k.getLastnosti().setAvtor(getGradivo().getAKnjige().get(i).getLastnosti().getAvtor());
									k.getLastnosti().setZalozba(getGradivo().getAKnjige().get(i).getLastnosti().getZalozba());
									k.getLastnosti().setSkupina(getGradivo().getAKnjige().get(i).getLastnosti().getSkupina());
									for(int j = 0 ; j <getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size();j++){
										if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
											if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
												if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
													System.out.println(" ----------------------------------------------------- ");
													System.out.println("| Gradivo ni na voljo                                 |");
													System.out.println(" ----------------------------------------------------- ");
													break;
												}
												continue;
											}
											s.setSerijskaSt(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
											s.setRezervirano(true);
											s.setIzposojeno(true);
											getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).setRezervirano(true);
											k.getLastnosti().getStatus().add(s);
											u.getGradivo().getAKnjige().add(k);
											System.out.println(" ----------------------------------------------------- ");
											System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" rezervirano ");
											System.out.println(" ----------------------------------------------------- ");
											break konec;
										}
										if((j+1)== getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size()){
											System.out.println(" ----------------------------------------------------- ");
											System.out.println("| Gradivo ni na voljo                                 |");
											System.out.println(" ----------------------------------------------------- ");
										}	
									}
								}else if(g > (knjige.size()+karte.size()+publikacije.size()+ aKnjige.size()) && g <= (knjige.size()+karte.size()+publikacije.size()+ aKnjige.size()+eKnjige.size())){
									int i = eKnjige.get(g-1-knjige.size()-karte.size()-publikacije.size());
									EKnjiga k = new EKnjiga();
									Status s = new Status();
									for(int b = 0; b < getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size();b++){
										for(int r = 0; r < u.getGradivo().getEKnjige().size() ; r++){
											for(int p = 0; p < u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().size();p++){
												if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
													if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
														System.out.println("| Gradivo imate ze izposojeno |");
														break konec;
													}	
												}else if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
													if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
														System.out.println("| Gradivo imate ze rezervirano |");
														break konec;
													}
												}
											}		
										}	
									}
									k.getLastnosti().setNaslov(getGradivo().getEKnjige().get(i).getLastnosti().getNaslov());
									k.getLastnosti().setAvtor(getGradivo().getEKnjige().get(i).getLastnosti().getAvtor());
									k.getLastnosti().setZalozba(getGradivo().getEKnjige().get(i).getLastnosti().getZalozba());
									k.getLastnosti().setSkupina(getGradivo().getEKnjige().get(i).getLastnosti().getSkupina());
									for(int j = 0 ; j <getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size();j++){
										if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
											if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
												if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
													System.out.println(" ----------------------------------------------------- ");
													System.out.println("| Gradivo ni na voljo                                 |");
													System.out.println(" ----------------------------------------------------- ");
													break;
												}
												continue;
											}
											s.setSerijskaSt(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
											s.setRezervirano(true);
											s.setIzposojeno(true);
											getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).setRezervirano(true);
											k.getLastnosti().getStatus().add(s);
											u.getGradivo().getEKnjige().add(k);
											System.out.println(" ----------------------------------------------------- ");
											System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" rezervirano ");
											System.out.println(" ----------------------------------------------------- ");
											break konec;
										}
										if((j+1)== getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size()){
											System.out.println(" ----------------------------------------------------- ");
											System.out.println("| Gradivo ni na voljo                                 |");
											System.out.println(" ----------------------------------------------------- ");
										}	
									}
									
								}else{
									break konec;
								}
								} catch(NumberFormatException e){
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Napacen vnos poskusite ponovno                      |");
								System.out.println(" ----------------------------------------------------- ");
								
							}catch(IndexOutOfBoundsException e ){
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Knjiga s tem indeksom ne obstaja, poskusite ponovno |");
								System.out.println(" ----------------------------------------------------- ");
								
							}
						}			
						case 'x':
							return;
						default:
							System.out.println(" ----------------------------------------------------- ");
							System.out.println("| Napacen vnos                                        |");
							System.out.println(" ----------------------------------------------------- ");
							break;
								
					}
				
				}else{
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("| Napacen vnos poskusite ponovno                      |");
					System.out.println(" ----------------------------------------------------- ");
				}
			}
		}
		
	}		
				
	public Uporabnik vpis() throws Exception{
		BufferedReader br  = new BufferedReader( new InputStreamReader(System.in));
		System.out.println(" ----------------------------------------------------- ");
		System.out.println("| vnesite svoje uporabnisko ime                       |");
		System.out.print("| ");
		String ime = br.readLine();
		System.out.println(" ----------------------------------------------------- ");  
		boolean jeUporabnik = false;
		Uporabnik u = new Uporabnik();
		if(getUporabniki().size() == 0){
			return null;
		}
		for(int i = 0; i<getUporabniki().size();i++){
			if(ime.equals(getUporabniki().get(i).getIme())){
				jeUporabnik = true;
				int stevec = 0;
				for(int j = 0; j < 3 ; j++){
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("| vnesite svoje geslo                                 |");
					System.out.print("| ");
					String geslo = br.readLine();	
					System.out.println(" ----------------------------------------------------- ");  
					if(geslo.equals(getUporabniki().get(i).getGeslo())){
						System.out.println(" ----------------------------------------------------- ");
						System.out.println("| Dobrodosli                                          |");
						System.out.println(" ----------------------------------------------------- ");
						u = getUporabniki().get(i);
						getUporabniki().remove(i);
						break;
					}
					stevec++;
				}
				if(stevec == 3){
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("| Preveckrat ste vnesli napacno geslo                 |");
					System.out.println(" ----------------------------------------------------- ");
					return null;
				}
				
				
			}
		}	
		if(!jeUporabnik){
			System.out.println(" ----------------------------------------------------- ");
			System.out.println("| Uporabnisko ime ne obstaja                          |");
			System.out.println(" ----------------------------------------------------- ");
			return null;
		}
		return u;
	}
			
	public Uporabnik vstop()throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Uporabnik u = new Uporabnik();
		System.out.println(" ----------------------------------------------------- ");
		System.out.println("| Dobrodosli v knjiznico                              |");
		while(true){
		System.out.println(" ----------------------------------------------------- ");
		System.out.println("| Pritisnite (p) za prijavo                           |");
		System.out.println("| Pritisnite (r) za registracijo                      |");
		System.out.println("| Pritisnite (x) za izhod                             |");
		System.out.println(" ----------------------------------------------------- ");
		String vnos = br.readLine().trim().toLowerCase();
		if(vnos.length() == 1){
			char znak = vnos.charAt(0);
			switch(znak){
				case 'p':
					u = vpis();
					if(u == null){
						System.out.println(" ----------------------------------------------------- ");
						System.out.println("| Napacen vnos poskusite ponovno                      |");
						System.out.println(" ----------------------------------------------------- ");
						u = new Uporabnik();
						break;	
					}else{
					return u;
					}
				case 'r':
					u.vnosImena();
					konec:
					while(true){
						if(getUporabniki().size() == 0){
							break;
						}
						for(int i = 0; i< getUporabniki().size();i++){
							if(u.getIme().equals(getUporabniki().get(i).getIme())){
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Uporabnisko ime ze obstaja. Izberite novo ime.      |");
								System.out.println(" ----------------------------------------------------- ");
								u.vnosImena();
							}else{
							break konec;
							}
						}
					}
					u.vnosGesla();
					return u;
				case 'x':
					return null;
				default:
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("| Napacen vnos poskusite ponovno                      |");
					System.out.println(" ----------------------------------------------------- ");
					
			
			
			}
		}else{
			System.out.println(" ----------------------------------------------------- ");
			System.out.println("| Napacen vnos poskusite ponovno                      |");
			System.out.println(" ----------------------------------------------------- ");
		}
		
	}
	}	
		
	public void dodajGradivo() throws Exception{
		boolean pravilniVnos = false;
		while(!pravilniVnos){
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(" ----------------------------------------------------- ");
			System.out.println("| Pritisnite (k) da dodate knjigo                     |");
			System.out.println("| Pritisnite (m) da dodate karto                      |");
			System.out.println("| Pritisnite (p) da dodate periodicno publikacijo     |");
			System.out.println("| Pritisnite (e) da dodate e-Knjigo                   |");
			System.out.println("| Pritisnite (a) da dodate audioknjigo                |");
			System.out.println("| Pritisnite (x) za vrnitev nazaj                     |");
			System.out.println(" ----------------------------------------------------- ");
			System.out.print("| ");
			String vnos = br.readLine();
			if(vnos.length() == 1){
				char znak = vnos.charAt(0);
				switch(znak){
					case 'k':
						getGradivo().dodajKnjiga();
						pravilniVnos = true;						
						break;
					case 'm':
						getGradivo().dodajKarta();
						pravilniVnos = true;
						break;
					case 'p':
						getGradivo().dodajPublikacija();
						pravilniVnos = true;
						break;
					case 'a':
						getGradivo().dodajAKnjiga();
						pravilniVnos = true;
						break;
					case 'e':	
						getGradivo().dodajEKnjiga();
						pravilniVnos = true;
					case 'x':
						pravilniVnos = true;
						return ;
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
	}
	
	public void gradivoIzDatoteke() throws Exception{
			try{	
				Gradivo g = new Gradivo();
				BufferedReader br = new BufferedReader(new FileReader("Gradivo.txt"));
				String beri = br.readLine().trim();
				if(beri.equals("#Gradivo")){
					beri = br.readLine().trim();
					while(br.ready()){	
						Knjiga k = new Knjiga();
						Karta m = new Karta();
						Publikacija p = new Publikacija();
						AKnjiga a = new AKnjiga();
						EKnjiga e = new EKnjiga();
						
						switch(beri){
							case "#Roman":
						
								k = new Roman();
								k.getLastnosti().setNaslov(br.readLine().trim());
								k.getLastnosti().setAvtor(br.readLine().trim());
								k.getLastnosti().setZalozba(br.readLine().trim());
								while(br.ready()){
									beri = br.readLine().trim();
									if(beri.equals("#Status")){
										Status s = new Status();
										s.setSerijskaSt(Integer.parseInt(br.readLine().trim()));
										br.readLine();
										s.setIzposojeno(true);
										k.getLastnosti().getStatus().add(s);
									}else{
									break;
									}
								}
								while(br.ready()){
									
									if(beri.equals("#Skupina")){
										k.getLastnosti().getSkupina().add(br.readLine().trim());
										beri = br.readLine().trim();									
									}else{
										break;
									}
								}
								g.getKnjige().add(k);
								
								
								break;					
							case "#Strip":
								k = new Strip();
								k.getLastnosti().setNaslov(br.readLine().trim());
								k.getLastnosti().setAvtor(br.readLine().trim());
								k.getLastnosti().setZalozba(br.readLine().trim());
								while(br.ready()){
									beri = br.readLine().trim();
									if(beri.equals("#Status")){
										Status s = new Status();
										s.setSerijskaSt(Integer.parseInt(br.readLine().trim()));
										br.readLine();
										s.setIzposojeno(true);
										k.getLastnosti().getStatus().add(s);
									}else{
									break;
									}
								}
								while(br.ready()){
									
									if(beri.equals("#Skupina")){
										k.getLastnosti().getSkupina().add(br.readLine().trim());
										beri = br.readLine().trim();	
									}else{
										break;
									}
								}
								g.getKnjige().add(k);
								
								break;	
							case "#Prirocnik":
								k = new Prirocnik();
								k.getLastnosti().setNaslov(br.readLine().trim());
								k.getLastnosti().setAvtor(br.readLine().trim());
								k.getLastnosti().setZalozba(br.readLine().trim());
								while(br.ready()){
									beri = br.readLine().trim();
									if(beri.equals("#Status")){
										Status s = new Status();
										s.setSerijskaSt(Integer.parseInt(br.readLine().trim()));
										br.readLine();
										s.setIzposojeno(true);
										k.getLastnosti().getStatus().add(s);
									}else{
									break;
									}
								}
								while(br.ready()){
									
									if(beri.equals("#Skupina")){
										k.getLastnosti().getSkupina().add(br.readLine().trim());
										beri = br.readLine().trim();	
									}else{
										break;
									}
								}
								g.getKnjige().add(k);
							
								break;
							case "#Slovar":
								k = new Slovar();
								k.getLastnosti().setNaslov(br.readLine().trim());
								k.getLastnosti().setAvtor(br.readLine().trim());
								k.getLastnosti().setZalozba(br.readLine().trim());
								while(br.ready()){
									beri = br.readLine().trim();
									if(beri.equals("#Status")){
										Status s = new Status();
										s.setSerijskaSt(Integer.parseInt(br.readLine().trim()));
										br.readLine();
										s.setIzposojeno(true);
										k.getLastnosti().getStatus().add(s);
									}else{
									break;
									}
								}
								while(br.ready()){
									
									if(beri.equals("#Skupina")){
										k.getLastnosti().getSkupina().add(br.readLine().trim());
										beri = br.readLine().trim();	
									}else{
										break;
									}
								}
								g.getKnjige().add(k);
								
								break;
							case "#Drugo":
								k = new Drugo();
								k.getLastnosti().setNaslov(br.readLine().trim());
								k.getLastnosti().setAvtor(br.readLine().trim());
								k.getLastnosti().setZalozba(br.readLine().trim());
								while(br.ready()){
									beri = br.readLine().trim();
									if(beri.equals("#Status")){
										Status s = new Status();
										s.setSerijskaSt(Integer.parseInt(br.readLine().trim()));
										br.readLine();
										s.setIzposojeno(true);
										k.getLastnosti().getStatus().add(s);
									}else{
									break;
									}
								}
								while(br.ready()){
									
									if(beri.equals("#Skupina")){
										k.getLastnosti().getSkupina().add(br.readLine().trim());
										beri = br.readLine().trim();	
									}else{
										break;
									}
								}
								g.getKnjige().add(k);
								
								break;	
							case "#Casopis":
								p = new Casopis();
								p.getLastnosti().setNaslov(br.readLine().trim());
								p.getLastnosti().setAvtor(br.readLine().trim());
								p.getLastnosti().setZalozba(br.readLine().trim());
								while(br.ready()){
									beri = br.readLine().trim();
									if(beri.equals("#Status")){
										Status s = new Status();
										s.setSerijskaSt(Integer.parseInt(br.readLine().trim()));
										br.readLine();
										s.setIzposojeno(true);
										p.getLastnosti().getStatus().add(s);
									}else{
									break;
									}
								}
								while(br.ready()){
									
									if(beri.equals("#Skupina")){
										p.getLastnosti().getSkupina().add(br.readLine().trim());
										beri = br.readLine().trim();	
									}else{
										break;
									}
								}
								g.getPublikacije().add(p);
								
								break;	
							case "#Revija":
								p = new Revija();
								p.getLastnosti().setNaslov(br.readLine().trim());
								p.getLastnosti().setAvtor(br.readLine().trim());
								p.getLastnosti().setZalozba(br.readLine().trim());
								while(br.ready()){
									beri = br.readLine().trim();
									if(beri.equals("#Status")){
										Status s = new Status();
										s.setSerijskaSt(Integer.parseInt(br.readLine().trim()));
										br.readLine();
										s.setIzposojeno(true);
										p.getLastnosti().getStatus().add(s);
									}else{
									break;
									}
								}
								while(br.ready()){
									
									if(beri.equals("#Skupina")){
										p.getLastnosti().getSkupina().add(br.readLine().trim());
										beri = br.readLine().trim();	
									}else{
										break;
									}
								}
								g.getPublikacije().add(p);
								
								break;
							case "#Clanek":
								p = new Clanek();
								p.getLastnosti().setNaslov(br.readLine().trim());
								p.getLastnosti().setAvtor(br.readLine().trim());
								p.getLastnosti().setZalozba(br.readLine().trim());
								while(br.ready()){
									beri = br.readLine().trim();
									if(beri.equals("#Status")){
										Status s = new Status();
										s.setSerijskaSt(Integer.parseInt(br.readLine().trim()));
										br.readLine();
										s.setIzposojeno(true);
										p.getLastnosti().getStatus().add(s);
									}else{
									break;
									}
								}
								while(br.ready()){
									
									if(beri.equals("#Skupina")){
										p.getLastnosti().getSkupina().add(br.readLine().trim());
										beri = br.readLine().trim();	
									}else{
										break;
									}
								}
								g.getPublikacije().add(p);
								
								break;
							case "#Ostalo":
								p = new Ostalo();
								p.getLastnosti().setNaslov(br.readLine().trim());
								p.getLastnosti().setAvtor(br.readLine().trim());
								p.getLastnosti().setZalozba(br.readLine().trim());
								while(br.ready()){
									beri = br.readLine().trim();
									if(beri.equals("#Status")){
										Status s = new Status();
										s.setSerijskaSt(Integer.parseInt(br.readLine().trim()));
										br.readLine();
										s.setIzposojeno(true);
										p.getLastnosti().getStatus().add(s);
									}else{
									break;
									}
								}
								while(br.ready()){
									
									if(beri.equals("#Skupina")){
										p.getLastnosti().getSkupina().add(br.readLine().trim());
										beri = br.readLine().trim();	
									}else{
										break;
									}
								}
								g.getPublikacije().add(p);
								
								break;
							case "#Atlas":
								m = new Atlas();
								m.getLastnosti().setNaslov(br.readLine().trim());
								m.getLastnosti().setAvtor(br.readLine().trim());
								m.getLastnosti().setZalozba(br.readLine().trim());
								while(br.ready()){
									beri = br.readLine();
									if(beri.equals("#Status")){
										Status s = new Status();
										s.setSerijskaSt(Integer.parseInt(br.readLine().trim()));
										br.readLine();
										s.setIzposojeno(true);
										m.getLastnosti().getStatus().add(s);
									}else{
									break;
									}
								}
								while(br.ready()){
									
									if(beri.equals("#Skupina")){
										m.getLastnosti().getSkupina().add(br.readLine().trim());
										beri = br.readLine().trim();	
									}else{
										break;
									}
								}
								g.getKarte().add(m);
						
								break;
							case "#Zemljevid":
								m = new Zemljevid();
								m.getLastnosti().setNaslov(br.readLine().trim());
								m.getLastnosti().setAvtor(br.readLine().trim());
								m.getLastnosti().setZalozba(br.readLine().trim());
								while(br.ready()){
									beri = br.readLine().trim();
									if(beri.equals("#Status")){
										Status s = new Status();
										s.setSerijskaSt(Integer.parseInt(br.readLine().trim()));
										br.readLine();
										s.setIzposojeno(true);
										m.getLastnosti().getStatus().add(s);
									}else{
									break;
									}
								}
								while(br.ready()){
									
									if(beri.equals("#Skupina")){
										m.getLastnosti().getSkupina().add(br.readLine().trim());
										beri = br.readLine().trim();	
									}else{
										break;
									}
								}
								g.getKarte().add(m);
								
								break;	
							case "#ZvezdnaKarta":
								ZvezdnaKarta z = new ZvezdnaKarta();
								z.getLastnosti().setNaslov(br.readLine().trim());
								z.getLastnosti().setAvtor(br.readLine().trim());
								z.getLastnosti().setZalozba(br.readLine().trim());
								while(br.ready()){
									beri = br.readLine().trim();
									if(beri.equals("#Status")){
										Status s = new Status();
										s.setSerijskaSt(Integer.parseInt(br.readLine().trim()));
										br.readLine();
										s.setIzposojeno(true);
										z.getLastnosti().getStatus().add(s);
									}else{
									break;
									}
								}
								while(br.ready()){
									
									if(beri.equals("#Skupina")){
										z.getLastnosti().getSkupina().add(br.readLine().trim());
										beri = br.readLine().trim();	
									}else{
										break;
									}
								}
								if(beri.equals("#Ephona")){
									z.setEpoha(Double.parseDouble(br.readLine().trim()));
									beri = br.readLine().trim();
								}
								if(beri.equals("#mejnaMagnituda")){
									z.setMejnaMagnituda(Double.parseDouble(br.readLine().trim()));
									beri = br.readLine().trim();
								}
								g.getKarte().add(z);
								
								break;		
							case "#aRoman":
								a = new aRoman();
								a.getLastnosti().setNaslov(br.readLine().trim());
								a.getLastnosti().setAvtor(br.readLine().trim());
								a.getLastnosti().setZalozba(br.readLine().trim());
								while(br.ready()){
									beri = br.readLine().trim();
									if(beri.equals("#Status")){
										Status s = new Status();
										s.setSerijskaSt(Integer.parseInt(br.readLine().trim()));
										br.readLine();
										s.setIzposojeno(true);
										a.getLastnosti().getStatus().add(s);
									}else{
									break;
									}
								}
								while(br.ready()){
									
									if(beri.equals("#Skupina")){
										m.getLastnosti().getSkupina().add(br.readLine().trim());
										beri = br.readLine().trim();	
									}else{
										break;
									}
								}
								g.getAKnjige().add(a);
								
								break;	
							case "#aPrirocnik":
								a = new aPrirocnik();
								a.getLastnosti().setNaslov(br.readLine().trim());
								a.getLastnosti().setAvtor(br.readLine().trim());
								a.getLastnosti().setZalozba(br.readLine().trim());
								while(br.ready()){
									beri = br.readLine().trim();
									if(beri.equals("#Status")){
										Status s = new Status();
										s.setSerijskaSt(Integer.parseInt(br.readLine().trim()));
										br.readLine();
										s.setIzposojeno(true);
										a.getLastnosti().getStatus().add(s);
									}else{
									break;
									}
								}
								while(br.ready()){
									
									if(beri.equals("#Skupina")){
										a.getLastnosti().getSkupina().add(br.readLine().trim());
										beri = br.readLine().trim();	
									}else{
										break;
									}
								}
								g.getAKnjige().add(a);
								break;
								
							case "#aSlovar":
								a = new aSlovar();
								a.getLastnosti().setNaslov(br.readLine().trim());
								a.getLastnosti().setAvtor(br.readLine().trim());
								a.getLastnosti().setZalozba(br.readLine().trim());
								while(br.ready()){
									beri = br.readLine().trim();
									if(beri.equals("#Status")){
										Status s = new Status();
										s.setSerijskaSt(Integer.parseInt(br.readLine().trim()));
										br.readLine();
										s.setIzposojeno(true);
										a.getLastnosti().getStatus().add(s);
									}else{
									break;
									}
								}
								while(br.ready()){
									
									if(beri.equals("#Skupina")){
										a.getLastnosti().getSkupina().add(br.readLine().trim());
										beri = br.readLine().trim();	
									}else{
										break;
									}
								}
								g.getAKnjige().add(a);
								
								break;
							case "#aDrugo":
								a = new aDrugo();
								a.getLastnosti().setNaslov(br.readLine().trim());
								a.getLastnosti().setAvtor(br.readLine().trim());
								a.getLastnosti().setZalozba(br.readLine().trim());
								while(br.ready()){
									beri = br.readLine().trim();
									if(beri.equals("#Status")){
										Status s = new Status();
										s.setSerijskaSt(Integer.parseInt(br.readLine().trim()));
										br.readLine();
										s.setIzposojeno(true);
										a.getLastnosti().getStatus().add(s);
									}else{
									break;
									}
								}
								while(br.ready()){
									
									if(beri.equals("#Skupina")){
										a.getLastnosti().getSkupina().add(br.readLine().trim());
										beri = br.readLine().trim();	
									}else{
										break;
									}
								}
								g.getAKnjige().add(a);
								
								break;
							case "#eRoman":
								e = new eRoman();
								e.getLastnosti().setNaslov(br.readLine().trim());
								e.getLastnosti().setAvtor(br.readLine().trim());
								e.getLastnosti().setZalozba(br.readLine().trim());
								while(br.ready()){
									beri = br.readLine().trim();
									if(beri.equals("#Status")){
										Status s = new Status();
										s.setSerijskaSt(Integer.parseInt(br.readLine().trim()));
										br.readLine();
										s.setIzposojeno(true);
										e.getLastnosti().getStatus().add(s);
									}else{
									break;
									}
								}
								while(br.ready()){
									
									if(beri.equals("#Skupina")){
										e.getLastnosti().getSkupina().add(br.readLine().trim());
										beri = br.readLine().trim();	
									}else{
										break;
									}
								}
								g.getEKnjige().add(e);
								
								break;
							case "#ePrirocnik":
								e = new ePrirocnik();
								e.getLastnosti().setNaslov(br.readLine().trim());
								e.getLastnosti().setAvtor(br.readLine().trim());
								e.getLastnosti().setZalozba(br.readLine().trim());
								while(br.ready()){
									beri = br.readLine().trim();
									if(beri.equals("#Status")){
										Status s = new Status();
										s.setSerijskaSt(Integer.parseInt(br.readLine().trim()));
										br.readLine();
										s.setIzposojeno(true);
										e.getLastnosti().getStatus().add(s);
									}else{
									break;
									}
								}
								while(br.ready()){
									
									if(beri.equals("#Skupina")){
										e.getLastnosti().getSkupina().add(br.readLine().trim());
										beri = br.readLine().trim();	
									}else{
										break;
									}
								}
								g.getEKnjige().add(e);
								
								break;	
							case "#eStrip":
								e = new eStrip();
								e.getLastnosti().setNaslov(br.readLine().trim());
								e.getLastnosti().setAvtor(br.readLine().trim());
								e.getLastnosti().setZalozba(br.readLine().trim());
								while(br.ready()){
									beri = br.readLine().trim();
									if(beri.equals("#Status")){
										Status s = new Status();
										s.setSerijskaSt(Integer.parseInt(br.readLine().trim()));
										br.readLine();
										s.setIzposojeno(true);
										e.getLastnosti().getStatus().add(s);
									}else{
									break;
									}
								}
								while(br.ready()){
									
									if(beri.equals("#Skupina")){
										e.getLastnosti().getSkupina().add(br.readLine().trim());
										beri = br.readLine().trim();	
									}else{
										break;
									}
								}
								g.getEKnjige().add(e);
								
								break;	
							case "#eSlovar":
								e = new eSlovar();
								e.getLastnosti().setNaslov(br.readLine().trim());
								e.getLastnosti().setAvtor(br.readLine().trim());
								e.getLastnosti().setZalozba(br.readLine().trim());
								while(br.ready()){
									beri = br.readLine().trim();
									if(beri.equals("#Status")){
										Status s = new Status();
										s.setSerijskaSt(Integer.parseInt(br.readLine().trim()));
										br.readLine();
										s.setIzposojeno(true);
										e.getLastnosti().getStatus().add(s);
									}else{
									break;
									}
								}
								while(br.ready()){
									
									if(beri.equals("#Skupina")){
										e.getLastnosti().getSkupina().add(br.readLine().trim());
										beri = br.readLine().trim();	
									}else{
										break;
									}
								}
								g.getEKnjige().add(e);
								
								break;	
							case "#eDrugo":
								e = new eDrugo();
								e.getLastnosti().setNaslov(br.readLine().trim());
								e.getLastnosti().setAvtor(br.readLine().trim());
								e.getLastnosti().setZalozba(br.readLine().trim());
								while(br.ready()){
									beri = br.readLine().trim();
									if(beri.equals("#Status")){
										Status s = new Status();
										s.setSerijskaSt(Integer.parseInt(br.readLine().trim()));
										br.readLine();
										s.setIzposojeno(true);
										e.getLastnosti().getStatus().add(s);
									}else{
									break;
									}
								}
								while(br.ready()){
									
									if(beri.equals("#Skupina")){
										e.getLastnosti().getSkupina().add(br.readLine().trim());
										beri = br.readLine().trim();	
									}else{
										break;
									}
								}
								g.getEKnjige().add(e);
								
								break;
							case "Gradivo#":
								setGradivo(g);
								br.close();
								return;
								
							default:
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Napaka v datoteki 'Gradivo.txt'                     |");
								System.out.println("| Obrnite se na admina                                |");
								System.out.println(" ----------------------------------------------------- ");
								br.close();
								return;
						}			
																
						
					}
					
				}else{
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("| Datoteka 'Gradivo.txt' je prazna                    |");
					System.out.println("| ali pa narobe vnesena.                              |");
					System.out.println(" ----------------------------------------------------- ");	
				
				}
				setGradivo(g);
				br.close();
					
				
			}catch(FileNotFoundException e){
				System.out.println(" ----------------------------------------------------- ");
				System.out.println("| Datoteke 'Gradivo.txt' ni bilo mogoče najti         |");
				System.out.println("| Obrnite se na admina za odpravitev napake           |");
				System.out.println(" ----------------------------------------------------- ");	
			}
			
		
		}
	
	public void registracija() throws Exception{
		Uporabnik u = new Uporabnik();
		boolean vnos = false;
		zacetek:
		while(!vnos){
			//System.out.print("| ");	
			u.vnosImena();
			for(int i = 0; i<uporabniki.size();i++){
				if(u.getIme().equals(uporabniki.get(i).getIme())){
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("|Uporabnik s tem imenom ze obstaja, poskusite ponovno |");
					System.out.println(" ----------------------------------------------------- ");
				}else{
					vnos = true;
					break zacetek;
				}
			}
			vnos = true;
		}
		u.vnosGesla();	
		this.uporabniki.add(u);	
	}
	
	public void vnosVDatoteko()throws Exception{
		PrintWriter pw = new PrintWriter(new FileWriter("Uporabniki.txt"));
		for(int i = 0; i<getUporabniki().size();i++){
			pw.println("U*");
			pw.println("#I");
			pw.println(getUporabniki().get(i).getIme());
			pw.println("#G");
			getUporabniki().get(i).kodiranjeGesla();
			pw.println(getUporabniki().get(i).getGeslo());
			getUporabniki().get(i).odkodiranjeGesla(getUporabniki().get(i).getGeslo());
			pw.println("#A");
			pw.println(getUporabniki().get(i).getAdmin());
			
			pw.println("G*");
				for(int k = 0 ; k<getUporabniki().get(i).getGradivo().getKnjige().size();k++){
					pw.println("#K");
					pw.println(getUporabniki().get(i).getGradivo().getKnjige().get(k).getLastnosti().getStatus().get(0).getSerijskaSt());
				}
				for(int k = 0 ; k<getUporabniki().get(i).getGradivo().getKarte().size();k++){
					pw.println("#M");
					pw.println(getUporabniki().get(i).getGradivo().getKarte().get(k).getLastnosti().getStatus().get(0).getSerijskaSt());
				}
				for(int k = 0 ; k<getUporabniki().get(i).getGradivo().getAKnjige().size();k++){
					pw.println("#Ak");
					pw.println(getUporabniki().get(i).getGradivo().getAKnjige().get(k).getLastnosti().getStatus().get(0).getSerijskaSt());
				}
				for(int k = 0 ; k<getUporabniki().get(i).getGradivo().getEKnjige().size();k++){
					pw.println("#Ek");
					pw.println(getUporabniki().get(i).getGradivo().getEKnjige().get(k).getLastnosti().getStatus().get(0).getSerijskaSt());
				}
				for(int k = 0 ; k<getUporabniki().get(i).getGradivo().getPublikacije().size();k++){
					pw.println("#P");
					pw.println(getUporabniki().get(i).getGradivo().getPublikacije().get(k).getLastnosti().getStatus().get(0).getSerijskaSt());
				}
			pw.println("*G");
			pw.println("*U");
		}
		pw.close();
	}
	
	public void uporabnikiIzDatoteke() throws Exception{
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("Uporabniki.txt"));
			Gradivo g = new Gradivo();
			while(br.ready()){
				String vnos = br.readLine().trim();
				Uporabnik u = new Uporabnik();
				if(vnos.equals("U*")){
					
					konec:
					while(br.ready()){
						vnos = br.readLine().trim();
						if(vnos.equals("#I")){
							u.setIme(br.readLine().trim());
						}else{
							System.out.println(" ----------------------------------------------------- ");
							System.out.println("| Napaka v datoteki 'Uporabniki.txt'                  |");
							System.out.println(" ----------------------------------------------------- ");
							return;
						}	
						vnos = br.readLine().trim();
						if(vnos.equals("#G")){
							String geslo = br.readLine().trim();
							u.odkodiranjeGesla(geslo);
						}else{
							System.out.println(" ----------------------------------------------------- ");
							System.out.println("| Napaka v datoteki 'Uporabniki.txt'                  |");
							System.out.println(" ----------------------------------------------------- ");
							return;
						}
						vnos = br.readLine().trim();
						if(vnos.equals("#A")){
							u.setAdmin(Boolean.parseBoolean(br.readLine().trim()));
						}else{
							System.out.println(" ----------------------------------------------------- ");
							System.out.println("| Napaka v datoteki 'Uporabniki.txt'                  |");
							System.out.println(" ----------------------------------------------------- ");
							return;
						}
						vnos = br.readLine().trim();
						if(vnos.equals("G*")){
							while(br.ready()){
								
								vnos = br.readLine().trim();
								if(vnos.equals("*G")){
									u.setGradivo(g);
									g = new Gradivo();
									vnos = br.readLine().trim();
									break konec;			
								}else{
									switch(vnos){
										case "#K":
											int serijskaK = Integer.parseInt(br.readLine().trim());
											for(int i = 0; i < getGradivo().getKnjige().size();i++){
													for(int j = 0; j< getGradivo().getKnjige().get(i).getLastnosti().getStatus().size();j++){
														Knjiga k = new Knjiga();
														if(serijskaK == getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt()){
															k.getLastnosti().setNaslov(getGradivo().getKnjige().get(i).getLastnosti().getNaslov());
															k.getLastnosti().setAvtor(getGradivo().getKnjige().get(i).getLastnosti().getAvtor());
															k.getLastnosti().setZalozba(getGradivo().getKnjige().get(i).getLastnosti().getZalozba());
															k.getLastnosti().setSkupina(getGradivo().getKnjige().get(i).getLastnosti().getSkupina());
															ArrayList<Status> s = new ArrayList<>();
															Status st = new Status();
															st.setSerijskaSt(serijskaK);
															st.setIzposojeno(false);
															getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
															s.add(st);
															k.getLastnosti().setStatus(s);
															g.getKnjige().add(k);
														}
													}
											}	
											break;
										case "#M":
											int serijskaM = Integer.parseInt(br.readLine().trim());
											for(int i = 0; i < getGradivo().getKarte().size();i++){
													for(int j = 0; j<getGradivo().getKarte().get(i).getLastnosti().getStatus().size();j++){
														Karta k = new Karta();
														if(serijskaM == getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getSerijskaSt()){
															k.getLastnosti().setNaslov(getGradivo().getKarte().get(i).getLastnosti().getNaslov());
															k.getLastnosti().setAvtor(getGradivo().getKarte().get(i).getLastnosti().getAvtor());
															k.getLastnosti().setZalozba(getGradivo().getKarte().get(i).getLastnosti().getZalozba());
															k.getLastnosti().setSkupina(getGradivo().getKarte().get(i).getLastnosti().getSkupina());
															ArrayList<Status> s = new ArrayList<>();
															Status st = new Status();
															st.setSerijskaSt(serijskaM);
															st.setIzposojeno(false);
															getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
															s.add(st);
															k.getLastnosti().setStatus(s);
															g.getKarte().add(k);
														}
													}
											}
											break;
										case "#Ek":
											int serijskaEK = Integer.parseInt(br.readLine().trim());
											for(int i = 0; i < getGradivo().getEKnjige().size();i++){
													for(int j = 0; j<getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size();j++){
														EKnjiga k = new EKnjiga();
														if(serijskaEK == getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt()){
															k.getLastnosti().setNaslov(getGradivo().getEKnjige().get(i).getLastnosti().getNaslov());
															k.getLastnosti().setAvtor(getGradivo().getEKnjige().get(i).getLastnosti().getAvtor());
															k.getLastnosti().setZalozba(getGradivo().getEKnjige().get(i).getLastnosti().getZalozba());
															k.getLastnosti().setSkupina(getGradivo().getEKnjige().get(i).getLastnosti().getSkupina());
															ArrayList<Status> s = new ArrayList<>();
															Status st = new Status();
															st.setSerijskaSt(serijskaEK);
															st.setIzposojeno(false);
															getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
															s.add(st);
															k.getLastnosti().setStatus(s);
															g.getEKnjige().add(k);
														}
													}
											}	
											break;											
										case "#Ak":
											int serijskaAK = Integer.parseInt(br.readLine().trim());
											for(int i = 0; i < getGradivo().getAKnjige().size();i++){
													for(int j = 0; j<getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size();j++){
														AKnjiga k = new AKnjiga();
														if(serijskaAK == getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt()){
															k.getLastnosti().setNaslov(getGradivo().getAKnjige().get(i).getLastnosti().getNaslov());
															k.getLastnosti().setAvtor(getGradivo().getAKnjige().get(i).getLastnosti().getAvtor());
															k.getLastnosti().setZalozba(getGradivo().getAKnjige().get(i).getLastnosti().getZalozba());
															k.getLastnosti().setSkupina(getGradivo().getAKnjige().get(i).getLastnosti().getSkupina());
															ArrayList<Status> s = new ArrayList<>();
															Status st = new Status();
															st.setSerijskaSt(serijskaAK);
															st.setIzposojeno(false);
															getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
															s.add(st);
															k.getLastnosti().setStatus(s);
															g.getAKnjige().add(k);
														}
													}
											}	
											break;
										case "#P":
											int serijskaP = Integer.parseInt(br.readLine().trim());
											for(int i = 0; i < getGradivo().getPublikacije().size();i++){
													for(int j = 0; j<getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size();j++){
														Publikacija k = new Publikacija();
														if(serijskaP == getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getSerijskaSt()){
															k.getLastnosti().setNaslov(getGradivo().getPublikacije().get(i).getLastnosti().getNaslov());
															k.getLastnosti().setAvtor(getGradivo().getPublikacije().get(i).getLastnosti().getAvtor());
															k.getLastnosti().setZalozba(getGradivo().getPublikacije().get(i).getLastnosti().getZalozba());
															k.getLastnosti().setSkupina(getGradivo().getPublikacije().get(i).getLastnosti().getSkupina());
															ArrayList<Status> s = new ArrayList<>();
															Status st = new Status();
															st.setSerijskaSt(serijskaP);
															st.setIzposojeno(false);
															getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
															s.add(st);
															k.getLastnosti().setStatus(s);
															g.getPublikacije().add(k);
														}
													}
											}	
											break;									
										default:
											System.out.println(" ----------------------------------------------------- ");
											System.out.println("| Napaka v datoteki 'Uporabniki.txt'                  |");
											System.out.println(" ----------------------------------------------------- ");
											return;
									}
								}
							
							}	
						}
						
					}
				
				}
				if(vnos.equals("*U")){
					
				}else{
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("| Napaka v datoteki 'Uporabniki.txt'                  |");
					System.out.println(" ----------------------------------------------------- ");
					return;
				}
				getUporabniki().add(u);
			}	
			br.close();
			
		}catch(FileNotFoundException e){
			System.out.println(" ----------------------------------------------------- ");
			System.out.println("| Datoteke 'Uporabniki.txt' program ni nasel.         |");
			System.out.println("| Prosimo obrnite se na administratorja               |");
			System.out.println(" ----------------------------------------------------- ");
			return;
		}
	}
	
	public void izpisUporabnikov() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		konec:
		nadaljuj:
		for(int i = 0; i < getUporabniki().size(); i++){
			getUporabniki().get(i).izpisImen();
			if(i%5 == 0 && i!=0){
				System.out.println(" ----------------------------------------------------- ");
				System.out.println("| Pritisni (n) za naslednjo stran                     |");
				System.out.println("| Pritisni (x) za zakljucek iskanja                   |");
				System.out.println(" ----------------------------------------------------- ");
				String vnos = br.readLine().trim().toLowerCase();
				if(vnos.length() == 1){
					char znak = vnos.charAt(0);
					switch(znak){
						case 'n':
							continue nadaljuj;
						case 'x':
							break konec;
						default:
							System.out.println(" ----------------------------------------------------- ");
							System.out.println("| Napacen vnos                                        |");
							System.out.println(" ----------------------------------------------------- ");
							break;
					}
				}else{
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("| Napacen vnos                                        |");
					System.out.println(" ----------------------------------------------------- ");
				}	
			}else if((i+1)==getUporabniki().size()){
				System.out.println(" ----------------------------------------------------- ");
				System.out.println("| Pritisni (x) za konec                               |");
				System.out.println(" ----------------------------------------------------- ");
				String vnos = br.readLine().trim().toLowerCase();
				if(vnos.length() == 1){
					char znak = vnos.charAt(0);
					switch(znak){
						case 'x':
							break konec;
						default:
							System.out.println(" ----------------------------------------------------- ");
							System.out.println("| Napacen vnos                                        |");
							System.out.println(" ----------------------------------------------------- ");
							break;
					}
				}else{
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("| Napacen vnos                                        |");
					System.out.println(" ----------------------------------------------------- ");
				}
			}	
		}
	}

	public void izpisIzposojenih() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int stevec = 0;
		ArrayList<Integer>  knjige = new ArrayList<>();
		ArrayList<Integer>  karte = new ArrayList<>();
		ArrayList<Integer>  publikacije = new ArrayList<>();
		ArrayList<Integer>  aKnjige = new ArrayList<>();
		ArrayList<Integer>  eKnjige = new ArrayList<>();
		
		novo:
		for(int i = 0; i<getGradivo().getKnjige().size();i++){
			for(int k = 0 ; k< getGradivo().getKnjige().get(i).getLastnosti().getStatus().size();k++){
				if(!(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(k).getIzposojeno())){
							knjige.add(i);
							continue novo;
				}
			}
		}
		novo1:
		for(int i = 0; i<getGradivo().getKarte().size();i++){
			for(int k = 0 ; k< getGradivo().getKarte().get(i).getLastnosti().getStatus().size();k++){
				if(!(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(k).getIzposojeno())){
							karte.add(i);
							continue novo1;
				}
			}
		}
		novo2:
		for(int i = 0; i<getGradivo().getPublikacije().size();i++){
			for(int k = 0 ; k< getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size();k++){
				if(!(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(k).getIzposojeno())){
							publikacije.add(i);
							continue novo2;
				}
			}
		}
		novo3:
		for(int i = 0; i<getGradivo().getEKnjige().size();i++){
			for(int k = 0 ; k< getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size();k++){
				if(!(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(k).getIzposojeno())){
							eKnjige.add(i);
							continue novo3;
				}
			}
		}
		novo4:
		for(int i = 0; i<getGradivo().getAKnjige().size();i++){
			for(int k = 0 ; k< getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size();k++){
				if(!(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(k).getIzposojeno())){
							aKnjige.add(i);
							continue novo4;
				}
			}
		}
		int steviloNajdenih = knjige.size() + karte.size() + publikacije.size() + aKnjige.size() + eKnjige.size();
		int stevecS = 0;
		konec:
		novaStran:
		while(stevec< steviloNajdenih){
			
			stevec++;
			System.out.println(" ----------------------------------------------------- ");
			System.out.println("| Zaporedna stevilka " + stevec);
			if(stevec <= knjige.size()){
				getGradivo().getKnjige().get(knjige.get(stevec-1)).izpis();
			}else if(stevec > knjige.size() && stevec <= (knjige.size()+karte.size())){
				getGradivo().getKarte().get(karte.get(stevec-1-knjige.size())).izpis();
			}else if(stevec > (knjige.size()+karte.size()) && stevec <= (knjige.size()+karte.size()+publikacije.size())){
				getGradivo().getPublikacije().get(publikacije.get(stevec-1-knjige.size()-karte.size())).izpis();
			}else if(stevec > (knjige.size()+karte.size()+publikacije.size()) && stevec <= (knjige.size()+karte.size()+publikacije.size()+ aKnjige.size())){
				getGradivo().getAKnjige().get(aKnjige.get(stevec-1-knjige.size()-karte.size()-publikacije.size())).izpis();
			}else{
				getGradivo().getEKnjige().get(eKnjige.get(stevec-1-knjige.size()-karte.size()-publikacije.size()-aKnjige.size())).izpis();
			}
				
			if(stevec%5 == 0  || stevec == steviloNajdenih){
				stevecS++;
				nadaljuj2:
				while(true){
					System.out.println("| Stran "+stevecS);
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("| Pritisnite (n) za naslednjo stran                   |");
					System.out.println("| Pritisnite (x) za zakljucitev iskanja               |");	
					System.out.println(" ----------------------------------------------------- ");
					String input = br.readLine().toLowerCase();
					if(input.length() == 1){
						char znak = input.charAt(0);
						switch(znak){
							case 'n':
								continue novaStran;
							case 'x':
								break konec;
							default:
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Napacen vnos                                        |");
								System.out.println(" ----------------------------------------------------- ");
								continue nadaljuj2;
						}
					}else{
						System.out.println(" ----------------------------------------------------- ");
						System.out.println("| Napacen vnos                                        |");
						System.out.println(" ----------------------------------------------------- ");
					}
				}			
			}
		}	
	}
	
	public void brisanjeGradiv() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(" ----------------------------------------------------- ");
		System.out.println("| Vpisite iskalni niz gradiva, ki ga želite odstraniti|");
		System.out.println(" ----------------------------------------------------- ");
		ArrayList<Integer>  knjige = new ArrayList<>();
		ArrayList<Integer>  karte = new ArrayList<>();
		ArrayList<Integer>  publikacije = new ArrayList<>();
		ArrayList<Integer>  aKnjige = new ArrayList<>();
		ArrayList<Integer>  eKnjige = new ArrayList<>();
		String vnos = br.readLine().trim().toLowerCase();
		vnos +=" ";
		ArrayList<String> vnosi= new ArrayList<>();
		String niz = "";
		for(int i = 0; i<vnos.length();i++){
			if(vnos.charAt(i) == ' '){
				vnosi.add(niz.trim());
				niz = "";
				
			}else{
				niz+= vnos.charAt(i);
			}
		}
		int stevec = 0;
		novo:
		for(int i = 0; i<getGradivo().getKnjige().size();i++){
			String naslov = getGradivo().getKnjige().get(i).getLastnosti().getNaslov();
			naslov +=" ";
			String glejNiz ="";
			for(int k = 0 ; k< naslov.length();k++){
				if(naslov.charAt(k) == ' '){
					glejNiz = glejNiz.toLowerCase();
					for(int j = 0 ; j<vnosi.size();j++){
						if(glejNiz.equals(vnosi.get(j))){
							knjige.add(i);
							continue novo;
						}
					}
					glejNiz ="";
						
				}else{
					glejNiz += naslov.charAt(k);	
				}	
			}		
		}
		novo1:
		for(int i = 0; i<getGradivo().getAKnjige().size();i++){
			String naslov = getGradivo().getAKnjige().get(i).getLastnosti().getNaslov();
			naslov +=" ";
			String glejNiz ="";
			for(int k = 0 ; k< naslov.length();k++){
				if(naslov.charAt(k) == ' '){
					glejNiz = glejNiz.toLowerCase();
					for(int j = 0 ; j<vnosi.size();j++){
						if(glejNiz.equals(vnosi.get(j))){
							aKnjige.add(i);
							continue novo1;
						}
					}
					glejNiz ="";
						
				}else{
					glejNiz += naslov.charAt(k);	
				}	
			}		
		}
		novo2:
		for(int i = 0; i<getGradivo().getKarte().size();i++){
			String naslov = getGradivo().getKarte().get(i).getLastnosti().getNaslov();
			naslov +=" ";
			String glejNiz ="";
			for(int k = 0 ; k< naslov.length();k++){
				if(naslov.charAt(k) == ' '){
					glejNiz = glejNiz.toLowerCase();
					for(int j = 0 ; j<vnosi.size();j++){
						if(glejNiz.equals(vnosi.get(j))){
							karte.add(i);
							continue novo2;
						}
					}
					glejNiz ="";
						
				}else{
					glejNiz += naslov.charAt(k);	
				}	
			}		
		}
		novo3:
		for(int i = 0; i<getGradivo().getPublikacije().size();i++){
			String naslov = getGradivo().getPublikacije().get(i).getLastnosti().getNaslov();
			naslov +=" ";
			String glejNiz ="";
			for(int k = 0 ; k< naslov.length();k++){
				if(naslov.charAt(k) == ' '){
					glejNiz = glejNiz.toLowerCase();
					for(int j = 0 ; j<vnosi.size();j++){
						if(glejNiz.equals(vnosi.get(j))){
							publikacije.add(i);
							continue novo3;
						}
					}
					glejNiz ="";
						
				}else{
					glejNiz += naslov.charAt(k);	
				}	
			}		
		}
		novo4:
		for(int i = 0; i<getGradivo().getEKnjige().size();i++){
			String naslov = getGradivo().getEKnjige().get(i).getLastnosti().getNaslov();
			naslov +=" ";
			String glejNiz ="";
			for(int k = 0 ; k< naslov.length();k++){
				if(naslov.charAt(k) == ' '){
					glejNiz = glejNiz.toLowerCase();
					for(int j = 0 ; j<vnosi.size();j++){
						if(glejNiz.equals(vnosi.get(j))){
							eKnjige.add(i);
							continue novo4;
						}
					}
					glejNiz ="";
						
				}else{
					glejNiz += naslov.charAt(k);	
				}	
			}		
		}
		
		
		int steviloNajdenih = knjige.size() + karte.size() + publikacije.size() + aKnjige.size() + eKnjige.size();
		int stevecS = 0;	
		konec:
		novaStran:
		while(stevec< steviloNajdenih){
			stevec++;
			System.out.println(" ----------------------------------------------------- ");
			System.out.println("| Zaporedna stevilka " + stevec);
			if(stevec <= knjige.size()){
				getGradivo().getKnjige().get(knjige.get(stevec-1)).izpis();
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("|Serijske stevilka /-e                                |");
					System.out.println(" ----------------------------------------------------- ");
					System.out.print("| ");
				for(int i = 0; i < getGradivo().getKnjige().get(knjige.get(stevec-1)).getLastnosti().getStatus().size();i++){
					System.out.print(getGradivo().getKnjige().get(knjige.get(stevec-1)).getLastnosti().getStatus().get(i).getSerijskaSt()+" ");
				}
				System.out.println();
				System.out.println(" ----------------------------------------------------- ");
			}else if(stevec > knjige.size() && stevec <= (knjige.size()+karte.size())){
				getGradivo().getKarte().get(karte.get(stevec-1-knjige.size())).izpis();
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("|Serijske stevilka /-e                                |");
					System.out.println(" ----------------------------------------------------- ");
					System.out.print("| ");
				for(int i = 0; i < getGradivo().getKarte().get(knjige.get(stevec-1)).getLastnosti().getStatus().size();i++){
					System.out.print(getGradivo().getKarte().get(knjige.get(stevec-1)).getLastnosti().getStatus().get(i).getSerijskaSt()+" ");
				}
				System.out.println();
				System.out.println(" ----------------------------------------------------- ");
			}else if(stevec > (knjige.size()+karte.size()) && stevec <= (knjige.size()+karte.size()+publikacije.size())){
				getGradivo().getPublikacije().get(publikacije.get(stevec-1-knjige.size()-karte.size())).izpis();
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("|Serijske stevilka /-e                                |");
					System.out.println(" ----------------------------------------------------- ");
					System.out.print("| ");
				for(int i = 0; i < getGradivo().getPublikacije().get(knjige.get(stevec-1)).getLastnosti().getStatus().size();i++){
					System.out.print(getGradivo().getPublikacije().get(knjige.get(stevec-1)).getLastnosti().getStatus().get(i).getSerijskaSt()+" ");
				}
				System.out.println();
				System.out.println(" ----------------------------------------------------- ");
			}else if(stevec > (knjige.size()+karte.size()+publikacije.size()) && stevec <= (knjige.size()+karte.size()+publikacije.size()+ aKnjige.size())){
				getGradivo().getAKnjige().get(aKnjige.get(stevec-1-knjige.size()-karte.size()-publikacije.size())).izpis();
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("|Serijske stevilka /-e                                |");
					System.out.println(" ----------------------------------------------------- ");
					System.out.print("| ");
				for(int i = 0; i < getGradivo().getAKnjige().get(knjige.get(stevec-1)).getLastnosti().getStatus().size();i++){
					System.out.print(getGradivo().getAKnjige().get(knjige.get(stevec-1)).getLastnosti().getStatus().get(i).getSerijskaSt()+" ");
				}
				System.out.println();
				System.out.println(" ----------------------------------------------------- ");
			}else{
				getGradivo().getEKnjige().get(eKnjige.get(stevec-1-knjige.size()-karte.size()-publikacije.size()-aKnjige.size())).izpis();
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("|Serijske stevilka /-e                                |");
					System.out.println(" ----------------------------------------------------- ");
					System.out.print("| ");
				for(int i = 0; i < getGradivo().getEKnjige().get(knjige.get(stevec-1)).getLastnosti().getStatus().size();i++){
					System.out.print(getGradivo().getEKnjige().get(knjige.get(stevec-1)).getLastnosti().getStatus().get(i).getSerijskaSt()+" ");
				}
				System.out.println();
				System.out.println(" ----------------------------------------------------- ");
			}
			
			if(stevec%5 == 0  || stevec == steviloNajdenih){
				stevecS++;
				System.out.println("| Stran "+stevecS);
				System.out.println(" ----------------------------------------------------- ");
				System.out.println("| Pritisnite (i) da odstranite gradivo po indeksu     |");
				System.out.println("| Pritisnite (s) da odstranite gradivo po serijski st |");
				System.out.println("| Pritisnite (n) za naslednjo stran                   |");
				System.out.println("| Pritisnite (x) za zakljucitev iskanja               |");	
				System.out.println(" ----------------------------------------------------- ");
				String input = br.readLine().toLowerCase();
				if(input.length() == 1){
					char znak = input.charAt(0);
					switch(znak){
						case 'n':
							continue novaStran;
						case 'i':
							System.out.println(" ----------------------------------------------------- ");
							System.out.println("| Vpisite indeks gradiva na strani                    |");
							System.out.println(" ----------------------------------------------------- ");
							niNaVoljo:
							
							dodaj:
							while(true){
								try{
									int g = Integer.parseInt(br.readLine().trim());
									if(g <= knjige.size()){
										int i = knjige.get(g-1);
										for(int j = 0 ; j <getGradivo().getKnjige().get(i).getLastnosti().getStatus().size();j++){
										if(!(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno())){
											System.out.println(" ----------------------------------------------------- ");
											System.out.println("| Gradivo je izposojeno, ne morete ga odstraniti      |");
											System.out.println(" ----------------------------------------------------- ");
											break dodaj;
										}
											if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
												getGradivo().getKnjige().remove(i);
												break konec;
											}	
										}
									}else if(g > knjige.size() && g <= (knjige.size()+karte.size())){
										int i = karte.get(g-1-knjige.size());
										for(int j = 0 ; j <getGradivo().getKarte().get(i).getLastnosti().getStatus().size();j++){
										if(!(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getIzposojeno())){
											System.out.println(" ----------------------------------------------------- ");
											System.out.println("| Gradivo je izposojeno, ne morete ga odstraniti      |");
											System.out.println(" ----------------------------------------------------- ");
											break dodaj;
										}
											if((j+1)== getGradivo().getKarte().get(i).getLastnosti().getStatus().size()){
												getGradivo().getKarte().remove(i);
												break konec;
											}	
										}
									}else if(g > (knjige.size()+karte.size()) && g <= (knjige.size()+karte.size()+publikacije.size())){
										int i = publikacije.get(g-1-knjige.size()-karte.size());
										for(int j = 0 ; j <getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size();j++){
										if(!(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getIzposojeno())){
											System.out.println(" ----------------------------------------------------- ");
											System.out.println("| Gradivo je izposojeno, ne morete ga odstraniti      |");
											System.out.println(" ----------------------------------------------------- ");
											break dodaj;
										}
											if((j+1)== getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size()){
												getGradivo().getPublikacije().remove(i);
												break konec;
											}	
										}										
									}else if(g > (knjige.size()+karte.size()+publikacije.size()) && g <= (knjige.size()+karte.size()+publikacije.size()+ aKnjige.size())){
										int i = aKnjige.get(g-1-knjige.size()-karte.size()-publikacije.size());
										for(int j = 0 ; j <getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size();j++){
										if(!(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno())){
											System.out.println(" ----------------------------------------------------- ");
											System.out.println("| Gradivo je izposojeno, ne morete ga odstraniti      |");
											System.out.println(" ----------------------------------------------------- ");
											break dodaj;
										}
											if((j+1)== getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size()){
												getGradivo().getAKnjige().remove(i);
												break konec;
											}	
										}
									}else if(g > (knjige.size()+karte.size()+publikacije.size()+ aKnjige.size()) && g <= (knjige.size()+karte.size()+publikacije.size()+ aKnjige.size()+eKnjige.size())){
										int i = eKnjige.get(g-1-knjige.size()-karte.size()-publikacije.size());
										for(int j = 0 ; j <getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size();j++){
										if(!(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno())){
											System.out.println(" ----------------------------------------------------- ");
											System.out.println("| Gradivo je izposojeno, ne morete ga odstraniti      |");
											System.out.println(" ----------------------------------------------------- ");
											break dodaj;
										}
											if((j+1)== getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size()){
												getGradivo().getEKnjige().remove(i);
												break konec;
											}	
										}
									}else{
										break konec;
									}
									
								} catch(NumberFormatException e){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Napacen vnos                                        |");
									System.out.println(" ----------------------------------------------------- ");
									break konec;
								}catch(IndexOutOfBoundsException e ){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Knjiga s tem indeksom ne obstaja                    |");
									System.out.println(" ----------------------------------------------------- ");
									break konec;
								}
							}
							break;
						case 's':
							System.out.println(" ----------------------------------------------------- ");
							System.out.println("| Vpisite serijsko stevilko                           |");
							System.out.println(" ----------------------------------------------------- ");
							niNaVoljo:
							
							dodaj:
							while(true){
								try{
									int g = Integer.parseInt(br.readLine().trim());
										for(int i = 0; i<getGradivo().getKnjige().size();i++){
											for(int j = 0 ; j <getGradivo().getKnjige().get(i).getLastnosti().getStatus().size();j++){
												if(g == getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt()){	
													if(!(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno())){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo je izposojeno, ne morete ga odstraniti      |");
														System.out.println(" ----------------------------------------------------- ");
														break dodaj;
													}else{
															getGradivo().getKnjige().get(i).getLastnosti().getStatus().remove(j);
															break konec;
														}	
												}	
											}
										}
										for(int i = 0; i<getGradivo().getKarte().size();i++){
											for(int j = 0 ; j <getGradivo().getKarte().get(i).getLastnosti().getStatus().size();j++){
												if(g == getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getSerijskaSt()){	
													if(!(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getIzposojeno())){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo je izposojeno, ne morete ga odstraniti      |");
														System.out.println(" ----------------------------------------------------- ");
														break dodaj;
													}else{
															getGradivo().getKarte().get(i).getLastnosti().getStatus().remove(j);
															break konec;
														}	
												}	
											}
										}
										for(int i = 0; i<getGradivo().getPublikacije().size();i++){
											for(int j = 0 ; j <getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size();j++){
												if(g == getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getSerijskaSt()){	
													if(!(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getIzposojeno())){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo je izposojeno, ne morete ga odstraniti      |");
														System.out.println(" ----------------------------------------------------- ");
														break dodaj;
													}else{
															getGradivo().getPublikacije().get(i).getLastnosti().getStatus().remove(j);
															break konec;
														}	
												}	
											}
										}
										for(int i = 0; i<getGradivo().getAKnjige().size();i++){
											for(int j = 0 ; j <getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size();j++){
												if(g == getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt()){	
													if(!(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno())){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo je izposojeno, ne morete ga odstraniti      |");
														System.out.println(" ----------------------------------------------------- ");
														break dodaj;
													}else{
															getGradivo().getAKnjige().get(i).getLastnosti().getStatus().remove(j);
															break konec;
														}	
												}	
											}
										}
										for(int i = 0; i<getGradivo().getEKnjige().size();i++){
											for(int j = 0 ; j <getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size();j++){
												if(g == getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt()){	
													if(!(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno())){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo je izposojeno, ne morete ga odstraniti      |");
														System.out.println(" ----------------------------------------------------- ");
														break dodaj;
													}else{
															getGradivo().getEKnjige().get(i).getLastnosti().getStatus().remove(j);
															break konec;
														}	
												}	
											}
										}
								} catch(NumberFormatException e){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Napacen vnos                                        |");
									System.out.println(" ----------------------------------------------------- ");
									break konec;
								}catch(IndexOutOfBoundsException e ){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Knjiga s tem indeksom ne obstaja                    |");
									System.out.println(" ----------------------------------------------------- ");
									break konec;
								}
							}
							break;
						case 'x':
							return;
						default:
							System.out.println(" ----------------------------------------------------- ");
							System.out.println("| Napacen vnos                                        |");
							System.out.println(" ----------------------------------------------------- ");
							break;
								
					}
				
				}else{
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("| Napacen vnos poskusite ponovno                      |");
					System.out.println(" ----------------------------------------------------- ");
				}
			}
		}
		
	}

	public void iskanjeTeme(Uporabnik u) throws Exception{
	ArrayList<Integer>  knjige = new ArrayList<>();
		ArrayList<Integer>  karte = new ArrayList<>();
		ArrayList<Integer>  publikacije = new ArrayList<>();
		ArrayList<Integer>  aKnjige = new ArrayList<>();
		ArrayList<Integer>  eKnjige = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(" ----------------------------------------------------- ");
		System.out.println("| Vtipkajte temo za iskanje                           |");
		System.out.println(" ----------------------------------------------------- ");
		String vnos = br.readLine().trim().toLowerCase();
		vnos +=" ";
		ArrayList<String> vnosi= new ArrayList<>();
		String niz = "";
		for(int i = 0; i<vnos.length();i++){
			if(vnos.charAt(i) == ' '){
				vnosi.add(niz.trim());
				niz = "";
				
			}else{
				niz+= vnos.charAt(i);
			}
		}
		int stevec = 0;
		novo:
		for(int i = 0; i<getGradivo().getKnjige().size();i++){
			ArrayList<String> teme = new ArrayList<>();
			teme = getGradivo().getKnjige().get(i).getLastnosti().getSkupina();
			for(int l = 0; l < getGradivo().getKnjige().get(i).getLastnosti().getSkupina().size() ; l++){
				String tema = getGradivo().getKnjige().get(i).getLastnosti().getSkupina().get(l);
				tema +=" ";
				String glejNiz ="";
				for(int k = 0 ; k< tema.length();k++){
					if(tema.charAt(k) == ' '){
						glejNiz = glejNiz.toLowerCase();
						for(int j = 0 ; j<vnosi.size();j++){
							if(glejNiz.equals(vnosi.get(j))){
								knjige.add(i);
								continue novo;
							}
						}
						glejNiz ="";
							
					}else{
						glejNiz += tema.charAt(k);	
					}	
				}
			}			
		}
		novo1:
		for(int i = 0; i<getGradivo().getAKnjige().size();i++){
			ArrayList<String> teme = new ArrayList<>();
			teme = getGradivo().getAKnjige().get(i).getLastnosti().getSkupina();
			for(int l = 0; l < getGradivo().getAKnjige().get(i).getLastnosti().getSkupina().size() ; l++){
				String tema = getGradivo().getAKnjige().get(i).getLastnosti().getSkupina().get(l);
				tema +=" ";
				String glejNiz ="";
				for(int k = 0 ; k< tema.length();k++){
					if(tema.charAt(k) == ' '){
						glejNiz = glejNiz.toLowerCase();
						for(int j = 0 ; j<vnosi.size();j++){
							if(glejNiz.equals(vnosi.get(j))){
								aKnjige.add(i);
								continue novo1;
							}
						}
						glejNiz ="";
							
					}else{
						glejNiz += tema.charAt(k);	
					}	
				}
			}				
		}
		novo2:
		for(int i = 0; i<getGradivo().getKarte().size();i++){
			ArrayList<String> teme = new ArrayList<>();
			teme = getGradivo().getKarte().get(i).getLastnosti().getSkupina();
			for(int l = 0; l < getGradivo().getKarte().get(i).getLastnosti().getSkupina().size() ; l++){
				String tema = getGradivo().getKarte().get(i).getLastnosti().getSkupina().get(l);
				tema +=" ";
				String glejNiz ="";
				for(int k = 0 ; k< tema.length();k++){
					if(tema.charAt(k) == ' '){
						glejNiz = glejNiz.toLowerCase();
						for(int j = 0 ; j<vnosi.size();j++){
							if(glejNiz.equals(vnosi.get(j))){
								karte.add(i);
								continue novo2;
							}
						}
						glejNiz ="";
							
					}else{
						glejNiz += tema.charAt(k);	
					}	
				}
			}			
		}
		novo3:
		for(int i = 0; i<getGradivo().getPublikacije().size();i++){
			ArrayList<String> teme = new ArrayList<>();
			teme = getGradivo().getPublikacije().get(i).getLastnosti().getSkupina();
			for(int l = 0; l < getGradivo().getPublikacije().get(i).getLastnosti().getSkupina().size() ; l++){
				String tema = getGradivo().getPublikacije().get(i).getLastnosti().getSkupina().get(l);
				tema +=" ";
				String glejNiz ="";
				for(int k = 0 ; k< tema.length();k++){
					if(tema.charAt(k) == ' '){
						glejNiz = glejNiz.toLowerCase();
						for(int j = 0 ; j<vnosi.size();j++){
							if(glejNiz.equals(vnosi.get(j))){
								publikacije.add(i);
								continue novo3;
							}
						}
						glejNiz ="";
							
					}else{
						glejNiz += tema.charAt(k);	
					}	
				}
			}	
		}
		novo4:
		for(int i = 0; i<getGradivo().getEKnjige().size();i++){
			ArrayList<String> teme = new ArrayList<>();
			teme = getGradivo().getEKnjige().get(i).getLastnosti().getSkupina();
			for(int l = 0; l < getGradivo().getEKnjige().get(i).getLastnosti().getSkupina().size() ; l++){
				String tema = getGradivo().getEKnjige().get(i).getLastnosti().getSkupina().get(l);
				tema +=" ";
				String glejNiz ="";
				for(int k = 0 ; k< tema.length();k++){
					if(tema.charAt(k) == ' '){
						glejNiz = glejNiz.toLowerCase();
						for(int j = 0 ; j<vnosi.size();j++){
							if(glejNiz.equals(vnosi.get(j))){
								eKnjige.add(i);
								continue novo4;
							}
						}
						glejNiz ="";
							
					}else{
						glejNiz += tema.charAt(k);	
					}	
				}
			}		
		}
		
		
		int steviloNajdenih = knjige.size() + karte.size() + publikacije.size() + aKnjige.size() + eKnjige.size();
		int stevecS = 0;
		konec:
		novaStran:
		while(stevec< steviloNajdenih){
			stevec++;
			System.out.println(" ----------------------------------------------------- ");
			System.out.println("| Zaporedna stevilka " + stevec);
			if(stevec <= knjige.size()){
				getGradivo().getKnjige().get(knjige.get(stevec-1)).izpis();
			}else if(stevec > knjige.size() && stevec <= (knjige.size()+karte.size())){
				getGradivo().getKarte().get(karte.get(stevec-1-knjige.size())).izpis();
			}else if(stevec > (knjige.size()+karte.size()) && stevec <= (knjige.size()+karte.size()+publikacije.size())){
				getGradivo().getPublikacije().get(publikacije.get(stevec-1-knjige.size()-karte.size())).izpis();
			}else if(stevec > (knjige.size()+karte.size()+publikacije.size()) && stevec <= (knjige.size()+karte.size()+publikacije.size()+ aKnjige.size())){
				getGradivo().getAKnjige().get(aKnjige.get(stevec-1-knjige.size()-karte.size()-publikacije.size())).izpis();
			}else{
				getGradivo().getEKnjige().get(eKnjige.get(stevec-1-knjige.size()-karte.size()-publikacije.size()-aKnjige.size())).izpis();
			}
				
			if(stevec%5 == 0  || stevec == steviloNajdenih){
				stevecS++;
				System.out.println("| Stran "+stevecS);
				System.out.println(" ----------------------------------------------------- ");
				
				System.out.println("| Pritisnite (i) za izposojo gradiva                  |");
				System.out.println("| Pritisnite (r) za rezervacijo gradiva               |");
				if(stevec > 5){
					System.out.println("| Pritisnite (b) za prejsnjo stran                    |");
				}if(stevec != steviloNajdenih){
					System.out.println("| Pritisnite (n) za naslednjo stran                   |");
				}	
				System.out.println("| Pritisnite (x) za zakljucitev iskanja               |");	
				System.out.println(" ----------------------------------------------------- ");
				String input = br.readLine().toLowerCase();
				if(input.length() == 1){
					char znak = input.charAt(0);
					switch(znak){
						case 'b':
							if(stevec <= 5){
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Napacen vnos                                        |");
								System.out.println(" ----------------------------------------------------- ");
								break;
							}
							stevec -= 10;
							stevecS -= 2;
							if(stevecS < 2){
								stevecS=0;
							}
							if(stevec < 0){
								stevec = 0;
							}
							break;
						case 'n':
							if(stevec == steviloNajdenih){
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Napacen vnos                                        |");
								System.out.println(" ----------------------------------------------------- ");
								break;
							}
							continue novaStran;
						case 'i':
							System.out.println(" ----------------------------------------------------- ");
							System.out.println("| Vpisite indeks gradiva na strani                    |");
							System.out.println(" ----------------------------------------------------- ");
							niNaVoljo:
							
							dodaj:
							while(true){
								try{
									int g = Integer.parseInt(br.readLine().trim());
									if(g <= knjige.size()){
										int i = knjige.get(g-1);
										Knjiga k = new Knjiga();
										Status s = new Status();
										rez:
										for(int b = 0; b < getGradivo().getKnjige().get(i).getLastnosti().getStatus().size();b++){
											for(int r = 0; r < u.getGradivo().getKnjige().size() ; r++){
												for(int p = 0; p < u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().size();p++){
													if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
														if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}	
													}else if(!(u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno())){
														if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}	
													}else if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
														System.out.println("| Gradivo imate rezervirano |");
														System.out.println("| si ga zelite izposoditi? |");
														while(true){
														System.out.println("| vtipkajte (d) za izposojo ali (n) da si ne izposodite gradiva |");
														String dane = br.readLine().toLowerCase().trim();
														if(dane.length() == 1){
															char dn = dane.charAt(0);
															switch(dn){
																case 'd':
																	getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).setRezervirano(false);
																	u.getGradivo().getKnjige().remove(r);
																	break rez;
																case 'n':
																	break konec;
																default:
																	System.out.println(" ----------------------------------------------------- ");
																	System.out.println("| Napacen vnos poskusite ponovno                      |");
																	System.out.println(" ----------------------------------------------------- ");		
															
															}
														}else{
															System.out.println(" ----------------------------------------------------- ");
															System.out.println("| Napacen vnos poskusite ponovno                      |");
															System.out.println(" ----------------------------------------------------- ");
														}
														
														}
													}
												}		
											}
											
										}
										k.getLastnosti().setNaslov(getGradivo().getKnjige().get(i).getLastnosti().getNaslov());
										k.getLastnosti().setAvtor(getGradivo().getKnjige().get(i).getLastnosti().getAvtor());
										k.getLastnosti().setZalozba(getGradivo().getKnjige().get(i).getLastnosti().getZalozba());
										k.getLastnosti().setSkupina(getGradivo().getKnjige().get(i).getLastnosti().getSkupina());
										for(int j = 0 ; j <getGradivo().getKnjige().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
												if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
													if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo ni na voljo                                 |");
														System.out.println(" ----------------------------------------------------- ");
														break konec;
													}
													continue;
												}
												s.setSerijskaSt(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
												s.setIzposojeno(false);
												getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).setRezervirano(false);
												getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
												k.getLastnosti().getStatus().add(s);
												u.getGradivo().getKnjige().add(k);
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" izposojeno");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}
											if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo ni na voljo                                 |");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}	
										}

									}else if(g > knjige.size() && g <= (knjige.size()+karte.size())){
										int i = karte.get(g-1-knjige.size());
										Karta k = new Karta();
										Status s = new Status();
										rez:
										for(int b = 0; b < getGradivo().getKarte().get(i).getLastnosti().getStatus().size();b++){
											for(int r = 0; r < u.getGradivo().getKarte().size() ; r++){
												for(int p = 0; p < u.getGradivo().getKarte().get(r).getLastnosti().getStatus().size();p++){
													if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
														if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}
													}else if(!(u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getIzposojeno())){
														if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}		
													}else if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
														System.out.println("| Gradivo imate rezervirano |");
														System.out.println("| si ga zelite izposoditi? |");
														while(true){
														System.out.println("| vtipkajte (d) za izposojo ali (n) da si ne izposodite gradiva |");
														String dane = br.readLine().toLowerCase().trim();
														if(dane.length() == 1){
															char dn = dane.charAt(0);
															switch(dn){
																case 'd':
																	getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).setRezervirano(false);
																	u.getGradivo().getKarte().remove(r);
																	break rez;
																case 'n':
																	break konec;
																default:
																	System.out.println(" ----------------------------------------------------- ");
																	System.out.println("| Napacen vnos poskusite ponovno                      |");
																	System.out.println(" ----------------------------------------------------- ");		
															
															}
														}else{
															System.out.println(" ----------------------------------------------------- ");
															System.out.println("| Napacen vnos poskusite ponovno                      |");
															System.out.println(" ----------------------------------------------------- ");
														}
														
														}
													}
												}		
											}
											
										}
										k.getLastnosti().setNaslov(getGradivo().getKarte().get(i).getLastnosti().getNaslov());
										k.getLastnosti().setAvtor(getGradivo().getKarte().get(i).getLastnosti().getAvtor());
										k.getLastnosti().setZalozba(getGradivo().getKarte().get(i).getLastnosti().getZalozba());
										k.getLastnosti().setSkupina(getGradivo().getKarte().get(i).getLastnosti().getSkupina());
										for(int j = 0 ; j <getGradivo().getKarte().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
												if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
													if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo ni na voljo                                 |");
														System.out.println(" ----------------------------------------------------- ");
														break;
													}
													continue;
												}
												s.setSerijskaSt(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
												s.setIzposojeno(false);
												getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
												k.getLastnosti().getStatus().add(s);
												u.getGradivo().getKarte().add(k);
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" izposojeno");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}
											if((j+1)== getGradivo().getKarte().get(i).getLastnosti().getStatus().size()){
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo ni na voljo                                 |");
												System.out.println(" ----------------------------------------------------- ");
											}	
										}

									}else if(g > (knjige.size()+karte.size()) && g <= (knjige.size()+karte.size()+publikacije.size())){
										int i = publikacije.get(g-1-knjige.size()-karte.size());
										Publikacija k = new Publikacija();
										Status s = new Status();
										rez:
										for(int b = 0; b < getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size();b++){
											for(int r = 0; r < u.getGradivo().getPublikacije().size() ; r++){
												for(int p = 0; p < u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().size();p++){
													if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
														if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}
													}else if(!(u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getIzposojeno())){
														if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}
													}else if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
														System.out.println("| Gradivo imate rezervirano |");
														System.out.println("| si ga zelite izposoditi? |");
														while(true){
														System.out.println("| vtipkajte (d) za izposojo ali (n) da si ne izposodite gradiva |");
														String dane = br.readLine().toLowerCase().trim();
														if(dane.length() == 1){
															char dn = dane.charAt(0);
															switch(dn){
																case 'd':
																	getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).setRezervirano(false);
																	u.getGradivo().getPublikacije().remove(r);
																	break rez;
																case 'n':
																	break konec;
																default:
																	System.out.println(" ----------------------------------------------------- ");
																	System.out.println("| Napacen vnos poskusite ponovno                      |");
																	System.out.println(" ----------------------------------------------------- ");		
															
															}
														}else{
															System.out.println(" ----------------------------------------------------- ");
															System.out.println("| Napacen vnos poskusite ponovno                      |");
															System.out.println(" ----------------------------------------------------- ");
														}
														
														}
													}
												}		
											}
											
										}
										k.getLastnosti().setNaslov(getGradivo().getPublikacije().get(i).getLastnosti().getNaslov());
										k.getLastnosti().setAvtor(getGradivo().getPublikacije().get(i).getLastnosti().getAvtor());
										k.getLastnosti().setZalozba(getGradivo().getPublikacije().get(i).getLastnosti().getZalozba());
										k.getLastnosti().setSkupina(getGradivo().getPublikacije().get(i).getLastnosti().getSkupina());
										for(int j = 0 ; j <getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
												if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
													if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo ni na voljo                                 |");
														System.out.println(" ----------------------------------------------------- ");
														break;
													}
													continue;
												}
												s.setSerijskaSt(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
												s.setIzposojeno(false);
												getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
												k.getLastnosti().getStatus().add(s);
												u.getGradivo().getPublikacije().add(k);
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" izposojeno");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}
											if((j+1)== getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size()){
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo ni na voljo                                 |");
												System.out.println(" ----------------------------------------------------- ");
											}	
										}

									}else if(g > (knjige.size()+karte.size()+publikacije.size()) && g <= (knjige.size()+karte.size()+publikacije.size()+ aKnjige.size())){
										int i = aKnjige.get(g-1-knjige.size()-karte.size()-publikacije.size());
										AKnjiga k = new AKnjiga();
										Status s = new Status();
										rez:
										for(int b = 0; b < getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size();b++){
											for(int r = 0; r < u.getGradivo().getAKnjige().size() ; r++){
												for(int p = 0; p < u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().size();p++){
													if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
														if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}	
													}else if(!(u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno())){
														if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}
													}else if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
														System.out.println("| Gradivo imate rezervirano |");
														System.out.println("| si ga zelite izposoditi? |");
														while(true){
														System.out.println("| vtipkajte (d) za izposojo ali (n) da si ne izposodite gradiva |");
														String dane = br.readLine().toLowerCase().trim();
														if(dane.length() == 1){
															char dn = dane.charAt(0);
															switch(dn){
																case 'd':
																	getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).setRezervirano(false);
																	u.getGradivo().getAKnjige().remove(r);
																	break rez;
																case 'n':
																	break konec;
																default:
																	System.out.println(" ----------------------------------------------------- ");
																	System.out.println("| Napacen vnos poskusite ponovno                      |");
																	System.out.println(" ----------------------------------------------------- ");		
															
															}
														}else{
															System.out.println(" ----------------------------------------------------- ");
															System.out.println("| Napacen vnos poskusite ponovno                      |");
															System.out.println(" ----------------------------------------------------- ");
														}
														
														}
													}
												}		
											}
											
										}
										k.getLastnosti().setNaslov(getGradivo().getAKnjige().get(i).getLastnosti().getNaslov());
										k.getLastnosti().setAvtor(getGradivo().getAKnjige().get(i).getLastnosti().getAvtor());
										k.getLastnosti().setZalozba(getGradivo().getAKnjige().get(i).getLastnosti().getZalozba());
										k.getLastnosti().setSkupina(getGradivo().getAKnjige().get(i).getLastnosti().getSkupina());
										for(int j = 0 ; j <getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
												if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
													if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo ni na voljo                                 |");
														System.out.println(" ----------------------------------------------------- ");
														break;
													}
													continue;
												}
												s.setSerijskaSt(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
												s.setIzposojeno(false);
												getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
												k.getLastnosti().getStatus().add(s);
												u.getGradivo().getAKnjige().add(k);
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" izposojeno");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}
											if((j+1)== getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size()){
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo ni na voljo                                 |");
												System.out.println(" ----------------------------------------------------- ");
											}	
										}

									}else if(g > (knjige.size()+karte.size()+publikacije.size()+ aKnjige.size()) && g <= (knjige.size()+karte.size()+publikacije.size()+ aKnjige.size()+eKnjige.size())){
										int i = eKnjige.get(g-1-knjige.size()-karte.size()-publikacije.size());
										EKnjiga k = new EKnjiga();
										Status s = new Status();
										rez:
										for(int b = 0; b < getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size();b++){
											for(int r = 0; r < u.getGradivo().getEKnjige().size() ; r++){
												for(int p = 0; p < u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().size();p++){
													if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
														if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}
													}else if(!(u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno())){
														if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}
													}else if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
														System.out.println("| Gradivo imate rezervirano |");
														System.out.println("| si ga zelite izposoditi? |");
														while(true){
														System.out.println("| vtipkajte (d) za izposojo ali (n) da si ne izposodite gradiva |");
														String dane = br.readLine().toLowerCase().trim();
														if(dane.length() == 1){
															char dn = dane.charAt(0);
															switch(dn){
																case 'd':
																	getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).setRezervirano(false);
																	u.getGradivo().getEKnjige().remove(r);
																	break rez;
																case 'n':
																	break konec;
																default:
																	System.out.println(" ----------------------------------------------------- ");
																	System.out.println("| Napacen vnos poskusite ponovno                      |");
																	System.out.println(" ----------------------------------------------------- ");		
															
															}
														}else{
															System.out.println(" ----------------------------------------------------- ");
															System.out.println("| Napacen vnos poskusite ponovno                      |");
															System.out.println(" ----------------------------------------------------- ");
														}
														
														}
													}
												}		
											}
											
										}
										k.getLastnosti().setNaslov(getGradivo().getEKnjige().get(i).getLastnosti().getNaslov());
										k.getLastnosti().setAvtor(getGradivo().getEKnjige().get(i).getLastnosti().getAvtor());
										k.getLastnosti().setZalozba(getGradivo().getEKnjige().get(i).getLastnosti().getZalozba());
										k.getLastnosti().setSkupina(getGradivo().getEKnjige().get(i).getLastnosti().getSkupina());
										for(int j = 0 ; j <getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
												if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
													if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo ni na voljo                                 |");
														System.out.println(" ----------------------------------------------------- ");
														break;
													}
													continue;
												}
												s.setSerijskaSt(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
												s.setIzposojeno(false);
												getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
												k.getLastnosti().getStatus().add(s);
												u.getGradivo().getEKnjige().add(k);
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" izposojeno");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}
											if((j+1)== getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size()){
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo ni na voljo                                 |");
												System.out.println(" ----------------------------------------------------- ");
											}	
										}
										
									}else{
										break konec;
									}
									
									
								} catch(NumberFormatException e){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Napacen vnos poskusite ponovno                      |");
									System.out.println(" ----------------------------------------------------- ");
									
								}catch(IndexOutOfBoundsException e ){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Knjiga s tem indeksom ne obstaja, poskusite ponovno |");
									System.out.println(" ----------------------------------------------------- ");
									
								}
							
								
							}
						case 'r':
							System.out.println(" ----------------------------------------------------- ");
							System.out.println("| Vpisite indeks gradiva na strani                    |");
							System.out.println(" ----------------------------------------------------- ");
							niNaVoljo:
							
							dodaj:
							while(true){
								try{
									int g = Integer.parseInt(br.readLine().trim());
									if(g <= knjige.size()){
										int i = knjige.get(g-1);
										Knjiga k = new Knjiga();
										Status s = new Status();
										for(int b = 0; b < getGradivo().getKnjige().get(i).getLastnosti().getStatus().size();b++){
											for(int r = 0; r < u.getGradivo().getKnjige().size() ; r++){
												for(int p = 0; p < u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().size();p++){
													if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
														if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}	
													}else if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
														if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze rezervirano |");
															break konec;
														}
													}
												}		
											}	
										}
										k.getLastnosti().setNaslov(getGradivo().getKnjige().get(i).getLastnosti().getNaslov());
										k.getLastnosti().setAvtor(getGradivo().getKnjige().get(i).getLastnosti().getAvtor());
										k.getLastnosti().setZalozba(getGradivo().getKnjige().get(i).getLastnosti().getZalozba());
										k.getLastnosti().setSkupina(getGradivo().getKnjige().get(i).getLastnosti().getSkupina());
										for(int j = 0 ; j <getGradivo().getKnjige().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
												if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
													if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo ni na voljo                                 |");
														System.out.println(" ----------------------------------------------------- ");
														break;
													}
													continue;
												}
												s.setSerijskaSt(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
												s.setRezervirano(true);
												s.setIzposojeno(true);
												getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).setRezervirano(true);
												k.getLastnosti().getStatus().add(s);
												u.getGradivo().getKnjige().add(k);
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" rezervirano ");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}
											if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo ni na voljo                                 |");
												System.out.println(" ----------------------------------------------------- ");
											}	
										}
									}else if(g > knjige.size() && g <= (knjige.size()+karte.size())){
										int i = karte.get(g-1-knjige.size());
										Karta k = new Karta();
										Status s = new Status();
										for(int b = 0; b < getGradivo().getKarte().get(i).getLastnosti().getStatus().size();b++){
											for(int r = 0; r < u.getGradivo().getKarte().size() ; r++){
												for(int p = 0; p < u.getGradivo().getKarte().get(r).getLastnosti().getStatus().size();p++){
													if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
														if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}	
													}else if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
														if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze rezervirano |");
															break konec;
														}
													}
												}		
											}	
										}
										k.getLastnosti().setNaslov(getGradivo().getKarte().get(i).getLastnosti().getNaslov());
										k.getLastnosti().setAvtor(getGradivo().getKarte().get(i).getLastnosti().getAvtor());
										k.getLastnosti().setZalozba(getGradivo().getKarte().get(i).getLastnosti().getZalozba());
										k.getLastnosti().setSkupina(getGradivo().getKarte().get(i).getLastnosti().getSkupina());
										for(int j = 0 ; j <getGradivo().getKarte().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
												if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
													if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo ni na voljo                                 |");
														System.out.println(" ----------------------------------------------------- ");
														break;
													}
													continue;
												}
												s.setSerijskaSt(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
												s.setRezervirano(true);
												s.setIzposojeno(true);
												getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).setRezervirano(true);
												k.getLastnosti().getStatus().add(s);
												u.getGradivo().getKarte().add(k);
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" rezervirano ");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}
											if((j+1)== getGradivo().getKarte().get(i).getLastnosti().getStatus().size()){
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo ni na voljo                                 |");
												System.out.println(" ----------------------------------------------------- ");
											}	
										}
									}else if(g > (knjige.size()+karte.size()) && g <= (knjige.size()+karte.size()+publikacije.size())){
										int i = publikacije.get(g-1-knjige.size()-karte.size());
										Publikacija k = new Publikacija();
										Status s = new Status();
										for(int b = 0; b < getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size();b++){
											for(int r = 0; r < u.getGradivo().getPublikacije().size() ; r++){
												for(int p = 0; p < u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().size();p++){
													if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
														if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}	
													}else if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
														if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze rezervirano |");
															break konec;
														}
													}
												}		
											}	
										}
										k.getLastnosti().setNaslov(getGradivo().getPublikacije().get(i).getLastnosti().getNaslov());
										k.getLastnosti().setAvtor(getGradivo().getPublikacije().get(i).getLastnosti().getAvtor());
										k.getLastnosti().setZalozba(getGradivo().getPublikacije().get(i).getLastnosti().getZalozba());
										k.getLastnosti().setSkupina(getGradivo().getPublikacije().get(i).getLastnosti().getSkupina());
										for(int j = 0 ; j <getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
												if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
													if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo ni na voljo                                 |");
														System.out.println(" ----------------------------------------------------- ");
														break;
													}
													continue;
												}
												s.setSerijskaSt(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
												s.setRezervirano(true);
												s.setIzposojeno(true);
												getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).setRezervirano(true);
												k.getLastnosti().getStatus().add(s);
												u.getGradivo().getPublikacije().add(k);
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" rezervirano ");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}
											if((j+1)== getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size()){
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo ni na voljo                                 |");
												System.out.println(" ----------------------------------------------------- ");
											}	
										}										
									}else if(g > (knjige.size()+karte.size()+publikacije.size()) && g <= (knjige.size()+karte.size()+publikacije.size()+ aKnjige.size())){
										int i = aKnjige.get(g-1-knjige.size()-karte.size()-publikacije.size());
										AKnjiga k = new AKnjiga();
										Status s = new Status();
										for(int b = 0; b < getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size();b++){
											for(int r = 0; r < u.getGradivo().getAKnjige().size() ; r++){
												for(int p = 0; p < u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().size();p++){
													if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
														if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}	
													}else if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
														if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze rezervirano |");
															break konec;
														}
													}
												}		
											}	
										}
										k.getLastnosti().setNaslov(getGradivo().getAKnjige().get(i).getLastnosti().getNaslov());
										k.getLastnosti().setAvtor(getGradivo().getAKnjige().get(i).getLastnosti().getAvtor());
										k.getLastnosti().setZalozba(getGradivo().getAKnjige().get(i).getLastnosti().getZalozba());
										k.getLastnosti().setSkupina(getGradivo().getAKnjige().get(i).getLastnosti().getSkupina());
										for(int j = 0 ; j <getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
												if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
													if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo ni na voljo                                 |");
														System.out.println(" ----------------------------------------------------- ");
														break;
													}
													continue;
												}
												s.setSerijskaSt(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
												s.setRezervirano(true);
												s.setIzposojeno(true);
												getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).setRezervirano(true);
												k.getLastnosti().getStatus().add(s);
												u.getGradivo().getAKnjige().add(k);
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" rezervirano ");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}
											if((j+1)== getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size()){
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo ni na voljo                                 |");
												System.out.println(" ----------------------------------------------------- ");
											}	
										}
									}else if(g > (knjige.size()+karte.size()+publikacije.size()+ aKnjige.size()) && g <= (knjige.size()+karte.size()+publikacije.size()+ aKnjige.size()+eKnjige.size())){
										int i = eKnjige.get(g-1-knjige.size()-karte.size()-publikacije.size());
										EKnjiga k = new EKnjiga();
										Status s = new Status();
										for(int b = 0; b < getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size();b++){
											for(int r = 0; r < u.getGradivo().getEKnjige().size() ; r++){
												for(int p = 0; p < u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().size();p++){
													if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
														if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}	
													}else if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
														if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze rezervirano |");
															break konec;
														}
													}
												}		
											}	
										}
										k.getLastnosti().setNaslov(getGradivo().getEKnjige().get(i).getLastnosti().getNaslov());
										k.getLastnosti().setAvtor(getGradivo().getEKnjige().get(i).getLastnosti().getAvtor());
										k.getLastnosti().setZalozba(getGradivo().getEKnjige().get(i).getLastnosti().getZalozba());
										k.getLastnosti().setSkupina(getGradivo().getEKnjige().get(i).getLastnosti().getSkupina());
										for(int j = 0 ; j <getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
												if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
													if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo ni na voljo                                 |");
														System.out.println(" ----------------------------------------------------- ");
														break;
													}
													continue;
												}
												s.setSerijskaSt(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
												s.setRezervirano(true);
												s.setIzposojeno(true);
												getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).setRezervirano(true);
												k.getLastnosti().getStatus().add(s);
												u.getGradivo().getEKnjige().add(k);
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" rezervirano ");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}
											if((j+1)== getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size()){
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo ni na voljo                                 |");
												System.out.println(" ----------------------------------------------------- ");
											}	
										}
										
									}else{
										break konec;
									}
									} catch(NumberFormatException e){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Napacen vnos poskusite ponovno                      |");
									System.out.println(" ----------------------------------------------------- ");
									
								}catch(IndexOutOfBoundsException e ){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Knjiga s tem indeksom ne obstaja, poskusite ponovno |");
									System.out.println(" ----------------------------------------------------- ");
									
								}
							}
								
										
						case 'x':
							return;
						default:
							System.out.println(" ----------------------------------------------------- ");
							System.out.println("| Napacen vnos                                        |");
							System.out.println(" ----------------------------------------------------- ");
							break;
								
					}
				
				}else{
					System.out.println(" ----------------------------------------------------- ");
					System.out.println("| Napacen vnos poskusite ponovno                      |");
					System.out.println(" ----------------------------------------------------- ");
				}
			}
		}
	}
	public void pregledKnjig(Uporabnik u) throws Exception{
		
		konec:
		while(true){
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int stevec = 0;
			int stevecS =0;
			novaStran:
			for(int i = 0; i < getGradivo().getKnjige().size();i++){
				stevec++;
				System.out.println(" ----------------------------------------------------- ");
				System.out.println("| Zaporedna stevilka " + stevec);
				getGradivo().getKnjige().get(i).izpis();

				if(stevec%5 == 0  || stevec == getGradivo().getKnjige().size()){
					stevecS++;
					System.out.println("| Stran "+stevecS);
					System.out.println(" ----------------------------------------------------- ");
					
					System.out.println("| Pritisnite (i) za izposojo gradiva                  |");
					System.out.println("| Pritisnite (r) za rezervacijo gradiva               |");
					if(stevec > 5){
						System.out.println("| Pritisnite (b) za prejsnjo stran                    |");
					}if(stevec != getGradivo().getKnjige().size()){
						System.out.println("| Pritisnite (n) za naslednjo stran                   |");
					}	
					System.out.println("| Pritisnite (x) za zakljucitev iskanja               |");	
					System.out.println(" ----------------------------------------------------- ");
					String input = br.readLine().toLowerCase();
					if(input.length() == 1){
						char znak = input.charAt(0);
						switch(znak){
							case 'b':
								if(stevec <= 5){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Napacen vnos                                        |");
									System.out.println(" ----------------------------------------------------- ");
									break;
								}
								stevec -= 10;
								stevecS -= 2;
								if(stevecS < 2){
									stevecS=0;
								}
								if(stevec < 0){
									stevec = 0;
								}
								break;
							case 'n':
								if(stevec == getGradivo().getKnjige().size()){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Napacen vnos                                        |");
									System.out.println(" ----------------------------------------------------- ");
									break;
								}
								continue novaStran;
							case 'i':
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Vpisite indeks gradiva na strani                    |");
								System.out.println(" ----------------------------------------------------- ");
								niNaVoljo:
								
								dodaj:
								while(true){
									try{
										int g = Integer.parseInt(br.readLine().trim());
										g -= 1;
											Knjiga k = new Knjiga();
											Status s = new Status();
											rez:
											for(int b = 0; b < getGradivo().getKnjige().get(i).getLastnosti().getStatus().size();b++){
												for(int r = 0; r < u.getGradivo().getKnjige().size() ; r++){
													for(int p = 0; p < u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().size();p++){
														if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
															if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
																System.out.println("| Gradivo imate ze izposojeno |");
																break konec;
															}	
														}else if(!(u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno())){
															if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
																System.out.println("| Gradivo imate ze izposojeno |");
																break konec;
															}	
														}else if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
															System.out.println("| Gradivo imate rezervirano |");
															System.out.println("| si ga zelite izposoditi? |");
															while(true){
															System.out.println("| vtipkajte (d) za izposojo ali (n) da si ne izposodite gradiva |");
															String dane = br.readLine().toLowerCase().trim();
															if(dane.length() == 1){
																char dn = dane.charAt(0);
																switch(dn){
																	case 'd':
																		getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).setRezervirano(false);
																		u.getGradivo().getKnjige().remove(r);
																		break rez;
																	case 'n':
																		break konec;
																	default:
																		System.out.println(" ----------------------------------------------------- ");
																		System.out.println("| Napacen vnos poskusite ponovno                      |");
																		System.out.println(" ----------------------------------------------------- ");		
																
																}
															}else{
																System.out.println(" ----------------------------------------------------- ");
																System.out.println("| Napacen vnos poskusite ponovno                      |");
																System.out.println(" ----------------------------------------------------- ");
															}
															
															}
														}
														
													}		
												}
											}
											k.getLastnosti().setNaslov(getGradivo().getKnjige().get(i).getLastnosti().getNaslov());
														k.getLastnosti().setAvtor(getGradivo().getKnjige().get(i).getLastnosti().getAvtor());
														k.getLastnosti().setZalozba(getGradivo().getKnjige().get(i).getLastnosti().getZalozba());
														k.getLastnosti().setSkupina(getGradivo().getKnjige().get(i).getLastnosti().getSkupina());
														for(int j = 0 ; j <getGradivo().getKnjige().get(i).getLastnosti().getStatus().size();j++){
															if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
																if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
																	if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
																		System.out.println(" ----------------------------------------------------- ");
																		System.out.println("| Gradivo ni na voljo                                 |");
																		System.out.println(" ----------------------------------------------------- ");
																		break konec;
																	}
																	continue;
																}
																s.setSerijskaSt(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
																s.setIzposojeno(false);
																getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).setRezervirano(false);
																getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
																k.getLastnosti().getStatus().add(s);
																u.getGradivo().getKnjige().add(k);
																System.out.println(" ----------------------------------------------------- ");
																System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" izposojeno");
																System.out.println(" ----------------------------------------------------- ");
																break konec;
															}
															if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
																System.out.println(" ----------------------------------------------------- ");
																System.out.println("| Gradivo ni na voljo                                 |");
																System.out.println(" ----------------------------------------------------- ");
																break konec;
															}	
														}

												
									}catch(IndexOutOfBoundsException e){
										System.out.println(" ----------------------------------------------------- ");
										System.out.println("| Napacen vnos poskusite ponovno                      |");
										System.out.println(" ----------------------------------------------------- ");
										break;
									}catch(NumberFormatException e){
										System.out.println(" ----------------------------------------------------- ");
										System.out.println("| Napacen vnos poskusite ponovno                      |");
										System.out.println(" ----------------------------------------------------- ");
										break;
									}	
								}
							case 'r':
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Vpisite indeks gradiva na strani                    |");
								System.out.println(" ----------------------------------------------------- ");
								niNaVoljo:
								
								dodaj:
								while(true){
									try{
										int g = Integer.parseInt(br.readLine().trim());
										i = g - 1;
											
										Knjiga k = new Knjiga();
										Status s = new Status();
										for(int b = 0; b < getGradivo().getKnjige().get(i).getLastnosti().getStatus().size();b++){
											for(int r = 0; r < u.getGradivo().getKnjige().size() ; r++){
												for(int p = 0; p < u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().size();p++){
													if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
														if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}	
													}else if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
														if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze rezervirano |");
															break konec;
														}
													}
												}		
											}	
										}
										k.getLastnosti().setNaslov(getGradivo().getKnjige().get(i).getLastnosti().getNaslov());
										k.getLastnosti().setAvtor(getGradivo().getKnjige().get(i).getLastnosti().getAvtor());
										k.getLastnosti().setZalozba(getGradivo().getKnjige().get(i).getLastnosti().getZalozba());
										k.getLastnosti().setSkupina(getGradivo().getKnjige().get(i).getLastnosti().getSkupina());
										for(int j = 0 ; j <getGradivo().getKnjige().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
												if(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
													if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo ni na voljo                                 |");
														System.out.println(" ----------------------------------------------------- ");
														break;
													}
													continue;
												}
												s.setSerijskaSt(getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
												s.setRezervirano(true);
												s.setIzposojeno(true);
												getGradivo().getKnjige().get(i).getLastnosti().getStatus().get(j).setRezervirano(true);
												k.getLastnosti().getStatus().add(s);
												u.getGradivo().getKnjige().add(k);
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" rezervirano ");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}
											if((j+1)== getGradivo().getKnjige().get(i).getLastnosti().getStatus().size()){
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo ni na voljo                                 |");
												System.out.println(" ----------------------------------------------------- ");
											}	
										}
									}catch(IndexOutOfBoundsException e){
										System.out.println(" ----------------------------------------------------- ");
										System.out.println("| Napacen vnos poskusite ponovno                      |");
										System.out.println(" ----------------------------------------------------- ");
										break;
									}catch(NumberFormatException e){
										System.out.println(" ----------------------------------------------------- ");
										System.out.println("| Napacen vnos poskusite ponovno                      |");
										System.out.println(" ----------------------------------------------------- ");
										break;
									}
								}
							case 'x':
								break konec;
							default	:				
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Napacen vnos poskusite ponovno                      |");
								System.out.println(" ----------------------------------------------------- ");
								break;

						}

					}
				}
			}		
		}
	}		
	
	public void pregledKart(Uporabnik u) throws Exception{
		
		konec:
		while(true){
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int stevec = 0;
			int stevecS =0;
			novaStran:
			for(int i = 0; i < getGradivo().getKarte().size();i++){
				stevec++;
				System.out.println(" ----------------------------------------------------- ");
				System.out.println("| Zaporedna stevilka " + stevec);
				getGradivo().getKarte().get(i).izpis();

				if(stevec%5 == 0  || stevec == getGradivo().getKarte().size()){
					stevecS++;
					System.out.println("| Stran "+stevecS);
					System.out.println(" ----------------------------------------------------- ");
					
					System.out.println("| Pritisnite (i) za izposojo gradiva                  |");
					System.out.println("| Pritisnite (r) za rezervacijo gradiva               |");
					if(stevec > 5){
						System.out.println("| Pritisnite (b) za prejsnjo stran                    |");
					}if(stevec != getGradivo().getKarte().size()){
						System.out.println("| Pritisnite (n) za naslednjo stran                   |");
					}	
					System.out.println("| Pritisnite (x) za zakljucitev iskanja               |");	
					System.out.println(" ----------------------------------------------------- ");
					String input = br.readLine().toLowerCase();
					if(input.length() == 1){
						char znak = input.charAt(0);
						switch(znak){
							case 'b':
								if(stevec <= 5){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Napacen vnos                                        |");
									System.out.println(" ----------------------------------------------------- ");
									break;
								}
								stevec -= 10;
								stevecS -= 2;
								if(stevecS < 2){
									stevecS=0;
								}
								if(stevec < 0){
									stevec = 0;
								}
								break;
							case 'n':
								if(stevec == getGradivo().getKarte().size()){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Napacen vnos                                        |");
									System.out.println(" ----------------------------------------------------- ");
									break;
								}
								continue novaStran;
							case 'i':
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Vpisite indeks gradiva na strani                    |");
								System.out.println(" ----------------------------------------------------- ");
								niNaVoljo:
								
								dodaj:
								while(true){
									try{
										int g = Integer.parseInt(br.readLine().trim());
										g -= 1;
											Karta k = new Karta();
											Status s = new Status();
											rez:
											for(int b = 0; b < getGradivo().getKarte().get(i).getLastnosti().getStatus().size();b++){
												for(int r = 0; r < u.getGradivo().getKarte().size() ; r++){
													for(int p = 0; p < u.getGradivo().getKarte().get(r).getLastnosti().getStatus().size();p++){
														if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
															if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
																System.out.println("| Gradivo imate ze izposojeno |");
																break konec;
															}	
														}else if(!(u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getIzposojeno())){
															if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
																System.out.println("| Gradivo imate ze izposojeno |");
																break konec;
															}	
														}else if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
															System.out.println("| Gradivo imate rezervirano |");
															System.out.println("| si ga zelite izposoditi? |");
															while(true){
															System.out.println("| vtipkajte (d) za izposojo ali (n) da si ne izposodite gradiva |");
															String dane = br.readLine().toLowerCase().trim();
															if(dane.length() == 1){
																char dn = dane.charAt(0);
																switch(dn){
																	case 'd':
																		getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).setRezervirano(false);
																		u.getGradivo().getKarte().remove(r);
																		break rez;
																	case 'n':
																		break konec;
																	default:
																		System.out.println(" ----------------------------------------------------- ");
																		System.out.println("| Napacen vnos poskusite ponovno                      |");
																		System.out.println(" ----------------------------------------------------- ");		
																
																}
															}else{
																System.out.println(" ----------------------------------------------------- ");
																System.out.println("| Napacen vnos poskusite ponovno                      |");
																System.out.println(" ----------------------------------------------------- ");
															}
															
															}
														}
														
													}		
												}
											}
											k.getLastnosti().setNaslov(getGradivo().getKarte().get(i).getLastnosti().getNaslov());
														k.getLastnosti().setAvtor(getGradivo().getKarte().get(i).getLastnosti().getAvtor());
														k.getLastnosti().setZalozba(getGradivo().getKarte().get(i).getLastnosti().getZalozba());
														k.getLastnosti().setSkupina(getGradivo().getKarte().get(i).getLastnosti().getSkupina());
														for(int j = 0 ; j <getGradivo().getKarte().get(i).getLastnosti().getStatus().size();j++){
															if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
																if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
																	if((j+1)== getGradivo().getKarte().get(i).getLastnosti().getStatus().size()){
																		System.out.println(" ----------------------------------------------------- ");
																		System.out.println("| Gradivo ni na voljo                                 |");
																		System.out.println(" ----------------------------------------------------- ");
																		break konec;
																	}
																	continue;
																}
																s.setSerijskaSt(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
																s.setIzposojeno(false);
																getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).setRezervirano(false);
																getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
																k.getLastnosti().getStatus().add(s);
																u.getGradivo().getKarte().add(k);
																System.out.println(" ----------------------------------------------------- ");
																System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" izposojeno");
																System.out.println(" ----------------------------------------------------- ");
																break konec;
															}
															if((j+1)== getGradivo().getKarte().get(i).getLastnosti().getStatus().size()){
																System.out.println(" ----------------------------------------------------- ");
																System.out.println("| Gradivo ni na voljo                                 |");
																System.out.println(" ----------------------------------------------------- ");
																break konec;
															}	
														}

												
									}catch(IndexOutOfBoundsException e){
										System.out.println(" ----------------------------------------------------- ");
										System.out.println("| Napacen vnos poskusite ponovno                      |");
										System.out.println(" ----------------------------------------------------- ");
										break;
									}catch(NumberFormatException e){
										System.out.println(" ----------------------------------------------------- ");
										System.out.println("| Napacen vnos poskusite ponovno                      |");
										System.out.println(" ----------------------------------------------------- ");
										break;
									}	
								}
							case 'r':
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Vpisite indeks gradiva na strani                    |");
								System.out.println(" ----------------------------------------------------- ");
								niNaVoljo:
								
								dodaj:
								while(true){
									try{
										int g = Integer.parseInt(br.readLine().trim());
										i = g - 1;
											
										Karta k = new Karta();
										Status s = new Status();
										for(int b = 0; b < getGradivo().getKarte().get(i).getLastnosti().getStatus().size();b++){
											for(int r = 0; r < u.getGradivo().getKarte().size() ; r++){
												for(int p = 0; p < u.getGradivo().getKarte().get(r).getLastnosti().getStatus().size();p++){
													if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
														if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}	
													}else if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
														if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getKarte().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze rezervirano |");
															break konec;
														}
													}
												}		
											}	
										}
										k.getLastnosti().setNaslov(getGradivo().getKarte().get(i).getLastnosti().getNaslov());
										k.getLastnosti().setAvtor(getGradivo().getKarte().get(i).getLastnosti().getAvtor());
										k.getLastnosti().setZalozba(getGradivo().getKarte().get(i).getLastnosti().getZalozba());
										k.getLastnosti().setSkupina(getGradivo().getKarte().get(i).getLastnosti().getSkupina());
										for(int j = 0 ; j <getGradivo().getKarte().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
												if(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
													if((j+1)== getGradivo().getKarte().get(i).getLastnosti().getStatus().size()){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo ni na voljo                                 |");
														System.out.println(" ----------------------------------------------------- ");
														break;
													}
													continue;
												}
												s.setSerijskaSt(getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
												s.setRezervirano(true);
												s.setIzposojeno(true);
												getGradivo().getKarte().get(i).getLastnosti().getStatus().get(j).setRezervirano(true);
												k.getLastnosti().getStatus().add(s);
												u.getGradivo().getKarte().add(k);
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" rezervirano ");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}
											if((j+1)== getGradivo().getKarte().get(i).getLastnosti().getStatus().size()){
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo ni na voljo                                 |");
												System.out.println(" ----------------------------------------------------- ");
											}	
										}
									}catch(IndexOutOfBoundsException e){
										System.out.println(" ----------------------------------------------------- ");
										System.out.println("| Napacen vnos poskusite ponovno                      |");
										System.out.println(" ----------------------------------------------------- ");
										break;
									}catch(NumberFormatException e){
										System.out.println(" ----------------------------------------------------- ");
										System.out.println("| Napacen vnos poskusite ponovno                      |");
										System.out.println(" ----------------------------------------------------- ");
										break;
									}
								}
							case 'x':
								break konec;
							default	:				
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Napacen vnos poskusite ponovno                      |");
								System.out.println(" ----------------------------------------------------- ");
								break;

						}

					}
				}
			}		
		}
	}
	public void pregledPublikacij(Uporabnik u) throws Exception{
		
		konec:
		while(true){
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int stevec = 0;
			int stevecS =0;
			novaStran:
			for(int i = 0; i < getGradivo().getPublikacije().size();i++){
				stevec++;
				System.out.println(" ----------------------------------------------------- ");
				System.out.println("| Zaporedna stevilka " + stevec);
				getGradivo().getPublikacije().get(i).izpis();

				if(stevec%5 == 0  || stevec == getGradivo().getPublikacije().size()){
					stevecS++;
					System.out.println("| Stran "+stevecS);
					System.out.println(" ----------------------------------------------------- ");
					
					System.out.println("| Pritisnite (i) za izposojo gradiva                  |");
					System.out.println("| Pritisnite (r) za rezervacijo gradiva               |");
					if(stevec > 5){
						System.out.println("| Pritisnite (b) za prejsnjo stran                    |");
					}if(stevec != getGradivo().getPublikacije().size()){
						System.out.println("| Pritisnite (n) za naslednjo stran                   |");
					}	
					System.out.println("| Pritisnite (x) za zakljucitev iskanja               |");	
					System.out.println(" ----------------------------------------------------- ");
					String input = br.readLine().toLowerCase();
					if(input.length() == 1){
						char znak = input.charAt(0);
						switch(znak){
							case 'b':
								if(stevec <= 5){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Napacen vnos                                        |");
									System.out.println(" ----------------------------------------------------- ");
									break;
								}
								stevec -= 10;
								stevecS -= 2;
								if(stevecS < 2){
									stevecS=0;
								}
								if(stevec < 0){
									stevec = 0;
								}
								break;
							case 'n':
								if(stevec == getGradivo().getPublikacije().size()){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Napacen vnos                                        |");
									System.out.println(" ----------------------------------------------------- ");
									break;
								}
								continue novaStran;
							case 'i':
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Vpisite indeks gradiva na strani                    |");
								System.out.println(" ----------------------------------------------------- ");
								niNaVoljo:
								
								dodaj:
								while(true){
									try{
										int g = Integer.parseInt(br.readLine().trim());
										g -= 1;
											Publikacija k = new Publikacija();
											Status s = new Status();
											rez:
											for(int b = 0; b < getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size();b++){
												for(int r = 0; r < u.getGradivo().getPublikacije().size() ; r++){
													for(int p = 0; p < u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().size();p++){
														if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
															if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
																System.out.println("| Gradivo imate ze izposojeno |");
																break konec;
															}	
														}else if(!(u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getIzposojeno())){
															if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
																System.out.println("| Gradivo imate ze izposojeno |");
																break konec;
															}	
														}else if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
															System.out.println("| Gradivo imate rezervirano |");
															System.out.println("| si ga zelite izposoditi? |");
															while(true){
															System.out.println("| vtipkajte (d) za izposojo ali (n) da si ne izposodite gradiva |");
															String dane = br.readLine().toLowerCase().trim();
															if(dane.length() == 1){
																char dn = dane.charAt(0);
																switch(dn){
																	case 'd':
																		getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).setRezervirano(false);
																		u.getGradivo().getPublikacije().remove(r);
																		break rez;
																	case 'n':
																		break konec;
																	default:
																		System.out.println(" ----------------------------------------------------- ");
																		System.out.println("| Napacen vnos poskusite ponovno                      |");
																		System.out.println(" ----------------------------------------------------- ");		
																
																}
															}else{
																System.out.println(" ----------------------------------------------------- ");
																System.out.println("| Napacen vnos poskusite ponovno                      |");
																System.out.println(" ----------------------------------------------------- ");
															}
															
															}
														}
														
													}		
												}
											}
											k.getLastnosti().setNaslov(getGradivo().getPublikacije().get(i).getLastnosti().getNaslov());
														k.getLastnosti().setAvtor(getGradivo().getPublikacije().get(i).getLastnosti().getAvtor());
														k.getLastnosti().setZalozba(getGradivo().getPublikacije().get(i).getLastnosti().getZalozba());
														k.getLastnosti().setSkupina(getGradivo().getPublikacije().get(i).getLastnosti().getSkupina());
														for(int j = 0 ; j <getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size();j++){
															if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
																if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
																	if((j+1)== getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size()){
																		System.out.println(" ----------------------------------------------------- ");
																		System.out.println("| Gradivo ni na voljo                                 |");
																		System.out.println(" ----------------------------------------------------- ");
																		break konec;
																	}
																	continue;
																}
																s.setSerijskaSt(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
																s.setIzposojeno(false);
																getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).setRezervirano(false);
																getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
																k.getLastnosti().getStatus().add(s);
																u.getGradivo().getPublikacije().add(k);
																System.out.println(" ----------------------------------------------------- ");
																System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" izposojeno");
																System.out.println(" ----------------------------------------------------- ");
																break konec;
															}
															if((j+1)== getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size()){
																System.out.println(" ----------------------------------------------------- ");
																System.out.println("| Gradivo ni na voljo                                 |");
																System.out.println(" ----------------------------------------------------- ");
																break konec;
															}	
														}

												
									}catch(IndexOutOfBoundsException e){
										System.out.println(" ----------------------------------------------------- ");
										System.out.println("| Napacen vnos poskusite ponovno                      |");
										System.out.println(" ----------------------------------------------------- ");
										break;
									}catch(NumberFormatException e){
										System.out.println(" ----------------------------------------------------- ");
										System.out.println("| Napacen vnos poskusite ponovno                      |");
										System.out.println(" ----------------------------------------------------- ");
										break;
									}	
								}
							case 'r':
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Vpisite indeks gradiva na strani                    |");
								System.out.println(" ----------------------------------------------------- ");
								niNaVoljo:
								
								dodaj:
								while(true){
									try{
										int g = Integer.parseInt(br.readLine().trim());
										i = g - 1;
											
										Publikacija k = new Publikacija();
										Status s = new Status();
										for(int b = 0; b < getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size();b++){
											for(int r = 0; r < u.getGradivo().getPublikacije().size() ; r++){
												for(int p = 0; p < u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().size();p++){
													if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
														if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}	
													}else if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
														if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getPublikacije().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze rezervirano |");
															break konec;
														}
													}
												}		
											}	
										}
										k.getLastnosti().setNaslov(getGradivo().getPublikacije().get(i).getLastnosti().getNaslov());
										k.getLastnosti().setAvtor(getGradivo().getPublikacije().get(i).getLastnosti().getAvtor());
										k.getLastnosti().setZalozba(getGradivo().getPublikacije().get(i).getLastnosti().getZalozba());
										k.getLastnosti().setSkupina(getGradivo().getPublikacije().get(i).getLastnosti().getSkupina());
										for(int j = 0 ; j <getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
												if(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
													if((j+1)== getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size()){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo ni na voljo                                 |");
														System.out.println(" ----------------------------------------------------- ");
														break;
													}
													continue;
												}
												s.setSerijskaSt(getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
												s.setRezervirano(true);
												s.setIzposojeno(true);
												getGradivo().getPublikacije().get(i).getLastnosti().getStatus().get(j).setRezervirano(true);
												k.getLastnosti().getStatus().add(s);
												u.getGradivo().getPublikacije().add(k);
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" rezervirano ");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}
											if((j+1)== getGradivo().getPublikacije().get(i).getLastnosti().getStatus().size()){
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo ni na voljo                                 |");
												System.out.println(" ----------------------------------------------------- ");
											}	
										}
									}catch(IndexOutOfBoundsException e){
										System.out.println(" ----------------------------------------------------- ");
										System.out.println("| Napacen vnos poskusite ponovno                      |");
										System.out.println(" ----------------------------------------------------- ");
										break;
									}catch(NumberFormatException e){
										System.out.println(" ----------------------------------------------------- ");
										System.out.println("| Napacen vnos poskusite ponovno                      |");
										System.out.println(" ----------------------------------------------------- ");
										break;
									}
								}
							case 'x':
								break konec;
							default	:				
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Napacen vnos poskusite ponovno                      |");
								System.out.println(" ----------------------------------------------------- ");
								break;

						}

					}
				}
			}		
		}
	}
	public void pregledAKnjig(Uporabnik u) throws Exception{
		
		konec:
		while(true){
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int stevec = 0;
			int stevecS =0;
			novaStran:
			for(int i = 0; i < getGradivo().getAKnjige().size();i++){
				stevec++;
				System.out.println(" ----------------------------------------------------- ");
				System.out.println("| Zaporedna stevilka " + stevec);
				getGradivo().getAKnjige().get(i).izpis();

				if(stevec%5 == 0  || stevec == getGradivo().getAKnjige().size()){
					stevecS++;
					System.out.println("| Stran "+stevecS);
					System.out.println(" ----------------------------------------------------- ");
					
					System.out.println("| Pritisnite (i) za izposojo gradiva                  |");
					System.out.println("| Pritisnite (r) za rezervacijo gradiva               |");
					if(stevec > 5){
						System.out.println("| Pritisnite (b) za prejsnjo stran                    |");
					}if(stevec != getGradivo().getAKnjige().size()){
						System.out.println("| Pritisnite (n) za naslednjo stran                   |");
					}	
					System.out.println("| Pritisnite (x) za zakljucitev iskanja               |");	
					System.out.println(" ----------------------------------------------------- ");
					String input = br.readLine().toLowerCase();
					if(input.length() == 1){
						char znak = input.charAt(0);
						switch(znak){
							case 'b':
								if(stevec <= 5){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Napacen vnos                                        |");
									System.out.println(" ----------------------------------------------------- ");
									break;
								}
								stevec -= 10;
								stevecS -= 2;
								if(stevecS < 2){
									stevecS=0;
								}
								if(stevec < 0){
									stevec = 0;
								}
								break;
							case 'n':
								if(stevec == getGradivo().getAKnjige().size()){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Napacen vnos                                        |");
									System.out.println(" ----------------------------------------------------- ");
									break;
								}
								continue novaStran;
							case 'i':
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Vpisite indeks gradiva na strani                    |");
								System.out.println(" ----------------------------------------------------- ");
								niNaVoljo:
								
								dodaj:
								while(true){
									try{
										int g = Integer.parseInt(br.readLine().trim());
										g -= 1;
											AKnjiga k = new AKnjiga();
											Status s = new Status();
											rez:
											for(int b = 0; b < getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size();b++){
												for(int r = 0; r < u.getGradivo().getAKnjige().size() ; r++){
													for(int p = 0; p < u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().size();p++){
														if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
															if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
																System.out.println("| Gradivo imate ze izposojeno |");
																break konec;
															}	
														}else if(!(u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno())){
															if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
																System.out.println("| Gradivo imate ze izposojeno |");
																break konec;
															}	
														}else if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
															System.out.println("| Gradivo imate rezervirano |");
															System.out.println("| si ga zelite izposoditi? |");
															while(true){
															System.out.println("| vtipkajte (d) za izposojo ali (n) da si ne izposodite gradiva |");
															String dane = br.readLine().toLowerCase().trim();
															if(dane.length() == 1){
																char dn = dane.charAt(0);
																switch(dn){
																	case 'd':
																		getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).setRezervirano(false);
																		u.getGradivo().getAKnjige().remove(r);
																		break rez;
																	case 'n':
																		break konec;
																	default:
																		System.out.println(" ----------------------------------------------------- ");
																		System.out.println("| Napacen vnos poskusite ponovno                      |");
																		System.out.println(" ----------------------------------------------------- ");		
																
																}
															}else{
																System.out.println(" ----------------------------------------------------- ");
																System.out.println("| Napacen vnos poskusite ponovno                      |");
																System.out.println(" ----------------------------------------------------- ");
															}
															
															}
														}
														
													}		
												}
											}
											k.getLastnosti().setNaslov(getGradivo().getAKnjige().get(i).getLastnosti().getNaslov());
														k.getLastnosti().setAvtor(getGradivo().getAKnjige().get(i).getLastnosti().getAvtor());
														k.getLastnosti().setZalozba(getGradivo().getAKnjige().get(i).getLastnosti().getZalozba());
														k.getLastnosti().setSkupina(getGradivo().getAKnjige().get(i).getLastnosti().getSkupina());
														for(int j = 0 ; j <getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size();j++){
															if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
																if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
																	if((j+1)== getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size()){
																		System.out.println(" ----------------------------------------------------- ");
																		System.out.println("| Gradivo ni na voljo                                 |");
																		System.out.println(" ----------------------------------------------------- ");
																		break konec;
																	}
																	continue;
																}
																s.setSerijskaSt(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
																s.setIzposojeno(false);
																getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).setRezervirano(false);
																getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
																k.getLastnosti().getStatus().add(s);
																u.getGradivo().getAKnjige().add(k);
																System.out.println(" ----------------------------------------------------- ");
																System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" izposojeno");
																System.out.println(" ----------------------------------------------------- ");
																break konec;
															}
															if((j+1)== getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size()){
																System.out.println(" ----------------------------------------------------- ");
																System.out.println("| Gradivo ni na voljo                                 |");
																System.out.println(" ----------------------------------------------------- ");
																break konec;
															}	
														}

												
									}catch(IndexOutOfBoundsException e){
										System.out.println(" ----------------------------------------------------- ");
										System.out.println("| Napacen vnos poskusite ponovno                      |");
										System.out.println(" ----------------------------------------------------- ");
										break;
									}catch(NumberFormatException e){
										System.out.println(" ----------------------------------------------------- ");
										System.out.println("| Napacen vnos poskusite ponovno                      |");
										System.out.println(" ----------------------------------------------------- ");
										break;
									}	
								}
							case 'r':
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Vpisite indeks gradiva na strani                    |");
								System.out.println(" ----------------------------------------------------- ");
								niNaVoljo:
								
								dodaj:
								while(true){
									try{
										int g = Integer.parseInt(br.readLine().trim());
										i = g - 1;
											
										AKnjiga k = new AKnjiga();
										Status s = new Status();
										for(int b = 0; b < getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size();b++){
											for(int r = 0; r < u.getGradivo().getAKnjige().size() ; r++){
												for(int p = 0; p < u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().size();p++){
													if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
														if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}	
													}else if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
														if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getAKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze rezervirano |");
															break konec;
														}
													}
												}		
											}	
										}
										k.getLastnosti().setNaslov(getGradivo().getAKnjige().get(i).getLastnosti().getNaslov());
										k.getLastnosti().setAvtor(getGradivo().getAKnjige().get(i).getLastnosti().getAvtor());
										k.getLastnosti().setZalozba(getGradivo().getAKnjige().get(i).getLastnosti().getZalozba());
										k.getLastnosti().setSkupina(getGradivo().getAKnjige().get(i).getLastnosti().getSkupina());
										for(int j = 0 ; j <getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
												if(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
													if((j+1)== getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size()){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo ni na voljo                                 |");
														System.out.println(" ----------------------------------------------------- ");
														break;
													}
													continue;
												}
												s.setSerijskaSt(getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
												s.setRezervirano(true);
												s.setIzposojeno(true);
												getGradivo().getAKnjige().get(i).getLastnosti().getStatus().get(j).setRezervirano(true);
												k.getLastnosti().getStatus().add(s);
												u.getGradivo().getAKnjige().add(k);
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" rezervirano ");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}
											if((j+1)== getGradivo().getAKnjige().get(i).getLastnosti().getStatus().size()){
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo ni na voljo                                 |");
												System.out.println(" ----------------------------------------------------- ");
											}	
										}
									}catch(IndexOutOfBoundsException e){
										System.out.println(" ----------------------------------------------------- ");
										System.out.println("| Napacen vnos poskusite ponovno                      |");
										System.out.println(" ----------------------------------------------------- ");
										break;
									}catch(NumberFormatException e){
										System.out.println(" ----------------------------------------------------- ");
										System.out.println("| Napacen vnos poskusite ponovno                      |");
										System.out.println(" ----------------------------------------------------- ");
										break;
									}
								}
							case 'x':
								break konec;
							default	:				
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Napacen vnos poskusite ponovno                      |");
								System.out.println(" ----------------------------------------------------- ");
								break;

						}

					}
				}
			}		
		}
	}

	public void pregledEKnjig(Uporabnik u) throws Exception{
		
		konec:
		while(true){
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int stevec = 0;
			int stevecS =0;
			novaStran:
			for(int i = 0; i < getGradivo().getEKnjige().size();i++){
				stevec++;
				System.out.println(" ----------------------------------------------------- ");
				System.out.println("| Zaporedna stevilka " + stevec);
				getGradivo().getEKnjige().get(i).izpis();

				if(stevec%5 == 0  || stevec == getGradivo().getEKnjige().size()){
					stevecS++;
					System.out.println("| Stran "+stevecS);
					System.out.println(" ----------------------------------------------------- ");
					
					System.out.println("| Pritisnite (i) za izposojo gradiva                  |");
					System.out.println("| Pritisnite (r) za rezervacijo gradiva               |");
					if(stevec > 5){
						System.out.println("| Pritisnite (b) za prejsnjo stran                    |");
					}if(stevec != getGradivo().getEKnjige().size()){
						System.out.println("| Pritisnite (n) za naslednjo stran                   |");
					}	
					System.out.println("| Pritisnite (x) za zakljucitev iskanja               |");	
					System.out.println(" ----------------------------------------------------- ");
					String input = br.readLine().toLowerCase();
					if(input.length() == 1){
						char znak = input.charAt(0);
						switch(znak){
							case 'b':
								if(stevec <= 5){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Napacen vnos                                        |");
									System.out.println(" ----------------------------------------------------- ");
									break;
								}
								stevec -= 10;
								stevecS -= 2;
								if(stevecS < 2){
									stevecS=0;
								}
								if(stevec < 0){
									stevec = 0;
								}
								break;
							case 'n':
								if(stevec == getGradivo().getEKnjige().size()){
									System.out.println(" ----------------------------------------------------- ");
									System.out.println("| Napacen vnos                                        |");
									System.out.println(" ----------------------------------------------------- ");
									break;
								}
								continue novaStran;
							case 'i':
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Vpisite indeks gradiva na strani                    |");
								System.out.println(" ----------------------------------------------------- ");
								niNaVoljo:
								
								dodaj:
								while(true){
									try{
										int g = Integer.parseInt(br.readLine().trim());
										g -= 1;
											EKnjiga k = new EKnjiga();
											Status s = new Status();
											rez:
											for(int b = 0; b < getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size();b++){
												for(int r = 0; r < u.getGradivo().getEKnjige().size() ; r++){
													for(int p = 0; p < u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().size();p++){
														if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
															if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
																System.out.println("| Gradivo imate ze izposojeno |");
																break konec;
															}	
														}else if(!(u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno())){
															if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
																System.out.println("| Gradivo imate ze izposojeno |");
																break konec;
															}	
														}else if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
															System.out.println("| Gradivo imate rezervirano |");
															System.out.println("| si ga zelite izposoditi? |");
															while(true){
															System.out.println("| vtipkajte (d) za izposojo ali (n) da si ne izposodite gradiva |");
															String dane = br.readLine().toLowerCase().trim();
															if(dane.length() == 1){
																char dn = dane.charAt(0);
																switch(dn){
																	case 'd':
																		getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).setRezervirano(false);
																		u.getGradivo().getEKnjige().remove(r);
																		break rez;
																	case 'n':
																		break konec;
																	default:
																		System.out.println(" ----------------------------------------------------- ");
																		System.out.println("| Napacen vnos poskusite ponovno                      |");
																		System.out.println(" ----------------------------------------------------- ");		
																
																}
															}else{
																System.out.println(" ----------------------------------------------------- ");
																System.out.println("| Napacen vnos poskusite ponovno                      |");
																System.out.println(" ----------------------------------------------------- ");
															}
															
															}
														}
														
													}		
												}
											}
											k.getLastnosti().setNaslov(getGradivo().getEKnjige().get(i).getLastnosti().getNaslov());
														k.getLastnosti().setAvtor(getGradivo().getEKnjige().get(i).getLastnosti().getAvtor());
														k.getLastnosti().setZalozba(getGradivo().getEKnjige().get(i).getLastnosti().getZalozba());
														k.getLastnosti().setSkupina(getGradivo().getEKnjige().get(i).getLastnosti().getSkupina());
														for(int j = 0 ; j <getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size();j++){
															if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
																if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
																	if((j+1)== getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size()){
																		System.out.println(" ----------------------------------------------------- ");
																		System.out.println("| Gradivo ni na voljo                                 |");
																		System.out.println(" ----------------------------------------------------- ");
																		break konec;
																	}
																	continue;
																}
																s.setSerijskaSt(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
																s.setIzposojeno(false);
																getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).setRezervirano(false);
																getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).setIzposojeno(false);
																k.getLastnosti().getStatus().add(s);
																u.getGradivo().getEKnjige().add(k);
																System.out.println(" ----------------------------------------------------- ");
																System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" izposojeno");
																System.out.println(" ----------------------------------------------------- ");
																break konec;
															}
															if((j+1)== getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size()){
																System.out.println(" ----------------------------------------------------- ");
																System.out.println("| Gradivo ni na voljo                                 |");
																System.out.println(" ----------------------------------------------------- ");
																break konec;
															}	
														}

												
									}catch(IndexOutOfBoundsException e){
										System.out.println(" ----------------------------------------------------- ");
										System.out.println("| Napacen vnos poskusite ponovno                      |");
										System.out.println(" ----------------------------------------------------- ");
										break;
									}catch(NumberFormatException e){
										System.out.println(" ----------------------------------------------------- ");
										System.out.println("| Napacen vnos poskusite ponovno                      |");
										System.out.println(" ----------------------------------------------------- ");
										break;
									}	
								}
							case 'r':
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Vpisite indeks gradiva na strani                    |");
								System.out.println(" ----------------------------------------------------- ");
								niNaVoljo:
								
								dodaj:
								while(true){
									try{
										int g = Integer.parseInt(br.readLine().trim());
										i = g - 1;
											
										EKnjiga k = new EKnjiga();
										Status s = new Status();
										for(int b = 0; b < getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size();b++){
											for(int r = 0; r < u.getGradivo().getEKnjige().size() ; r++){
												for(int p = 0; p < u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().size();p++){
													if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getIzposojeno() != u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getIzposojeno()){
														if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze izposojeno |");
															break konec;
														}	
													}else if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getRezervirano() == u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getRezervirano()){
														if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(b).getSerijskaSt() == u.getGradivo().getEKnjige().get(r).getLastnosti().getStatus().get(p).getSerijskaSt()) {
															System.out.println("| Gradivo imate ze rezervirano |");
															break konec;
														}
													}
												}		
											}	
										}
										k.getLastnosti().setNaslov(getGradivo().getEKnjige().get(i).getLastnosti().getNaslov());
										k.getLastnosti().setAvtor(getGradivo().getEKnjige().get(i).getLastnosti().getAvtor());
										k.getLastnosti().setZalozba(getGradivo().getEKnjige().get(i).getLastnosti().getZalozba());
										k.getLastnosti().setSkupina(getGradivo().getEKnjige().get(i).getLastnosti().getSkupina());
										for(int j = 0 ; j <getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size();j++){
											if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getIzposojeno()){
												if(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getRezervirano()){
													if((j+1)== getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size()){
														System.out.println(" ----------------------------------------------------- ");
														System.out.println("| Gradivo ni na voljo                                 |");
														System.out.println(" ----------------------------------------------------- ");
														break;
													}
													continue;
												}
												s.setSerijskaSt(getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).getSerijskaSt());
												s.setRezervirano(true);
												s.setIzposojeno(true);
												getGradivo().getEKnjige().get(i).getLastnosti().getStatus().get(j).setRezervirano(true);
												k.getLastnosti().getStatus().add(s);
												u.getGradivo().getEKnjige().add(k);
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo "+k.getLastnosti().getNaslov()+" rezervirano ");
												System.out.println(" ----------------------------------------------------- ");
												break konec;
											}
											if((j+1)== getGradivo().getEKnjige().get(i).getLastnosti().getStatus().size()){
												System.out.println(" ----------------------------------------------------- ");
												System.out.println("| Gradivo ni na voljo                                 |");
												System.out.println(" ----------------------------------------------------- ");
											}	
										}
									}catch(IndexOutOfBoundsException e){
										System.out.println(" ----------------------------------------------------- ");
										System.out.println("| Napacen vnos poskusite ponovno                      |");
										System.out.println(" ----------------------------------------------------- ");
										break;
									}catch(NumberFormatException e){
										System.out.println(" ----------------------------------------------------- ");
										System.out.println("| Napacen vnos poskusite ponovno                      |");
										System.out.println(" ----------------------------------------------------- ");
										break;
									}
								}
							case 'x':
								break konec;
							default	:				
								System.out.println(" ----------------------------------------------------- ");
								System.out.println("| Napacen vnos poskusite ponovno                      |");
								System.out.println(" ----------------------------------------------------- ");
								break;

						}

					}
				}
			}		
		}
	}
}
