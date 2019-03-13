package per.oraly.codegenerator.dto;

import java.util.Date;

/**
 * 表信息
 *
 * @author liyuan 2019/03/09 16:38
 */
public class TableInfo {

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表注释
     */
    private String comments;

    /**
     * 存储引擎
     */
    private String engine;

    /**
     * 创建时间
     */
    private Date createTime;

    // -----------------------
    // 额外字段 （非数据库查询字段）
    // -----------------------


    private String className;
    private String classNameLower;
    private String routeName;


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassNameLower() {
        return classNameLower;
    }

    public void setClassNameLower(String classNameLower) {
        this.classNameLower = classNameLower;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }
}
