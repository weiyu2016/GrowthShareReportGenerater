package jfilechooserdemo.resources;

import java.io.File;
import javax.swing.filechooser.FileFilter;

class MyCustomFilter extends FileFilter
{
  public boolean accept(File file)
  {
    return (file.isDirectory()) || (file.getAbsolutePath().endsWith(".txt"));
  }

  public String getDescription()
  {
    return "Text documents (*.txt)";
  }
}