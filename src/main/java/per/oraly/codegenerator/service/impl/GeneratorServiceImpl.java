package per.oraly.codegenerator.service.impl;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.oraly.codegenerator.dto.ColumnInfo;
import per.oraly.codegenerator.dto.DDDReqParam;
import per.oraly.codegenerator.dto.TableInfo;
import per.oraly.codegenerator.mapper.DBInfoMapper;
import per.oraly.codegenerator.service.GeneratorService;
import per.oraly.codegenerator.util.GeneratorUtil;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * description
 *
 * @author liyuan 2019/03/09 17:25
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Autowired
    private DBInfoMapper dbInfoMapper;

    @Override
    public byte[] generatorDDDCode(DDDReqParam dddReqParam) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : dddReqParam.getTableList()) {
            TableInfo tableInfo = this.getTableInfo(tableName);
            List<ColumnInfo> columnInfos = this.getColumnInfo(tableName);
            GeneratorUtil.generatorCode(dddReqParam, tableInfo, columnInfos, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    @Override
    public byte[] generatorMVCCode() {
        return null;
    }

    @Override
    public List<TableInfo> getTableListInfo() {
        return dbInfoMapper.getTableInfo(null);
    }

    @Override
    public TableInfo getTableInfo(String tableName) {
        return dbInfoMapper.getTableInfo(tableName).get(0);
    }

    @Override
    public List<ColumnInfo> getColumnInfo(String tableName) {
        return dbInfoMapper.getColumnInfo(tableName);
    }
}
