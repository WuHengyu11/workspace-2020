package cn.wolfcode.luowowo.search.vo;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.domain.Travel;
import cn.wolfcode.luowowo.domain.UserInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class SearchResult {

    private Long total=0L;
    private List<Strategy> strategys = new ArrayList();
    private List<Travel> travels = new ArrayList();
    private List<UserInfo> users = new ArrayList();
    private List<Destination> dests = new ArrayList();


}
