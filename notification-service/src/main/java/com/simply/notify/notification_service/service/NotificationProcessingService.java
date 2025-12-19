package com.simply.notify.notification_service.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.simply.notify.notification_service.entity.Notification;
import com.simply.notify.notification_service.repo.NotificationRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProcessingService {

	private static final int MAX_ATTEMPTS = 3;

	private final NotificationRepo notificationRepo;

	@Transactional
	public void processNotification(Long notificationId) {
		//TODO later need to study how here the lamda expression is working.
		Notification n = notificationRepo.findById(notificationId).orElseThrow(
				() -> new RuntimeException("Notification not found :- "+ notificationId));
		try {
			log.info("Processing Notification with id - " + n.getId() + " to - " + n.getToAddr());

			sendNotification(n);

			n.setStatus("SENT");
			n.setAttempts(n.getAttempts() == null ? 1 : n.getAttempts() + 1);

		} catch (Exception e) {
			log.error("Failed to send notification {} ", n.getId(), e);
			n.setAttempts(n.getAttempts() == null ? 1 : n.getAttempts() + 1);
			if (n.getAttempts() >= MAX_ATTEMPTS)
				n.setStatus("FAILED");
			else
				n.setStatus("PENDING");
		}

		n.setUpdatedAt(Instant.now());
		notificationRepo.save(n);
	}

	private void sendNotification(Notification notification) {
		
		throw new RuntimeException("Simulate faliure");

	}

}
