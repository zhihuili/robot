
package com.nana.robot.chatterbean;

import java.util.Arrays;

import com.nana.robot.chatterbean.text.Sentence;

import junit.framework.TestCase;

public class MatchTest extends TestCase
{
  /*
  Methods
  */
  
  public void testMatchPath()
  {
    Match match = new Match(new Sentence(" Say goodbye again. ", new Integer[] {0, 4, 12, 19}, " SAY GOODBYE AGAIN "));
    
    String[] expected = {"SAY", "GOODBYE", "AGAIN", "<THAT>", "*", "<TOPIC>", "*"};
    String[] actual = match.getMatchPath();
    assertTrue(Arrays.toString(expected) + ' ' + Arrays.toString(actual), Arrays.equals(expected, actual));
  }
}
