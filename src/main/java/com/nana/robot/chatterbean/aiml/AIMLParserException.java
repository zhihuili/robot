
package com.nana.robot.chatterbean.aiml;

public class AIMLParserException extends Exception
{
  /*
  Attribute Section
  */
  
  private static final long serialVersionUID = 7L;

  /*
  Constructor Section
  */
  
  public AIMLParserException(Exception e)
  {
    super(e);
  }
  
  public AIMLParserException(String message)
  {
    super(message);
  }
  
  public AIMLParserException(String message, Throwable cause)
  {
    super(message, cause);
  }
}
