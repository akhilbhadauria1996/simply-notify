package com.simply.notify.notification_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simply.notify.notification_service.dto.CreateNotificationRequest;
import com.simply.notify.notification_service.entity.Notification;
import com.simply.notify.notification_service.service.NotificationService;

@RestController
@RequestMapping(value = "/notification")
public class NotificationController {
	
	@Autowired
	NotificationService notificationService;
	
	
	@PostMapping
	public ResponseEntity<Notification> createNotification(@RequestBody CreateNotificationRequest createNotificationRequest) {
		Notification notification = notificationService.createAndEnque(createNotificationRequest);
		return ResponseEntity.accepted().body(notification);
	}
	
//	public Notification getNotification() {
//		notificationService.g
//	}

}
