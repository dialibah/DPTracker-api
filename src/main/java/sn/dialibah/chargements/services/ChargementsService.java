package sn.dialibah.chargements.services;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.dialibah.chargements.models.*;
import sn.dialibah.chargements.repositories.ChargementsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public Chargement createChargement(ChargementExtra data) {
        final ChargementEntity chargement = this.mapper.map(
                Chargement.builder()
                        .guid(buildGuid())
                        .createdBy(data.getCreatedBy())
                        .statutChargement(StatutChargement.CREATED)
                        .build(),
                ChargementEntity.class);
        return this.mapper.map(this.chargementsRepository.save(chargement), Chargement.class);
    }

    @Override
    public List<Chargement> getAllChargements() {
        return StreamSupport
                .stream(this.chargementsRepository.findAll().spliterator(), true)
                .map(chargementEntity -> mapper.map(chargementEntity, Chargement.class))
                .collect(Collectors.toList());
    }

    @Override
    public Colis createColis(String chargementId, ChargementExtra data) {
        final ChargementEntity chargementEntity = this.chargementsRepository.findByGuid(chargementId);
        final Colis colis = Colis.builder()
                .guid(buildGuid())
                .createdBy(data.getCreatedBy())
                .build();
        List<Colis> chargementColis = chargementEntity.getColis();
        if(chargementColis == null) chargementColis = new ArrayList<>();
        chargementColis.add(colis);
        chargementEntity.setColis(chargementColis);
        this.chargementsRepository.save(chargementEntity);
        return colis;
    }

    @Override
    public Chargement getChargement(String chargementId) {
        final ChargementEntity chargementEntity = this.chargementsRepository.findByGuid(chargementId);
        return this.mapper.map(chargementEntity, Chargement.class);
    }

    @Override
    public Chargement updateChargement(String chargementId, Chargement chargement) {
        ChargementEntity chargementEntity = this.chargementsRepository.findByGuid(chargementId);

        return null;
    }

    private String buildGuid() {
        return UUID.randomUUID().toString();
    }
}
