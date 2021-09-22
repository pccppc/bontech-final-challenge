package org.bontech.finalproject.service.base;

import java.util.List;

import org.bontech.finalproject.model.*;
import org.bontech.finalproject.model.dto.*;

public interface SiloService {

	Silo createSilo(CreateSilo createSilo);
	void updateSilo(UpdateSilo updateSilo);
	void deleteSilo(DeleteSilo deleteSilo);
	Silo findSilo(Long siloId);
	List<ChangesOfSilo> getHistory(Long siloId,Integer n);
	Silo addOrHarvest(Long siloId);

}
