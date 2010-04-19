require "rubygems"
gem "rspec"
gem "selenium-client"
require "selenium/client"
require "selenium/rspec/spec_helper"
require "triangle_test_lib"

# this version uses a Triangle class to track the state of the app and provide related behavior

describe "Triangle Test:" do
  attr_reader :selenium_driver
  attr :triangle

  before(:all) do
    @verification_errors = []
    @selenium_driver = Selenium::Client::Driver.new \
      :host => "localhost",
      :port => 4444,
      :browser => "*firefox",
      :url => "http://practice.agilistry.com",
      :timeout_in_second => 60
    @selenium_driver.start_new_browser_session
    @selenium_driver.window_maximize
    @selenium_driver.set_speed(1000)
    @triangle = TrianglePage.new(@selenium_driver)
  end
  
  append_after(:all) do
     @selenium_driver.close_current_browser_session
     #@verification_errors.should == []
  end
  
  describe "Renders valid" do
    
     after(:each) do
       @triangle.coordinates_valid?.should be_true, "Coordinates on drawn triangle were out of range: #{@triangle.coordinates_as_string}"
     end 
     
     it "Equilaterals" do
        @triangle.enter_sides("1","1","1")
        @triangle.get_type.should == "Equilateral"
     end
       
     it "Right Triangles" do
         @triangle.enter_sides("3","4","5")
         @triangle.get_type.should == "Right"
     end
     
     it "acute Scalene triangles" do
         @triangle.enter_sides("4","5","6")
         @triangle.get_type.should == "Scalene"
     end
     
     it "obtuse Scalene triangles" do
         @triangle.enter_sides("2","5","6")
         @triangle.get_type.should == "Scalene"
     end
     
     it "Scalene with floating point" do
        @triangle.enter_sides("4.2","5.6","6.1")
        @triangle.get_type.should == "Scalene"
     end
  end
  
  describe "Detects invalid with" do
    after(:each) do
      @triangle.get_type.should == "Invalid"
      @triangle.drawn?.should be_false, "Image appears to have been rendered. Should not have been."
    end
    
    it "0-length side" do
        @triangle.enter_sides("0","4","5")
    end
    
    it "largest bigger than the sum of the other two sides" do
        @triangle.enter_sides("1","4","6")
    end
  end
end
