package sn.dialibah.chargements.services;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sn.dialibah.chargements.models.*;
import sn.dialibah.chargements.repositories.ChargementsRepository;
import sn.dialibah.common.exception.ColisNotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
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
        String chargementMongoId = chargementEntity.getId();
        chargementEntity = mapper.map(chargement, ChargementEntity.class);
        chargementEntity.setId(chargementMongoId);

        return mapper.map(chargementsRepository.save(chargementEntity), Chargement.class);
    }

    @Override
    public Colis getColis(String chargementId, String colisId) {
        final ChargementEntity chargementEntity = this.chargementsRepository.findByGuid(chargementId);
        return  chargementEntity.getColis().stream()
                .filter(colis1 -> colis1.getGuid().equals(colisId))
                .findFirst().orElseThrow(() -> new NoSuchElementException("Colis "+colisId+" not found in "+ chargementId));
    }

    @Override
    public Colis updateColis(String chargementId, String colisId, Colis colis) {
        final ChargementEntity chargementEntity = this.chargementsRepository.findByGuid(chargementId);
        List<Colis> colisInChgt = chargementEntity.getColis();
        colisInChgt = colisInChgt.stream().map(c -> {
            if(c.getGuid().equals(colisId)){
                c = colis;
                c.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
                c.setGuid(colisId);
            }
            return c;
        }).collect(Collectors.toList());
        chargementEntity.setColis(colisInChgt);
        chargementsRepository.save(chargementEntity);
        Optional<Colis> savedColis = chargementEntity.getColis()
                .stream()
                .filter(colis1 -> colis.getGuid().equals(colisId))
                .findFirst();
        if(!savedColis.isPresent()) throw new ColisNotFoundException("Colis not found");
        return savedColis.get();
    }

    @Override
    public List<Chargement> deleteCHargement(String chargementId) {
        this.chargementsRepository.deleteByGuid(chargementId);
        return this.getAllChargements();
    }

    @Override
    public List<Colis> deleteColis(String chargementId, String colisId) {
        final ChargementEntity chargementEntity = this.chargementsRepository.findByGuid(chargementId);
        List<Colis> colisInChgt = chargementEntity.getColis();
        if(!colisInChgt.removeIf(c -> c.getGuid().equals(colisId)))
            throw new ColisNotFoundException("Colis not found");

        chargementEntity.setColis(colisInChgt);
        chargementsRepository.save(chargementEntity);

        return colisInChgt;
    }

//    private String buildGuid() {
//        return UUID.randomUUID().toString();
//    }

    private String buildGuid() {
        final Iterator<ChargementEntity> chargementEntityIteratorIterator = this.chargementsRepository.
                findAll(new Sort(Sort.Direction.DESC, "_id")).iterator();
        ChargementEntity chargementEntity = chargementEntityIteratorIterator.hasNext() ? chargementEntityIteratorIterator.next() : null;
        if (chargementEntity == null) return "AA-20170000-000";
        StringBuilder sb = new StringBuilder();
        sb.append("AA");
        sb.append("-");
        final String prevGuid = chargementEntity.getGuid();
        final String randomLetters = prevGuid.substring(0, 2);
        final String date = prevGuid.substring(3, 11);
        final String index = prevGuid.substring(12, 15);

        String newDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        if (newDate.equals(date)) {
            sb.append(date);
            sb.append("-");
            sb.append(String.format("%03d", Integer.parseInt(index) + 1));
        } else {
            sb.append(newDate);
            sb.append("-");
            sb.append("000");
        }
        return sb.toString();
    }
}
