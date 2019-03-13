package per.oraly.codegenerator.dto;

import java.util.Arrays;

/**
 * description
 *
 * @author liyuan 2019/03/09 16:31
 */
public class DDDReqParam {

    private String projectName;

    private String author;

    private String tablePrefix;

    private String packageName;

    private boolean frame = false;

    private String[] tableList;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public boolean isFrame() {
        return frame;
    }

    public void setFrame(boolean frame) {
        this.frame = frame;
    }

    public String[] getTableList() {
        return tableList;
    }

    public void setTableList(String[] tableList) {
        this.tableList = tableList;
    }

    @Override
    public String toString() {
        return "DDDReqParam{" +
                "projectName='" + projectName + '\'' +
                ", author='" + author + '\'' +
                ", tablePrefix='" + tablePrefix + '\'' +
                ", packageName='" + packageName + '\'' +
                ", frame=" + frame +
                ", tableList=" + Arrays.toString(tableList) +
                '}';
    }
}
