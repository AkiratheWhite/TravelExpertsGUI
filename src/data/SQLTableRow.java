package data;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class SQLTableRow {

    public Map<String, Data> row;
    public static Map <String, Class> TYPE;

    static
    {
        TYPE = new HashMap<String, Class>();

        TYPE.put("INT", Integer.class);
        TYPE.put("INTEGER", Integer.class);
        TYPE.put("TINYINT", Byte.class);
        TYPE.put("SMALLINT", Short.class);
        TYPE.put("BIGINT", Long.class);
        TYPE.put("REAL", Float.class);
        TYPE.put("FLOAT", Double.class);
        TYPE.put("DOUBLE", Double.class);
        TYPE.put("DECIMAL", BigDecimal.class);
        TYPE.put("NUMERIC", BigDecimal.class);
        TYPE.put("BOOLEAN", Boolean.class);
        TYPE.put("CHAR", String.class);
        TYPE.put("VARCHAR", String.class);
        TYPE.put("LONGVARCHAR", String.class);
        TYPE.put("DATE", Date.class);
        TYPE.put("TIME", Time.class);
        // ...
    }

    public SQLTableRow() {
        row = new HashMap<String, Data>();
    }

    public void Add (String columnName, Object data, String sqlType)
    {
        Data currentData = new Data(data, SQLTableRow.TYPE.get(sqlType));
        this.row.put(columnName, currentData);
    }

    public static void CreateTable(ResultSet result, List<SQLTableRow> Table) throws SQLException {
        if (result == null) return;

        ResultSetMetaData metaData = result.getMetaData();
        int NumOfCol = metaData.getColumnCount();

        while (result.next())
        {
            SQLTableRow row = new SQLTableRow();

            for(int i = 1; i <= NumOfCol; i++)
            {
                row.Add(metaData.getColumnName(i), result.getObject(i), metaData.getColumnTypeName(i));
            }
            Table.add(row);
        }
    }
}

