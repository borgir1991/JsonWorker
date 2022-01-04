import javax.swing.*;
import java.io.File;

public class Service extends JFrame{

    private JPanel Panel;

    public Service(){
    setContentPane(Panel);
        setTitle("MiniService");
        setSize(250, 150);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args){
    try {
        Service service = new Service();
        File file = new File("c:\\00\\log.json");

        String jsondata = JsonWorker.JsnReader(file);
        JsonWorker.DataProcessing(jsondata);

        JsonWorker.LogWork();



    }catch (Exception e){
        e.printStackTrace();
        JsonWorker.MessageBox("Nem adta ki: "+ e.toString(),"Error");
        }
    }

}
