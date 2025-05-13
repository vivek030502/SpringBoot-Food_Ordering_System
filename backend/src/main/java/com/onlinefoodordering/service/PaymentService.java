package com.onlinefoodordering.service;

import com.onlinefoodordering.model.Order;
import com.onlinefoodordering.response.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {
	
	public PaymentResponse createPaymentLink(Order order) throws StripeException;

}
