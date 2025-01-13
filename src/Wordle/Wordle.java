/**
 * Title      : Wordle.java
 * Description: This class contains the main function of wordle game.
 *
 * @author Yi Shi
 * @version 1.0
 */

package Wordle;

public class Wordle {

    /**
     * This is the main function of Wordle.
     *
     * @param args String array from command line.
     */
    public static void main(String[] args) {
        var game = new mainInterface("Wordle");
        game.setVisible(true);
    }
}