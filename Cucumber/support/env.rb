require "rubygems"
gem "selenium-client"
require "selenium/client"
require "../RSpec/triangle_test_lib"
require 'spec/expectations'

# "before all"
selenium_driver = Selenium::Client::Driver.new \
  :host => "localhost",
  :port => 4444,
  :browser => "*firefox",
  :url => "http://practice.agilistry.com",
  :timeout_in_second => 60
selenium_driver.start_new_browser_session
selenium_driver.window_maximize
selenium_driver.set_speed(1000)
$triangle = TrianglePage.new(selenium_driver)


# "after all"
at_exit do
  selenium_driver.close_current_browser_session
end
