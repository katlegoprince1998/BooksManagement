package com.qda.book_management_service.collecttions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@Builder
@Document(collection = "book")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {

    @Id
    private String bookId;
    private String title;
    private String author;
    private String publisher;
    private String yearPublished;
    private String imageUrl;
    private String type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
