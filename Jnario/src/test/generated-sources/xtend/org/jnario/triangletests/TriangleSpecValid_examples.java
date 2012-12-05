package org.jnario.triangletests;

import java.util.List;
import org.jnario.lib.ExampleTableRow;

public class TriangleSpecValid_examples extends ExampleTableRow {
  public TriangleSpecValid_examples(final List<String> cellNames, final double side1, final double side2, final double side3, final String triangle_type) {
    super(cellNames);
    this.side1 = side1;
    this.side2 = side2;
    this.side3 = side3;
    this.triangle_type = triangle_type;
  }
  
  public double side1;
  
  public double getSide1() {
    return side1;
  }
  
  public double side2;
  
  public double getSide2() {
    return side2;
  }
  
  public double side3;
  
  public double getSide3() {
    return side3;
  }
  
  public String triangle_type;
  
  public String getTriangle_type() {
    return triangle_type;
  }
  
  public List<String> getCells() {
    return java.util.Arrays.asList(String.valueOf(side1) , String.valueOf(side2) , String.valueOf(side3) , String.valueOf(triangle_type));
  }
}
