package org.anakrimba.club.controller;

import org.anakrimba.club.model.Barcelona;
import org.anakrimba.club.repository.BarcelonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class BarcelonaController {

    @Autowired
    BarcelonaRepository barcelonaRepository;

    @GetMapping("/barcelonas")
    public ResponseEntity<List<Barcelona>> getAllBarcelonas(@RequestParam(required = false) String nama){
        try {
            List<Barcelona> barcelonas = new ArrayList<>();

            if (nama == null)
                barcelonaRepository.findAll().forEach(barcelonas::add);
            else
                barcelonaRepository.findByNamaContaining(nama).forEach(barcelonas::add);
            if (barcelonas.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(barcelonas, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/barcelonas/{id}")
    public ResponseEntity<Barcelona> getBarcelonaById(@PathVariable("id") long id){
        Optional<Barcelona> barcelonaData = barcelonaRepository.findById(id);

        if (barcelonaData.isPresent()){
            return new ResponseEntity<>(barcelonaData.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/barcelonas")
    public ResponseEntity<Barcelona> createBarcelona(@RequestBody Barcelona barcelona){
        try {
            Barcelona _barcelona = barcelonaRepository
                    .save(new Barcelona(barcelona.getNama(), barcelona.getPosisi(), barcelona.getNegara()));
            return new ResponseEntity<>(_barcelona, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/barcelonas/{id}")
    public ResponseEntity<Barcelona> updateBarcelona(@PathVariable("id") long id, @RequestBody Barcelona barcelona){
        Optional<Barcelona> barcelonaData =barcelonaRepository.findById(id);

        if (barcelonaData.isPresent()){
            Barcelona _barcelona = barcelonaData.get();
            _barcelona.setNama(barcelona.getNama());
            _barcelona.setPosisi(barcelona.getPosisi());
            _barcelona.setNegara(barcelona.getNegara());
            return new ResponseEntity<>(barcelonaRepository.save(_barcelona), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/barcelonas/{id}")
    public ResponseEntity<HttpStatus> deleteBarcelona(@PathVariable("id") long id){
        try {
            barcelonaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/barcelonas")
    public ResponseEntity<HttpStatus> deleteAllBarcelonas(){
        try {
            barcelonaRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
