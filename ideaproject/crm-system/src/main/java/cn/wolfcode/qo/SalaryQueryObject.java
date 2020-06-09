package cn.wolfcode.qo;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class SalaryQueryObject extends QueryObject {
    private String keyword;
    private Long payoutId = -1L;
}
