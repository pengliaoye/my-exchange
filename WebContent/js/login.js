$("#f").validate({
	rules : {
		j_username : "required",
		j_password : "required"
	}
});

function submitOnEnter(e) {
	var keycode = e.keyCode || e.which;
	if (keycode == 13) {
		document.forms[0].submit();
	}
}