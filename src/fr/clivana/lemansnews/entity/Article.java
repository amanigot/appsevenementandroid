package fr.clivana.lemansnews.entity;

import java.util.Date;

public class Article {
    
	private long id;
	private String titre;
	private String article;
	private String accroche;
	private Date dateParution;
	private String auteur;
	private String urlArticle;
	private String urlImage;
	private String urlImageMobile;
	private String urlMiniature;
	private String motsClefs;
	private boolean notification;
	private boolean favoris;
// ajouter favoris;

	public Article(long id, String titre, String article, String accroche,
			Date dateParution, String auteur, String urlArticle, String urlImage, String urlImageMobile,
			String urlMiniature, String motsClefs, boolean notification, boolean favoris) {
		super();
		this.id = id;
		this.titre = titre;
		this.article = article;
		this.accroche = accroche;
		this.dateParution = dateParution;
		this.auteur = auteur;
		this.urlArticle = urlArticle;
		this.urlImage = urlImage;
		this.urlImageMobile = urlImageMobile;
		this.urlMiniature = urlMiniature;
		this.motsClefs = motsClefs;
		this.notification = notification;
		this.favoris = favoris;
	}
	 
	public static Article noNews(String sujet){
		Article noNews = new Article(
				0,
				"Aucune nouveauté",
				"Aucune nouveauté pour le theme : "+sujet.replace("/1", "")+".",
				"",
				new Date(),
				"Clivana",
				"",
				"",
				"",
				"",
				"noNews",
				false,
				false);
		return noNews;
	}
	
	public static Article noNewsFound(){
		Article noNewsFound = new Article(
				0,
				"Pas de réseau",
				"Veuillez vous connecter à Internet pour connaître les dernières nouveautés",
				"",
				new Date(),
				"Clivana",
				"",
				"",
				"",
				"",
				"noNewsFound",
				false,
				false);
		return noNewsFound;
	}
	
	public Article() {
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

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getAccroche() {
		return accroche;
	}

	public void setAccroche(String accroche) {
		this.accroche = accroche;
	}

	public Date getDateParution() {
		return dateParution;
	}

	public void setDateParution(Date dateParution) {
		this.dateParution = dateParution;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getUrlArticle() {
		return urlArticle;
	}

	public void setUrlArticle(String urlArticle) {
		this.urlArticle = urlArticle;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getUrlImageMobile() {
		return urlImageMobile;
	}

	public void setUrlImageMobile(String urlImageMobile) {
		this.urlImageMobile = urlImageMobile;
	}

	public String getUrlMiniature() {
		return urlMiniature;
	}

	public void setUrlMiniature(String urlMiniature) {
		this.urlMiniature = urlMiniature;
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

	public boolean isFavoris() {
		return favoris;
	}

	public void setFavoris(boolean favoris) {
		this.favoris = favoris;
	}
	
}
