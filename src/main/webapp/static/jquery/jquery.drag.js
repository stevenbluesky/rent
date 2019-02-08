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

				// $parent.css('position', options.position); Ϊʲô����һ�䲻�У������ֽ��
				$parent.get(0).style.position = options.position;

				options.range.top = $parent.offset().top + parseInt($parent.css("border-top-width"));// $parent.offset().top�������߿�ĳ���
				options.range.left = $parent.offset().left + parseInt($parent.css("border-left-width"));// $parent.offset().left�������߿�ĳ���
				options.range.bottom = options.range.top + $parent.innerHeight();
				options.range.right = options.range.left + $parent.innerWidth();
			}
			
			$this.mousedown(function(event){
				options.onStart();
				// ��ǰ�������ƶ�Ԫ���ϱ߿�ľ���
				_x = event.clientX - $obj.offset().left;
				// ��ǰ�������ƶ�Ԫ����߿�ľ���
				_y = event.clientY - $obj.offset().top;
				$(document).bind("mousemove", move).bind("mouseup", stop).bind("blur", stop);
			});

			// ����Ԫ�صĵײ����Ҳ�������Χ
			var _bottom = options.range.bottom - $obj.outerHeight() - parseInt($(obj).css("margin-top")) - parseInt($(obj).css("margin-bottom"));
			var _right = options.range.right - $obj.outerWidth() - parseInt($(obj).css("margin-left")) - parseInt($(obj).css("margin-right"));

			function move(event) {
				//($this[0]).setCapture();
				// ��ǰ�ƶ�Ԫ�ؾര���ϱ߿����
				var iTop = event.clientY - _y;
				// ��ǰ�ƶ�Ԫ�ؾര����߿����
				var iLeft = event.clientX - _x;

				/* 
				 * ����Χ��������Ԫ�صĺ��Ĵ��룬��ǰ����Ԫ�����ƶ�ʱ��top��leftֵ�ķ�Χ�趨
				 * Top: ���ڻ��Χ�ϱ߿򶥲�����������ϱ߿�ľ��루��Ҫ�ӻ��Χ�ϱ߿�Ŀ�ȣ��� С�ڻ��Χ�±߿򶥲�����������ϱ߿�ľ����ȥ����Ԫ�ظ߶�
				 * Left: ���ڻ��Χ��߿��󲿾����������߿�ľ��루��Ҫ�ӻ��Χ��߿�Ŀ�ȣ��� С�ڻ��Χ�ұ߿򶥲�����������ұ߿�ľ����ȥ����Ԫ�ؿ��
				 */
				iTop = Math.min(Math.max(iTop, options.range.top), _bottom);
				iLeft = Math.min(Math.max(iLeft, options.range.left), _right);

				if (options.lockedX) iTop = $obj.offset().top;
				if (options.lockedY) iLeft = $obj.offset().left;

				$obj.css({position : 'absolute', left : iLeft + 'px', top : iTop + 'px'});

				options.onMove();

				if (isIE) {
					//������겶��
					$obj.get(0).setCapture();
				} else {
					// ��ֹĬ�϶���
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