package net.designpattern.门面模式;

/**
 * 4, 邮局的出现, 邮寄信件方便多了,只需要 信的内容和地址  即可
 */
public class ModenPostOffice {

    private LetterProcess letterProcess = new LetterProcessImpl();

    // 警察例行检查用
    private Police letterPolice = new Police();

    //写信，封装，投递，一体化了
    public void sendLetter(String context, String address) {

        //帮你写信
        letterProcess.writeContext(context);
        //写好信封
        letterProcess.fillEnvelope(address);

        // 警察检查过程
        letterPolice.checkLetter(letterProcess);


        //把信放到信封中
        letterProcess.letterInotoEnvelope();
        //邮递信件
        letterProcess.sendLetter();
    }
}
