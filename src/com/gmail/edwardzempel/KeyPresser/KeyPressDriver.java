package com.gmail.edwardzempel.KeyPresser;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Experiment to simulate something that would type to the console. This was an idea for a game with the nephews.
 */
public class KeyPressDriver {
    private static final int CHARACTER_PAUSE = 100;
    private static final int PERIOD_PAUSE = 500;


    public static void main(String[] args) {
        System.out.println("Hello Keypresser");
        ghostPrint("I'm still here. Bet you didn't think that was possible.");
        runNotepad("helloFriend.txt");
        ghostType("I like writing here better.", 1000);

    }

    /**
     * Type out the message with the specified leading time delay
     *
     * @param message to type to the current open application
     * @param ms      leading time delay
     */
    private static void ghostType(String message, int ms) {
        try {
            Robot robot = new Robot();
            robot.delay(ms);

            for (char c : message.toCharArray()) {
                int keyInt = KeyEvent.getExtendedKeyCodeForChar(c);
                robot.keyPress(keyInt);
                robot.keyRelease(keyInt);
                if (keyInt != KeyEvent.VK_PERIOD) {
                    robot.delay(Math.max(CHARACTER_PAUSE, 0));
                } else {
                    robot.delay(Math.max(PERIOD_PAUSE, 0));
                }
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }


    }

    /**
     * Send the message to system out once character at a time
     *
     * @param message for console
     */
    public static void ghostPrint(String message) {
        try {
            Robot robot = new Robot();
            for (char c : message.toCharArray()) {
                int keyInt = KeyEvent.getExtendedKeyCodeForChar(c);
                System.out.print(c);
                if (keyInt != KeyEvent.VK_PERIOD) {
                    robot.delay(Math.max(CHARACTER_PAUSE, 0));
                } else {
                    robot.delay(Math.max(PERIOD_PAUSE, 0));
                }
            }
            System.out.println();

        } catch (AWTException e) {
            e.printStackTrace();
        }


    }

    /**
     * Run notepad with the default filename
     */
    private static void runNotepad() {
        runNotepad("");
    }

    /**
     * Run notepad with the specified filename
     *
     * @param filename name of file to create
     */
    private static void runNotepad(String filename) {
        Runtime rs = Runtime.getRuntime();
        try {
            rs.exec("notepad" + (filename.trim().length() > 0 ? " " + filename : ""));
            Robot robot = new Robot();
            robot.delay(2000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(100);
        } catch (IOException e) {
            ghostPrint("I tried.");
            throw new RuntimeException("couldn't open notepad");
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

}
