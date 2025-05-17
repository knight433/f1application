package com.f1app.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.f1app.CrudOperationException;
import com.f1app.CrudValidationException;
import com.f1app.ServiceConstants;
import com.f1app.model.TrackModel;
import com.f1app.repository.TrackRepo;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Service
public class TrackService {

    @Autowired
    private TrackRepo trackRepo;

    private Logger log = LogManager.getLogger(getClass());

    @Autowired
    private Validator validator;

    private void checkForNull(TrackModel model) throws CrudOperationException {
        if (model == null) {
            throw CrudOperationException.asNullEntity(TrackModel.class);
        }
    }

    private void validate(TrackModel model) throws CrudValidationException {
        Set<ConstraintViolation<TrackModel>> violations = validator.validate(model);
        if (!violations.isEmpty()) {
            throw CrudValidationException.asFailedValidationOperation(TrackModel.class, violations);
        }
    }

    private void checkId(long id) throws CrudValidationException {
        if (id <= 0) {
            throw CrudValidationException.asInvalidEntityId(TrackModel.class);
        }
    }

    private TrackModel save(TrackModel model) throws CrudOperationException {
        try {
            boolean isNew = model.getId() <= 0;
            TrackModel saved = trackRepo.save(model);
            log.info((isNew ? "Added" : "Updated") + " TrackModel with ID: " + saved.getId());
            return saved;
        } catch (Exception e) {
            throw CrudOperationException.asFailedAddOperation(TrackModel.class, e);
        }
    }

    public TrackModel getById(Long id) throws CrudOperationException, CrudValidationException {
        checkId(id);
        Optional<TrackModel> result = trackRepo.findById(id);
        checkForNull(result.get());
        return result.get();
    }

    public List<TrackModel> getAll(int page, int size) throws CrudOperationException {
        try {
            Pageable pageable = PageRequest.of(
                page < ServiceConstants.STARTING_PAGE_NUMBER ? ServiceConstants.STARTING_PAGE_NUMBER : page,
                size <= 0 ? ServiceConstants.DEFAULT_ITEMS_PER_PAGE : size);
            return trackRepo.findAll(pageable).getContent();
        } catch (Exception e) {
            throw CrudOperationException.asFailedGetOperation(getClass(), e);
        }
    }

    public TrackModel addNewTrackModel(TrackModel model) throws CrudValidationException, CrudOperationException {
        checkForNull(model);
        //add any backend update here 
        validate(model);        return save(model);
    }

    public long getCountOftracks() throws CrudOperationException {
        try {
            return trackRepo.count();
        } catch (Exception e) {
            throw CrudOperationException.asFailedGetOperation(TrackModel.class, e);
        }
    }
}
