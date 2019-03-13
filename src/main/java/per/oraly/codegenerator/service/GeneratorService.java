package per.oraly.codegenerator.service;

import per.oraly.codegenerator.dto.ColumnInfo;
import per.oraly.codegenerator.dto.DDDReqParam;
import per.oraly.codegenerator.dto.TableInfo;

import java.util.List;

/**
 * description
 *
 * @author liyuan 2019/03/09 16:35
 */
public interface GeneratorService {

    /**
     * 生成DDD 代码
     */
    byte[] generatorDDDCode(DDDReqParam dddReqParam);

    /**
     * 生成MVC 代码
     */
    byte[] generatorMVCCode();

    /**
     * 获取表展示信息
     * @return
     */
    List<TableInfo> getTableListInfo();

    /**
     * 获取表信息
     * @return
     */
    TableInfo getTableInfo(String tableName);

    /**
     * 获取列信息
     * @return
     */
    List<ColumnInfo> getColumnInfo(String tableName);

}
