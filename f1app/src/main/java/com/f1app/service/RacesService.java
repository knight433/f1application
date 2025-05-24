package com.f1app.service;

import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;
import java.util.Optional;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f1app.CrudOperationException;
import com.f1app.CrudValidationException;
import com.f1app.model.RacesModel;
import com.f1app.repository.RacesRepo;
import java.util.List;


@Service
public class RacesService {

    @Autowired
    private RacesRepo racesRepo;

    private Logger log = LogManager.getLogger(getClass());

    @Autowired
    private Validator validator;

    private void checkForNull(RacesModel model) throws CrudOperationException {
        if (model == null) {
            throw CrudOperationException.asNullEntity(RacesModel.class);
        }
    }

    private void validate(RacesModel model) throws CrudValidationException {
        Set<ConstraintViolation<RacesModel>> violations = validator.validate(model);
        if (!violations.isEmpty()) {
            throw CrudValidationException.asFailedValidationOperation(RacesModel.class, violations);
        }
    }

    private void checkId(long id) throws CrudValidationException {
        if (id <= 0) {
            throw CrudValidationException.asInvalidEntityId(RacesModel.class);
        }
    }

    private RacesModel save(RacesModel model) throws CrudOperationException {
        try {
            boolean isNew = model.getId() <= 0;
            RacesModel saved = racesRepo.save(model);
            log.info((isNew ? "Added" : "Updated") + " RacesModel with ID: " + saved.getId());
            return saved;
        } catch (Exception e) {
            throw CrudOperationException.asFailedAddOperation(RacesModel.class, e);
        }
    }

    public RacesModel getById(Long id) throws CrudOperationException, CrudValidationException {
        checkId(id);
        Optional<RacesModel> result = racesRepo.findById(id);
        checkForNull(result.get());
        return result.get();
    }

    public RacesModel addNewRacesModel(RacesModel model) throws CrudValidationException, CrudOperationException {
        checkForNull(model);
        validate(model);
        
        return save(model);
    }

    public List<RacesModel> getAll() throws CrudOperationException {
        List<RacesModel> result = racesRepo.findAll();
        if (result.isEmpty()) {
            throw CrudOperationException.asNullEntity(RacesModel.class);
        }
        return result;
    }
}
