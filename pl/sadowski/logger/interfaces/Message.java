package pl.sadowski.logger.interfaces;

import java.util.Date;

import pl.sadowski.logger.Level;

public interface Message {
	/**
	 * Getter
	 * @return
	 * Date of creation of message
	 */
	public Date getDate();
	
	/**
	 * Getter
	 * @return
	 * String which contains log message
	 */
	public String getMessage();
	
	/**
	 * Getter
	 * @return
	 * Level of importance of message
	 */
	public Level getLevel();
}
