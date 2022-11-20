package com.esprit.examen.controllers;



import java.util.List;
import java.util.Set;

import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.services.IStockService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.Setter;

@RestController
@Api(tags = "Gestion des stocks")
@RequestMapping("/stock")
@CrossOrigin("*")
public class StockRestController {

	@Autowired
	IStockService stockService;

	@GetMapping("/retrieve-all-stocks")
	@ResponseBody
	public List<Stock> getStocks() {
		return stockService.retrieveAllStocks();
		
	}

	@GetMapping("/retrieve-stock/{stock-id}")
	@ResponseBody
	public Stock retrieveStock(@PathVariable("stock-id") Long stockId) {
		return stockService.retrieveStock(stockId);
	}

	@PostMapping("/add-stock")
	@ResponseBody
	public Stock addStock(@RequestBody StockModel stockModel) {
	    Stock stock = new Stock();
        stock.setLibelleStock(stockModel.getLibelleStock());
        stock.setQte(stockModel.getQte());
        stock.setQteMin(stockModel.getQteMin());
        stockService.addStock(stock);
        return stockService.addStock(stock);
	}

	
	@DeleteMapping("/remove-stock/{stock-id}")
	@ResponseBody
	public void removeStock(@PathVariable("stock-id") Long stockId) {
		stockService.deleteStock(stockId);
	}

	@PutMapping("/modify-stock")
	@ResponseBody
	public Stock modifyStock(@RequestBody StockModel stockModel) {
	    
	    Stock stock = new Stock();
        stock.setLibelleStock(stockModel.getLibelleStock());
        stock.setQte(stockModel.getQte());
        stock.setQteMin(stockModel.getQteMin());
        stockService.addStock(stock);
		return stockService.updateStock(stock);
	}



}


@Getter
@Setter
class StockModel {
    
    private Long idStock;
    private String libelleStock;
    private Integer qte;
    private Integer qteMin;
    @OneToMany(mappedBy = "stock")
    @JsonIgnore
    private Set<Produit> produits;
    
}
