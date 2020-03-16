package com.epam.preproduction.kaliuha.filter.localization.strategy.container;

import com.epam.preproduction.kaliuha.filter.localization.strategy.LocaleStorageStrategy;
import com.epam.preproduction.kaliuha.filter.localization.strategy.impl.CookieLocaleStorage;
import com.epam.preproduction.kaliuha.filter.localization.strategy.impl.SessionLocaleStorage;

import java.util.HashMap;
import java.util.Map;

public class LocaleStorageStrategyContainer {

    private Map<String, LocaleStorageStrategy> localeStoreStrategies;

    public LocaleStorageStrategyContainer() {
        initStrategies();
    }

    public LocaleStorageStrategy getStrategy(String strategyName) {
        return localeStoreStrategies.get(strategyName);
    }

    private void initStrategies() {
        this.localeStoreStrategies = new HashMap<>();
        localeStoreStrategies.put("session", new SessionLocaleStorage());
        localeStoreStrategies.put("cookie", new CookieLocaleStorage());
    }
}
