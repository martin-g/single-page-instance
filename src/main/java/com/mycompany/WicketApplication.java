package com.mycompany;

import org.apache.wicket.DefaultPageManagerProvider;
import org.apache.wicket.IPageFactory;
import org.apache.wicket.IPageManagerProvider;
import org.apache.wicket.Page;
import org.apache.wicket.core.request.mapper.MountedMapper;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.page.DefaultPageManagerContext;
import org.apache.wicket.page.IPageManager;
import org.apache.wicket.page.IPageManagerContext;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.session.DefaultPageFactory;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 */
public class WicketApplication extends WebApplication
{
	private SinglePageManager managerAndFactory;

	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return PageA.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		setPageManagerProvider(new IPageManagerProvider()
		{
			@Override
			public IPageManager get(IPageManagerContext context)
			{
				return managerAndFactory;
			}
		});

		mountPage("/a", PageA.class);
		mountPage("/b", PageB.class);
		mountPage("/c", PageC.class);
	}

	@Override
	public <T extends Page> MountedMapper mountPage(String path, Class<T> pageClass)
	{
		NoVersionMapper mapper = new NoVersionMapper(path, pageClass);
		mount(mapper);
		return mapper;
	}

	@Override
	protected IPageFactory newPageFactory()
	{
		IPageManagerProvider provider = new DefaultPageManagerProvider(WicketApplication.this);
		IPageManager delegate = provider.get(new DefaultPageManagerContext());

		IPageFactory factory = new DefaultPageFactory();

		managerAndFactory = new SinglePageManager(delegate, factory);
		return managerAndFactory;
	}
}
