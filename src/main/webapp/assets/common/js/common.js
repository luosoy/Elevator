/*
 * 全局变量.
 */
var S = SYS = $.extend(SYS || {}, {});

/**
 * ajax全局设置.
 */
$.ajaxSetup({
    beforeSend: function (jqx, st) {
    },
    ajaxStart: function () {
    },
    ajaxSend: function () {
    },
    type: 'post'
});

$.jqAjax = $.ajax;

$.ajax = $.send = function (settings) {
    settings.url = settings.url || '';
    var newst = $.extend(true, {}, settings, {
        url: (settings.url || '').indexOf('/') == 0 ? (SYS.ctx + settings.url) : (SYS.path + settings.url),
        type: settings.ajaxType || settings.type || "get",
        success: function (data) {
            var args = arguments, ndata = data, func;
            if (ndata.content) {
                if ('SESSION_TIMEOUT' == ndata.content) {
                    window.location.reload();
                    return;
                } else if (!settings.failed) {
                    alert(ndata.content);
                }
            }
            func = ndata.type == 'SUCCESS' ? settings.success : settings.failed;
            if (func) {
                func.call(this, ndata, args[1], args[2]);
            }
        },
        error: function (jqx, type, text) {
            var args = arguments;
            if (jqx.status != 0 && !settings.nomsg) {
                alert('[' + jqx.status + '] ' + text, jqx.status);
            }
            settings.error && settings.error.call(this, jqx, type, text);
        },
        complete: function (jqx, text) {
            settings.complete && settings.complete.call(this, jqx, text);
        }
    });
    if (newst.needpid !== false) {
        if (newst.data) {
            if (!$.isPlainObject(newst.data)) {
                newst.data = $.parseJSON(newst.data);
            }
            newst.data.partition = SYS.pid;
        } else {
            newst.data = {
                partition: SYS.pid
            };
        }
    }
    if (newst.isjson
        || (newst.contentType || jQuery.ajaxSettings.contentType)
            .indexOf('json') > -1 && $.isPlainObject(newst.data)) {
        newst.contentType = 'application/json;charset=UTF-8';
        newst.data = JSON.stringify(newst.data);
    }
    return $.jqAjax(newst);
};
var DateUtils = SYS.DateUtils = {
    padLeft: function (s, l, c) {
        s = '' + s;
        if (l < s.length)
            return s;
        else
            return Array(l - s.length + 1).join(c || ' ') + s;
    },
    parseValue: function (val, fmt) {
        var today = new Date,
            params = val.split(','),
            d = {
                oy: params[0] || 'y',
                oM: params[1] || 'M',
                od: params[2] || 'd'
            };
        d.y = eval(d.oy.replace('y', today.getFullYear()));
        today.setYear(d.y);
        d.M = eval(d.oM.replace('M', today.getMonth() + 1));
        while (d.M < 1) {
            d.M = d.M + 12;
            d.y--;
        }
        while (d.M > 12) {
            d.M = d.M - 12;
            d.y++;
        }
        today.setYear(d.y);
        if (d.od.indexOf('ld') > -1) {
            today.setMonth(d.M);
            today.setDate(0);
            d.d = eval(d.od.replace('ld', today.getDate()));
        } else {
            d.d = eval(d.od.replace('d', today.getDate()));
        }
        today.setMonth(d.M - 1);
        today.setDate(d.d);
        d.y = today.getFullYear();
        d.M = today.getMonth() + 1;
        d.d = today.getDate();
        d.date = $.formatDate(d.y + '/' + d.M + '/' + d.d, fmt);
        return d;
    },
    formatValue: function (val, fmt) {
        return DateUtils.parseValue(val, fmt).date;
    }
};
$.formatDate = function (date, ptn) {
    if (typeof date !== Date) {
        date = new Date(date);
    }
    ptn = ptn || "yyyy-MM-dd";
    date = date || new Date();
    var dt = {
        // 年份
        "yyyy": date.getFullYear(),
        // 月份
        "MM": date.getMonth() + 1,
        // 日
        "dd": date.getDate(),
        // 小时
        "hh": date.getHours(),
        // 分
        "mm": date.getMinutes(),
        // 秒
        "ss": date.getSeconds(),
        // 季度
        "q": Math.floor((date.getMonth() + 3) / 3),
        // 毫秒
        "SSS": date.getMilliseconds()
    };
    dt.MM = DateUtils.padLeft(dt.MM, 2, '0');
    dt.dd = DateUtils.padLeft(dt.dd, 2, '0');
    dt.hh = DateUtils.padLeft(dt.hh, 2, '0');
    dt.mm = DateUtils.padLeft(dt.mm, 2, '0');
    dt.ss = DateUtils.padLeft(dt.ss, 2, '0');
    for (var key in dt) {
        ptn = ptn.replace(key, dt[key]);
    }
    return ptn;
};

$.fn.mask = function (opt) {
    return this.each(function () {
        var $el = $(this),
            dis = {
                w: $el.outerWidth(),
                h: $el.outerHeight()
            };
        if (opt === undefined) {
            opt = !$el.find('>.icon-spinner.icon-spin').length;
        }
        if (opt == true) {
            $el.find('.mask-loading').remove();
            $('<div class="mask-loading"></div>').appendTo($el).css({
                lineHeight: dis.h + 'px'
            });
        } else {
            $el.find('.mask-loading').remove();
        }
    });
};
$.submit = function (opts) {
    SysUtils.mask($(opts.mask));
    opts = opts || {};
    opts.url = opts.url || '';
    var $form = $('<form>').appendTo('body'), data = {};
    var attr = {
        action: opts.url.indexOf('/') == 0 ? (SYS.ctx + opts.url) : (SYS.path + opts.url),
        method: opts.type || opts.method || 'POST',
        target: opts.target
    };
    if (opts.target) {
        attr.target = opts.target;
    }
    $form.attr(attr);
    if (opts.data) {
        data = SysUtils.j2d(opts.data);
    }
    data.partition = SYS.pid;
    for (var key in data) {
        $form.append($('<input>').attr({
            type: 'hidden',
            name: key,
            value: data[key]
        }));
    }
    $form.submit();
};
