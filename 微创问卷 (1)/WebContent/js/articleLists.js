/**
 * Created by caterpillar on 17/4/18.
 */
$(function () {
    $('tr:gt(1)').each(function(){
        $(this).find('td:last').find('span:last').click(function(){
            $('#f-tipbox p').text('删除后该账户将无法使用，确定删除？');
            var top = $(this).offset().top - $('#f-tipbox').height()+30;
            var left = $(this).offset().left-$('#f-tipbox').width();
            $('#f-tipbox').removeClass('dn');
            $('#f-tipbox').attr('style', 'top:'+top+'px'+';' +'left:'+left+'px'+';');
        });
    });
    $('.f-xjwz-header img').mouseover(function(){
        $(this).prop('src','img/xjwzfh2.png');
    }).mouseleave(function(){
        $(this).prop('src','img/xjwzfh.png');
    });
    $('.f-xjwz-fb').mouseover(function(){
        $(this).prop('src','img/xjwzfb2.png');
    }).mouseleave(function(){
        $(this).prop('src','img/xjwzfb.png');
    });
    $('.f-xjwz-qk').mouseover(function(){
        $(this).prop('src','img/xjwzqk2.png');
    }).mouseleave(function(){
        $(this).prop('src','img/xjwzqk.png');
    });

  

})
