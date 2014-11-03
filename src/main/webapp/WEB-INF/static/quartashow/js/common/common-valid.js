	
	function getHtmlTooltipsErrorMessage(message) {
		if (message == '' || message == undefined)
			return '';
		return '<span data-placement="right" data-original-title="' + message + '"  class="glyphicon glyphicon-warning-sign text-danger tooltips" ></span>';

	};