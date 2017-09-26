//单页配置
const App = new Vue({
    el:"#wrapper",
    router:AppRouter,
    data:{
        baseUrl:baseUrl,
        /*user: sessionUser,*/
        menu:AppMenu,
        alwaysTrue:true
    },
    methods:{
        logout:function () {
           /* new WebBuilder("/public/logout").post(function () {
                debugger
                //Web.go("/backstage/login");
                window.localtion.href="/backstage/login";
            });*/
           var postUrl="/public/logout";
            $.ajax({
                type:"post",
                url:postUrl,
                success:function(response){
                    debugger
                    Web.go("/backstage/login");
                },
                error:function(e){

                }
            })
        }
    }
});