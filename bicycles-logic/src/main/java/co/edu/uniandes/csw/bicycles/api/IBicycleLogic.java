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

import co.edu.uniandes.csw.bicycles.entities.BicycleEntity;
import co.edu.uniandes.csw.bicycles.entities.ClientEntity;
import java.util.List;

public interface IBicycleLogic {

    public int countBicycles();

    public List<BicycleEntity> getBicycles();

    public List<BicycleEntity> getBicycles(Integer page, Integer maxRecords);

    public BicycleEntity getBicycle(Long id);

    public BicycleEntity createBicycle(BicycleEntity entity);

    public BicycleEntity updateBicycle(BicycleEntity entity);

    public void deleteBicycle(Long id);

    /**
     * Validar vigencia.
     *
     * @param page numero de pagina.
     * @param maxRecords maximo de records.
     * @return Lista de Bicicletas.
     */
    public List<BicycleEntity> validarVigencia(Integer page, Integer maxRecords);

    /**
     * Obtener bicicletas por descripcion
     *
     * @param description
     * @return Lista de bibicletas
     */
    public List<BicycleEntity> getByDescription(String description);

    /**
     * Obtener bicicletas por estado
     *
     * @param status
     * @return Lista de bicicletas
     */
    public List<BicycleEntity> getByStatus(String status);
    
    /**
     * Obtener bicicletas favoritas de un usuario
     *
     * @param username
     * @return Lista de bicicletas
     */
    public List<BicycleEntity> getFavorite(String username);
}
