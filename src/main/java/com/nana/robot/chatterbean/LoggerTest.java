
package com.nana.robot.chatterbean;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;

import com.nana.robot.chatterbean.util.Sequence;

import junit.framework.TestCase;

public class LoggerTest extends TestCase
{
  /*
  Attributes
  */

  private File file;
  private Logger logger;

  /* 
  Events
  */

  protected void setUp() throws Exception
  {
    Sequence sequence = new Sequence("Logs/sequence.txt");
    file = new File("Logs/log" + sequence.getNext() + ".txt");
    logger = new Logger(new FileWriter(file));
  }

  protected void tearDown()
  {
    file = null;
    logger = null;
  }

  /*
  Methods
  */

  public void testAddEntry() throws IOException
  {
    logger.append("First request", "First response");
    logger.append("Second request", "Second response");
    logger.append("Third request", "Third response");
    
    BufferedReader reader = new BufferedReader(new FileReader(file));
    
    assertTrue(reader.readLine().matches("\\[[0-9]{2}/[0-9]{2}/[0-9]{4} [0-9]{2}:[0-9]{2}:[0-9]{2}\\]" +
                                         "\\[First request\\]\\[First response\\]"));

    assertTrue(reader.readLine().matches("\\[[0-9]{2}/[0-9]{2}/[0-9]{4} [0-9]{2}:[0-9]{2}:[0-9]{2}\\]" +
                                         "\\[Second request\\]\\[Second response\\]"));

    assertTrue(reader.readLine().matches("\\[[0-9]{2}/[0-9]{2}/[0-9]{4} [0-9]{2}:[0-9]{2}:[0-9]{2}\\]" +
                                         "\\[Third request\\]\\[Third response\\]"));
  }
}
