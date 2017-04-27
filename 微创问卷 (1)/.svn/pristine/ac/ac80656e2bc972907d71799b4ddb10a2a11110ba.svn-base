$(function(){
    var imgArr = ['img/cx2.png','img/xj2.png','img/qrxg2.png','img/login2.png','img/mmxggb2.png',
        'img/mmxgqx2.png','img/nav-left1.png','img/nav-right1.png','img/qd2.png','img/qrxg2.png',
    'img/qx2.png','img/xjwzfb2.png','img/xjwzfh2.png','img/xjwzqk2.png','img/xjzhbc1.png','img/xjzhfh1.png',
    'img/xjzhqk1.png','img/zhsz1.png','img/zhszqd1.png','img/zhszxjzh1.png'];

    preloadimages(imgArr);
    function preloadimages(arr) {
        var newimages = [],loadedimages = 0;
        var postaction = function (arr){};
        var arr = (typeof arr!="object")?[arr]:arr;
        function imageloadpost() {
            loadedimages++;
            postaction(newimages);
        }
        for(var i = 0; i < arr.length; i++) {
            newimages[i] = new Image();
            newimages[i].src = arr[i];
            if(newimages[i].complete){
                return;
            }
            newimages[i].onload = function() {
                imageloadpost();
            }
            newimages[i].onerror = function() {
                imageloadpost();
            }
        }
        return {
            done:function(f) {
                postaction = f || postaction
            }
        }
    }
})