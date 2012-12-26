package de.futjikato.mrwhiz.xml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

import de.futjikato.mrwhiz.game.Block;
import de.futjikato.mrwhiz.game.BlockDefinitions;
import de.futjikato.mrwhiz.game.Item;
import de.futjikato.mrwhiz.game.UnknownType;

public class Blockfile extends XmlObject {

	private FileReader blockReader;

	private FileReader defineReader;

	private BlockDefinitions definitions;

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		throw new ObjectNoValueSupport();
	}

	private void readBlockFile(BufferedReader reader) throws IOException, UnknownType {
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

				String blockType = definitions.getType(ch);

				if (blockType.equals(BlockDefinitions.TYPE_BLOCK)) {
					Block cBlock = new Block(x, y, ch, definitions);
					BlockCollector.getInstance().addBlock(cBlock, x, y);
				} else if (blockType.equals(BlockDefinitions.TYPE_ITEM)) {
					Item cItem = new Item(x, y, ch, definitions);
					BlockCollector.getInstance().addBlock(cItem, x, y);
				} else {
					throw new UnknownType(ch);
				}

				x++;
			}

			y++;

			// read next line
			line = reader.readLine();
		}
	}

	private void readDefineFile(FileReader reader) throws IOException {
		Gson gson = new Gson();
		definitions = gson.fromJson(reader, BlockDefinitions.class);
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild {
		if (mapObj instanceof File) {
			File f = (File) mapObj;

			if (f.getEnding().equals(".blocks")) {
				blockReader = f.getReader();
			} else if (f.getEnding().equals(".defines")) {
				defineReader = f.getReader();
			} else {
				throw new ObjectInvalidChild();
			}
		}
	}

	@Override
	protected void complete() throws ObjectIncomplete {
		if (defineReader == null || blockReader == null) {
			throw new ObjectIncomplete();
		}

		try {
			readDefineFile(defineReader);
			readBlockFile(new BufferedReader(blockReader));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownType e) {
			System.out.println("Unknown type found : " + e.getUnknownType());
			e.printStackTrace();
		}
	}
}
