package pl.tcs.po.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ExceptionModel {
    private LocalDateTime timestamp;
    private int status;
    private String message;
}