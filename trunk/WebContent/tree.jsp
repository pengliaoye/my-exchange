<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>My JSP 'tree.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">		
		<link rel="stylesheet" href="yui/build/gallery-yui3treeview/assets/treeview-classic.css" type="text/css" />
		<link rel="stylesheet" href="css/gallerycss-xarno-skins.css" type="text/css" />
		<script type="text/javascript" src="yui/build/yui/yui.js"></script>
		<script type="text/javascript" src="js/tree.js"></script>
		<script type="text/javascript">
			YUI({gallery: 'gallery-2011.01.03-18-30'}).use("gallery-yui3treeview",function(Y){
				var treeView=new Y.TreeView({
					srcNode:"#tree",
					contentBox:null,
					type:"TreeView",
					children: [
                   {
                      label: "level1-1",
                      type: "TreeView",
                      children: [
                          {label: "level2-1"},
                          {label: "level2-2"}
                      ]
                   },
                   {
                       type: "TreeView",
                      label : "level1-2"
                  }
               ]
					});
				/*treeView.plug(Y.Plugin.TreeviewDatasource,{
					source:"/treeload",
					loadOnDemand: "true",
					sourceMethod:"io",
					loadingMsg:"<span>Loading</span>",
					dataSchemaJSON:{
						resultListLocator:""
					}
				});*/
				treeView.render();
				/*treeView.on("treeleaf:click",function(e){
					alert(e.target.get("label"));
				});*/
				//treeView.on("treeview:click",function(e){
					//alert(e.target.isRoot());						
				// });				
				//treeView.ds.fire("sendRequest");					
				//treeView.ds.on("onDataReturnSuccess",function(){alert(2);});
				//treeView.ds.on("onDataReturnFailure",function(){alert(1);});				
			});
			YUI({gallery: 'gallery-2011.03.11-23-49'}).use('gallerycss-xarno-skins',"dd-plugin","gallery-dialog",function(Y){
				var d=new Y.Dialog();
				d.alert('You have an Alert!',null,alertCallback);
				function alertCallback(){
					Y.log("Alert callback has fired.");
				}
			});
			YUI().use("node","dump",function(Y){
				var i;
				var handleClick = function(e) {
					alert(e.target);
				};
				Y.on("click",handleClick,"#test")
				
				var Logging = function () {
				var logger = null;
				
				return {
						initLogger : function (logNode) {
							if (!logger) {
								logger = Y.one(logNode);
							}
						},
						log : function (message) {
							if (logger) {
								logger.append("<p>" + message + "</p>");
							}
						}
					} 
				}();
				
				var PageController = function () {
				var app_const = 12345;
					
					return {
						getConst : function () {
							return app_const;
						},
						logConst : function () {
							
						}
					}				
				}()
				
				Y.mix(PageController,Logging);	
				
				function Bird(name) {
					this.name = name;
				}			
				
				Bird.prototype.flighted = true;
				Bird.prototype.isFlighted = function () {return this.flighted};
				Bird.prototype.getName = function () {return this.name};
				
				function Chicken(name) {
					 Chicken.superclass.constructor.call(this,name);
				}
				Y.extend(Chicken,Bird);
				
				var chicken = new Chicken("123");
				//alert(chicken.isFlighted());
				//alert(chicken.name);
				
				var Foo = function() {
					this.publish("interestingMoment");
				};
				
				Foo.prototype.doSomething = function() {
					var eventData = {};
					eventData.statusText = "bar";
					this.fire("interestingMoment",eventData);
				}
				
				Y.augment(Foo,Y.EventTarget);
				
				var foo = new Foo();
				foo.on("interestingMoment",function(data){
					alert(data.statusText);
				})		
				
				//foo.doSomething();
				
				var set1 = {foo : "foo"};
				var set2 = {foo : "BAR", bar : "bar"};
				var set3 = {foo : "FOO", baz : "BAZ"};
				
				var merged = Y.merge(set1,set2,set3);
			});	
			YUI().use("cookie", function(Y){
				var value = Y.Cookie.get("name");	
				//alert(value);			
				var date = new Date();
				date.setDate(date.getDate()+31);
				Y.Cookie.set("name","123",{expires:date})
				Y.Cookie.remove("name");
			});
			function uploadFile(){
				YUI({filter:"raw"}).use("io-upload-iframe",function(Y){
					var uri="servlet/upload";				
					var cfg = {					
						form: {
							id: "uploadForm",
							upload: true
						},
						on: {
							start: start,
							complete: complete
						}
					};
					function start(id,args){
						
					}
					function complete(id,o,args){
						alert(o.responseText);
					}
					var request = Y.io(uri,cfg);	
				});
			}
			YUI().use("jsonp",function(Y){
				var url="http://134.201.138.79:9081/zxcc/heartHold.jsp?callback={callback}";
				function handleJSONP(response){
					// alert(response);
				}
				var service = new Y.JSONPRequest(url,handleJSONP);
				service.send();
			})
			YUI({filter:"raw"}).use("event-key","widget",function(Y){
				var Lang = Y.Lang,
					Widget = Y.Widget,
					Node = Y.Node;
				function Spinner(config){
					Spinner.superclass.constructor.apply(this,arguments);
				}
				Spinner.NAME = "spinner";
				Spinner.ATTRS = {
					min : {
						value:0
					},
					max : {
						value:100
					},
					value : {
						value:0,
						validator:function(val){
							return this._validateValue(val);
						}
					},
					minorStep : {
						value:1
					},
					majorStep : {
						value:10
					},
					strings : {
						value:{
							tooltip: "Press the arrow up/down keys for minor increments, \
                      page up/down for major increments.",
							increment: "Increment",
							decrement: "Decrement"
						}
					}					
				};
				Spinner.INPUT_CLASS = Y.ClassNameManager.getClassName(Spinner.NAME,"value");
				Spinner.INPUT_TEMPLATE = "<input type='text' class='"+Spinner.INPUT_CLASS+"'>";
				Spinner.BTN_TEMPLATE = "<button type='button'></button>";
				Y.extend(Spinner, Widget,{
					initializer : function(config){
					},
					destructor : function(){
						this._documentMouseUpHandle.detach();
						
						this.inputNode = null;
						this.incrementNode = null;
						this.decrementNode = null;
					},
					renderUI : function(){
						this._renderInput();
						this._renderButtons();
					},
					bindUI : function(){
						this.after("valueChange",this._afterValueChange);
						
						var boundingBox = this.get("boundingBox");
						
						var keyEventSpec = (!Y.UA.opera) ? "down:" : "press:";
						keyEventSpec += "38, 40, 33, 34";
						
						Y.on("key", Y.bind(this._onDirectionKey, this), boundingBox, keyEventSpec);
						Y.on("mousedown", Y.bind(this._onMouseDown, this), boundingBox);
					    this._documentMouseUpHandle = Y.on("mouseup", Y.bind(this._onDocMouseUp, this), boundingBox.get("ownerDocument"));
					},
					syncUI : function(){
					},
					_renderInput : function(){
						var contentBox = this.get("contentBox"),
							input = contentBox.one("."+Spinner.INPUT_CLASS);
						    strings = this.get("strings");
						if(!input){
							input = Node.create(Spinner.INPUT_TEMPLATE);
							contentBox.appendChild(input);
						}				
						input.set("title",strings.tooltip);
						this.inputNode = input;
					},
					_renderButtons : function(){
						var contentBox = this.get("contentBox");
							strings = this.get("strings");
						var inc = this._createButton(strings.increment,this.getClassName("increment")); 
						var dec = this._createButton(strings.decrement,this.getClassName("decrement"));	
						
						this.incrementNode = contentBox.appendChild(inc);
						this.decrementNode = contentBox.appendChild(dec);						
					},
					_createButton : function(text,className){
						var btn = Node.create(Spinner.BTN_TEMPLATE);
						btn.set("innerHTML",text);
						btn.set("title",text);
						btn.addClass(className);
						
						return btn;
					},
					_onMouseDown : function(e) {
						var node = e.target,
							dir,
							handled = false,
							currVal = this.get("value"),
							minorStep = this.get("minorStep");
							
						if (node.hasClass(this.getClassName("increment"))){
							this.set("value", currVal + minorStep);
							dir = 1;
							handled = true;
						} else if (node.hasClass(this.getClassName("decrement"))){
							this.set("value", currVal - minorStep);
							dir = -1;
							handled = true;
						}	
						
						if (handled) {
							this._setMouseDownTimers(dir, minorStep);
						}						
					},
					_defaultCB : function() {
						return null;
					},
					_onDocMouseUp : function(e) {
						this._clearMouseDownTimers();
					},
					_onDirectionKey : function(e) {
					
						e.preventDefault();
						
						var currVal = this.get("value"),
							newVal = currVal,
							minorStep = this.get("minorStep"),
							majorStep = this.get("majorStep");
						switch (e.charCode) {
							case 38:
								newVal += minorStep;
								break;
							case 40:
								newVal -= minorStep;
								break;
							case 33:
								newVal += majorStep;
								break;
							case 34:
								newVal -= majorStep;
								break;																
						}
						if (newVal !== currVal) {
							this.set("value",newVal);
						}
					},
					_setMouseDownTimers : function(dir, step){
						this._mouseDownTimer = Y.later(500, this, function(){
							this._mousePressTimer = Y.later(100, this, function(){
								this.set("value", this.get("value") + (dir * step));
							}, null, true);
						});
					},
					_clearMouseDownTimers : function() {
						if (this._mouseDownTimer) {
							this._mouseDownTimer.cancel();
							this._mouseDownTimer = null;
						}		
						if (this._mousePressTimer) {
							this._mousePressTimer.cancel();
							this._mousePressTimer = null;
						}					
					},
					_afterValueChange : function(e){
						this._uiSetValue(e.newVal);
					},
					_uiSetValue : function(val){
						this.inputNode.set("value",val);
					},
					_validateValue : function(val){
						var min = this.get("min"),
						    max = this.get("max");
						return (Lang.isNumber(val) && val >= min && val <= max);
					}					
				});
				Spinner.HTML_PARSER = {
					value:function(contentBox){
						var node = contentBox.one("." + Spinner.INPUT_CLASS);
						return (node)?parseInt(node.get("value")):null;
					}
				};
				var spinner = new Spinner({
					srcNode : "#spinner"
				});
				spinner.render();	
			});
		</script>
	</head>
	<body class="yui3-skin-sam">
		<ul id="tree"></ul>
		<input id="test" type="button" value="test">
		<form id="uploadForm">
			<input type="file">
		</form>
		<input type="button" value="提交" onclick="uploadFile()">
		<div id="demo"></div>
		<input type="text" id="spinner"  value="20">
	</body>
</html>
