package com.wolverinesolution.lendingengine.application.service;

import com.wolverinesolution.lendingengine.domain.model.User;

public interface TokenValidationService {

    User validateToken(final String token);
}
