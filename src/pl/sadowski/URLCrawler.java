package pl.sadowski;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.sadowski.interfaces.Crawler;
import pl.sadowski.interfaces.CrawlerLinkList;
import pl.sadowski.interfaces.CrawlerVisitedSet;
import pl.sadowski.interfaces.OutputSaver;
import pl.sadowski.interfaces.WebpageDownloader;

public class URLCrawler implements Crawler {
	CrawlerLinkList list;
	CrawlerVisitedSet visited;
	OutputSaver saver;
	WebpageDownloader downloader;

	public URLCrawler(CrawlerLinkList list, CrawlerVisitedSet visited, OutputSaver saver,
			WebpageDownloader downloader) {
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
		String s = downloadWebpage(startUrl);
		String foundLinks = findLinks(s);
		saveToList(foundLinks);
		saveToOutput(foundLinks);
		while (!list.isEmpty()) {
			String pulled = list.pull();
			if(!visited.contains(pulled)) {
				visited.add(pulled);
				run(pulled);
			}
		}
		visited.add(startUrl);
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
			list.add(m.group(1));
		}
	}

	public void saveToList(String links) {
		String[] linksArray = links.split("[\\r\\n]+");
		for (String s : linksArray) {
			list.add(s);
		}
	}

	@Override
	public String downloadWebpage(String url) {
		return downloader.downloadWebpage(url);
	}

	public static void main(String[] args) {
		URLCrawler m = new URLCrawler(new CrawlerLinkListOnArrayList(), new CrawlerVisitedSetOnTreeSet(),
				new ToFileSaver(args[0]), new WebpageDownloaderViaURL());
		m.run(args[1]);
	}
}
