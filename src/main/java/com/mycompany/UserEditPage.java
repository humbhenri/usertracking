package com.mycompany;

import java.util.Date;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import com.mycompany.dao.UserDAO;
import com.mycompany.model.User;

public class UserEditPage extends WebPage {

    private User user;

    @SpringBean
    private UserDAO userDAO;

    public UserEditPage(final PageParameters parameters) {
        super();
        Form<User> form = new Form<User>("form") {
            @Override
            protected void onSubmit() {
                super.onSubmit();
                userDAO.save(user);
                Session.get().getFeedbackMessages().success(null, "User added");
                setResponsePage(UserListPage.class);
            }
        };
        form.setDefaultModel(new CompoundPropertyModel<WebPage>(this));

        form.add(new RequiredTextField<String>("user.firstName").add(StringValidator.lengthBetween(1,
                User.FIRST_NAME_LEN)));
        form.add(new RequiredTextField<String>("user.lastName").add(StringValidator
                .lengthBetween(1, User.LAST_NAME_LEN)));
        form.add(new RequiredTextField<String>("user.email").add(EmailAddressValidator.getInstance()));
        form.add(new RequiredTextField<Date>("user.birthDate"));
        form.add(new Button("cancel").add(new AjaxEventBehavior("onclick") {

            @Override
            protected void onEvent(AjaxRequestTarget target) {
                setResponsePage(UserListPage.class);
            }

        }));

        add(new FeedbackPanel("feedbackMessage", new ExactErrorLevelFilter(FeedbackMessage.ERROR)));
        add(new FeedbackPanel("succesMessage", new ExactErrorLevelFilter(FeedbackMessage.SUCCESS)));

        add(form);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
