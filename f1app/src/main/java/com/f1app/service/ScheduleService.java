package com.f1app.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import com.f1app.model.ScheduleModel;
import com.f1app.repository.ScheduleRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import com.f1app.CrudOperationException;
import com.f1app.CrudValidationException;

@Service
public class ScheduleService {
	@Autowired
	private ScheduleRepository repository;
	@Autowired
	private Validator validator;
	private final Logger log = LogManager.getLogger(getClass());

	public ScheduleService() {
	}

	public void checkForNull(ScheduleModel model) throws CrudOperationException {
		if (model == null) {
			throw CrudOperationException.asNullEntity(ScheduleModel.class);
		}
	}

	private void validate(ScheduleModel model) throws CrudValidationException {
		Set<ConstraintViolation<ScheduleModel>> violations = validator.validate(model);
		if (!violations.isEmpty()) {
			throw CrudValidationException.asFailedValidationOperation(ScheduleModel.class, violations);
		}
	}

	private void checkId(long id) throws CrudValidationException {
		if (id <= 0) {
			throw CrudValidationException.asInvalidEntityId(ScheduleModel.class);
		}
	}

	public ScheduleModel save(ScheduleModel model) throws CrudOperationException {
		try {
			boolean isNew = model.getId() <= 0;
			ScheduleModel savedModel = repository.save(model);
			log.info((isNew ? "Added" : "Updated") + " ScheduleModel with ID: " + savedModel.getId());
			return savedModel;
		} catch (Exception exception) {
			throw CrudOperationException.asFailedAddOperation(ScheduleModel.class, exception);
		}
	}

	public List<ScheduleModel> getAll() {
		try {
			return repository.findAll();
		} catch (Exception exception) {
			throw CrudOperationException.asFailedGetOperation(ScheduleModel.class, exception);
		}
	}

	public ScheduleModel getById(long id) throws CrudOperationException, CrudValidationException {
		checkId(id);
		try {
			Optional<ScheduleModel> result = repository.findById(id);
			if (result.isEmpty()) {
				throw CrudOperationException.asEntityNotFound(ScheduleModel.class, id);
			}
			return result.get();
		} catch (Exception exception) {
			throw CrudOperationException.asFailedGetOperation(ScheduleModel.class, exception);
		}
	}

	public ScheduleModel add(ScheduleModel model) throws CrudValidationException, CrudOperationException {
		checkForNull(model);
		validate(model);
		return save(model);
	}

	public List<ScheduleModel> addAll(List<ScheduleModel> scheduleModels)
			throws CrudValidationException, CrudOperationException {
		try {
	
			for (ScheduleModel model : scheduleModels) {
				validate(model); 
			}
			return repository.saveAll(scheduleModels);
		} catch (DataIntegrityViolationException e) {
			throw new CrudValidationException("Data integrity violation: " + e.getMessage());
		} catch (Exception e) {
			throw new CrudOperationException();
		}
	}

	public ScheduleModel update(ScheduleModel model) throws CrudValidationException, CrudOperationException {
		checkForNull(model);
		Optional<ScheduleModel> existing = repository.findById(model.getId());
		if (existing.isEmpty()) {
			throw CrudOperationException.asEntityNotFound(ScheduleModel.class, model.getId());
		}
		ScheduleModel existingModel = existing.get();
		validate(model);
		existingModel.updateFrom(model);
		return save(existingModel);
	}

	public long count() throws CrudOperationException {
		try {
			return repository.count();
		} catch (Exception exception) {
			throw CrudOperationException.asFailedGetOperation(ScheduleModel.class, exception);
		}
	}

	public long deleteAll() {
		try {
			long count = repository.count();
			repository.deleteAll();
			return count;
		} catch (Exception exception) {
			throw CrudOperationException.asFailedDeleteOperation(ScheduleModel.class, exception);
		}
	}

	public void deleteById(long id) {
		try {
			repository.deleteById(id);
		} catch (Exception exception) {
			throw CrudOperationException.asFailedDeleteOperation(ScheduleModel.class, exception);
		}
	}
}
