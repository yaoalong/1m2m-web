/**
 * Created by yaoalong on 2016/6/10.
 */
var banNum;
var floorNum;
$(document).ready(function () {

    $(".num").on('click', 'a', function () {
        $(".building").removeClass("none");
        banNum=$(this).children().html();
    });
    $(".close").on("click", function () {
        $(".building").addClass("none");
    });
    $(".building").on("click",'a', function () {
       floorNum=$(this).children().html();
        
    });
});