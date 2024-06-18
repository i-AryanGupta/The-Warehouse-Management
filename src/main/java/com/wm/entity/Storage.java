package com.wm.entity;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.wm.enums.MaterialTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Storage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int storageId;
	private String blockName;
	private String section;
	private double maxAdditionalInKg;
	private double availableArea;
	
	@Enumerated(EnumType.STRING)
	private List<MaterialTypes> materialTypes;
	
	@ManyToOne
	private WareHouse warehouse;
	
	@ManyToOne
	private StorageType storageType;
}
