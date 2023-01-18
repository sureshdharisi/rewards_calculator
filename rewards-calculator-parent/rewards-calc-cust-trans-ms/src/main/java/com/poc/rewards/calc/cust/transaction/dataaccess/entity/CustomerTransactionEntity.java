package com.poc.rewards.calc.cust.transaction.dataaccess.entity;

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
@Table(name = "CUST_TRANSACTIONS")
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTransactionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust_transaction_id_generator")
	@SequenceGenerator(name = "cust_transaction_id_generator", sequenceName = "cust_transaction_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name = "CUSTMER_ID")
	private String customerId;
	
	@Column(name = "TRANSACTION_AMT")
	private Integer transactionAmt;
	
	@Column(name = "TRANSACTION_DT")
	private Timestamp transactionDateTime;
	
	@Column(name = "CREAE_DT")
	private Timestamp createdDate;
	

}
