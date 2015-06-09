
package com.nana.robot.chatterbean;

import com.nana.robot.chatterbean.aiml.Category;
import com.nana.robot.chatterbean.aiml.Pattern;
import com.nana.robot.chatterbean.aiml.Template;
import com.nana.robot.chatterbean.aiml.That;
import com.nana.robot.chatterbean.text.Sentence;

import static com.nana.robot.chatterbean.text.Sentence.ASTERISK;
import junit.framework.TestCase;

public class GraphmasterTest extends TestCase
{
  /*
  Attributes
  */

  private final GraphmasterMother mother  = new GraphmasterMother();

  private Graphmaster root;
  
  /*
  Events
  */

  protected void setUp()
  {
    root = mother.newInstance();
  }

  protected void tearDown()
  {
    root = null;
  }
  
  /*
  Methods
  */

  public void testMatch()
  {
    Category category;
    Match match;
    
    match = new Match(new Sentence(" Say goodbye again. ", new Integer[] {0, 4, 12, 19}, " SAY GOODBYE AGAIN "));
    category = root.match(match);
    assertNotNull(category);
    assertEquals("What, again? \"goodbye\".", category.process(match));

    match = new Match(new Sentence(" Say it now. ", new Integer[] {0, 4, 7, 12}, " SAY IT NOW "));
    category = root.match(match);
    assertNotNull(category);
    assertEquals("Whatever you want...", category.process(match));

    match = new Match(new Sentence(" Say goodbye. ", new Integer[] {0, 4, 13}, " SAY GOODBYE "));
    category = root.match(match);
    assertNotNull(category);
    assertEquals("goodbye!", category.process(match));
    
    match = new Match(
              new Sentence(" Do you see the fire in my eyes? ",
                           new Integer[] {0, 3, 7, 11, 15, 20, 23, 26, 32},
                           " DO YOU SEE THE FIRE IN MY EYES "));

    category = root.match(match);
    assertNotNull(category);
    assertEquals("Yes, I see the fire in your eyes.", category.process(match));
  }
  
  public void testThatMatch()
  {
    Sentence input = new Sentence(" Do you like it? ", new Integer[] {0, 3, 12, 16}, " DO YOU LIKE IT ");
    Sentence that = new Sentence(" CHEESE ", new Integer[] {0, 7}, " CHEESE ");
    Match match = new Match(null, input, that, ASTERISK);

    Category expected = new Category(new Pattern("DO YOU LIKE IT"), new That("CHEESE"), new Template("Yes."));
    root.append(expected);
    Category actual = root.match(match);

    assertEquals(expected, actual);
  }
}
