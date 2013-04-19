package de.futjikato.mrwhiz.rendering.tests;

import de.futjikato.mrwhiz.App;
import de.futjikato.mrwhiz.rendering.Boundary;
import de.futjikato.mrwhiz.rendering.ImageStorage;
import de.futjikato.mrwhiz.rendering.Storage;
import org.junit.*;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 19.04.13
 * Time: 23:13
 * To change this template use File | Settings | File Templates.
 */
public class StorageTest {

    public Storage imgStorage;

    public Boundary bound;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        App.getInstance().enableUnittestMode();
    }

    @Before
    public void setUp() throws Exception {
        imgStorage = new ImageStorage(new File("resources/data/level/l1.png"));

        bound = new Boundary(null, 0, 149, 10, 50);
    }

    @Test
    public void testPlayerTakeDamage() {
        imgStorage.getStructures(bound);
    }
}
