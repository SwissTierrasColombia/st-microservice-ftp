package com.ai.st.microservice.ftp.controllers.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ai.st.microservice.ftp.business.FTPBusiness;
import com.ai.st.microservice.ftp.dto.BasicResponseDto;
import com.ai.st.microservice.ftp.dto.CreateUserDto;
import com.ai.st.microservice.ftp.exceptions.BusinessException;
import com.ai.st.microservice.ftp.exceptions.InputValidationException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "FTP", tags = { "FTP" })
@RestController
@RequestMapping("api/managers/v1/ftp")
public class FTPV1Controller {

	private final Logger log = LoggerFactory.getLogger(FTPV1Controller.class);

	@Autowired
	private FTPBusiness FTPBusiness;

	@RequestMapping(value = "/add-user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create Manager")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Create Manager", response = BasicResponseDto.class),
			@ApiResponse(code = 500, message = "Error Server", response = String.class) })
	@ResponseBody
	public ResponseEntity<Object> createUser(@RequestBody CreateUserDto createUserDto) {

		HttpStatus httpStatus = null;
		Object responseDto = null;

		try {

			// validation user name
			String username = createUserDto.getUsername();
			if (username == null || username.isEmpty()) {
				throw new InputValidationException("El nombre del usuario es requerido.");
			}

			// validation password
			String password = createUserDto.getPassword();
			if (password == null || password.isEmpty()) {
				throw new InputValidationException("La contraseña es requerida.");
			}

			FTPBusiness.createUserFTP(username, password);
			responseDto = new BasicResponseDto("Credenciales creadas!", 7);
			httpStatus = HttpStatus.CREATED;

		} catch (InputValidationException e) {
			log.error("Error FTPV1Controller@FTPV1Controller#Validation ---> " + e.getMessage());
			httpStatus = HttpStatus.BAD_REQUEST;
			responseDto = new BasicResponseDto(e.getMessage(), 1);
		} catch (BusinessException e) {
			log.error("Error FTPV1Controller@FTPV1Controller#Business ---> " + e.getMessage());
			httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
			responseDto = new BasicResponseDto(e.getMessage(), 2);
		} catch (Exception e) {
			log.error("Error FTPV1Controller@FTPV1Controller#General ---> " + e.getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			responseDto = new BasicResponseDto(e.getMessage(), 3);
		}

		return new ResponseEntity<>(responseDto, httpStatus);
	}

}
