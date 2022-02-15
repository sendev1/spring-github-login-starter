package io.javabrains.entity;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class MessageId {
    
    @PrimaryKeyColumn(name="userId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String userId;

    @PrimaryKeyColumn(name="folderName", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String folderName;

    @PrimaryKeyColumn(name="createdTimeUUID", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    private UUID timeUuid;    

    public MessageId() {
    }

    public MessageId(String userId, String folderName, UUID timeUuid) {
        this.userId = userId;
        this.folderName = folderName;
        this.timeUuid = timeUuid;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public UUID getTimeUuid() {
        return timeUuid;
    }

    public void setTimeUuid(UUID timeUuid) {
        this.timeUuid = timeUuid;
    }
}
