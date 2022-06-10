package br.com.foursys.fourcamp.fourstore.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regex {

	public static Boolean validateCpf(String cpf) {
		String regex = "\\b([0-9]{3})\\.([0-9]{3})\\.([0-9]{3})\\-([0-9]{2})";
		String regex1 = "\\b([0-9]{11})";
		Pattern pattern = Pattern.compile(regex);
		Pattern pattern1 = Pattern.compile(regex1);
		Matcher match = pattern.matcher(cpf);
		Matcher match1 = pattern1.matcher(cpf);
		if(match1.find() || match.find()) {
			return true;
		}
		return false;
	}
}
