
  function test_commons_string() {
      alert('test_commons_string');
  }

	//examples
	//console.log(padLeft(23,5));         Result //=> '00023'
	//console.log((23).padLeft(5));       Result //=> '00023'
	//console.log((23).padLeft(5,' '));   Result //=> '   23'
	//console.log(padLeft(23,5,'$'));     Result //=> '$$$$$$23'	 
	function padLeft(value, length, valueAdd) {
		return Array(length-String(value).length+1).join(valueAdd||'0')+value;
	}	


	// left padding valueString with charIncluded to a total of n chars
	//padding_left('abcd', '0', 8);       Result //=> 0000abcd
	function padding_left(valueString, charIncluded, length) {
	    if (! valueString || ! charIncluded || valueString.length >= length) {
	        return valueString;
	    }
	
	    var max = (length - valueString.length)/charIncluded.length;
	    for (var i = 0; i < max; i++) {
	        valueString = charIncluded + valueString;
	    }
	
	    return valueString;
	}
	
	// right padding valueString with charIncluded to a total of n chars
	//padding_right('1234', '0', 9);      Result //=> 123400000	
	function padding_right(valueString, charIncluded, length) {
	    if (! valueString || ! charIncluded || valueString.length >= length) {
	        return valueString;
	    }
	
	    var max = (length - valueString.length)/charIncluded.length;
	    for (var i = 0; i < max; i++) {
	        valueString += charIncluded ;
	    }
	
	    return valueString;
	}
	
	function isEmpty(str) {
		if(str == '' || str == undefined || str == null) 
			return true;
		return false;
	}

