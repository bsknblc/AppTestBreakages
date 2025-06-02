package com.WebApps.Benchmark.Model;

import java.util.List;

public class CommitChanges {
    private String sha;
    private Commit commit;
    private List<FileChange> files;



    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public List<FileChange> getFiles() {
        return files;
    }

    public void setFiles(List<FileChange> files) {
        this.files = files;
    }

    public static class Commit {
        private String message;
        private Author author;

        public Commit(String message, Author author) {
            this.message = message;
            this.author = author;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public static class Author {
            private String name;
            private String email;
            private String date;

            public Author(String name, String email, String date) {
                this.name = name;
                this.email = email;
                this.date = date;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class FileChange {
        private String filename;
        private int additions;
        private int deletions;
        private String changes;
        private String status;
        private String patch;

        public FileChange(String filename, int additions, int deletions, String changes, String status, String patch) {
            this.filename = filename;
            this.additions = additions;
            this.deletions = deletions;
            this.changes = changes;
            this.status = status;
            this.patch = patch;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public int getAdditions() {
            return additions;
        }

        public void setAdditions(int additions) {
            this.additions = additions;
        }

        public int getDeletions() {
            return deletions;
        }

        public void setDeletions(int deletions) {
            this.deletions = deletions;
        }

        public String getChanges() {
            return changes;
        }

        public void setChanges(String changes) {
            this.changes = changes;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPatch() {
            return patch;
        }

        public void setPatch(String patch) {
            this.patch = patch;
        }
    }
}