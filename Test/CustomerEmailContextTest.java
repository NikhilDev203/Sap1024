/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.hybris.mystore.facades.process.email.context;

import com.hybris.mystore.facades.storefinder.populators.SearchPagePointOfServiceDistancePopulator;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;


/**
 * Unit test for {@link SearchPagePointOfServiceDistancePopulator}.
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class CustomerEmailContextTest
{
	@Mock
	private EmailPageModel emailPageModel;
	@Mock
	Converter<UserModel, CustomerData> customerConverter;
	@Mock
	Converter<OrderModel, OrderData> orderConverter;
	@Mock
	private StoreFrontCustomerProcessModel  storeFrontCustomerProcessModel;
	@Mock
	private CustomerModel customerModel;
	@Mock
	private BaseSiteModel baseSiteModel;

	@Test
	public void testInit()
	{
		CustomerEmailContext spy = Mockito.spy(new CustomerEmailContext ());
		Mockito.doNothing().when((AbstractEmailContext<StoreFrontCustomerProcessModel>)spy).init(storeFrontCustomerProcessModel,emailPageModel);
		when(storeFrontCustomerProcessModel.getCustomer()).thenReturn(customerModel);
		when(storeFrontCustomerProcessModel.getSite()).thenReturn(baseSiteModel);
		spy.init(storeFrontCustomerProcessModel,emailPageModel);
		Assert.assertNotNull( customerModel);
	}

}
