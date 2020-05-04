package com.mferreira.coopapi.controller.documentation;


import com.mferreira.coopapi.vo.SessaoEntryVO;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

public interface SessaoControllerDocumentation {
    @ApiOperation(value = "Inserir uma nova sessão", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Sessão cadastrada com sucesso"),
            @ApiResponse(code = 400, message = "Parâmetros incorretos informados. SessionId e/ou pauta inválidas"),
            @ApiResponse(code = 500, message = "Erro interno no processamento da requisição")
    })
    ResponseEntity create(
            @ApiParam(name = "payload", value = "VO com os atributos da sessão") SessaoEntryVO payload);
    @ApiOperation(value = "Listar todas as sessões", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Sucesso ao consultar registro de sessões"),
            @ApiResponse(code = 500, message = "Erro interno no processamento da requisição")
    })
    ResponseEntity getAll();
    @ApiOperation(value = "Buscar pelo id da sessão", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Sucesso na consulta da sessão pelo id"),
            @ApiResponse(code = 404, message = "Sessão não encontrada com o id informado"),
            @ApiResponse(code = 500, message = "Erro interno no processamento da requisição")
    })
    ResponseEntity get(
            @ApiParam(name = "id", value = "Id da sessão.") Long id);
}
