package cn.wolfcode.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色
 */
@AllArgsConstructor@NoArgsConstructor@ToString@Setter@Getter
public class Role {
    private Long id;

    private String name;
    //角色编号
    private String sn;

    private List<Permission> permissions = new ArrayList<>();

}