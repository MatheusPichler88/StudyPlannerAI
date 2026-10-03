package com.ufn.studyplannerai.service;

import com.ufn.studyplannerai.entity.MateriaEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatGptService {

    private final WebClient webClient;
    private final String apiKey = System.getenv("API_KEY");


    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> gerarCronograma(List<MateriaEntity> materiasEntity) {

        String materias = materiasEntity.stream()
                .map(materia -> String.format("- %s (Área: %s, Dificuldade: %s) - Horas: %dh, Prova: %s",
                        materia.getNome(),
                        materia.getArea(),
                        materia.getDificuldade(),
                        materia.getHorasEstudadas(),
                        materia.getDataProva()))
                .collect(Collectors.joining("\n"));
        String prompt = """
                Atue como um professor e orientador de estudos. Monte um cronograma baseado nos dados:
                
                - Data Atual: %s
                - Horas Disponíveis por Dia: %dh
                - Matérias com Prova Futura:
                %s
                
                REGRAS:
                1. Use APENAS as matérias fornecidas, sem inventar outras.
                2. Priorize provas mais próximas e matérias de maior dificuldade.
                3. Respeite o limite diário de horas e reserve a véspera de cada prova para revisão.
                
                ESTRUTURA OBRIGATÓRIA DA RESPOSTA:
                1. Visão geral das prioridades
                2. Cronograma dia a dia até a próxima prova
                3. Dicas de método de estudo para cada matéria
                """;
        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4o-mini",
                "messages", List.of(
                        Map.of("role", "system", "content", """
                        Você é um professor e orientador de estudos experiente. Sua tarefa é criar cronogramas
                        de estudo altamente otimizados e realistas com base nas matérias e restrições fornecidas.
                        
                        Siga sempre e rigorosamente a estrutura solicitada na mensagem do usuário.
                        """),
                        Map.of("role", "user", "content", prompt)
                )
        );
        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var choices = (List<Map<String, Object>>) response.get("choices");
                    if (choices != null && !choices.isEmpty()) {
                        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                        return message.get("content").toString();
                    }
                    return "Nenhum cronograma foi gerado.";
                });
    }
}
