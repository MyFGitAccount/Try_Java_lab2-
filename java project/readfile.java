import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class readfile {
    public static int aCounter,bCounter,cCounter,dCounter,eCounter,fCounter,gCounter,hCounter,iCounter,jCounter,kCounter,lCounter,mCounter,nCounter,oCounter,pCounter,qCounter,rCounter,sCounter,tCounter,uCounter,vCounter,wCounter,xCounter,yCounter,zCounter=0;
    public static enum WordCounter{
        a(97),
        b(98),
        c(99),
        d(100),
        e(101),
        f(102),
        g(103),
        h(104),
        i(105),
        j(106),
        k(107),
        l(108),
        m(109),
        n(110),
        o(111),
        p(112),
        q(113),
        r(114),
        s(115),
        t(116),
        u(117),
        v(118),
        w(119),
        x(120),
        y(121),
        z(122),
        A(65),
        B(66),
        C(67),
        D(68),
        E(69),
        F(70),
        G(71),
        H(72),
        I(73),
        J(74),
        K(75),
        L(76),
        M(77),
        N(78),
        O(79),
        P(80),
        Q(81),
        R(82),
        S(83),
        T(84),
        U(85),
        V(86),
        W(87),
        X(88),
        Y(89),
        Z(90);

        public int val=0;
        WordCounter(int i){
            this.val=i;
        }
    }
    public static boolean isEqual(int num,WordCounter word1,WordCounter word2){
       if(num==word1.val||num==word2.val){
        return true;
       }
       return false;
    }
    public static void main(String []arg){
        try {
            FileInputStream in=new FileInputStream(arg[0]);
            int ch;
             

            while ((ch =in.read())!=-1){
                if(isEqual(ch, WordCounter.a, WordCounter.A)){
                    aCounter++;
                }
                else if(isEqual(ch,WordCounter.b, WordCounter.B)){
                    bCounter++;
                }
                else if(isEqual(ch,WordCounter.c, WordCounter.C)){
                    cCounter++;
                }
                else if(isEqual(ch,WordCounter.d, WordCounter.D)){
                    dCounter++;
                }
                else if(isEqual(ch,WordCounter.e, WordCounter.E)){
                    eCounter++;
                }
                else if(isEqual(ch,WordCounter.g, WordCounter.G)){
                    gCounter++;
                }
                  else if(isEqual(ch,WordCounter.h, WordCounter.H)){
                    hCounter++;
                }  else if(isEqual(ch,WordCounter.i, WordCounter.I)){
                    iCounter++;
                }  else if(isEqual(ch,WordCounter.j, WordCounter.J)){
                    jCounter++;
                }  else if(isEqual(ch,WordCounter.k, WordCounter.K)){
                    kCounter++;
                }  else if(isEqual(ch,WordCounter.l, WordCounter.L)){
                    lCounter++;
                }  else if(isEqual(ch,WordCounter.m, WordCounter.M)){
                    mCounter++;
                }  else if(isEqual(ch,WordCounter.n, WordCounter.N)){
                    nCounter++;
                }  else if(isEqual(ch,WordCounter.o, WordCounter.O)){
                    oCounter++;
                }  else if(isEqual(ch,WordCounter.p, WordCounter.P)){
                    pCounter++;
                }  else if(isEqual(ch,WordCounter.q, WordCounter.Q)){
                    qCounter++;
                }  else if(isEqual(ch,WordCounter.r, WordCounter.R)){
                    rCounter++;
                }  else if(isEqual(ch,WordCounter.s, WordCounter.S)){
                    sCounter++;
                }  else if(isEqual(ch,WordCounter.t, WordCounter.T)){
                    tCounter++;
                }  else if(isEqual(ch,WordCounter.u, WordCounter.U)){
                    uCounter++;
                }  else if(isEqual(ch,WordCounter.v, WordCounter.V)){
                    vCounter++;
                }  else if(isEqual(ch,WordCounter.w, WordCounter.W)){
                    wCounter++;
                }  else if(isEqual(ch,WordCounter.x, WordCounter.X)){
                    xCounter++;
                }  else if(isEqual(ch,WordCounter.y, WordCounter.Y)){
                    yCounter++;
                }  else if(isEqual(ch,WordCounter.z, WordCounter.Z)){
                    zCounter++;
                }
                
                //System.out.print((char)ch);
            }
            //System.out.println(String.format("a:%d", aCounter));

        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
           System.out.println(e);
        }
    }
}
