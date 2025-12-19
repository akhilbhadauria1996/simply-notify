package com.simply.notify.notification_service.service;

import java.time.Instant;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.simply.notify.notification_service.dto.CreateNotificationRequest;
import com.simply.notify.notification_service.entity.Notification;
import com.simply.notify.notification_service.repo.NotificationRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {
	
	private final NotificationRepo notificationRepo;
	private final RabbitTemplate rabbitTemplate;
	
	@Value("${notification.exchange}")
	private String exchange;
	@Value("${notification.routing-key}")
	private String key;
	
	public Notification createAndEnque(CreateNotificationRequest request) {
		Notification notification = Notification.builder()
				.userId(request.getUserId())
				.subject(request.getSubject())
				.body(request.getBody())
				.type(request.getType())
				.status("Pending")
				.toAddr(request.getTo())
				.attempts(0)
				.createdAt(Instant.now())
				.updatedAt(Instant.now())
				.build();
		
		notification = notificationRepo.save(notification);
		rabbitTemplate.convertAndSend(exchange, key, notification.getId().toString());
		return notification;
	}

}
