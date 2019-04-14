package mybean.data;

/**
 * Created by lei02 on 2019/4/12.
 */
public class Bean {
    private String[] columnName;    //存放别名
    private String[][] tableRecord;    //存放查询的数据，这是要把数据库中的数据全部读取呀

    public Bean() {
        columnName = new String[1];
        tableRecord = new String[1][1];
    }

    public String[] getColumnName() {
        return columnName;
    }

    public void setColumnName(String[] columnName) {
        this.columnName = columnName;
    }

    public String[][] getTableRecord() {
        return tableRecord;
    }

    public void setTableReord(String[][] tableRecord) {
        this.tableRecord = tableRecord;
    }
}
