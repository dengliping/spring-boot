//todo 将menu数据存入数据库
const AppMenu = [
    {
        menu:"Home",
        name:"首页",
        icon:"fa-dashboard",
        url:"/",
        staticUrl:"/model/index.vue"
    },
    {
        menu:"recruit",
        name:"招聘管理",
        icon:"fa-th-large",
        url:"/bar",
        staticUrl:"/model/xxxx/recruit.vue"
        /*list:[
            {menu:"ac",name:"招聘信息",url:"/bar",staticUrl:"/model/xxxx/recruit.vue"}
        ]*/
    },
    {
        menu:"news",
        name:"新闻管理",
        icon:"fa-pie-chart",
        url:"/car",
        staticUrl:"/model/xxxx/news.vue"
        /*list:[
            {menu:"sa",name:"新闻信息",url:"/bar",staticUrl:"/model/xxxx/news.vue"}
        ]*/
    },
    {
        menu:"parter",
        name:"商务合作",
        icon:"fa-pie-chart",
        url:"/parter",
        staticUrl:"/model/pc/parter.vue"
    },/*,
    {
        menu:"System",
        name:"系统管理",
        icon:"fa-cogs",
        list:[
            {menu:"User",name:"用户管理",url:"/system/user",staticUrl:"/model/system/user.vue"},
            {menu:"Role",name:"角色管理",url:"/system/role",staticUrl:"/model/system/role.vue"},
            // {name:"权限菜单",url:"#",staticUrl:"/model/system/recruit.vue"},
            {menu:"Permission",name:"权限管理",url:"/system/permission",staticUrl:"/model/system/permission.vue"}
        ]
    }*/
];

//定义路由
const MenuRoutes = {};

//菜单工具类
const MenuUtils = {
    pushMenuRoute: function (key, item) {
        MenuRoutes[key] = item;
    },
    pushMenuRoutes: function (menus) {
        for (var i = 0; i < menus.length; i++) {
            var item = menus[i];
            if (item.list !== undefined) {
                MenuUtils.pushMenuRoutes(item.list);
            }
            if (item.url !== undefined) {
                MenuUtils.pushMenuRoute(item.menu, item)
            }
        }
    }
};

//通过菜单添加路由
MenuUtils.pushMenuRoutes(AppMenu);