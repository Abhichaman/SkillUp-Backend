package skill_up.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skill_up.entity.Leaderboard;
import skill_up.service.LeaderboardService;

import java.util.List;

@RestController
@RequestMapping("/leaderboard")
@CrossOrigin(origins = "*")
public class LeaderboardController {

     @Autowired
    private LeaderboardService leaderboardService;

     @PostMapping("/submit")
    public Leaderboard submitScore(@RequestParam String name , @RequestParam String email, @RequestParam int score){
         return leaderboardService.SaveOrUpdateScore(name,email,score);
     }

     @GetMapping
    public List<Leaderboard> getLeaderboard(){
         return leaderboardService.getLeaderboard();
     }

}
