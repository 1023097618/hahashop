package com.mall.common;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(String[].class)
public class StringArrayTypeHandler extends BaseTypeHandler<String[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
        String joinedString = String.join(",", parameter);  // 将 String[] 转换为逗号分隔的字符串
        ps.setString(i, joinedString);
    }

    @Override
    public String[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String result = rs.getString(columnName);
        return result != null ? result.split(",") : null;  // 将逗号分隔的字符串转换回 String[]
    }

    @Override
    public String[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String result = rs.getString(columnIndex);
        return result != null ? result.split(",") : null;
    }

    @Override
    public String[] getNullableResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
        String result = cs.getString(columnIndex);
        return result != null ? result.split(",") : null;
    }
}