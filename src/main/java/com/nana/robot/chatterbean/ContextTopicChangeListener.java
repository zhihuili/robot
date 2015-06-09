
package com.nana.robot.chatterbean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.nana.robot.chatterbean.text.Sentence;
import com.nana.robot.chatterbean.text.Transformations;

import static com.nana.robot.chatterbean.text.Sentence.ASTERISK;

/**
Property change listener for the <code>predicate.topic</code> property. Updates the Context with the new Topic value.
*/
public class ContextTopicChangeListener extends ContextPropertyChangeListener
{
  /*
  Constructor Section
  */
  
  /**
  Default class constructor.
  */
  public ContextTopicChangeListener()
  {
    super("predicate.topic");
  }
  
  /*
  Method Section
  */

  // Fired when the predicate.topic property changes.  
  public void propertyChange(PropertyChangeEvent event)
  {
    Object oldTopic = event.getOldValue();
    Object newTopic = event.getNewValue();
    Context context = (Context) event.getSource();
    Transformations transformations = context.getTransformations();
    
    if (oldTopic == null ? newTopic == null : oldTopic.equals(newTopic))
      return;
    
    String input = newTopic.toString().trim();
    if ("".equals(input) || "*".equals(input))
      context.setTopic(ASTERISK);
    else
    {
      Sentence topic = new Sentence(input);
      transformations.normalization(topic);
      context.setTopic(topic);
    }
  }
}
