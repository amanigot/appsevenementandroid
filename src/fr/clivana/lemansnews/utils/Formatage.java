package fr.clivana.lemansnews.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Formatage {

	public static String dateEnTexteComplet(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
		return formatter.format(date);
	}
	
	public static String datePourPlay(Date date) {
		TimeZone tz = TimeZone.getTimeZone("Europe/Paris" );
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		formatter.setTimeZone(tz);
		return formatter.format(date);
	}
	public static Date stringToDate(String date) {
		TimeZone tz = TimeZone.getTimeZone("Europe/Paris" );
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		formatter.setTimeZone(tz);
		Date retour = null;
		try {
			retour = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return retour;
	}
	
	public static String datePourTriEvenement(Date date) {
		TimeZone tz = TimeZone.getTimeZone("Europe/Paris" );
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
		formatter.setTimeZone(tz);
		return formatter.format(date);
	}

	public static String suppressionHtml(String texte) {
		texte = replace(texte, "<br>", "\n");
		texte = replace(texte, "&amp;", "&");
		texte = replace(texte, "<ul>", "\n");
		texte = replace(texte, "</ul>", "\n");
		texte = replace(texte, "<li>", "* ");
		texte = replace(texte, "</li>", "\n");
		return texte;
	}

	private static String replace(String s, String replaced, String replacing) {
		StringBuffer tmp = new StringBuffer(s);
		int i = 0, debut, fin;
		char c = replaced.charAt(0);
		while (i < tmp.length()) {
			while ((i < tmp.length()) && (tmp.charAt(i) != c))
				i++;
			debut = i;
			i++;
			fin = 1;
			while ((i < tmp.length()) && (fin < replaced.length())
					&& (tmp.charAt(i) == replaced.charAt(fin))) {
				fin++;
				i++;
			}
			if ((i <= tmp.length()) && (fin == replaced.length())) {
				tmp.replace(debut, debut + fin, replacing);
				i = debut + replacing.length();
			}
		}
		return new String(tmp);
	}
}
