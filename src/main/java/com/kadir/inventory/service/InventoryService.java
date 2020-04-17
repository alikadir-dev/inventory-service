/**
 * 
 */
package com.kadir.inventory.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.kadir.inventory.model.Inventory;

/**
 * @author Mohammad Kadir Ali
 *
 */
public interface InventoryService
{

	public void addInventory(Inventory inventory);

	public List<Inventory> getInventoryList();

	public List<Inventory> getByProductName(String itemName);

	public Optional<Inventory> getById(String id);
}
