$(function () {

    $.send({
        url: 'test/test',
        type: 'post',
        isjson: true,
        data: {
            test: "123123",
            name: "张三",
            date: "2018-01-01"
        },
        success: function (result) {
            console.log(result);
        },
        beforeSend: function () {
            $("body").mask(true);
        },
        complete: function () {
            $("body").mask(false);
        }
    });

    $.send({
        url: 'test/savetest',
        type: 'post',
        isjson: true,
        data: {
            age: "50",
            name: "张三",
            date: "2018-01-01"
        },
        success: function (result) {
            console.log(result);
        },
        beforeSend: function () {
            $("body").mask(true);
        },
        complete: function () {
            $("body").mask(false);
        }
    });

    $.send({
        url: 'test/querytest',
        type: 'post',
        isjson: true,
        data: {
            name: "张三"
        },
        success: function (result) {
            console.log(result);
        },
        beforeSend: function () {
            $("body").mask(true);
        },
        complete: function () {
            $("body").mask(false);
        }
    });



});