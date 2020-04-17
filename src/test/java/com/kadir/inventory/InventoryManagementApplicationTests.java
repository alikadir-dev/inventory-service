package com.kadir.inventory;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kadir.inventory.controller.InventoryController;
import com.kadir.inventory.model.Inventory;
import com.kadir.inventory.model.Product;


/**
 * @author Mohammad Kadir Ali
 */

@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
@WebMvcTest(InventoryController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class InventoryManagementApplicationTests
{
	
	@Autowired
	private WebApplicationContext context;

	@Autowired
	private MockMvc mockMvc;

	List<Inventory> orders = null;

	@BeforeEach
	public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation)
	{

		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(documentationConfiguration(restDocumentation)).build();
		List<Product> products = new ArrayList<Product>();
		products.add(new Product("Laptop", 1920393, 32000.0, "Electronics", "Dell Laptop", 1.1));
		products.add(new Product("Acer Laptop", 1920383, 40000.0, "Electronics", "Acer Laptop", 1.2));

		orders = Stream.of(new Inventory(products, "Aamir", "10-04-2020", "Credit Card")).collect(Collectors.toList());
	}

	@Test
    public void testAddOrder() throws Exception {
        String ordersJson=new ObjectMapper().writeValueAsString(orders);
        mockMvc.perform(post("/inventory/addInventory")
                .content(ordersJson)
                .contentType("application/json")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(ordersJson))
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    public void testGetOrders() throws Exception {
           mockMvc.perform(get("/inventory/getAllInventory")
                   .contentType("application/json")).andDo(print())
                   .andExpect(status().isOk())
                   .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(orders)))
                   .andDo(document("{methodName}",
                           preprocessRequest(prettyPrint()),
                           preprocessResponse(prettyPrint())));
    }


}
