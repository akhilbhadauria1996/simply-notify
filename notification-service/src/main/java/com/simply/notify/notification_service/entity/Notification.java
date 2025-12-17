package com.simply.notify.notification_service.entity;

import java.time.Instant;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private String type;
	private String toAddr;
	private String subject;
	
	private String body;
	
	private String status; //Pending, Failed, Sent
	private Integer attempts;
	private Instant createdAt;
	private Instant updatedAt;

}
