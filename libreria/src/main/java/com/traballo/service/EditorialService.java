package com.traballo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.traballo.entity.Editorial;
import com.traballo.repository.EditorialRepository;

@Service
public class EditorialService {

    private final EditorialRepository editorialRepository;

    public EditorialService(EditorialRepository editorialRepository) {
        this.editorialRepository = editorialRepository;
    }

    public Editorial save(Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    public List<Editorial> findAll() {
        return editorialRepository.findAll();
    }

    public Optional<Editorial> findById(Long id) {
        return editorialRepository.findById(id);
    }

    public void deleteById(Long id) {
        editorialRepository.deleteById(id);
    }
}