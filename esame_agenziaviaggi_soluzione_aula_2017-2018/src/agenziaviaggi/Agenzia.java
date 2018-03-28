package agenziaviaggi;

import java.util.*;

public class Agenzia {
	private List<Cliente> clienti = new LinkedList<Cliente>();
	private int idPratica = 1000;
	private Map<Integer, Pratica> pratiche = new HashMap<Integer, Pratica>();

	public Cliente aggiungiCliente(String cognome, String nome, String indirizzo) 
	throws EccezioneClienteGiaEsistente{
		
		Cliente c = cercaCliente(cognome, nome, indirizzo);
		
		// controllo se il cliente e' gia' presente
		if (c != null)
			throw new EccezioneClienteGiaEsistente();
		
		// creo il cliente
		c = new Cliente(cognome, nome, indirizzo);
		clienti.add(c);
		
		return c;
	}
	
	public Collection<Cliente> elencoClienti(){
		Collections.sort(clienti); //ordina la linsta in ordine alfabetico
		return clienti;
	}
	
	public Cliente cercaCliente(String cognome, String nome, String indirizzo){
		for (Cliente c : clienti)
			if (c.getCognome().compareTo(cognome) == 0 &&
					c.getNome().compareTo(nome) == 0 &&
					c.getIndirizzo().compareTo(indirizzo) == 0)
				return c;
			
		return null;
	}
	
	public Cliente cercaCliente(String ricerca){
		for (Cliente c : clienti)
			if (c.getCognome().contains(ricerca) ||
					c.getNome().contains(ricerca) ||
					c.getIndirizzo().contains(ricerca))
				return c;
		
		return null;
	}
	
	public int nuovaPratica(String descrizione, String cognome, String nome, String indirizzo){
		Cliente c = cercaCliente(cognome, nome, indirizzo);
		
		if (c == null){
			c = new Cliente (cognome, nome, indirizzo);
			clienti.add(c);
		}
		
		Pratica p = new Pratica(idPratica, descrizione, c);
		pratiche.put(idPratica, p);
		idPratica++;
		
		c.aggiungiPratica(p);
		
		return p.getIdPratica();
	}
	
	public Pratica getPratica(int idPratica) throws EccezionePraticaInesistente{ //throws aggiunta, non presente nel testo d'esame
		Pratica p = pratiche.get(idPratica);
		
		if (p != null)
			return p;
		
		throw new EccezionePraticaInesistente();
	}
	
	public void eliminaPratica(int idPratica) throws EccezionePraticaInesistente{
		Pratica p = this.getPratica(idPratica);
		
		pratiche.remove(idPratica);
		p.getCliente().eliminaPratica(idPratica);
	}
	
	public Collection<Pratica> elencoPratiche(){
		List<Pratica> ltemp = new LinkedList<Pratica>(pratiche.values());
		Collections.sort(ltemp); //mi ricordo di aggiungere "implements" a pratiche e il metodo compareTo
		return ltemp;
	}

	public double calcolaImportoPerPeriodo(String da, String a){
		String data = "";
		double totale = 0.0;
		
		for (Pratica p : pratiche.values()){ // ciclo sulle pratiche
			for (Prenotazione pr : p.elencoPrenotazioniPerData()){
				if (pr instanceof PrenotazioneAlbergo)
					data = ((PrenotazioneAlbergo) pr).getDataCheckIn();
				else if (pr instanceof PrenotazioneVolo)
					data = ((PrenotazioneVolo) pr).getDataPartenza();
				
				if (data.compareTo(da) >=0 && data.compareTo(a) <=0)
					totale += pr.getImporto();
			}
		}
		
		return totale;
	}
	
	public Collection<Cliente> elencoClientiSelezionati(double importo){
		List<Cliente> temp = new LinkedList<Cliente>();
		Collections.sort(clienti);
		
		for (Cliente c : clienti){
			double tot = 0.0;
			
			for (Pratica p : c.elencoPratiche())
				tot += p.getImportoTotale();
			
			if (tot > importo) // ho un cliente gold
				temp.add(c);
		}
		
		return temp;
	}}
