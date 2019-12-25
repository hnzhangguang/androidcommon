package net.designpattern.builder;

/**
 * 抽象建造
 * @author liaowp
 *
 */
public interface Builder {

    /**
     * 打基础
     */
    public void  buildBasic();
    
    /**
     * 砌墙
     */
    public void  buildWalls();
    
    /**
     * 封顶
     */
    public void  roofed();
    
    /**
     * 造房子
     * @return
     */
    public Product buildProduct();
}