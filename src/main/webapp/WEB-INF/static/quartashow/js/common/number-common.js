<script type="text/javascript">
		
		// Bloqueia no input qualquer caracter que nao seje numerico
		// Ex: <input type="number" onkeypress="return NumbersOnly(event);">
		function NumbersOnly(e) {
			  var key = (window.event)?event.keyCode:e.which;   
			  if(key > 47 && key < 58)
				  return true;
			  else {
			    if (key == 8 || key == 0) 
			    	return true;
				  else  
					  return false;
		    }
		}
		
</script>
