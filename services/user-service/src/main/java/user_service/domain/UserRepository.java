package user_service.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import edu.cmu.ini.ericsson.practicum.models.userService.User;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends MongoRepository<User, String> {

    public User findByName(String name);
    public User findById(String id);
    
}
