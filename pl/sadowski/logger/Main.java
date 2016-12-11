package pl.sadowski.logger;

import pl.sadowski.logger.interfaces.Logger;

public class Main {
	public static void main(String[] args) {
		Logger l = MyLogger.getLoggerInstance();
		l.setLevel(LevelCreator.createLevel("INFO"));
		l.addHandler(new ConsoleHandler());
		
		l.log(new SimpleMessage("1st error", LevelCreator.createLevel("INFO")));
		l.log(new SimpleMessage("2nd error", LevelCreator.createLevel("ERROR")));
		l.log(new SimpleMessage("3rd error", LevelCreator.createLevel("INFO")));
		l.log(new SimpleMessage("4th error", LevelCreator.createLevel("INFO")));
		l.log(new SimpleMessage("5th error", LevelCreator.createLevel("WARNING")));
		l.log(new SimpleMessage("6th error", LevelCreator.createLevel("ERROR")));
		l.log(new SimpleMessage("7th error", LevelCreator.createLevel("WARNING")));
		
		l.addHandler(new FileHandler("logs.txt"));
		l.setLevel(LevelCreator.createLevel("WARNING"));
		
		l.log(new SimpleMessage("1st error", LevelCreator.createLevel("INFO")));
		l.log(new SimpleMessage("2nd error", LevelCreator.createLevel("ERROR")));
		l.log(new SimpleMessage("3rd error", LevelCreator.createLevel("INFO")));
		l.log(new SimpleMessage("4th error", LevelCreator.createLevel("INFO")));
		l.log(new SimpleMessage("5th error", LevelCreator.createLevel("WARNING")));
		l.log(new SimpleMessage("6th error", LevelCreator.createLevel("ERROR")));
		l.log(new SimpleMessage("7th error", LevelCreator.createLevel("WARNING")));
	}
}
