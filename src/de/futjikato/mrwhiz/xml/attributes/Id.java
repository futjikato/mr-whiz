package de.futjikato.mrwhiz.xml.attributes;

import java.util.HashMap;

import de.futjikato.mrwhiz.xml.XmlObject;

public class Id extends XmlAttribute {

	private static HashMap<String, Id> idList = new HashMap<String, Id>();

	private String idStr;

	private XmlObject ref;

	public void editId(String newIDStr) throws AttributeInvalidInput {
		if (idList.containsKey(this.idStr)) {
			idList.remove(this.idStr);
		}

		idList.put(newIDStr, this);
		this.idStr = newIDStr;
	}

	@Override
	public void handleValue(String value, XmlObject xmlObject) throws AttributeInvalidInput {
		this.ref = xmlObject;
		this.idStr = value;

		if (idList.containsKey(this.idStr)) {
			idList.remove(this.idStr);
		}

		idList.put(this.idStr, this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idStr == null) ? 0 : idStr.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Id other = (Id) obj;
		if (idStr == null) {
			if (other.idStr != null)
				return false;
		} else if (!idStr.equals(other.idStr))
			return false;
		return true;
	}

	public XmlObject getReference() {
		return this.ref;
	}

	public static XmlObject getReferenceById(String id) {
		if (!idList.containsKey(id)) {
			return null;
		}

		Id idObj = idList.get(id);
		return idObj.getReference();
	}

	@Override
	public XmlAttribute copyFrom(XmlAttribute o) throws AttributeCopyError {
		if (!(o instanceof Id)) {
			throw new AttributeCopyError();
		}

		Id oId = (Id) o;

		this.idStr = oId.idStr;
		this.ref = oId.ref;

		return this;
	}
}
