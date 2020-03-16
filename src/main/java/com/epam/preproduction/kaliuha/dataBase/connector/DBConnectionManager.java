package com.epam.preproduction.kaliuha.dataBase.connector;

import com.epam.preproduction.kaliuha.dataBase.constant.WebShopDBMessage;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Objects;

public class DBConnectionManager {

    private static final Logger LOG = Logger.getLogger(DBConnectionManager.class);
    private static final String CONTEXT = "java:/comp/env";
    private static final String WEB_SHOP_DB = "jdbc/WebShopDB";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private DataSource ds;
    private ThreadLocal<Connection> connection;

    public DBConnectionManager(ThreadLocal<Connection> connection) {
        this.connection = connection;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup(CONTEXT);
            ds = (DataSource) envContext.lookup(WEB_SHOP_DB);
            LOG.trace("Data source ==> " + ds);
        } catch (NamingException ex) {
            LOG.error(WebShopDBMessage.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
        }
    }

    public Connection getConnection() {
        Connection con = connection.get();
        if (Objects.isNull(con)) {
            con = createConnection();
        }
        return con;
    }

    public Connection createConnection() {
        Connection con = null;
        try {
            Class.forName(JDBC_DRIVER);
            con = ds.getConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            LOG.error(WebShopDBMessage.ERR_CANNOT_OBTAIN_CONNECTION, ex);
        }
        return con;
    }

    public void close(Connection con) {
        if (con != null) {
            try {
                con.close();
                connection.remove();
            } catch (SQLException ex) {
                LOG.error(WebShopDBMessage.ERR_CANNOT_CLOSE_CONNECTION, ex);
            }
        }
    }

    public void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOG.error(WebShopDBMessage.ERR_TRANSACTION_FAIL, ex);
            }
        }
    }

    public void commit(Connection con) {
        if (con != null) {
            try {
                con.commit();
            } catch (SQLException ex) {
                LOG.error(WebShopDBMessage.TRANSACTION_SUCCESSFUL, ex);
            }
        }
    }

    public void rollback(Connection con, Savepoint savepoint) {
        if (con != null) {
            try {
                con.rollback(savepoint);
            } catch (SQLException ex) {
                LOG.error(WebShopDBMessage.ERR_TRANSACTION_FAIL, ex);
            }
        }
    }
}
