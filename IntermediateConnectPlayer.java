

//--------------------------------------------------------------------------------------------------------------------------------------
// CLASS: IntermediateConnectPlayer
//
// Author: AJIRI OSAUZO JEFFREY, 7682469
//
// REMARKS: The purpose of this class is to implement a function that returns a logical automated Connect player move to a back-end class.
//
//--------------------------------------------------------------------------------------------------------------------------------------


public class IntermediateConnectPlayer implements ConnectPlayer
  
{
  //declare class variables
  private int[] humanColumns = new int[21];
  private int[] aiColumns = new int[21];
  
  //------------------------------------------------------------------------------------------------------------------------------------
  // IntermediateConnectPlayer
  //
  // PURPOSE:    constructor
  // PARAMETERS:
  //     
  // Returns: IntermediateConnectPlayer
  //------------------------------------------------------------------------------------------------------------------------------------
  public IntermediateConnectPlayer()
    
  {
    int count;
    
    for (count = 0; count < humanColumns.length; count++)
      
    {
      
      humanColumns[count] = -1;
      
    }
    
    for (count = 0; count < aiColumns.length; count++)
      
    {
      
      aiColumns[count] = -1;
      
    }
    
  }
  
  //------------------------------------------------------------------------------------------------------------------------------------
  // makeMove
  //
  // PURPOSE:    tells the ConnectController which column to add a piece to.
  // PARAMETERS:
  //     
  // Returns: int column - 
  //------------------------------------------------------------------------------------------------------------------------------------
  @Override
  public int makeMove(int lastCol)
    
  {
    //declare column variable
    int column;
    
    //initialize
    column = solveValidColumn();
    
    insertToHumanColumns(lastCol);
    
    if (column == -1)
      
    {
      
      column = lastCol;
      
    }
    
    insertToAiColumns(column);
    
    //return column to the caller
    return column;
    
  }
  
  //------------------------------------------------------------------------------------------------------------------------------------
  // insertToHumanColumns
  //
  // PURPOSE:    inserts a column into an array of user columns.
  // PARAMETERS:
  //     
  // Returns: void
  //------------------------------------------------------------------------------------------------------------------------------------
  private void insertToHumanColumns(int col)
    
  {
    int count;
    
    for (count = 0; count < humanColumns.length; count++)
      
    {
      
      if (humanColumns[count] == -1)
        
      {
        
        humanColumns[count] = col;
        
      }
      
    }
    
    
    
  }
  
  //------------------------------------------------------------------------------------------------------------------------------------
  // insertToAiColumns
  //
  // PURPOSE:    inserts a column into an array of ai columns
  // PARAMETERS:
  //     
  // Returns: void
  //------------------------------------------------------------------------------------------------------------------------------------
  private void insertToAiColumns(int col)
    
  {
    int count;
    
    for (count = 0; count < humanColumns.length; count++)
      
    {
      
      if (aiColumns[count] == -1)
        
      {
        
        aiColumns[count] = col;
        
      }
      
    }
    
  }
  
  //------------------------------------------------------------------------------------------------------------------------------------
  // solveValidColumn
  //
  // PURPOSE:    calculate and return a valid column.
  // PARAMETERS:
  //     
  // Returns: int validColumn
  //------------------------------------------------------------------------------------------------------------------------------------
  private int solveValidColumn()
    
  {
    int validColumn;
    int validSame;
    int validAscending;
    int validDescending;
    
    validColumn = -1;
    validSame = checkValidSame();
    validAscending = checkValidAscending();
    validDescending = checkValidDescending();
    
    if (validDescending != -1)
      
    {
      
      validColumn = validDescending;
      
    }
    
    if (validAscending != -1)
      
    {
      
      validColumn = validAscending;
      
    }
    
    if (validSame != -1)
      
    {
      
      validColumn = validSame;
      
    }
    
    return validColumn;
    
  }
  
  //------------------------------------------------------------------------------------------------------------------------------------
  // checkValidSame
  //
  // PURPOSE:    checks the column number of three same values and returns it.
  // PARAMETERS:
  //     
  // Returns: int validSame
  //------------------------------------------------------------------------------------------------------------------------------------
  private int checkValidSame()
    
  {
    int validSame;
    int sameCount;
    int select;
    int count;
    
    validSame = -1;
    sameCount = 0;
    
    for (select = 0; (select < humanColumns.length) && (humanColumns[select] != -1); select++)
      
    {
      
      for (count = 0; (count < humanColumns.length) && (humanColumns[count] != -1); count++)
        
      {
        
        if (humanColumns[select] == humanColumns[count])
          
        {
          
          sameCount++;
          
          if (sameCount == 3)
        
          {
            
            validSame = humanColumns[count];
            
          }
          
        }
      
      }
      
    }
    
    return validSame;
    
  }
  
  //------------------------------------------------------------------------------------------------------------------------------------
  // checkValidAscending
  //
  // PURPOSE:    checks a valid column number of three ascending values and returns it.
  // PARAMETERS:
  //     
  // Returns: int validAscending
  //------------------------------------------------------------------------------------------------------------------------------------
  private int checkValidAscending()
    
  {
    int validAscending;
    int ascendingCount;
    int select;
    int count;
    boolean loopEnd;
    
    validAscending = -1;
    ascendingCount = 0;
    loopEnd = false;
    
    for (select = 0; (select < humanColumns.length) && (humanColumns[select] != -1) && (loopEnd == false); select++)
      
    {
      
      for (count = 0; (count < humanColumns.length) && (humanColumns[count] != -1); count++)
        
      {
        
        if (humanColumns[select] == humanColumns[count])
          
        {
          
          ascendingCount++;
          humanColumns[select]++;
          
          if (ascendingCount == 3)
        
          {
            
            validAscending = humanColumns[select];
            loopEnd = true;
            
          }
          
        }
      
      }
      
      
    }
    
    return validAscending;
    
  }
  
  //------------------------------------------------------------------------------------------------------------------------------------
  // checkValidDescending
  //
  // PURPOSE:    checks a valid column number of three descending values and returns it.
  // PARAMETERS:
  //     
  // Returns: int validDescending
  //------------------------------------------------------------------------------------------------------------------------------------
  private int checkValidDescending()
    
  {
    int validDescending;
    int descendingCount;
    int select;
    int count;
    boolean loopEnd;
    
    validDescending = -1;
    descendingCount = 0;
    loopEnd = false;
    
    for (select = 0; (select < humanColumns.length) && (humanColumns[select] != -1) && (loopEnd == false); select++)
      
    {
      
      for (count = 0; (count < humanColumns.length) && (humanColumns[count] != -1); count++)
        
      {
        
        if (humanColumns[select] == humanColumns[count])
          
        {
          
          descendingCount++;
          humanColumns[select]--;
          
          if (descendingCount == 3)
        
          {
            
            validDescending = humanColumns[select] + 3;
            loopEnd = true;
            
          }
          
        }
      
      }
      
    }
    
    return validDescending;
    
  }
  
  
}