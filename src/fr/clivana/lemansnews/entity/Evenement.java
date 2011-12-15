package fr.clivana.lemansnews.entity;



public class Evenement {

	private long id;
	private String titre;
	private String detailEvenement;
	private String accroche;
	private String auteur;
	private String lieu;
	private String urlEvenement;
	private String dateHeureEvenement;
	private String dateEnregistrement;
	private String nomImage;
	private String nomImageMobile;
	private String nomImageMiniature;
	private String motsClefs;
	private boolean notification;
	
	public Evenement(long id, String titre, String detailEvenement,
			String accroche, String auteur, String lieu, String urlEvenement,
			String dateHeureEvenement, String dateEnregistrement,
			String nomImage, String nomImageMobile, String nomImageMiniature,
			String motsClefs, boolean notification) {
		super();
		this.id = id;
		this.titre = titre;
		this.detailEvenement = detailEvenement;
		this.accroche = accroche;
		this.auteur = auteur;
		this.lieu = lieu;
		this.urlEvenement = urlEvenement;
		this.dateHeureEvenement = dateHeureEvenement;
		this.dateEnregistrement = dateEnregistrement;
		this.nomImage = nomImage;
		this.nomImageMobile = nomImageMobile;
		this.nomImageMiniature = nomImageMiniature;
		this.motsClefs = motsClefs;
		this.notification = notification;
	}

	public Evenement() {
		super();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDetailEvenement() {
		return detailEvenement;
	}

	public void setDetailEvenement(String detailEvenement) {
		this.detailEvenement = detailEvenement;
	}

	public String getAccroche() {
		return accroche;
	}

	public void setAccroche(String accroche) {
		this.accroche = accroche;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getUrlEvenement() {
		return urlEvenement;
	}

	public void setUrlEvenement(String urlEvenement) {
		this.urlEvenement = urlEvenement;
	}

	public String getDateHeureEvenement() {
		return dateHeureEvenement;
	}

	public void setDateHeureEvenement(String dateHeureEvenement) {
		this.dateHeureEvenement = dateHeureEvenement;
	}

	public String getDateEnregistrement() {
		return dateEnregistrement;
	}

	public void setDateEnregistrement(String dateEnregistrement) {
		this.dateEnregistrement = dateEnregistrement;
	}

	public String getNomImage() {
		return nomImage;
	}

	public void setNomImage(String nomImage) {
		this.nomImage = nomImage;
	}

	public String getNomImageMobile() {
		return nomImageMobile;
	}

	public void setNomImageMobile(String nomImageMobile) {
		this.nomImageMobile = nomImageMobile;
	}

	public String getNomImageMiniature() {
		return nomImageMiniature;
	}

	public void setNomImageMiniature(String nomImageMiniature) {
		this.nomImageMiniature = nomImageMiniature;
	}

	public String getMotsClefs() {
		return motsClefs;
	}

	public void setMotsClefs(String motsClefs) {
		this.motsClefs = motsClefs;
	}

	public boolean isNotification() {
		return notification;
	}

	public void setNotification(boolean notification) {
		this.notification = notification;
	}
	
	public static Evenement noEvents(){
		Evenement noNews = new Evenement(
				(long)0,
				"Aucun évènement",
				"Aucun évènement",
				"",
				"Clivana",
				"",
				"",
				"",
				"",
				"",
				"",
				"",
				"noNews",
				false);
		return noNews;
	}
	
	public static Evenement noEventFound(){
		Evenement noNewsFound = new Evenement(
				(long)0,
				"Pas de réseau",
				"Veuillez vous connecter à Internet pour connaître les dernières nouveautés",
				"",
				"",
				"Clivana",
				"",
				"",
				"",
				"",
				"",
				"",
				"noNewsFound",
				false);
		return noNewsFound;
	}

}