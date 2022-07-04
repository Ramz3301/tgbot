package dto;

import lombok.*;

import java.util.List;

@Data
public class UserReportDTO {
    private String fullName;
    private List<TaskDTO> tasks;
}