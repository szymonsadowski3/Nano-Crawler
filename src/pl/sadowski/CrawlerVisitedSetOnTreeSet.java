package pl.sadowski;

import java.util.TreeSet;

import pl.sadowski.interfaces.CrawlerVisitedSet;

public class CrawlerVisitedSetOnTreeSet implements CrawlerVisitedSet {
	
	TreeSet<String> visited = new TreeSet<String>();

	@Override
	public void add(String link) {
		visited.add(link);
	}

	@Override
	public boolean contains(String toFind) {
		return visited.contains(toFind);
	}
	
}
