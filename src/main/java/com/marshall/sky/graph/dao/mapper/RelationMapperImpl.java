package com.marshall.sky.graph.dao.mapper;

import com.marshall.sky.graph.model.Relation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RelationMapperImpl implements RelationMapper {

    @Override
    public int insert(String sql) {
        return execute(sql);
    }

    @Override
    public int remove(String sql) {
        return execute(sql);
    }

    @Override
    public List<Relation> select(String sql) {
        List<Relation> relations = new ArrayList<>();
        try (final Connection connection = SqlPoolFactory.getPool().getConnection();
             final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Relation relation = new Relation();
                relation.setLeftId(resultSet.getLong("left_id"));
                relation.setRightId(resultSet.getLong("right_id"));
                relation.setCreateTime(resultSet.getLong("create_time"));
                relation.setUpdateTime(resultSet.getDate("update_time"));
                relation.setState(resultSet.getInt("state"));
                relation.setExtParams(resultSet.getString("ext_params"));
                relations.add(relation);
            }
            return relations;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int execute(String sql) {
        try (final Connection connection = SqlPoolFactory.getPool().getConnection();
             final Statement statement = connection.createStatement()) {
            final boolean execute = statement.execute(sql);
            return execute ? 1 : 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
