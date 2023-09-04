package com.example.demopaginationspringlargedatasets.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demopaginationspringlargedatasets.model.Persona;
import com.example.demopaginationspringlargedatasets.reposity.PersonaRepository;

@Controller
public class PersonaController {

	@Autowired
	private PersonaRepository personaRepository;

	@GetMapping(value = "/")
	public String findAll(@PageableDefault(size = 10, page = 0) Pageable pageable, Model model) {
		Page<Persona> page = personaRepository
				.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
		
		model.addAttribute("page", page);
		var totalPages = page.getTotalPages();
		var currentPage = page.getNumber();
		
		var start = Math.max(1, currentPage);
		var end = Math.min(currentPage + 5, totalPages);
		
		if (totalPages > 0) {
			List<Integer> pageNumbers = new ArrayList<>();
			for (int i = start; i <= end; i++) {
				pageNumbers.add(i);
			}
			
			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		
		List<Integer> pageSizeOptions = Arrays.asList(10,20, 50, 100);
		model.addAttribute("pageSizeOptions", pageSizeOptions);
		
		
		return "index";
	}
}
