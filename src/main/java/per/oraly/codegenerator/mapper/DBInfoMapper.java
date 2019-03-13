package per.oraly.codegenerator.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import per.oraly.codegenerator.dto.ColumnInfo;
import per.oraly.codegenerator.dto.TableInfo;

import java.util.List;

/**
 * description
 *
 * @author liyuan 2019/03/09 16:39
 */
@Mapper
public interface DBInfoMapper {

    /**
     * 获取表信息
     * @param tableName 不传这个参数表示查询列表信息
     * @return
     */
    List<TableInfo> getTableInfo(@Param("tableName") String tableName);

    /**
     * 获取某张表的列信息
     * @param tableName
     * @return
     */
    List<ColumnInfo> getColumnInfo(@Param("tableName") String tableName);

}
