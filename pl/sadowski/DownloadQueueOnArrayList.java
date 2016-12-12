package pl.sadowski;

import java.net.URL;
import java.util.ArrayList;

import pl.sadowski.interfaces.DownloadQueue;

public class DownloadQueueOnArrayList implements DownloadQueue {
	ArrayList<URL> list = new ArrayList<URL>();

	@Override
	public void addPage(URL pageURL) {
		list.add(pageURL);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public URL getNextPage() {
		return list.remove(0);
	}
	
}
