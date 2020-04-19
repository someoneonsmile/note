package com.example.designpattern.visitor;

public class Apple implements Acceptor{

    @Override
    public void accept(FruitVisitor visitor) {
        visitor.visit(this);
    }
}
