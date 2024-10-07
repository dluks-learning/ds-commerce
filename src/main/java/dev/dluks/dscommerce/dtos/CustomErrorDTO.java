package dev.dluks.dscommerce.dtos;

import java.time.Instant;

public record CustomErrorDTO(
        Instant timestamp,
        Integer status,
        String error,
        String path
) {

}
