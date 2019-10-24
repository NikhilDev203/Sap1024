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
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.model.process.ForgottenPasswordProcessModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


/**
 * Unit test for {@link SearchPagePointOfServiceDistancePopulator}.
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class ForgottenPasswordEmailContextTest
{
	@Mock
	private EmailPageModel emailPageModel;
	@Mock
	Converter<UserModel, CustomerData> customerConverter;
	@Mock
	Converter<OrderModel, OrderData> orderConverter;
	@InjectMocks
	private ForgottenPasswordEmailContext  forgottenPasswordEmailContext;
	@Mock
	private ForgottenPasswordProcessModel storeFrontCustomerProcessModel;
	@Mock
	private CustomerModel customerModel;
	@Mock
	private BaseSiteModel baseSiteModel;

	@Test
	public void testInit()
	{
		MockitoAnnotations.initMocks(this);
		ForgottenPasswordEmailContext spy = Mockito.spy(new ForgottenPasswordEmailContext ());
		Mockito.doNothing().when((CustomerEmailContext)spy).init(any(),any());
		when(storeFrontCustomerProcessModel.getToken()).thenReturn("token");
		forgottenPasswordEmailContext.init(storeFrontCustomerProcessModel,emailPageModel);
		Assert.assertEquals("token", forgottenPasswordEmailContext.getToken());
	}

}
