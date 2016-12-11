package pl.sadowski;

//TODO relative links

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.sadowski.exceptions.DownloaderException;
import pl.sadowski.interfaces.Crawler;
import pl.sadowski.interfaces.DownloadQueue;
import pl.sadowski.interfaces.OutputSaver;
import pl.sadowski.interfaces.VisitedPages;
import pl.sadowski.interfaces.WWWPageDownloader;

public class URLCrawler implements Crawler {
	DownloadQueue list;
	VisitedPages visited;
	OutputSaver saver;
	WWWPageDownloader downloader;

	public URLCrawler(DownloadQueue list, VisitedPages visited, OutputSaver saver,
			WWWPageDownloader downloader) {
		super();
		this.list = list;
		this.visited = visited;
		this.saver = saver;
		this.downloader = downloader;
	}

	public static void printArrayList(ArrayList<String> list) {
		System.out.println(Arrays.toString(list.toArray(new String[0])));
	}

	public void run(String startUrl) {
		saver.append("Downloading page: " + startUrl);
		String s = downloadWebpage(startUrl);
		String foundLinks = findLinks(s);
		saveToList(foundLinks);
		saveToOutput(foundLinks);
		while (!list.isEmpty()) {
			URL pulled = list.getNextPage();
			if(!visited.pageAlreadyVisited(pulled)) {
				visited.addVisitedPage(pulled);
				run(pulled.toString());
			}
		}
		try {
			visited.addVisitedPage(new URL(startUrl));
		} catch (MalformedURLException e) {
			saver.append("MalformedURLException for: " + startUrl);
		}
	}

	public void saveToOutput(String foundLinks) {
		String[] linksArray = foundLinks.split("[\\r\\n]+");
		saver.append(linksArray);
	}

	String findLinks(String content) {
		StringBuilder links = new StringBuilder();

		Pattern p = Pattern.compile("<[aA] [^>]*href=\"([^\"]+)\"");
		Matcher m = p.matcher(content);

		while (m.find()) {
			links.append(m.group(1));
			links.append("\n");
		}

		return links.toString();
	}

	public void findLinksSaveToList(String content) {
		Pattern p = Pattern.compile("<[aA] [^>]*href=\"([^\"]+)\"");
		Matcher m = p.matcher(content);

		while (m.find()) {
			try {
				list.addPage(new URL(m.group(1)));
			} catch (MalformedURLException e) {
				saver.append("MalformedURLException for: " + m.group(1));
			}
		}
	}

	public void saveToList(String links) {
		String[] linksArray = links.split("[\\r\\n]+");
		for (String s : linksArray) {
			try {
				URL toAdd = new URL(s);
				if(!visited.pageAlreadyVisited(toAdd))
					list.addPage(toAdd);
			} catch (MalformedURLException e) {
				saver.append("MalformedURLException for: " + s);
			}
		}
	}

	@Override
	public String downloadWebpage(String url) {
		try {
			return downloader.downloadPage(url);
		} catch (DownloaderException e) {
			saver.append("DownloaderException for: " + url);
			return "";
		}
	}

	public static void main(String[] args) {
		URLCrawler m = new URLCrawler(new DownloadQueueOnArrayList(), new VisitedPagesOnTreeSet(),
				new LoggerSaver("links.txt"), new WebpageDownloaderViaURL());
		m.run("http://kis.agh.edu.pl/");
	}
}