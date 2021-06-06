package AiHex.players;

import AiHex.gameMechanics.Runner;

import java.awt.Point;
import java.util.ArrayList;

import AiHex.hexBoards.Board;
import AiHex.gameMechanics.Move;
import AiHex.hexBoards.ScoreBoard;

import java.util.List;
import java.util.Queue;



public class PointAndClickPlayer implements Player {
    public Runner game;
    private int colour;


    public PointAndClickPlayer() {
        game = null;
        colour = 0;
    }
    public PointAndClickPlayer(Runner game, int colour) {
        this.game = game;
        this.colour = colour;
    }
    static int x1 = 0, y1 = 0;
    public Move getMove() {
        switch (colour) {
            case Board.RED:
                System.out.print("Red move: ");
                break;
            case Board.BLUE:
                System.out.print("Blue move: ");
                break;
        }

        while (game.getBoard().getSelected() == null) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Point choice = game.getBoard().getSelected();

        x1 = choice.x;
        y1 = choice.y;
        Move move = new Move(colour, choice.x, choice.y);

        game.getBoard().setSelected(null);

        return move;
    }

    public ArrayList < Board > getAuxBoards() {
        return new ArrayList < Board > ();
    }

    static int f1 = 0, f2 = 0;
    static int k1 = 0, k2 = 0;
    static int f = 0, fp = 0;
    static int firstturn = 0;
    static boolean nextturn=false;
    static boolean restart=false;
    public Move getMove2() {
        switch (colour) {
            case Board.RED:
                System.out.print("Red move: ");
                break;
            case Board.BLUE:
                System.out.print("Blue move: ");
                break;
        }



        tree dnktree = new tree();
        /////////////////first time random move do then kro dundudnudnudnudnun
        int arr[] = new int[2];

        Move move;

        if (firstturn == 0) {
                 int randi=(int)(Math.random()*(game.getBoard().size-1)-1)+0;
                 int randj=(int)(Math.random()*(game.getBoard().size-1)-1)+0;
                 while(game.getBoard().get(randi,randj)!=0){
                 randi=(int)(Math.random()*(game.getBoard().size-1)-1)+0;
                 randj=(int)(Math.random()*(game.getBoard().size-1)-1)+0;
                 }
                
            move = new Move(colour, randi, randj);
           
            k1 = randi;
            k2 = randj;
            

            f1 = randi;
            f2 = randj;

            firstturn++;
        }
         
                else {

            ////////////////

            if(fp==0){
             int value = game.getBoard().get(k1, k2);
             if(value>=1&&k2==0){
                    System.out.println("absb");
                 if(k2+1<game.getBoard().size){          // upright , down
                    if( game.getBoard().get(k1,k2+1)>=1 ){
                           System.out.println("abb");
                   nextturn=true;
                    fp=1;
                   
                  
                    }
                 
                 }
             }
            
            }


            arr = dnktree.maketree(game, k1, k2, f1, f2);
         
            move = new Move(colour, arr[0], arr[1]);
            k1 = arr[0];
            k2 = arr[1];
        }
        game.getBoard().setSelected(null);



        return move;





    }




}

//

class node {
    node left;
    node right;
    int val;

    public node() {
        this.left = null;
        this.right = null;
        this.val = 0;
    }

}

class tree {
    node head;
    int alpha;
    int beta;
    static int vals = 0;
    static int d = 0, d2 = 0;
     static boolean global=false;

    public List < int[] > generateMoves(Runner game, int[][] myboard,int index1,int index2,int val) {
        List < int[] > nextMoves = new ArrayList(); // allocate List

        // If gameover, i.e., no next move
        /*     if (hasWon(mySeed) || hasWon(oppSeed)) {
                return nextMoves;   // return empty list
             }*/

        // Search for empty cells and add to the List
        for (int row = 0; row < game.getBoard().size; row++) {
            for (int col = 0; col < game.getBoard().size; col++) {
                if (myboard[row][col]>=250  && game.getBoard().get(row,col)==0) {
                    nextMoves.add(new int[] {
                        row,
                        col
                    });

                }
            }
        }
        return nextMoves;
    }
    int evaluationfuncforTree(Runner game, int myboard[][], int index1, int index2, int index3, int index4) {


        int x = game.getBoard().size;
        
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {

                myboard[i][j] = 0;

            }


        }

        int edge1[] = new int[x];
        for (int i = 0; i < x; i++) {
            edge1[i] = i;


        }
        int edge2[] = new int[x];
        for (int i = 0; i < x; i++) {
            edge2[i] = (x - 1) - i;


        }

        boolean leftnodealreadyConnected = false;
        if(global==false){
        for (int i = 0; i < x && vals == 0; i++) {
            if (index2==i && index1==0) {
              
                leftnodealreadyConnected = true;
                vals = 1;

            }

        }}
        
        for (int i = 0; i < x && vals == 1; i++) {
         
            if (index3==x-1 && index4==i) {
              
                leftnodealreadyConnected = false;
                vals = 0;
               

            } else if (index1==x-1 && index2==i) {
                global=true;
                leftnodealreadyConnected = false;
                vals = 0;
            }

        }
       
        int score = 0;
        if (leftnodealreadyConnected == false && vals == 0) {
                  System.out.println(global);
            score = evaluationfuncforleftTree(game, myboard, edge1, index1, index2);

        } else {

            if (d == 0) {
               
                score = evaluationfuncforrightTree(game, myboard, edge2, index3, index4);
               d++;
            }
            else{
            score = evaluationfuncforrightTree(game, myboard, edge2, index1, index2);
            }
        }
        return score;
    }


    int evaluationfuncforrightTree(Runner game, int[][] myboard, int edge2[], int index1, int index2) {


        int right = 0;
        int upright = 0;
        int down = 0;
        int x = game.getBoard().size;

        if (game.getBoard().get(index1, index2) >= 1) {

            ////////////////////when all child exist
           

            
                if (index1 + 1 < x) {
                    
                   
                   if (game.getBoard().get(index1 + 1, index2) == 0) {
                    if (right <= upright) {
                         System.out.println("ajao yr");
                        myboard[index1 + 1][index2] = 270; ////////////////// right
                        return myboard[index1 + 1][index2];
                    
                    }
                    }
                
            }
                
                
                
            
           
                if (index1 + 1 < x && index2 - 1 >= 0) {
                        if (game.getBoard().get(index1 + 1, index2 - 1) == 0) {
                    if (upright <= right) {


                        myboard[index1 + 1][index2 - 1] = 260; ////////////////upright
                        return myboard[index1 + 1][index2 - 1];
                    }
                }
            } if (index2 + 1 < x) {
                  if(game.getBoard().get(index1,index2+1)==0){
                myboard[index1][index2 + 1] = 250; ////////////////////////////down
                return myboard[index1][index2 + 1];
                  }
            }

        }


               
     /*   int randi=(int)(Math.random()*(game.getBoard().size-1)-1)+0;
                 int randj=(int)(Math.random()*(game.getBoard().size-1)-1)+0;
              
                 while(game.getBoard().get(randi,randj)!=0){
                 randi=(int)(Math.random()*(game.getBoard().size-1)-1)+0;
                 randj=(int)(Math.random()*(game.getBoard().size-1)-1)+0;
                 
               }
               
                 myboard[randi][randj]=280;
                 return myboard[randi][randj];

*/

     
     for(int i=index2 ;i<x;i++){
         if(i+1<x){
             if(game.getBoard().get(index1,i+1)<=0){
                                  
                 myboard[index1][i+1]=250;
                 return myboard[index1][i+1];
                 
             }
         }
     
     }
      for(int i=index2 ;i>=0;i--){
        
             if(game.getBoard().get(index1,i-1)<=0){
                                  
                 myboard[index1][i-1]=250;
                 return myboard[index1][i-1];
                 
             }
         }
     
     
             return 0;
    }
    int evaluationfuncforleftTree(Runner game, int[][] myboard, int edge1[], int index1, int index2) {

        int left = 0;
        int downleft = 0;
        int up = 0;
        int x = game.getBoard().size;



        if (game.getBoard().get(index1, index2) >= 1) {



            ////////////////////when all child exist
            if (index1 - 1 >= 0) {
                left = game.getBoard().get(index1 - 1, index2) - edge1[index2];

            }
            if (index1 - 1 >= 0 && index2 + 1 < x) {
                downleft = game.getBoard().get(index1 - 1, index2 + 1) - edge1[index2];
            }
           System.out.println(left+"last"+downleft);
                if (index1 - 1 >= 0) {
                  
                  if (game.getBoard().get(index1 - 1, index2) == 0 ) {
                    if (left <= downleft) {

                        myboard[index1 - 1][index2] = 270; ////////////////// left
                        return myboard[index1 - 1][index2];
                    }
                }
            }

            

                if (index1 - 1 >= 0 && index2 + 1 < x){
                    if (game.getBoard().get(index1 - 1, index2 + 1) == 0) {
                    if (downleft <= left) {


                        myboard[index1 - 1][index2 + 1] = 260; ////////////////down left
                        return myboard[index1 - 1][index2 + 1];
                    }
            }}  if (index2 - 1 >= 0) {
                 if(game.getBoard().get(index1,index2-1)==0){
                myboard[index1][index2 - 1] = 250; ////////////////////////////up
                return myboard[index1][index2 - 1];
                 }
            }

        }


     /* int randi=(int)(Math.random()*(game.getBoard().size-1)-1)+0;
                 int randj=(int)(Math.random()*(game.getBoard().size-1)-1)+0;
                 while(game.getBoard().get(randi,randj)!=0){
                 randi=(int)(Math.random()*(game.getBoard().size-1)-1)+0;
                 randj=(int)(Math.random()*(game.getBoard().size-1)-1)+0;
                 }
                 myboard[randi][randj]=280;
                 return myboard[randi][randj];*/
      
      System.out.println(index1+"bbbbbbbbbbbbbbbbbbbbbbb"+index2);
     
     for(int i=index2 ;i<x;i++){
         if(index2+1<x){
             if(game.getBoard().get(index1,i+1)<=0){
                                  
                 myboard[index1][i+1]=250;
                 return myboard[index1][i+1];
                 
             }
         }
     
     }
      
       for(int i=index2 ;i>=0;i--){
        
             if(game.getBoard().get(index1,i-1)<=0){
                                  
                 myboard[index1][i-1]=250;
                 return myboard[index1][i-1];
                 
             }
         }
     
     
             return 0;
    }

    int max(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;

    }
    int min(int a, int b) {
        if (a < b) {
            return a;
        }
        return b;

    }


    int[] move(Runner game, int[][] myboard,int index1,int index2,int index3,int index4,int score) {
        int[] result = minimax(2, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, game, myboard, index1, index2, index3, index4,score); //for depth 2
        // depth, max-turn, alpha, beta
        return new int[] {
            result[1], result[2]
        }; // row, col
    }

    //Runner game, int myboard[][],int index1,int index2,int index3,int index4
    int[] minimax(int depth, int player, int alpha, int beta, Runner game, int[][] myboard,int index1,int index2,int index3,int index4,int val) {
        // Generate possible next moves in a list of int[2] of {row, col}.
    
        List < int[] > nextMoves = generateMoves(game, myboard,index1,index2,val);

        int score;
        int bestRow = -1;
        int bestCol = -1;
    
        if ( depth == 0) {
            // Gameover or depth reached, evaluate score
             score =evaluationfuncforTree(game,myboard,index1,index2,index3,index4);
            return new int[] {score,bestRow,bestCol
            };
        } else {
            for (int[] move: nextMoves) {
                // try this move for the current "player"
                myboard[move[0]][move[1]] = player;
                if (player == 0) { // mySeed (computer) is maximizing player
                    score = minimax(depth - 1, 1, alpha, beta, game, myboard,index1,index2,index3,index4,val)[0];
                    if (score > alpha) {
                        alpha = score;
                        bestRow = move[0];
                        bestCol = move[1];
                    }
                } else { // oppSeed is minimizing player
                    score = minimax(depth - 1, 0, alpha, beta, game, myboard,index1,index2,index3,index4,val)[0];
                    if (score < beta) {
                        beta = score;
                        bestRow = move[0];
                        bestCol = move[1];
                    }
                }
                // undo move
                myboard[move[0]][move[1]] = 0;
                // cut-off
                if (alpha >= beta) break;
            }
            return new int[] {
                (player == 0) ? alpha: beta, bestRow, bestCol
            };
        }
    }


    int[] maketree(Runner game, int indexX, int indexY, int index3, int index4) {
        int x = game.getBoard().size;
         
 
        int myboard[][] = new int[game.getBoard().size][game.getBoard().size];

        int score=evaluationfuncforTree(game, myboard, indexX, indexY, index3, index4);
      
        int arr[] = new int[2];
        arr = move(game, myboard,indexX,indexY,index3,index4,score);

        
     
        return arr;

       





    }
}