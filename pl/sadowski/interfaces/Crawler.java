package pl.sadowski.interfaces;

public interface Crawler {
	public String downloadWebpage(String url);
	public void findLinksSaveToList(String content);
	void run(String startUrl);
}
