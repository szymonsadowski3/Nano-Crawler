package pl.sadowski;

import java.util.ArrayList;

import pl.sadowski.interfaces.CrawlerLinkList;

public class CrawlerLinkListOnArrayList implements CrawlerLinkList {
	ArrayList<String> list = new ArrayList<String>();
	
	@Override
	public void add(String link) {
		list.add(link);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public String pull() {
		return list.remove(0);
	}

	
}
