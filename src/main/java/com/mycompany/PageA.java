package com.mycompany;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

public class PageA extends WebPage {
	private static final long serialVersionUID = 1L;

	private int counter;

	public PageA(final PageParameters parameters) {
		super(parameters);

		Link<Void> increment = new Link<Void>("increment")
		{
			@Override
			public void onClick()
			{
				counter++;
				getPage().dirty();
			}
		} ;

		Label show = new Label("show", new PropertyModel<Integer>(this, "counter"));
		add(increment, show);
    }
}
