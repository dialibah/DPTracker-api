package sn.dialibah.chargements.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sn.dialibah.chargements.models.Chargement;
import sn.dialibah.chargements.services.IChargementsService;

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
    public ResponseEntity<Chargement> createChargement(){
        log.debug("Creating a new chargement");
        return new ResponseEntity<>(this.chargementsService.createChargement(), HttpStatus.OK);
    }
}