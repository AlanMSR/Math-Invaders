package com.mygdx.game;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;

public class PlayerControllerListener extends ControllerAdapter {
    private int playerNumber;

    public PlayerControllerListener(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        // Handle button down event for the corresponding player
        if (playerNumber == 1) {
            // Handle Player 1's button down event
        } else if (playerNumber == 2) {
            // Handle Player 2's button down event
        }
        return true;  // Return true to consume the event
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        // Handle button up event for the corresponding player
        if (playerNumber == 1) {
            // Handle Player 1's button up event
        } else if (playerNumber == 2) {
            // Handle Player 2's button up event
        }
        return true;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        // Handle analog stick or trigger movement for the corresponding player
        if (playerNumber == 1) {
            // Handle Player 1's analog stick or trigger movement
        } else if (playerNumber == 2) {
            // Handle Player 2's analog stick or trigger movement
        }
        return true;
    }
}
