package pl.sadowski.interfaces;

public interface CrawlerLinkList {
	void add(String link);
	boolean isEmpty();
	String pull();
}
