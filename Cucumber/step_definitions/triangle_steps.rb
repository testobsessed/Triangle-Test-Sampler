When /^I enter (.*) for (.*)$/ do |side_value, side_id|
  $triangle.enter(side_value, side_id)
end

Then /^triangle displays "(.*)" as the triangle type$/ do |triangle_type|
  $triangle.get_type.should == triangle_type
end

And /^draws the triangle inside the canvas$/ do
  $triangle.coordinates_valid?.should be_true, "Coordinates on drawn triangle were out of range: #{$triangle.coordinates_as_string}"
end

