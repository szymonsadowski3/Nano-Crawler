package pl.sadowski.logger;

import java.io.IOException;
import java.io.PrintWriter;

import pl.sadowski.logger.interfaces.Handler;
import pl.sadowski.logger.interfaces.Message;

/**
 * Handler that prints log message to Text File
 * @author Szymon Sadowski
 *
 */
public class FileHandler implements Handler {
	/**
	 * Path to file, where handler writes log messages
	 */
	private String outputPath;
	
	/**
	 * Auxiliary, which clears file before using it
	 */
	private void clearFile() {
		try {
			LogFileWriter.writeLine(outputPath, "", false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This constructor only sets outputPath
	 * @param outputPath
	 * Path to file, where handler writes log messages
	 */
	public FileHandler(String outputPath) {
		super();
		this.outputPath = outputPath;
		clearFile();
	}

	@Override
	public void doHandle(Message msg) {
		try {
			LogFileWriter.writeLine(outputPath, msg.toString(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
