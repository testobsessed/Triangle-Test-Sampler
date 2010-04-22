import re

class CoordinateCheck:
	
    def coordsInRange(self,sString):
        import re
        regex = re.compile("(-*[0-9]+),(-*[0-9]+)\) \((-*[0-9]+),(-*[0-9]+)\) \((-*[0-9]+),(-*[0-9]+)")
        r = regex.search(sString)
        coords_valid = True
        for i in range(6):
            if r.group(i+1) < 0 or r.group(i+1) > 200:
                coords_valid = False
        return coords_valid

