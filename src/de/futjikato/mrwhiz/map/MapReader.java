package de.futjikato.mrwhiz.map;

import java.io.FileReader;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import de.futjikato.mrwhiz.App;
import de.futjikato.mrwhiz.xml.ObjectInvalidChild;
import de.futjikato.mrwhiz.xml.ObjectNoChildSupport;
import de.futjikato.mrwhiz.xml.ObjectNoValueSupport;
import de.futjikato.mrwhiz.xml.World;
import de.futjikato.mrwhiz.xml.XmlObject;
import de.futjikato.mrwhiz.xml.XmlObjectTypes;

//TODO move the reader to the xml package
public class MapReader implements ContentHandler {

	private String currentValue;
	private Stack<XmlObject> objStack = new Stack<XmlObject>();
	private World world;

	public MapReader(String worldmap) {
		try {
			XMLReader xmlr = XMLReaderFactory.createXMLReader();

			FileReader reader = new FileReader(worldmap);
			InputSource inputSource = new InputSource(reader);

			xmlr.setContentHandler(this);
			xmlr.parse(inputSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public World getWorld() {
		return this.world;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// save current value
		this.currentValue = new String(ch, start, length);
	}

	@Override
	public void endDocument() throws SAXException {
		if (this.objStack.size() != 1) {
			return;
		}

		XmlObject mapObj = this.objStack.pop();

		if (!(mapObj instanceof World)) {
			return;
		}

		this.world = (World) mapObj;
	}

	@Override
	public void startElement(String uri, String tagName, String qName, Attributes attributes) throws SAXException {
		// get type
		XmlObjectTypes mapObjType = XmlObjectTypes.valueOf(tagName);
		XmlObject mapObj = mapObjType.getType();

		// handle attributes
		mapObj.handleAttributes(attributes);

		// push onto stack
		this.objStack.push(mapObj);
	}

	@Override
	public void endElement(String uri, String tagName, String qName) throws SAXException {
		// get last element from stack
		XmlObject mapObj = this.objStack.pop();

		if (this.currentValue != null) {
			// trim before processing
			this.currentValue = this.currentValue.trim();
			if (this.currentValue.length() > 0) {
				try {
					mapObj.handleValue(this.currentValue);
				} catch (ObjectNoValueSupport e) {
					if (App.getInstance().isDebug()) {
						System.out.println(String.format("Error in processing value `%s´ for object `%s´", this.currentValue, tagName));
					}
				}
			}
			this.currentValue = null;
		}

		if (!this.objStack.empty()) {
			// if parent exists on stack try to add complete child to parent
			XmlObject parentMapObj = this.objStack.pop();

			try {
				parentMapObj.addChildObj(mapObj);
			} catch (ObjectNoChildSupport e) {
				// element does not support children
				e.printStackTrace();
			} catch (ObjectInvalidChild e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// push parent back on the stack
			this.objStack.push(parentMapObj);
		}

		// put the world back on the stack
		if (mapObj instanceof World) {
			this.objStack.push(mapObj);
		}
	}

	@Override
	public void endPrefixMapping(String arg0) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void ignorableWhitespace(char[] arg0, int arg1, int arg2) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processingInstruction(String arg0, String arg1) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDocumentLocator(Locator arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void skippedEntity(String arg0) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
	}

	@Override
	public void startPrefixMapping(String arg0, String arg1) throws SAXException {
		// TODO Auto-generated method stub

	}
}
