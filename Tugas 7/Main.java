class Library {
    private Book book;

    public void addBook(Book b) {
        this.book = b;
        System.out.println("Book added: " + book.getTitle());
    }
}

class Book {
    private String title;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

class Member {
    public void readBook(Book book) {
        System.out.println("Reading: " + book.getTitle());
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Book book = new Book("Java Programming");
        Member member = new Member();

        library.addBook(book);
        member.readBook(book);
    }
}
