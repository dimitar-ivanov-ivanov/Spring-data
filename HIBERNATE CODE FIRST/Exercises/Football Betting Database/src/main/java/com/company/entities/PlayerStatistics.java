package com.company.entities;

import javax.persistence.*;

@Entity
@Table(name = "player_statistics")
public class PlayerStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "goals")
    private int scoredGoals;

    @Column(name = "assists")
    private int playerAssists;

    @Column(name = "minutes_played")
    private int minutesPlayed;

    public PlayerStatistics() {
    }

    public PlayerStatistics(Game game, Player player, int scoredGoals, int playerAssists, int minutesPlayed) {
        this.game = game;
        this.player = player;
        this.scoredGoals = scoredGoals;
        this.playerAssists = playerAssists;
        this.minutesPlayed = minutesPlayed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(int scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public int getPlayerAssists() {
        return playerAssists;
    }

    public void setPlayerAssists(int playerAssists) {
        this.playerAssists = playerAssists;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(int minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }
}
