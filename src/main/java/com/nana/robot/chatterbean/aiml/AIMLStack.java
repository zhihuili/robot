
package com.nana.robot.chatterbean.aiml;

import java.util.LinkedList;
import java.util.List;

public class AIMLStack
{
  /*
  Attributes
  */
  
  private final List<Object> stack = new LinkedList<Object>();
  
  /*
  Methods
  */
  
  public Object peek()
  {
    return stack.get(0);
  }
  
  public Object pop()
  {
    return (stack.size() > 0 ? stack.remove(0) : null);
  }
  
  public void push(Object element)
  {
    stack.add(0, element);
  }
}
