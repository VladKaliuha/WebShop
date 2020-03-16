package com.epam.preproduction.kaliuha.dao.fabricator;

import com.epam.preproduction.kaliuha.dao.Dao;
import com.epam.preproduction.kaliuha.entity.impl.Fabricator;

import java.util.List;
import java.util.Optional;

public interface FabricatorDao extends Dao {

    Optional<Fabricator> getFabricator(long id);

    List<Fabricator> getAllFabricators();
}
