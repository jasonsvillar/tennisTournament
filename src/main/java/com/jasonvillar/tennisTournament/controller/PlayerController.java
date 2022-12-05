package com.jasonvillar.tennisTournament.controller;

import com.jasonvillar.tennisTournament.model.Player;
import com.jasonvillar.tennisTournament.model.enums.GenderEnum;
import com.jasonvillar.tennisTournament.service.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/player")
@Api(value = "api/v1", tags = "Player Controller")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @ApiOperation(value = "Get hardcoded names")
    @GetMapping("/getDefaultPlayerNames")
    public Map<String, GenderEnum> getDefaultPlayerNames() {
        return playerService.getDefaultPlayerNames();
    }

    @ApiOperation(value = "Get hardcoded surnames")
    @GetMapping("/getDefaultPlayerSurnames")
    public List<String> getDefaultPlayerSurnames() {
        return playerService.getDefaultPlayerSurnames();
    }

    @ApiOperation(value = "Filtered hardcoded names by gender")
    @GetMapping("/getDefaultPlayerNamesByGender/{gender}")
    public Map<String, GenderEnum> getDefaultPlayerNamesByGender(@PathVariable GenderEnum gender) {
        return playerService.getDefaultPlayerNamesByGender(gender);
    }

    @ApiOperation(value = "Get one random name")
    @GetMapping("/getRandomNameByGender/{gender}")
    public String getRandomNameByGender(@PathVariable GenderEnum gender) {
        return playerService.getRandomNameByGender(gender);
    }

    @ApiOperation(value = "Get one random surname")
    @GetMapping("/getRandomSurname")
    public String getRandomSurname() {
        return playerService.getRandomSurname();
    }

    @ApiOperation(value = "Get a new random player by gender")
    @GetMapping("/newRandomPlayerByGender/{gender}")
    public Player newRandomPlayerByGender(@PathVariable GenderEnum gender) {
        return playerService.newRandomPlayerByGender(gender);
    }

    @ApiOperation(value = "Get a list of players by gender and total tournament rounds")
    @GetMapping("/newPlayersByGenderAndRounds/{gender}/{rounds}")
    public List<Player> newPlayersByGenderAndRounds(@PathVariable GenderEnum gender, @PathVariable int rounds) {
        return playerService.newPlayersByGenderAndRounds(gender, rounds);
    }
}
