var pagination = new Pagination({ 
	          totalCount:"#span_maxRowCount",
			  currentPage:"#span_curPage",
			  totalPage:"#span_maxPage",
			  firstPage:"#href_page_first",
			  nextPage:"#href_page_next",
			  prevPage:"#href_page_pre",
			  lastPage:"#href_page_last",
			  pageSize:"#rowsPerPageSelect",
			  toPageIndex:"#toPageIndex",
			  fn:pageTo    
			});
/*
 * 
 * var pageInfo= { totalCount:10, currentPage:2, pageSize:5, pageCount:5, param: }
 * 
 */
function query() {
	pagination.init(); // 初始化
	pagination.getPageControl().fn(1); // 默认查询第一页
}
function Pagination(pageControl) {
	var _pageControl = pageControl;
	var _pageInfo = {};

	this.setPageInfo = function(pageInfo) {
		_pageInfo = pageInfo;
	};

	this.getPageInfo = function() {
		if (_pageInfo == null) {
			_pageInfo = {};
		}
		return _pageInfo;
	};

	this.getPageControl = function() {
		return _pageControl;
	};

}

Pagination.prototype.refresh = function() {
	var _pageInfo = this.getPageInfo();
	var _pageControl = this.getPageControl();

	$(_pageControl.totalCount).text("" + _pageInfo.totalCount);
	$(_pageControl.currentPage).text("" + _pageInfo.currentPage);

	var pagecount = Math.floor(_pageInfo.totalCount / _pageInfo.pageSize);
	if (_pageInfo.totalCount % _pageInfo.pageSize > 0) {
		pagecount++;
	}

	_pageInfo.pageCount = pagecount;

	$(_pageControl.totalPage).text("" + pagecount);

	$(_pageControl.firstPage).unbind("click");
	$(_pageControl.firstPage).click(function() {
		_pageControl.fn(1);
	});

	$(_pageControl.prevPage).unbind("click");
	$(_pageControl.prevPage).click(function() {
		if (_pageInfo.currentPage > 1) {
			_pageControl.fn(_pageInfo.currentPage - 1);

		} else {
			return false;
		}
	});

	$(_pageControl.nextPage).unbind("click");
	$(_pageControl.nextPage).click(function() {
		if (_pageInfo.currentPage < pagecount) {
			_pageControl.fn(_pageInfo.currentPage + 1);

		} else {
			// return false;
		}
	});

	$(_pageControl.lastPage).unbind("click");
	$(_pageControl.lastPage).click(function() {
		_pageControl.fn(pagecount);

	});

}

Pagination.prototype.init = function() {
	var _pageControl = this.getPageControl();
	var _pageInfo = this.getPageInfo();

	if (_pageControl != null) {
		$(_pageControl.totalCount).text("0");
		$(_pageControl.currentPage).text("1");
		$(_pageControl.totalPage).text("1");
		if (_pageControl.toPageIndex != null) {
			// $(_pageControl.toPageIndex).text("");
			$(_pageControl.toPageIndex).val("");
		}

		this.getPageInfo().pageSize = parseInt($(_pageControl.pageSize).val());

		$(_pageControl.pageSize).change(function() {
			if (_pageInfo.param != null) {
				_pageInfo.pageSize = parseInt($(_pageControl.pageSize).val());
				_pageControl.fn(1);
			}

		});

	}

};