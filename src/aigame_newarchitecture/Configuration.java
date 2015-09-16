/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aigame_newarchitecture;

/**
 *
 * @author Rajind
 */
public class Configuration {
    
    public static final int BOARD_SIZE = 8;
    public static final int MINMAX_DEPTH = 10;
    
    public static final int[] PLAYER_START_POSITION = new int[] {0,0};
    public static final int[] OPPONENT_START_POSITION = new int[] {BOARD_SIZE - 1, BOARD_SIZE - 1};
    
    public static final char PLAYER_SYMBOL = 'x';          //my player character
    public static char OPPONENT_SYMBOL = 'o';        //my opponent character
    public static char EMPTY_SYMBOL = '-';              //empty character
    public static char FILLED_SYMBOL = '*';             //filled character
}
