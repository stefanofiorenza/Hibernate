package corso.hibernate.demo.mapping.values.tipi.components;

public class Image {

	
	private String nomeImmagine;
	private String pathImmagine;
	private int sizeX;
	private int sizeY;
	//private Item articolo;
	
	public Image(){}


	public String getNomeImmagine() {
		return nomeImmagine;
	}


	public void setNomeImmagine(String nomeImmagine) {
		this.nomeImmagine = nomeImmagine;
	}


	public String getPathImmagine() {
		return pathImmagine;
	}


	public void setPathImmagine(String pathImmagine) {
		this.pathImmagine = pathImmagine;
	}


	public int getSizeX() {
		return sizeX;
	}


	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}


	public int getSizeY() {
		return sizeY;
	}


	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}


	@Override
	public String toString() {
		
		return "Nome Immagine: "+ this.nomeImmagine+"\n"+
				"Path Immagine: "+ this.pathImmagine+"\n"+
				"SIzeX: "+ this.sizeX+"\n"+
				"SIzeY: "+ this.sizeY+"\n";
	}


	@Override
	public int hashCode() {
		
		return (this.nomeImmagine+"00"+this.pathImmagine+"00"+this.sizeX+"00"+this.sizeY).hashCode();
	}


	@Override
	public boolean equals(Object obj) {
		
		Image altra = (Image)obj;
		if(this.hashCode()==altra.hashCode()) return true;
		else return false;
	}

/*
	public Item getArticolo() {
		return articolo;
	}


	public void setArticolo(Item articolo) {
		this.articolo = articolo;
	};
	
	*/
	
	
}
