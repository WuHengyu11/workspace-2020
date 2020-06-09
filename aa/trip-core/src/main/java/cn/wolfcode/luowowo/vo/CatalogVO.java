package cn.wolfcode.luowowo.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class CatalogVO {
    private String destName;

    private List<Map<String,Object>> mapList = new ArrayList<>();
}
