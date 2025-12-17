package com.simply.notify.notification_service.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateNotificationRequest {

	private Long userId;
	@NotBlank
	private String type;
	@NotBlank
	private String to;
	private String subject;
	private String body;
	
}
