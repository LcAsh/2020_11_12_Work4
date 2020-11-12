import java.io.*;

class myWriter extends Thread{
    private PipedOutputStream outStream;
    private String messages[] = {"Monday","Tuesday","Wednsday","Thursday","Friday","Saturday","Sunday"};
    //从序列化对象流中读数据然后out到线程流中
    public myWriter(PipedOutputStream o)
    {
        outStream = o;
    }
    public void run(){
        PrintStream p =new PrintStream(outStream);
        for(int i=0;i<messages.length;i++){
            p.println(messages[i]);
            p.flush();
            System.out.println("Write:"+ messages[i]);
        }
        p.close();
        p =null;
    }
}

class myReader extends Thread{
    private PipedInputStream inStream;
    public myReader(PipedInputStream i){
        inStream = i;
    }
    public void run(){
        String line;
        boolean reading = true;
        BufferedReader d = new BufferedReader (new InputStreamReader(inStream));
        while (reading && d!= null){
            try{
                line = d.readLine();
                if(line != null)System.out.println("Read:"+line);
                else reading = false;
            }catch(IOException e){}
        }
        try{
            Thread.currentThread().sleep(4000);
        }catch(InterruptedException e){}
    }
}

public class Pipethread {
    public static void main(String args[]){
        Pipethread thisPipe = new Pipethread();
        thisPipe.process();
    }

    private void process() {
        // TODO Auto-generated method stub
        PipedInputStream inStream;
        PipedOutputStream outStream;
        try{
            outStream = new PipedOutputStream();
            inStream = new PipedInputStream(outStream);
            new myWriter(outStream).start();//线程启动
            new myReader(inStream).start();
        }catch(IOException e){}

    }
}