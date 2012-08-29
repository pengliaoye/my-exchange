
(function (window, undefined) {
	window.context = {};
	context.param = function (a) {
		var rquery = /\?/, r20 = /%20/g;
		var s = [], add = function (key, value) {
			s[s.length] = encodeURIComponent(key) + "=" + encodeURIComponent(value);
		};
		for (var prefix in a) {
			add(prefix, a[prefix]);
		}
		return s.join("&").replace("r20", "+");
	};
	context.ajax = function (url, data, callback, type) {
		if (typeof data == "function") {
			type = type || callback;
			callback = data;
			data = {};
		}
		data = context.param(data);
		var xmlhttp = null;
		if (window.XMLHttpRequest) {
			// code for all new browsers
			xmlhttp = new XMLHttpRequest();
		} else {
			if (window.ActiveXObject) {
			// code for IE5 and IE6
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		if (xmlhttp != null) {
			xmlhttp.onreadystatechange = function () {
				if (xmlhttp.readyState == 4) {// 4 = "loaded"
					if (xmlhttp.status == 200) {// 200 = OK
						callback(xmlhttp.responseText);
			//var data = eval("(" + xmlhttp.responseText + ")");
					} else {
						alert("Problem retrieving data:" + xmlhttp.statusText);
					}
				}
			};
			xmlhttp.open("POST", url);
			xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xmlhttp.send(data == null ? null : data);
		} else {
			alert("Your browser does not support XMLHTTP.");
		}
	};
	context.initDataGrid = function () {
		jQuery(".dataGrid tbody tr").hover(function () {
			jQuery(this).addClass("over");
		}, function () {
			jQuery(this).removeClass("over");
		}).click(function (e) {
			var $dt = jQuery(this).parents("table.dataGrid"), $tr = jQuery(this);
			if ($dt.hasClass("singleSelect")) {
				if (e.target == jQuery(this).find("td:first :radio")[0]) {
					if (jQuery(e.target).is(":checked") && !jQuery(this).hasClass("selected")) {
						$tr.siblings().removeClass("selected");
						$tr.addClass("selected");
					}
				}
			} else {
				if ($dt.hasClass("multiSelect")) {
					if (e.target == jQuery(this).find("td:first :checkbox")[0]) {
						if (jQuery(e.target).is(":checked") && !jQuery(this).hasClass("selected")) {
							jQuery(this).addClass("selected");
						} else {
							jQuery(this).removeClass("selected");
							jQuery(".dataGrid thead tr:first th:first :checkbox").removeAttr("checked");
						}
					}
				} else {
					$tr.siblings().removeClass("selected");
					$tr.addClass("selected");
				}
			}
		});
		if (jQuery(".dataGrid").hasClass("multiSelect")) {
			var $checkbox = jQuery(".dataGrid thead tr:first th:first input[type='checkbox']");
			if ($checkbox[0]) {
				$checkbox.click(function () {
					var checked = jQuery(this).is(":checked");
					jQuery(".dataGrid tbody tr").each(function (i, n) {
						var $n = jQuery(n);
						var $firstTdChk = $n.find("td:first :checkbox");
						if (checked) {
							$firstTdChk.attr("checked", "checked");
							$n.addClass("selected");
						} else {
							$firstTdChk.removeAttr("checked");
							$n.removeClass("selected");
						}
					});
				});
			}
		}
		jQuery(".pagelinks a").hover(function () {
			jQuery(this).attr("oldcolor", jQuery(this).css("background-color"));
			jQuery(this).css("background-color", "#e2ffdc");
		}, function () {
			jQuery(this).css("background-color", jQuery(this).attr("oldcolor"));
		});
	};
	context.initPage = function () {
		jQuery("input[type='button'],input[type='submit'], input[type='reset']").hover(function () {
			jQuery(this).addClass("button_on");
		}, function () {
			jQuery(this).removeClass("button_on");
		});
		jQuery("input[type='text'],textarea,select").not("[readonly],[disabled]").focus(function () {
			jQuery(this).addClass("input_on");
		}).blur(function () {
			jQuery(this).removeClass("input_on");
		});
		jQuery("input[type='button'][disabled],input[type='reset'][disabled='disabled']").addClass("disabledButton");
		jQuery("input[type='text'][disabled],textarea[disabled],select[disabled]").addClass("disabled");
		jQuery("input[type='text'][readonly],textarea[readonly],select[readonly]").addClass("readonly");
	};
	context.getTableContent = function (tabid) {
		var titles = [];
		var data = new Array();
		jQuery("#" + tabid + " tr").each(function (i, n) {
			var $n = jQuery(n);
			if (i == 0) {
				$n.find("th").each(function (j, m) {
					titles.push(jQuery(m).text());
				});
			} else {
				var innerArray = new Array();
				$n.find("td").each(function (j, m) {
					innerArray.push(jQuery(m).text());
				});
				data.push(innerArray);
			}
		});
		alert(data);
	};
})(window);
jQuery(function () {
	context.initDataGrid();
	context.initPage();
});
function getArgs() {
	var args = new Object();
	var query = window.location.search.substring(1);
	var pairs = query.split("&");
	for (var i = 0; i < pairs.length; i++) {
		var pos = pairs[i].indexOf("=");
		if (pos == -1) {
			continue;
		}
		var argname = pairs[i].substring(0, pos);
		var value = pairs[i].substring(pos + 1);
		value = decodeURIComponent(value);
		args[argname] = value;
		return args;
	}
}
Date.prototype.toString = function () {
	function f(n) {
            // Format integers to have at least two digits.
		return n < 10 ? "0" + n : n;
	}
	return this.getFullYear() + "-" + f(this.getMonth() + 1) + "-" + f(this.getDate()) + " " + f(this.getHours()) + ":" + f(this.getMinutes()) + ":" + f(this.getSeconds());
};
Date.prototype.format = function (mask) {
	var d = this;
	var zeroize = function (value, length) {
		if (!length) {
			length = 2;
		}
		value = String(value);
		for (var i = 0, zeros = ""; i < (length - value.length); i++) {
			zeros += "0";
		}
		return zeros + value;
	};
	return mask.replace(/"[^"]*"|'[^']*'|\b(?:d{1,4}|m{1,4}|yy(?:yy)?|([hHMstT])\1?|[lLZ])\b/g, function ($0) {
		switch ($0) {
		  case "d":
			return d.getDate();
		  case "dd":
			return zeroize(d.getDate());
		  case "ddd":
			return ["Sun", "Mon", "Tue", "Wed", "Thr", "Fri", "Sat"][d.getDay()];
		  case "dddd":
			return ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"][d.getDay()];
		  case "M":
			return d.getMonth() + 1;
		  case "MM":
			return zeroize(d.getMonth() + 1);
		  case "MMM":
			return ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"][d.getMonth()];
		  case "MMMM":
			return ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"][d.getMonth()];
		  case "yy":
			return String(d.getFullYear()).substr(2);
		  case "yyyy":
			return d.getFullYear();
		  case "h":
			return d.getHours() % 12 || 12;
		  case "hh":
			return zeroize(d.getHours() % 12 || 12);
		  case "H":
			return d.getHours();
		  case "HH":
			return zeroize(d.getHours());
		  case "m":
			return d.getMinutes();
		  case "mm":
			return zeroize(d.getMinutes());
		  case "s":
			return d.getSeconds();
		  case "ss":
			return zeroize(d.getSeconds());
		  case "l":
			return zeroize(d.getMilliseconds(), 3);
		  case "L":
			var m = d.getMilliseconds();
			if (m > 99) {
				m = Math.round(m / 10);
			}
			return zeroize(m);
		  case "tt":
			return d.getHours() < 12 ? "am" : "pm";
		  case "TT":
			return d.getHours() < 12 ? "AM" : "PM";
		  case "Z":
			return d.toUTCString().match(/[A-Z]+$/);

			// Return quoted strings with the surrounding quotes removed
		  default:
			return $0.substr(1, $0.length - 2);
		}
	});
};
String.prototype.toDate = function () {
	return new Date(this.replace(/-/g, "/"));
};
function StringBuffer() {
	this._strings_ = new Array();
}
StringBuffer.prototype.append = function (str) {
	this._strings_.push(str);
};
StringBuffer.prototype.toString = function () {
	return this._strings_.join("");
};

