package org.jnario.triangletests;

import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.hamcrest.StringDescription;
import org.jnario.lib.ExampleTable;
import org.jnario.lib.ExampleTableIterators;
import org.jnario.lib.Should;
import org.jnario.runner.ExampleGroupRunner;
import org.jnario.runner.Extension;
import org.jnario.runner.Named;
import org.jnario.runner.Order;
import org.jnario.triangletests.TrianglePage;
import org.jnario.triangletests.TriangleSpecInvalid_examples;
import org.jnario.triangletests.TriangleSpecValid_examples;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

@SuppressWarnings("all")
@RunWith(ExampleGroupRunner.class)
@Named("Triangle")
public class TriangleSpec {
  final static FirefoxDriver driver = new Function0<FirefoxDriver>() {
    public FirefoxDriver apply() {
      FirefoxDriver _firefoxDriver = new FirefoxDriver();
      return _firefoxDriver;
    }
  }.apply();
  
  @Extension
  public static TrianglePage trianglePage;
  
  @BeforeClass
  public static void before() throws Exception {
    TriangleSpec.driver.get("http://practice.agilistry.com/triangle");
    TrianglePage _initElements = PageFactory.<TrianglePage>initElements(TriangleSpec.driver, TrianglePage.class);
    TriangleSpec.trianglePage = _initElements;
  }
  
  @Before
  public void _initTriangleSpecValid_examples() {
    valid_examples = ExampleTable.create("valid_examples", 
      java.util.Arrays.asList("side1", "side2", "side3", "triangle_type"), 
      new TriangleSpecValid_examples(  java.util.Arrays.asList("1", "1", "1", "\"Equilateral\""), 1, 1, 1, "Equilateral"),
      new TriangleSpecValid_examples(  java.util.Arrays.asList("3", "4", "5", "\"Right\""), 3, 4, 5, "Right"),
      new TriangleSpecValid_examples(  java.util.Arrays.asList("4", "5", "6", "\"Scalene\""), 4, 5, 6, "Scalene"),
      new TriangleSpecValid_examples(  java.util.Arrays.asList("2", "5", "6", "\"Scalene\""), 2, 5, 6, "Scalene"),
      new TriangleSpecValid_examples(  java.util.Arrays.asList("4.2", "5.6", "6.1", "\"Scalene\""), 4.2, 5.6, 6.1, "Scalene")
    );
  }
  
  protected ExampleTable<TriangleSpecValid_examples> valid_examples;
  
  @Test
  @Named("valid_examples.forEach[ enterSideLengths[side1.toString, side2.toString, side3.toString] triangleType => triangle_type coordinatesAreValid => true ]")
  @Order(99)
  public void validExamplesForEachEnterSideLengthsSide1ToStringSide2ToStringSide3ToStringTriangleTypeTriangleTypeCoordinatesAreValidTrue() throws Exception {
    final Procedure1<TriangleSpecValid_examples> _function = new Procedure1<TriangleSpecValid_examples>() {
        public void apply(final TriangleSpecValid_examples it) {
          try {
            String _string = Double.valueOf(it.side1).toString();
            String _string_1 = Double.valueOf(it.side2).toString();
            String _string_2 = Double.valueOf(it.side3).toString();
            TriangleSpec.trianglePage.enterSideLengths(_string, _string_1, _string_2);
            String _triangleType = TriangleSpec.trianglePage.triangleType();
            boolean _doubleArrow = Should.operator_doubleArrow(_triangleType, it.triangle_type);
            Assert.assertTrue("\nExpected triangleType => triangle_type but"
             + "\n     triangleType is " + new StringDescription().appendValue(_triangleType).toString()
             + "\n     triangle_type is " + new StringDescription().appendValue(it.triangle_type).toString() + "\n", _doubleArrow);
            
            boolean _coordinatesAreValid = TriangleSpec.trianglePage.coordinatesAreValid();
            boolean _doubleArrow_1 = Should.operator_doubleArrow(Boolean.valueOf(_coordinatesAreValid), Boolean.valueOf(true));
            Assert.assertTrue("\nExpected coordinatesAreValid => true but"
             + "\n     coordinatesAreValid is " + new StringDescription().appendValue(Boolean.valueOf(_coordinatesAreValid)).toString() + "\n", _doubleArrow_1);
            
          } catch (Exception _e) {
            throw Exceptions.sneakyThrow(_e);
          }
        }
      };
    ExampleTableIterators.<TriangleSpecValid_examples>forEach(this.valid_examples, _function);
  }
  
  @Before
  public void _initTriangleSpecInvalid_examples() {
    invalid_examples = ExampleTable.create("invalid_examples", 
      java.util.Arrays.asList("side1", "side2", "side3", "triangle_type"), 
      new TriangleSpecInvalid_examples(  java.util.Arrays.asList("0", "4", "5", "\"Invalid\""), 0, 4, 5, "Invalid"),
      new TriangleSpecInvalid_examples(  java.util.Arrays.asList("1", "4", "6", "\"Invalid\""), 1, 4, 6, "Invalid")
    );
  }
  
  protected ExampleTable<TriangleSpecInvalid_examples> invalid_examples;
  
  @Test
  @Named("invalid_examples.forEach[ enterSideLengths[side1.toString, side2.toString, side3.toString] triangleType => triangle_type ]")
  @Order(99)
  public void invalidExamplesForEachEnterSideLengthsSide1ToStringSide2ToStringSide3ToStringTriangleTypeTriangleType() throws Exception {
    final Procedure1<TriangleSpecInvalid_examples> _function = new Procedure1<TriangleSpecInvalid_examples>() {
        public void apply(final TriangleSpecInvalid_examples it) {
          try {
            String _string = Integer.valueOf(it.side1).toString();
            String _string_1 = Integer.valueOf(it.side2).toString();
            String _string_2 = Integer.valueOf(it.side3).toString();
            TriangleSpec.trianglePage.enterSideLengths(_string, _string_1, _string_2);
            String _triangleType = TriangleSpec.trianglePage.triangleType();
            boolean _doubleArrow = Should.operator_doubleArrow(_triangleType, it.triangle_type);
            Assert.assertTrue("\nExpected triangleType => triangle_type but"
             + "\n     triangleType is " + new StringDescription().appendValue(_triangleType).toString()
             + "\n     triangle_type is " + new StringDescription().appendValue(it.triangle_type).toString() + "\n", _doubleArrow);
            
          } catch (Exception _e) {
            throw Exceptions.sneakyThrow(_e);
          }
        }
      };
    ExampleTableIterators.<TriangleSpecInvalid_examples>forEach(this.invalid_examples, _function);
  }
  
  @AfterClass
  public static void after() throws Exception {
    TriangleSpec.driver.close();
    TriangleSpec.driver.quit();
  }
}
