/**
 * Created by caterpillar on 17/4/18.
 */
$(function(){
    $('.unused').find('span:lt(2)').addClass('undoable');
    $('.list span').click(function(){
        var text = $(this).text();
        var that = this;
        switch(text) {
           // case('初始化密码'):initalPsw(that);
           //                 break;
       //     case('停用'):stopUsing(that);
      //                      break;
       //     case('启用'):startUsing(that);
        //                    break;
         //   case('删除'):deleteAccount(that);
       //                     break;
       //     default:
        }

    });
    $('.f-xjzh-header img').mouseover(function(){
        $(this).prop('src','img/xjzhfh1.png');
    }).mouseleave(function(){
        $(this).prop('src','img/xjzhfh.png');
    });
    $('.f-xjzh-save').mouseover(function(){
        $(this).prop('src','img/xjzhbc1.png');
    }).mouseleave(function(){
        $(this).prop('src','img/xjzhbc.png');
    });
    $('.f-xjzh-clear').mouseover(function(){
        $(this).prop('src','img/xjzhqk1.png');
    }).mouseleave(function(){
        $(this).prop('src','img/xjzhqk.png');
    });
  
})
function initalPsw(that){
    $('#f-tipbox p').text('初始化密码将导致用户自行修改的密码不可用，确定初始化密码？');
    var top = $(that).offset().top - $('#f-tipbox').height()+30;
    var left = $(that).offset().left-$('#f-tipbox').width();
    $('#f-tipbox').removeClass('dn');
    $('#f-tipbox').attr('style', 'top:'+top+'px'+';' +'left:'+left+'px'+';');
}
function stopUsing(that) {
    $(that).parents('tr').addClass('unused');
    $('.unused').find('span:lt(2)').addClass('undoable');
    $(that).text('启用');
}
function startUsing(that) {
    $(that).parents('tr').removeClass('unused').find('span:lt(2)').removeClass('undoable');
    $(that).text('停用');
}
function deleteAccount(that) {
    $('#f-tipbox p').text('删除后该账户将无法使用，确定删除？');
    var top = $(that).offset().top - $('#f-tipbox').height()+30;
    var left = $(that).offset().left-$('#f-tipbox').width();
    $('#f-tipbox').removeClass('dn');
    $('#f-tipbox').attr('style', 'top:'+top+'px'+';' +'left:'+left+'px'+';');
}