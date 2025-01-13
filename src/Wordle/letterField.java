/**
 * Title      : letterField.java
 * Description: This class contains the definition of letterField.
 *
 * @author Yi Shi
 * @version 1.0
 */

package Wordle;

import javax.swing.*;
import java.awt.*;

public class letterField extends JLabel {

    /**
     * This is the constructor for letterField,
     * it set the font, background color, border
     * of the letter Field.
     */
    public letterField() {
        super("", JLabel.CENTER);
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        this.setFont(font);
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.white));
    }
}
