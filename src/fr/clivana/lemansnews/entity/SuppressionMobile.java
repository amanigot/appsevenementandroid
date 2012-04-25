package fr.clivana.lemansnews.entity;

public class SuppressionMobile {

	private long idObjet;
	private String typeObjet;
	
	public SuppressionMobile(long idObjet, String typeObjet) {
		super();
		this.idObjet = idObjet;
		this.typeObjet = typeObjet;
	}
	
	public SuppressionMobile() {
		super();
	}

	public long getIdObjet() {
		return idObjet;
	}
	public void setIdObjet(long idObjet) {
		this.idObjet = idObjet;
	}
	public String getTypeObjet() {
		return typeObjet;
	}
	public void setTypeObjet(String typeObjet) {
		this.typeObjet = typeObjet;
	}
	
}
