package com.sistema.dao.Services;

import com.sistema.dao.IRegionDao;
import com.sistema.modals.modal.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService implements IRegionServImpl{

    @Autowired
    private IRegionDao iRegionDao;



    @Override
    public List<Region> findAll() {
        return this.iRegionDao.findAll();
    }

    @Override
    public Region findById(String id_region) {
        return this.iRegionDao.findById(id_region).orElse(null);
    }

    @Override
    public Region save(Region region) {
        return this.iRegionDao.save(region);
    }

    @Override
    public void delete(Region region) {
        this.iRegionDao.delete(region);

    }
}
