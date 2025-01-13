/**
 * Title      : gamePanel.java
 * Description: This class contains the definition of gamePanel.
 *
 * @author Yi Shi
 * @version 1.0
 */

package Wordle;

import javax.swing.*;
import java.awt.*;

public class gamePanel extends JPanel {

    /**
     * Array contains all the letterField
     */
    public static letterField[] letters = new letterField[30];

    /**
     * This is the constructor for gamePanel,
     * it sets the layout and initialize the
     * letters array.
     */
    public gamePanel() {
        this.setLayout(new GridLayout(6, 5));

        //add all letters in to the gamePanel
        for (int i = 0; i < 30; i++) {
            var tempLetter = new letterField();
            tempLetter.setOpaque(true);//set the background color visible
            this.add(tempLetter);
            letters[i] = tempLetter;
        }
    }
}

