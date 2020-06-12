package com.izen1231.ontime.model;

import java.io.Serializable;

public class Meta implements Serializable {
    public Meta() {}

    private int goal_h;
    private int goal_m;
    private int move_h;
    private int move_m;
    private int ready_h;
    private int ready_m;
    private int start_h;
    private int start_m;
    private int up_h;
    private int up_m;
    private String start_address;
    private String end_address;

    public int getGoal_h() {
        return goal_h;
    }

    public void setGoal_h(int goal_h) {
        this.goal_h = goal_h;
    }

    public int getGoal_m() {
        return goal_m;
    }

    public void setGoal_m(int goal_m) {
        this.goal_m = goal_m;
    }

    public int getMove_h() {
        return move_h;
    }

    public void setMove_h(int move_h) {
        this.move_h = move_h;
    }

    public int getMove_m() {
        return move_m;
    }

    public void setMove_m(int move_m) {
        this.move_m = move_m;
    }

    public int getReady_h() {
        return ready_h;
    }

    public void setReady_h(int ready_h) {
        this.ready_h = ready_h;
    }

    public int getReady_m() {
        return ready_m;
    }

    public void setReady_m(int ready_m) {
        this.ready_m = ready_m;
    }

    public int getStart_h() {
        return start_h;
    }

    public void setStart_h(int start_h) {
        this.start_h = start_h;
    }

    public int getStart_m() {
        return start_m;
    }

    public void setStart_m(int start_m) {
        this.start_m = start_m;
    }

    public int getUp_h() {
        return up_h;
    }

    public void setUp_h(int up_h) {
        this.up_h = up_h;
    }

    public int getUp_m() {
        return up_m;
    }

    public void setUp_m(int up_m) {
        this.up_m = up_m;
    }

    public String getStart_address() {
        return start_address;
    }

    public void setStart_address(String start_address) {
        this.start_address = start_address;
    }

    public String getEnd_address() {
        return end_address;
    }

    public void setEnd_address(String end_address) {
        this.end_address = end_address;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "goal_h=" + goal_h +
                ", goal_m=" + goal_m +
                ", move_h=" + move_h +
                ", move_m=" + move_m +
                ", ready_h=" + ready_h +
                ", ready_m=" + ready_m +
                ", start_h=" + start_h +
                ", start_m=" + start_m +
                ", up_h=" + up_h +
                ", up_m=" + up_m +
                ", start_address='" + start_address + '\'' +
                ", end_address='" + end_address + '\'' +
                '}';
    }
}

