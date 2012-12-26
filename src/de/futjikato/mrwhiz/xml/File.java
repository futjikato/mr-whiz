package de.futjikato.mrwhiz.xml;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class File extends XmlObject {

	private String ending;

	private FileReader reader;

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		try {
			// load file
			java.io.File f = new java.io.File(currentValue);
			reader = new FileReader(f);
			ending = f.getName().substring(f.getName().lastIndexOf('.'));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild {
		throw new ObjectNoChildSupport();
	}

	public String getEnding() {
		return ending;
	}

	public FileReader getReader() {
		return reader;
	}
}
