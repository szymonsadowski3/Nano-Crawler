package pl.sadowski;

import java.net.URL;
import java.util.HashSet;

import pl.sadowski.interfaces.VisitedPages;

public class VisitedPagesOnTreeSet implements VisitedPages {
	HashSet<URL> visited = new HashSet<URL>();

	@Override
	public boolean pageAlreadyVisited(URL pageURL) {
		return visited.contains(pageURL);
	}

	@Override
	public void addVisitedPage(URL pageURL) {
		visited.add(pageURL);
	}
	
	/*TreeSet<String> visited = new TreeSet<String>();

	@Override
	public void add(String link) {
		visited.add(link);
	}

	@Override
	public boolean contains(String toFind) {
		return visited.contains(toFind);
	}*/
	
}
