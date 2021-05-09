package com.satyaki.courierdemo;

import java.util.List;
import java.util.Map;

class BasicInformationClass {

    String duration,tasks;
    List<String> participants,tasktopics;
    List<String> scores;

    public BasicInformationClass() {

    }

    public BasicInformationClass(String duration, String tasks, List<String> participants,List<String> scores,List<String> tasktopics) {
        this.duration = duration;
        this.tasks = tasks;
        this.participants = participants;
        this.scores = scores;
        this.tasktopics=tasktopics;
    }

    public String getDuration() {
        return duration;
    }

    public String getTasks() {
        return tasks;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public List<String> getScores() {
        return scores;
    }

    public List<String> getTasktopics() {
        return tasktopics;
    }
}
