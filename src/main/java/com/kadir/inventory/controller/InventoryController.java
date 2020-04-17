package com.kadir.inventory.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.kadir.inventory.model.Inventory;
import com.kadir.inventory.service.InventoryService;

/**
 * @author Mohammad Kadir Ali
 */

@RestController
@RequestMapping(value = "/inventory")
public class InventoryController
{

	@Autowired
	InventoryService inventoryService;

	/**
	 * @param List of Product
	 * @param orderBy
	 * @param paymentMode
	 * @return message : Inventory Added Successfully
	 */

	@PostMapping(value = "/addInventory", headers = "Accept=application/json")
	public ResponseEntity<Void> createInventory(@RequestBody Inventory inventory, UriComponentsBuilder ucBuilder)
	{
		inventoryService.addInventory(inventory);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/Inventory/{id}").buildAndExpand(inventory.getOrderBy()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	/**
	 * @param {id} - Inventory Id
	 * @return List of Inventory associated with that Id
	 */

	@GetMapping("/{id}")
	public Optional<Inventory> getInventryById(@PathVariable("id") String id)
	{
		return inventoryService.getById(id);
	}

	/**
	 * @return List of All Inventory
	 */

	@GetMapping(value = "/getAllInventory", headers = "Accept=application/json")
	public List<Inventory> getAllInventory()
	{
		return inventoryService.getInventoryList();
	}

	/**
	 * @param {id}  - item
	 * @return List of All Inventory associate with particular item
	 */

	@GetMapping("/product/{item}")
	public List<Inventory> getAllInventryByProductName(@PathVariable("item") String item)
	{
		return inventoryService.getByProductName(item);
	}
}
