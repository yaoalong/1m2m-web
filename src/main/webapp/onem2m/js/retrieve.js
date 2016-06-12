/**
 * Created by yaoalong on 2016/6/10.
 */
$(document).ready(function(){

     $("#retrieveParking").click(function () {
        var text=$(this).attr("href");
       // $.getJSON("/retrieve.do",{key:text},function(data){
            bootbox.alert("有车!", function() {

          //  });
        }) ;
        $(this).attr("href","####");
    });
    $("#retrieveMachine").click(function () {
        var text=$(this).attr("href");
      //  $.getJSON("/retrieve.do",{key:text},function(data){
            bootbox.alert("打开!", function() {

            });
      //  }) ;
        $(this).attr("href","####");
    });
 

});