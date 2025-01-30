package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;  // Import ObjectId

import java.time.LocalDateTime;

@Document // Marks this class as a MongoDB document
@Data
public class JournalEntry {

    @Id
    private ObjectId id;  // Use ObjectId instead of long or String

    private String title;
    private String content;
    private LocalDateTime date;

    // Default Constructor

//    public JournalEntry() {
//        this.id = new ObjectId(); // Automatically generate a new ObjectId
//        this.date = LocalDateTime.now(); // Default to current timestamp
//    }
//
//    // Parameterized Constructor
//    public JournalEntry(String title, String content) {
//        this.id = new ObjectId();
//        this.title = title;
//        this.content = content;
//        this.date = LocalDateTime.now();
//    }

    // Getters and Setters
//    public ObjectId getId() {
//        return id;
//    }
//
//    public void setId(ObjectId id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }
}
