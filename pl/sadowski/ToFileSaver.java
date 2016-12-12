package pl.sadowski;

import java.io.IOException;

import pl.sadowski.interfaces.OutputSaver;
import pl.sadowski.util.MyWriter;

public class ToFileSaver implements OutputSaver {
	String outputPath;
	MyWriter mw;

	public ToFileSaver(String outputPath) {
		super();
		this.outputPath = outputPath;
		mw = new MyWriter(outputPath);
	}

	@Override
	public void append(String toSave) {
		try {
			mw.writeLine(toSave, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void append(String[] toSave) {
		try {
			mw.writeLines(toSave, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
