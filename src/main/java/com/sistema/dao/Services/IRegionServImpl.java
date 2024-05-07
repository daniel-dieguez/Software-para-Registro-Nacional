package com.sistema.dao.Services;

import com.sistema.modals.modal.Region;

import java.util.List;

public interface IRegionServImpl {

    public List<Region> findAll();
    public Region findById (String idRegion);
    public Region save (Region region);
    public void delete (Region region);



}
