package daronek6.cinema.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id) {
        super("id: " + id + ", not found!");
    }
}
