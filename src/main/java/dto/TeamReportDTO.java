package dto;

import lombok.*;

import java.util.List;

@Data
public class TeamReportDTO {
    private String teamName;
    private List<UserReportDTO> userReports;
}