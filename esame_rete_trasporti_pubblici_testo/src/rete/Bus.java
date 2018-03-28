package rete;

public class Bus extends Mezzo {

	private String targa;
	
	public Bus(int id, String targa) {
		super(id);
		this.targa = targa;
	}

	public String getTarga() {
		return targa;
	}
	
	public int getId() {
		return id;
	}

}
