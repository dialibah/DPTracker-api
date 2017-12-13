package sn.dialibah.chargements.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.dialibah.chargements.models.Chargement;
import sn.dialibah.chargements.models.ChargementExtra;
import sn.dialibah.chargements.models.Colis;
import sn.dialibah.chargements.services.IChargementsService;

import java.util.List;

/**
 * Created by nureynisow on 22/09/2017.
 * for DPTracker
 */
@RestController
@RequestMapping("chargements")
@Slf4j
public class ChargementsController {

    private final IChargementsService chargementsService;

    @Autowired
    public ChargementsController(IChargementsService chargementsService) {
        this.chargementsService = chargementsService;
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Chargement> createChargement(@RequestBody ChargementExtra data){
        log.debug("Creating a new chargement");
        return new ResponseEntity<>(this.chargementsService.createChargement(data), HttpStatus.OK);
    }

    @RequestMapping(value = "{chargementId}/colis", method = RequestMethod.POST)
    public ResponseEntity<Colis> createColis(@RequestBody ChargementExtra data,
                                             @PathVariable("chargementId") String chargementId){
        log.debug("Creating a new colis");
        return new ResponseEntity<>(this.chargementsService.createColis(chargementId, data), HttpStatus.OK);
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Chargement>> getChargements(){
        log.debug("Get all chargements");
        return new ResponseEntity<>(this.chargementsService.getAllChargements(), HttpStatus.OK);
    }

    @RequestMapping(value = "{chargementId}", method = RequestMethod.GET)
    public ResponseEntity<Chargement> getChargement(@PathVariable("chargementId") String chargementId){
        log.debug("Get chargement {}", chargementId);
        return new ResponseEntity<>(this.chargementsService.getChargement(chargementId), HttpStatus.OK);
    }

    @RequestMapping(value = "{chargementId}", method = RequestMethod.PUT)
    public ResponseEntity<Chargement> updateChargement(@PathVariable("chargementId") String chargementId, @RequestBody Chargement chargement){
        log.debug("Update chargement {} with", chargementId, chargement);
        return new ResponseEntity<>(this.chargementsService.updateChargement(chargementId, chargement), HttpStatus.OK);
    }

    @RequestMapping(value = "{chargementId}/colis/{colisId}", method = RequestMethod.GET)
    public ResponseEntity<Colis> getColis(@PathVariable("chargementId") String chargementId, @PathVariable("colisId") String colisId){
        log.debug("Get colis {} of chargement {}", colisId, chargementId);
        return new ResponseEntity<>(this.chargementsService.getColis(chargementId, colisId), HttpStatus.OK);
    }

    @RequestMapping(value = "{chargementId}/colis/{colisId}", method = RequestMethod.PUT)
    public ResponseEntity<Colis> updateColis(@PathVariable("chargementId") String chargementId, @PathVariable("colisId") String colisId, @RequestBody Colis colis){
        log.debug("Update colis {} of chargement {}", colisId, chargementId);
        return new ResponseEntity<>(this.chargementsService.updateColis(chargementId, colisId, colis), HttpStatus.OK);
    }
}