package per.oraly.codegenerator.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import per.oraly.codegenerator.dto.ColumnInfo;
import per.oraly.codegenerator.dto.DDDReqParam;
import per.oraly.codegenerator.dto.TableInfo;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * description
 *
 * @author liyuan 2019/03/09 16:40
 */
public class GeneratorUtil {

    public static final String DEFAULT_CHARSET = "UTF-8";

    public static final char UNDERLINE = '_';

    public static final char HORIZONTALLINE = '-';

    public static void generatorCode(DDDReqParam info, TableInfo tableInfo,
                                     List<ColumnInfo> columnInfos, ZipOutputStream zip) {

        // 获取配置信息
        Configuration config = getConfig();
        boolean hasBigDecimal = false;
        boolean hasDate = false;

        // 处理各个字段
        String className = toCamelStyle(tableInfo.getTableName(), info.getTablePrefix(), false);
        tableInfo.setClassName(className);
        tableInfo.setClassNameLower(StringUtils.uncapitalize(className));

        // 列信息
        for (ColumnInfo column : columnInfos) {
            column.setUppper(StringUtils.upperCase(column.getName()).replaceAll("_", ""));
            column.setAttrType(config.getString(column.getDataType(), "ErrorType"));
            column.setAttrName(toCamelStyle(column.getName(),null,true));
            column.setJdbcType(StringUtils.upperCase(config.getString(column.getAttrType())));

            if (!hasBigDecimal && column.getAttrType().equalsIgnoreCase("BigDecimal")) {
                hasBigDecimal = true;
            }
            if (!hasDate && column.getAttrType().equalsIgnoreCase("Date")) {
                hasDate = true;
            }
            if ("PRI".equalsIgnoreCase(column.getColumnKey())) {
               column.setPkName(column.getAttrName());
            }
        }

        // 设置velocity 资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        // 封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("author", info.getAuthor());
        map.put("packageName", info.getPackageName());
        map.put("comments", tableInfo.getComments());
        map.put("className", tableInfo.getClassName());
        map.put("classNameLower", tableInfo.getClassNameLower());
        map.put("routeName", camelToLineStyle(tableInfo.getClassName()));
        map.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        map.put("tableName", tableInfo.getTableName());
        map.put("columns", columnInfos);
        VelocityContext context = new VelocityContext(map);

        // 生成项目框架
        if (info.isFrame()) {
            // todo
        }

        // 获取并渲染模板
        List<String> templates = getTemplates();
        for (String template : templates) {
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, GeneratorUtil.DEFAULT_CHARSET);
            tpl.merge(context, sw);

            try {
                zip.putNextEntry(new ZipEntry(getFileName(template, tableInfo.getClassName(), info.getPackageName())));
                IOUtils.write(sw.toString(), zip, GeneratorUtil.DEFAULT_CHARSET);
                IOUtils.closeQuietly();
                zip.closeEntry();
            } catch (IOException e) {
                throw new RuntimeException("模板渲染失败,表明"+tableInfo.getTableName(), e);
            }
        }

        return;
    }

    private static List<String> getTemplates() {
        List<String> templates = new ArrayList<>();
        templates.add("template/Controller.java.vm");
        templates.add("template/Entity.java.vm");
        templates.add("template/Mapper.java.vm");
        templates.add("template/Mapper.xml.vm");
        templates.add("template/Repository.java.vm");
        templates.add("template/RepositoryImpl.java.vm");
        templates.add("template/Service.java.vm");
        templates.add("template/ServiceImpl.java.vm");
        return templates;
    }

    private static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new RuntimeException("获取配置文件失败，", e);
        }
    }

    public static String toCamelStyle(String source, String prefix, boolean lowerStart) {
        if (prefix != null) {
            source = StringUtils.removeStart(source, prefix);
        }
        source = WordUtils.capitalizeFully(source, new char[]{'_'})
                        .replaceAll("_", "");
        if (lowerStart) {
            return StringUtils.uncapitalize(source);
        }
        return source;
    }

    public static String camelToLineStyle(String source) {
        if (StringUtils.isEmpty(source)) {
            return "";
        }
        int len = source.length();
        StringBuilder sb = new StringBuilder(len);
        sb.append(Character.toLowerCase(source.charAt(0)));
        for (int i = 1; i < len; i++) {
            char c = source.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(HORIZONTALLINE).append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static String getFileName(String template, String className, String packageName) {

        String javaPackagePath = "main" + File.separator + "java" + File.separator;

        if (StringUtils.isNotBlank(packageName)) {
            javaPackagePath += packageName.replace(".", File.separator) + File.separator;
        }

        String resourcePackagePath = "main" + File.separator + "resources" + File.separator;

        if (template.contains("Entity.java.vm")) {
            return javaPackagePath + "domain" + File.separator + "entity" + File.separator + className + ".java";
        }

        if (template.contains("Controller.java.vm")) {
            return javaPackagePath + "api" + File.separator + "controller" + File.separator
                    + className + "Controller.java";
        }

        if (template.contains("Mapper.java.vm")) {
            return javaPackagePath + "infra" + File.separator + "mapper" + File.separator + className + "Mapper.java";
        }

        if (template.contains("Repository.java.vm")) {
            return javaPackagePath + "domain" + File.separator + "repository" + File.separator + className
                    + "Repository.java";
        }

        if (template.contains("RepositoryImpl.java.vm")) {
            return javaPackagePath + "infra" + File.separator + "repository" + File.separator + "impl" + File.separator
                    + className + "RepositoryImpl.java";
        }

        if (template.contains("Service.java.vm")) {
            return javaPackagePath + "app" + File.separator + "service" + File.separator + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm")) {
            return javaPackagePath + "app" + File.separator + "service" + File.separator + "impl" + File.separator
                    + className + "ServiceImpl.java";
        }

        if (template.contains("Mapper.xml.vm")) {
            return resourcePackagePath + "mapper" + File.separator + className + "Mapper.xml";
        }

        return null;
    }
}
