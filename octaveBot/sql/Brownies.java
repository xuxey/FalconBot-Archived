package com.xuxe.octaveBot.sql;

import com.xuxe.octaveBot.main.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Brownies
{
    private Connection connection;
    public Brownies()
    {
        connection = new Main().getConnection();
    }

    public void addBrownie(String id, int count)
    {
        try {

            if (!connection.prepareStatement("select trivpoints from users where uid='" + id+"';").executeQuery().isBeforeFirst()) {
                //System.out.println("create");
                PreparedStatement statement = connection.prepareStatement("insert into users(uid,trivpoints) values('" + id + "'," + count + ");");
                statement.execute();
                statement.close();
            } else {
                //System.out.println("increment");
                PreparedStatement statement = connection.prepareStatement("update users set trivpoints=trivpoints+" + count + " where uid = '" + id + "';");
                statement.execute();
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //"update users set trivpoints=trivpoints+"+count+" where uid = "+id+";"
    }
    public void removeBrownie(String id, int count)
    {
        try {

            if (!connection.prepareStatement("select * from users where uid=" + id).executeQuery().first()) {
                //System.out.println("create");
                PreparedStatement statement = connection.prepareStatement("insert into users(uid,trivpoints) values('" + id + "'," + count + ");");
                statement.execute();
            } else {
                //System.out.println("increment");
                PreparedStatement statement = connection.prepareStatement("update users set trivpoints=trivpoints-" + count + " where uid = '" + id + "';");
                statement.execute();
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //"update users set trivpoints=trivpoints-"+count+" where uid = "+id+";"
    }
    public int countBrownies(String id)
    {
        try {
            PreparedStatement statement = connection.prepareStatement("select trivpoints from users where uid = '" + id + "';");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first())
                return resultSet.getInt("trivpoints");
            resultSet.close();
            statement.close();
        }
        catch (SQLException sql)
        {
            sql.printStackTrace();
            return -1;
        }
        return 0;
    }
    public void setBrownie(String id, int count)
    {
        //owner only.
        try {

            if (connection.prepareStatement("select uid from users where uid='" + id+"'").executeQuery()==null) {
                //System.out.println("create");
                PreparedStatement statement = connection.prepareStatement("insert into users(uid,trivpoints) values('" + id + "'," + count + ");");
                statement.execute();
                statement.close();
            } else {
                //System.out.println("increment");
                PreparedStatement statement = connection.prepareStatement("update users set trivpoints=" + count + " where uid = '" + id + "';");
                statement.execute();
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getTopBrownies(int count) throws SQLException {
        PreparedStatement statement = null;
        try
        {
            statement = connection.prepareStatement("select * from users order by trivpoints DESC LIMIT "+count+";");
            return statement.executeQuery();
           /*while (resultSet.next())
            {
                System.out.println(resultSet.getString("uid")+"~~~~~~~~~"+resultSet.getInt("trivpoints"));
            }*/
        }
        catch (Exception e)
        {
            return null;
        }
        finally {
            //statement.close();
        }
    }
}
