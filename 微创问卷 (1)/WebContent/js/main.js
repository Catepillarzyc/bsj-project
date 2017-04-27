$(function() {

    var sWidth = $('.slideList li').width();
    var len = $('.slideList li').length;
    var index = 0;
    var picTimer;


//            $('.slide-prev').click(function () {
//                index -= 1;
//                if(index == -1) {
//                    index = len -1;
//                }
//                showPics(index);
//            });
//
//            $('.slide-next').click(function() {
//                index += 1;
//                if(index == len) {
//                    index = 0;
//                }
//                showPics(index);
//            });
//
    $('.slide-dots span').mouseover(function() {
        $('.slideList').stop();
        $(this).addClass('dotItemOn').siblings().removeClass('dotItemOn');
        index = $(this).index();
        showPics($(this).index());
    }).eq(0).trigger('mouseover');

    $(".slideList").hover(function() {
        clearInterval(picTimer);
    },function() {
        picTimer = setInterval(function() {
            showPics(index);
            index++;
            if(index == len) {index = 0;}
        },4000);
    }).trigger("mouseleave");


    $('.slideContainer ul').css('width',sWidth * (len));

    function showPics(index) {
        var nowLeft = -1 * index * sWidth;
        $('.slideList').animate({
            marginLeft:nowLeft
        },300);
        $('.slide-dots').find('span').eq(index).addClass('dotItemOn');
        $('.slide-dots').find('span').eq(index).siblings().removeClass('dotItemOn');
    };

})/**
 * Created by caterpillar on 17/3/30.
 */
