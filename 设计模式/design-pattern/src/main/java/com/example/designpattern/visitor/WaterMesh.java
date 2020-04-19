package com.example.designpattern.visitor;

public class WaterMesh implements Acceptor {
    @Override
    public void accept(FruitVisitor visitor) {
        visitor.visit(this);
    }
}
