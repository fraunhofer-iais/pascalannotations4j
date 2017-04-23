package com.wulff.pascalannotations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;

public class PascalAnnotations {

  static ImageAnnotations read(String filename) throws IOException {
    File inputfile = new File(filename);
    if (inputfile.exists() && inputfile.canRead()) {
      BufferedReader reader = new BufferedReader(new FileReader(inputfile));
      return parse(reader);
    } else {
      throw new IOException("Input file " + filename + " not existing or not readable.");
    }
  }

  static ImageAnnotations parse(String raw) throws IOException {
    BufferedReader reader = new BufferedReader(new StringReader(raw));
    return parse(reader);
  }

  static ImageAnnotations parse(BufferedReader reader) throws IOException {
    ImageAnnotations imageAnnotation = new ImageAnnotations();
    String line;
    int numObjects = 0;
    while ((line = reader.readLine()) != null) {
      line = line.trim();

      // COMMENT
      if (line.startsWith("#")) {
        continue;

        // Database
      } else if (line.startsWith(Constant.LINESTART_DATABASE)) {
        Matcher m = Constant.REGEXP_STRING.matcher(line);
        if (m.find()) {
          imageAnnotation.DBname = m.group(1);
        } else {
          throw new IllegalStateException("Failed to parse database name.");
        }

        // image file name  
      } else if (line.startsWith(Constant.LINESTART_FILENAME)) {
        Matcher m = Constant.REGEXP_STRING.matcher(line);
        if (m.find()) {
          imageAnnotation.filename = m.group(1);
        } else {
          throw new IllegalStateException("Failed to parse image file name.");
        }

        // image dimensions
      } else if (line.startsWith(Constant.LINESTART_IMAGESIZE)) {
        Matcher m = Constant.REGEXP_IMAGEDIM.matcher(line);
        if (m.find()) {
          imageAnnotation.width = Integer.parseInt(m.group(1));   
          imageAnnotation.height = Integer.parseInt(m.group(2));
          imageAnnotation.channels = Integer.parseInt(m.group(3));
        } else {
          throw new IllegalStateException("Failed to parse image dimensions.");
        }

        // number of objects in image
      } else if (line.startsWith(Constant.LINESTART_OBJECTS)) {
        Matcher m = Constant.REGEXP_NUMOBJS.matcher(line);              
        if (m.find()) {
          numObjects = Integer.parseInt(m.group(1));
          for (int i = 0; i < numObjects; i++) {
            imageAnnotation.objects.add(new ImageObject());
          }
        } else {
          throw new IllegalStateException("Failed to parse number of objects.");
        }

        // bounding box of an object
      } else if (line.startsWith(Constant.LINESTART_OBJ_BBOX)) {
        int i = parseObjectIndex(line);
        Matcher m = Constant.REGEXP_OBJ_BBOX.matcher(line);
        if (m.find()) {
          ImageObject obj = imageAnnotation.getObject(i);
          obj.setUpperLeft(new Point(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))));
          obj.setUpperLeft(new Point(Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4))));
        } else {
          throw new IllegalStateException("Failed to parse bounding box of object " + i);
        }

        // center point of an object
      } else if (line.startsWith(Constant.LINESTART_OBJ_CENTER)) {
        int i = parseObjectIndex(line);
        Matcher m = Constant.REGEXP_OBJ_CENTER.matcher(line);
        if (m.find()) {
          ImageObject obj = imageAnnotation.getObject(i);
          obj.setCenter(new Point(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))));
        } else {
          throw new IllegalStateException("Failed to parse center of object " + i);
        }

      } else if (line.startsWith(Constant.LINESTART_OBJ_LABEL)) {
        int i = parseObjectIndex(line);
        Matcher m = Constant.REGEXP_OBJ_LABELS.matcher(line);
        if (m.find()) {
          ImageObject obj = imageAnnotation.getObject(i);
          obj.setOriginalLabel(m.group(1));
          obj.setOriginalLabel(m.group(2));
        } else {
          throw new IllegalStateException("Failed to parse labels of object " + i);
        }
      }
    }
    return imageAnnotation;
  }

  private static int parseObjectIndex(String s) {
    Matcher m = Constant.REGEXP_OBJ_IDX.matcher(s);
    if (m.find()) {
      return Integer.parseInt(m.group(1));
    } else {
      throw new IllegalStateException("Failed to parse object index.");
    }
  }
}
