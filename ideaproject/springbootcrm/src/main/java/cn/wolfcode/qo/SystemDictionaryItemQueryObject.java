package cn.wolfcode.qo;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class SystemDictionaryItemQueryObject extends QueryObject{
    private  Long parentId = -1L;//目录

}
