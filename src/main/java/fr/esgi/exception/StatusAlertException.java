package fr.esgi.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class StatusAlertException extends AbstractThrowableProblem {

	private static final long serialVersionUID = 1L;

    private final String entityName;

    private final String errorKey;

    public StatusAlertException(Status status, String defaultMessage, String entityName, String errorKey) {
        this(status, ErrorConstants.DEFAULT_TYPE, defaultMessage, entityName, errorKey);
    }

    public StatusAlertException(Status status, URI type, String defaultMessage, String entityName, String errorKey) {
        super(type, defaultMessage, status, null, null, null, getAlertParameters(entityName, errorKey));
        this.entityName = entityName;
        this.errorKey = errorKey;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getErrorKey() {
        return errorKey;
    }

    private static Map<String, Object> getAlertParameters(String entityName, String errorKey) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", "error." + errorKey);
        parameters.put("params", entityName);
        return parameters;
    }
}