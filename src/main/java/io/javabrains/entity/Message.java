package io.javabrains.entity;

import java.util.List;

import com.datastax.oss.driver.api.core.uuid.Uuids;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

@Table("message_by_user_folder")
public class Message {
    
    @PrimaryKey
    private MessageId messageId;

    @CassandraType(type = Name.LIST, typeArguments = Name.TEXT)
    private List<String> tos;

    @CassandraType(type = Name.TEXT)
    private String subject;

    @CassandraType(type = Name.BOOLEAN)
    private boolean isRead;

    @Transient
    private String perttyTime;

    public Message() {
    }

    public Message(String userId, String folderName, List<String> tos, String subject, boolean isRead) {
        this.messageId = new MessageId(userId, folderName, Uuids.timeBased());
        this.tos = tos;
        this.subject = subject;
        this.isRead = isRead;
    }

    public MessageId getMessageId() {
        return messageId;
    }

    public void setMessageId(MessageId messageId) {
        this.messageId = messageId;
    }

    public List<String> getTos() {
        return tos;
    }

    public void setTos(List<String> tos) {
        this.tos = tos;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String getPerttyTime() {
        return perttyTime;
    }

    public void setPerttyTime(String perttyTime) {
        this.perttyTime = perttyTime;
    }

    
}
