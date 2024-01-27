package dio.springboot.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SistemaMensagem implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(SistemaMensagem.class);

	@Autowired
	private Rementente remetente;

	@Override
	public void run(String... args) throws Exception {
		if (remetente == null) {
			logger.error("Remetente is null");
			return;
		}

		logger.info("Mensagem enviada por: {}", remetente.getNome());
		logger.info("E-mail: {}", remetente.getEmail());
		logger.info("Com telefones para contato: {}", remetente.getTelefones());
		logger.info("Seu cadastro foi aprovado");
	}
}