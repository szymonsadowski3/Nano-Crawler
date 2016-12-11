package pl.sadowski.logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Simple class which allows to write some content to file
 * @author SzymonSadowski
 *
 */
public class LogFileWriter {
	/**
	 * Simple method which allows to write some lines to file
	 * @param path
	 * To file under this path method will write
	 * @param lines
	 * Array of Strings to be written
	 * @param append
	 * Set to true if method should append content to file, false if method should write from scratch
	 * @throws IOException
	 */
	static void writeLines(String path, String[] lines, boolean append) throws IOException {
		File outputFile = new File(path);
		outputFile.createNewFile();
		FileWriter w = new FileWriter(outputFile, append);

		for (String line : lines) {
			w.write(line);
			w.write(System.lineSeparator());
		}

		w.close();
	}

	/**
	 * Simple method which allows to write single to file
	 * @param path
	 * To file under this path method will write
	 * @param line
	 * String to be written
	 * @param append
	 * Set to true if method should append line to file, false if method should write from scratch
	 * @throws IOException
	 */
	static void writeLine(String path, String line, boolean append) throws IOException {
		File outputFile = new File(path);
		outputFile.createNewFile();
		FileWriter w = new FileWriter(outputFile, append);

		w.write(line);
		
		if(!line.isEmpty())
			w.write(System.lineSeparator());

		w.close();
	}
}
