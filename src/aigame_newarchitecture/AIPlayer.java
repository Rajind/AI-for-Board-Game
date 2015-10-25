package aigame_newarchitecture;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rajind
 */
public class AIPlayer {

    private char [][] BOARD;
    private int [] player_position;              //int[2] player position
    private int [] opponent_position;            //int[2] opponent position
    
    //undo position is used in mix max algorithm
    //to try out a position and move back if it is not a good move
    private int [] undo_position;                //int[2] undo position
    
    /**
     * Calculated num of positions player could move to direction wise
     * @param BOARD
     * @param direction
     * @param player_position
     * @param other_player_symbol character of the opponent
     * @return 
     */
    private int evalDirectrionWise(Direction direction, int [] player_position, char other_player_symbol ){        
        int move_count = 0;                         //move count
        int i,j,k;
        
        int [] position_array = player_position;
        
        switch(direction){
            case UP:
                move_count = 0;                     //initiates move count to zero
                i = position_array[0] -1;              //row of player
                j = position_array[1];              //column of player
                
                //starts from zeroth row and goes down in player column
                for(k = i; k >= 0; k--){
                    
                    //if we find a filled suqare or one where opponenet is on we stop there
                    if(BOARD[k][j] == Configuration.FILLED_SYMBOL || BOARD[k][j] == other_player_symbol){
                        break;
                    }
                    
                    //we count empty squares
                    if(BOARD[k][j] == Configuration.EMPTY_SYMBOL){
                        move_count++;  
                    }
                }
            break;
                
            case UP_DIAGONAL_RIGHT:
                move_count = 0;                 //initiates move count to zero
                i = position_array[0] -1;       //row 
                j = position_array[1] +1;       //column
                
                while(i >= 0 && j < Configuration.BOARD_SIZE){
                    //if we find a filled suqare or one where opponenet is on we stop there
                    if(BOARD[i][j] == Configuration.FILLED_SYMBOL || BOARD[i][j] == other_player_symbol){
                        break;
                    }
                    
                    //we count empty squares
                    if(BOARD[i][j] == Configuration.EMPTY_SYMBOL){
                        move_count++;  
                    }
                    i--;
                    j++;
                }
            break;
                
            case RIGHT:
                move_count = 0;
                i = position_array[0];          //row
                j = position_array[1] +1;       //column
                
                for(k = j; k < Configuration.BOARD_SIZE; k++){
                    //if we find a filled suqare or one where opponenet is on we stop there
                    if(BOARD[i][k] == Configuration.FILLED_SYMBOL || BOARD[i][k] == other_player_symbol){
                        break;
                    }
                    
                    //we count empty squares
                    if(BOARD[i][k] == Configuration.EMPTY_SYMBOL){
                        move_count++;  
                    }
                }
            break;
                
            case DOWN_DIAGONAL_RIGHT:
                move_count = 0;
                i = position_array[0] +1;      //row
                j = position_array[1] +1;      //column
                
                while(i < Configuration.BOARD_SIZE && j < Configuration.BOARD_SIZE){
                    //if we find a filled suqare or one where opponenet is on we stop there
                    if(BOARD[i][j] == Configuration.FILLED_SYMBOL || BOARD[i][j] == other_player_symbol){
                        break;
                    }
                    
                    //we count empty squares
                    if(BOARD[i][j] == Configuration.EMPTY_SYMBOL){
                        move_count++;  
                    }
                    i++;
                    j++;
                }
            break;
                
            case DOWN:
                move_count = 0;
                i = position_array[0] +1;       //row
                j = position_array[1];          //column
                
                for(k = i; k < Configuration.BOARD_SIZE; k++){
                    //if we find a filled suqare or one where opponenet is on we stop there
                    if(BOARD[k][j] == Configuration.FILLED_SYMBOL || BOARD[k][j] == other_player_symbol){
                        break;
                    }
                    
                    //we count empty squares
                    if(BOARD[k][j] == Configuration.EMPTY_SYMBOL){
                        move_count++;  
                    }
                }
            break;
                
            case DOWN_DIAGONAL_LEFT:
                move_count = 0;
                i = position_array[0] +1;       //row
                j = position_array[1] -1;       //column
                
                while(i < Configuration.BOARD_SIZE && j >= 0){
                    //if we find a filled suqare or one where opponenet is on we stop there
                    if(BOARD[i][j] == Configuration.FILLED_SYMBOL || BOARD[i][j] == other_player_symbol){
                        break;
                    }
                    
                    //we count empty squares
                    if(BOARD[i][j] == Configuration.EMPTY_SYMBOL){
                        move_count++;  
                    }
                    i++;
                    j--;
                }
            break;
                
            case LEFT:
                move_count = 0;
                i = position_array[0];          //row
                j = position_array[1] -1;       //column
                
                for(k = j; k >= 0 ; k--){
                    //if we find a filled suqare or one where opponenet is on we stop there
                    if(BOARD[i][k] == Configuration.FILLED_SYMBOL || BOARD[i][k] == other_player_symbol){
                        break;
                    }
                    
                    //we count empty squares
                    if(BOARD[i][k] == Configuration.EMPTY_SYMBOL){
                        move_count++;  
                    }
                }
            break;
                
            case UP_DIAGONAL_LEFT:
                move_count = 0;
                i = position_array[0] -1;       //row
                j = position_array[1] -1;       //column
                
                while(i >= 0 && j >= 0){
                    //if we find a filled suqare or one where opponenet is on we stop there
                    if(BOARD[i][j] == Configuration.FILLED_SYMBOL || BOARD[i][j] == other_player_symbol){
                        break;
                    }
                    
                    //we count empty squares
                    if(BOARD[i][j] == Configuration.EMPTY_SYMBOL){
                        move_count++;  
                    }
                    i--;
                    j--;
                }
            break;
                
            default:
                System.out.println("Must not get here");
        }
        
        //System.out.format("Direction wise values: %d\n",move_count);
        return move_count;
    }
    
    /**
     * Evaluation function 
     * @param BOARD
     * @param position_player 
     * @param position_opponent
     * @return 
     */
    private int evalFunction(int [] position_player, int [] position_opponent){
        //try threading later
        //try to improve evaluation function more
        
        //to store number of positions player cound move to
        int player_value = 0;       
        
        //to store number of positions oppotion could move to
        int opponent_value = 0;
        
        //System.out.println("For PlayerType \n");
        //count the number of positions player could move to
        for (Direction dir : Direction.values()) {
            int x = evalDirectrionWise(dir, position_player, Configuration.OPPONENT_SYMBOL);
            player_value = player_value + x;
            //System.out.format("Value for %s: %d \n",dir.toString(),x);
        }
        
        //System.out.println("\n\nFor Opponent \n");
        
        //count the number of positions player could move to
        for (Direction dir : Direction.values()) {
            int x = evalDirectrionWise(dir, position_opponent, Configuration.PLAYER_SYMBOL);
            opponent_value = opponent_value + x;
            //System.out.format("Value for %s: %d \n",dir.toString(),x);
        }
        
        //System.out.format("\n\nTotal Value: %d\n", player_value - opponent_value);
        //returns num of positions player could move to - 
        //num of players opponent could move to
        return (player_value - opponent_value);
    }
    
    /**
     * get position of the player or opponent
     * @param BOARD
     * @param player
     * @return int[2] with row and column values
     */
    private int[] getPosition(PlayerType player){
        int [] position_array = new int[2];
        
        switch(player){
            case PLAYER:
                for(int i=0; i < Configuration.BOARD_SIZE; i++){
                    for(int j=0; j < Configuration.BOARD_SIZE; j++){
                        if(BOARD[i][j] == Configuration.PLAYER_SYMBOL){
                            position_array[0] = i;              //row
                            position_array[1] = j;              //column
                            break;
                        }
                    }
                }
            break;
                
            case OPPONENT:
                for(int i=0; i < Configuration.BOARD_SIZE; i++){
                    for(int j=0; j < Configuration.BOARD_SIZE; j++){
                        if(BOARD[i][j] == Configuration.OPPONENT_SYMBOL){
                            position_array[0] = i;
                            position_array[1] = j;
                            break;
                        }
                    }
                }
            break;
                
            default:
                  System.out.println("Must not get here");
        }
        
        //returns int[2]
        return position_array;
    }
    
    /**
     * Reads position
     * @param player
     * @return 
     */
    private int [] readPosition(PlayerType player){
        if(player == PlayerType.PLAYER){
            return player_position;
        }else{
            return opponent_position;
        }
    }
    
    /**
     * To move a player to a given position
     * @param BOARD
     * @param player
     * @param new_position 
     */
    private void movePlayer(PlayerType player, int [] new_position){
        int [] prev;
        switch(player){
            case PLAYER:
                prev = readPosition(PlayerType.PLAYER);
                undo_position = prev;
                BOARD[prev[0]][prev[1]] = Configuration.FILLED_SYMBOL;
                BOARD[new_position[0]][new_position[1]] = Configuration.PLAYER_SYMBOL;
                player_position = new_position; 
            break;
                
            case OPPONENT:
                prev = readPosition(PlayerType.OPPONENT);
                undo_position = prev;
                BOARD[prev[0]][prev[1]] = Configuration.FILLED_SYMBOL;
                BOARD[new_position[0]][new_position[1]] = Configuration.OPPONENT_SYMBOL;
                opponent_position = new_position;
            break;
            
            default:
                System.out.println("Should never get here");
        }
    }
    
    /**
     * Undo player move
     * @param BOARD
     * @param player
     * @param current_position 
     */
    private void undoMovePlayer(PlayerType player, int [] current_position){
        switch(player){
            case PLAYER:
                BOARD[undo_position[0]][undo_position[1]] = Configuration.PLAYER_SYMBOL;
                BOARD[current_position[0]][current_position[1]] = Configuration.EMPTY_SYMBOL;
                player_position = undo_position; 
            break;
                
            case OPPONENT:
                BOARD[undo_position[0]][undo_position[1]] = Configuration.OPPONENT_SYMBOL;
                BOARD[current_position[0]][current_position[1]] = Configuration.EMPTY_SYMBOL;
                opponent_position = undo_position;            
            break;            
            
            default:
                System.out.println("Should never get here");
        }
    }
    
    /**
     * Minimax (recursive) at level of depth for maximizing or minimizing player
       with alpha-beta cut-off
     * @param depth
     * @param player
     * @param alpha
     * @param beta
     * @return int[3] of {score, row, col} 
     */
    private int[] minmax(int depth, PlayerType player, int alpha, int beta) {
        // Generate possible next moves in a list of int[2] of {row, col}.
        List<int[]> nextMoves = generateMoves(BOARD, player);

        // Configuration.PLAYER_SYMBOL is maximizing; while OPPONENT_SYMBOL is minimizing
        int score;
        int bestRow = -1;
        int bestCol = -1;

        if (nextMoves.isEmpty() || depth == 0) {
            // Gameover or depth reached, evaluate score
            score = evalFunction(player_position, opponent_position);
            if(player == PlayerType.PLAYER){
                //nothing here
            }else{
                score = score*(-1);
            }
            return new int[] {score, bestRow, bestCol};
            
        } else {
            for (int[] move : nextMoves) {
                // try this move for the current "player"
                movePlayer(player, move);
                
                if (player == PlayerType.PLAYER) {  // my_player (computer) is maximizing player
                    score = minmax(depth - 1, PlayerType.OPPONENT, alpha, beta)[0];
                    if (score > alpha) {
                       alpha = score;
                       bestRow = move[0];
                       bestCol = move[1];
                    }
                } else {  // opponent is minimizing player
                    score = minmax(depth - 1, PlayerType.PLAYER, alpha, beta)[0];
                    if (score < beta) {
                       beta = score;
                       bestRow = move[0];
                       bestCol = move[1];
                    }
                }
                
                // undo move
                undoMovePlayer(player, move);
                
                // cut-off
                if (alpha >= beta) break;
            }
            return new int[] {(player == PlayerType.PLAYER) ? alpha : beta, bestRow, bestCol};
        }
    }
    
    /**
     * Generates a list of moves directiWise
     * @param BOARD
     * @param moves
     * @param direction
     * @param player_position
     * @param opp_symbol 
     */
    private void generateMovesDirectionWise(List<int[]> moves, Direction direction, int [] player_position, char opp_symbol){
        int i,j,k;
        int [] position_array = player_position;
        
        switch(direction){
            case UP:
                i = position_array[0] -1;      //row
                j = position_array[1];      //column
                
                for(k = i; k >= 0; k--){
                    if(BOARD[k][j] == Configuration.FILLED_SYMBOL || BOARD[k][j] == opp_symbol){
                        break;
                    }
                    
                    if(BOARD[k][j] == Configuration.EMPTY_SYMBOL){
                        moves.add(new int[] {k, j});
                    }
                }
            break;
                
            case UP_DIAGONAL_RIGHT:
                i = position_array[0] -1;      //row
                j = position_array[1] +1;      //column
                
                while(i >= 0 && j < Configuration.BOARD_SIZE){
                    if(BOARD[i][j] == Configuration.FILLED_SYMBOL || BOARD[i][j] == opp_symbol){
                        break;
                    }
                    
                    if(BOARD[i][j] == Configuration.EMPTY_SYMBOL){
                        moves.add(new int[] {i, j});  
                    }
                    i--;
                    j++;
                }
            break;
                
            case RIGHT:
                i = position_array[0];      //row
                j = position_array[1] +1;      //column
                
                for(k = j; k < Configuration.BOARD_SIZE; k++){
                    if(BOARD[i][k] == Configuration.FILLED_SYMBOL || BOARD[i][k] == opp_symbol){
                        break;
                    }
                    
                    if(BOARD[i][k] == Configuration.EMPTY_SYMBOL){
                        moves.add(new int[] {i, k});  
                    }
                }
            break;
                
            case DOWN_DIAGONAL_RIGHT:
                i = position_array[0] +1;      //row
                j = position_array[1] +1;      //column
                
                while(i < Configuration.BOARD_SIZE && j < Configuration.BOARD_SIZE){
                    if(BOARD[i][j] == Configuration.FILLED_SYMBOL || BOARD[i][j] == opp_symbol){
                        break;
                    }
                    
                    if(BOARD[i][j] == Configuration.EMPTY_SYMBOL){
                        moves.add(new int[] {i, j});  
                    }
                    i++;
                    j++;
                }
            break;
                
            case DOWN:
                i = position_array[0] +1;      //row
                j = position_array[1];      //column
                
                for(k = i; k < Configuration.BOARD_SIZE; k++){
                    if(BOARD[k][j] == Configuration.FILLED_SYMBOL || BOARD[k][j] == opp_symbol){
                        break;
                    }
                    
                    if(BOARD[k][j] == Configuration.EMPTY_SYMBOL){
                        moves.add(new int[] {k, j});  
                    }
                }
            break;
                
            case DOWN_DIAGONAL_LEFT:
                i = position_array[0] +1;      //row
                j = position_array[1] -1;      //column
                
                while(i < Configuration.BOARD_SIZE && j >= 0){
                    if(BOARD[i][j] == Configuration.FILLED_SYMBOL || BOARD[i][j] == opp_symbol){
                        break;
                    }
                    
                    if(BOARD[i][j] == Configuration.EMPTY_SYMBOL){
                        moves.add(new int[] {i, j});  
                    }
                    i++;
                    j--;
                }
            break;
                
            case LEFT:
                i = position_array[0];      //row
                j = position_array[1] -1;      //column
                
                for(k = j; k >= 0 ; k--){
                    if(BOARD[i][k] == Configuration.FILLED_SYMBOL || BOARD[i][k] == opp_symbol){
                        break;
                    }
                    
                    if(BOARD[i][k] == Configuration.EMPTY_SYMBOL){
                        moves.add(new int[] {i, k});  
                    }
                }
            break;
                
            case UP_DIAGONAL_LEFT:
                i = position_array[0] -1;      //row
                j = position_array[1] -1;      //column
                
                while(i >= 0 && j >= 0){
                    if(BOARD[i][j] == Configuration.FILLED_SYMBOL || BOARD[i][j] == opp_symbol){
                        break;
                    }
                    
                    if(BOARD[i][j] == Configuration.EMPTY_SYMBOL){
                        moves.add(new int[] {i, j});  
                    }
                    i--;
                    j--;
                }
            break;
                
            default:
                System.out.println("Must not get here");
        }
    }
    
    /**
     * Generates list of available moves
     * @param board
     * @param player
     * @return 
     */
    public List<int[]> generateMoves(char [][] board, PlayerType player) {
        BOARD = board;
        List<int[]> nextMoves = new ArrayList<>(); // allocate List
        
        if(player == PlayerType.PLAYER){
            player_position = getPosition(player);
            for (Direction dir : Direction.values()) {
                generateMovesDirectionWise(nextMoves, dir, player_position, Configuration.OPPONENT_SYMBOL);
            }
        }else{
            opponent_position = getPosition(player);
            for (Direction dir : Direction.values()) {
                generateMovesDirectionWise(nextMoves, dir, opponent_position, Configuration.PLAYER_SYMBOL);
            }
        }
      
      return nextMoves;
    } 
    
    /**
     * Returns moves using the MINMAX with Alpha Beta Pruning Algorithm
     * @param board
     * @return 
     */
    public int[] player(char [][] board){
        BOARD = board;
        
        // depth, max-turn, alpha, beta
        int[] result = minmax(Configuration.MINMAX_DEPTH, PlayerType.OPPONENT, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("\nAI Move: ");
        
        //correction +1 is added as numbering starts from 1,1
        System.out.format("Row: %d  Column: %d\n",result[1]+1, result[2]+1);
           
        return new int[] {result[1], result[2]};   // row, col
    }

    public char[][] getBOARD() {
        return BOARD;
    }
    
    public void setBOARD(char[][] BOARD) {
        this.BOARD = BOARD;
    }
}
