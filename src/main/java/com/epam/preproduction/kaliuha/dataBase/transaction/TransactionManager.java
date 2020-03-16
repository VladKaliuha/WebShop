package com.epam.preproduction.kaliuha.dataBase.transaction;

import java.util.function.Supplier;

public interface TransactionManager {

    <T> T doTransaction(Supplier<T> action);

    void doTransaction(Runnable action);
}
