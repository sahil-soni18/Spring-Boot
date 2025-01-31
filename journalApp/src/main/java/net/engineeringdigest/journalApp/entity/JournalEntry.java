package net.engineeringdigest.journalApp.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import net.engineeringdigest.journalApp.services.ObjectIdSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;  // Import ObjectId

import java.time.LocalDateTime;

@Document // Marks this class as a MongoDB document
@Data
@NoArgsConstructor
public class JournalEntry {

    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;  // Use ObjectId instead of long or String

    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;

    // Project lombok have automatically on runtime added getters, setters or constructors to reduce the boiler plate code.
}
