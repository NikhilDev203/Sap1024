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
import de.hybris.platform.acceleratorservices.order.strategies.impl.CustomerServiceUncollectedConsignmentStrategy;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.order.data.ConsignmentData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.ConsignmentProcessModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.FormatFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.mockito.Mockito.when;


/**
 * Unit test for {@link SearchPagePointOfServiceDistancePopulator}.
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class ConsigmentCollectionremindEmailContextTest
{
	@Mock
	private EmailPageModel emailPageModel;
	@Mock
	Converter<ConsignmentModel, ConsignmentData> consignmentConverter;
	@Mock
	Converter<OrderModel, OrderData> orderConverter;
	@Mock
	private ConsignmentProcessModel  consignmentProcessModel;
	@Mock
	private AbstractOrderModel abstractOrderModel;
	@Mock
	private ConsignmentModel consignmentModel;
	@Mock
	private CustomerModel customerModel;
	@Mock
	private CustomerType customerType;
	@Mock
	private CustomerServiceUncollectedConsignmentStrategy customerServiceUncollectedConsignmentStrategy;
	@Mock
	private FormatFactory formatFactory;
	@InjectMocks
	private ConsignmentCollectionReminderEmailContext consignmentCollectionReminderEmailContext;

	@Test
	public void testInit()
	{
		ConsignmentCollectionReminderEmailContext spy = Mockito.spy(new ConsignmentCollectionReminderEmailContext ());
		Mockito.doNothing().when((AbstractEmailContext<ConsignmentProcessModel>)spy).init(consignmentProcessModel,emailPageModel);
		when(consignmentProcessModel.getConsignment()).thenReturn(consignmentModel);
		when(consignmentModel.getOrder()).thenReturn(abstractOrderModel);
		when(consignmentModel.getShippingDate()).thenReturn(new Date());
		when(abstractOrderModel.getCode()).thenReturn("code");
		when(abstractOrderModel.getGuid()).thenReturn("guid");
		when(abstractOrderModel.getUser()).thenReturn(customerModel);
		when(abstractOrderModel.getSite()).thenReturn(new BaseSiteModel());
		when(customerModel.getType()).thenReturn(customerType);
		when(customerServiceUncollectedConsignmentStrategy.getTimeThreshold()).thenReturn(2);
		spy.init(consignmentProcessModel,emailPageModel);
		Assert.assertNotNull( abstractOrderModel);
	}

}
