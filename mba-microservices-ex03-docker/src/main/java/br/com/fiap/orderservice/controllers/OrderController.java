package br.com.fiap.orderservice.controllers;

import br.com.fiap.orderservice.dto.OrderDTO;
import br.com.fiap.orderservice.exceptions.EntityNotFoundException;
import br.com.fiap.orderservice.exceptions.EntityNotUpdatedException;
import br.com.fiap.orderservice.exceptions.InternalServerErrorException;
import br.com.fiap.orderservice.repository.OrderRepository;
import br.com.fiap.orderservice.dto.PaymentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import java.util.ArrayList;

@RestController
@RequestMapping("orders")
@Api(value = "Order", description = "ORDER RESTAPI")

public class OrderController {
	
	private OrderRepository repository = new OrderRepository();

    @GetMapping()
    @ApiOperation(httpMethod = "GET", value = "ALL ORDERS")
	@ApiResponses(value = {
		@ApiResponse(
            code = 200,
            message = "ALL ORDERS",
            response = ArrayList.class
        )
    })
    public ResponseEntity<ArrayList<OrderDTO>> all() throws InternalServerErrorException {

        return new ResponseEntity<>(repository.all(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    
    @ApiOperation(httpMethod = "GET", value = "GET ORDER BY ID")

    @ApiResponses(value = {
				@ApiResponse(
		            code = 200,
		            message = "GET ORDER BY ID",
		            response = OrderDTO.class
	        ),
				@ApiResponse(
		            code = 404,
		            message = "ORDER NOT FOUND"
		        )
	    })
    public ResponseEntity<OrderDTO> findById(
    		@PathVariable(value = "uuid", required = true)
    		@ApiParam( value = "ID", required = true)
    		String uuid) throws EntityNotFoundException {
    	
    	OrderDTO orderDTO = repository.findById(uuid);
    	
    	if (orderDTO != null) {						
    		return new ResponseEntity<>(orderDTO, HttpStatus.OK);
		} else {			
			throw new EntityNotFoundException("ORDER NOT FOUND");
		}
    }

    @PostMapping()
    
    @ApiOperation(httpMethod = "POST", value = "INPUT ORDER")
    
	@ApiResponses(value = {
			@ApiResponse(
	            code = 200,
	            message = "INPUT ORDER",
	            response = OrderDTO.class
	        )
	    })
    public ResponseEntity<OrderDTO> save(@RequestBody OrderDTO orderDTO) throws InternalServerErrorException {
    	
		OrderDTO order = repository.save(orderDTO);

		return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    
    @ApiOperation(httpMethod = "PUT", value = "UPDATE ORDER")
    
	@ApiResponses(value = {
			@ApiResponse(
	            code = 200,
	            message = "UPDATE ORDER",
	            response = OrderDTO.class
	        ),
			@ApiResponse(
		            code = 400,
		            message = "UPDATE ERROR"
		        )
	    })
    public ResponseEntity<OrderDTO> update(
    		@ApiParam( value = "ORDER ID", required = true)
    		
    		@PathVariable("uuid") String uuid, @RequestBody OrderDTO orderDTO) throws EntityNotUpdatedException {
    	
    	OrderDTO order = repository.update(uuid, orderDTO);
    	
    	if (order != null) {			
			
    		return new ResponseEntity<>(order, HttpStatus.OK);
			
		} else {
			
			throw new EntityNotUpdatedException("ORDER NOT FOUND");
		}
    }

    @DeleteMapping("/{uuid}")
    
    @ApiOperation(httpMethod = "DELETE", value = "DELETE ORDER")
	
    @ApiResponses(value = {
			@ApiResponse(
	            code = 200,
	            message = "DELETE ORDER",
	            response = OrderDTO.class
	        ),
			@ApiResponse(
		            code = 404,
		            message = "ORDER NOT FOUND"
		        )
	    })
    public ResponseEntity<OrderDTO> delete(
    		@ApiParam( value = "ORDER ID", required = true)
    		@PathVariable("uuid") String uuid) throws EntityNotFoundException {

    	OrderDTO orderDTO = repository.delete(uuid);
    	
    	if (orderDTO != null) {						
    		return new ResponseEntity<>(orderDTO, HttpStatus.OK);
		} else {			
			throw new EntityNotFoundException("ORDER NOT FOUND");
		}
    }

}
