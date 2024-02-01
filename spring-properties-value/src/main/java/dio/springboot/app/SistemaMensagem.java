package dio.springboot.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SistemaMensagem implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(SistemaMensagem.class);

	@Value("${nome:NoReply-DIO}")
	private String nome;

	@Value("${email}")
	private String email;

	@Value("#{'${telefones:}'.split(',')}")
	private List<Long> telefones;

	@Override
	public void run(String... args) throws Exception {
		if (telefones == null || telefones.isEmpty()) {
			logger.error("Nenhum telefone foi informado.");
			return;
		}

		logger.info("Mensagem enviada por: {}", nome);
		logger.info("E-mail: {}", email);
		logger.info("Com telefones para contato: {}", telefones);
		logger.info("Seu cadastro foi aprovado");
	}
}