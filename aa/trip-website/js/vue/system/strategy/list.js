var vue = new Vue({
    el:"#app",
    data:{
        page:{},
        toasts:[]

    },
    methods:{
        doPage:function(page){
            var param = getParams();
            ajaxGet("/strategies/query",{destId:param.destId, currentPage:page}, function (data) {
                vue.page = data.data;
                vue.page.number = page;   //指定当前页
                buildPage(vue.page.number, vue.page.totalPages,vue.doPage);
            })
        }
    },
    mounted:function () {
        var param = getParams();
        //吐司
        ajaxGet("/destinations/toasts",{destId:param.destId}, function (data) {
            var list = data.data;
            vue.toasts = list;
        })
        //攻略分页
        ajaxGet("/strategies/query",{destId:param.destId}, function (data) {
            vue.page =data.data;
            //分页
            buildPage(vue.page.number, vue.page.totalPages,vue.doPage);
        })
    }
});

