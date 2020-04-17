/**
 * 
 */
package com.kadir.inventory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.kadir.inventory.model.Inventory;

/**
 * @author Mohammad Kadir Ali
 *
 */

@Repository
public interface InventoryRepository extends MongoRepository<Inventory, String>
{
	/**
	 * Write your own Method which is not present in the CrudRepository
	 * Interface
	 */
	Optional<Inventory> findById(String id);

	@Query(value = "{'productArray.productName':?0}")
	List<Inventory> findByProductName(String itemName);

}
