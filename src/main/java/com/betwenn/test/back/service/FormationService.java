package com.betwenn.test.back.service;

import java.util.List;

import com.betwenn.test.back.model.Formation;

public interface FormationService {
	
	List<Formation> chargeAllFormations();

	List<Formation> findAllFormations();

	Formation updateFormation(Long id, Formation formation);

	void deleteFormation(Long id);


}
