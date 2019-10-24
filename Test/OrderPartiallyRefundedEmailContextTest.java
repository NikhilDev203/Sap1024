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
import de.hybris.platform.acceleratorservices.document.context.AbstractHybrisVelocityContext;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.orderprocessing.model.OrderModificationProcessModel;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.order.OrderModel;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static de.hybris.platform.commercefacades.product.data.PriceDataType.BUY;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;


/**
 * Unit test for {@link SearchPagePointOfServiceDistancePopulator}.
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class OrderPartiallyRefundedEmailContextTest
{
	@Mock
	private EmailPageModel emailPageModel;
	@Mock
	Converter<UserModel, CustomerData> customerConverter;
	@Mock
	PriceData totalPrice;
	@Mock
	private OrderModificationProcessModel orderProcessModel;
	@Mock
	private OrderModel orderModel;
	@Mock
	private OrderEntryData orderEntryData;
	@InjectMocks
	OrderPartiallyModifiedEmailContext orderPartiallyModifiedEmailContext;
	@InjectMocks
	OrderPartiallyRefundedEmailContext orderPartiallyRefundedEmailContext;
	@Mock
	PriceDataFactory priceDataFactory;
	@Mock
	PriceData refundAmount;
	@Mock
	AbstractHybrisVelocityContext abstractHybrisVelocityContext;

	@Test
	public void testInit()
	{
		MockitoAnnotations.initMocks(this);
		List<OrderEntryData> list=new ArrayList<>();
		list.add(orderEntryData);
		OrderPartiallyRefundedEmailContext spy = Mockito.spy(new OrderPartiallyRefundedEmailContext ());
		Mockito.doNothing().when((OrderPartiallyModifiedEmailContext)spy).init(any(),any());
		when(orderProcessModel.getOrder()).thenReturn(orderModel);
		when(orderModel.getSite()).thenReturn(new BaseSiteModel());
		when(orderEntryData.getTotalPrice()).thenReturn(totalPrice);
		when(totalPrice.getValue()).thenReturn(BigDecimal.valueOf(123.8));
		when(totalPrice.getPriceType()).thenReturn(BUY);
		when(totalPrice.getCurrencyIso()).thenReturn("iso");
		when(priceDataFactory.create(any(),any(),anyString())).thenReturn(refundAmount);
		spy.init(orderProcessModel,emailPageModel);
		Assert.assertNotNull(orderPartiallyRefundedEmailContext.getRefundAmount());
	}

}
