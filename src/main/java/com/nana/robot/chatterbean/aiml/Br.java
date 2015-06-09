package com.nana.robot.chatterbean.aiml;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

import org.xml.sax.Attributes;

import com.nana.robot.chatterbean.AliceBot;
import com.nana.robot.chatterbean.Context;
import com.nana.robot.chatterbean.Graphmaster;
import com.nana.robot.chatterbean.Match;

public class Br extends TemplateElement
{
  /*
  Constructors
  */

  public Br(Attributes attributes)
  {
  }

  public Br(Object... children)
  {
    super(children);
  }

  /*
  Methods
  */

  public String process(Match match)
  {
    return "";
  }
}