package com.senyk.comprehensiveLaba.gems.services;

import com.senyk.comprehensiveLaba.gems.entity.Gem;
import com.senyk.comprehensiveLaba.gems.entity.Necklace;
import com.senyk.comprehensiveLaba.gems.dao.NecklaceRepository;
import com.senyk.comprehensiveLaba.gems.dao.SketchRepository;
import com.senyk.comprehensiveLaba.gems.entity.Sketch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class NecklaceService {

    private final NecklaceRepository necklaceRepository;
    private final SketchRepository sketchRepository;
    private static final Logger logger= LoggerFactory.getLogger(NecklaceService.class);

    @Autowired
    public NecklaceService(NecklaceRepository necklaceRepository,SketchRepository sketchRepository) {
        this.necklaceRepository = necklaceRepository;
        this.sketchRepository = sketchRepository;
    }

    @Transactional
    public void reconstructNecklace(Long necklaceId){
        logger.info("reconstruct necklace try");
        boolean exists= necklaceRepository.existsById(necklaceId);
        if (!exists){
            logger.error("failing");
            throw new IllegalStateException("necklace with id "+necklaceId+" does not exists");
        }
        for (Gem gem:necklaceRepository.getReferenceById(necklaceId).getGems()) {
            gem.setStatus("No used");
        }
        necklaceRepository.deleteById(necklaceId);
        logger.info("successful reconstructing");
    }

    @Transactional
    public void implementSketch(Long sketchId){
        logger.info("implement necklace try");
        Optional<Sketch> exists= sketchRepository.findById(sketchId);
        if (exists.isEmpty()){
            logger.error("failing");
            throw new IllegalStateException("sketch with id "+sketchId+" does not exists");
        }
        Sketch sketch =exists.get();
        List<Gem> gems = new LinkedList<>();
        for (int i = 0; i < sketch.getGems().size(); i++) {
            gems.add(sketch.getGems().get(i));
        }
        for (Gem g :sketchRepository.getReferenceById(sketchId).getGems()) {
            if (g.getStatus()=="Used")
                throw new IllegalStateException("gem "+g.getId()+" is already used");
        }
        for (Gem g :sketchRepository.getReferenceById(sketchId).getGems()) {
            g.setStatus("Used");
        }
        String name=sketchRepository.getReferenceById(sketchId).getName();
        Necklace necklace= new Necklace(name,gems);
        necklaceRepository.save(necklace);
        logger.info("successful implementing");
    }
    @Transactional
    public Double getNecklacePrice(Long necklaceId){
        Optional<Necklace> exists= necklaceRepository.findById(necklaceId);
        if (exists.isEmpty()){
            throw new IllegalStateException("necklace with id "+necklaceId+" does not exists");
        }
        double sum=0.0;
        Necklace necklace =exists.get();
        List<Gem> gems = necklace.getGems();
        for (int i = 0; i < gems.size(); i++) {
            sum+=gems.get(i).getPrice();
        }
        return Double.valueOf(sum);
    }
    @Transactional
    public Double getNecklaceCarat(Long necklaceId){
        boolean exists= necklaceRepository.existsById(necklaceId);
        if (!exists){
            throw new IllegalStateException("necklace with id "+necklaceId+" does not exists");
        }
        return necklaceRepository.getReferenceById(necklaceId).getGems().stream()
                .reduce(0.0,(sketchCarat,gem)->sketchCarat+gem.getCarat(),Double::sum);
    }
    @Transactional
    public List<Necklace> getNecklaces(){
        return necklaceRepository.findAll();
    }
    @Transactional
    public List<Gem> getNecklaceGems(Long necklaceId){
        boolean exists= necklaceRepository.existsById(necklaceId);
        if (!exists){
            throw new IllegalStateException("necklace with id "+necklaceId+" does not exists");
        }
        return necklaceRepository.getReferenceById(necklaceId).getGems();
    }
    @Transactional
    public void getNecklaceGems1(Long necklaceId){
        Optional<Necklace> exists= necklaceRepository.findById(necklaceId);
        if (exists.isEmpty()){
            throw new IllegalStateException("necklace with id "+necklaceId+" does not exists");
        }
        Necklace necklace =exists.get();
        List<Gem> gems = necklace.getGems();
        for (Gem g : gems) {
            System.out.println(g.toString());
        }
    }
}