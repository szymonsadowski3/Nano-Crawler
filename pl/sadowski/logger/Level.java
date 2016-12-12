package pl.sadowski.logger;

/**
 * Class representing single Level (e.g. INFO/WARNING/ERROR)
 * @author Szymon Sadowski
 *
 */
public class Level {
	/**
	 * String representing level in UpperCase
	 */
	private String level;

	/**
	 * This constructor only sets <var>level</var> String
	 * @param level
	 * String representing level in UpperCase
	 */
	Level(String level) {
		this.level = level;
	}
	
	@Override
	public String toString() {
		return level;
	}
}
