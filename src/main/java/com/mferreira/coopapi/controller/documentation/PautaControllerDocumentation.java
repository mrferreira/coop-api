package com.mferreira.coopapi.controller.documentation;


import com.mferreira.coopapi.vo.PautaEntryVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface PautaControllerDocumentation {
    @ApiOperation(value = "Inserir uma nova pauta.", httpMethod = "POST")
    @ApiResponses({@ApiResponse(code = 201, message = "Pauta cadastrada com sucesso."),
                @ApiResponse(code = 500, message = "Erro interno no processamento da requisição.")})
    ResponseEntity cadastrar(
            @ApiParam(name = "payload", value = "VO com os atributos da pauta.") PautaEntryVO payload
    );
    @ApiOperation(value = "Listar todas as pautas ativas", httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 200, message = "Sucesso ao consultar registro de pautas."),
            @ApiResponse(code = 404, message = "Pauta não encontrada com o id informado."),
            @ApiResponse(code = 500, message = "Erro interno no processamento da requisição.")})
    ResponseEntity listActive();
    @ApiOperation(value = "Buscar pelo id da pauta.", httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 200, message = "Sucesso na consulta da pauta pelo id."),
            @ApiResponse(code = 404, message = "Pauta não encontrada com o id informado."),
            @ApiResponse(code = 500, message = "Erro interno no processamento da requisição.")})
    ResponseEntity get(
            @ApiParam(name = "id", value = "Id da pauta a ser consultada") Long id
    );
}
