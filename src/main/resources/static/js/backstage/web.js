//ajax请求
const Web = {
    baseUrl:null,
    innerOptions:null,
    buildUrl:function (url) {
        if (url === undefined) return null;
        if (url.charAt(0) === "/"){
            if (url.charAt(1) === "/"){
                return "http:" + url;
            }
            return Web.baseUrl + url;
        }
        if (url.charAt(0) === "~"){
            if (url.charAt(1) === "/"){
                return url.substring(1);
            }
        }
        return url;
    },
    isSuccess:function(obj){
        return obj.code.charAt(1) === "0";
    },
    setPrototype:function (options) {
        jQuery.extend(true, WebBuilder.prototype, options);
    },
    setDefault:function (options) {
        Web.innerOptions = options;
    },
    setBaseUrl:function (url) {
        Web.baseUrl = url;
    },
    go:function (url) {
        window.location.href = Web.buildUrl(url);
    },
    updateDate:function (data) {
        jQuery.each(data,function (key,value) {
            Web.updateObject(key,value,data);
        });
        return data;
    },
    updateObject:function (key,value,content) {
        if (value === null) content[key] = undefined;
        if (typeof(value) === "object" && Object.prototype.toString.call(value).toLowerCase() === "[object object]" && !value.length){
            jQuery.each(value,function (x,y) {
                Web.updateObject(key+"."+x,y,content);
                if (content[key] === {}){
                    content[key+"."+x] = undefined;
                }else {
                    content[key+"."+x] = value[x];
                }
                value[x] = undefined;
            });
        }
    }
};

function WebBuilder(url,defaultOptions) {
    this.options = {};
    this.innerOptions = {};
    this.defaultOptions = jQuery.extend(true,{
        dataType:"json"
    },Web.innerOptions,defaultOptions);
    this.options.intercepts = [];
    var tempIntercepts  = this.defaultOptions.intercepts || [];
    this.defaultOptions.intercepts = jQuery.isFunction(tempIntercepts)?[tempIntercepts]:tempIntercepts;
    this.defaultOptions.url = Web.buildUrl(url);
}
jQuery.extend(true,WebBuilder.prototype,{
    url:function (url) {
        this.options.url = Web.buildUrl(url);
        return this;
    },
    set:function (options) {
        jQuery.extend(true,this.options,options);
        return this;
    },
    setData:function (data) {
        //string
        if (Object.prototype.toString.call(data) === "[object String]"){
            this.options.data = data;
            return this;
        }
        //json
        Web.updateDate(data);
        this.options.data =data;
        return this;
    },
    setMethod:function (method) {
        this.options.type = method;
        return this;
    },
    setType:function (daraType) {
        this.options.dataType = daraType;
        return this;
    },
    setAsync:function (async) {
        this.options.async = async;
        return this;
    },
    setIntercepts:function (intercepts) {
        this.options.intercepts = jQuery.isFunction(intercepts)?[intercepts]:intercepts;
        return this;
    },
    setSuccess:function (callback) {
        this.options.success = callback;
        return this;
    },
    setError:function (callback) {
        this.options.error = callback;
        return this;
    }
});
jQuery.each(['get','post','execute'],function (i,method) {
    WebBuilder.prototype[method] = function (callback) {
        if (method !== 'execute') this.options.type = method;
        if (jQuery.isFunction(callback)){
            this.options.success = callback;
        }else {
            jQuery.extend(true,this.options,callback);
        }

        var tempOptions = jQuery.extend(true,{},this.defaultOptions,this.options);
        var tempIntercepts = [];
        tempIntercepts = this.defaultOptions.intercepts.concat(this.options.intercepts);
        this.options = {};
        this.options.intercepts = [];

        var tempSuccess = tempOptions.success;
        tempOptions.success = function (request, status, thrown) {
            for (var i in tempIntercepts){
                if (tempIntercepts[i](request, status, thrown,tempOptions)) return;
            }
            tempSuccess(request, status, thrown);
        };

        return jQuery.ajax(tempOptions);
    }
});