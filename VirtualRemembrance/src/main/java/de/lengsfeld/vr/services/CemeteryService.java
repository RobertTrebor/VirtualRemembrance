package de.lengsfeld.vr.services;

import de.lengsfeld.vr.model.Cemetery;

import java.util.List;


public interface CemeteryService {
    List<Cemetery> getAllCemeteries();
    void addCemetery(Cemetery cemetery);
    void deleteCemetery(Cemetery cemetery);
    void updateCemetery(Cemetery cemetery);
}
