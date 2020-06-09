package cn.wolfcode.luowowo.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Getter
@Setter
public class BaseDomain implements Serializable {
    @Id
    protected String id;
}
