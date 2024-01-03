package com.betwenn.test.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betwenn.test.back.model.Formation;
import com.betwenn.test.back.service.FormationService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/formations")
public class FormationController {

	private final FormationService formationService;

	@Autowired
	public FormationController(FormationService formationService) {
		this.formationService = formationService;
	}

	@GetMapping("/charge")
	public ResponseEntity<List<Formation>> chargeAllFormations() {
		List<Formation> formations = formationService.chargeAllFormations();
		return ResponseEntity.ok(formations);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Formation>> getAllFormations() {
		List<Formation> formations = formationService.findAllFormations();
		return ResponseEntity.ok(formations);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Formation> updateFormation(@PathVariable("id") Long id,
			@RequestBody Formation formation) {

		Formation updatedFormation = formationService.updateFormation(id, formation);

		return ResponseEntity.ok(updatedFormation);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteFormation(@PathVariable("id") Long id) {

		formationService.deleteFormation(id);

		return ResponseEntity.ok(id);
	}
}
