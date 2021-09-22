package org.bontech.finalproject.service.base;

import org.bontech.finalproject.model.*;
import org.bontech.finalproject.model.dto.*;
import java.util.List;

public interface StorageService {

	Storage creteStorage(CreateStorage creteStorage);
	void deleteStorage(DeleteStorage deleteStorage);
	Storage updateStorage(UpdateStorage updateStorage);
	Storage	findStorage(Long storageId);
	Long getCurrentStored(Long storageId);
	List<Silo> getAllSilo(Long storageId);

}
