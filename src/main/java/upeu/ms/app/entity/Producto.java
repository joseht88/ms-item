package upeu.ms.app.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Producto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;
	private Double precio;
	private Date createAt;
	private Integer port;
}
