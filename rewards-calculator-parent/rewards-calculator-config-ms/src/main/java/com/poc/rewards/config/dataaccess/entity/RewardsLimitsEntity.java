package com.poc.rewards.config.dataaccess.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "REWARDS_CONFIG")
@AllArgsConstructor
@NoArgsConstructor
public class RewardsLimitsEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rewards_config_generator")
	@SequenceGenerator(name = "rewards_config_generator", sequenceName = "rewards_config_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name = "LOWER_LIMIT")
	private Integer lowerLimit;
	
	@Column(name = "UPPER_LIMIT")
	private Integer upperLimit;
	
	@Column(name = "POINTS")
	private Integer points;
	
	@Column(name = "CREAE_DT")
	private Timestamp createdDate;
	
	@Column(name = "UPDATE_DT")
	private Timestamp updateDate;

}
