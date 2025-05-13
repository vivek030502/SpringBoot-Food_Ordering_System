package com.onlinefoodordering.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.onlinefoodordering.model.Order;
import com.onlinefoodordering.response.PaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Value("${stripe.apikey}")
	private String stripeApiKey;

	@Override
	public PaymentResponse createPaymentLink(Order order) throws StripeException {
		Stripe.apiKey = stripeApiKey;
		
		SessionCreateParams params = SessionCreateParams.builder()
			    .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
			    .setMode(SessionCreateParams.Mode.PAYMENT)
			    .setSuccessUrl("http://localhost:3000/payment/success/" + order.getId())
			    .setCancelUrl("http://localhost:3000/payment/fail")
			    .addLineItem(
			        SessionCreateParams.LineItem.builder()
			            .setQuantity(1L)
			            .setPriceData(
			                SessionCreateParams.LineItem.PriceData.builder()
			                    .setCurrency("usd") // ✅ currency is set here
			                    .setUnitAmount((long) (order.getTotalPrice() * 100)) // Stripe expects amount in cents/paise
			                    .setProductData(
			                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
			                            .setName("my food")
			                            .build()
			                    )
			                    .build()
			            )
			            .build()
			    )
			    .build();

		
		
		Session session = Session.create(params);
		
		PaymentResponse res = new PaymentResponse();
		res.setPayment_url(session.getUrl());
		
		return res;
	}

}
