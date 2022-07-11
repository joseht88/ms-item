package upeu.ms.app.service;

import java.util.List;

import upeu.ms.app.entity.Item;

public interface ItemService {

	public List<Item> findAll();
	
	public Item findById(Long id, Integer cantidad);
}
