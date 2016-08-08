package test;

import java.util.List;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;
import junit.framework.TestCase;

public class CarroTest extends TestCase {

	private CarroService carroService;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		// Cria o bean pelo spring
		carroService = (CarroService) SpringUtil.getInstance().getBean(CarroService.class);
	}

	public void testListaCarros() {
		List<Carro> carros = carroService.getCarros();
		assertNotNull(carros);

		// Valida se encontrou algo
		assertTrue(carros.size() > 0);

		// Valida se encontrou o Tucker
		Carro tucker = carroService.findByName("Tucker 1948").get(0);
		assertEquals("Tucker 1948", tucker.getNome());

		// Valida se encontrou a ferrari
		Carro ferrari = carroService.findByName("Ferrari FF").get(0);
		assertEquals("Ferrari FF", ferrari.getNome());

		// Valida se encontrou o Bugatti
		Carro bugatti = carroService.findByName("Bugatti Veyron").get(0);
		assertEquals("Bugatti Veyron", bugatti.getNome());
	}

	public void testSalvarDeletarCarro() {
		Carro c = new Carro();
		c.setNome("Teste");
		c.setDesc("Teste Desc");
		c.setUrlFoto("teste urlfoto");
		c.setUrlVideo("teste urlvideo");
		c.setLatitude("teste latitude");
		c.setLongitude("longitude");
		c.setTipo("tipo");
		carroService.save(c);

		// id do carro salvo
		Long id = c.getId();
		assertNotNull(id);

		// Busca no banco de dados para confirmar se o carro foi salvo
		c = carroService.getCarro(id);
		assertEquals("Teste", c.getNome());
		assertEquals("Teste Desc", c.getDesc());
		assertEquals("teste urlfoto", c.getUrlFoto());
		assertEquals("teste urlvideo", c.getUrlVideo());
		assertEquals("teste latitude", c.getLatitude());
		assertEquals("longitude", c.getLongitude());
		assertEquals("tipo", c.getTipo());

		// atualiza carro
		c.setNome("Teste Update");
		carroService.save(c);

		// Busca carro novamente
		c = carroService.getCarro(id);
		assertEquals("Teste Update", c.getNome());

		// Deleta o carro
		carroService.delete(id);

		// Busca carro novamente
		c = carroService.getCarro(id);

		// Desta vez o carro nao existe
		assertNull(c);

	}
}
