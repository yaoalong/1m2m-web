/**
 * Created by yaoalong on 2016/6/10.
 */
$(document).ready(function(){

     $(".machine").click(function () {
         var text=$(this).attr("href");
        $.getJSON("/retrieve.do",{key:text},function(data){
            alert(data.closed?"打开":"关闭");
        }) ;
         $(this).attr("href","####");
     });

    
});