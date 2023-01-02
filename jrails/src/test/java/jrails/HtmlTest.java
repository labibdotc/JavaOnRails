package jrails;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class HtmlTest {

    private Html html;

    @Before
    public void setUp() throws Exception {
        html = new Html();
    }

    @Test
    public void empty() {
        assertThat(View.empty().toString(), isEmptyString());
        System.out.println( (html.p(new Html().t("heeh")))
        
        .toString());
        //<p><strong>Title:</strong>Programming Languages: Build, Prove, and Compare</p>
        //<p><strong>Author:</strong>Norman Ramsey</p>
        //<p><strong>Copies:</strong>999</p>
        //<a href="/edit?id=...">Edit</a> | <a href="/">Back</a>

        //html = html.t()
        assertThat(html.t("").toString(),is(""));
    }

    @Test
    public void test2() {

        Html h = (new Html()).t("<p>Text of this</p>"); // Suppose h is an HTML object that represents the HTML code:
                // "<p>Text of this</p>"
        Html a = (new Html()).t("<div></div>");
        Html h1 = h.br().p(a); // For some Html objects a and b
        System.out.println(h1.toString()); //<br><p>Text of this</p></br><p>
    }
    
    @Test
    public void test3() {
        Html html = new Html();
        System.out.println(html.toString()); //<br><p>Text of this</p></br><p>
    }


}
