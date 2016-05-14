(function(){
	var bigAutocomplete = new function(){
		this.currentInputText = null;
		this.holdText = null;
		
		//初始化插入自动补全div
		this.init = function(){
			$("body").append("<div id='bigAutocompleteContent' class='bigautocomplete-layout'></div>");
		}
	};
})();