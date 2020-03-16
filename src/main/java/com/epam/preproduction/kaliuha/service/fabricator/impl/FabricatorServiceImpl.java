package com.epam.preproduction.kaliuha.service.fabricator.impl;

import com.epam.preproduction.kaliuha.dao.fabricator.FabricatorDao;
import com.epam.preproduction.kaliuha.entity.impl.Fabricator;
import com.epam.preproduction.kaliuha.service.fabricator.FabricatorService;

import java.util.List;
import java.util.Optional;

public class FabricatorServiceImpl implements FabricatorService {

    private FabricatorDao fabricatorDao;

    public FabricatorServiceImpl(FabricatorDao fabricatorDao) {
        this.fabricatorDao = fabricatorDao;
    }

    @Override
    public Optional<Fabricator> getFabricator(long id) {
        return fabricatorDao.getFabricator(id);
    }

    @Override
    public List<Fabricator> getAllFabricators() {
        return fabricatorDao.getAllFabricators();
    }
}
