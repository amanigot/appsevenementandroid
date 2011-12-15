package fr.clivana.lemansnews.utils.reseau;

import java.io.IOException;
import java.io.InputStream;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import android.util.Log;

public class DeSerializer <T>{

	@SuppressWarnings({ "unchecked", "null" })
	public T deJson(InputStream is){
		ObjectMapper mapper = new ObjectMapper();
		T retour = null;
		if (is != null){
			try {
				retour = (T) mapper.readValue(is, retour.getClass());
			} catch (JsonParseException e) {
				Log.e("erreur parsing JSON", e.getMessage());
			} catch (JsonMappingException e) {
				Log.e("erreur mapping JSON", e.getMessage());
			} catch (IOException e) {
				Log.e("erreur IO JSON", e.getMessage());
			}
		}
		return retour;

	}
}
