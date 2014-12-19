package com.mycompany;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class UserListPage extends WebPage {

    public UserListPage(final PageParameters parameters) {
        add(new Link<Void>("addUser") {

            @Override
            public void onClick() {
                setResponsePage(UserEditPage.class);
            }

        });
    }

}
