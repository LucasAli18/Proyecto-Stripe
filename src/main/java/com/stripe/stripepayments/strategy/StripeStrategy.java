package com.stripe.stripepayments.strategy;

import com.stripe.model.Event;

public interface StripeStrategy {
	
	boolean isApplicable(Event evento);
	Event process(Event event);
}
