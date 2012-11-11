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

public class MapReader implements ContentHandler {
	
	private boolean ready = false;
	private String currentValue;
	private Stack<MapObject> objStack;
	
	public MapReader(String worldmap) {
		try {
			XMLReader xmlr = XMLReaderFactory.createXMLReader();
			
			FileReader reader = new FileReader(worldmap);
			InputSource inputSource = new InputSource(reader);
			
			xmlr.setContentHandler(this);
			xmlr.parse(inputSource);
			
			this.ready = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean mapReady() {
		return this.ready;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// save current value
		this.currentValue = new String(ch, start, length);
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void startElement(String uri, String tagName, String qName, Attributes attributes) throws SAXException {
		// handle every tag but not "world" which is used as root
		if(!tagName.equals("world")) {
			try {
				MapObject mapObj = MapObjectFactory.getMapObject(tagName);
				mapObj.handleAttributes(attributes);
				this.objStack.push(mapObj);
			} catch (MapObjectTypeNotDefiniedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void endElement(String uri, String tagName, String qName) throws SAXException {
		// get last element from stack
		MapObject mapObj = this.objStack.pop();
		
		if(this.currentValue != null) {
			mapObj.handleValue(this.currentValue);
			this.currentValue = null;
		}
		
		if(!this.objStack.empty()) {
			// if parent exists on stack add complete child to parent
			MapObject parentMapObj = this.objStack.pop();
			parentMapObj.addChildObj(mapObj);
			
			// push parent back on the stack
			this.objStack.push(parentMapObj);
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
