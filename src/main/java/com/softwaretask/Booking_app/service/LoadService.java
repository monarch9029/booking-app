package com.softwaretask.Booking_app.service;

import com.softwaretask.Booking_app.entity.Load;
import com.softwaretask.Booking_app.entity.LoadStatus;
import com.softwaretask.Booking_app.repository.LoadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LoadService {
    private final LoadRepository loadRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoadService.class);


    public LoadService(LoadRepository loadRepository) {
        this.loadRepository = loadRepository;
    }

    public Load createLoad(Load load) {
        logger.info("Creating new load");
        return loadRepository.save(load);
    }

    public List<Load> getLoadsByShipperId(String shipperId) {
        return loadRepository.findByShipperId(shipperId);
    }

    public Load getLoadById(UUID loadId) {
        return loadRepository.findById(loadId).orElseThrow(()->
        {   logger.error("Load not found");
            return new RuntimeException("Load not found");
        });
    }

    public List<Load> getLoadByTruckType(String truckType) {
        return loadRepository.findByTruckType(truckType);
    }

    public void deleteLoadById(UUID loadId) {
        loadRepository.deleteById(loadId);
    }

    public Load updateLoad(Load load,UUID loadId) {
        return loadRepository.findById(loadId).map(existingLoad->
                {
                    if(existingLoad.getStatus()== LoadStatus.CANCELLED){
                        logger.error("Load status is CANCELLED");
                        throw new IllegalStateException("Load status is CANCELLED");
                    }
                    existingLoad.setFacility(load.getFacility());
                    existingLoad.setShipperId(load.getShipperId());
                    existingLoad.setProductType(load.getProductType());
                    existingLoad.setTruckType(load.getTruckType());
                    existingLoad.setNoOfTrucks(load.getNoOfTrucks());
                    existingLoad.setWeight(load.getWeight());
                    existingLoad.setComment(load.getComment());
                    existingLoad.setDatePosted(load.getDatePosted());
                    logger.info("Updating existing load");
                    return loadRepository.save(existingLoad);
                }).orElseThrow(()->
        {
            logger.error("Load not found");
            return new RuntimeException("Load not found");
        });

    }
}
