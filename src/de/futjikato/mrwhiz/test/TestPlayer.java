package de.futjikato.mrwhiz.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.futjikato.mrwhiz.App;
import de.futjikato.mrwhiz.game.GamePlayer;

public class TestPlayer {

	protected GamePlayer player;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		App.getInstance().enableUnittestMode();
	}

	@Before
	public void setUp() throws Exception {
		this.player = new GamePlayer(0, 0, 50);
	}

	@Test
	public void testPlayerTakeDamage() {
		int initHealth = this.player.getHealth();
		assertEquals("Inital health is not 100 !", 100, initHealth);
		this.player.damage(20);
		int dmgHealth = this.player.getHealth();
		assertEquals("Damage failed to decrement health !", 80, dmgHealth);
	}
}