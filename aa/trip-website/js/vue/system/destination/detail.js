var vue = new Vue({
    el:"#app",
    data:{
        toasts:[],  //吐司
        dest:{},  //目的地
        catalogs:[], //概况中攻略分类
        strategies:[],  //点击量前3
        page:{}  //游记分页
    },
    methods:{
        commPage:function (page) {
            var param = getParams();
            var p = $("#travelForm").serialize() + "&destId="+param.id + "&currentPage=" + page;
            //游记分页
            ajaxGet("/travel/query?"+p,{}, function (data) {
                vue.page = data.data;
                buildPage(vue.page.number, vue.page.totalPages, vue.doPage)
            })
        },
        doPage:function(page){
            this.commPage(page);
        },
        conditionChange:function(){
            this.commPage(1);
        }
    },
    mounted:function () {
        var param = getParams();

        //吐司
        ajaxGet("/destinations/toasts",{destId:param.id}, function (data) {
            var list = data.data;
            vue.dest = list.pop();
            vue.toasts = list;
        })

        //目的地
        ajaxGet("/destinations/detail",{id:param.id}, function (data) {
            vue.dest = data.data
        })

        //目的下所有攻略分类
        ajaxGet("/destinations/catalogs",{destId:param.id}, function (data) {
            vue.catalogs = data.data;
        })

        //点击量前3的攻略文章
        ajaxGet("/destinations/strategies/viewnumTop3",{destId:param.id}, function (data) {
            vue.strategies = data.data;
        })
        //游记分页
        ajaxGet("/travels/query",{destId:param.id}, function (data) {
            vue.page = data.data;
            buildPage(vue.page.number, vue.page.totalPages, vue.doPage)
        })
    }
});

