package com.vozhov.caesarapi.payload.request;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectRequest {
    Long id;
    String name;
    String description;
    String author;
    String responsible;
    Date startDate;
    Date deadline;
}
