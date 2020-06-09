package cn.wolfcode.luowowo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
@ApiModel(value="人",description="用于api接口测试实体模型")
public class Person {


    @ApiModelProperty(value="用户iD",name="id",dataType = "String",required = true)
    private String id;
    @ApiModelProperty(value="用户名称",name="name",dataType = "String",required = true)
    private String name;
    @ApiModelProperty(value="用户年龄",name="age",dataType = "String",required = true)
    private String age;
}
