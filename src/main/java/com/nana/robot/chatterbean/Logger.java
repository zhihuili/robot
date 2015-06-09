
package com.nana.robot.chatterbean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
Log file generator.
*/
public class Logger
{
  /*
  Attributes
  */

  private static final DateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

  private PrintWriter writer;

  /*
  Constructor
  */

  /**
  Constructs a new Log on the given directory.
  */
  public Logger(Writer writer)
  {
    this.writer =  new PrintWriter(writer, true);
  }
  
  /*
  Methods
  */
  
  /**
  Adds an entry to this Log.
  */
  public void append(String request, String response) throws IOException
  {
    String now = date.format(new Date());
  
    writer.println("[" + now + "][" + request + "][" + response + "]");
  }
}
