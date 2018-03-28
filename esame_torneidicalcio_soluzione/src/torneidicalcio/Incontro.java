package torneidicalcio;

import java.util.*;

public class Incontro {

	int giornata;
	Squadra squadraCasa;
	Squadra squadraOspite;
	int numeroGolSquadraCasa;
	int numeroGolSquadraOspite;
	Arbitro arbitro;
	
	public Incontro(int giornata, Squadra squadraCasa, Squadra squadraOspite, String risultato, Arbitro arbitro ){
		this.giornata=giornata;
		this.squadraCasa=squadraCasa;
		this.squadraOspite=squadraOspite;
		this.arbitro=arbitro;
		String[] ris = risultato.split("-");
		this.numeroGolSquadraCasa=Integer.parseInt(ris[0]);
		this.numeroGolSquadraOspite=Integer.parseInt(ris[1]);;
	}

	public int getGiornata() {
		return giornata;
	}

	public Squadra getSquadraCasa() {
		return squadraCasa;
	}

	public Squadra getSquadraOspite() {
		return squadraOspite;
	}

	public int getNumeroGolSquadraCasa() {
		return numeroGolSquadraCasa;
	}

	public int getNumeroGolSquadraOspite() {
		return numeroGolSquadraOspite;
	}

	public String getArbitro() {
		return "("+arbitro.getNome()+" "+arbitro.getCognome()+", "+arbitro.getSezione()+")";
	}
	
	static class ComparatoreIncontriPerGiornata implements Comparator<Incontro>{

		public int compare(Incontro i1, Incontro i2) {
			return i1.getGiornata()-i2.getGiornata();
		}
		
	}
	
	static class ComparatoreIncontriPerDifferenzaReti implements Comparator<Incontro>{

		public int compare(Incontro i1, Incontro i2) {
			return (i1.getNumeroGolSquadraCasa()-i1.getNumeroGolSquadraOspite())-(i2.getNumeroGolSquadraCasa()-i2.getNumeroGolSquadraOspite());
		}
		
	}
}
