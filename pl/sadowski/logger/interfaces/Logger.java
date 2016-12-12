package pl.sadowski.logger.interfaces;

import pl.sadowski.logger.Level;
import pl.sadowski.logger.LevelCreator;
import pl.sadowski.logger.SimpleMessage;

/**
 * Logger interface for saving log informations
 * 
 * @author Szymon Sadowski
 *
 */
public interface Logger {
	/**
	 * Setter
	 * 
	 * @param level
	 *            level of importance (e.g. INFO/WARNING/ERROR)
	 */
	public void setLevel(Level level);

	/**
	 * Send message with INFO level of importance (if another Level is set in
	 * message, message will not be printed)
	 * 
	 * @param msg
	 *            Message with some level of importance
	 */
	void info(Message msg);

	/**
	 * Send message with WARNING level of importance (if another Level is set in
	 * message, message will not be printed)
	 * 
	 * @param msg
	 *            Message with level of importance
	 */
	void warning(Message msg);

	/**
	 * Send message with ERROR level of importance (if another Level is set in
	 * message, message will not be printed)
	 * 
	 * @param msg
	 *            Message with level of importance
	 */
	void error(Message msg);

	/**
	 * Send message only if its Level matches the level which is set in this
	 * Logger
	 * 
	 * @param msg
	 *            Message with level of importance
	 */
	void log(Message msg);

	/**
	 * Add handler, which allows to print log message on desired output (e.g. console/file/e-mail)
	 * @param handler
	 * (e.g. console/file/e-mail handler)
	 */
	void addHandler(Handler handler);
}
