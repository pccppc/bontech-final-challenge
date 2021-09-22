package org.bontech.finalproject.service.base;

import org.bontech.finalproject.model.*;
import org.bontech.finalproject.model.dto.*;
import java.util.List;

public interface StorageService {

	/**
	 * create a storage and insert into database 
	 * @param createStorage include information of storage 
	 * @return Storage created Storage
	 */

	Storage creteStorage(CreateStorage createStorage);

	/**
	 * find a storage from database and delete that
	 * @param deleteStorage include storageId for search storage 
	 */

	void deleteStorage(DeleteStorage deleteStorage);

	/**
	 * find a storage and update that
	 * @param updateStorage include id for search and name for update
	 * @return Storage 
	 */

	Storage updateStorage(UpdateStorage updateStorage);

	/**
	 * find a storage
	 * @param storageId for search into database
	 * @return Storage
	 */

	Storage	findStorage(Long storageId);

	/**
	 * find all silo of Selectd storage with storageId and return sum of currentWeight of all silo
	 * @param storageId for search storage from database
	 * @return Long result of sum opperation on all silo from selected storage
	 */
	Long getCurrentStored(Long storageId);

	/**
	 * find all silo from selected storage
	 * @param storageId for search storage
	 * @return List<Silo>
	 */
	List<Silo> getAllSilo(Long storageId);

}
