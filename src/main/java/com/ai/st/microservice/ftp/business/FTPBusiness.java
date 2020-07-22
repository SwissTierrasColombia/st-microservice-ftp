package com.ai.st.microservice.ftp.business;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ai.st.microservice.ftp.exceptions.BusinessException;

@Component
public class FTPBusiness {

	private final Logger log = LoggerFactory.getLogger(FTPBusiness.class);

	public String createUserFTP(String username, String password) throws BusinessException {

		StringBuffer output = new StringBuffer();

		Process p;
		try {

			String command = "docker exec st-ftpd bash add_user.sh " + username + " " + password;

			log.info("Executing command: " + command);

			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

			log.info("Command result: " + output.toString());

		} catch (Exception e) {
			log.error("Error executin command: " + e.getMessage());
		}

		return output.toString();
	}

}
