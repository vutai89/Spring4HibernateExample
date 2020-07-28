package com.sport.api.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sport.api.entities.User;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		String userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser_id();
		String createBy = "";
		if (!userId.equals("")) {
			createBy = userId;
		}
		return Optional.of(createBy);
	}

}
