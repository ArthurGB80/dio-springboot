package dio.springboot.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SistemaMensagem {
    private final Remetente noreply;
    private final Remetente techTeam;
    private final Logger logger;

    public SistemaMensagem(Remetente noreply, Remetente techTeam) {
        this.noreply = noreply;
        this.techTeam = techTeam;
        this.logger = LoggerFactory.getLogger(SistemaMensagem.class);
    }

    public void enviarConfirmacaoCadastro() {
        logger.info(noreply.toString());
        logger.info("Seu cadastro foi aprovado");
    }

    public void enviarMensagemBoasVindas() {
        techTeam.setEmail("tech@dio.com.br");
        logger.info(techTeam.toString());
        logger.info("Bem-vindo à Tech Elite");
    }
}