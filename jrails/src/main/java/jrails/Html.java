package jrails;

public class Html {
    private String html = "";
    public String toString() {
        return html;
        //throw new UnsupportedOperationException();
    }

    public Html seq(Html h) {
        Html res = new Html();
        return res.make(html+h.toString());
        //throw new UnsupportedOperationException();
    }

    public Html br() {
        Html h = new Html();
        return h.make(html+"<br/>");
        //throw new UnsupportedOperationException();
    }

    public Html t(Object o) {
        // Use o.toString() to get the text for this
        Html h = new Html();
        return h.make(html+o.toString());
        //return this.seq(h);
        //throw new UnsupportedOperationException();
    }
    private Html make(Object o) {
        // Use o.toString() to get the text for this
        this.html = o.toString();
        return this;
        //throw new UnsupportedOperationException();
    }

    public Html p(Html child) {
        Html h = new Html();
        return h.make(html+"<p>"+child.toString()+"</p>");
        //throw new UnsupportedOperationException();
    }

    public Html div(Html child) {
        Html h = new Html();
        return h.make(html+"<div>"+child.toString()+"</div>");
        //throw new UnsupportedOperationException();
    }

    public Html strong(Html child) {
        Html h = new Html();
        return h.make(html+"<strong>"+child.toString()+"</strong>");
        //throw new UnsupportedOperationException();
    }

    public Html h1(Html child) {
        Html h = new Html();
        return h.make(html+"<h1>"+child.toString()+"</h1>");
        //throw new UnsupportedOperationException();
    }

    public Html tr(Html child) {
        Html h = new Html();
        return h.make(html+"<tr>"+child.toString()+"</tr>");
        //throw new UnsupportedOperationException();
    }

    public Html th(Html child) {
        Html h = new Html();
        return h.make(html+"<th>"+child.toString()+"</th>");
        //throw new UnsupportedOperationException();
    }

    public Html td(Html child) {
        Html h = new Html();
        return h.make(html+"<td>"+child.toString()+"</td>");
        //throw new UnsupportedOperationException();
    }

    public Html table(Html child) {
        Html h = new Html();
        return h.make(html+"<table>"+child.toString()+"</table>");
        //throw new UnsupportedOperationException();
    }

    public Html thead(Html child) {
        Html h = new Html();
        return h.make(html+"<thead>"+child.toString()+"</thead>");
        //throw new UnsupportedOperationException();
    }

    public Html tbody(Html child) {
        Html h = new Html();
        return h.make(html+"<tbody>"+child.toString()+"</tbody>");
        //throw new UnsupportedOperationException();
    }

    public Html textarea(String name, Html child) {
        Html h = new Html();
        return h.make(html+"<textarea name=\"" + name + "\">"+child.toString() + "</textarea>");
        //throw new UnsupportedOperationException();
    }

    public Html link_to(String text, String url) {
        Html h = new Html();
        return h.make(html+"<a href=\""+url+"\">"+text+"</a>");
    }

    public Html form(String action, Html child) {
        Html h = new Html();
        return h.make(html+"<form action=\""+action+"\" accept-charset=\"UTF-8\" method=\"post\">"+child.toString()+"</form>");
    }

    public Html submit(String value) {
        Html h = new Html();
        return h.make(html+"<input type=\"submit\" value=\"" + value + "\"/>");
    }
}