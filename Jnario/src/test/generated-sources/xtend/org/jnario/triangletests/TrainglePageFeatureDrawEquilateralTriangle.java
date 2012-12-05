package org.jnario.triangletests;

import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.hamcrest.StringDescription;
import org.jnario.lib.JnarioIterableExtensions;
import org.jnario.lib.Should;
import org.jnario.lib.StepArguments;
import org.jnario.runner.FeatureRunner;
import org.jnario.runner.Named;
import org.jnario.runner.Order;
import org.jnario.triangletests.TriangleTestsRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(FeatureRunner.class)
@Named("Scenario: Draw equilateral triangle")
@SuppressWarnings("all")
public class TrainglePageFeatureDrawEquilateralTriangle {
  @Test
  @Order(0)
  @Named("When  I enter \"1\", \"1\", \"1\" as side lengths")
  public void whenIEnter111AsSideLengths() {
    try {
        StepArguments _stepArguments = new StepArguments("1", "1", "1");
        final StepArguments args = _stepArguments;
        String _first = JnarioIterableExtensions.<String>first(args);
        String _second = JnarioIterableExtensions.<String>second(args);
        String _third = JnarioIterableExtensions.<String>third(args);
        this.context.trianglePage.enterSideLengths(_first, _second, _third);
    } catch (Exception _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  @Order(1)
  @Named("Then triangle displays \"Equilateral\" as the triangle type")
  public void thenTriangleDisplaysEquilateralAsTheTriangleType() {
      StepArguments _stepArguments = new StepArguments("Equilateral");
      final StepArguments args = _stepArguments;
      String _triangleType = this.context.trianglePage.triangleType();
      String _first = JnarioIterableExtensions.<String>first(args);
      boolean _doubleArrow = Should.operator_doubleArrow(_triangleType, _first);
      Assert.assertTrue("\nExpected context.trianglePage.triangleType => args.first but"
       + "\n     context.trianglePage.triangleType is " + new StringDescription().appendValue(_triangleType).toString()
       + "\n     context.trianglePage is " + new StringDescription().appendValue(this.context.trianglePage).toString()
       + "\n     context is " + new StringDescription().appendValue(this.context).toString()
       + "\n     args.first is " + new StringDescription().appendValue(_first).toString()
       + "\n     args is " + new StringDescription().appendValue(args).toString() + "\n", _doubleArrow);
      
  }
  
  @Test
  @Order(2)
  @Named("And draws the triangle inside the canvas")
  public void andDrawsTheTriangleInsideTheCanvas() {
    boolean _coordinatesAreValid = this.context.trianglePage.coordinatesAreValid();
    boolean _doubleArrow = Should.operator_doubleArrow(Boolean.valueOf(_coordinatesAreValid), Boolean.valueOf(true));
    Assert.assertTrue("\nExpected context.trianglePage.coordinatesAreValid => true but"
     + "\n     context.trianglePage.coordinatesAreValid is " + new StringDescription().appendValue(Boolean.valueOf(_coordinatesAreValid)).toString()
     + "\n     context.trianglePage is " + new StringDescription().appendValue(this.context.trianglePage).toString()
     + "\n     context is " + new StringDescription().appendValue(this.context).toString() + "\n", _doubleArrow);
    
  }
  
  @Rule
  public TriangleTestsRule context = new Function0<TriangleTestsRule>() {
    public TriangleTestsRule apply() {
      TriangleTestsRule _triangleTestsRule = new TriangleTestsRule();
      return _triangleTestsRule;
    }
  }.apply();
}
