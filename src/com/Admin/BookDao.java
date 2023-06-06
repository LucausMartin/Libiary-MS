package com.Admin;

import com.Database.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class BookDao {
    Connection con = DB.getConnection();
    public Vector bookHead() throws Exception{
        String sql = "SELECT * FROM `books`";
        ResultSet rs = DB.executeQuery(con, sql);
        ResultSetMetaData rSMD = rs.getMetaData();
        Vector columnHeads = new Vector();

        for(int i = 1; i <= rSMD.getColumnCount(); i++)
            columnHeads.addElement(rSMD.getColumnName(i));
        return columnHeads;
    }

    public Vector bookRows() throws Exception{
        String sql = "SELECT * FROM `books`";
        ResultSet rs = DB.executeQuery(con, sql);
        ResultSetMetaData rSMD = rs.getMetaData();
        Vector rows = new Vector();

        while(rs.next()){
            rows.addElement(setNextRow(rs, rSMD));
        }
        return rows;
    }
    public Vector setNextRow(ResultSet rs, ResultSetMetaData rSMD) throws SQLException {
        Vector currentRow = new Vector();
        for(int i = 1; i <= rSMD.getColumnCount(); i++){
            currentRow.addElement(rs.getString(i));
        }
        return currentRow;
    }
}
