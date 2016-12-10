package pl.sadowski;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import pl.sadowski.interfaces.WebpageDownloader;

public class WepbageDownloaderViaSockets implements WebpageDownloader {

	@Override
	public String downloadWebpage(String url) {
		StringBuilder content = new StringBuilder();
		String host = null;
		String path = null;
		try {
			URL u = new URL(url);
			host = u.getHost();
			path = u.getPath();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
			return "";
		}
		
		try {
			Socket socket = new Socket(host, 80);

			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
			out.println("GET " + path + " HTTP/1.1");
			out.println("Host: " + host);
			out.println();
			out.flush();

			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String inputLine;
			int count = 0;

			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
				content.append("\n");
			}

			in.close();
			return content.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new WepbageDownloaderViaSockets().downloadWebpage("http://student.agh.edu.pl/~woscwozn/"));
	}
}
