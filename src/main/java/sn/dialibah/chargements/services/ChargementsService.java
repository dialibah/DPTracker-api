package sn.dialibah.chargements.services;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.dialibah.chargements.models.Chargement;
import sn.dialibah.chargements.models.ChargementEntity;
import sn.dialibah.chargements.repositories.ChargementsRepository;

import java.util.UUID;

/**
 * Created by nureynisow on 22/09/2017.
 * for DPTracker
 */

@Service
public class ChargementsService implements IChargementsService {

    private final ChargementsRepository chargementsRepository;

    private final DozerBeanMapper mapper;

    @Autowired
    public ChargementsService(ChargementsRepository chargementsRepository, DozerBeanMapper mapper) {
        this.chargementsRepository = chargementsRepository;
        this.mapper = mapper;
    }

    @Override
    public Chargement createChargement() {
        final ChargementEntity chargement = this.mapper.map(
                Chargement.builder()
                        .guid(buildGuid())
                        .build(),
                ChargementEntity.class);
        return this.mapper.map(this.chargementsRepository.save(chargement), Chargement.class);
    }

    private String buildGuid() {
        return UUID.randomUUID().toString();
    }
}
