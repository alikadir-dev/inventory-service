/**
 * 
 */
package com.kadir.inventory.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kadir.inventory.model.Inventory;
import com.kadir.inventory.repository.InventoryRepository;
import com.kadir.inventory.service.InventoryService;

/**
 * @author Mohammad Kadir Ali
 *
 */
@Service
@Transactional
public class InventoryServiceImpl implements InventoryService
{

	@Autowired
	InventoryRepository inventoryRepository;

	@Override
	public void addInventory(Inventory inventory)
	{
		inventoryRepository.save(inventory);
	}

	@Override
	public List<Inventory> getInventoryList()
	{
		return (List<Inventory>) inventoryRepository.findAll();
	}

	@Override
	public List<Inventory> getByProductName(String itemName)
	{
		return inventoryRepository.findByProductName(itemName);
	}

	@Override
	public Optional<Inventory> getById(String id)
	{
		return inventoryRepository.findById(id);
	}

}
