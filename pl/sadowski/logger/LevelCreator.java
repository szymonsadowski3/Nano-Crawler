package pl.sadowski.logger;

/**
 * Class which contains Factory Method Design Pattern for Level class
 * @author Szymon Sadowski
 * @see pl.sadowski.logger.Level
 *
 */
public class LevelCreator {
	/**
	 * Factory Method for Level class
	 * @param type
	 * Type of Level (case insensitive)
	 * @return
	 * Created Level
	 */
	public static Level createLevel(String type) {
		String typeLower = type.toUpperCase();
		switch (typeLower) {
        case ("INFO"):
            return new Level("INFO");
        case ("WARNING"):
        	return new Level("WARNING");
        case ("ERROR"):
        	return new Level("ERROR");
        default:
        	return new Level("INFO");
        }
	}
}