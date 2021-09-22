package org.bontech.finalproject.service.base;

import java.util.List;

import org.bontech.finalproject.model.*;
import org.bontech.finalproject.model.dto.*;

public interface SiloService {

	/**
	 * create a silo and insert that into database
	 * @param createSilo include name and maximumWeigh of silo
	 * @return Silo 
	 */
	Silo createSilo(CreateSilo createSilo);

	/**
	 * update a silo and save changes into database
	 * @param updateSilo information of silo and siloId for search and update
	 */

	void updateSilo(UpdateSilo updateSilo);

	/**
	 * delete a silo from database
	 * @param deleteSilo for search silo and delete it
	 */

	void deleteSilo(DeleteSilo deleteSilo);

	/**
	 * find a silo
	 * @param siloId for search into database and select it with siloId
	 * @return Silo
	 */

	Silo findSilo(Long siloId);

	/**
	 * fnid (add or harvest) history of silo from table Changes_of_silo and return n record from that
	 * @param siloId for check silo existence
	 * @param n for return n record of result (handle it into database query)
	 * @return List<ChangesOfSilo> list of history record
	 */

	List<ChangesOfSilo> getHistory(Long siloId,Integer n);

	/**
	 * add or harvest from silo, sensor of silo sense two time and check maximum and minimum and save history 
	 * @param siloId for search silo into database
	 * @return Silo
	 */

	Silo addOrHarvest(Long siloId);


}
