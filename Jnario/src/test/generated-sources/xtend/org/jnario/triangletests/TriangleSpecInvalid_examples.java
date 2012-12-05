package org.jnario.triangletests;

import java.util.List;
import org.jnario.lib.ExampleTableRow;

public class TriangleSpecInvalid_examples extends ExampleTableRow {
  public TriangleSpecInvalid_examples(final List<String> cellNames, final int side1, final int side2, final int side3, final String triangle_type) {
    super(cellNames);
    this.side1 = side1;
    this.side2 = side2;
    this.side3 = side3;
    this.triangle_type = triangle_type;
  }
  
  public int side1;
  
  public int getSide1() {
    return side1;
  }
  
  public int side2;
  
  public int getSide2() {
    return side2;
  }
  
  public int side3;
  
  public int getSide3() {
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
