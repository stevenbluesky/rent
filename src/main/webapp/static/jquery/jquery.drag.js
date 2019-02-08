/**
 * jQuery lightbox plugin : simple lightbox implements
 * http://blog.csdn.net/xxd851116
 *
 * @Copyright (c) 2009 XingXiuDong
 *
 * @Date: 2009-09-20
 * @author: XingXiuDong
 * @version: 1.0 simple drag and drop implements
 * @version: 1.1 add move area, x, y
 * @version: 1.2 update mouseup don't capture exception in intenet explore
 * @version: 1.3 update range error when lockedR is defalut value: false @date: 2009.09.22 22:33
 */
;(function($){
	/**
	 * @param obj: String id of moving element, or jQuery, or Element
	 * @param settings: options according to user
	 * @param lockedX: only move on the horizon
	 * @param lockedY: only move on the verticality
	 * @param lockedR: only move on the range
	 * @param range: the range size
	 */
	$.fn.extend({
		drag : function(obj, settings) {

			var _x, _y;
			var $this = this.hover(function(){$(this).css("cursor", "move");},function(){$(this).css("cursor", "");});;
			var $obj = obj === undefined ? $this : (typeof obj === "string" ? $(obj) : $(obj)) ;
			var isIE = (document.all) ? true : false;

			var options = $.extend({
				lockedX		: false,
				lockedY		: false,
				lockedR		: false,
				range		: { top : 0 , right : $(window).width(), bottom : $(window).height(), left : 0 },
				position	: "static",
				onStart		: function(){},
				onMove		: function(){},
				onStop		: function(){}
			}, settings);

			if (options.lockedR) {
				var $parent = $obj.parent();

				// $parent.css('position', options.position); 为什么用这一句不行？望高手解答！
				$parent.get(0).style.position = options.position;

				options.range.top = $parent.offset().top + parseInt($parent.css("border-top-width"));// $parent.offset().top不包括边框的长度
				options.range.left = $parent.offset().left + parseInt($parent.css("border-left-width"));// $parent.offset().left不包括边框的长度
				options.range.bottom = options.range.top + $parent.innerHeight();
				options.range.right = options.range.left + $parent.innerWidth();
			}
			
			$this.mousedown(function(event){
				options.onStart();
				// 当前鼠标距离移动元素上边框的距离
				_x = event.clientX - $obj.offset().left;
				// 当前鼠标距离移动元素左边框的距离
				_y = event.clientY - $obj.offset().top;
				$(document).bind("mousemove", move).bind("mouseup", stop).bind("blur", stop);
			});

			// 计算元素的底部和右部滑动范围
			var _bottom = options.range.bottom - $obj.outerHeight() - parseInt($(obj).css("margin-top")) - parseInt($(obj).css("margin-bottom"));
			var _right = options.range.right - $obj.outerWidth() - parseInt($(obj).css("margin-left")) - parseInt($(obj).css("margin-right"));

			function move(event) {
				//($this[0]).setCapture();
				// 当前移动元素距窗口上边框距离
				var iTop = event.clientY - _y;
				// 当前移动元素距窗口左边框距离
				var iLeft = event.clientX - _x;

				/* 
				 * 按范围锁定滑动元素的核心代码，当前滑动元素在移动时的top和left值的范围设定
				 * Top: 大于活动范围上边框顶部距离浏览器上边框的距离（还要加活动范围上边框的宽度）， 小于活动范围下边框顶部距离浏览器上边框的距离减去滑动元素高度
				 * Left: 大于活动范围左边框左部距离浏览器左边框的距离（还要加活动范围左边框的宽度）， 小于活动范围右边框顶部距离浏览器右边框的距离减去滑动元素宽度
				 */
				iTop = Math.min(Math.max(iTop, options.range.top), _bottom);
				iLeft = Math.min(Math.max(iLeft, options.range.left), _right);

				if (options.lockedX) iTop = $obj.offset().top;
				if (options.lockedY) iLeft = $obj.offset().left;

				$obj.css({position : 'absolute', left : iLeft + 'px', top : iTop + 'px'});

				options.onMove();

				if (isIE) {
					//设置鼠标捕获
					$obj.get(0).setCapture();
				} else {
					// 阻止默认动作
					event.preventDefault();
				}
				
			}

			function stop(event) {
				//($this[0]).releaseCapture();
				$(document).unbind("mousemove").unbind("mouseup");
				if (isIE) {
					$obj.get(0).releaseCapture();
				}
				options.onStop();
			}
		}
	});
})(jQuery);