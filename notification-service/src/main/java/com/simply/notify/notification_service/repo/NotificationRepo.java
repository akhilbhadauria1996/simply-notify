package com.simply.notify.notification_service.repo;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simply.notify.notification_service.entity.Notification;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {

	public List<Notification> findByAttemptsLessThanAndStatus(int attempts, String status);
	
	public List<Notification> findByAttemptsLessThanAndStatusAndNextRetryAtLessThanEqual(int attempts, String status, Instant nextRetry);
}
