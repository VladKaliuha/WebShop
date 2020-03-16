package com.epam.preproduction.kaliuha.dataBase.transaction;

import com.epam.preproduction.kaliuha.dataBase.connector.DBConnectionManager;
import com.epam.preproduction.kaliuha.dataBase.constant.WebShopDBMessage;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Savepoint;
import java.util.function.Supplier;

public class TransactionManagerSql implements TransactionManager {

    private static final Logger LOG = Logger.getLogger(TransactionManagerSql.class);
    private static final String SAVEPOINT = "Start transaction";

    private DBConnectionManager connectionManager;

    public TransactionManagerSql(DBConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public <T> T doTransaction(Supplier<T> action) {
        Connection con = connectionManager.createConnection();
        Savepoint savepoint = null;
        try {
            LOG.info(WebShopDBMessage.TRANSACTION_START);
            savepoint = con.setSavepoint(SAVEPOINT);
            T result = action.get();
            connectionManager.commit(con);
            LOG.info(WebShopDBMessage.TRANSACTION_SUCCESSFUL);
            return result;
        } catch (Exception ex) {
            LOG.warn(WebShopDBMessage.ERR_TRANSACTION_FAIL);
            connectionManager.rollback(con, savepoint);
        } finally {
            connectionManager.close(con);
        }
        return null;
    }

    @Override
    public void doTransaction(Runnable action) {
        Connection con = connectionManager.createConnection();
        Savepoint savepoint = null;
        try {
            LOG.info(WebShopDBMessage.TRANSACTION_START);
            savepoint = con.setSavepoint(SAVEPOINT);
            action.run();
            connectionManager.commit(con);
            LOG.info(WebShopDBMessage.TRANSACTION_SUCCESSFUL);
        } catch (Exception ex) {
            LOG.warn(WebShopDBMessage.ERR_TRANSACTION_FAIL);
            connectionManager.rollback(con, savepoint);
        } finally {
            connectionManager.close(con);
        }
    }
}
