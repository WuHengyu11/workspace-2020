package cn.wolfcode.elastic.mapper;

import cn.wolfcode.elastic.domain.FileCustom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FileCustomMapper {
    @Select("select * from t_file_custom where backedUp = 0")
    List<FileCustom> selectAll();
    @Select("select * from t_file_custom where backedUp = 0 and type=#{fileType}")
    List<FileCustom> selecByType(String fileType);
    @Update("update t_file_custom set backedUp = #{state} where id = #{id}")
    int changeState(@Param("id") Long id, @Param("state")int state);
    @Select("select * from t_file_custom where backedUp = 0 limit 0,#{size}")
    List<FileCustom> fetchData(int size);
}