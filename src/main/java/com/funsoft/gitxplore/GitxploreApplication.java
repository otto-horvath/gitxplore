package com.funsoft.gitxplore;

import java.io.File;
import java.time.Instant;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.filter.AuthorRevFilter;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GitxploreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GitxploreApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Olá enfermeira!");

			Repository repo = new FileRepositoryBuilder()
					.setGitDir(new File("/Users/ottohenrique/Documents/workspace/aoc2022/.git")).build();

			try (Git git = new Git(repo)) {
				var walk = git.log().setMaxCount(10).setRevFilter(AuthorRevFilter.create("ottoh")).call();
				for (RevCommit commit : walk) {
					System.out.println(Instant.ofEpochSecond(commit.getCommitTime()) + ": " + commit.getShortMessage()
							+ " - " + commit.getName());
				}
			}
		};
	}
}
