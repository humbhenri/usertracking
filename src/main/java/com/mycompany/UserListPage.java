package com.mycompany;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.mycompany.dao.UserDAO;
import com.mycompany.model.User;

public class UserListPage extends WebPage {

    @SpringBean
    private UserDAO userDAO;

    public UserListPage(final PageParameters parameters) {
        addLinkToNewUserPage();
        addUserList();
        add(new FeedbackPanel("feedbackMessage", new ExactErrorLevelFilter(FeedbackMessage.ERROR)));
        add(new FeedbackPanel("successMessage", new ExactErrorLevelFilter(FeedbackMessage.SUCCESS)));
    }

    private void addLinkToNewUserPage() {
        add(new Link<Void>("addUser") {

            @Override
            public void onClick() {
                setResponsePage(UserEditPage.class);
            }

        });
    }

    private void addUserList() {
        add(new ListView<User>("userList", userDAO.findAll()) {

            @Override
            protected void populateItem(ListItem<User> item) {
                final User user = (User) item.getDefaultModelObject();
                item.add(new Label("id", PropertyModel.of(user, "id")));
                item.add(new Label("firstName", PropertyModel.of(user, "firstName")));
                item.add(new Label("lastName", PropertyModel.of(user, "lastName")));
                item.add(new Label("email", PropertyModel.of(user, "email")));
                item.add(new Label("birthDate", PropertyModel.of(user, "birthDate")));
                item.add(new Link<Void>("edit") {
                    @Override
                    public void onClick() {
                        setResponsePage(new UserEditPage(user));
                    }
                });
            }
        });
    }

}
