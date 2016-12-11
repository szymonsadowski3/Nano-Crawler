package pl.sadowski.interfaces;

import java.net.URL;

public interface DownloadQueue {
	/*void add(String link);
	boolean isEmpty();
	String pull();*/
	
	/**
     * Dodaje adres strony do odwiedzenia na koniec kolejki
     *
     * @param pageURL adres strony do odwiedzenia
     */
     void addPage(URL pageURL);

    /**
     * Zwraca informacje czy kolejka jest pusta, czy nie
     * @return true - kolejka pusta, false - w przeciwnym razie
     */
    boolean isEmpty();

    /**
     * Zwraca adres pierwszej strony w kolejce, ktora ma zostac odwiedzona i
     * usuwa ja z kolejki.
     * 
     * @return adres URL strony do odwiedzenia
     */ 
     URL getNextPage();
}
