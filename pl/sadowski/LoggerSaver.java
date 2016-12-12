package pl.sadowski;

import pl.sadowski.interfaces.OutputSaver;
import pl.sadowski.logger.ConsoleHandler;
import pl.sadowski.logger.FileHandler;
import pl.sadowski.logger.LevelCreator;
import pl.sadowski.logger.MyLogger;
import pl.sadowski.logger.SimpleMessage;
import pl.sadowski.logger.interfaces.Logger;

public class LoggerSaver implements OutputSaver {
	Logger l;
	String outputPath;
	
	public LoggerSaver(String outputPath) {
		this.outputPath = outputPath;
		l = MyLogger.getLoggerInstance();
		l.setLevel(LevelCreator.createLevel("INFO"));
		l.addHandler(new FileHandler(outputPath));
	}

	@Override
	public void append(String toSave) {
		l.log(new SimpleMessage(toSave, LevelCreator.createLevel("INFO")));
	}

	@Override
	public void append(String[] toSave) {
		for(String s : toSave) {
			l.log(new SimpleMessage(s, LevelCreator.createLevel("INFO")));
		}
	}

}
