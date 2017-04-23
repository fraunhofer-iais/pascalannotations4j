package com.wulff.pascalannotations;

public class ImageObject {

  private String originalLabel = "N/A";
  private String label = "N/A";

  private Point center;
  private Point upperLeft;
  private Point lowerRight;

  public String getOriginalLabel() {
    return originalLabel;
  }

  public void setOriginalLabel(String originalLabel) {
    this.originalLabel = originalLabel;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public Point getCenter() {
    return center;
  }

  public void setCenter(Point center) {
    this.center = center;
  }

  public Point getUpperLeft() {
    return upperLeft;
  }

  public void setUpperLeft(Point upperLeft) {
    this.upperLeft = upperLeft;
  }

  public Point getLowerRight() {
    return lowerRight;
  }

  public void setLowerRight(Point lowerRight) {
    this.lowerRight = lowerRight;
  }

}
