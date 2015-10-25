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
public class AIGame {
    
    public final int BOARD_SIZE = 8;
    public final char MY_PLAYER = 'x';
    public final char OPPONENT = 'o'; 
    
    public enum Direction {
        UP,
        UP_DIAGONAL_RIGHT,
        RIGHT,
        DOWN_DIAGONAL_RIGHT,
        DOWN,
        DOWN_DIAGONAL_LEFT,
        LEFT,
        UP_DIAGONAL_LEFT
    }
    
    public enum Player {
        MY_PLAYER,
        OPPONENT
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }  
    
    public int evalFunction(char [][] board){
        //try threading later
        return 0;
    }
    
    public int[] getPosition(char [][] board, enum Player){
        int [] arr = new int[2];
        arr[0] = 2;
        arr[1] = 3;
        
        switch(Player){
            case MY_PLAYER:
                for(int i=0; i < BOARD_SIZE; i++){
                    for(int j=0; j < BOARD_SIZE; j++){
                        
                    }
                }
            break;
                
            default:
                  System.out.println("nothing"); 
        }
        
        return arr;
    }
    //public int evalDirectrionWise(char [][] board, enum Direction ){        
    //}
}
