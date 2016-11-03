package web.validation;

import data.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

/**
 * validates if id not present in DB
 */
@ManagedBean
@RequestScoped
public class IdValidator implements Validator {

    @Inject
    private UserService userService;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if (!validationRequired())
            return;
        String id = (String) o;
        if (o == null || id.trim().isEmpty()) {
            FacesMessage msg = new FacesMessage("Id must not be empty or blank spaces.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        if (userService.hasId(id)) {
            FacesMessage msg = new FacesMessage("Id already exists.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public static boolean validationRequired() {
        String skipValidation = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("skipValidation");
        if (skipValidation != null && skipValidation.equalsIgnoreCase("true")) {
            return false;
        }
        return true;
    }
}
