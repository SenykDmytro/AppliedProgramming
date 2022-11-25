package com.senyk.comprehensiveLaba.gems.services;

import com.senyk.comprehensiveLaba.gems.dao.GemRepository;
import com.senyk.comprehensiveLaba.gems.entity.Gem;
import com.senyk.comprehensiveLaba.gems.entity.Sketch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class GemService {
    private GemRepository gemRepository;
    private static final Logger logger= LoggerFactory.getLogger(GemService.class);
    @Autowired
    public GemService(GemRepository gemRepository) {
        this.gemRepository = gemRepository;
    }
    public List<Gem> getGems(){
        logger.info("get list of gems");
        return gemRepository.findAll();
    }
    @Transactional
    public void addGem(Gem gem){
        logger.info("add gem try");
        Optional<Gem> optionalPlayer= gemRepository.findById(gem.getId());
        if (optionalPlayer.isPresent()){
            logger.error("failing");
            throw new IllegalStateException("id taken");
        }
        gemRepository.save(gem);
        logger.info("successful adding");
    }
    @Transactional
    public void removeGem(Long gemId){
        logger.info("remove gem try");
        boolean exists= gemRepository.existsById(gemId);
        if (!exists){
            logger.error("failing");
            throw new IllegalStateException("gem with id "+gemId+" does not exists");
        }
        if (gemRepository.getReferenceById(gemId).getStatus()=="Used"){
            logger.error("failing");
            throw new IllegalStateException("gem with id "+gemId+" is used");
        }
        if (!gemRepository.getReferenceById(gemId).getSketchList().isEmpty()){
            logger.error("failing");
            throw new IllegalStateException("gem with id "+gemId+" is used");
        }
        gemRepository.deleteById(gemId);
        logger.info("successful removing");
    }
    @Transactional
    public void updateGem(Long gemId,Double price){
        logger.info("update gem");
        Gem gem=gemRepository.findById(gemId).orElseThrow(
                ()->{logger.error("failing");
                    return new IllegalStateException("gem with id "+gemId+" does not exists");});
        if (price<=0){
            logger.error("failing");
            throw new IllegalStateException("incorrect price");
        }
        gem.setPrice(price);
        logger.info("successful updating");
    }
}
