package com.jmd.poec.java.demojpa2.controller;

import com.jmd.poec.java.demojpa2.domain.dto.WilderDTO;
import com.jmd.poec.java.demojpa2.domain.dto.WilderFullDTO;
import com.jmd.poec.java.demojpa2.service.WilderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wilders")
public class WilderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WilderController.class);

    private final WilderService wilderService;

    private final Environment environment;

    public WilderController(WilderService wilderService, Environment environment) {
        this.wilderService = wilderService;
        this.environment = environment;
    }

    /*
     * CONTROLLER WILDER - LOGIQUE CRUD
     *
     *  /wilders -> GET : list of wilders
     *  /wilders/{id} -> GET a wilder
     *  /wilders/{id} -> PUT, PATCH update a wilder
     *  /wilders -> POST create a wilder
     *  /wilders/{id} -> DELETE a wilder
     */

    /*
     * READ
     */

    @GetMapping("")
    public ResponseEntity<List<WilderFullDTO>> list(){
        if(isProduction()){
            LOGGER.info("LIST wilders for PRODUCTION");
            return ResponseEntity.ofNullable(wilderService.list());
        }else{
            LOGGER.info("AVOID LISTING wilders for DEVELOPMENT");
            return ResponseEntity.ofNullable(new ArrayList<>());
        }

    }

    private boolean isProduction() {
        return this.environment.matchesProfiles("prod");
    }

    @GetMapping("/{id}")
    public ResponseEntity<WilderFullDTO> get(@PathVariable Long id) {
        LOGGER.info("GET wilder by id {}",id);
        return ResponseEntity.ofNullable(wilderService.find(id));
    }

    /*
     * UPDATE
     */

    @PutMapping("/{id}")
    public ResponseEntity<WilderFullDTO> update(@PathVariable Long id, @RequestBody WilderDTO wilder){
        LOGGER.info("UPDATE wilder {} : {}",id,wilder);
        return ResponseEntity.ofNullable(wilderService.update(id,wilder));
    }

    /*
     * CREATE
     */

    @PostMapping("")
    public ResponseEntity<WilderFullDTO> post(@RequestBody WilderFullDTO wilder){
        LOGGER.info("CREATE wilder : {}",wilder);
        return ResponseEntity.status(HttpStatus.CREATED).body(wilderService.create(wilder));
    }

    /*
     * DELETE
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        LOGGER.info("DELETE wilder {}",id);
        wilderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /*
     * OTHERS
     */

    @GetMapping("/custom")
    public ResponseEntity<List<WilderFullDTO>> customFind(@RequestParam String category){
        return ResponseEntity.ok(wilderService.customFind(category));
    }
}
