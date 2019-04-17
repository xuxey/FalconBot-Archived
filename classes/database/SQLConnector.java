package com.xuxe.octaveBot.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnector
{
    public Connection connect()
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Falcon", "", "");
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
