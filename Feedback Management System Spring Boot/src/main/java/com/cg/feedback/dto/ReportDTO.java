package com.cg.feedback.dto;

import java.util.ArrayList;
import java.util.List;

import com.cg.feedback.exceptions.CustomException;

public class ReportDTO {
    private float q1;
    private float q2;
    private float q3;
    private float q4;
    private float q5;
    private List<String> comments;
    private List<String> suggestions;

    public ReportDTO(List<FeedbackDTO> feedbacks) throws CustomException {
        if (feedbacks.size() < 1 || feedbacks == null)
            throw new CustomException("Feedbacks are Empty");
        q1 = 0f;
        q2 = 0f;
        q3 = 0f;
        q4 = 0f;
        q5 = 0f;
        int count = feedbacks.size();
        this.comments = new ArrayList<String>(count);
        this.suggestions = new ArrayList<String>(count);
        feedbacks.stream().forEach((FeedbackDTO f) -> {
            q1 += f.getQ1();
            q2 += f.getQ2();
            q3 += f.getQ3();
            q4 += f.getQ4();
            q5 += f.getQ5();
            this.comments.add(f.getComments());
            this.suggestions.add(f.getSuggestions());
        });
        q1 = q1 / count;
        q2 = q2 / count;
        q3 = q3 / count;
        q4 = q4 / count;
        q5 = q5 / count;
    }

    public float getQ1() {
        return q1;
    }

    public void setQ1(float q1) {
        this.q1 = q1;
    }

    public float getQ2() {
        return q2;
    }

    public void setQ2(float q2) {
        this.q2 = q2;
    }

    public float getQ3() {
        return q3;
    }

    public void setQ3(float q3) {
        this.q3 = q3;
    }

    public float getQ4() {
        return q4;
    }

    public void setQ4(float q4) {
        this.q4 = q4;
    }

    public float getQ5() {
        return q5;
    }

    public void setQ5(float q5) {
        this.q5 = q5;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }

    @Override
    public String toString() {
        return "ReportDTO [comments=" + comments.toString() + ", q1=" + q1 + ", q2=" + q2 + ", q3=" + q3 + ", q4="
                + q4 + ", q5=" + q5 + ", suggestions=" + suggestions.toString() + "]";
    }
}