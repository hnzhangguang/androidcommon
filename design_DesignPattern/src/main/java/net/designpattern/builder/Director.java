package net.designpattern.builder;

/**
 * 建造者
 * @author liaowp
 *
 */
public class Director {
    
    public Product constructProduct(ConcreteBuilder concreteBuilder){
        concreteBuilder.buildBasic();
        concreteBuilder.buildWalls();
        concreteBuilder.roofed();
        return concreteBuilder.buildProduct();
    }
}