package cn.wolfcode._05_di;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

@Getter
@Setter
@ToString
public class SomeBean {
    private OtherBean otherBean;
}
