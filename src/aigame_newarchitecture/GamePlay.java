/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aigame_newarchitecture;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Rajind
 */
public class GamePlay {
    
    public static GameBoard game_board;
    public static AIPlayer ai_player;
    public static Scanner scanner;
    
    public static char[][] deepCopy(char[][] original) {
        if (original == null) {
            return null;
        }

        final char[][] result = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
            // For Java versions prior to Java 6 use the next:
            // System.arraycopy(original[i], 0, result[i], 0, original[i].length);
        }
        return result;
    }
    
    public static void main(String args[]){
        game_board = new GameBoard();
        ai_player = new AIPlayer();
        scanner = new Scanner(System.in);
        
        game_board.printBoard();
        
        while(!game_board.hasWon(PlayerType.PLAYER) || game_board.hasWon(PlayerType.OPPONENT)){
            System.out.println("Waiting for user input (Input Format number,number):"); 
            String user_input = scanner.next();
            user_input.trim();
            
            String [] coordinate = user_input.split(",");
            
            if(coordinate[0].equals("-1") && coordinate[0].equals("-1")){
                game_board.setWin(PlayerType.OPPONENT);
                break;
            }
            
            int row = Integer.parseInt(coordinate[0]) -1;
            int column = Integer.parseInt(coordinate[1]) -1;
            
            List<int[]> allowed_moves = ai_player.generateMoves(deepCopy(game_board.getGame_board()), PlayerType.PLAYER);
            int [] move = new int[]{row, column};
            
            System.out.format("Requested move: row %d column %d\n", move[0] + 1, move[1] + 1);
            
            boolean allowed = false;
            
            //System.out.println("Allowed Moves:");
            for(int [] allowed_move : allowed_moves) {
                //System.out.format("row %d  column %d\n", allowed_move[0], allowed_move[1]);
                if(allowed_move[0] == move[0] && allowed_move[1] == move[1]){
                    allowed = true;
                    break;
                }
            }
            
            //System.out.println("");
            if(!allowed){
                System.out.println("INVALID MOVE");
                game_board.setWin(PlayerType.OPPONENT);
                break;
            }
            
            game_board.movePlayer(PlayerType.PLAYER, move);
            game_board.printBoard();
            
            int [] ai_move = ai_player.player(deepCopy(game_board.getGame_board()));
            
            if(ai_move[0] == -1 && ai_move[1] == -1){
                System.out.println("INVALID MOVE");
                game_board.setWin(PlayerType.OPPONENT);
                break;
            }
            
            game_board.movePlayer(PlayerType.OPPONENT, ai_move);
            game_board.printBoard();
        }
    }
}
