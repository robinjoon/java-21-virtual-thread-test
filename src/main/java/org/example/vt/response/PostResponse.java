package org.example.vt.response;

public record PostResponse(
        Long id,
        String title,
        String content,
        BoardResponse boardResponse,
        WriterResponse writerResponse
) {
}
