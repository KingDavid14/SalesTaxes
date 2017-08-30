package com.ve.salestaxes.resources;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 
 * @author Valerio Emanuele
 * This singleton class manage the bundle files
 */
public class BundleManager {
	
	private static final BundleManager INSTANCE = new BundleManager();
	private static final String BUNDLE_NAME = "com.ve.salestaxes.resources.MessagesBundle";
	
	private static ResourceBundle messages;
	private static final transient Logger log = Logger.getLogger(BundleManager.class);
	
	
	private BundleManager(){
		setBundleLocale(Locale.getDefault());
	}


	public static void setBundleLocale(Locale locale) {
		if (locale == null){
			log.warn("Invalid locale value passed, the default one will be used");
		}
		else{
			messages = ResourceBundle.getBundle(BUNDLE_NAME, locale);
		}
	}
	
	
	public static BundleManager getInstance(){
		return INSTANCE;
	}
	
	public String getString(String key){
		if (StringUtils.isEmpty(key)){
			String msg = "The key must be not empty!";
			log.error(msg);
			throw new IllegalArgumentException(msg);
		}
		return messages.getString(key);
	}
	
	public String getString(String key, Object...parameter){
		if (!ObjectUtils.allNotNull(parameter)){
			String msg = "All the parameter must be not empty!";
			log.error(msg);
			throw new IllegalArgumentException(msg);
		}
		return MessageFormat.format(getString(key), parameter);
	}
}