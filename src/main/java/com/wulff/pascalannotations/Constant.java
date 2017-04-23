package com.wulff.pascalannotations;

import java.util.regex.Pattern;

public class Constant {

  static String LINESTART_FILENAME = "Image filename";                          
  static String LINESTART_IMAGESIZE = "Image size (X x Y x C)";                 
  static String LINESTART_DATABASE = "Database";                                
  static String LINESTART_OBJECTS = "Objects with ground truth";                
  static String LINESTART_OBJ_LABEL = "Original label for object";              
  static String LINESTART_OBJ_CENTER = "Center point on object";                
  static String LINESTART_OBJ_BBOX = "Bounding box for object";                 
  
  static Pattern REGEXP_STRING = Pattern.compile("\"(.+)\"");
  static Pattern REGEXP_IMAGEDIM = Pattern.compile("(\\d+) x (\\d+) x (\\d+)");
  static Pattern REGEXP_NUMOBJS = Pattern.compile(": (\\d+) \\{");
  static Pattern REGEXP_OBJ_IDX = Pattern.compile("object (\\d+)");
  static Pattern REGEXP_OBJ_BBOX = Pattern.compile("\\((\\d+), (\\d+)\\) - \\((\\d+), (\\d+)\\)");
  static Pattern REGEXP_OBJ_CENTER = Pattern.compile("\\((\\d+), (\\d+)\\)");
  static Pattern REGEXP_OBJ_LABELS = Pattern.compile("\"(.+)\" : \"(.+)\"");
}
