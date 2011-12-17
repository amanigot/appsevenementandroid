package fr.clivana.lemansnews.entity;

public class Categorie {
	
	private int id;
	private String motClef;
	private long date;
	private int count;
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMotClef() {
		return motClef;
	}
	public void setMotClef(String motClef) {
		this.motClef = motClef;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}

}
