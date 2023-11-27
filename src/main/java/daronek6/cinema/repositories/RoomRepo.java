package daronek6.cinema.repositories;

import daronek6.cinema.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room, Long> {
}
