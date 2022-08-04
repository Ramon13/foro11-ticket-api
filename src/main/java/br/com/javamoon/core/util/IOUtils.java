package br.com.javamoon.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.util.StreamUtils;

public class IOUtils {

	private IOUtils() {}
	
	public static String getContentFromResource(String resourceName) {
		try (InputStream is = IOUtils.class.getResourceAsStream(resourceName)) {
			return StreamUtils.copyToString(is, Charset.forName("UTF-8"));
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
}
