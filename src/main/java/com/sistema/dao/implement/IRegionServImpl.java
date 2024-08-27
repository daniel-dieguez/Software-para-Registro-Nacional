package com.sistema.dao.implement;

import com.sistema.modals.modal.Region;

import java.util.List;

public interface IRegionServImpl {

    public List<Region> findAll();
    public Region findById (String id_region);
    public Region save (Region region);
    public void delete (Region region);



}
