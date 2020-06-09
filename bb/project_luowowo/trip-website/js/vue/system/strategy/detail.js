var user = getUserInfo();
var vue = new Vue({
    el:"#app",
    data:{
        strategy:{},
        page:{},
        vo:{},
        sids:[]
    },
    methods:{
        strategyThumbup:function(){
            ajaxPost("/strategies/strategyThumbup",{sid:vue.strategy.id}, function (data) {
                var map =  data.data;
                if(map.ret){
                    popup("顶成功啦");
                }else{
                    popup("今天你已经定过了");
                }
                vue.queryStatisVo(vue.strategy.id);
            })
        },
        favor:function(){
            ajaxPost("/strategies/favor",{sid:vue.strategy.id}, function (data) {
                var map =  data.data;
                if(map.ret){
                    popup("收藏成功");
                }else{
                    popup("已取消收藏");
                }
                vue.queryStatisVo(vue.strategy.id);
                if(user){
                    vue.queryUserFavor(vue.strategy.id,user.id);
                }

            })
        },
        contentFocus:function(){
            $("#content").focus();
        },
        commentThumb:function(commentId){
            var page = $("#pagination").find("a.active").html()||1;
            ajaxPost("/strategies/commentThumb",{cid:commentId,sid:getParams().id}, function (data) {
                vue.commentPage(page,getParams().id);
            })
        },
        mouseover:function(even){
            $(even.currentTarget).find(".rep-del").css("display", "block");
        },
        mouseout:function(even){
            $(even.currentTarget).find(".rep-del").css("display", "none");
        },
        commentPage:function (page,strategyId) {//分页
            strategyId = strategyId || vue.strategy.id;
            ajaxGet("/strategies/comments", {currentPage:page, strategyId:strategyId}, function(data){
                vue.page = data.data;
                buildPage(vue.page.number, vue.page.totalPages,vue.commentPage);
            })
        },
        addComment:function(){ //添加评论
            var param = {}
            param.strategyId = vue.strategy.id;
            param.strategyTitle = vue.strategy.title;

            var content = $("#content").val();
            if(!content){
                popup("评论内容必填");
                return;
            }
            param.content = content;
            $("#content").val('');

            ajaxPost("/strategies/addComment",param, function (data) {
                vue.queryStatisVo(param.strategyId);
                vue.commentPage(1,param.strategyId);
            })
        },
        queryStatisVo:function (sid) {
            //统计数据
            ajaxGet("/strategies/statisVo",{sid:sid}, function (data) {
                vue.vo =data.data;
            })

        },
        queryUserFavor:function (sid,userId) {
            ajaxGet("/users/strategies/favor",{sid:sid, userId:userId}, function (data) {
                vue.sids = data.data;
            })
        }
    },
    filters:{
        dateFormat:function(date){
            return dateFormat(date, "YYYY-MM-DD HH:mm:ss")
        }
    },
    mounted:function () {
        var param = getParams();

        //攻略明细
        ajaxGet("/strategies/detail",{id:param.id}, function (data) {
            vue.strategy = data.data;
        })

        //统计数据
        this.queryStatisVo(param.id);

        //用户收藏攻略id集合
        if(user){
            this.queryUserFavor(param.id,user.id);
        }

        //攻略评论分页
        this.commentPage(1, param.id);
    }
});

