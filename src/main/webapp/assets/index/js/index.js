$(function(){

    $.send({
        url:'test/test',
        type:'post',
        isjson:true,
        data:{
            test:"123123",
            name:"张三",
            date:"2018-01-01"
        },
        success:function(result){
            console.log(result);
        }
    });


});