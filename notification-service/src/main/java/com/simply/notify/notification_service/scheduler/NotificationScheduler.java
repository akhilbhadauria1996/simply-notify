package com.simply.notify.notification_service.scheduler;

import java.time.Instant;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.simply.notify.notification_service.entity.Notification;
import com.simply.notify.notification_service.repo.NotificationRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationScheduler {
	
	private final NotificationRepo notificationRepo;
	private final RabbitTemplate rabbitTemplate;
	@Value("${notification.exchange}")
	private String exchange;
	@Value("${notification.routing-key}")
	private String key;
	
	@Scheduled(fixedDelay = 10000)
	public void retryPendingNotifications() {
		log.info("============ processing the scheduler ============");
		try {
			List<Notification> notificationList =  notificationRepo.findByAttemptsLessThanAndStatusAndNextRetryAtLessThanEqual(3, "PENDING", Instant.now());
			log.info("============ sending notifications to "+notificationList.size()+" users ============");
			for(Notification n : notificationList) {
				rabbitTemplate.convertAndSend(exchange, key, n.getId().toString());
			}
		}catch (Exception e) {
			log.error("failed to get notification list using findByAttemptsLessThanAndStatus() ", e);
		}	
	}
}
