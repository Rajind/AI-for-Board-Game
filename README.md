<h1>Java Implementation of Min-Max tree with Alpha Beta Pruning based AI for the Board Game</h1>

Simple evaluation function  used.
Number of positions player can move – Number of positions opponent can move

It was implemented using two functions. The evalDirectrionWise method will calculate possible move positions directions wise. The evalFunction will give the final value using the direction-wise values.

The Game is as follows

The game has two players: x and o. The players take alternate turns, with player x moving first at the beginning of each game.
Player x starts at position (1,1) while o starts at (8,8).
Each turn, a player can move like a queen in chess (in any of the eight directions) as long as her path does not cross a square already filled in or occupied. After moving, the space vacated by the player is designated as filled and cannot be moved to again. Notice that only the space that was occupied is filled, not the entire path.
The game ends when one player can no longer move, leaving the other player as the winner.
The coordinate (1 1) indicates the top left hand side of the board.
The board is specified as a list of rows. Each row is a list of entries:
- is an empty square
* is a filled in square
x is the current position of the x player
o is the current position of the o player
The board will always be 8 by 8.
Your player function should take in the parameters as described above and return a move as a list in the format (row column) within 1 minute. If you cannot move, return (nil nil). The Tournament Referees will check for this. If you return an illegal move, the other player (and the Tournament Referees) will detect it and you will lose. Additionally if your time expires you will lose.



