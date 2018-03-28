package torneidicalcio;

public class Incontro {
	
	private int giornata;
	private Squadra squadraCasa;
	private Squadra squadraOspite;
	private int numeroGolSquadraCasa;
	private int numeroGolSquadraOspite;
	private Arbitro arbitro;

	public Incontro(int giornata, Squadra squadraCasa, Squadra squadraOspite, int numeroGolSquadraCasa,
			int numeroGolSquadraOspite, Arbitro arbitro) {
		this.giornata = giornata;
		this.squadraCasa = squadraCasa;
		this.squadraOspite = squadraOspite;
		this.numeroGolSquadraCasa = numeroGolSquadraCasa;
		this.numeroGolSquadraOspite = numeroGolSquadraOspite;
		this.arbitro = arbitro;
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
		return arbitro.getNome()+" "+arbitro.getCognome()+", "+arbitro.getSezione();
	}

}
