public class Users {
    String id;
    String comment;
    String timeSpent;
    String author;
    String authorFullName;
    String created;
    String startDate;
    String updateAuthor;
    String updateAuthorFullName;
    String updated;

    public Users(String id, String comment, String timeSpent, String author, String authorFullName, String created,
                 String startDate, String updateAuthor, String updateAuthorFullName, String updated) {
        this.id = id;
        this.comment = comment;
        this.timeSpent = timeSpent;
        this.author = author;
        this.authorFullName = authorFullName;
        this.created = created;
        this.startDate = startDate;
        this.updateAuthor = updateAuthor;
        this.updateAuthorFullName = updateAuthorFullName;
        this.updated = updated;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(String timeSpent) {
        this.timeSpent = timeSpent;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public void setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getUpdateAuthor() {
        return updateAuthor;
    }

    public void setUpdateAuthor(String updateAuthor) {
        this.updateAuthor = updateAuthor;
    }

    public String getUpdateAuthorFullName() {
        return updateAuthorFullName;
    }

    public void setUpdateAuthorFullName(String updateAuthorFullName) {
        this.updateAuthorFullName = updateAuthorFullName;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
