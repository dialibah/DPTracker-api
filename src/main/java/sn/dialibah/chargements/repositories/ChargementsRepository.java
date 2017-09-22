package sn.dialibah.chargements.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import sn.dialibah.chargements.models.ChargementEntity;

/**
 * Created by nureynisow on 22/09/2017.
 * for DPTracker
 */
public interface ChargementsRepository extends PagingAndSortingRepository<ChargementEntity, String> {
}
