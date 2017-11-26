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
package co.edu.uniandes.csw.bicycles.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.bicycles.entities.BicycleEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @generated
 */
@Stateless
public class BicyclePersistence extends CrudPersistence<BicycleEntity> {

    @PersistenceContext(unitName = "BicyclesPU")
    protected EntityManager em;

    /**
     * @return 
     * @generated
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * @return 
     * @generated
     */
    @Override
    protected Class<BicycleEntity> getEntityClass() {
        return BicycleEntity.class;
    }

    /**
     * Obtener bicicletas por descripcion
     *
     * @param description
     * @return Lista de bicicletas
     */
    public List<BicycleEntity> getByDescription(String description) {
        Map<String, Object> params = new HashMap<>();
        params.put("description", "%" + description.toUpperCase() + "%");
        return executeListNamedQuery("Bicycle.getByDescription", params);
    }

    /**
     * Obtener bicicletas por estado
     *
     * @param status
     * @return Lista de bicicletas
     */
    public List<BicycleEntity> getByStatus(String status) {
        Map<String, Object> params = new HashMap<>();
        params.put("status", "%" + status.toUpperCase() + "%");
        return executeListNamedQuery("Bicycle.getByStatus", params);
    }
    
    /**
     * Obtener últimas bicicletas
     *
     * 
     * @return Lista de bicicletas
     */
    public List<BicycleEntity> getLastBikes() {
        //Map<String, Object> params = new HashMap<>();
        //params.put("status", "%" + status.toUpperCase() + "%");
        return executeSingleNamedQuery("Bicycle.getLastBikes");
    }

}
