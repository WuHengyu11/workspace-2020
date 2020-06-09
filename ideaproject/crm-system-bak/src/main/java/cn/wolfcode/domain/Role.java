package cn.wolfcode.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class Role {
    private Long id;
    private String name;
    private String sn;
    private List<Permission> permissions = new ArrayList<>();//角色拥有的权限的集合
}