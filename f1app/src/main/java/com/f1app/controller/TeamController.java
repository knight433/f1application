package com.f1app.controller;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.f1app.CrudOperationException;
import com.f1app.CrudValidationException;
import com.f1app.model.TeamModel;
import com.f1app.service.TeamService;


@RestController
@RequestMapping("/f1buzz/api/v1/team")
public class TeamController {
	@Autowired
	private TeamService teamService;
	private Logger log = LogManager.getLogger(getClass());

	@GetMapping("/get/by/id/{id}")
	public ResponseEntity<?> getTeamModelById(@PathVariable long id) {
		if (id <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id cannot be less than 1");
		}
		try {
			TeamModel teammodel = teamService.getById(id);
			return ResponseEntity.status(HttpStatus.OK).body(teammodel);
		} catch (CrudValidationException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (CrudOperationException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/get/all/page/{page}/size/{size}")
	public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		try {
			List<TeamModel> teammodels = teamService.getAll(page, size);
			if (teammodels.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No teammodels found.");
			}
			return ResponseEntity.status(HttpStatus.OK).body(teammodels);
		} catch (CrudValidationException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (CrudOperationException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping("/add")
	public ResponseEntity<?> addNewTeamModel(@RequestBody TeamModel teammodel) {
		if (teammodel == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was no teammodel model given");
		}
		try {
			TeamModel savedTeamModel = teamService.addNewTeamModel(teammodel);
			return ResponseEntity.status(HttpStatus.OK).body(savedTeamModel);
		} catch (CrudValidationException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (CrudOperationException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/get/count")
	public ResponseEntity<?> getCountOfteam() {
		try {
			long count = teamService.getCountOfteams();
			return ResponseEntity.status(HttpStatus.OK).body(count);
		} catch (CrudValidationException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (CrudOperationException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}