package com.carshop.adm.model;

import java.util.List;

public interface AdmDAO_interface {
    public void insert(AdmVO admVO);
    public void update(AdmVO admVO);
    public void delete(Integer admno);
    public AdmVO findByPK(Integer admno);
    public List<AdmVO> getAll();
}
