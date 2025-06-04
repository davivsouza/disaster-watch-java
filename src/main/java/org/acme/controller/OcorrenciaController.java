package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.OcorrenciaEntity;
import org.acme.service.OcorrenciaService;

import java.util.List;

@Path("/api/ocorrencias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OcorrenciaController {

  @Inject
  OcorrenciaService ocorrenciaService;

  @GET
  public List<OcorrenciaEntity> listAll() {
    return ocorrenciaService.listAll();
  }

  @GET
  @Path("/{id}")
  public Response getById(@PathParam("id") Long id) {
    try {
      return Response.ok(ocorrenciaService.findById(id)).build();
    } catch (NotFoundException e) {
      return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    }
  }

  @POST
  public Response create(OcorrenciaEntity ocorrencia) {
    try {
      // Ensure tipoEvento.idTipoEvento is passed in the request payload for
      // Ocorrencia
      if (ocorrencia.tipoEvento == null || ocorrencia.tipoEvento.idTipoEvento == null) {
        return Response.status(Response.Status.BAD_REQUEST)
            .entity("idTipoEvento é obrigatório dentro do objeto tipoEvento.").build();
      }
      return Response.status(Response.Status.CREATED).entity(ocorrenciaService.create(ocorrencia)).build();
    } catch (NotFoundException e) {
      return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    } catch (IllegalArgumentException e) {
      return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    } catch (Exception e) {
      return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao criar Ocorrência: " + e.getMessage()).build();
    }
  }

  @PUT
  @Path("/{id}")
  public Response update(@PathParam("id") Long id, OcorrenciaEntity ocorrencia) {
    try {
      if (ocorrencia.tipoEvento != null && ocorrencia.tipoEvento.idTipoEvento == null) {
        // If tipoEvento is provided for update, its ID must be there
        return Response.status(Response.Status.BAD_REQUEST)
            .entity("Se tipoEvento for fornecido para atualização, seu idTipoEvento é obrigatório.").build();
      }
      return Response.ok(ocorrenciaService.update(id, ocorrencia)).build();
    } catch (NotFoundException e) {
      return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    } catch (Exception e) {
      return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao atualizar Ocorrência: " + e.getMessage())
          .build();
    }
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") Long id) {
    try {
      ocorrenciaService.delete(id);
      return Response.noContent().build();
    } catch (NotFoundException e) {
      return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    } catch (Exception e) {
      return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao deletar Ocorrência: " + e.getMessage())
          .build();
    }
  }
}