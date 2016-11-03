package web.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * validates if not empty
 */
@FacesValidator("notEmptyValidator")
public class NotEmptyValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if (!IdValidator.validationRequired())
            return;
        String value = (String) o;
        if (value == null || value.isEmpty()) {
            FacesMessage msg = new FacesMessage("Value is required.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
