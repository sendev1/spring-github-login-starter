package io.javabrains.entity;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface MessageRepository extends CassandraRepository<Message, MessageId> {
    List<Message> findAllByMessageId_UserIdAndMessageId_FolderName(String userId, String folderName);
}
