package pl.sadowski.logger;

import pl.sadowski.logger.interfaces.*;

/**
 * Singleton, custom implementation of Logger interface
 * @author Szymon Sadowski
 *
 */
public class MyLogger implements Logger {
	private static Logger INSTANCE;
	private Level level;
	private Handler handler;

	private MyLogger() {
	}

	/**
	 * "getInstance()" type method from Singleton pattern
	 * @return
	 * Only existing instance of Logger
	 */
	public static Logger getLoggerInstance() {
		if (INSTANCE == null)
			INSTANCE = new MyLogger();
		return INSTANCE;
	}

	@Override
	public void setLevel(Level l) {
		level = l;
	}

	@Override
	public void info(Message msg) {
		if(msg.getLevel().toString().equals("INFO")) {
			handler.doHandle(msg);
		}
	}

	@Override
	public void warning(Message msg) {
		if(msg.getLevel().toString().equals("WARNING")) {
			handler.doHandle(msg);
		}
	}

	@Override
	public void error(Message msg) {
		if(msg.getLevel().toString().equals("ERROR")) {
			handler.doHandle(msg);
		}
	}
	
	@Override
	public void log(Message msg) {
		if(msg.getLevel().toString().equals(level.toString())) {
			handler.doHandle(msg);
		}
	}

	@Override
	public void addHandler(Handler h) {
		handler = h;
	}

}
