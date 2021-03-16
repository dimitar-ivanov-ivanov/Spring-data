package app.services;

import java.util.List;

import app.model.labels.BasicLabel;
import app.model.labels.Label;
import app.repositories.LabelRepository;
import app.services.interfaces.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelServiceImpl implements LabelService {

    private LabelRepository labelRepository;

    @Autowired
    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public BasicLabel findById(long id) {
        return this.labelRepository
                .findById(id)
                .get();
    }
}
