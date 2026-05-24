package fiap._tdspo.clyvoHelper.clyvoHelper.service;

import fiap._tdspo.clyvoHelper.clyvoHelper.dto.AIRequest;
import fiap._tdspo.clyvoHelper.clyvoHelper.dto.DiagnosticoDto;
import fiap._tdspo.clyvoHelper.clyvoHelper.dto.DiagnosticoResponseDto;
import fiap._tdspo.clyvoHelper.clyvoHelper.dto.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

@Service
public class DiagnosticoService {

    private final RestTemplate restTemplate;

    @Value("${openrouter.api.key}")
    private String apiKey;

    public DiagnosticoService(
            RestTemplate restTemplate
    ) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(
            value = "diagnosticos",
            key = "#dto.especie + #dto.sintomas"
    )
    public DiagnosticoResponseDto gerarDiagnostico(
            DiagnosticoDto dto
    ) {
        try {

            String prompt = """
                    Voce e um veterinario.
                    
                    Analise os dados abaixo e gere
                    um diagnostico preliminar curto.
                    
                    Especie: %s
                    Idade: %d
                    Peso: %.2f
                    Sintomas: %s
                    """
                    .formatted(
                            dto.getEspecie(),
                            dto.getIdade(),
                            dto.getPeso(),
                            dto.getSintomas()
                    );

            // call AI API here
            AIRequest aiRequest = new AIRequest();

            aiRequest.setModel("openrouter/owl-alpha");
            aiRequest.setMessages(
                    List.of(
                            new Message(
                                    "user",
                                    prompt
                            )
                    )
            );
            HttpHeaders headers =
                    new HttpHeaders();

            headers.setContentType(
                    MediaType.APPLICATION_JSON
            );

            headers.setBearerAuth(apiKey);

            HttpEntity<AIRequest> entity =
                    new HttpEntity<>(
                            aiRequest,
                            headers
                    );

            ResponseEntity<Map> response =
                    restTemplate.postForEntity(
                            "https://openrouter.ai/api/v1/chat/completions",
                            entity,
                            Map.class
                    );

            Map body = response.getBody();

            List choices =
                    (List) body.get("choices");

            Map firstChoice =
                    (Map) choices.get(0);

            Map message =
                    (Map) firstChoice.get("message");

            String respostaIA =
                    (String) message.get("content");

            return new DiagnosticoResponseDto(
                    respostaIA
            );
        }catch (Exception e){
            throw new RuntimeException(e);
        }}
}