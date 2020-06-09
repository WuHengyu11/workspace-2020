package cn.wolfcode.luowowo.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CatalogVO {
    //目的地
    private String destName;
    //目的地下所有攻略分类对象
    private List<Map<String,Object>> mapList = new ArrayList<>();
}
