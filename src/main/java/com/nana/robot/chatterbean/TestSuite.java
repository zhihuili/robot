
package com.nana.robot.chatterbean;

import junit.framework.Test;
import junit.swingui.TestRunner;

public class TestSuite extends junit.framework.TestSuite
{
  /*
  Attributes
  */
  
  /** Names of the test classes to include in the test. */
  private static String[] testNames;

  /*
  Methods
  */
  
  /**
  Adds all the known unit tests to the suite.
  
  @param suite The test suite to which the known unit tests must be added. 
  */
  private static void addAllTests(TestSuite suite)
  {
    suite.addTestSuite(com.nana.robot.chatterbean.AliceBotTest.class);
    suite.addTestSuite(com.nana.robot.chatterbean.GraphmasterTest.class);
    suite.addTestSuite(com.nana.robot.chatterbean.LoggerTest.class);
    suite.addTestSuite(com.nana.robot.chatterbean.MatchTest.class);
    suite.addTestSuite(com.nana.robot.chatterbean.aiml.AIMLHandlerTest.class);
    suite.addTestSuite(com.nana.robot.chatterbean.aiml.AIMLParserTest.class);
    suite.addTestSuite(com.nana.robot.chatterbean.aiml.CategoryTest.class);
    suite.addTestSuite(com.nana.robot.chatterbean.aiml.SystemTest.class);
    suite.addTestSuite(com.nana.robot.chatterbean.aiml.TemplateElementTest.class);
    suite.addTestSuite(com.nana.robot.chatterbean.parser.ContextParserTest.class);
    suite.addTestSuite(com.nana.robot.chatterbean.text.SentenceTest.class);
    suite.addTestSuite(com.nana.robot.chatterbean.text.SentenceSplitterTest.class);
    suite.addTestSuite(com.nana.robot.chatterbean.text.TransformationsTest.class);
    suite.addTestSuite(com.nana.robot.chatterbean.text.SubstitutionTest.class);
    suite.addTestSuite(com.nana.robot.chatterbean.text.TokenizerTest.class);
    suite.addTestSuite(com.nana.robot.chatterbean.util.SearcherTest.class);
    suite.addTestSuite(com.nana.robot.chatterbean.util.SequenceTest.class);
  }

  /**
  Main entry point.
  
  @param args The names of test classes that must be included in the test. If ommited, all known tests will be included. 
  */
  public static void main(String args[])
  {
    testNames = args;
    TestRunner.main(new String[] {"-noloading", "bitoflife.chatterbean.TestSuite"});
  }

  /**
  Returns a new test suite.
  
  @return A new test suite.
  */
  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    
    if (testNames == null || testNames.length == 0) /* If no argument was given during the command-line call... */
      addAllTests(suite); /* Add all known tests to the suite. */
    else try // Otherwise...
    {
      // Add only the given tests to the suite.
      for (String name : testNames) 
        suite.addTestSuite(Class.forName(name));
    }
    catch (ClassNotFoundException e)
    {
      throw new RuntimeException(e);
    }

    return suite;
  }
}
