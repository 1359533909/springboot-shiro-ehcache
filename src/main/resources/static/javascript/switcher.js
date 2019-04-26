window.console = window.console || function() {
   var e = {};
   e.log = e.warn = e.debug = e.info = e.error = e.time = e.dir = e.profile = e.clear = e.exception = e.trace = e.assert = function() {};
   return e
}();
function gbc1(){
    document.getElementById('mygbc').href='css/globalBackgroudColor1.css';
}
function gbc2(){
    document.getElementById('mygbc').href='css/globalBackgroudColor2.css';
}
function gbc3(){
    document.getElementById('mygbc').href='css/globalBackgroudColor3.css';
}
function gbc4(){
    document.getElementById('mygbc').href='css/globalBackgroudColor4.css';
}
function gbc5(){
    document.getElementById('mygbc').href='css/globalBackgroudColor5.css';
}
function gbc6(){
    document.getElementById('mygbc').href='css/globalBackgroudColor6.css';
}
$(document).ready(function() {
	var e =  '<div class="switcher-container">'+
               '<h2>样式切换<a href="#" class="sw-click"><i class="fa fa-cog"></i></a></h2>'+
               '<div class="selector-box">'+
                    '<div class="clearfix"></div>'+
                    '<div class="sw-odd"><h3>系统颜色</h3>'+
                    '<div class="ws-colors">'+
                        '<a href="#color1" id="color1" class="styleswitch" onclick="gbc1()">'+
                            '&nbsp;<span class="cl1"></span>'+
                        '</a>'+
                        '<a href="#color2" class="styleswitch" id="color2" onclick="gbc2()">'+
                            '&nbsp;<span class="cl2"></span>'+
                        '</a>'+
                        '<a href="#color3" class="styleswitch" id="color3" onclick="gbc3()">'+
                            '&nbsp;<span class="cl3"></span>'+
                        '</a>'+
                        '<a href="#color4" class="styleswitch" id="color4" onclick="gbc4()">'+
                            '&nbsp;<span class="cl4"></span>'+
                        '</a>'+
                        '<a href="#color5" class="styleswitch" id="color5" onclick="gbc5()">'+
                            '&nbsp;<span class="cl5"></span>'+
                        '</a>'+
                        '<a href="#color6" class="styleswitch" id="color6" onclick="gbc6()">'+
                            '&nbsp;<span class="cl6"></span>'+
                        '</a>'+
                        
                    '</div></div>'+
               '</div>'+
            '</div>';
	$('body').append(e);
	switchAnimate.loadEvent();
	switchColor.loadEvent();
    });

    var switchColor = {
    colorObj: {
        colorCookie: "colorCookie",
        switchClass: ".styleswitch",
        currentClass: "current",
        headLink: "head link[id=colors]",
        colorItem: ".ws-colors a.styleswitch",
        reset: "#reset",
        defaultColor: "color1"
    },
    loadEvent: function() {
        var e = switchColor.colorObj;
        if ($.cookie(e.colorCookie)) {
            switchColor.setColor($.cookie(e.colorCookie))
        } else {
            switchColor.setColor(e.defaultColor)
        }
        $(e.colorItem).on("click", function() {
            var e = $(this).attr("id");
            switchColor.setColor(e)
        });
        $(e.reset).on("click",function () {
            switchColor.setColor(e.defaultColor)
        })
    },
    setColor: function(e) {
        var t = switchColor.colorObj;
        $.cookie(t.colorCookie, e);
        $(t.headLink).attr("href", "stylesheets/colors/" + e + ".css");

        $(t.switchClass).removeClass(t.currentClass);
        $("#" + e).addClass(t.currentClass);

          //set color for text in content
        if($('.infomation.text-center h3').length === 1) {
            var hiText = $('.infomation.text-center h3').closest('.section').css("background-color").toString();
            if(hiText === "rgb(91, 91, 91)")
                $('.infomation.text-center h3').css('color', "#fff");
            }
        }
    };


    var switchAnimate = {
		loadEvent: function() {
		  $(".switcher-container h2 a.sw-click").on('click',function(e) {
            $(this).addClass('active');
			 var t = $(".switcher-container");

			 if (t.css("left") === "-220px") {
				$(".switcher-container").animate({ left: "0"}, 300, 'easeInOutExpo')
			 } else {
                $(this).removeClass('active');
				$(".switcher-container").animate({ left: "-220px" }, 300, 'easeInOutExpo')
			 }

			 e.preventDefault();
		 })
	   }
	};


