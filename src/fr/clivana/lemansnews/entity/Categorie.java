package fr.clivana.lemansnews.entity;

public class Categorie {
	
	private long id;
	private String nom;
	private String nomImage;
	private int count;
	private int total;
	private boolean supprimable;
	private boolean selected;
	private String dateConsult;
	
	/* identifiant (auto),
	nom = motclef (un seul),
	selected(equivalent de favoris = categories affichées dans la gridview),
	dateConsultation (date de la dernière consultation par l'utilisateur)
	count (nombre de nouvelles news non lus dans cette categorie depuis la dernière consultation)
	total (nombre de news total)
	supprimable (si la categorie peut etre retirée de la grid)*/
	
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDateConsult() {
		return dateConsult;
	}
	public void setDateConsult(String dateConsult) {
		this.dateConsult = dateConsult;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public boolean isSupprimable() {
		return supprimable;
	}
	public void setSupprimable(boolean supprimable) {
		this.supprimable = supprimable;
	}
	
	public String getNomImage() {
		return nomImage;
	}
	public void setNomImage(String nomImage) {
		this.nomImage = nomImage;
	}
	
	public Categorie(long id, String nom, String nomImage, int count,
			int total, boolean supprimable, boolean selected, String dateConsult) {
		super();
		this.id = id;
		this.nom = nom;
		this.nomImage = nomImage;
		this.count = count;
		this.total = total;
		this.supprimable = supprimable;
		this.selected = selected;
		this.dateConsult = dateConsult;
	}
	public Categorie() {
		super();
	}

}
