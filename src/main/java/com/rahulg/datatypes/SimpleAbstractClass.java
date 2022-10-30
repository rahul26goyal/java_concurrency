package com.rahulg.datatypes;

public abstract class SimpleAbstractClass {

    public abstract String mustImplement();

    public String canOverride() {
        System.out.println("The is the default implementaiton of `canOverride` method in "+ this.getClass().getName());
        return "DefaultValue";
    }
}

// Concrete class which object can be instanciated.
class SimpleConcreteClassImpl extends SimpleAbstractClass {

    @Override
    public String mustImplement() {
        System.out.println("Implementation of `mustImplement`");
        return null;
    }

    public void newMethod() {
        System.out.println("Implementation of `newMethod`");
    }


    public  static void main(String ...args) {
        // SimpleAbstractClass abs = new SimpleAbstractClass(); // abstract class can not be instanciated.
        SimpleConcreteClassImpl concretClass = new SimpleConcreteClassImpl();
        concretClass.mustImplement();
        concretClass.canOverride();
        concretClass.newMethod();
        System.out.println("Decalred as abstract but instanciated using concret class.");
        SimpleAbstractClass concretClass2 = new SimpleConcreteClassImpl();
        concretClass2.mustImplement();
        concretClass2.canOverride();

//        java: cannot find symbol
//        symbol:   method newMethod()
        //concretClass2.newMethod(); // this can not be accessed as the variale is declared of type base class.


    }
}
