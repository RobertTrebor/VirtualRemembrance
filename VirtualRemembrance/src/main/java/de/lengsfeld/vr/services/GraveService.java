package de.lengsfeld.vr.services;

import de.lengsfeld.vr.model.Cemetery;
import de.lengsfeld.vr.model.Grave;

import java.util.List;

public interface GraveService {
    List<Grave> getGraveList(Cemetery cemetery);
    void addGrave(Grave grave);
    void deleteGrave(Grave grave);
    void updateGrave(Grave grave);
}
