package dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReportDTO {
    private LocalDate date;
    private List<TeamReportDTO> teamReports;
}