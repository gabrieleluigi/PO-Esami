package torneidicalcio;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeMap;

public class Torneo {

	private String nome;
	private int numeroSquadre;

	private TreeMap<String, Squadra> mappaSquadre = new TreeMap<String, Squadra>();
	private LinkedList<Squadra> listaSquadre = new LinkedList<Squadra>();
	private LinkedList<Incontro> incontri = new LinkedList<Incontro>();
	
	public Torneo(String nome, int numeroSquadre){
		this.nome=nome;
		this.numeroSquadre=numeroSquadre;
	}
	
	public void iscrivi(Squadra squadra){
		mappaSquadre.put(squadra.getNome(), squadra);
		listaSquadre.add(squadra);
	}

	public Incontro nuovoIncontro(int giornata, String nsCasa, String nsOspite,  String risultato, Arbitro arbitro){
		
		Incontro itemp = new Incontro(giornata, mappaSquadre.get(nsCasa), mappaSquadre.get(nsOspite), risultato, arbitro);
		incontri.add(itemp);
		return itemp;
		
	}
	
	public int puntiSquadra(String nomeSquadra){
		int punti=0;
			for(Incontro itemp : incontri){

				if(nomeSquadra.compareTo(itemp.getSquadraCasa().getNome())==0){
					if(itemp.getNumeroGolSquadraCasa()>itemp.getNumeroGolSquadraOspite())
						punti+=3;
					if(itemp.getNumeroGolSquadraCasa()==itemp.getNumeroGolSquadraOspite())
						punti+=1;
				}

				if(nomeSquadra.compareTo(itemp.getSquadraOspite().getNome())==0){
					if(itemp.getNumeroGolSquadraOspite()>itemp.getNumeroGolSquadraCasa())
						punti+=3;
					if(itemp.getNumeroGolSquadraOspite()==itemp.getNumeroGolSquadraCasa())
						punti+=1;
				}

			}
		return punti;
	}
	
	public String classifica(){
		
		for(Squadra stemp : listaSquadre)
			stemp.setPunti(puntiSquadra(stemp.getNome()));
		
		Collections.sort(listaSquadre);
		
		String ret="";
		
		int cnt=0;
		for(Squadra stemp : listaSquadre){
			cnt++;
			if(cnt!=listaSquadre.size())
				ret+=" "+puntiSquadra(stemp.getNome())+" "+stemp.getNome()+";\n";
			else
				ret+=" "+puntiSquadra(stemp.getNome())+" "+stemp.getNome()+".";
				
		}
		return ret;
	}
	
	
	public Collection<Incontro> elencoIncontriPerGiornata(){
		Collections.sort(incontri, new Incontro.ComparatoreIncontriPerGiornata());
		return incontri;
	}
	
	public Collection<Incontro> elencoIncontriPerDifferenzaReti(){
		Collections.sort(incontri, new Incontro.ComparatoreIncontriPerDifferenzaReti());
		return incontri;
	}
	
	public Collection<Squadra> elencoSquadre(){
		return mappaSquadre.values();
	}
	

	
	public String getNome() {
		return nome;
	}

	public int getNumeroSquadre() {
		return numeroSquadre;
	}
}
