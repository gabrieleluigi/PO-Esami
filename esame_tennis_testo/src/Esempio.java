import java.io.IOException;
import java.util.*;

import tennis.*;

public class Esempio {

	public static void main(String[] args) throws TorneoGiaDefinitoException, IOException {
		
		Associazione a = new Associazione();
		
		System.out.println("Definito nuovo giocatore:");
		
		Giocatore g = a.nuovoGiocatore("Roger", "Federer", "19810808", "Switzerland");
		
		System.out.println(" Nome: "+g.getNome());
		System.out.println(" Cognome: "+g.getCognome());
		System.out.println(" Data di nascita: "+g.getDataNascita());
		System.out.println(" Nazionalità: "+g.getNazionalita());

		System.out.println("	\nId assegnato al giocatore:");
		System.out.println(" "+g.getId());

		System.out.println("\nDefiniti altri giocatori");
		a.nuovoGiocatore("Rafael", "Nadal", "19860603", "Spain");
		a.nuovoGiocatore("Rafael", "Nadal", "19860603", "Spain");
		a.nuovoGiocatore("Novak", "Djokovic", "19870522", "Serbia");
		a.nuovoGiocatore("Alexander", "Zverev", "19970420", "Germany");

		System.out.println("\nGiocatori (ord. per cognome e nome):");
		LinkedList<Giocatore> listaGiocatori = new LinkedList<Giocatore>(a.elencoGiocatori());
		for(Giocatore gtemp : listaGiocatori)
			System.out.println(" "+gtemp.getId()+" "+gtemp.getCognome()+" "+gtemp.getNome()+" "+gtemp.getDataNascita()+" "+gtemp.getNazionalita());

		System.out.println("	\nCerca giocatori 'er' (ord. per id crescente):");
		listaGiocatori = new LinkedList<Giocatore>(a.cercaGiocatori("er"));
		for(Giocatore gtemp : listaGiocatori)
			System.out.println(" "+gtemp.getId()+" "+gtemp.getCognome()+" "+gtemp.getNome()+" "+gtemp.getDataNascita()+" "+gtemp.getNazionalita());
		
		System.out.println("\nDefinito nuovo torneo:");
		
		Torneo t = a.nuovoTorneo("Rolex Paris Masters", "Paris-Bercy, France", 1000, 4);
		
		System.out.println(" Nome: "+t.getNome());
		System.out.println(" Luogo: "+t.getLuogo());
		System.out.println(" Numero partecipanti: "+t.getNumeroPartecipanti());
		
		System.out.println("	\nNumero punti:");
		if(t instanceof Torneo500)
			System.out.println(" 500");
		else if(t instanceof Torneo1000)
			System.out.println(" 1000");
		else if(t instanceof Torneo2000)
			System.out.println(" 2000");

		System.out.println("	\nNumero turni:");
		System.out.println(" "+a.numeroTurniTorneo(t.getNome()));

		System.out.println("	\nStampa torneo: ");
		System.out.println(" "+a.stampaTornei());
		
		System.out.println("	\nDefinito nuovo incontro torneo");

		Incontro i = a.nuovoIncontroTorneo("Rolex Paris Masters", "0001", "0003", 1);
		
		System.out.println("	\nId assegnato all'incontro:");
		System.out.println(" "+i.getId());

		System.out.println("	\nCerca incontro "+i.getId()+":");
		i = a.cercaIncontroPerId(i.getId());
		System.out.println(" Turno "+i.getTurno()+": "+i.getGiocatore1().getCognome()+ " - " + i.getGiocatore2().getCognome());
		
		System.out.println("	\nImpostato risultato per l'incontro:");
		a.impostaRisultatoIncontro(i.getId(),"6-0 6-2 6-1");
		
		System.out.println(" "+i.getRisultato());
		
		System.out.println("	\nVincitore dell'incontro:");
		g = a.vincitoreIncontro(i.getId());
		System.out.println(" "+g.getCognome());
		
		System.out.println("	\nDefiniti altri incontri e risultati");
		i = a.nuovoIncontroTorneo("Rolex Paris Masters", "0001", "0002", 2);
		a.impostaRisultatoIncontro(i.getId(), "6-3 6-4 0-6 0-6 1-6");
		i = a.nuovoIncontroTorneo("Rolex Paris Masters", "0002", "0004", 1);
		a.impostaRisultatoIncontro(i.getId(), "0-6 7-5 6-3 6-3");

		System.out.println("\nElenco incontri torneo (ord. turno crescente)");
		LinkedList<Incontro> listaIncontri = new LinkedList<Incontro>(a.elencoIncontriTorneo(t.getNome()));
		for(Incontro itemp : listaIncontri) {
			System.out.println(" Turno "+itemp.getTurno()+": "+itemp.getGiocatore1().getCognome()+ " - " + itemp.getGiocatore2().getCognome());
			System.out.println(" "+itemp.getRisultato());
		}

		System.out.println("	\nLettura informazioni da file");

		a.letturaDaFile("input.txt");
		
		System.out.println("\nGiocatori:");
		listaGiocatori = new LinkedList<Giocatore>(a.elencoGiocatori());
		for(Giocatore gtemp : listaGiocatori)
			System.out.println(" "+gtemp.getId()+" "+gtemp.getCognome()+" "+gtemp.getNome()+" "+gtemp.getDataNascita()+" "+gtemp.getNazionalita());
		
		System.out.println("\nTornei (ord. per punti decr. e num. partecipanti decr.):");
		System.out.println(a.stampaTornei());
		
	}
}









