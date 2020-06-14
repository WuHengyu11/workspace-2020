package cn.wolfcode.elastic.domain;

import lombok.Data;

@Data
public class FileCustom {
    //唯一标识
    private Long id;
    //文件名
    private String name;
    //文件类型
    private String type;
    //文件内容
    private String content;
    //是否已备份
    private Boolean backedUp = false;
    public FileCustom(){}
    public FileCustom(Long id, String name, String type, String content){
        this.id = id;
        this.name = name;
        this.type = type;
        this.content = content;
    }
}