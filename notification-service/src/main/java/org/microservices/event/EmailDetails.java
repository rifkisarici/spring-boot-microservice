package org.microservices.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class EmailDetails {
    String recipient;
    String messageBody;
    String subject;
}
