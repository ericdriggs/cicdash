package com.github.ericdriggs.cicdash.jenkins;

import com.fasterxml.jackson.annotation.*;

import java.util.Map;

public class JenkinsJobQuery {

    private String jenkinsServerUrl;
    private String jobNamePattern;
    private Map<String, String> links;

    @JsonCreator
    public JenkinsJobQuery() {

    }

    public JenkinsJobQuery(String jenkinsServerUrl,
                           String jobNamePattern, Map<String, String> links) {
        this.jenkinsServerUrl = jenkinsServerUrl;
        this.jobNamePattern = jobNamePattern;
        this.links = links;
    }

    @JsonGetter("jenkinsServerUrl")
    public String getJenkinsServerUrl() {
        return jenkinsServerUrl;
    }

    @JsonSetter("jenkinsServerUrl")
    public JenkinsJobQuery setJenkinsServerUrl(String jenkinsServerUrl) {
        this.jenkinsServerUrl = jenkinsServerUrl;
        return this;
    }

    @JsonGetter("jobNamePattern")
    public String getJobNamePattern() {
        return jobNamePattern;
    }

    @JsonSetter("jobNamePattern")
    public JenkinsJobQuery setJobNamePattern(String jobNamePattern) {
        this.jobNamePattern = jobNamePattern;
        return this;
    }

    @JsonGetter("links")
    public Map<String, String> getLinks() {
        return links;
    }

    @JsonSetter("links")
    public JenkinsJobQuery setLinks(Map<String, String> links) {
        this.links = links;
        return this;
    }


    @Override
    public String toString() {
        return "JenkinsJobQuery{" +
                "jenkinsServerUrl='" + jenkinsServerUrl + '\'' +
                ", jobNamePattern='" + jobNamePattern + '\'' +
                ", links='" + links + '\'' +
                '}';
    }
}
