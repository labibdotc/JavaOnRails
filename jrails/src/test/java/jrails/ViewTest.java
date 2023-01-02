package jrails;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.*;

public class ViewTest {
    private class Book extends Model{
        @Column public String title;
        @Column public String author;
        @Column public int num_copies;

    }
    private class Viewtest extends View {
        public static Html show(Book b) {
            return p(strong(t("Title:")).t(b.title))
            .
                p(strong(t("Author:")).t(b.author)).
                p(strong(t("Copies:")).t(b.num_copies)).
                t(link_to("Edit", "/edit?id=" + b.id())).t(" | ").
                t(link_to("Back", "/"));
        }
        public static void print(Book b){
            System.out.println(show(b).toString());
        }
    }
    @Test
    public void empty() {
        assertThat(View.empty().toString(), isEmptyString());
    }
    @Test
    public void test1() {
        
    }
    @Test
    public void booktest() {
        Book b = new Book();
        b.author = "Charles Dickens";
        b.title = "Programming in the age of Literature";
        b.num_copies = 999;
        Viewtest view = new Viewtest();
        view.print(b);
        //<p><strong>Title:</strong>Programming Languages: Build, Prove, and Compare</p>
        //<p><strong>Author:</strong>Norman Ramsey</p>
        //<p><strong>Copies:</strong>999</p>
        //<a href="/edit?id=...">Edit</a> | <a href="/">Back</a>

        //html = html.t()
        //assertThat(html.t("").toString(),is(""));
    }
}