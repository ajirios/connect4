


//--------------------------------------------------------------------------------------------------------------------------------------
// CLASS: ControllerFactory
//
// Author: AJIRI OSAUZO JEFFREY, 7682469
//
// REMARKS: The purpose of this class is to return a back-end class to the gameDisplay without knowing the identity of the class.
//
//--------------------------------------------------------------------------------------------------------------------------------------


public class ControllerFactory 
  
{
  
  //------------------------------------------------------------------------------------------------------------------------------------
  // makeController
  //
  // PURPOSE:    tell me what it does!
  // PARAMETERS:
  //     describe the purpose and expectations of parameters, 
  //     including whether the parameters are returning data
  //     (via alteration of the parameters)
  // Returns: ConnectController
  //------------------------------------------------------------------------------------------------------------------------------------
  public static ConnectController makeController(GameDisplay gameDisplay)
    
  {
    
    return new GameLogic(gameDisplay);
    
  }
  
}