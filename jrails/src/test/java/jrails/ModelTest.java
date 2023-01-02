package jrails;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class ModelTest {

    private model1 model;
    private model1 model0;
    private Model model2;
    private Model model3;
    private Model model4;
    private Model model5;
    private model1 one;
    private model1 two;
    private model1 three;
    private model1 four;
    private model1 five;
    private model1 six;
    


    @Before
    public void setUp() throws Exception {
        model = new model1();
        model2 = new Model(){@Column public String name = "fatma"; @Column public int x = 20;};
        model3 = new model1();
        model4 = new Model(){};
        model5 = new model1(){};
        one = new model1();
        two = new model1();
        three = new model1();
        four = new model1();
        five = new model1();
        six = new model1();
        model.save();
        model.name = "Not Labib";
        model.x = 40;
        //model = new Model(){@Column public String name = "NotLabib"; @Column public int x = 30;};
        model.save();
        model.save();
        model2.save();
        model3.save();
    }

    // @Test
    // public void id() {
    //     System.out.println("model's id: "+ model.id());
    //     System.out.println("model2's id: "+ model2.id());
    //     System.out.println("model3's id: "+ model3.id());
    //     System.out.println("model4's id: "+ model4.id());
    //     System.out.println(Model.all(model1.class).size());

    //     System.out.println(Model.all(Model.class).size());
    //     //System.out.println("hey");
    //     //assertThat(model.id(), notNullValue());
    // }
    // @Test
    // public void id2() {
    //     Model.reset();
    //     System.out.println("Test 2 ***********************");
    //     model2 = new model1(){@Column public String name = "fatma"; @Column public int x = 20;};
    //     model3 = new model1(){@Column public String name = null; @Column public int x = 20;};
    //     model2.save();
    //     model3.save();
    //     System.out.println("model2's id: "+ model2.id());
    //     System.out.println("model3's id: "+ model3.id());
    //     System.out.println("******************************");

    // }
    // @Test
    // public void id3() {
    //     Model.reset();
    //     System.out.println("Test 3 ***********************");
    //     model = new model1();
    //    //{@Column public String name = "fatma"; @Column public int x = 20;};
    //     model.save();
    //     model.name = "fatima";
    //     model.x = 20;
    //     model.save();
    //     System.out.println("model's id: "+ model.id());
    //     model.name = "labib";
    //     model.x = 99;
    //     System.out.println("model's after changing data id: "+ model.id());
    //     model.save();
    //     System.out.println("model's after save id: "+ model.id());
    //     System.out.println("******************************");
    //     //System.out.println("hee");

    // }
    // @Test
    // public void id4() {
    //     Model.reset();
    //     System.out.println("Test 4 ***********************");

    //     model = new model1();
    //     model.name = "labib";
    //     model.x = 100;
    //     model0 = model;
    //     model.save(); // id:1?
    //     model0.save(); //id:1?
    //     System.out.println("model's id: "+ model.id());
    //     model0.name = "fatma";
    //     model0.x = 100;
    //     model.save(); //name: fatma, x: 100 id: 1?
    //     model0.save(); // name: fatma, x: 100 id: 1?
    //     //dp has only one row

        
    //     System.out.println("model0's id: "+ model0.id());
    //     System.out.println("******************************");

    // }
    // @Test
    // public void id5() {
    //     Model.reset();
    //     System.out.println("Test 5 ***********************");
    //     model = new model1();
    //     model.name = "labib";
    //     model.x = 100;
    //     model0 = new model1();
    //     System.out.println("before saving: model's id: "+ model.id());
    //     System.out.println("before saving: model0's id: "+ model0.id());
    //     model0.name = "labib";
    //     model0.x = 100;
    //     model.save();
    //     model0.save();
    //     System.out.println("model's id: "+ model.id());
    //     System.out.println("model0's id: "+ model0.id());
    //     model.name = "fatma";
    //     model0.x = 200;
    //     model0.save();
    //     model.save();

    //     System.out.println("******************************");

    // }
    @Test
    public void destroy2() {
        Model.reset();
        System.out.println("Test 5 ***********************");
        model = new model1();
        model.name = "labib";
        model.x = 100;
        model0 = new model1();
        model0.name = "fatma";
        model0.x = 100;
        model.save();
        model0.save();
       //model0.destroy();
        System.out.println("******************************");

    }
    @Test
    public void destroy() {
        Model.reset();
        Model.all(model1.class);
        //one.save();
        one.name = "sijnfeij";
        one.x = 382983;
        one.save();
        two.save();
        three.save();
        four.save();
        five.save();
        six.save();
        System.out.println(one.id());
        System.out.println(two.id());
        System.out.println(three.id());
        System.out.println(four.id());
        System.out.println(five.id());
        System.out.println(six.id());
        one.save();
        two.save();
        three.save();
        four.save();
        five.save();
        six.name = "six";
        six.x = 38;
        six.save();
        System.out.println(one.id());
        System.out.println(two.id());
        System.out.println(three.id());
        System.out.println(four.id());
        System.out.println(five.id());
        System.out.println(six.id());
        System.out.println(Model.find(model1.class, 6).id());
        // model1 dub = Model.find(model1.class, 6);
        // Model.reset();
        // System.out.println("id: " + six.id());
        // dub.name = "sixdub";
        // dub.x = 329;
        // dub.save();
        //Model.find(model1.class, 6).save();
        

        System.out.println("******************************");

    }

    // @After
    // public void tearDown() throws Exception {
    //     //Model.reset();
    // }
}