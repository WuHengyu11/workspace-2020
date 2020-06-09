package cn.wolfcode._07_properties;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MyDataResource {
    private String url;
    private String driverClassName;
    private String username;
    private String password;
}
