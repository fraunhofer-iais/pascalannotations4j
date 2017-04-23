package com.wulff.pascalannotations;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class ImageAnnotations {

  String filename = null;
  String DBname = "N/A";

  int width;
  int height;
  int channels;

  List<ImageObject> objects = new LinkedList<>();

  public ImageObject getObject(int index) {
    if (index < objects.size()) {
      return objects.get(index);
    } else {
      throw new NoSuchElementException("Requested object #" + index + " but only " + objects.size() + " known.");
    }
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public String getDBname() {
    return DBname;
  }

  public void setDBname(String DBname) {
    this.DBname = DBname;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    if (width > 0) {
      this.width = width;
    } else {
      throw new IllegalArgumentException("Can not set negativ value for field 'width'.");
    }
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    if (height > 0) {
      this.height = height;
    } else {
      throw new IllegalArgumentException("Can not set negativ value for field 'height'.");
    }
  }

  public int getChannels() {
    return channels;
  }

  public void setChannels(int channels) {
    if (channels > 0) {
      this.channels = channels;
    } else {
      throw new IllegalArgumentException("Can not set negativ value for field 'channels'.");
    }
  }
}
