package com.f1app.service;

import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;

import java.time.LocalDateTime;
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
import com.f1app.model.UserModel;
import com.f1app.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private Logger log = LogManager.getLogger(getClass());

    @Autowired
    private Validator validator;

    private void checkForNull(UserModel model) throws CrudOperationException {
        if (model == null) {
            throw CrudOperationException.asNullEntity(UserModel.class);
        }
    }

    private void validate(UserModel model) throws CrudValidationException {
        Set<ConstraintViolation<UserModel>> violations = validator.validate(model);
        if (!violations.isEmpty()) {
            throw CrudValidationException.asFailedValidationOperation(UserModel.class, violations);
        }
    }

    private UserModel save(UserModel model) throws CrudOperationException {
        try {
            boolean isNew = model.getId() <= 0;
            UserModel saved = userRepo.save(model);
            log.info((isNew ? "Added" : "Updated") + " UserModel with ID: " + saved.getId());
            return saved;
        } catch (Exception e) {
            throw CrudOperationException.asFailedAddOperation(UserModel.class, e);
        }
    }

    public UserModel validateUser(String username, String rawPassword) throws CrudValidationException {
        Optional<UserModel> optionalUser = userRepo.findByUsername(username);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            System.out.println("User: " + user.getPassword() + " Raw Password: " + rawPassword);
            if (rawPassword.equals(user.getPassword())) {
                return user;
            }
            else{
                throw new CrudValidationException("Incorrect Password a test");
            }
        }
        else{
            throw new CrudValidationException("User does not exits");
        }
    }

    public List<UserModel> getAll(int page, int size) throws CrudOperationException {
        try {
            Pageable pageable = PageRequest.of(
                page < ServiceConstants.STARTING_PAGE_NUMBER ? ServiceConstants.STARTING_PAGE_NUMBER : page,
                size <= 0 ? ServiceConstants.DEFAULT_ITEMS_PER_PAGE : size);
            return userRepo.findAll(pageable).getContent();
        } catch (Exception e) {
            throw CrudOperationException.asFailedGetOperation(getClass(), e);
        }
    }

    public UserModel addNewUserModel(UserModel model) throws CrudValidationException, CrudOperationException {
        checkForNull(model);
        String rawPassword = model.getPassword();
        model.setCreatedAt(LocalDateTime.now());
        model.setUpdatedAt(LocalDateTime.now());
        model.setPassword(rawPassword);
        validate(model);        
        return save(model);
    }

    public UserModel updateUser(UserModel model) throws CrudValidationException, CrudOperationException
    {
        checkForNull(model);
        UserModel foundModel = userRepo.findById(model.getId()).get();
        checkForNull(foundModel);
        foundModel.update(model);
        validate(foundModel);
        return save(foundModel);
    }

    public long getCountOfusers() throws CrudOperationException {
        try {
            return userRepo.count();
        } catch (Exception e) {
            throw CrudOperationException.asFailedGetOperation(UserModel.class, e);
        }
    }
}
