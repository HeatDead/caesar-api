package com.vozhov.caesarapi.payload.request;

import com.vozhov.caesarapi.entity.ProjectStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ProjectRequest {
    Long id;
    String name;
    String description;
    ProjectStatus status;
    String author;
    String responsible;
    Date startDate;
    Date deadline;
}
