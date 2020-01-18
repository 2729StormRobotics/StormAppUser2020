package org.stormroboticsnj.stormuserradar2020.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

/**
 * This class is a blueprint for a Whoosh object, which will be the future name of what was once
 * "DeepSpace" and "PowerUp". These objects are Entities of the Room database. You can treat them
 * like rows for our purposes, though in reality they act more like tables. Each Whoosh contains all
 * of the data scouted in a single match on a single tablet. Whooshes are differentiated by their
 * team and match numbers. No two whooshes should have the same team and match number. This will
 * cause a Primary Key conflict.
 */
@Entity(tableName = "whooshes", primaryKeys = {"team_num", "match_num"})
public class WhooshTeleop {
    @ColumnInfo(name = "team_num") // Column for team number
    private int team; // Private integer variable for team number
    @ColumnInfo(name = "match_num") // Column for match number
    private int match; // Private integer variable for match number
    @ColumnInfo(name = "alliance") // Column for alliance color ("red" or "blue"
    private boolean alliance; // Private boolean variable for alliance color
    @ColumnInfo(name = "score_lower_port") // Column for bottom port score
    private int scoreLVL1; // Private integer variable for bottom port score
    @ColumnInfo(name = "score_outer_port") // Column for outer port score
    private int scoreLVL2; // Private integer variable for outer port score
    @ColumnInfo(name = "score_inner_port") // Column for inner port score
    private int scoreLVL3; // Private integer variable for inner port score
    @ColumnInfo(name = "rotation_control") // Column for control panel wheel rotation
    private boolean isRotated; // Private boolean variable for wheel rotation
    @ColumnInfo(name = "rotation_control") // Column for control panel wheel rotation
    private boolean isPosition; // Private boolean variable for wheel rotation

    /**
     * Constructor with team and match variables to construct data set
     * @param t: team number variable
     * @param m: match number variable
     */
    public WhooshTeleop(int t, int m) {
        team = t;
        match = m;
    }

    /**
     * Blank constructor - mandatory
     */
    public WhooshTeleop(){}

    /**
     * Getting the team number
     * @return team number
     */
    public int getTeam() {
        return team;
    }

    /**
     * Getting the match number
     * @return match number
     */
    public int getMatch() {
        return match;
    }

    /**
     * Getting the bottom port score
     * @return bottom port score
     */
    public int getScoreLVL1() {
        return scoreLVL1;
    }

    /**
     * Getting the outer port score
     * @return outer port score
     */
    public int getScoreLVL2() {
        return scoreLVL2;
    }

    /**
     * Getting the inner port score
     * @return inner port score
     */
    public int getScoreLVL3() { return scoreLVL3; }

    /**
     * Getting the rotation control progress
     * @return boolean isRotated
     */
    public boolean isRotated() {
        return isRotated;
    }

    /**
     * Getting the position control progress
     * @return boolean isPosition
     */
    public boolean isPosition() {
        return isPosition;
    }

    /**
     * Getting the alliance color through boolean variable
     * @return boolean alliance: "true" is red, "false" is blue
     */
    public boolean isAlliance() {
        return alliance;
    }

    /**
     * Set the team variable to inputted team number
     * @param team
     */
    public void setTeam(int team) {
        this.team = team;
    }

    /**
     * Set the match variable to inputted match number
     * @param match
     */
    public void setMatch(int match) {
        this.match = match;
    }

    /**
     * Set the alliance variable to inputted alliance result
     * @param alliance
     */
    public void setAlliance(boolean alliance) {
        this.alliance = alliance;
    }

    /**
     * Set the bottom port score to inputted bottom port score
     * @param score
     */
    public void setScoreLVL1(int score) {
        this.scoreLVL1 = score;
    }

    /**
     * Set the outer port score to inputted outer port score
     * @param scoreTwo
     */
    public void setScoreLVL2(int scoreTwo) {
        this.scoreLVL2 = scoreTwo;
    }

    /**
     * Set the inner port score to inputted inner port score
     * @param scoreThree
     */
    public void setScoreLVL3(int scoreThree) { this.scoreLVL3 = scoreThree; }

    /**
     * Set rotation to inputted data
     * @param isR
     */
    public void setRotated(boolean isR) {
        isRotated = isR;
    }

    /**
     * Set position to inputted data
     * @param isP
     */
    public void setPosition(boolean isP) {
        isPosition = isP;
    }

    @NonNull
    @Override
    public String toString() {
        return          team
                + "," + match
                + "," + (alliance ? "r" : "b")
                + "," + scoreLVL1
                + "," + scoreLVL2
                + "," + scoreLVL3
                + "|";
    }
}
