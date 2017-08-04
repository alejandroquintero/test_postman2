/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.bicycles.api;

import co.edu.uniandes.csw.bicycles.entities.BrandEntity;
import co.edu.uniandes.csw.bicycles.entities.BicycleEntity;
import co.edu.uniandes.csw.bicycles.exceptions.BusinessLogicException;
import java.util.List;

public interface IBrandLogic {
    public int countBrands();
    public List<BrandEntity> getBrands();
    public List<BrandEntity> getBrands(Integer page, Integer maxRecords);
    public BrandEntity getBrand(Long id);
    public BrandEntity createBrand(BrandEntity entity) throws BusinessLogicException ; 
    public BrandEntity updateBrand(BrandEntity entity);
    public void deleteBrand(Long id);
    public List<BicycleEntity> listBicycle(Long brandId);
    public BicycleEntity getBicycle(Long brandId, Long bicycleId);
    public BicycleEntity addBicycle(Long brandId, Long bicycleId);
    public List<BicycleEntity> replaceBicycle(Long brandId, List<BicycleEntity> list);
    public void removeBicycle(Long brandId, Long bicycleId);
}
