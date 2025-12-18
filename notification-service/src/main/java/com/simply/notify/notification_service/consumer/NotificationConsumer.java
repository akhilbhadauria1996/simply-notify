package com.simply.notify.notification_service.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.simply.notify.notification_service.service.NotificationProcessingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationConsumer {

	private final NotificationProcessingService notificationProcessingService;

	@RabbitListener(queues = "notifications.queue")
	public void handle(String notificationIdStr) {

		log.info("==================inside listener handler==================");

		try {
			Long notificationId = Long.parseLong(notificationIdStr);

			notificationProcessingService.processNotification(notificationId);

		} catch (NumberFormatException e) {
			System.out.println("Invalid notification id " + notificationIdStr);
		}

	}
}
