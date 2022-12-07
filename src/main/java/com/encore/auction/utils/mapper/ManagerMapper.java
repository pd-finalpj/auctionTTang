package com.encore.auction.utils.mapper;

import com.encore.auction.controller.manager.responses.ManagerDeleteResponse;
import com.encore.auction.controller.manager.responses.ManagerDetailsResponse;
import com.encore.auction.controller.manager.responses.ManagerIdResponse;
import com.encore.auction.model.manager.Manager;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ManagerMapper {

	private static ManagerMapper managerMapper = null;

	public static ManagerMapper of() {
		if (managerMapper == null) {
			managerMapper = new ManagerMapper();
		}
		return managerMapper;
	}

	public ManagerIdResponse entityToManagerIdResponse(Manager manager) {
		return new ManagerIdResponse(manager.getId());
	}

	public ManagerDetailsResponse entityToManagerDetailsResponse(Manager manager) {
		return new ManagerDetailsResponse(manager.getId(), manager.getName(), manager.getAge(), manager.getNickname(),
			manager.getPhoneNumber(), manager.getEmail());
	}

	public ManagerDeleteResponse entityToManagerDeleteResponse(Manager manager) {
		return new ManagerDeleteResponse(manager.getId(), manager.getState());
	}






}
