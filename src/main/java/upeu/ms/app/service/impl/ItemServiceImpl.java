package upeu.ms.app.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import upeu.ms.app.entity.Item;
import upeu.ms.app.entity.Producto;
import upeu.ms.app.service.ItemService;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private RestTemplate clienteRest;
	
	@Override
	public List<Item> findAll() {
		List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://servicio-producto/listar", Producto[].class));
		return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map<String, String> pathVariable = new HashMap<>();
		pathVariable.put("id", id.toString());
		var producto = clienteRest.getForObject("http://servicio-producto/ver/{id}",Producto.class, pathVariable);
		return new Item(producto, cantidad);
	}

}
