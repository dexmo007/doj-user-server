package web.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * validates the password
 */
@FacesValidator("pwValidator")
public class PasswordValidator implements Validator {

    public static final int MIN_LENGTH = 5;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if (!IdValidator.validationRequired())
            return;

        String pw = (String) o;
        if (pw == null || pw.isEmpty() || pw.length() < MIN_LENGTH) {
            FacesMessage msg = new FacesMessage("Password invalid (must be at least 5 characters)");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
