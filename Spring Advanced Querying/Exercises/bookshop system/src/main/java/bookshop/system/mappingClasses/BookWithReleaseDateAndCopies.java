package bookshop.system.mappingClasses;

import java.time.LocalDate;

public class BookWithReleaseDateAndCopies
        implements Comparable<BookWithReleaseDateAndCopies> {

    private String title;
    private LocalDate releaseDate;
    private Long copies;

    public BookWithReleaseDateAndCopies(String title, LocalDate releaseDate, Long copies) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.copies = copies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Long getCopies() {
        return copies;
    }

    public void setCopies(Long copies) {
        this.copies = copies;
    }

    @Override
    public int compareTo(BookWithReleaseDateAndCopies other) {
        int releaseDateDesc = this.getReleaseDate().compareTo(other.getReleaseDate()) * (-1);
        int bookTitleAsc = this.getTitle().compareTo(other.getTitle());

        if (releaseDateDesc != 0) {
            return releaseDateDesc;
        }

        return bookTitleAsc;
    }

    @Override
    public String toString() {
        return "BookWithReleaseDateAndCopies{" +
                "title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", copies=" + copies +
                '}';
    }
}
