package aigame_newarchitecture;

/**
 *
 * @author Rajind
 */
public class GameBoard {
    
    private char [][] game_board;
    private final int BOARD_SIZE;
    
    private int [] player_position;      //user 
    private int [] opponent_position;      //AI
    
    //for undo move purposes
    private int [] prev_player_position;
    private int [] prev_opponent_position;
    
    private boolean player_won;          
    private boolean opponent_won;
    
    public GameBoard(){
        
        BOARD_SIZE = Configuration.BOARD_SIZE;
        
        player_won = false;
        opponent_won = false;
        
        player_position = Configuration.PLAYER_START_POSITION;  
        opponent_position = Configuration.OPPONENT_START_POSITION;
        
        generateBoard(player_position, opponent_position);
        /*
        game_board = new char[][]{
            { 'x', '-', '-', '-', '-', '-', '-', '-'},
            { '-', '-', '-', '-', '-', '-', '-', '-'},
            { '-', '-', '-', '-', '-', '-', '-', '-'},
            { '-', '-', '-', '-', '-', '-', '-', '-'},
            { '-', '-', '-', '-', '-', '-', '-', '-'},
            { '-', '-', '-', '-', '-', '-', '-', '-'},
            { '-', '-', '-', '-', '-', '-', '-', '-'},
            { '-', '-', '-', '-', '-', '-', '-', 'o'},
        };
        */
    }
    
    /**
     * Generates Board
     * @param player_start_position
     * @param opponent_start_position 
     */
    public void generateBoard(int[] player_start_position, int[] opponent_start_position){
        game_board = new char [BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i < BOARD_SIZE; i++){
            for(int j=0; j < BOARD_SIZE; j++){
                game_board[i][j] = Configuration.EMPTY_SYMBOL;
            }
        }
        game_board[player_start_position[0]][player_start_position[1]] = Configuration.PLAYER_SYMBOL;
        game_board[opponent_start_position[0]][opponent_start_position[1]] = Configuration.OPPONENT_SYMBOL;
        
        System.out.println("Game Board Generated\n");
    }
    
    /**
     * Prints Board State
     * @param board 
     */
    public void printBoard(){
        for(int i=0; i < BOARD_SIZE; i++){
            System.out.print(" ---------------------------------\n");
            for(int j=0; j < BOARD_SIZE; j++){
                System.out.print(" | "+game_board[i][j]);
            }
            System.out.print(" |\n");
        }
        System.out.print(" ---------------------------------\n");
    }
      
    /**
     * Returns if player has won or not
     * @param player
     * @return 
     */
    public boolean hasWon(PlayerType player){
        
        /*
        if(player == PlayerType.PLAYER_SYMBOL){
            return player_won;
        }else {
            return opponent_won;
        }
        */
        
        boolean return_value = false;
        
        switch(player){
            case PLAYER:
                return_value = player_won;
            break;
              
            case OPPONENT:
                return_value = opponent_won;
            break;
                
            default:
                System.out.println("Sould never get here");
        }
        return return_value;
    }
    
    /**
     * To move a player to a given position
     * @param player
     * @param new_position 
     */
    public void movePlayer(PlayerType player, int [] new_position){
        switch(player){
            case PLAYER:
                prev_player_position = player_position;
                game_board[player_position[0]][player_position[1]] = Configuration.FILLED_SYMBOL;
                game_board[new_position[0]][new_position[1]] = Configuration.PLAYER_SYMBOL;
                player_position = new_position; 
            break;
                
            case OPPONENT:
                prev_opponent_position = opponent_position;
                game_board[opponent_position[0]][opponent_position[1]] = Configuration.FILLED_SYMBOL;
                game_board[new_position[0]][new_position[1]] = Configuration.OPPONENT_SYMBOL;
                opponent_position = new_position;
            break;
            
            default:
                System.out.println("Should never get here");
        }
    }
    
    /**
     * Sets who won the game
     * @param player 
     */
    public void setWin(PlayerType player){
        switch(player){
            case PLAYER:
                player_won = true;
                System.out.println("GAME OVER:  USER WINS");
            break;
                
            case OPPONENT:
                opponent_won = true;
                System.out.println("GAME OVER:  AI WINS");
            break;
        }
    }

    /**
     * Getter for board
     * @return 
     */
    public char[][] getGame_board() {
        return game_board;
    }

    /**
     * Setter for board
     * @param game_board 
     */
    public void setGame_board(char[][] game_board) {
        this.game_board = game_board;
    }
}
