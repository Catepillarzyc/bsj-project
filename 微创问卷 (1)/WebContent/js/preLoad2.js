$(function(){
    var imgArr = ['../img/button2.png','../img/bzfg2.png','../img/index2.png','../img/jj2.png',
    '../img/jkzt2.png','../img/jt2.png','../img/login_but_h.png','../img/tzgg2.png','../img/zkgf2.png',
    '../img/zxdt2.png','../img/button2.png'];

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