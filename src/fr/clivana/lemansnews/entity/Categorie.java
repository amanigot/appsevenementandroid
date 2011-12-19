package fr.clivana.lemansnews.entity;

public class Categorie {
	
	private int id;
	private String motClef;
	private long date;
	private int count;
	private boolean selected;
	/* identifiant (auto), 
	motclef (plusieurs séparrés par un espace), 
	selected(equivalent de favoris), 
	dateConsultation (date de la dernière consultation par l'utilisateur)
	count (nombre de nouvelles news non lus dans cette categorie depuis la dernière consultation) 
	total (nombre de news total)
	count10j (nombre de news ses 10 derniers jours)
	count1m (nombre de news depuis 1 mois
	supprimable (si la categorie peut etre retirée de la grid)
	position (position dans la grid view, non actif pour le moment) */
	
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
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
