package pl.sadowski.interfaces;

import java.net.URL;

public interface VisitedPages {
	/**
     * Sprawdza czy dana strona byla juz odwiedzana.
     * 
     * @param pageURL adres strony do sprawdzenia
     * @return true - strona byla juz wczesniej odwiedzana, false -
     *         w przeciwnym razie
     */ 
     boolean pageAlreadyVisited(URL pageURL);

    /**
     * Rejestruje strone o podanym adresie jako odwiedzona.
     *
     * @param pageURL adres odwiedzonej strony
     */
    void addVisitedPage(URL pageURL);
}
