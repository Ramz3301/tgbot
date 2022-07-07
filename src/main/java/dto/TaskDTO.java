package dto;

import lombok.*;

@Data
public class TaskDTO {
    private String description;
    private int timeInMinutes;
}