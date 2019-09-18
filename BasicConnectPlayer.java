
//--------------------------------------------------------------------------------------------------------------------------------------
// CLASS: BasicConnectPlayer
//
// Author: AJIRI OSAUZO JEFFREY, 7682469
//
// REMARKS: The purpose of this class is to implement a function that returns an automated Connect player move to a back-end class.
//
//--------------------------------------------------------------------------------------------------------------------------------------

import java.lang.Math;

public class BasicConnectPlayer implements ConnectPlayer
  
{
  //------------------------------------------------------------------------------------------------------------------------------------
  // makeMove
  //
  // PURPOSE:    tell me what it does!
  // PARAMETERS:
  //     describe the purpose and expectations of parameters, 
  //     including whether the parameters are returning data
  //     (via alteration of the parameters)
  // Returns: int column - the column the AI wants to add a piece to.
  //------------------------------------------------------------------------------------------------------------------------------------
  @Override
  public int makeMove(int lastCol)
    
  {
    //declare column variable
    int column;
    
    //initialize
    column = 0;
    
    //randomly generate a column
    column = (int) (Math.random() * 6.0);
    
    //return the column to the caller
    return column;
    
  }
  
  
}