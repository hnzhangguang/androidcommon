package net.designpattern.门面模式;

/**
 * 门面模式   -   FacadePattern
 * <p>
 * 1
 *
 * @定义一个写信的过程 , 顺序是不能乱的
 */
public interface LetterProcess {


    //首先要写信的内容
    public void writeContext(String context);

    //其次写信封
    public void fillEnvelope(String address);


    // 有可能信件需要例行检查通过好才能继续走下面的流程
    public void checkLetter(Police police);

    //把信放到信封里
    public void letterInotoEnvelope();

    //然后邮递
    public void sendLetter();


}
