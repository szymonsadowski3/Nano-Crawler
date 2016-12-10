package pl.sadowski.interfaces;

public interface CrawlerVisitedSet {
	void add(String link);
	boolean contains(String toFind);
}
