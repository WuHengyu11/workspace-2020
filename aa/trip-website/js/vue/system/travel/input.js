var vue = new Vue({
    el:"#app",
    data:{
        tv:{destId:''},
        dests:{},
        content:''
    },
    filters:{
        dateFormat:function(date){
            return dateFormat(date, "YYYY-MM-DD")
        }
    },
    methods:{
        chosePic:function () {
            $("#coverBtn").click();
        },
        saveOrUpdate:function (state) {
            $("#state").val(state);

            var param = $("#editForm").serialize() ;

            ajaxPost("/travel/saveOrUpdate", param, function (data) {


                window.location.href = "/views/travel/detail.html?id=" + data.data;



            })
        }
    },
    mounted:function () {
        var id = getParams().id;

        //游记编辑
        ajaxGet("/travel/input",{id:id}, function (data) {
            var map = data.data;

            if(map.tv){
                vue.tv = map.tv;
                ue.setContent( map.tv.content);
            }
            vue.dests = map.dests;
        })
    }
});

