package co.edu.unicauca.microserviceproject.infra.dto;

import lombok.Getter;


@Getter
public class ProjectStatusRequest {
    private Long projectId;
    private String action;

}
