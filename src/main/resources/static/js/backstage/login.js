const loginApp = new Vue({
    el: '#login-block',
    data: {
        baseUrl:baseUrl,
        username: null,
        password:null
    },
    methods:{
        login:function () {
            var now=new Date();
            var time = now.getTime();
            var MD5Password = hex_md5(this.password+time);
            new WebBuilder("/public/login")
                .setIntercepts(defaultIntercept)
                .setData({
                    name:this.username,
                    password:MD5Password,
                    time:time
                })
                .post(function (data) {
                    Web.go("/backstage/index");
                });
        },
        show:function(e){
            //按下回车键进行搜索
            var self = this;
            e = e || event;
            //    当按下回车键，执行我们的代码
            if(e.keyCode == 13){
                self.login();
            }
        }
    }
});
/*
document.onkeydown=function(e){
    e = e || event;
    //    当按下回车键，执行我们的代码
    if(e.keyCode == 13){
        self.login();
    }
}*/
