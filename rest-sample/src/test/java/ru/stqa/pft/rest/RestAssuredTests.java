package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests {

    @BeforeClass
    public void init(){
        RestAssured.authentication = RestAssured.basic("02276e82280489b4fa0cd56b59abad4c","");
    }

    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(1);
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("Test issue my 4").withDescription("Test description my 4");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues,oldIssues);
    }


 //   public void testCreateIssueID() throws IOException {
    //       Issue oldIssues = getIssueById(1);
//    }

    private Set<Issue> getIssues() throws IOException {
        String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues,new TypeToken<Set<Issue>>(){}.getType());
    }
    private Issue getIssueById(int issueid) throws IOException {
        String json = RestAssured.get("https://bugify.stqa.ru/api/issues/" + issueid + ".json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues,new TypeToken<Issue>(){}.getType());
    }
   public boolean isIssueOpen(int issueId) throws IOException {
        Issue issue = getIssueById(issueId);
        String status = issue.getState_name();
        return !(status.equals("Resolved") || status.equals("Closed"));
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
    private int createIssue(Issue newIssue) throws IOException {
        String json = RestAssured.given()
                .parameter("subject",newIssue.getSubject())
                .parameter("description", newIssue.getDescription())
                .post("https://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }
}
