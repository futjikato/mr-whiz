package de.futjikato.mrwhiz.game.ui;

import com.google.gson.Gson;
import de.futjikato.mrwhiz.game.GamePlayer;
import org.newdawn.slick.Input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 27.04.13
 * Time: 18:27
 * To change this template use File | Settings | File Templates.
 */
public class InputHandler {

    /**
     * ++++++++++++++++++++++++++++++++
     * + The following properties will be set by gson
     * ++++++++++++++++++++++++++++++++
     */

    protected int jump;
    protected int run;
    protected int moveLeft;
    protected int moveRight;

    /* ++++++++++++++++++++++++++++++++ */

    protected GamePlayer player;

    public static InputHandler create(File configFile, GamePlayer player) throws FileNotFoundException {
        // create instance with gson
        BufferedReader bufferedReader = new BufferedReader(new FileReader(configFile));
        Gson gson = new Gson();
        InputHandler instane = gson.fromJson(bufferedReader, InputHandler.class);

        // set player reference
        instane.player = player;

        return instane;
    }

    public void intersect(Input input) {
        // check for running before move or jump
        running(input);
        move(input);
        jump(input);
    }

    private void running(Input input) {
        player.setRunning(input.isKeyDown(run));
    }

    private void move(Input input) {
        boolean leftPressed = input.isKeyDown(moveLeft);
        boolean rightPressed = input.isKeyDown(moveRight);

        if(leftPressed && rightPressed)
            return;

        if(leftPressed)
            player.moveLeft();

        if(rightPressed)
            player.moveRight();
    }

    private void jump(Input input) {
        if(!player.canJump())
            return;

        if(input.isKeyDown(jump))
            player.jump();
    }
}
