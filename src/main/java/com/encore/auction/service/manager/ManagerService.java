package com.encore.auction.service.manager;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.encore.auction.controller.manager.requests.ManagerLoginRequest;
import com.encore.auction.controller.manager.requests.ManagerUpdateRequest;
import com.encore.auction.controller.manager.responses.ManagerDeleteResponse;
import com.encore.auction.controller.manager.responses.ManagerDetailsResponse;
import com.encore.auction.controller.manager.responses.ManagerIdResponse;
import com.encore.auction.controller.user.responses.UserDetailsResponse;
import com.encore.auction.exception.NonExistResourceException;
import com.encore.auction.exception.WrongRequestException;
import com.encore.auction.model.manager.Manager;
import com.encore.auction.model.user.User;
import com.encore.auction.repository.ManagerRepository;
import com.encore.auction.utils.encrypt.Encrypt;
import com.encore.auction.utils.mapper.ManagerMapper;
import com.encore.auction.utils.mapper.UserMapper;

@Service
public class ManagerService {

	private final ManagerRepository managerRepository;

	public ManagerService(ManagerRepository managerRepository) {
		this.managerRepository = managerRepository;
	}


	public ManagerIdResponse loginManager(ManagerLoginRequest managerLoginRequest) {
		Manager manager = checkManagerExistAndCheckPasswordIsCorrect(managerLoginRequest.getManagerId(),
			managerLoginRequest.getPassword());

		return ManagerMapper.of().entityToManagerIdResponse(manager);
	}
	// 이제 할부분

	public ManagerDetailsResponse retrieveManager(String managerId) {
		Manager manager = managerRepository.findById(managerId)
			.orElseThrow(() -> new NonExistResourceException("Manager does not exist"));

		return ManagerMapper.of().entityToManagerDetailsResponse(manager);
	}

	@Transactional
	public ManagerDetailsResponse updateManager(String managerId, ManagerUpdateRequest managerUpdateRequest){
		Manager manager = checkManagerExistAndCheckPasswordIsCorrect(managerId, managerUpdateRequest.getOldPassword());

		if (!managerUpdateRequest.getNewPassword().equals(managerUpdateRequest.getPasswordCheck()))
			throw new WrongRequestException("Manager Password is incorrect with Password check");

		String newSalt = Encrypt.of().getSalt();

		String encryptedPassword = Encrypt.of().getEncrypt(managerUpdateRequest.getNewPassword(), newSalt);

		manager.updateManager(managerUpdateRequest, encryptedPassword, newSalt);

		return ManagerMapper.of().entityToManagerDetailsResponse(manager);
	}

	public ManagerDeleteResponse deleteManager(String managerId, String password) {
		Manager manager = checkManagerExistAndCheckPasswordIsCorrect(managerId, password);

		manager.deleteManager();

		return ManagerMapper.of().entityToManagerDeleteResponse(manager);

	}

	private Manager checkManagerExistAndCheckPasswordIsCorrect(String managerId, String managerPassword) {
		Manager manager = managerRepository.findById(managerId)
			.orElseThrow(() -> new NonExistResourceException("Manager does not exist"));

		if (!isManagerPasswordCorrect(managerPassword, manager))
			throw new WrongRequestException("Manager password in correct");
		return manager;
	}

	private boolean isManagerPasswordCorrect(String inputPassword, Manager manager) {
		return manager.getPassword().equals(Encrypt.of().getEncrypt(inputPassword, manager.getSalt()));
	}






















}