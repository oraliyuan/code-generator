package per.oraly.codegenerator.dto;

/**
 * 列信息
 *
 * @author liyuan 2019/03/09 16:38
 */
public class ColumnInfo {

    /**
     * 列名称
     */
    private String name;

    /**
     * 列数据类型
     */
    private String dataType;

    /**
     * 列类型
     */
    private String columnType;

    /**
     * 列默认值
     */
    private String cDefault;

    /**
     * 列注释
     */
    private String comment;

    /**
     * 列大小
     */
    private String size;

    /**
     * 列是否可为空
     */
    private Boolean nullAble;

    private String columnKey;

    /**
     * 是否为自增列
     */
    private Boolean incrementAble;

    // 额外字段

    private String uppper;
    private String attrType;
    private String attrName;
    private String jdbcType;
    private String pkName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getcDefault() {
        return cDefault;
    }

    public void setcDefault(String cDefault) {
        this.cDefault = cDefault;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean getNullAble() {
        return nullAble;
    }

    public void setNullAble(Boolean nullAble) {
        this.nullAble = nullAble;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public Boolean getIncrementAble() {
        return incrementAble;
    }

    public void setIncrementAble(Boolean incrementAble) {
        this.incrementAble = incrementAble;
    }

    public String getUppper() {
        return uppper;
    }

    public void setUppper(String uppper) {
        this.uppper = uppper;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }
}
