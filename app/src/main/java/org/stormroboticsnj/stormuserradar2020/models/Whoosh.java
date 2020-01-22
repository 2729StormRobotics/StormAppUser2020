package org.stormroboticsnj.stormuserradar2020.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * This class is a blueprint for a Whoosh object, which will be the future name of what was once
 * "DeepSpace" and "PowerUp". These objects are Entities of the Room database. You can treat them
 * like rows for our purposes, though in reality they act more like tables. Each Whoosh contains all
 * of the data scouted in a single match on a single tablet. Whooshes are differentiated by their
 * team and match numbers. No two whooshes should have the same team and match number. This will
 * cause a Primary Key conflict.
 */
@Entity(tableName = "whooshes", primaryKeys = {"team_num", "match_num"})
public class Whoosh {
    @ColumnInfo(name = "team_num") // Column for team number
    private int team; // Private integer variable for team number
    @ColumnInfo(name = "match_num") // Column for match number
    private int match; // Private integer variable for match number
    @ColumnInfo(name = "alliance") // Column for alliance color ("red" or "blue"
    private boolean alliance; // Private boolean variable for alliance color
    @ColumnInfo(name ="bottom_port_auto") // Column for bottom port score auto
    private int aPowerCell1; // Private integer variable for bottom port score auto
    @ColumnInfo(name ="outer_port_auto") // Column for outer port score auto
    private int aPowerCell2; // Private integer variable for outer port score
    @ColumnInfo(name ="inner_port_auto") // Column for inner port score
    private int aPowerCell3; // Private integer variable for inner port score
    @ColumnInfo(name = "power_cells_picked_up_auto") // Column for power cell pickup auto
    private int aPowerCellPickup; // Private integer variable for power cell pickup auto
    @ColumnInfo(name ="bottom_port_teleop") // Column for bottom port score teleop
    private int tPowerCell1; // Private integer variable for bottom port score teleop
    @ColumnInfo(name ="outer_port_teleop") //
    private int tPowerCell2;
    @ColumnInfo(name ="inner_port_teleop")
    private int tPowerCell3;
    @ColumnInfo(name ="rotation_control")
    private boolean rotationControl;
    @ColumnInfo(name ="position_control")
    private boolean positionControl;
    @ColumnInfo(name ="bottom_port_endgame")
    private int ePowerCell1;
    @ColumnInfo(name ="outer_port_endgame")
    private int ePowerCell2;
    @ColumnInfo(name ="inner_port_endgame")
    private int ePowerCell3;
    @ColumnInfo(name ="hang")
    private int hang;



    public Whoosh(int t, int m) {
        team = t;
        match = m;
    }

    public Whoosh(){}

    public int getTeam() {
        return team;
    }

    public int getMatch() {
        return match;
    }

    public boolean isAlliance() {
        return alliance;
    }

    public int getAPowerCell1() {
        return aPowerCell1;
    }

    public int getAPowerCell2() {
        return aPowerCell2;
    }

    public int getAPowerCell3() {
        return aPowerCell3;
    }

    public int getAPowerCellPickup() {
        return aPowerCellPickup;
    }

    public int getTPowerCell1() {
        return tPowerCell1;
    }

    public int getTPowerCell2() {
        return tPowerCell2;
    }

    public int getTPowerCell3() {
        return tPowerCell3;
    }

    public boolean isPositionControl() {
        return positionControl;
    }

    public boolean isRotationControl() {
        return rotationControl;
    }

    public int getEPowerCell1() {
        return ePowerCell1;
    }

    public int getEPowerCell2() {
        return ePowerCell2;
    }

    public int getEPowerCell3() {
        return ePowerCell3;
    }

    public int getHang() {
        return hang;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public void setMatch(int match) {
        this.match = match;
    }

    public void setAlliance(boolean alliance) {
        this.alliance = alliance;
    }

    public void setAPowerCell1(int aPowerCell1) {this.aPowerCell1 = aPowerCell1; }

    public void setAPowerCell2(int aPOwerCell2) {this.aPowerCell2 = aPOwerCell2; }

    public void setAPowerCell3(int aPowerCell3) {this.aPowerCell3 = aPowerCell3; }

    public void setAPowerCellPickup(int aPowerCellPickup) {this.aPowerCellPickup = aPowerCellPickup;
    }

    public void setTPowerCell1(int tPowerCell1) { this.tPowerCell1 = tPowerCell1; }

    public void setTPowerCell3(int tPowerCell3) { this.tPowerCell3 = tPowerCell3; }

    public void setTPowerCell2(int tPowerCell2) {this.tPowerCell2 = tPowerCell2; }

    public void setPositionControl(boolean positionControl) {this.positionControl = positionControl;
    }

    public void setRotationControl(boolean rotationControl) {this.rotationControl = rotationControl;
    }

    public void setEPowerCell1(int ePowerCell1) { this.ePowerCell1 = ePowerCell1; }

    public void setEPowerCell2(int ePowerCell2) { this.ePowerCell2 = ePowerCell2; }

    public void setEPowerCell3(int ePowerCell3) { this.ePowerCell3 = ePowerCell3; }

    public void setHang(int hang) { this.hang = hang; }

    @NonNull
    @Override
    public String toString() {
        return          team
                + "," + match
                + "," + (alliance ? "r" : "b")
                + "," + aPowerCell1
                + "," + aPowerCell2
                + "," + aPowerCell3
                + "," + aPowerCellPickup
                + "," + tPowerCell1
                + "," + tPowerCell2
                + "," + tPowerCell3
                + "," + (rotationControl ? "y" : "n")
                + "," + (positionControl ? "y" : "n")
                + "," + ePowerCell1
                + "," + ePowerCell2
                + "," + ePowerCell3
                + "," + hang
                + "|";
    }
}
