
package com.nana.robot.chatterbean.aiml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.nana.robot.chatterbean.text.Sentence;

import junit.framework.TestCase;

public class CategoryTest extends TestCase
{
  /*
  Methods
  */
  
  public void testGetMatchPath()
  {
    Category category = new Category(
      new Pattern(" YES "),
      new That(" DO YOU LIKE CHEESE "),
      new Topic(" LIKE "),
      new Template("Good for you.", new Think(new Set("topic", "*")))
    );
    
    String[] expected = {"YES", "<THAT>", "DO", "YOU", "LIKE", "CHEESE", "<TOPIC>", "LIKE"};
    String[] actual = category.getMatchPath();
    assertEquals(Arrays.toString(actual), expected.length, actual.length);
    for (int i = 0, n = expected.length; i < n; i++)
      assertEquals(expected[i], actual[i]);
  }
}
