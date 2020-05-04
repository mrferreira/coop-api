package com.mferreira.coopapi.controller.documentation;


import com.mferreira.coopapi.vo.ResultadoVotacaoVO;
import com.mferreira.coopapi.vo.VotacaoEntryVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface VotoControllerDocumentation {
    @ApiOperation(value = "Registrar um voto.", httpMethod = "POST")
    @ApiResponses({@ApiResponse(code = 201, message = "Voto registrado com sucesso"),
            @ApiResponse(code = 404, message = "Sessão não encontrada com o sessionId informado"),
            @ApiResponse(code = 500, message = "Erro interno no processamento da requisição")})
    ResponseEntity votar(
            @ApiParam(name = "sessionId", value = "Id da sessão da votação") Long sessionId,
            @ApiParam(name = "payload", value= "VO contendo os atributos da votação") VotacaoEntryVO payload
    );

    @ApiOperation(value = "Exibir resultado da votação", httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 201, message = "Sucesso ao consultar resultado da votação"),
            @ApiResponse(code = 404, message = "Sessão não encontrada com o sessionId informado"),
            @ApiResponse(code = 500, message = "Erro interno no processamento da requisição")})
    ResponseEntity<ResultadoVotacaoVO> contabilizarResultado(
            @ApiParam(name = "sessionId", value = "Id da sessão a ser consultada") Long sessionId
    );
}
