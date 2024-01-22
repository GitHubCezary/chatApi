package pl.chatApi.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.chatApi.Models.ChatUser;

import java.util.Optional;
@Repository
public interface ChatUserRepository extends CrudRepository<ChatUser, Long> {
    Optional<ChatUser> findByUsername(String username);
}
