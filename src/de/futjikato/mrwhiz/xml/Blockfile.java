package de.futjikato.mrwhiz.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import de.futjikato.mrwhiz.game.Block;

public class Blockfile extends XmlObject {

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		try {
			// load file
			BufferedReader reader = new BufferedReader(new FileReader(new File(currentValue)));
			readBlockFile(reader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readBlockFile(BufferedReader reader) throws IOException {
		String line = reader.readLine();

		int y = 0;
		while (line != null) {

			char[] chars = line.toCharArray();
			int x = 0;
			for ( char ch : chars ) {

				// air block
				if (ch == '0') {
					x++;
					continue;
				}

				Block cBlock = new Block(x, y, ch);
				BlockCollector.getInstance().addBlock(cBlock, x, y);
				x++;
			}

			y++;

			// read next line
			line = reader.readLine();
		}
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild {
		throw new ObjectNoChildSupport();
	}

}
