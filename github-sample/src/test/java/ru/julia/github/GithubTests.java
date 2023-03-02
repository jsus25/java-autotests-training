package ru.julia.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("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");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("jsus25", "java-autotests-training")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }

}
