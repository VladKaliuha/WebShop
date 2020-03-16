package com.epam.preproduction.kaliuha.service.fabricator;

import com.epam.preproduction.kaliuha.entity.impl.Fabricator;
import com.epam.preproduction.kaliuha.service.Service;

import java.util.List;
import java.util.Optional;

public interface FabricatorService extends Service {

    /**
     * Return fabricator by id
     *
     * @param id fabricator id
     * @return suitable fabricator
     */
    Optional<Fabricator> getFabricator(long id);

    /**
     * Return all fabricators
     *
     * @return fabricators list
     */
    List<Fabricator> getAllFabricators();
}
