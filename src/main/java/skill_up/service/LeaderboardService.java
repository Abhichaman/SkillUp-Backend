package skill_up.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skill_up.entity.Leaderboard;
import skill_up.repo.LeaderboardRepo;

import java.util.List;

@Service
public class LeaderboardService {

    @Autowired
    private LeaderboardRepo leaderboardRepo;

    public Leaderboard SaveOrUpdateScore(String name, String email, int score){
        return leaderboardRepo.findByEmail(email)
                .map(existing ->{
                    if(score > existing.getScore()){
                        existing.setScore(score);
                        return leaderboardRepo.save(existing);
                    }
                    return existing;
                })
                .orElseGet(()->{
                    Leaderboard newEntry = new Leaderboard();
                    newEntry.setName(name);
                    newEntry.setEmail(email);
                    newEntry.setScore(score);
                    return leaderboardRepo.save(newEntry);
                });

    }


    public List<Leaderboard> getLeaderboard(){
        return leaderboardRepo.findAll()
                .stream()
                .sorted((a, b) -> Integer.compare(b.getScore(), a.getScore())) // DESC sort
                .toList();

    }
}
