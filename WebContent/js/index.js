YUI().use("tabview", function(Y) {
	var tabview = new Y.TabView({
		children : [ {
			label : "foo",
			content : "<p>foo content</p>"
		}, {
			label : "bar",
			content : "<p>bar content</p>"
		}, {
			label : "baz",
			content : "<p>baz content</p>"
		} ]
	});
	tabview.render("#nav");
});