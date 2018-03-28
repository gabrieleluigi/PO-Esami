package rete;

import java.util.Comparator;

public class ElencoFermatePerNumeroLineeComparator implements Comparator<Fermata> {

	@Override
	public int compare(Fermata f1, Fermata f2) {
		return - (f1.getNumerolinee() - f2.getNumerolinee());
	}

}
