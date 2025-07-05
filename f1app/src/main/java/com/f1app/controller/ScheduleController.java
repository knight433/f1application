package com.f1app.controller;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.f1app.CrudOperationException;
import com.f1app.CrudValidationException;
import com.f1app.service.ScheduleService;
import com.f1app.model.ScheduleModel;
@RestController
@RequestMapping("/f1app/api/v1/schedual")
public class ScheduleController {
	@Autowired
	private ScheduleService service;
	private final Logger log = LogManager.getLogger(getClass());
	@GetMapping("/get/all")
	public ResponseEntity<?> getAll() {
		try {
			List<ScheduleModel> items = service.getAll();
			if (items.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No records found.");
			return ResponseEntity.ok(items);
		} catch (CrudValidationException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (CrudOperationException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}


	@GetMapping("/get/by/id/{id}")
	public ResponseEntity<?> getById(@PathVariable long id) {
		if (id <= 0)
			return ResponseEntity.badRequest().body("Invalid ID");
		try {
			return ResponseEntity.ok(service.getById(id));
		} catch (CrudValidationException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (CrudOperationException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody ScheduleModel scheduleModel) {
		try {
			return ResponseEntity.ok(service.add(scheduleModel));
		} catch (CrudValidationException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (CrudOperationException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping("/add/all")
public ResponseEntity<?> addAll(@RequestBody List<ScheduleModel> scheduleModels) {
    try {
        return ResponseEntity.ok(service.addAll(scheduleModels));
    } catch (CrudValidationException e) {
        log.error("Validation error while adding schedule list", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    } catch (CrudOperationException e) {
        log.error("Operation error while adding schedule list", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}


	@GetMapping("/get/count")
	public ResponseEntity<?> count() {
		try {
			return ResponseEntity.ok(service.count());
		} catch (CrudOperationException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	@DeleteMapping("/delete/all")
	public ResponseEntity<?> deleteAll() {
		try {
			service.deleteAll();
			return ResponseEntity.ok("Deleted successfully.");
		} catch (CrudValidationException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (CrudOperationException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	@DeleteMapping("/delete/by/id/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		try {
			service.deleteById(id);
			return ResponseEntity.ok("Deleted successfully.");
		} catch (CrudValidationException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (CrudOperationException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody ScheduleModel model) {
		try {
			return ResponseEntity.ok(service.update(model));
		} catch (CrudValidationException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (CrudOperationException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}