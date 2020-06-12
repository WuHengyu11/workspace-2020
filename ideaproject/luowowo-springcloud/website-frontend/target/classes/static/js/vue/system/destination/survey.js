var vue = new Vue({
    el:"#app",
    data:{
        toasts:[],
        dest:{},
        catalogs:[],
        catalog:'',
        strategy:{}
    },
    methods:{
    },
    mounted:function () {
        var param = getParams();
        //吐司
        ajaxGet("/destinations/toasts",{destId:param.destId}, function (data) {
            var list = data.data;
            vue.dest = list.pop();
            vue.toasts = list;
        })
        //概况
        ajaxGet("/destinations/catalogs",{destId:param.destId}, function (data) {
            //[{攻略分类1}, {攻略分类2},{攻略分类3}]
            vue.catalogs = data.data;
            $.each(vue.catalogs, function(index, item){
                if(item.id == param.catalogId){
                    vue.catalog = item;  //选中攻略分类
                    vue.strategy = item.strategies[0]
                    //攻略分类下所有攻略文章第一篇, 需要在页面显示文章内容
                }
            })
        })
    }
});

