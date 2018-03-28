package tennis;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Torneo implements Comparable<Torneo> {
	
	private String nome;
	private String luogo;
	private int numeroPartecipanti;
	private int numeroPunti;
	protected Map<Integer, Incontro> mappaIncontri = new TreeMap<Integer, Incontro>();

	public Torneo(String nome, String luogo, int numeroPartecipanti) {
		this.nome = nome;
		this.luogo = luogo;
		this.numeroPartecipanti = numeroPartecipanti;
	}

	public String getNome() {
		return nome;
	}

	public String getLuogo() {
		return luogo;
	}

	public int getNumeroPartecipanti() {
		return numeroPartecipanti;
	}

	public void setNumeroPunti(int numeroPunti) {
		this.numeroPunti = numeroPunti;
	}

	public int getNumeroPunti() {
		return numeroPunti;
	}

	@Override
	public int compareTo(Torneo t) {
		if(this.numeroPunti == t.numeroPunti)
			return -(this.numeroPartecipanti - t.getNumeroPartecipanti());
		return -(this.numeroPunti - t.numeroPunti);
	}

	public Map<Integer, Incontro> getMappaIncontri() {
		return mappaIncontri;
	}
	
	
	public void addIncontro(Incontro i, Integer t){
		mappaIncontri.put(t, i);
	}
}





