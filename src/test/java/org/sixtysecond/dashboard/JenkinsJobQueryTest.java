package org.sixtysecond.dashboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.ericdriggs.cicdash.endpoint.JenkinsJobQueryResource;
import com.github.ericdriggs.cicdash.jenkins.JenkinsJobQuery;
import com.github.ericdriggs.cicdash.jenkins.JenkinsJobQueryCallable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

//import org.testng.annotations.Test;


/**
 * Created by edriggs on 11/2/15.
 */
public class JenkinsJobQueryTest {
    final static int wiremockServerPort = 8080;

//    protected static WireMockServer wireMockServer = new WireMockServer(wiremockServerPort);

//    public static void stubJobFixtureGet(String fixtureName) {
//
//        stubFor(WireMock.get(urlEqualTo("job/" + fixtureName + "/api/json.*?"))
//                .willReturn(aResponse().withBody(fixture("fixtures/" + fixtureName + ".json"))));
//    }

//    @BeforeClass
//    public static void beforeClass() {
//
//        wireMockServer.start();
//        configureFor(wireMockServer.port());
//
//        stubFor(WireMock.get(urlEqualTo("/api/json.*?"))
//                .willReturn(aResponse().withBody(fixture("fixtures/jobs.json"))));
//
//        stubJobFixtureGet(FixtureJson.ACCUMULO_1_6);
//        stubJobFixtureGet(FixtureJson.ACCUMULO_1_7);
//        stubJobFixtureGet(FixtureJson.ACCUMULO_MASTER);
//        stubJobFixtureGet(FixtureJson.ACCUMULO_PULL_REQUESTS);
//
//
//
//    }


//    @AfterClass
//    public static void stopServer() {
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        wireMockServer.stop();
//    }

//    @After
//    public void after() {
//        WireMock.shutdownServer();
//    }

    @Test
    public void jenkinsJobQueryGetTest() throws ExecutionException, InterruptedException, JsonProcessingException {
        //        String jenkinsServerUrl = "https://builds.apache.org";
        //        String jobNamePattern = "Ambari.*?";
//        System.out.println(wireMockServer.listAllStubMappings());

        String jenkinsServerUrl = "http://builds.apache.org";
        String jobNamePattern = "Accumulo-1.7";
        JenkinsJobQuery jenkinsJobQuery = new JenkinsJobQuery(jenkinsServerUrl, jobNamePattern, null);
        JSONObject response = new JenkinsJobQueryCallable(jenkinsJobQuery).call();
        System.out.println("response=" + response);
    }

    @Test
    public void jenkinsJobQueryGetMultipleJobsTest() throws ExecutionException, InterruptedException, JsonProcessingException {
        //        String jenkinsServerUrl = "https://builds.apache.org";
        //        String jobNamePattern = "Ambari.*?";

        String jenkinsServerUrl = "https://builds.apache.org";
        String jobNamePattern = "ActiveMQ.*?";
        JenkinsJobQuery jenkinsJobQuery = new JenkinsJobQuery(jenkinsServerUrl, jobNamePattern, null);
        JSONObject response = new JenkinsJobQueryCallable(jenkinsJobQuery).call();
        System.out.println("response=" + response);
    }


    @Test
    public void jenkinsJobQueryPostMultiplePatternsTest() {
        List<JenkinsJobQuery> jenkinsJobQueryList = new ArrayList<JenkinsJobQuery>();
        {
            String jenkinsServerUrl = "https://builds.apache.org";
            String jobNamePattern = "Accumulo-1.6";
            Map<String,String> links  = new HashMap<String,String>();
            links.put("foo", "http://foo.com");
            links.put("bar", "http://bar.com");
            jenkinsJobQueryList.add(new JenkinsJobQuery(jenkinsServerUrl, jobNamePattern, links));
        }
        {
            String jenkinsServerUrl = "https://builds.apache.org";
            String jobNamePattern = "Accumulo-1.7";
            jenkinsJobQueryList.add(new JenkinsJobQuery(jenkinsServerUrl, jobNamePattern, null));
        }

        Response response =
                new JenkinsJobQueryResource()
                        .queryJenkins(jenkinsJobQueryList);
        assertThat(response.getStatus(), is(200));
        String jsonString = (String) response.getEntity();
        JSONArray jsonArray = new JSONArray(jsonString);
        assertThat(jsonArray.length(), is(greaterThan(0)));
        System.out.println("jsonArray=" + response.getEntity()
                .toString());

    }

    // @Test //disabled for faster debug build
    public void jenkinsJobQueryPostMultipleJobMatchedTest() {
        List<JenkinsJobQuery> jenkinsJobQueryList = new ArrayList<JenkinsJobQuery>();
        {
            String jenkinsServerUrl = "https://builds.apache.org";
            String jobNamePattern = "ActiveMQ.*?";
            jenkinsJobQueryList.add(new JenkinsJobQuery(jenkinsServerUrl, jobNamePattern, null));
        }

        Response response =
                new JenkinsJobQueryResource()
                        .queryJenkins(jenkinsJobQueryList);
        assertThat(response.getStatus(), is(200));
        String jsonString = (String) response.getEntity();
        JSONArray jsonArray = new JSONArray(jsonString);
        assertThat(jsonArray.length(), is(1));


        System.out.println("jsonArray=" + response.getEntity()
                .toString());

    }

}
