/**
 * Created by caterpillar on 17/4/18.
 */

$(function(){
    $('tr').each(function(){
        $(this).find('td:first').css({'text-align':'left','text-indent':'10px'});
    });
    var height = $(window).height();
    $('#tt').height(height);
    $('#f-right-items').height(height);
    $('#iframe1').height(height);
    $('#iframe1').width('825');
    

    $(window).resize(function(){
        var height= $(window).height();
        $('#f-right-items').height(height);
        $('#tt').height(height);
        $('#iframe1').height(height);
        $('#iframe1').width('825');
    });

    $('#f-delete-button,.f-edit').click(function(){
        $('.hou').hide();
        $('#f-tipbox').hide();
    });
    $('.two').click(function(){
        $('.two').removeClass('selected');
        $(this).addClass('selected');

    })
    $('#f-cx').mouseover(function(){
        $(this).prop('src','img/cx2.png');
    }).mouseleave(function(){
        $(this).prop('src','img/cx.png');
    });
    $('#f-xjwz').mouseover(function(){
        $(this).prop('src','img/xj2.png');
    }).mouseleave(function(){
        $(this).prop('src','img/xj.png');
    });
    $('#f-xjzh').mouseover(function(){
        $(this).prop('src','img/zhszxjzh1.png');
    }).mouseleave(function(){
        $(this).prop('src','img/zhszxjzh.png');
    });
    $('#f-newpswBox-sure').mouseover(function(){
        $(this).prop('src','img/qrxg2.png');
    }).mouseleave(function(){
        $(this).prop('src','img/qrxg.png');
    });
    $('#f-newpswBox-cancle').mouseover(function(){
        $(this).prop('src','img/mmxgqx2.png');
    }).mouseleave(function(){
        $(this).prop('src','img/mmxgqx.png');
    });
    $('.f-window-close').mouseover(function(){
        $(this).prop('src','img/mmxggb2.png');
    }).mouseleave(function(){
        $(this).prop('src','img/mmxggb.png');
    });
    $('#f-main-pswEdit').click(function(){
        $('.f-pswEdit-before').removeClass('dn');
        $('.f-pswEdit-after').addClass('dn');
        $('.hou').show().height($(document).height());
        var top = - $('#f-newpswBox').height()/2;
        var width = - $('#f-newpswBox').width()/2;
        $('#f-newpswBox').removeClass('dn');
        $('#f-newpswBox').attr('style','top:' + '50%' +';' +'margin-top:'+top+'px'+';'+'left:' + '50%' +';' +'margin-left:'+width+'px'+';');
    });
    $('.f-window-cancle,.f-window-close').click(function(){
        $('.hou').hide();
        $('#f-newpswBox').hide();
    });
   

})