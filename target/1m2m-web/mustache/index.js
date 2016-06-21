/**
 * Created by Administrator on 2016/6/8.
 */
$(document).ready(function () {
    var person = {
        firstName: "Christophe",
        lastName: "Coenraets",
        blogURL: "http://coenraets.org"
    };
    var buildings=10;
    var floors=20;
    var census=4;
    var allBuilds=new Array;
    for(var i=0;i<10;i++){
        allBuilds[i]=i+1;
        $("#hehe").append(i+"<br/>");
    }
    
    var template="{{firstName}}{{lastName}}Blog:{{blogURL}}";
    var html=Mustache.to_html(template,person);
    $(".main").append(html);

});