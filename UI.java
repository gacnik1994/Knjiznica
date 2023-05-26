import java.io.*;
import java.util.*;

class UI{
	public static void main(String[] args)throws Exception{
		System.out.println(" -----------------------------------------------------");
		System.out.println("|*  _  _  _   _     _  _  ___  _   _  _  ___    _    *|");
		System.out.println("|  | |/ /| \\ | |   | || ||_  || \\ | || |/ __|  / \\    |");
		System.out.println("|  |   | |  \\| |||_| || | / / |  \\| || |||__  / _ \\   |");
		System.out.println("|  |_|\\_\\|_|\\__||____||_||___||_|\\__||_|\\___|/_/ \\_\\  |");
		System.out.println("|*                                                   *|");
		System.out.println(" -----------------------------------------------------");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Knjiznica k = new Knjiznica();
		k.gradivoIzDatoteke();
		k.uporabnikiIzDatoteke();
		Uporabnik u = new Uporabnik();
		u = k.vstop();
		if(u==null){
			return;
		}
		loop:
		while(true)	{
			System.out.println(" -----------------------------------------------------");
			System.out.println("| Pritisnite (i) za iskanje gradiv po naslovih       |");
			System.out.println("| Pritisnite (t) za iskanje gradiv po temah          |");
			System.out.println("| Pritisnite (k) za pregled knjig                    |");
			System.out.println("| Pritisnite (m) za pregled kartografskih gradiv     |");
			System.out.println("| Pritisnite (w) za pregled periodicnih publikacij   |");
			System.out.println("| Pritisnite (e) za pregled e-knjig                  |");
			System.out.println("| Pritisnite (s) za pregled audio knjig              |");
			System.out.println(" -----------------------------------------------------");
			System.out.println("| Pritisnite (p) za pregled izposojenih in           |");
			System.out.println("| rezerviranih gradiv                                |");
			System.out.println(" -----------------------------------------------------");
			if(u.getAdmin()){
				System.out.println("| Pritisnite (a) za orodja administratorja           |");
			}
			System.out.println("| Pritisnite (x) za izhod                            |");
			System.out.println(" -----------------------------------------------------");
			String vnos = br.readLine().trim().toLowerCase();
			if(vnos.length() == 1){
				char znak = vnos.charAt(0);
				switch(znak){
					case 's':
						k.pregledAKnjig(u);
						break;
					case 'e':
						k.pregledEKnjig(u);
						break;	
					case 'w':
						k.pregledPublikacij(u);
						break;
					case 'm':
						k.pregledKart(u);
						break;
					case 'k':
						k.pregledKnjig(u);
						break;
					case 'i':
						k.isciGradivo(u);
						break;
					case 't':
						k.iskanjeTeme(u);
						break;
					case 'p':
						System.out.println(" -----------------------------------------------------");
						System.out.println("| Pritisnite (v) za pregled izposojenih gradiv,      |");
						System.out.println("| ter vracilo                                        |");
						System.out.println("| Pritisnite (i) za pregled rezerviranih gradiv in   |");
						System.out.println("| izposojo                                           |");
						System.out.println("| Pritisnite (x) za nazaj                            |");
						System.out.println(" -----------------------------------------------------");
						iskanje:
						while(true){
							System.out.print("|  ");
							String v = br.readLine().toLowerCase().trim();
							if(v.length() == 1){
								char w = v.charAt(0);
								switch(w){
									case 'v':
										k.odstraniGradivo(u);
									case 'i':
										k.odstraniRezervirano(u);
									case 'x':
										break iskanje;
									default:
										System.out.println(" -----------------------------------------------------");
										System.out.println("| Napacen vnos                                       |");
										System.out.println(" -----------------------------------------------------");
										break;
								}					
							}else{
								System.out.println(" -----------------------------------------------------");
								System.out.println("| Napacen vnos                                       |");
								System.out.println(" -----------------------------------------------------");
							}
						}		
						break;
					case 'a':
						if(!u.getAdmin()){
							System.out.println(" -----------------------------------------------------");
							System.out.println("| Napacen vnos                                       |");
							System.out.println(" -----------------------------------------------------");
						break;
						}
						admin:
						while(true){	
							System.out.println(" -----------------------------------------------------");
							System.out.println("| Pritisnite (d) da dodate gradivo                   |");
							System.out.println("| Pritisnite (u) za pregled uporabnikov              |");
							System.out.println("| Pritisnite (i) za pregled izposojenih gradiv       |");
							System.out.println("| Pritisnite (b) da izbrisete gradivo                |");
							System.out.println("| Pritisnite (x) za nazaj                            |");
							System.out.println(" -----------------------------------------------------");
							String a = br.readLine().trim().toLowerCase();
							if(a.length() == 1){
								char znakA = a.charAt(0);
								switch(znakA){
									case 'd':
										while(true){
											try{
												k.dodajGradivo();
												break;
											}catch(NullPointerException e){
												break;
											}
										}
										break;
									case 'u':
										k.izpisUporabnikov();
										break;
									case 'i':
										k.izpisIzposojenih();
										break;
									case 'b':
										k.brisanjeGradiv();
										break;
									case 'x':
										break admin;
									default:
										System.out.println(" -----------------------------------------------------");
										System.out.println("| Napacen vnos                                       |");
										System.out.println(" -----------------------------------------------------");		
								}	
							}		
						}			
						break;
					case 'x':
						break loop;
					default:
						System.out.println(" -----------------------------------------------------");
						System.out.println("| Napacen vnos                                       |");
						System.out.println(" -----------------------------------------------------");
						break;
				}
				
				
			}else{
				System.out.println(" -----------------------------------------------------");
				System.out.println("| Napacen vnos                                       |");
				System.out.println(" -----------------------------------------------------");
			}	
			/*u.izpis();
			k.isciKnjigo(u);
			
			k.odstraniGradivo(u);
			
			
			for(int i = 0; i< k.getUporabniki().size();i++){
				k.getUporabniki().get(i).izpis();
				
			}*/
			
		}
		k.dodajUporabnika(u);
		k.knjiznicaToFile();
		k.vnosVDatoteko();
		
	}
}
