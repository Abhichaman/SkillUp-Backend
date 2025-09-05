package skill_up.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skill_up.entity.Leaderboard;

import java.util.Optional;

@Repository
public interface LeaderboardRepo extends JpaRepository<Leaderboard,Long >  {
    Optional<Leaderboard> findByEmail(String email);
}
