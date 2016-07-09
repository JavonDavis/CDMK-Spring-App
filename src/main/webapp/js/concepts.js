function requestConcepts(text) {
	URL = "http://skos.cdmk-caribbean.net/share";
	return $.ajax({
		type: "POST",
		url: URL,
		data: text
	});
}

(function() {
	$('#getConceptsForm').submit((e) => {
		e.preventDefault();
		textToExtractFrom = document.querySelector('#text').value;
		response = requestConcepts(textToExtractFrom)
			.then((data, textStatus) => {
				conceptNode = document.querySelector('.concepts');
				// foreach returned concept, append it to the conceptsNode'
				console.log(data);
			});		
	});
})();