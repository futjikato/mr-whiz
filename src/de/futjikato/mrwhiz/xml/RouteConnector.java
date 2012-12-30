package de.futjikato.mrwhiz.xml;

import java.util.List;

import de.futjikato.mrwhiz.game.Block;
import de.futjikato.mrwhiz.game.Route;
import de.futjikato.mrwhiz.xml.attributes.Target;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;

public class RouteConnector extends XmlObject {

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		String target = this.getTarget();
		if (target == null) {
			return;
		}

		BlockCollector bc = BlockCollector.getInstance();

		List<Block> blocks = bc.getBlocksByName(target);
		Route cRoute = bc.getRoute(currentValue);
		for ( Block cBlock : blocks ) {
			cBlock.setRoute(cRoute);
			cBlock.startPatroll();
		}
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild {
		// TODO Auto-generated method stub

	}

	public String getTarget() {
		XmlAttribute xmlAttr = getAttribute("target");
		if (xmlAttr instanceof Target) {
			Target targetAttr = (Target) xmlAttr;
			return targetAttr.getValue();
		}

		return null;
	}
}
