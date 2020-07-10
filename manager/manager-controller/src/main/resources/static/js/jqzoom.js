; (function ($) {
    $.fn.gysFdj = function (options) {
        var defaluts = {
            cameraW: 150, //镜头宽度
            cameraH: 150, //镜头高度
            pointBjColor: "#000", //镜头的背景颜色
            pointOpacity: 0.6, //镜头的透明度
            zoomPos: 10, //放大框距离源框的位置
            zoom: 4, //放大倍数
            headerHeight : 0
        };
        options = $.extend(defaluts, options);
        var obj = $(this);
        var objOriImg=$("img",obj);
        var objOriImgW=objOriImg.width();
        var objOriImgH=objOriImg.height();
        var offset = obj.offset();
        var objLeft = offset.left-100; //对象left
        var objTop = offset.top-options.headerHeight/2; //对象top
        var objWidth = obj.width(); //对象宽度
        var objHeight = obj.height(); //对象高度
        //镜头相对box的活动范围

        var cameraMaxLeft = objWidth + objLeft-100; //最大左范围
        var cameraMaxTop = objHeight + objTop-100; //最大下范围

        var imgStr = obj.html();
        var html = "";
        $(".gysFdjBox").remove();
        html += "<div style='left:" + (objLeft + objWidth + options.zoomPos) + "px; top:" + objTop + "px;display:none; position:absolute;width:" + (options.cameraW * options.zoom) + "px;height:" + (options.cameraH* options.zoom) + "px;overflow:hidden;' class='gysFdjBox'>" + imgStr + "</div>";
        $("body").append(html);

        $("img", $(".gysFdjBox")).width(objWidth*options.zoom).height(objHeight*options.zoom);
        var objFdjcamera = null;
        if ($("#gysFdjcamera").length == 0) {
            var pointBlock = "<div id='gysFdjcamera' style='width:" + options.cameraW + "px; height:" + options.cameraH + "px; background-color:" + options.pointBjColor + ";opacity:" + options.pointOpacity + ";filter:alpha(opacity="+options.pointOpacity*100+");cursor:crosshair;position:absolute;display:none;'></div>";
            $("body").append(pointBlock);
        }
        objFdjcamera = $("#gysFdjcamera");
        var nowLeft = 0, nowTop = 0;
        obj.on("mouseover", function (event) {
        	console.info(obj);
            objFdjcamera.show();
            $(".gysFdjBox").show();
            $(document).on("mousemove", function (event) {
                var pointX = event.clientX+$(document).scrollLeft();
                var pointY = event.clientY+$(document).scrollTop();
                nowLeft = pointX - options.cameraW / 2;
                nowTop = pointY - options.cameraH / 2;
                if (nowLeft <= objLeft) { nowLeft = objLeft; }
                else if (nowLeft >= cameraMaxLeft) { nowLeft = cameraMaxLeft; }
                if (nowTop <= objTop) { nowTop = objTop; }
                else if (nowTop >= cameraMaxTop) { nowTop=cameraMaxTop;}
                objFdjcamera.css({ left: nowLeft + "px", top: nowTop + "px" });
                nowLeft=(nowLeft-objLeft)*options.zoom;
                nowTop=(nowTop-objTop)*options.zoom;
                $("img",$(".gysFdjBox")).css({ "margin-top": -nowTop + "px", "margin-left": -nowLeft + "px" });
            });
        });
        objFdjcamera.on("mouseleave", function () {
            $(document).off("mousemove");
            objFdjcamera.hide();
            $(".gysFdjBox").hide();
        });
    }
})(jQuery);