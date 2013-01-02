package de.futjikato.mrwhiz.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.futjikato.mrwhiz.game.Block;
import de.futjikato.mrwhiz.game.GameRenderer;
import de.futjikato.mrwhiz.game.Route;

public final class BlockCollector extends XmlObject {

	private Block[][] blockAry;

	private HashMap<String, List<Block>> nameMap = new HashMap<String, List<Block>>();

	private HashMap<String, Route> routeMap = new HashMap<String, Route>();

	private List<Block> patrollingBlocks = new ArrayList<Block>();

	private static BlockCollector instance;

	private BlockCollector() {

	}

	public static BlockCollector getInstance() {
		if (instance == null) {
			instance = new BlockCollector();
		}
		return instance;
	}

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		throw new ObjectNoValueSupport();
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild {
		if (!(mapObj instanceof Blockfile)) {
			throw new ObjectInvalidChild();
		}
	}

	public List<Block> getBlocksByBlockCoords(int bx, int by, int bx2, int by2) {
		List<Block> list = new ArrayList<Block>();

		for ( int cx = bx ; cx <= bx2 ; cx++ ) {
			for ( int cy = by ; cy >= by2 ; cy-- ) {

				// prevent access on negative values
				if (cx < 0 || cy < 0) {
					continue;
				}

				// prevent from running out of upper bound
				if (blockAry.length <= cx || blockAry[cx].length <= cy) {
					continue;
				}

				Block cBlock = blockAry[cx][cy];

				if (cBlock != null) {
					list.add(cBlock);
				}
			}
		}

		return list;
	}

	public Block getBlock(int bx, int by) {
		return blockAry[bx][by];
	}

	public void removeBlock(Block block) {
		blockAry[block.getX()][block.getY()] = null;
	}

	public List<Block> getBlocks(float x, float y, float x2, float y2) {
		int blocksize = GameRenderer.getInstance().getBlocksize();
		int by = (int) Math.floor(y / blocksize);
		int bx = (int) Math.floor(x / blocksize);
		int by2 = (int) Math.floor(y2 / blocksize);
		int bx2 = (int) Math.floor(x2 / blocksize);

		return getBlocksByBlockCoords(bx, by, bx2, by2);
	}

	public void addBlock(Block cBlock, int x, int y) {

		if (blockAry == null) {
			blockAry = new Block[100][100];
		}

		// x is out of old array bounds. copy into new bigger array
		while (blockAry.length < x) {
			Block[][] newBlockAry = new Block[blockAry.length + 100][blockAry[0].length];
			System.arraycopy(blockAry, 0, newBlockAry, 0, blockAry.length);
			blockAry = newBlockAry;
		}

		// y is out of old array bounds. copy into new bigger array
		while (blockAry[0].length < y) {
			Block[][] newBlockAry = new Block[blockAry.length][blockAry[0].length + 100];
			System.arraycopy(blockAry, 0, newBlockAry, 0, blockAry.length);
			blockAry = newBlockAry;
		}

		blockAry[x][y] = cBlock;
	}

	public void addNameList(String name, List<Block> blockList) {
		nameMap.put(name, blockList);
	}

	public List<Block> getBlocksByName(String name) {
		return nameMap.get(name);
	}

	public void addRoute(String cName, Route cRoute) {
		routeMap.put(cName, cRoute);
	}

	public Route getRoute(String name) {
		return routeMap.get(name);
	}

	public void moveBlock(int oldX, int oldY, int newX, int newY) {
		// TODO verify psoition is a valid array position
		blockAry[newX][newY] = blockAry[oldX][oldY];
		blockAry[oldX][newY] = null;
	}

	public List<Block> getPatrollingBlocks() {
		return patrollingBlocks;
	}

	public void addPatrollingBlock(Block cBlock) {
		if (!patrollingBlocks.contains(cBlock)) {
			patrollingBlocks.add(cBlock);
		}
	}

	public void removePatrollingBlock(Block cBlock) {
		if (patrollingBlocks.contains(cBlock)) {
			patrollingBlocks.remove(cBlock);
		}
	}
}
