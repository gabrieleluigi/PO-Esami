package tennis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Associazione {
	
	private Map<String, Giocatore> mappaGiocatoriNC = new TreeMap<String, Giocatore>();
	private Map<String, Giocatore> mappaGiocatoriID = new TreeMap<String, Giocatore>();
	private int CODICE_GIOCATORE = 1;
	private Map<String, Torneo> mappaTornei = new TreeMap<String, Torneo>();
	private List<Torneo> listaTornei = new LinkedList<Torneo>();
	private int CODICE_INCONTRO = 1;
	private Map<String, Incontro> mappaInconti = new TreeMap<String, Incontro>();

	public Giocatore nuovoGiocatore(String nome, String cognome, String dataNascita, String nazionalita) {
		if(mappaGiocatoriNC.containsKey(cognome+" "+nome))
			return mappaGiocatoriNC.get(cognome+" "+nome);
		String c = Integer.toString(CODICE_GIOCATORE);
		String z = "";
		if(c.length() == 1)
			z = "000";
		else if(c.length() == 2)
			z = "00";
		else if(c.length() == 3)
			z = "0";
		String key = z + c;
		Giocatore g = new Giocatore(key, nome, cognome, dataNascita, nazionalita);
		mappaGiocatoriNC.put(cognome+" "+nome, g);
		mappaGiocatoriID.put(key, g);
		CODICE_GIOCATORE++;
		return g;
	}
	
	public Collection<Giocatore> elencoGiocatori(){
		return mappaGiocatoriNC.values();
	}
	
	public Collection<Giocatore> cercaGiocatori(String daCercare){
		List<Giocatore> ltemp = new LinkedList<Giocatore>();
		for(Giocatore g: mappaGiocatoriID.values())
			if(g.getNome().contains(daCercare) || g.getCognome().contains(daCercare) || g.getDataNascita().contains(daCercare) || g.getNazionalita().contains(daCercare))
				ltemp.add(g);
		return ltemp;
	}
	
	public Torneo nuovoTorneo(String nome, String luogo, int numeroPunti, int numeroPartecipanti) throws TorneoGiaDefinitoException {
		if(mappaTornei.containsKey(nome))
			throw new TorneoGiaDefinitoException();
		if(numeroPartecipanti%2 != 0 && potenza(numeroPartecipanti)%2 !=0)
			return null;
		Torneo t = null;
		if(numeroPunti == 500)
			t = new Torneo500(nome, luogo, numeroPartecipanti);
		else if(numeroPunti == 1000)
			t = new Torneo1000(nome, luogo, numeroPartecipanti);
		else if(numeroPunti == 2000)
			t = new Torneo2000(nome, luogo, numeroPartecipanti);
		t.setNumeroPunti(numeroPunti);
		mappaTornei.put(nome, t);
		listaTornei.add(t);
		return t;
	}	
	
	public int potenza(int n){
		int c = 0;
		if(n>1){
			while(n/2 != 1){
				n=n/2;
				c++;
			}
			c++;
		}
		return c;
	}
	
	public int numeroTurniTorneo(String nomeTorneo) {
		return potenza(mappaTornei.get(nomeTorneo).getNumeroPartecipanti());
	}
	
	public String stampaTornei() {
		Collections.sort(listaTornei);
		String s = "";
		for(Torneo t: listaTornei)
			s += t.getNome() +", "+t.getLuogo() +", "+ t.getNumeroPunti() +", "+ t.getNumeroPartecipanti()+"\n";
		s = s.substring(0, s.length() - 1);
		return s;
	}

	public Incontro nuovoIncontroTorneo(String nomeTorneo, String idGiocatore1, String idGiocatore2, int turno) {
		int cont = 0;
		for(Incontro j: mappaInconti.values()) {
			if(j.getTorneo().getNome().compareTo(nomeTorneo) == 0 && j.getGiocatore1().getId().compareTo(idGiocatore1) == 0 && j.getGiocatore2().getId().compareTo(idGiocatore2) == 0)
				return j;
			if(mappaTornei.containsKey(nomeTorneo))
				if(j.getTorneo().getNome().compareTo(nomeTorneo)==0)
					cont++;}
		if(cont == 0)
			cont = 1;
		else
			cont += CODICE_INCONTRO;
		String cod = Integer.toString(cont);
		String id = nomeTorneo+"-"+cod;
		Incontro i = new Incontro(id, mappaTornei.get(nomeTorneo), mappaGiocatoriID.get(idGiocatore1), mappaGiocatoriID.get(idGiocatore2), turno);
		mappaInconti.put(id,i);
		mappaTornei.get(nomeTorneo).addIncontro(i);
		mappaGiocatoriID.get(idGiocatore1).addIncontro(i);
		mappaGiocatoriID.get(idGiocatore2).addIncontro(i);
		//CODICE_INCONTRO++;
		return i;
	}
	
	public Incontro cercaIncontroPerId(String idIncontro) {
		if(mappaInconti.containsKey(idIncontro))
			return mappaInconti.get(idIncontro);
		return null;
	}

	public void impostaRisultatoIncontro(String idIncontro, String risultato) {
		mappaInconti.get(idIncontro).setRisultato(risultato);
	}
	
	public Collection<Incontro> elencoIncontriTorneo(String nomeTorneo){
		if(mappaTornei.containsKey(nomeTorneo))
			return mappaTornei.get(nomeTorneo).getMappaIncontri().values();
		return null;
	}

	public Collection<Incontro> elencoIncontriGiocatore(String idGiocatore){
		return mappaGiocatoriID.get(idGiocatore).getMappaIncontri().values();
	}

	public Giocatore vincitoreIncontro(String idIncontro) {
		if(mappaInconti.containsKey(idIncontro) == false ||  mappaInconti.get(idIncontro).getRisultato().compareTo("") == 0)
			return null;
		String r = mappaInconti.get(idIncontro).getRisultato();
		String [] array = r.split(" ");
		int v1 = 0;
		int v2 = 0;
		for(int i=0;i<array.length ;i++) {
			int p1 = Integer.parseInt(array[i].substring(0, 1).trim());
			int p2 =  Integer.parseInt(array[i].substring(2, 3).trim());
			if(p1 > p2)
				v1++;
			else if (p2 > p1)
				v2++;
		}
		if(v1>v2)
			return mappaInconti.get(idIncontro).getGiocatore1();
		else if(v2>v1)
			return mappaInconti.get(idIncontro).getGiocatore2();
		return null;
	}
	
    public void letturaDaFile(String nomeFile) throws TorneoGiaDefinitoException{
    	BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(nomeFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line; 
		try {
			while ((line = br.readLine()) != null){
				String [] array = line.split(";");
				if (array[0].trim().compareTo("T") == 0) 
					nuovoTorneo(array[1].trim(), array[2].trim(), Integer.parseInt(array[3].trim()), Integer.parseInt(array[4].trim()));
				if (array[0].trim().compareTo("G") == 0) 
					nuovoGiocatore(array[1].trim(), array[2].trim(), array[3].trim(), array[4].trim());
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
