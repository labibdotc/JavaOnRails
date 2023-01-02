package jrails;

public class View {
    private static Html html = new Html(); //"Title" //<strong>Title<strong> //
    public static Html empty() {
        Html empty = new Html();
        html = empty.t("");
        return html;
        //throw new UnsupportedOperationException();
    }

    public static Html br() {
        Html h = new Html();
        return h.br();
        //throw new UnsupportedOperationException();
    }

    public static Html t(Object o) {
        Html h = new Html();
        return h.t(o);
        //throw new UnsupportedOperationException();
    }

    public static Html p(Html child) {
        Html h = new Html();
        return h.p(child);
        //throw new UnsupportedOperationException();
    }

    public static Html div(Html child) {
        Html h = new Html();
        return h.div(child);
        //throw new UnsupportedOperationException();
    }

    public static Html strong(Html child) {
        Html h = new Html();
        return h.strong(child);
        //throw new UnsupportedOperationException();
    }

    public static Html h1(Html child) {
        Html h = new Html();
        return h.h1(child);
        //throw new UnsupportedOperationException();
    }

    public static Html tr(Html child) {
        Html h = new Html();
        return h.tr(child);
        //throw new UnsupportedOperationException();
    }

    public static Html th(Html child) {
        Html h = new Html();
        return h.th(child);
        //throw new UnsupportedOperationException();
    }

    public static Html td(Html child) {
        Html h = new Html();
        return h.td(child);
        //throw new UnsupportedOperationException();
    }

    public static Html table(Html child) {
        Html h = new Html();
        return h.table(child);
        //throw new UnsupportedOperationException();
    }

    public static Html thead(Html child) {
        Html h = new Html();
        return h.thead(child);
        //throw new UnsupportedOperationException();
    }

    public static Html tbody(Html child) {
        Html h = new Html();
        return h.tbody(child);
        //throw new UnsupportedOperationException();
    }

    public static Html textarea(String name, Html child) {
        Html h = new Html();
        return h.textarea(name, child);
        //throw new UnsupportedOperationException();
    }

    public static Html link_to(String text, String url) {
        Html h = new Html();
        return h.link_to(text,url);
        //throw new UnsupportedOperationException();
    }

    public static Html form(String action, Html child) {
        Html h = new Html();
        return h.form(action, child);
        //throw new UnsupportedOperationException();
    }

    public static Html submit(String value) {
        Html h = new Html();
        return h.submit(value);
        //throw new UnsupportedOperationException();
    }
}