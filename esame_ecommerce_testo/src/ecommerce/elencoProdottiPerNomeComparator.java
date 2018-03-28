package ecommerce;

import java.util.Comparator;

public class elencoProdottiPerNomeComparator implements Comparator<Prodotto> {

	@Override
	public int compare(Prodotto p1, Prodotto p2) {
		return p1.getNome().compareTo(p2.getNome());
	}

}
