var timer = null;
function sendReqCallable() {
	timer = setInterval(function() {
		$.ajax({
			type : "post",
			url : 'http://172.16.1.80:8080/async/async/callable/response-body',
			success : function(msg) {
				console.log(msg);
			}
		});
	}, 10);
	$("#startCallable").attr("disabled", true);
}

function diableSendReqCallable() {
	clearInterval(timer);
	$("#startCallable").attr("disabled", false);
}

function sendReqDefered() {
	timer = setInterval(function() {
		$.ajax({
			type : "post",
			url : 'http://172.16.1.80:8080/async/async/deferred-result/response-body',
			success : function(msg) {
				console.log(msg);
			}
		});
	}, 10);
	$("#startDefered").attr("disabled", true);
}

function diableSendReqDefered() {
	clearInterval(timer);
	$("#startDefered").attr("disabled", false);
}
