package app.services.interfaces;

import app.model.labels.BasicLabel;
import app.model.labels.Label;

public interface LabelService {

    BasicLabel findById(long id);
}
