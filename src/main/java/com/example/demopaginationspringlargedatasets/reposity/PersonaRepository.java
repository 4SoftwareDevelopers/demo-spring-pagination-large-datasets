package com.example.demopaginationspringlargedatasets.reposity;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demopaginationspringlargedatasets.model.Persona;

public interface PersonaRepository extends PagingAndSortingRepository<Persona, Integer> {
	
	

}
