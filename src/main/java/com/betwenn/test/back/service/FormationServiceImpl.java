package com.betwenn.test.back.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betwenn.test.back.exception.FormationNotFoundException;
import com.betwenn.test.back.model.Formation;
import com.betwenn.test.back.repository.FormationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FormationServiceImpl implements FormationService {

	private final FormationRepository formationRepository;

	@Autowired
	public FormationServiceImpl(FormationRepository formationRepository) {
		this.formationRepository = formationRepository;
	}

	// Méthode pour récupérer toutes les formations de l'api la bonne alternance 
	// Parameters : romeDomain, latitude, longitude, radius, caller
	public void getAllFormations() {
		
		// Construction de l'URL avec les paramètres demandés
		String romeDomain = "D11";
		Float latitude = 48.112880F;
		Float longitude = -1.672530F;
		int radius = 50;
		String caller = "ts.thomas.salmon%40gmail.com";
		
		String apiUrl = "https://labonnealternance.apprentissage.beta.gouv.fr/api/v1/formations?"
				+ "romeDomain=" + romeDomain
				+ "&latitude=" + latitude
				+ "&longitude=" + longitude 
				+ "&radius=" + radius
				+ "&caller=" + caller;

		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).build();

		try {
			// Appel GET à l'API pour récupérer le JSON
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			String jsonResponse = response.body();

			// Désérialisation du JSON en Map
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> jsonMap = objectMapper.readValue(jsonResponse, Map.class);

			// Extraction des données
			List<Map<String, Object>> results = (List<Map<String, Object>>) jsonMap.get("results");
			for (Map<String, Object> result : results) {
				String title = (String) result.get("title");

				Map<String, Object> place = (Map<String, Object>) result.get("place");
				String address = (String) place.get("address");
				String zipCode = (String) place.get("zipCode");
				String city = (String) place.get("city");

				Map<String, Object> contact = (Map<String, Object>) result.get("contact");
				String phoneNumber = (String) contact.get("phone");

				List<Map<String, Object>> romes = (List<Map<String, Object>>) result.get("romes");
				String romeCode = "";
				if (!romes.isEmpty()) {
					Map<String, Object> rome = romes.get(0);
					romeCode = (String) rome.get("code");
				}

				// Enregistrement de la formation en base
				Formation formation = new Formation(title, address, zipCode, city, phoneNumber, romeCode);
				formationRepository.save(formation);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Formation> findAllFormations() {
		List<Formation> formations = formationRepository.findAll();
		
		if(formations.isEmpty()) {
			throw new FormationNotFoundException("No formations were found");
		}
		return formations;
	}


	public List<Formation> chargeAllFormations() {
		// vide la BDD avant de récupérer toutes les formations auprès de l'API la bonne alternance
		formationRepository.deleteAll();
		getAllFormations();
		return findAllFormations();
	}

	public Formation updateFormation(Long id, Formation formation) {
		if(!formationRepository.existsById(id)) {
			throw new FormationNotFoundException("Formation with id " + id + "not found.");
		}
		formation.setId(id);
		formation = formationRepository.save(formation);
		return formation;
	}

	public void deleteFormation(Long id) {
		if(!formationRepository.existsById(id)) {
			throw new FormationNotFoundException("Formation with id " + id + "not found.");
		}
		formationRepository.deleteById(id);
	}

}
