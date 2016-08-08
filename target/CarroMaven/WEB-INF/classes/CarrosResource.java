import java.awt.PageAttributes.MediaType;

import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.livro.domain.CarroService;
import br.com.livro.domain.Response;

@Path("/carros")
@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
public class CarrosResource {
	//Antes do Spring
	private CarroService carroService = new CarroService();
	
	@GET
	public List<Carro> get()
	{
		List<Carro> carros = carroService.getCarros();
		return carros;
	}
	
	@GET
	@Path("{id}")
	public Carro get(@PathParam("id")long id)
	{
		Carro c = carroService.getCarro(id);
		return c;
	}
	
	@GET
	@Path("/tipo/{tipo}")
	public List<Carro> getByTipo(@PathParam("tipo")String tipo)
	{
		List<Carro> carros = carroService.findByTipo(tipo);
		return carros;
	}
	
	@GET
	@Path("nome/{nome}")
	public List<Carro> getByNome(@PathParam("nome")String nome)
	{
		List<Carro> carros = carroService.findByName(nome);
		return carros;
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id")long id)
	{
		carroService.delete(id);
		return Response.Ok("Carro deletado com sucesso!");
	}
	
	@POST
	public Response post(Carro c)
	{
		carroService.save(c);
		return Response.Ok("Carro salvo com sucesso!");
	}
	
	@PUT
	public Response put(Carro c)
	{
		carroService.save(c);
		return Response.Ok("Carro atualizado com sucesso!");
	}
}
