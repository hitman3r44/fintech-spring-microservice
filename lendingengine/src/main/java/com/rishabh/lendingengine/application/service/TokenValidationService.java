package com.rishabh.lendingengine.application.service;

import com.rishabh.lendingengine.domain.model.User;

public interface TokenValidationService {

    User validateToken(final String token);
}
