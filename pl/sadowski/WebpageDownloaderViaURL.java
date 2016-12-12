package pl.sadowski;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import pl.sadowski.interfaces.WWWPageDownloader;

public class WebpageDownloaderViaURL implements WWWPageDownloader {
	public String downloadPage(String pageURL) {
		StringBuilder content = new StringBuilder();
		try {
			URL u = new URL(pageURL);
			URLConnection conn = u.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				content.append(inputLine);
				content.append("\n");
			}
			br.close();
			System.out.println("Done");
		} catch (MalformedURLException e) {
			return "";
		} catch (IOException e) {
			return "";
		} catch (Exception fin) {
			return "";
		}
		return content.toString();
	}
}
