package pl.sadowski.logger;

import java.util.Date;

import pl.sadowski.logger.interfaces.Message;

/**
 * Custom implementation of Message interface
 * 
 * @author Szymon Sadowski
 *
 */
public class SimpleMessage implements Message {
	private Date date = new Date();
	private String msg;
	private Level level;

	/**
	 * Constructor which sets message, level, and sets Date to current Date in
	 * OS
	 * 
	 * @param msg
	 *            Log message
	 * @param level
	 *            Level of importance
	 */
	public SimpleMessage(String msg, Level level) {
		super();
		this.msg = msg;
		this.level = level;
	}

	public Date getDate() {
		return date;
	}

	public String getMessage() {
		return msg;
	}

	public Level getLevel() {
		return level;
	}

	@Override
	public String toString() {
		return "[" + date + " : " + msg + "]";
	}
}
