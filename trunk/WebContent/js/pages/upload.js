YUI()
		.use(
				"uploader",
				"gallery-progress-bar",
				function(Y) {
					var uploader, selectedFiles = {};

					function init() {
						var overlayRegion = Y.one("#selectLink").get('region');
						Y.one("#uploaderOverlay").set("offsetWidth",
								overlayRegion.width);
						Y.one("#uploaderOverlay").set("offsetHeight",
								overlayRegion.height);

						var swfURL = "http://localhost:8080/exchange/yui/build/uploader/assets/uploader.swf";

						if (Y.UA.ie >= 6) {
							swfURL += "?t=" + Y.guid();
						}

						uploader = new Y.Uploader({
							boundingBox : "#uploaderOverlay",
							swfURL : swfURL
						});

						uploader.on("uploaderReady", setupUploader);
						uploader.on("fileselect", fileSelect);
						uploader.on("uploadprogress", updateProgress);
						uploader.on("uploadcomplete", uploadComplete);
						uploader.on("uploadcompletedata", uploadCompleteData);

					}

					Y.on("domready", init);

					function setupUploader(event) {
						uploader.set("multiFiles", true);
						uploader.set("simLimit", 3);
						uploader.set("log", true);

						var fileFilters = new Array({
							description : "Images",
							extensions : "*.jpg;*.png;*.gif"
						}, {
							description : "Videos",
							extensions : "*.avi;*.mov;*.mpg"
						});
						uploader.set("fileFilters", fileFilters);
					}

					function fileSelect(event) {
						var fileData = event.fileList;

						for ( var key in fileData) {
							var id = fileData[key].id;
							if (!selectedFiles[id]) {
								var output = "<tr><td>"
										+ fileData[key].name
										+ "</td><td>"
										+ fileData[key].size
										+ "</td><td><div id='div_"
										+ id
										+ "' class='progressbars'></div></td></tr>";
								Y.one("#filenames tbody").append(output);

								var progressBar = new Y.ProgressBar(
										{
											id : "pb_" + id,
											layout : '<div class="{labelClass}"></div><div class="{sliderClass}"></div>'
										});
								progressBar.render("#div_" + id);
								progressBar.set("progress", 0);

								selectedFiles[fileData[key].id] = true;
							}
						}
					}

					function updateProgress(event) {
						var pb = Y.Widget.getByNode("#pb_" + event.id);
						pb.set("progress", Math.round(100 * event.bytesLoaded
								/ event.bytesTotal));
					}

					function uploadComplete(event) {
						var pb = Y.Widget.getByNode("#pb_" + event.id);
						pb.set("progress", 100);
					}

					function uploadCompleteData(event) {
						alert(event.data);
					}

					function uploadFiles(event) {
						uploader.uploadAll("upload.action");
					}

					Y.one("#uploadFilesLink").on("click", uploadFiles);
				});
/*YUI().use("io-upload-iframe", function(Y) {
	var uri = "upload.do";
	var cfg = {
		form : {
			id : "uploadForm",
			upload : true
		},
		on : {
			start : function(id, args) {
				// alert(id);
			}
		}
	};
	var request = Y.io(uri, cfg);
});*/