package sn.dialibah.chargements.services;

import sn.dialibah.chargements.models.Chargement;
import sn.dialibah.chargements.models.ChargementExtra;
import sn.dialibah.chargements.models.Colis;

import java.util.List;

/**
 * Created by nureynisow on 22/09/2017.
 * for DPTracker
 */
public interface IChargementsService {

    /**
     * This method creates a {@link Chargement}
     * initialize the guid
     *
     * @return created chargement with guid
     * @param data eventual extra data to add
     */
    Chargement createChargement(ChargementExtra data);


    /**
     * Retrieves all chargements
     *
     * @return a {@link List} of all {@link Chargement}
     */
    List<Chargement> getAllChargements();

    /**
     * Add colis in a specified chargement
     *
     * @param chargementId guid of the chargement
     * @param data eventual extra data to add
     * @return created colis
     */
    Colis createColis(String chargementId, ChargementExtra data);

    /**
     * Get a specified chargement
     *
     * @param chargementId guid
     * @return the {@link Chargement}
     */
    Chargement getChargement(String chargementId);

    Chargement updateChargement(String chargementId, Chargement chargement);
}
