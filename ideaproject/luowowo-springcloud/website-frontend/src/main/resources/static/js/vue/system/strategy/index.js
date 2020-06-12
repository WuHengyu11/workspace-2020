var vue = new Vue({
    el:"#app",
    data:{
        page:{},
        themes:{}
    },
    methods:{
        themeSelect:function(themeId, event){
            $("._j_tag").removeClass("on")
            $(event.currentTarget).addClass("on");
            vue.doPage(1);
        },
        doPage:function(page){
            var themeId = $("._j_tag.on").data("tid");
            ajaxGet("/strategies/query",{themeId:themeId, currentPage:page}, function (data) {
                vue.page = data.data;
                vue.page.number = page;

                buildPage(vue.page.number, vue.page.totalPages,vue.doPage);
            })
        }
    },
    mounted:function () {
        var param = getParams();
        //攻略主题
        ajaxGet("/strategies/themes",{}, function (data) {
            vue.themes = data.data;
        })

        //攻略分页列表
        ajaxGet("/strategies/query",{}, function (data) {
            vue.page = data.data;
            //分页
            buildPage(vue.page.number, vue.page.totalPages,vue.doPage);
        })

    }
});

