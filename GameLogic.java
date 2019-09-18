


//--------------------------------------------------------------------------------------------------------------------------------------
// CLASS: GameLogic
//
// Author: AJIRI OSAUZO JEFFREY, 7682469
//
// REMARKS: The purpose of this class is to control the logical steps of the entire Connect game with regards to the Connect board.
//
//--------------------------------------------------------------------------------------------------------------------------------------

public class GameLogic implements ConnectController
  
{
  //declare class variables
  private final int WIDTH = 7;
  private final int HEIGHT = 6;
  private Status[][] board;
  private final int WIN = 4;
  private GameDisplay gameDisplay;
  private ConnectPlayer ai;
  
  //------------------------------------------------------------------------------------------------------------------------------------
  // GameLogic
  //
  // PURPOSE:    this is a constructor for ConnectController
  // PARAMETERS:
  //     GameDisplay gameDisplay
  //
  // Returns: GameLogic
  //------------------------------------------------------------------------------------------------------------------------------------
  public GameLogic(GameDisplay gameDisplay)
    
  {
    //instantiate gameDisplay and the board boundary conditions
    this.gameDisplay = gameDisplay;
    board = new Status[HEIGHT][WIDTH];
    
    //for each row
    for (int row = 0; row < HEIGHT; row++)
      
    {
      //for each column
      for (int column = 0; column < WIDTH; column++)
        
      {
        //set the current element status as neither
        board[row][column] = Status.NEITHER;
        
      }
      
    }
    
    
    
  }
  
  //------------------------------------------------------------------------------------------------------------------------------------
  // startGame
  //
  // PURPOSE:    scan ai difficulty level from user and set this class' ai to what is requested.
  // PARAMETERS:
  //     
  // Returns: void
  //------------------------------------------------------------------------------------------------------------------------------------
  private void startGame()
    
  {
    //declare the game difficulty
    int gameDifficulty;
    
    //initialize the game difficulty to zero.
    gameDifficulty = 0;
    
    //prompt the user for difficulty
    gameDifficulty = gameDisplay.promptForOpponentDifficulty(2);
    
    //if the difficulty is 1
    if (gameDifficulty == 1)
      
    {
      //set the ai as basic
      ai = new BasicConnectPlayer();
      
    }
    
    //else if the difficulty is 2
    else if (gameDifficulty == 2)
      
    {
      //set the ai as intermediate
      ai = new IntermediateConnectPlayer();
      
    }
    
    else //else print an error message, not likely to happen.
      
    {
      //error message
      System.out.println("An automatic player does not exist for the difficulty level you entered!");
      
    }
    
  }
  
  //------------------------------------------------------------------------------------------------------------------------------------
  // checkGameWon
  //
  // PURPOSE:    get a Status that wins or null if there isn't one.
  // PARAMETERS:
  //     
  // Returns: Status gameWon - get a Status that wins or null if there isn't one.
  //------------------------------------------------------------------------------------------------------------------------------------
  private Status checkGameWon()
    
  {
    //declare variables
    Status gameWon;
    Status horizontalWin;
    Status verticalWin;
    Status leftDiagonalWin;
    Status rightDiagonalWin;
    
    //initialize the variables
    gameWon = null;
    horizontalWin = checkHorizontalWin();
    verticalWin = checkVerticalWin();
    leftDiagonalWin = checkLeftDiagonalWin();
    rightDiagonalWin = checkRightDiagonalWin();
    
    //if there is a horizontal win
    if (horizontalWin != null)
      
    {
      //the winning status is its status
      gameWon = horizontalWin;
      
    }
    
    //if there is a vertical win
    if (verticalWin != null)
      
    {
      //the winning status is its status
      gameWon = verticalWin;
      
    }
    
    //if there is a diagonal win
    if (leftDiagonalWin != null)
      
    {
      //the winning status is its status
      gameWon = leftDiagonalWin;
      
    }
    
    //if there is a right diagonal win
    if (rightDiagonalWin != null)
      
    {
      //the winning status is its status
      gameWon = rightDiagonalWin;
      
    }
    
    //if the board is full and no game won
    if ((isBoardFull() == true) && (gameWon == null))
      
    {
      
      //end the game with no winner
      gameDisplay.gameOver(Status.NEITHER);
      
    }
    
    //return the game winner
    return gameWon;
    
  }
  
  //------------------------------------------------------------------------------------------------------------------------------------
  // checkHorizontalWin
  //
  // PURPOSE:    check if there are any 4 pieces in a row.
  // PARAMETERS:
  //     
  // Returns: Status horizontalWin - returns the winning status if there is a win or null if not.
  //------------------------------------------------------------------------------------------------------------------------------------
  private Status checkHorizontalWin()
    
  {
    //declare variables
    Status horizontalWin;
    int oneCount;
    int twoCount;
    
    //initialize variables
    horizontalWin = null;
    oneCount = 0;
    twoCount = 0;
    
    //for each row
    for (int row = 0; row < HEIGHT; row++)
      
    {
      //reinitialize counters to zero
      oneCount = 0;
      twoCount = 0;
      
      //for each column in the row
      for (int column = 0; column < WIDTH; column++)
        
      {
        //if the current element status is one
        if (board[row][column] == Status.ONE)
          
        {
          //increment one count and set two count to zero
          oneCount++;
          twoCount = 0;
          
          //if there are 4 ones in a row
          if ((oneCount == WIN) && (horizontalWin != Status.ONE))
            
          {
            //the winning status is one
            horizontalWin = Status.ONE;
            
          }
          
          //else if there are 4 ones and 4 twos
          else if ((oneCount == WIN) && (horizontalWin == Status.ONE))
            
          {
            
            //the winning status is neither
            horizontalWin = Status.NEITHER;
            
          }
          
        }
        
        else //else if neither or two
          
        {
          //restart one counter
          oneCount = 0;
          
        }
        
        //if the current element status is two
        if (board[row][column] == Status.TWO)
          
        {
          //increment two count and set one count to zero
          twoCount++;
          oneCount = 0;
          
          //if there are 4 twos in a row
          if ((twoCount == WIN) && (horizontalWin != Status.ONE))
            
          {
            //the winning status is two
            horizontalWin = Status.TWO;
            
          }
          
          //else if there are 4 twos and 4 ones
          else if ((twoCount == WIN) && (horizontalWin == Status.ONE))
            
          {
            //the winning status is neither
            horizontalWin = Status.NEITHER;
            
          }
          
        }
        
        else //if either neither or one
          
        {
          //restart two counter
          twoCount = 0;
          
        }
        
      }
      
    }
    
    //return the winning status
    return horizontalWin;
    
  }
  
  //------------------------------------------------------------------------------------------------------------------------------------
  // checkVerticalWin
  //
  // PURPOSE:    check if there are any 4 pieces in a column.
  // PARAMETERS:
  //     
  // Returns: Status verticalWin - returns the winning status if there is a win or null if not.
  //------------------------------------------------------------------------------------------------------------------------------------
  private Status checkVerticalWin()
    
  {
    //declare variables
    Status verticalWin;
    int oneCount;
    int twoCount;
    
    //initialize variables
    verticalWin = null;
    oneCount = 0;
    twoCount = 0;
    
    //for each column
    for (int column = 0; column < WIDTH; column++)
      
    {
      //reinitialize counters to zero
      oneCount = 0;
      twoCount = 0;
      
      //for each row
      for (int row = 0; row < HEIGHT; row++)
        
      {
        //if the current element status is one
        if (board[row][column] == Status.ONE)
          
        {
          //increment one count and set two count to zero
          oneCount++;
          twoCount = 0;
          
          //if there are 4 ones
          if ((oneCount == WIN) && (verticalWin != Status.TWO))
            
          {
            //the winning status is one
            verticalWin = Status.ONE;
            
          }
          
          //else if there are 4 ones and 4 twos
          else if ((oneCount == WIN) && (verticalWin == Status.TWO))
            
          {
            //the winning status is neither
            verticalWin = Status.NEITHER;
            
          }
          
        }
        
        //if the current element status is two
        if (board[row][column] == Status.TWO)
          
        {
          //increment two count and set one count to zero
          twoCount++;
          oneCount = 0;
          
          //if there are 4 twos
          if ((twoCount == WIN) && (verticalWin != Status.ONE))
            
          {
            //the winning status is two
            verticalWin = Status.TWO;
            
          }
          
          //else if there are 4 twos and 4 ones
          else if ((twoCount == WIN) && (verticalWin == Status.ONE))
            
          {
            //the winning status is neither
            verticalWin = Status.NEITHER;
            
          }
          
        }
        
      }
      
    }
    
    //return the winning status
    return verticalWin;
    
  }
  
  //------------------------------------------------------------------------------------------------------------------------------------
  // checkLeftDiagonalWin
  //
  // PURPOSE:    check if there are any 4 pieces in a left diagonal.
  // PARAMETERS:
  //     
  // Returns: boolean diagonalWin - true or false, if there is a left diagonal quadruple or not.
  //------------------------------------------------------------------------------------------------------------------------------------
  private Status checkLeftDiagonalWin()
    
  {
    //declare variables
    Status diagonalWin;
    int diagonal;
    int row;
    int column;
    int oneCount;
    int twoCount;
    
    //initialize variables
    diagonalWin = null;
    row = 0;
    column = 3;
    diagonal = row - column;
    oneCount = 0;
    twoCount = 0;
    
    //while the diagonal value is less than three
    while (diagonal < 3)
      
    {
      //set the status counts back to zero.
      oneCount = 0;
      twoCount = 0;
      
      //setup the row and column initial values 
      if (diagonal < 0)
        
      {
        //initialize row and column variables
        row = 0;
        column = row - diagonal;
        
      }
      
      else if (diagonal >= 0)
        
      {
        //initialize row and column variables
        column = 0;
        row = diagonal;
        
      }
      
      //for each diagonal, add up the diagonal's row and column statuses to check if there are 4 in a row.
      while ((row < HEIGHT) && (column < WIDTH))
        
      {
        //if the current element status is one
        if (board[row][column] == Status.ONE)
          
        {
          //increment count and reinitialize the other as zero
          oneCount++;
          twoCount = 0;
          
          //if there are 4 ones 
          if ((oneCount == WIN) && (diagonalWin != Status.TWO))
            
          {
            //state that one has won the game
            diagonalWin = Status.ONE;
            
          }
          
          //else if there are 4 ones and 4 twos
          else if ((oneCount == WIN) && (diagonalWin == Status.TWO))
            
          {
            //state that neither has won the game
            diagonalWin = Status.NEITHER;
            
          }
          
        }
        
        //if the current element status is two
        if (board[row][column] == Status.TWO)
          
        {
          //increment two count and reinitialize one count to zero.
          twoCount++;
          oneCount = 0;
          
          //if there are 4 twos
          if ((twoCount == WIN) && (diagonalWin != Status.ONE))
            
          {
            //state that two has won the game
            diagonalWin = Status.TWO;
            
          }
          
          //else if there are 4 twos and 4 ones
          else if ((twoCount == WIN) && (diagonalWin == Status.ONE))
            
          {
            //state that neither has won the game
            diagonalWin = Status.NEITHER;
            
          }
          
        }
        
        //increment row and column counts to go to the next diagonal
        row++;
        column++;
        
      }
      
      //increment the diagonal
      diagonal++;
    
    }
    
    //return the variable stating if the diagonal has a win
    return diagonalWin;
    
  }
  
  //------------------------------------------------------------------------------------------------------------------------------------
  // checkRightDiagonalWin
  //
  // PURPOSE:    check if there are any 4 pieces in a right diagonal.
  // PARAMETERS:
  //     
  // Returns: boolean diagonalWin - true or false, if there is a right diagonal quadruple or not.
  //------------------------------------------------------------------------------------------------------------------------------------
  private Status checkRightDiagonalWin()
    
  {
    //declare variables
    Status diagonalWin;
    int diagonal;
    int row;
    int column;
    int oneCount;
    int twoCount;
    
    //initialize variables
    diagonalWin = null;
    row = 0;
    column = 3;
    diagonal = column + row;
    oneCount = 0;
    twoCount = 0;
    
    //while the diagonal value is less than three
    while (diagonal > 2)
      
    {
      //set the status counts back to zero.
      oneCount = 0;
      twoCount = 0;
      
      //setup the row and column initial values 
      if (diagonal < 7)
        
      {
        //initialize row and column variables
        row = 0;
        column = diagonal;
        
      }
      
      else if (diagonal >= 7)
        
      {
        //initialize row and column variables
        column = 6;
        row = diagonal - column;
        
      }
      
      //for each diagonal, add up the diagonal's row and column statuses to check if there are 4 in a row.
      while ((row < HEIGHT) && (column >= 0))
        
      {
        //if the current element status is one
        if (board[row][column] == Status.ONE)
          
        {
          //increment count and reinitialize the other as zero
          oneCount++;
          twoCount = 0;
          
          //if there are 4 ones 
          if ((oneCount == WIN) && (diagonalWin != Status.TWO))
            
          {
            //state that one has won the game
            diagonalWin = Status.ONE;
            
          }
          
          //else if there are 4 ones and 4 twos
          else if ((oneCount == WIN) && (diagonalWin == Status.TWO))
            
          {
            //state that neither has won the game
            diagonalWin = Status.NEITHER;
            
          }
          
        }
        
        else //if either neither or two
          
        {
          //restart one counter
          oneCount = 0;
          
        }
        
        //if the current element status is two
        if (board[row][column] == Status.TWO)
          
        {
          //increment two count and reinitialize one count to zero.
          twoCount++;
          oneCount = 0;
          
          //if there are 4 twos
          if ((twoCount == WIN) && (diagonalWin != Status.ONE))
            
          {
            //state that two has won the game
            diagonalWin = Status.TWO;
            
          }
          
          //else if there are 4 twos and 4 ones
          else if ((twoCount == WIN) && (diagonalWin == Status.ONE))
            
          {
            //state that neither has won the game
            diagonalWin = Status.NEITHER;
            
          }
          
        }
        
        else //if either neither or one
          
        {
          //restart two counter
          twoCount = 0;
          
        }
        
        //increment row and decrement column counts to go to the next diagonal
        row++;
        column--;
        
      }
      
      //increment the diagonal
      diagonal++;
    
    }
    
    //return the variable stating if the diagonal has a win
    return diagonalWin;
    
  }
  
  
 //------------------------------------------------------------------------------------------------------------------------------------
  // addPiece
  //
  // PURPOSE:    add a user's piece into the passed column.
  // PARAMETERS:
  //     int col - the column to be added to.
  //
  // Returns: boolean pieceAdded - tells you if the piece was successfully added.
  //------------------------------------------------------------------------------------------------------------------------------------
  @Override
 public boolean addPiece(int col)
   
 {
   //declare variables
   boolean loopEnd;
   boolean pieceAdded;
   boolean aiPieceAdded;
   Status gameWon;
   int count;
   int aiColumn;
   
   //initialize variables
   loopEnd = false;
   pieceAdded = false;
   aiPieceAdded = false;
   gameWon = null;
   aiColumn = -1;
   
   //if the top element in the column is one or two as status
   if ((board[0][col] == Status.ONE) || (board[0][col] == Status.TWO))
     
   {
     //pieceAdded is not true
     pieceAdded = false;
     
   }
   
   else //if the top element in the column is neither as status
     
   {
     //for each row, as long as its element is neither and the loop is not terminated
     for (count = 0; (count < HEIGHT - 1) && (board[count][col] == Status.NEITHER) && (loopEnd == false); count++)
       
     {
       
       //if the next elememt below it is not neither
       if (board[count + 1][col] != Status.NEITHER)
         
       {
         //stop and add the piece.
         board[count][col] = Status.ONE;
         gameDisplay.updateBoard(board);
         aiColumn = ai.makeMove(col);
         aiPieceAdded = addAiPiece(aiColumn);
         gameWon = checkGameWon();
         pieceAdded = true;
         
         //if someone wins, terminate the game and declare a winner
         if (gameWon != null)
           
         {
           
           gameDisplay.gameOver(gameWon);
           
         }
         
         //terminate the loop
         loopEnd = true;
         
       }
       
     }
     
     if (pieceAdded == false)
       
     {
       //if the piece has not still been added at the 5th row
       board[5][col] = Status.ONE;
       gameDisplay.updateBoard(board);
       aiColumn = ai.makeMove(col);
       aiPieceAdded = addAiPiece(aiColumn);
       gameWon = checkGameWon();
       pieceAdded = true;
       
       //if someone wins, terminate the game and declare a winner
       if (gameWon != null)
           
       {
         
         gameDisplay.gameOver(gameWon);
         
       }
       
       //terminate the loop
       loopEnd = true;
       
     }
     
   }
   
   //return variable stating if the piece was successfully added.
   return pieceAdded;
   
 }
  
  //------------------------------------------------------------------------------------------------------------------------------------
  // addAiPiece
  //
  // PURPOSE:    add a ConnectPlayer's piece into the passed column.
  // PARAMETERS:
  //     int col - the column to be added to.
  //
  // Returns: boolean pieceAdded - tells you if the piece was successfully added.
  //------------------------------------------------------------------------------------------------------------------------------------
  public boolean addAiPiece(int col)
   
 {
    //declare variables
    boolean loopEnd;
    boolean pieceAdded;
    Status gameWon;
    int count;
    int aiColumn;
   
    //initialize variables
   loopEnd = false;
   pieceAdded = false;
   gameWon = null;
   
   //if the top element in the column is one or two as status
   if ((board[0][col] == Status.ONE) || (board[0][col] == Status.TWO))
     
   {
     //pieceAdded is not true
     pieceAdded = false;
     
   }
   
   else //if the top element in the column is neither as status
     
   {
     //for each row, as long as its element is neither and the loop is not terminated
     for (count = 0; (count < HEIGHT - 1) && (board[count][col] == Status.NEITHER) && (loopEnd == false); count++)
       
     {
       
       //if the next elememt below it is not neither
       if (board[count + 1][col] != Status.NEITHER)
         
       {
         //stop and add the piece.
         board[count][col] = Status.TWO;
         gameDisplay.updateBoard(board);
         gameWon = checkGameWon();
         pieceAdded = true;
         
         //if someone wins, terminate the game and declare a winner
         if (gameWon != null)
           
         {
           
           gameDisplay.gameOver(gameWon);
           
         }
         
         //terminate the loop
         loopEnd = true;
         
       }
       
     }
     
     //if the piece has not still been added at the 5th row
     if (pieceAdded == false)
       
     {
       //add the piece at the bottom row.
       board[5][col] = Status.TWO;
       gameDisplay.updateBoard(board);
       gameWon = checkGameWon();
       pieceAdded = true;
       
       //if someone wins, end the game
       if (gameWon != null)
           
       {
         
         gameDisplay.gameOver(gameWon);
         
       }
       
       //end the loop
       loopEnd = true;
       
     }
     
   }
   
   //return variable stating if the piece was added
   return pieceAdded;
   
 }
 
 //------------------------------------------------------------------------------------------------------------------------------------
  // reset
  //
  // PURPOSE:    empty the Connect board, update the front-end, and start collecting input from the user.
  // PARAMETERS:
  //     
  // Returns: void
  //------------------------------------------------------------------------------------------------------------------------------------
 @Override
 public void reset()
   
 {
   //for each row
   for (int row = 0; row < HEIGHT; row++)
      
    {
      //for each column
      for (int column = 0; column < WIDTH; column++)
        
      {
        //set the current element status as neither
        board[row][column] = Status.NEITHER;
        
      }
      
    }
   
   //update the gui
   gameDisplay.updateBoard(board);
   
   //start the game input collection
   startGame();
   
 }
 
 //------------------------------------------------------------------------------------------------------------------------------------
  // isBoardFull
  //
  // PURPOSE:    check if this class' Connect board is full or not.
  // PARAMETERS:
  //     
  // Returns: boolean isFull - false or true
  //------------------------------------------------------------------------------------------------------------------------------------
 private boolean isBoardFull()
   
 {
   //declare variables
   boolean isFull;
   boolean isNeither;
   
   //initialize variables, the board is full
   isFull = true;
   isNeither = false;
   
   //for each row
   for (int row = 0; row < HEIGHT; row++)
      
    {
      //for each column
      for (int column = 0; column < WIDTH; column++)
        
      {
        //if the current status is neither
        if (board[row][column] == Status.NEITHER)
          
        {
          //one spot is open
          isNeither = true;
          
        }
        
      }
      
    }
   
   //if a spot is open
   if (isNeither == true)
     
   {
     //the board is not full
     isFull = false;
     
   }
   
   //return the result
   return isFull;
   
 }
 
 
  
}















