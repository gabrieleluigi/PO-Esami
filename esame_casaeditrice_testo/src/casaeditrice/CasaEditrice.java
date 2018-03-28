package casaeditrice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CasaEditrice {
	
	private int NUMERO_AUTORE = 10000;
	Map<Integer,Autore> autoriMappa = new HashMap<Integer,Autore>();
	List<Autore> autoriLista = new LinkedList<Autore>();

	public Autore definisciAutore(String nome, String cognome, String email){
		Autore a = new Autore(NUMERO_AUTORE, nome, cognome, email);
		autoriMappa.put(NUMERO_AUTORE, a);
		autoriLista.add(a);
		NUMERO_AUTORE++;
		return a;
	}
	
	public Collection<Autore> elencoAutori(){
		Collections.sort(autoriLista);
		return autoriLista;
	}
	
	public Autore getAutore(int codice){
		return autoriMappa.get(codice);
    }

	public Autore getAutore(String nome, String cognome){
		for(Autore a: autoriMappa.values())
			if(a.getCognome().compareTo(cognome) == 0 && a.getNome().compareTo(nome) == 0)
				return a;
     	return null;
	}

	public Collection<Pubblicazione> pubblicazioniAutore(String nome, String cognome) throws AutoreInesistenteException {
		Autore a = getAutore(nome,cognome);
		if(a == null) 
			throw new AutoreInesistenteException();
		System.out.println("Numero pubblicazioni autore: "+a.elencoPubblicazioni().size());
		return a.elencoPubblicazioni();
	}
	
	public void leggiPubblicazioni(String nomeFile) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(nomeFile));
		String line;
		int cont;
		Autore a;
		Pubblicazione p;
		
		while ((line = br.readLine()) != null){
			String [] array = line.split(";");
			if (array.length == 5)
			{
				if(array[0].compareTo("P") == 0)
				{
					//if(array[2].compareTo("R"))
						
					//if(array[2].compareTo("C"))
						
					cont=0;
				}
				if(array[0].compareTo("A") == 0)
				{
					a = definisciAutore(array[1],array[2],array[3]);
					//if(cont==0)
						
						
					
					//cont++;
				}
			}
		}
	}

}
