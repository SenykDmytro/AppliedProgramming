package com.senyk.comprehensiveLaba.gems.services;

import com.senyk.comprehensiveLaba.gems.entity.Gem;
import com.senyk.comprehensiveLaba.gems.entity.Sketch;
import com.senyk.comprehensiveLaba.gems.dao.GemRepository;
import com.senyk.comprehensiveLaba.gems.dao.SketchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class SketchService {

    private final SketchRepository sketchRepository;
    private final GemRepository gemRepository;
    private static final Logger logger= LoggerFactory.getLogger(SketchService.class);
    @Autowired
    public SketchService(SketchRepository sketchRepository, GemRepository gemRepository) {
        this.sketchRepository = sketchRepository;
        this.gemRepository = gemRepository;
    }

    @Transactional
    public void createSketch(Sketch sketch){
        logger.info("create sketch try");
        Optional<Sketch>optionalPlayer= sketchRepository.findSketchByName(sketch.getName());
        if (optionalPlayer.isPresent()){
            logger.error("failing");
            throw new IllegalStateException("name taken");
        }
        sketchRepository.save(sketch);
        logger.info("successful creating");
    }
    @Transactional
    public void removeSketch(Long sketchId){
        logger.info("remove sketch try");
        boolean exists= sketchRepository.existsById(sketchId);
        if (!exists){
            logger.error("failing");
            throw new IllegalStateException("sketch with id "+sketchId+" does not exists");
        }
        sketchRepository.getReferenceById(sketchId).getGems().clear();
        sketchRepository.deleteById(sketchId);
        logger.info("successful removing");
    }
    @Transactional
    public void addGemToSketch(Long sketchId, Long gemId){
        logger.info("add gem to sketch try");
        boolean exists= sketchRepository.existsById(sketchId);
        boolean exists1= gemRepository.existsById(gemId);
        if (!exists){
            logger.error("failing");
            throw new IllegalStateException("sketch with id "+sketchId+" does not exists");
        }
        if (!exists1){
            logger.error("failing");
            throw new IllegalStateException("gem with id "+gemId+" does not exists");
        }
        if (gemRepository.getReferenceById(gemId).getStatus()=="Used"){
            logger.error("failing");
            throw new IllegalStateException("gem "+gemId+" is already used in necklace");
        }
        if (sketchRepository.getReferenceById(sketchId).getGems().contains(gemRepository.getReferenceById(gemId))){
            logger.error("failing");
            throw new IllegalStateException("gem "+gemId+" is already used in this sketch");
        }
        sketchRepository.getReferenceById(sketchId).getGems()
                .add(gemRepository.getReferenceById(gemId));
        logger.info("successful adding");
    }
    @Transactional
    public void removeGemFromSketch(Long sketchId, Long gemId){
        logger.info("remove gem from sketch try");
        boolean exists= sketchRepository.existsById(sketchId);
        boolean exists1= gemRepository.existsById(gemId);
        if (!exists){
            logger.error("failing");
            throw new IllegalStateException("sketch with id "+sketchId+" does not exists");
        }
        if (!exists1){
            logger.error("failing");
            throw new IllegalStateException("gem with id "+gemId+" does not exists");
        }
        if (sketchRepository.getReferenceById(sketchId).getGems().contains(gemRepository.getReferenceById(gemId))){
            logger.error("failing");
            throw new IllegalStateException("gem "+gemId+" is no used in this sketch");
        }
        List<Gem> gems=sketchRepository.getReferenceById(sketchId).getGems();
        gems.remove(gemRepository.getReferenceById(gemId));
        sketchRepository.getReferenceById(sketchId).setGems(gems);
        logger.info("successful removing");
    }

    @Transactional
    public Double getSketchPrice(Long sketchId){
        Optional<Sketch> exists= sketchRepository.findById(sketchId);
        if (exists.isEmpty()){
            throw new IllegalStateException("sketch with id "+sketchId+" does not exists");
        }
        double sum=0.0;
        Sketch sketch =exists.get();
        List<Gem> gems = sketch.getGems();
        for (int i = 0; i < gems.size(); i++) {
            sum+=gems.get(i).getPrice();
        }
        return Double.valueOf(sum);
    }
    @Transactional
    public Double getSketchCarat(Long sketchId){
        Optional<Sketch> exists= sketchRepository.findById(sketchId);
        if (exists.isEmpty()){
            throw new IllegalStateException("sketch with id "+sketchId+" does not exists");
        }
        double sum=0.0;
        Sketch sketch =exists.get();
        List<Gem> gems = sketch.getGems();
        for (int i = 0; i < gems.size(); i++) {
            sum+=gems.get(i).getCarat();
        }
        return Double.valueOf(sum);
    }
    @Transactional
    public void getSketchGems(Long sketchId){
        Optional<Sketch> exists= sketchRepository.findById(sketchId);
        if (exists.isEmpty()){
            throw new IllegalStateException("sketch with id "+sketchId+" does not exists");
        }
        Sketch sketch =exists.get();
        List<Gem> gems = sketch.getGems();
        for (Gem g : gems) {
            System.out.println(g.toString());
        }
    }
    public List<Sketch> getSketches(){
        return sketchRepository.findAll();
    }

}
