class TrianglePage
  attr :results_row_count
  attr :page
  
  def initialize(page_handle)
    @page = page_handle
    @results_row_count = count_results_rows
    @page.open "/triangle"
  end
  
  def enter_sides(side1,side2,side3)
    enter(side2, "side2")
    enter(side1, "side1")
    enter(side3, "side3")
    wait_until("drawn?") if get_type != "Invalid"
  end
  
  def enter(side_value, side_id)
    @page.type "triangle_" + side_id, side_value
    wait_until "results_rows_updated?(1)"
    @results_row_count = count_results_rows
  end
  
  def get_type()
    return page.get_text("triangle_type")
  end
  
  def wait_until(condition)
    waited = 0 # num seconds waited so far
    poll_interval = 0.5
    timeout = 30
    while !eval(condition)
      sleep poll_interval
      waited = waited + poll_interval
      raise "Wait until timed out while waiting for #{condition}" if waited > timeout
    end
  end
  
  def get_coordinates()
    coord_text = coordinates_as_string
    coord_regx = /(-*[0-9]+),(-*[0-9]+)\) \((-*[0-9]+),(-*[0-9]+)\) \((-*[0-9]+),(-*[0-9]+)/
    return nil if coord_text.match(coord_regx).nil?
    return coord_text.match(coord_regx).to_a.values_at(1..6)
  end
  
  def coordinates_as_string()
    coord_xpath = "//div[@id='triangles_list']/div[contains(@class, 'triangle_row')][1]/div[contains(@class, 'triangle_data_cell')][5]"
    return @page.get_text(coord_xpath)
  end
  
  def count_results_rows
    return @page.get_xpath_count("//div[contains(@class, 'triangle_row')]").to_i
  end
  
  def results_rows_updated?(count = 1)
    current_count = count_results_rows
    return (@results_row_count + count <= current_count)
  end
  
  def drawn?
    return !get_coordinates.nil?
  end
  
  def coordinates_valid?
    # only validates coordinates if there *are* any
    coords = get_coordinates
    coords_valid = true
    if drawn?
      coords.each { |coord_num_as_string|
        # the following condition will catch any case where the returned coordinate is not a number 
        # (unlikely but possible if javascript returns NaN) or is negative
        coords_valid = false if coord_num_as_string.match(/^\d.*$/).nil?
        # this catches a case where the number is higher than it should be
        coords_valid = false if coord_num_as_string.to_i > 200
      }
    end
    return coords_valid
  end
  
end