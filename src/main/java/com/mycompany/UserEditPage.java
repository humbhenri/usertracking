package com.mycompany;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import com.mycompany.dao.UserDAO;
import com.mycompany.model.User;

public class UserEditPage extends WebPage {

    private User user;

    @SpringBean
    private UserDAO userDAO;

    public UserEditPage(User user) {
        this.user = user;
    }

    public UserEditPage() {
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(newForm());
        add(new FeedbackPanel("feedbackMessage", new ExactErrorLevelFilter(FeedbackMessage.ERROR)));
        add(new FeedbackPanel("successMessage", new ExactErrorLevelFilter(FeedbackMessage.SUCCESS)));
    }

    private Form<User> newForm() {
        Form<User> form = new Form<User>("form") {
            @Override
            protected void onSubmit() {
                super.onSubmit();
                saveOrUpdateUser();
            }

        };
        form.setDefaultModel(new CompoundPropertyModel<WebPage>(this));

        form.add(new RequiredTextField<String>("user.firstName").add(StringValidator.lengthBetween(1,
                User.FIRST_NAME_LEN)));
        form.add(new RequiredTextField<String>("user.lastName").add(StringValidator
                .lengthBetween(1, User.LAST_NAME_LEN)));
        form.add(new RequiredTextField<String>("user.email").add(EmailAddressValidator.getInstance()));
        form.add(new DateTextField("user.birthDate"));
        form.add(new Button("cancel").add(new AjaxEventBehavior("onclick") {

            @Override
            protected void onEvent(AjaxRequestTarget target) {
                setResponsePage(UserListPage.class);
            }

        }));
        return form;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void saveOrUpdateUser() {
        String msg = "User updated";
        if (user.getId() == null) {
            msg = "User added";
        }
        userDAO.save(user);
        getSession().success(msg);
        setResponsePage(UserListPage.class);
    }

}
