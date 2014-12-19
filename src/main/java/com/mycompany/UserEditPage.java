package com.mycompany;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class UserEditPage extends WebPage {

    public UserEditPage(final PageParameters parameters) {
        super();
        Form<Void> form = new Form<Void>("form") {
            @Override
            protected void onSubmit() {
                super.onSubmit();
                success("User added");
            }
        };

        add(new FeedbackPanel("feedbackMessage", new ExactErrorLevelFilter(FeedbackMessage.ERROR)));
        add(new FeedbackPanel("succesMessage", new ExactErrorLevelFilter(FeedbackMessage.SUCCESS)));

        add(form);
    }

}

class ExactErrorLevelFilter implements IFeedbackMessageFilter {
    private int errorLevel;

    public ExactErrorLevelFilter(int errorLevel) {
        this.errorLevel = errorLevel;
    }

    public boolean accept(FeedbackMessage message) {
        return message.getLevel() == errorLevel;
    }

}
