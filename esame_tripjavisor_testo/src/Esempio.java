import java.io.IOException;

import tripjavisor.*;

public class Esempio {

	public static void main(String[] args) throws IOException, HotelGiaPresenteException{
		
		Sistema s = new Sistema();
		
		System.out.println("******************************************");
		System.out.println("*               R1. HOTEL                *");
		System.out.println("******************************************");
		
		System.out.println("\nAggiunto un nuovo hotel");
		Hotel h1 = s.aggiungiHotel("Ibis Styles Torino Porta Nuova", "Via XX Settembre 4", "Torino", 57);
		
		System.out.println("\nDettagli hotel:");
		System.out.println("   Id: "+h1.getCodice());
		System.out.println("   Nome: "+h1.getNome());
		System.out.println("   Indirizzo: "+h1.getIndirizzo()+ " "+h1.getCitta());
		System.out.println("   Numero camere: "+h1.getNumCamere());
		
		System.out.println("\nAggiunto un nuovo hotel premium");
		Hotel h2 = s.aggiungiHotelPremium("Grand Hotel Sitea", "Via Carlo Alberto 35", "Torino", 120, "011 1962 0631", "http://grandhotelsitea.it", "info@grandhotelsitea.it");

		System.out.println("\nDettagli hotel premium:");
		System.out.println("   Id: "+h2.getCodice());
		System.out.println("   Nome: "+h2.getNome());
		System.out.println("   Indirizzo: "+h2.getIndirizzo()+ " "+h1.getCitta());
		System.out.println("   Numero camere: "+h2.getNumCamere());
		System.out.println("   Numero di telefono: "+((HotelPremium) h2).getNumeroTelefono());
		System.out.println("   Sito internet: "+((HotelPremium) h2).getSitoInternet());
		System.out.println("   E-mail: "+((HotelPremium) h2).getEmail());

		System.out.println("\nAggiunti altri hotel: ");
		Hotel h3 = s.aggiungiHotel("NH Torino Santo Stefano", "Via Porta Palatina 19", "Torino", 125);
		Hotel h4 = s.aggiungiHotelPremium("Hotel Smeraldo", "Via dei Chiavari 20", "Roma", 50, "06 9480 3979", "http://www.smeraldoroma.com/",  "info@smeraldoroma.com");
		Hotel h5 = s.aggiungiHotel("Hotel Colosseum", "Via Sforza 10", "Roma", 46);

		System.out.println("\nRicerca dell'hotel con id 2: ");
		Hotel trovato = s.cercaHotel(2);
		System.out.println(trovato.getCodice()+ " - "+trovato.getNome()+ " - "+trovato.getIndirizzo()+ " - "+trovato.getCitta());

		System.out.println("\nHotel in ordine di inserimento: ");
		for (Hotel h : s.elencoHotelInOrdineDiInserimento())
			System.out.println(h.getCodice()+ " - "+h.getNome()+ " - "+h.getIndirizzo()+ " - "+h.getCitta());

		System.out.println("\n");
		System.out.println("******************************************");
		System.out.println("*                R2. UTENTI              *");
		System.out.println("******************************************");
		
		System.out.println("Aggiungo degli utenti");
		Utente u1 = s.aggiungiUtente("mariorossi");
		Utente u2 = s.aggiungiUtente("cesarebianchi");
		Utente u3 = s.aggiungiUtente("ginoblu");
		
		System.out.println("\nUtenti aggiunti:");
		System.out.println("u1: "+u1.getUsername());
		System.out.println("u2: "+u2.getUsername());
		System.out.println("u3: "+u3.getUsername());
		
		System.out.println("\nUtenti in ordine alfabetico: ");
		for (Utente u : s.elencoUtentiInOrdineAlfabetico())
			System.out.println(u.getUsername());

		System.out.println("\n");
		System.out.println("******************************************");
		System.out.println("*             R3. RECENSIONI             *");
		System.out.println("******************************************");

		System.out.println("Aggiunta una recensione");
		String data = "20170629";
		String titolo = "Hotel semplice ma in ottima posizione";
		String testo = "L'hotel è piccolo e vecchio stile, ma tenuto bene e in ottima posizione nel centro sparato di Torino. Le camere sono pulite e ben tenute. Noi avevamo chiesto una camera senza moquette e ci è stata data.";
		double voto = 4.0;
		String username = "mariorossi";
		Recensione r = s.aggiungiRecensione(data, titolo, testo, voto, username, 1);
		
		System.out.println("\nDettagli recensione:");
		System.out.println("Hotel: "+r.getHotel().getNome());
		System.out.println("Utente: "+r.getUtente().getUsername());
		System.out.println("Data: "+r.getData());
		System.out.println("Titolo: "+r.getTitolo());
		System.out.println("Testo: "+r.getTesto());
		System.out.println("Voto: "+r.getVoto());
		System.out.println("idRecensione: "+r.getCodice());

		System.out.println("\nAggiunte altre recensioni per lo stesso albergo");
		data = "20170629";
		titolo = "Weekend musei a torino";
		testo = "Posto mediamente pulito colazione all americana degna di nota.";
		voto = 3.0;
		username = "ginoblu";
		s.aggiungiRecensione(data, titolo, testo, voto, username, 1);	

		titolo = "Vergognoso";
		data = "20170629";
		testo = "Un'esperienza da dimenticare. Stanze anguste e senza comfort, bagni dove ti giri a malapena.";
		voto = 1.0;
		username = "cesarebianchi";
		s.aggiungiRecensione(data, titolo, testo, voto, username, 1);
		
		System.out.println("\nAggiunte altre recensioni per altri alberghi");
		titolo = "Ottima struttura in pieno centro";
		testo = "Hotel situato a poche centinaia di metri dalle maggiori attrazioni turistiche della città (Colosseo, Piazza Venezia, Via del Corso, ecc.).";
		voto = 5.0;
		username = "ginoblu";
		s.aggiungiRecensione("20170628", titolo, testo, voto, username, 5);

		s.aggiungiRecensione("20170628","Altra recensione1", "", 1.5, "ginoblu", 2);
		s.aggiungiRecensione("20170627","Altra recensione3", "", 2.0, "ginoblu", 3);
		s.aggiungiRecensione("20170626","Altra recensione4", "", 0.5, "ginoblu", 4);

		System.out.println("\nRicerca di una recensione che contenga la parola \"Colosseo\"");
		Recensione r1 = s.cercaRecensione("Colosseo");
		System.out.println("Recensione trovata: \ntitolo: "+r1.getTitolo()+ "\ntesto: "+r1.getTesto());

		System.out.println("\nNumero di recensioni fornite in data \"20170629\": "+s.calcolaNumeroRecensioniGiornaliero("20170629"));

		System.out.println("\nNumero di recensioni fornite dall'utente \"ginoblu\": "+s.calcolaNumeroRecensioniUtente("ginoblu"));

		System.out.println("\n");
		System.out.println("******************************************");
		System.out.println("*            R4. RANKING HOTEL           *");
		System.out.println("******************************************");

		System.out.println("\nCalcolo media recensioni dell'hotel con codice 1: "+s.calcolaMediaVotiRecensioniHotel(1));
		System.out.println("Calcolo media recensioni dell'hotel con codice 5: "+s.calcolaMediaVotiRecensioniHotel(5));
		System.out.println("Calcolo media recensioni dell'hotel inesistente: "+s.calcolaMediaVotiRecensioniHotel(500));

		System.out.println("\nLista degli hotel di Torino ordinati per media di recensioni decrescente");
		for (Hotel h : s.elencoHotelCittaPerMediaVotiRecensioniDecrescenti("Torino"))
			System.out.println(""+h.getNome()+ " "+h.getIndirizzo()+ " "+h.getCitta());
		
		System.out.println("\nLista di tutti gli hotel ordinati per media di recensioni decrescente");
		for (Hotel h : s.elencoHotelPerMediaVotiRecensioniDecrescenti())
			System.out.println(""+h.getNome()+ " "+h.getIndirizzo()+ " "+h.getCitta());
		
		System.out.println("\n");
		System.out.println("******************************************");
		System.out.println("*      R5. MI PIACE E NON MI PIACE       *");
		System.out.println("******************************************");

		System.out.println("\nAggiunti degli utenti e dei relativi voti alle recensioni inserite");

		s.aggiungiUtente("camillaverdi");
		s.aggiungiUtente("martinablu");
		s.aggiungiUtente("marconeri");
		s.aggiungiUtente("giorgiobianchi");
		s.aggiungiUtente("mariaviola");

		s.miPiaceARecensione(102, "camillaverdi");
		s.miPiaceARecensione(102, "martinablu");
		s.miPiaceARecensione(102, "marconeri");

		s.nonMiPiaceARecensione(101, "martinablu");
		s.miPiaceARecensione(101, "marconeri");

		s.miPiaceARecensione(100, "giorgiobianchi");
		s.miPiaceARecensione(100, "mariaviola");

		s.miPiaceARecensione(103, "giorgiobianchi");
		s.miPiaceARecensione(103, "mariaviola");
		s.miPiaceARecensione(103, "marconeri");
		s.miPiaceARecensione(103, "martinablu");
		
		System.out.println("\nVoto dell'utente mariorossi: "+s.calcolaPunteggioUtente("mariorossi"));
		System.out.println("Voto dell'utente ginoblu: "+s.calcolaPunteggioUtente("ginoblu"));
		System.out.println("Voto dell'utente cesarebianchi: "+s.calcolaPunteggioUtente("cesarebianchi"));
		
		System.out.println("\nUtenti ordinati in base a punteggio decrescente: ");
		for (Utente u : s.elencoUtentiPerPunteggioDecrescente())
			System.out.println(u.getUsername()+ " "+s.calcolaPunteggioUtente(u.getUsername()));
	}

}

