/**
 * Created by caterpillar on 17/4/18.
 */
$(document).delegate(".overflow,td", "mouseover", function() {
    var windowWidth =$(window).width() - 190;
    var containerLength = $(this)[0].clientWidth;
    var textLength = $(this)[0].scrollWidth;
    var text = $(this).text();
    if(textLength > containerLength) {
        $(".ts").html(text);
        if($(window).height()-$(this).offset().top - $(this).height()<$(".ts").height()){
            var top = $(this).offset().top - $(".ts").height();
        }else{
            var top = $(this).offset().top + $(this).height();
        }
        var left = $(this).offset().left;
        var width=$(".ts").width();
        var o_width = width+left-windowWidth;
        left =o_width>0?left+$(this).width()-width:left+22;
        $(".ts").attr("style", 'top:' + top + 'px' + ';' + 'left:' + left + 'px' + ';' + 'display:block')
    } else {
        $(".ts").hide();
    }
});
$(document).delegate(".overflow,td","mouseout",function(){
    $(".ts").hide();
})