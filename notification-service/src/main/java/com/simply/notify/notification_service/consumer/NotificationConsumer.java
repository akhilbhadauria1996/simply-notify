package com.simply.notify.notification_service.consumer;

import java.time.Instant;
import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simply.notify.notification_service.entity.Notification;
import com.simply.notify.notification_service.repo.NotificationRepo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationConsumer {

	private final NotificationRepo notificationRepo;

	@RabbitListener(queues = "notifications.queue")
	public void handle(String notificationIdStr) {
		
		try {
			Long notificationId = Long.parseLong(notificationIdStr);
			notificationRepo.findById(notificationId).ifPresent(
					n -> {
						try {
							System.out.println("Processing Notification with id - "+n.getId()+" to - "+n.getToAddr());
							n.setStatus("SENT");
							n.setAttempts(n.getAttempts() == null ? 0 : n.getAttempts()+1);	
							n.setUpdatedAt(Instant.now());
							notificationRepo.save(n);
						}catch (Exception e) {
							System.out.println("Notification not found for id - "+ n.getId());
							n.setAttempts(n.getAttempts() == null ? 0 : n.getAttempts()+1);	
							if(n.getAttempts() >= 3) n.setStatus("Failed");
							else n.setStatus("Pending");
							notificationRepo.save(n);
						}
					});
			
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid notification id "+ notificationIdStr);
		}
		
		
	}
}
