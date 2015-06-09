
package com.nana.robot.chatterbean;

import com.nana.robot.chatterbean.Graphmaster;
import com.nana.robot.chatterbean.aiml.Category;
import com.nana.robot.chatterbean.aiml.Srai;
import com.nana.robot.chatterbean.aiml.Star;
import com.nana.robot.chatterbean.aiml.Text;

public class GraphmasterMother
{
  /*
  Methods
  */

  public Graphmaster newInstance()
  {
    Graphmaster root = new Graphmaster();

    root.append(new Category(" SAY _ AGAIN ", "What, again? \"", new Star(1), "\"."));
    root.append(new Category(" SAY IT NOW ", "Whatever you want..."));
    root.append(new Category(" SAY * ", new Star(1), "!"));
    root.append(new Category(" DO YOU SEE THE * IN MY EYES ", "Yes, I see the ", new Star(1), " in your eyes."));

    return root;
  }
}
